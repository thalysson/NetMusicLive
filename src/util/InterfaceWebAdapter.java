package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mainClasses.NetMusicLive;
import mainClasses.Som;
import mainClasses.Usuario;

public class InterfaceWebAdapter{
	
	private NetMusicLive netMusicLive;
	public static InterfaceWebAdapter interfaceWebAdapter;
	
	/**
	 * Construtor da Classe.
	 */
	private InterfaceWebAdapter(){
		netMusicLive = NetMusicLive.getInstance();
	}

	public static InterfaceWebAdapter getInstance(){
		if(interfaceWebAdapter == null){
			interfaceWebAdapter = new InterfaceWebAdapter();
		}
		return interfaceWebAdapter;
	}
	
	public void criaUsuario(String login,String senha,String nome,String email) throws Exception{
		this.netMusicLive.criarUsuario(login, senha, nome, email);
	}

	public boolean existeSessao(String login){
		return this.netMusicLive.existeSessao(login);
	}
	
	public String abrirSessao(String login, String password) throws Exception {
		return this.netMusicLive.abrirSessao(login, password);
	}

	public List<Som> getMainFeed(String idsessao) {
		try {
			return this.netMusicLive.getMainFeedComSonsDoUser(idsessao);			
		} catch (Exception e) {
			System.out.println("erro interfaceWebAdapter");
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

	public List<Usuario> search(String textSearch) {
		return this.netMusicLive.search(textSearch);
	}

	public List<Som> perfilMusicalUserSelected(String nameUserSelected) {
		return this.netMusicLive.perfilMusicalUserSelected(nameUserSelected);
		
	}	
}