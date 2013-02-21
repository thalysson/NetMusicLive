package mainClasses;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String id,login, senha, nome, email;
	private List<String> fontesDeSom;
	private List<String> visaoDosSons;
	
	public Usuario(String login, String senha, String nome, String email) {
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEmail(email);
		setId(login);
		this.fontesDeSom = new ArrayList<String>();
		this.visaoDosSons = new ArrayList<String>();
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

	public List<String> getListFontesDeSom() {
		return fontesDeSom;
	}

	public String getFontesDeSom(){
		String retorno = "{";
		try {
			int sizeList = fontesDeSom.size();
			for(int i=0;i<sizeList;i++){
				retorno = retorno + fontesDeSom.get(i);
				if(i < (sizeList-1)){
					retorno = retorno + ",";
				}
			}
		} catch (Exception e) {}
		retorno = retorno +"}";
		return retorno;

	}
	public void addFontesDeSom(String idFontesDeSom) {
		this.fontesDeSom.add(idFontesDeSom);
	}

	public List<String> getVisaoDosSons() {
		return this.visaoDosSons;	
	}
	
	public void addEmVisaoDosSons(String idSom){
		this.visaoDosSons.add(idSom);
	}
}
