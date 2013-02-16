package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String login, senha, nome, email;
	private List<String> perfilMusical;
	
	public Usuario(String login, String senha, String nome, String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEmail(email);
		this.perfilMusical = new ArrayList<String>();
	}

	public List<String> getPerfilMusical() {
		return this.perfilMusical;
	}

	
	public String getLogin() {
		return login;
	}

	private void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}
}
