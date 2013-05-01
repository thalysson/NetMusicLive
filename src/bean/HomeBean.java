package bean;

import java.util.HashSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Som;
import util.Mensagens;

@ManagedBean
@SessionScoped
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
		setMensagemDePostagem("Postagem de Mensagem...");
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
			Mensagens.addMsgErro("Postagem De Som Invalida");
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
	
	public void favoritarSom(Som som) {
		interfaceWebAdapter.favoritarSom(getUsuarioAtual(), som.getId());
	}
	
	public String sonsFavoritos() {
		
		return "sonsfavoritos?faces-redirect=true";
	}
	
	public HashSet<Som> sonsFavoritados() {
		return interfaceWebAdapter.sonsFavoritados(getUsuarioAtual());
	}

	/**
	 * Adiciona o usuário que será exibido o perfil no Sistema.
	 */
	public String addUsuarioPerfil(String login) {
		this.interfaceWebAdapter.adicionaUsuarioPerfil(login);
		return "usuariopage?faces-redirect=true";
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
	
	private String getUsuarioAtual() {
		return interfaceWebAdapter.recuperaSessaoAtual();
	}
}