package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

	private Usuario user;
	private String sessao;
	private List<Usuario> usuarios;

	public void zerarSistema() {
		this.user = null;
		this.sessao = null;
		this.usuarios = new ArrayList<Usuario>();
	}

	public void criarUsuario(String login, String senha, String nome,String email) throws Exception {
		if (login == null || login.isEmpty()) {
			throw new Exception("Login inválido");
		}
		
		else if (nome == null || nome.isEmpty()) {
			throw new Exception("Nome inválido");
		}
		
		else if (email == null || email.isEmpty()) {
			throw new Exception("Email inválido");
		}

		else {
			if(loginJaExiste(login)){
				throw new Exception("Já existe um usuário com este login");
			}
			if(emailJaExiste(email)){
				throw new Exception("Já existe um usuário com este email");
			}
			setUser(new Usuario(login, senha, nome, email));
			this.usuarios.add(getUser());
		}
	}
	
	public boolean loginJaExiste(String login){
		for (int i = 0; i < this.usuarios.size(); i++) {
			if(this.usuarios.get(i).getLogin().equals(login)){
				return true;
			}
		}
		return false;
	}
	
	public boolean emailJaExiste(String email){
		for (int i = 0; i < this.usuarios.size(); i++) {
			if(this.usuarios.get(i).getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaSenha(String login, String senha){
		for (int i = 0; i < this.usuarios.size(); i++) {
			if(this.usuarios.get(i).getLogin().equals(login)){
				if(this.usuarios.get(i).getSenha().equals(senha)){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	public String abrirSessao(String login, String senha) throws Exception {
		if(login == null || login.isEmpty()){
			throw new Exception("Login inválido");
		}else if(loginJaExiste(login)){
			if(!verificaSenha(login, senha)){
				throw new Exception("Login inválido");
			}
			setSessao(login);
			return getSessao();
		}else{
			throw new Exception("Usuário inexistente");
		}
	}
	
	public boolean elementIsValid(String element){
		if(element == null || element.isEmpty()){ 
			return false;
		}
		return true;
	}
	

	public List<String> getPerfilMusical(String idSessao) {
		System.out.println(this.user.getPerfilMusical());
		return this.user.getPerfilMusical();
	}

	public String getAtributoUsuario(String login, String atributo) throws Exception{
		if (!elementIsValid(atributo)) {
			throw new Exception("Atributo inválido");
		}
		else if(!elementIsValid(login)){
			throw new Exception("Login inválido");
		}
		else if (!loginJaExiste(login)) {
			throw new Exception("Usuário inexistente");
		}
		else {	
			for (int i = 0; i < this.usuarios.size(); i++) {
				if (this.usuarios.get(i).getLogin().equals(login)) {					
					if (atributo.equals("nome")) {
						return this.usuarios.get(i).getNome();
						}
					else if (atributo.equals("email")) {
						return this.usuarios.get(i).getEmail();
						}
				}		
			}
		}
		throw new Exception("Atributo inexistente");
	}

	public String getAtributoSom(String idsom, String atributo) {
		return "";
	}
	
	public void encerrarSessao(String login){
		
	}
	
	public void encerrarSistema(){
		
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String login) {
		this.sessao = "sessao".concat(login);
	}
}