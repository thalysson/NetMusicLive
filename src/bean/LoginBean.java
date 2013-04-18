package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.Menssagens;

@ManagedBean
@ViewScoped
public class LoginBean extends DefaultBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public LoginBean(){
		super();
	}
	
	public String logar(){
		try {
				String idsessao;
				if(interfaceWebAdapter.existeSessao(getLogin())){
					idsessao = "sessao"+getLogin();
				}else{
					idsessao = interfaceWebAdapter.abrirSessao(getLogin(),getSenha());
				}
				putInSession("idsessao", idsessao);
				return "homepage?faces-redirect=true";
		} catch (RuntimeException e) {
			Menssagens.addMsgErro(e.getMessage());
		}
		return "";
	}
	
	public String logout() {
		interfaceWebAdapter.encerrarSessao(login);
		return "index?faces-redirect=true";
	}
}