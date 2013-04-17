package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mainClasses.Som;
import mainClasses.Usuario;

@ManagedBean
public class HomeBean extends DefaultBean{

	private String mensagemDePostagem,
					idsessao,
					fotoUser;
	private final String caminhoFotoPadrao ="estilo/images/users/default.png";
	private String textSearch;
	private List<Usuario> searchResults;
	
	private String fotoUserSelected,nameUserSelected;
	
	
	public HomeBean() {
		super();
		setIDSession();
		setMensagemDePostagem("Postagem de Mensagem..");
	}

	public void postarSom(){
		boolean result = this.interfaceWebAdapter.postarSom(getIDSession(),mensagemDePostagem);
		apagarMensagemDePostagem();
		if(!result){
			geraMensagemDeErro("postagemDeSomInvalida");
		}
	}

	public void geraMensagemDeErro(String tipo){
		
	}
	
	public List<Som> getMainFeed(){
		return this.interfaceWebAdapter.getMainFeed(getIDSession());
	}
	
	public List<String> getFontesDeSom(){
		return this.interfaceWebAdapter.getNomesFontesDeSons(getIDSession());
	}
	
	public String getFotoUser() {
		setFotoUser(caminhoFotoPadrao);
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getMensagemDePostagem() {
		return mensagemDePostagem;
	}

	public void setMensagemDePostagem(String mensagemDePostagem) {
		this.mensagemDePostagem = mensagemDePostagem;
	}
	
	public void apagarMensagemDePostagem(){
		setMensagemDePostagem("");
	}
	
	public String search(){
		if(!getTextSearch().equals(null)){
			setSearchResults(this.interfaceWebAdapter.search(getTextSearch()));
			return "searchpage?faces-redirect=false";
		}
		//printa mensagem de erro
		return "homepage?faces-redirect=true";
	}

	public String goUsuarioPage(){
		System.out.println("entrou aqui");
		return "usuariopage?faces-redirect=false";
	}
	
	public List<Som> perfilMusicalUserSelected(){
		return this.interfaceWebAdapter.perfilMusicalUserSelected(this.nameUserSelected);
	}
	
	private void setIDSession() {
		this.idsessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idsessao");
	}
	
	private String getIDSession(){
		return this.idsessao;
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public List<Usuario> getSearchResults() {
		return searchResults;
	}
	
	public void setSearchResults(List<Usuario> searchResults) {
		this.searchResults = searchResults;
	}
}