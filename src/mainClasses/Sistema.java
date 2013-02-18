package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

	private GerenciaSons gerenciaSons;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarios gerenciaUsuarios;
	private GerenciaAmizades gerenciaAmizades;

	public void zerarSistema() {
		this.gerenciaSons = new GerenciaSons();
		this.gerenciaSessao = new GerenciaSessao();
		this.gerenciaUsuarios = new GerenciaUsuarios();
		this.gerenciaAmizades = new GerenciaAmizades();
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
	
	public static boolean elementIsValid(String element){
		if(element == null || element.isEmpty()){ 
			return false;
		}
		return true;
	}
	
	

	public String getPerfilMusical(String sessao) {
		String loginDaSessao = this.gerenciaSessao.getLogin(sessao);
		return this.gerenciaSons.getPerfilMusical(loginDaSessao);
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
		if(!elementIsValid(link)){
			throw new Exception("Som inválido");
		}else if(!dataIsValida(dataCriacao)){
			throw new Exception("Data de Criação inválida");
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
	
	public String enviarSolicitacaoAmizade(String sessao,String login) throws Exception{
		if(!elementIsValid(sessao)){
			throw new Exception("Sessão inválida");
		}else if(!elementIsValid(login)){
			throw new Exception("Login inválido");
		}
		String solicitante = this.gerenciaSessao.getLogin(sessao);
		return this.gerenciaAmizades.enviarSolicitacaoAmizade(solicitante, login);
	}
	
	public void aceitarSolicitacaoAmizade(String sessao, String idsolicitacao) throws Exception{
		if(!elementIsValid(sessao)){
			throw new Exception("Sessão inválida");
		}else if(!elementIsValid(idsolicitacao)){
			throw new Exception("Solicitação inválida");
		}
	}
	
	public String getFontesDeSons(String idsessao){
		return "";
	}
	
	public String getIDUsuario(String sessao){
		return this.gerenciaUsuarios.getAtributoUsuario(this.gerenciaSessao.getLogin(sessao),"id");
	}
	
	
	public void encerrarSessao(String login){
		this.gerenciaSessao.encerrarSessao(login);
	}

	public void encerrarSistema(){
		
	}
}