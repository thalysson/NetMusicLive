package mainClasses;

import java.util.List;

public class Sistema {
	
	private Usuario user;
	private String sessao;

	public void zerarSistema() {
		this.user = null;
		this.sessao = null;

	}
	
	public void criarUsuario(String login,String senha,String nome,String email){
		setUser(new Usuario(login,senha,nome,email));
	}
	
	public String abrirSessao(String login, String senha){
		setSessao(login);
		return getSessao();
	}

	public List getPerfilMusical(){
		return this.user.getPerfilMusical();
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