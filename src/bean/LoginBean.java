package bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean
@SessionScoped
public class LoginBean extends DefaultBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public LoginBean(){
		super();
	}
	
	public String logar(){
		try {
			if(interfaceWebAdapter.verificaLoginESenha(getLogin(),getSenha())){
				String idsessao;
				if(interfaceWebAdapter.existeSessao(getLogin())){
					idsessao = "sessao"+getLogin();
				}else{
					idsessao = interfaceWebAdapter.abrirSessao(getLogin(),getSenha());
				}
				putInSession("idsessao", idsessao);
				return "homepage?faces-redirect=true";
			}				
		} catch (Exception e) {
			System.out.println("erro ao abrir sessao (logar)");
			// gera erro na tela;
		}
		return "index?faces-redirect=true";
	}
	
	public String logout() {
		//interfaceWebAdapter.encerrarSessao(login);
		return "index?faces-redirect=true";
	}
	
}