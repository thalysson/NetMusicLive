package mainClasses;

import java.util.List;

public class Usuario {

	private String id,login, senha, nome, email;
	private List<String> fontesDeSom;
	
	public Usuario(String login, String senha, String nome, String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEmail(email);
		setId(login);
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

	public String getId() {
		return id;
	}

	private void setId(String login) {
		this.id = "ID"+login;
	}
}
