package mainclasses;

import java.util.HashMap;
import java.util.Map;

import util.Utilitario;

/**
 * Classe responsavel pelo gerenciamento das sessoes do Sistema.
 * 
 */
public class GerenciaSessao {
	/* Mapa com sessaoID e login */
	private Map<String, String> sessoes;

	public GerenciaSessao() {
		this.sessoes = new HashMap<String, String>();
	}

	/**
	 * Exclui uma sessao do conjunto de sessoes presentes no sistema.
	 * 
	 * @param login
	 *            Login correspondente a sessao a ser excluida.
	 */
	public void encerrarSessao(String login) {
		this.sessoes.remove(login);
	}

	/**
	 * Da inicio a uma sessao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param senha
	 *            Senha do usuario.
	 * @return Id da sessao.
	 */
	public String abrirSessao(String login, String senha) {
		String id = "sessao" + login;
		if (!existeSessao(id)) {
			this.sessoes.put(id, login);
			return id;
		}
		return null;
	}

	/**
	 * Retorna o login do usuario de uma determinada sessao a partir do id da
	 * sessao.
	 * 
	 * @param idSessao
	 *            Id da sessao correspondente.
	 * @return Login do usuario.
	 * @throws Exception
	 */
	public String getLogin(String idSessao) throws Exception {
		verificaSessao(idSessao);
		return this.sessoes.get(idSessao);
	}

	/**
	 * Verifica se uma sessao existe no sistema a partir de um id de sessao.
	 * 
	 * @param idsessao
	 *            Id da sessao.
	 * @return true caso a sessao exista, false caso contrario.
	 */
	public boolean existeSessao(String idsessao) {
		return this.sessoes.containsKey(idsessao);
	}

	/**
	 * Verifica se a sessao e valida/existe.
	 * 
	 * @param idsessao
	 *            Id da sessao a ser verificada.
	 * @throws Exception
	 *             {@link SessaoInvalidaException, SessaoInexistenteException}
	 */
	public void verificaSessao(String idSessao) throws Exception {
		if (!Utilitario.elementIsValid(idSessao)) {
			throw new IllegalArgumentException("Sess�o inv�lida");

		} else if (!existeSessao(idSessao)) {
			throw new RuntimeException("Sess�o inexistente");
		}
	}
}