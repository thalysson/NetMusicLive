package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import mainclasses.Som;
import util.Menssagens;

@ManagedBean
@RequestScoped
public class HomeBean extends DefaultBean {

	private String mensagemDePostagem, idsessao, fotoUser;
	private final String caminhoFotoPadrao = "/images/users/default.png";
	private String media = "http://www.youtube.com/v/KZnUr8lcqjo";

	// Search
	private List<String> searchResults;
	private String selectedUser;
	private String textSearch;

	public HomeBean() {
		super();
		setIDSession();
		setMensagemDePostagem("Postagem de Mensagem..");
	}

	public String search() {
		List<String> usersPesquisados = this.interfaceWebAdapter
				.search(getTextSearch());
		setSearchResults(usersPesquisados);
		return "searchpage?faces-redirect=false";
	}

	private void setIDSession() {
		this.idsessao = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("idsessao");
	}

	private String getIDSession() {
		return this.idsessao;
	}

	public void postarSom() {
		boolean result = this.interfaceWebAdapter.postarSom(getIDSession(),
				mensagemDePostagem);
		apagarMensagemDePostagem();
		if (!result) {
			Menssagens.addMsgErro("Postagem De Som Invalida");
		}
	}

	public List<Som> getMainFeed() {
		return this.interfaceWebAdapter.getMainFeed(getIDSession());
	}

	public List<String> getFontesDeSom() {
		return this.interfaceWebAdapter.getNomesFontesDeSons(getIDSession());
	}

	public void apagarMensagemDePostagem() {
		setMensagemDePostagem("");
	}

	// Metodos Set's e Get's dos campos da classe.


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

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public List<String> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<String> searchResults) {
		this.searchResults = searchResults;
	}

	public String getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}

}