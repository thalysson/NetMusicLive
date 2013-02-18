package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class GerenciaUsuarios {

	private List<Usuario> usuarios;

	public GerenciaUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}

	public void criarUsuario(String login, String senha, String nome,String email) throws Exception {
		if (!Sistema.elementIsValid(login)) {
			throw new Exception("Login inválido");
		}

		else if (!Sistema.elementIsValid(nome)) {
			throw new Exception("Nome inválido");
		}

		else if (!Sistema.elementIsValid(email)) {
			throw new Exception("Email inválido");
		}

		else {
			if (loginJaExiste(login)) {
				throw new Exception("Já existe um usuário com este login");
			}
			if (emailJaExiste(email)) {
				throw new Exception("Já existe um usuário com este email");
			}
			Usuario user = new Usuario(login, senha, nome, email);
			this.usuarios.add(user);
		}
	}

	public boolean emailJaExiste(String email) {
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean loginJaExiste(String login) {
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getLogin().equals(login)) {
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

	
	public String getAtributoUsuario(String login, String atributo){
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getLogin().equals(login)) {					
				if (atributo.equals("nome")) {
					return this.usuarios.get(i).getNome();
					}
				else if (atributo.equals("email")) {
					return this.usuarios.get(i).getEmail();
					}
				else if (atributo.equals("id")) {
						return this.usuarios.get(i).getId();
						}
			}		
		} return "";
	}
	
	public void enviarSolicitacaoAmizade(){}
	
	public Usuario getUser(String login){
		int sizeListUsers = this.usuarios.size();
		for(int i=0;i< sizeListUsers;i++){
			if(usuarios.get(i).getLogin().equals(login)){
				return usuarios.get(i);
			}
		}
		return null;
	}
}