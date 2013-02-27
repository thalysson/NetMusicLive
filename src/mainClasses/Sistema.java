package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

	private GerenciaSons gerenciaSons;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarios gerenciaUsuarios;

	public void zerarSistema() {
		this.gerenciaSons = new GerenciaSons();
		this.gerenciaSessao = new GerenciaSessao();
		this.gerenciaUsuarios = new GerenciaUsuarios();
	}

	public void criarUsuario(String login, String senha, String nome,String email) throws Exception {
		this.gerenciaUsuarios.criarUsuario(login, senha, nome, email);
	}


	public String abrirSessao(String login, String senha) throws Exception {
		if(!elementIsValid(login)){
			throw new Exception("Login inválido");
		}else if(this.gerenciaUsuarios.loginJaExiste(login)){
			if(!this.gerenciaUsuarios.verificaSenha(login, senha)){
				throw new Exception("Login inválido");
			}
			return this.gerenciaSessao.abrirSessao(login,senha);
		}else{
			throw new Exception("Usuário inexistente");
		}
	}
	
	public String getPerfilMusical(String sessao) {
		String loginDaSessao = this.gerenciaSessao.getLogin(sessao);
		return retornaListComChaves(this.gerenciaSons.getPerfilMusical(loginDaSessao));
	}
	
	public boolean dataIsValida(String dataParam){
		try {
			String[] datas = dataParam.split("/");
			Integer dia = Integer.parseInt(datas[0]);
			Integer mes = Integer.parseInt(datas[1]);
			Integer ano = Integer.parseInt(datas[2]);
			if(dia < 1 || dia > 31){return false;}
			else if(ano<2013){return false;}
			else if(mes < 1 || mes > 12){return false;}
			else{
				if(mes == 2){
					if(dia > 29){return false;}
					// se o ano for bisexto e dia for igual a 29, entao false.
					else if(!((ano % 4 == 0) && ( (ano % 100 != 0) || (ano % 400 == 0))) && dia==29){
						return false;
					}
				}else{
				List<Integer> meses30dias = new ArrayList<Integer>();
				meses30dias.add(4);meses30dias.add(6);meses30dias.add(9);meses30dias.add(11);
				if(meses30dias.contains(mes) && dia >=31){return false;}
				}
			}
		} catch (Exception e) {
			return false;
		}return true;
	}
	
	public String postarSom(String sessao,String link,String dataCriacao) throws Exception{
		if(!elementIsValid(link) || !dataIsValida(dataCriacao)){
			throw new Exception("Som inválido");
		}
		String login = this.gerenciaSessao.getLogin(sessao);
		 return this.gerenciaSons.postarSom(login,link,dataCriacao);
	}
	
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		if (!elementIsValid(atributo)) {
			throw new Exception("Atributo inválido");
		}
		else if(!elementIsValid(login)){
			throw new Exception("Login inválido");
		}
		else if (!this.gerenciaUsuarios.loginJaExiste(login)) {
			throw new Exception("Usuário inexistente");
		}
		else {	
			String result = this.gerenciaUsuarios.getAtributoUsuario(login,atributo);
			if(!result.isEmpty()){ return result;}
		}
		throw new Exception("Atributo inexistente");
	}

	public String getAtributoSom(String idSom, String atributo) throws Exception{
		if(idSom == null){
			throw new Exception("Som inexistente");
		} else if(idSom.isEmpty()){
			throw new Exception("Som inválido");
		} else if(!elementIsValid(atributo)){
			throw new Exception("Atributo inválido");
		}
		String result = this.gerenciaSons.getAtributoSom(idSom,atributo);
		if(!result.isEmpty()){
			return result;
		}else{
			throw new Exception("Atributo inexistente");
		}
	}
	
	public void seguirUsuario (String idsessao,String login) throws Exception{
		if(!elementIsValid(idsessao)){
			throw new Exception("Sessão inválida");
		} else if(!elementIsValid(login) || this.gerenciaSessao.getLogin(idsessao).equals(login)){
			throw new Exception("Login inválido");
		}
		// Identificando Usuario que sera seguido:
		if(this.gerenciaUsuarios.loginJaExiste(login)){
			Usuario userSeguido = this.gerenciaUsuarios.getUser(login);
			// Identificando Usuario que decidiu ser seguidor:
			String loginSeguidor = this.gerenciaSessao.getLogin(idsessao);
			Usuario userSeguidor = this.gerenciaUsuarios.getUser(loginSeguidor);
			// Passando para a classe que gerencia o sons a responsabilidade de adcionar nas fontes de som, e na lista de seguidores.
			this.gerenciaSons.seguirUsuario(userSeguidor, userSeguido);
		}else{
			throw new Exception("Login inexistente");
		}
	}
	
	public String getListaDeSeguidores(String idsessao) throws Exception{
		verificaSessao(idsessao);
		// Identificando Usuario:
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		Usuario user = this.gerenciaUsuarios.getUser(loginUser);
		return retornaListComChaves(user.getListaDeSeguidores());
	}
	
	public int getNumeroDeSeguidores (String idsessao) throws Exception{
		verificaSessao(idsessao);
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		Usuario user = this.gerenciaUsuarios.getUser(loginUser);
		return user.getNumeroDeSeguidores();
	}
	
	public String getFontesDeSons(String idsessao) throws Exception{
		verificaSessao(idsessao);
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		Usuario user = this.gerenciaUsuarios.getUser(loginUser);
		return retornaListComChaves(user.getFontesDeSom());
	}
	
	
	/** Metodo que retorna o id de uma determinado usuario a partir do identificador de uma sessao.
	 * 
	 * @param sessao
	 * @return
	 */
	public String getIDUsuario(String sessao){
		return this.gerenciaUsuarios.getAtributoUsuario(this.gerenciaSessao.getLogin(sessao),"id");
	}
	
	public String getVisaoDosSons(String idsessao){
		String loginUser = this.gerenciaSessao.getLogin(idsessao);
		Usuario user = this.gerenciaUsuarios.getUser(loginUser);
		return retornaListComChaves(this.gerenciaSons.getVisaoDosSons(user));
	}
	
	public void encerrarSessao(String login){
		this.gerenciaSessao.encerrarSessao(login);
	}

	/**
	 * Metodo que ao ser chamado finaliza o sistema.
	 */
	public void encerrarSistema(){
		
	}
	
	public String retornaListComChaves(List<String> list){
		String retorno ="{";
		try {
			int sizeList = list.size();
			for(int i=0;i<sizeList;i++){
				retorno = retorno + list.get(i);
				if(i < (sizeList-1)){
					retorno = retorno + ",";
				}
			}
		} catch (Exception e) {}
		retorno = retorno +"}";
		return retorno;
	}
	
	public void verificaSessao(String idsessao) throws Exception{
		if(!elementIsValid(idsessao)){
			throw new Exception("Sessão inválida");
		} else if(!elementIsValid(this.gerenciaSessao.getLogin(idsessao))){
			throw new Exception("Sessão inexistente");
		}
	}

	/** Metodo que auxilia na verificação de variaveis na forma de String.
	 * 
	 * @param  String element
	 * @return true se elemento nao for null ou vazio, false caso contrario.
	 */
	public static boolean elementIsValid(String element){
		if(element == null || element.isEmpty()){ 
			return false;
		}return true;
	}
}