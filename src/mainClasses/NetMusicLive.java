package mainclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tiposordenacao.OrdenaFeedPrincipal;
import tiposordenacao.SonsMaisFavoritadosSistema;
import tiposordenacao.SonsMaisFavoritadosUsuario;
import tiposordenacao.SonsMaisRecentes;
import tiposordenacao.TipoFeedPrincipal;
import util.Utilitario;

/**
 * Classe que representa as principais acoes do Sistema.
 * 
 */
public class NetMusicLive implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSessao gerenciaSessao;
	private Gerenciador gerenciador;
	private OrdenaFeedPrincipal feedPrincipal;
	private static NetMusicLive netMusicLive;

	private NetMusicLive() {
		zerarSistema();
	}

	/**
	 * Recupera a instancia de {@link NetMusicLive}. Caso seja null, uma
	 * intancia e criada.
	 * 
	 * @return Instancia de Sistema.
	 */
	public static NetMusicLive getInstance() {
		if (netMusicLive == null) {
			netMusicLive = new NetMusicLive();
		}
		return netMusicLive;
	}

	/**
	 * Todos os dados do sistema � zerado. Utilizado tamb�m para instaciar as
	 * principais classes de gerencia.
	 */
	public void zerarSistema() {
		gerenciaSessao = new GerenciaSessao();
		gerenciador = new Gerenciador();
		feedPrincipal = new SonsMaisRecentes();
	}

	/**
	 * Cria um novo usuario.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param senha
	 *            Senha do usuario.
	 * @param nome
	 *            Nome do usuario.
	 * @param email
	 *            Email do usuario.
	 * @throws Exception
	 *             Login, nome ou email inv�lidos (ou seja null ou ""). Login ou
	 *             email ja existente.
	 */
	public void criarUsuario(String login, String senha, String nome,
			String email) {
		if (!Utilitario.elementIsValid(login)) {
			throw new IllegalArgumentException("Login inv�lido");

		} else if (!Utilitario.elementIsValid(nome)) {
			throw new IllegalArgumentException("Nome inv�lido");

		} else if (!Utilitario.elementIsValid(email)) {
			throw new IllegalArgumentException("Email inv�lido");

		} else {
			if (gerenciador.existeLoginUsuario(login)) {
				throw new IllegalArgumentException("J� existe um usu�rio com este login");
			}
			if (gerenciador.existeEmailUsuario(email)) {
				throw new IllegalArgumentException("J� existe um usu�rio com este email");
			}
			gerenciador.criarUsuario(login, senha, nome, email);
		}
	}

	/**
	 * Verifica se o login e senha correspondente existem no sistema.
	 * 
	 * @param login
	 *            Login a ser pesquisado.
	 * @param senha
	 *            Senha a ser pesquisada.
	 * @return true, caso o existam.
	 */
	public boolean verificaLoginESenha(String login, String senha) {
		return gerenciador.verificaLoginESenha(login, senha);
	}
	
	public List<Usuario> search(String textSearch) {
		return this.gerenciador.search(textSearch);		
	}

	/**
	 * Uma nova sessao e criada.
	 * 
	 * @param login
	 *            Login do usuario dono da sessao.
	 * @param senha
	 *            Senha do usuario dono da sessao.
	 * @return O id da sessao.
	 * @throws Exception
	 *             Login invalido (ou seja, null ou ""). Login e senha
	 *             incompativeis. Login de usuario inexistente.
	 */
	public String abrirSessao(String login, String senha) {
		if (!Utilitario.elementIsValid(login)) {
			throw new IllegalArgumentException("Login inv�lido");

		} else if (gerenciador.existeLoginUsuario(login)) {

			if (!verificaLoginESenha(login, senha)) {
				throw new IllegalArgumentException("Login inv�lido");
			}
			return gerenciaSessao.abrirSessao(login, senha);

		} else {
			throw new RuntimeException("Usu�rio inexistente");
		}
	}

	/**
	 * Verifica se a sessao em questao existe.
	 * 
	 * @param login
	 *            Login do usuario dono da sessao.
	 * @return true, no caso de exitir.
	 */
	public boolean existeSessao(String login) {
		String idsessao = "sessao" + login;
		return gerenciaSessao.existeSessao(idsessao);
	}

	/**
	 * Retorna o atributo desejado de um usuario.
	 * 
	 * @param login
	 *            Login do usuario em que se deseja saber um atributo.
	 * @param atributo
	 *            Atributo desejado, podendo ser: login, nome, email ou id.
	 * @return O valor do atributo.
	 * @throws Exception
	 *             Atributo ou login invalidos (ou seja, null ou ""). Login ou
	 *             atributo inexistentes.
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(atributo)) {
			throw new IllegalArgumentException("Atributo inv�lido");

		} else if (!Utilitario.elementIsValid(login)) {
			throw new IllegalArgumentException("Login inv�lido");

		} else if (!this.gerenciador.existeLoginUsuario(login)) {
			throw new IllegalArgumentException("Usu�rio inexistente");

		} else {
			String result = this.gerenciador
					.getAtributoUsuario(login, atributo);
			if (!result.isEmpty()) {
				return result;
			}
		}
		throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Retorna o perfil musical do usuario correspondente ao idsessao.
	 * 
	 * @param idsessao
	 *            ID da sessao.
	 * @return Lista dos sons postados pelo usuario.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 * 
	 */
	public List<Som> getPerfilMusical(String idsessao) throws Exception {
		return gerenciador.getPerfilMusical(gerenciaSessao.getLogin(idsessao));
	}

	/**
	 * Acao do usuario de postar um novo som.
	 * 
	 * @param idSessao
	 *            ID da sessao do usuario.
	 * @param link
	 *            Link do som.
	 * @param dataCriacao
	 *            Data de criacao do som.
	 * @return ID do som postado.
	 * @throws Exception
	 *             Link invalido (ou seja, null ou ""). Data invalida.
	 */
	public String postarSom(String idSessao, String link, String dataCriacao)
			throws Exception {
		if (!Utilitario.elementIsValid(link)) {
			throw new IllegalArgumentException("Som inv�lido");

		} else if (!Utilitario.dataIsValida(dataCriacao)) {
			throw new IllegalArgumentException("Data de Cria��o inv�lida");
		}

		return gerenciador.postarSom(gerenciaSessao.getLogin(idSessao), link,
				dataCriacao);
	}

	/**
	 * Retorna o atributo desejado de um som.
	 * 
	 * @param idSom
	 *            ID do som em questao.
	 * @param atributo
	 *            Atributo desejado.
	 * @return O valor do atributo.
	 * @throws Exception
	 *             ID do som ou atributo invalidos (ou seja, null ou "").
	 *             Atributo inexistente.
	 */
	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new IllegalArgumentException("Som inv�lido");
		} else if (!Utilitario.elementIsValid(atributo)) {
			throw new IllegalArgumentException("Atributo inv�lido");
		}

		String valorAtributo = gerenciador.getAtributoSom(idSom, atributo);
		if (!valorAtributo.isEmpty()) {
			return valorAtributo;
		}
		throw new IllegalArgumentException("Atributo inexistente");
	}

	/**
	 * Acao de um usuario seguir outro.
	 * 
	 * @param idSessaoSeguidor
	 *            ID do usuario seguidor.
	 * @param loginSeguido
	 *            Login do usuario a ser seguido.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Login do seguido invalido ou
	 *             inexistente. Seguidor e seguido serem o mesmo usuario.
	 */
	public void seguirUsuario(String idSessaoSeguidor, String loginSeguido)
			throws Exception {
		String loginSeguidor = gerenciaSessao.getLogin(idSessaoSeguidor);

		if (!Utilitario.elementIsValid(loginSeguido)) {
			throw new IllegalArgumentException("Login inv�lido");

		} else if (loginSeguidor.equals(loginSeguido)) {
			throw new IllegalArgumentException("Login inv�lido");
		}

		if (gerenciador.existeLoginUsuario(loginSeguido)) {
			gerenciador.seguirUsuario(loginSeguidor, loginSeguido);

		} else {
			throw new RuntimeException("Login inexistente");
		}
	}

	/**
	 * Retorna a lista de seguidores de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista de usuarios seguidores.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Usuario> getListaDeSeguidores(String idSessao) throws Exception {
		return gerenciador.getListaSeguidoresUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o numero de seguidores de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return O numero de seguidores.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public int getNumeroDeSeguidores(String idSessao) throws Exception {
		return getListaDeSeguidores(idSessao).size();
	}

	/**
	 * Retorna as fontes de um usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das fontes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 * 
	 */
	public List<Usuario> getFontesDeSons(String idSessao) throws Exception {
		return gerenciador.getFontesSomUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o ID do usuario.
	 * 
	 * @param idsessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return ID do usuario.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public String getIDUsuario(String idsessao) throws Exception {
		return gerenciador.getAtributoUsuario(
				gerenciaSessao.getLogin(idsessao), "id");
	}

	/**
	 * Retorna a visao dos sons do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das visoes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getVisaoDosSons(String idSessao) throws Exception {
		return gerenciador.getVisaoSonsUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna os sons favoritos do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista com sons favoritos.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getSonsFavoritos(String idSessao) throws Exception {
		return gerenciador.getSonsFavoritosUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o feed extra do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Feed extra.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getFeedExtra(String idSessao) throws Exception {
		return gerenciador.getFeedExtraUsuario(gerenciaSessao
				.getLogin(idSessao));
	}

	/**
	 * Retorna o feed principal do usuario.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @return Lista das visoes.
	 * @throws Exception
	 *             Sessao invalida ou inexistente.
	 */
	public List<Som> getMainFeed(String idSessao) throws Exception {
		return feedPrincipal.ordena(getFontesDeSons(idSessao),
				getSonsFavoritos(idSessao));
	}
	
	public List<Som> getMainFeedComSonsDoUser(String idSessao) throws Exception { 
		List<Usuario> listAuxiliar = new ArrayList<Usuario>();
		Usuario user = gerenciador.getUsuario(gerenciaSessao.getLogin(idSessao), "login");
		listAuxiliar.add(user);
		
		List<Som> sons = feedPrincipal.ordena(listAuxiliar,getMainFeed(idSessao));
		return sons;
	}

	/**
	 * Acao do usuario de favoritar um som.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param idSom
	 *            ID do som a ser favoritado.
	 * @throws Exception
	 *             ID do som invalido ou inexistente. Sessao inexitente ou
	 *             invalida.
	 */
	public void favoritarSom(String idSessao, String idSom) throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new IllegalArgumentException("Som inv�lido");
		}

		if (!gerenciador.favoritarSom(gerenciaSessao.getLogin(idSessao), idSom)) {
			throw new RuntimeException("Som inexistente");
		}
	}

	/**
	 * Altera o tipo da ordenacao do feed principal do usuairo.
	 * 
	 * @param idsessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param rule
	 *            Tipo da ordenacao.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Regra invalida ou
	 *             inexistente.
	 */
	public void setMainFeedRule(String idsessao, String rule) throws Exception {
		gerenciaSessao.verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(rule)) {
			throw new IllegalArgumentException("Regra de composi��o inv�lida");
		}

		if (rule.equals(TipoFeedPrincipal.SONS_RECENTES.toString())) {
			feedPrincipal = new SonsMaisRecentes();

		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_SISTEMA
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosSistema();

		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_USUARIO
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosUsuario();

		} else {
			throw new RuntimeException("Regra de composi��o inexistente");
		}
	}

	/**
	 * Encerra uma sessao.
	 * 
	 * @param login
	 *            Login do usuario que ter� a sessao encerrada.
	 */
	public void encerrarSessao(String login) {
		gerenciaSessao.encerrarSessao("sessao" + login);
	}

	/**
	 * Encerra o sistema.
	 */
	public void encerrarSistema() {

	}

	/**
	 * Acao do usuario criar uma lista personalizada de usuarios.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario em questao.
	 * @param nomeLista
	 *            Nome da lista a ser criada.
	 * @return ID da lista criada.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Nome invalido ou j�
	 *             existente.
	 */
	public String criarLista(String idSessao, String nomeLista)
			throws Exception {
		String login = gerenciaSessao.getLogin(idSessao);
		if (!Utilitario.elementIsValid(nomeLista)) {
			throw new Exception("Nome inv�lido");
		}
		String idLista = gerenciador.criarLista(login, nomeLista);

		if (!Utilitario.elementIsValid(idLista)) {
			throw new Exception("Nome j� escolhido");
		}
		return idLista;
	}

	/**
	 * Adiciona um usuario a uma lista personalizada.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario dono da lista
	 *            personalizada.
	 * @param idLista
	 *            ID da lista.
	 * @param idUsuario
	 *            ID do usuario que ser� adicionado.
	 * @throws Exception
	 *             Sessao inexistente ou invalida. Usuario invalido ou ja
	 *             existente. Lista invalida. Usuario n�o pode adicionar-se a
	 *             propria lista.
	 */
	public void adicionarUsuario(String idSessao, String idLista,
			String idUsuario) throws Exception {
		gerenciador.adicionaUsuario(gerenciaSessao.getLogin(idSessao), idLista,
				idUsuario);
	}

	/**
	 * Retorna os sons de uma lista personalizada.
	 * 
	 * @param idSessao
	 *            ID da sessao correspondente ao usuario dono da lista
	 * @param idLista
	 *            ID da lista.
	 * @return Lista contendo os sons.
	 * @throws Exception
	 *             Sessao invalida ou inexistente. Lista invalida.
	 */
	public List<Som> getSonsEmLista(String idSessao, String idLista)
			throws Exception {
		List<Som> sons = gerenciador.getSonsEmLista(
				gerenciaSessao.getLogin(idSessao), idLista);
		if (sons == null) {
			throw new Exception("Lista inv�lida");
		}
		return sons;
	}
	
	public int getNumFontesEmComum(String idSessao, String idUsuario) throws Exception {
		return gerenciador.getNumFontesEmComum(gerenciaSessao.getLogin(idSessao),idUsuario);
		
	}
	
	public int getNumFavoritosEmComum(String idSessao, String idUsuario) throws Exception {
		return gerenciador.getNumFavoritosEmComum(gerenciaSessao.getLogin(idSessao), idUsuario);
	}
	
	public List<Usuario> getFontesDeSonsRecomendadas(String idSessao) throws Exception {
		return gerenciador.getFontesDeSonsRecomendadas(gerenciaSessao.getLogin(idSessao));
	}
}