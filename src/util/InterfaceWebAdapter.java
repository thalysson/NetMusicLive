package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import mainclasses.NetMusicLive;
import mainclasses.Som;
import mainclasses.Usuario;

public class InterfaceWebAdapter{
	
	private NetMusicLive netMusicLive;
	public static InterfaceWebAdapter interfaceWebAdapter;
	
	private InterfaceWebAdapter(){
		netMusicLive = NetMusicLive.getInstance();
	}

	public static InterfaceWebAdapter getInstance(){
		if(interfaceWebAdapter == null){
			interfaceWebAdapter = new InterfaceWebAdapter();
		}
		return interfaceWebAdapter;
	}
	
	public void criaUsuario(String login,String senha,String nome,String email) {
		this.netMusicLive.criarUsuario(login, senha, nome, email);
	}
	
	public void adicionaUsuarioPerfil(String login) {
		this.netMusicLive.adicionaUsuarioPerfil(login);
	}

	public boolean existeSessao(String login){
		return this.netMusicLive.existeSessao(login);
	}
	
	public void encerrarSessao(String login) {
		this.netMusicLive.encerrarSessao(login);
	}
	
	public String abrirSessao(String login, String password) {
		return this.netMusicLive.abrirSessao(login, password);
	}
	
	public String recuperaSessaoAtual() {
		return netMusicLive.recuperaSessaoAtual();
	}
	
	public void favoritarSom(String login, String idSom) {
		netMusicLive.favoritarSom(login, idSom);
	}

	public List<Som> getMainFeed(String idsessao) {
		try {
			return this.netMusicLive.getMainFeedComSonsDoUser(idsessao);			
		} catch (Exception e) {
			Mensagens.addMsgErro("erro InterfaceWebAdapter");
			return null;
		}
	}
	
	public boolean postarSom(String idsessao,String link){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String dataCriacao = dateFormat.format(date);
		
		try {
			this.netMusicLive.postarSom(idsessao, link, dataCriacao);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<String> search(String textSearch) {
		return this.netMusicLive.search(textSearch);
	}
	
	public boolean verificaLoginESenha(String login,String senha){
		return this.netMusicLive.verificaLoginESenha(login, senha);
	}

	public List<String> getNomesFontesDeSons(String idsessao){
		List<String> nomeUsuarios = new ArrayList<String>();
		try{
			List<Usuario> usuariosQueSegue = this.netMusicLive.getFontesDeSons(idsessao);
			for(int i=0;i<usuariosQueSegue.size();i++){
				nomeUsuarios.add(usuariosQueSegue.get(i).getNome());
			}		
		}catch(Exception e){}
		
		return nomeUsuarios;
	}

	public String getNomeUser(String idUser) {
		return this.netMusicLive.getUsuario(idUser, "id").getNome(); 
	}

	public HashSet<Som> sonsFavoritados(String usuarioAtual) {
		return netMusicLive.sonsFavoritados(usuarioAtual);
	}	
}