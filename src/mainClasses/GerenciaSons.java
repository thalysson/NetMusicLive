package mainClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciaSons {

	private Map<String, List<String>> mapaLoginIDSons;
	private List<Som> listaDeSons;
	
	
	public GerenciaSons() {
		this.mapaLoginIDSons = new HashMap<String, List<String>>();
		this.listaDeSons = new ArrayList<Som>();
	}


	public String postarSom(String login, String link, String dataCriacao) {
		String id = "som" + (this.listaDeSons.size() + 1) + "ID";
		Som som = new Som(id, link, dataCriacao);
		this.listaDeSons.add(som);
		if(mapaLoginIDSons.containsKey(login)){
			mapaLoginIDSons.get(login).add(som.getId());
		}else{
			List<String> novaLista = new ArrayList<String>();
			novaLista.add(som.getId());
			mapaLoginIDSons.put(login, novaLista);
		}
		return id;
	}

	public List<String> getPerfilMusical(String loginUser) {
			return mapaLoginIDSons.get(loginUser);
	}

	public String getAtributoSom(String idSom, String atributo) {
		int sizeList = this.listaDeSons.size();
		for(int i=0;i<sizeList;i++){
			if(this.listaDeSons.get(i).getId().equals(idSom)){
				if(atributo.equals("dataCriacao")){
					return listaDeSons.get(i).getData();
				}
			}
		}
		return "";
	}
	
	public void adcionaFonteDeSons(Usuario user01, Usuario user02){
		user01.addFontesDeSom(user02.getId());
		user02.addFontesDeSom(user01.getId());
		addVisaoDosSons(user01,user02);
	}

	private void addVisaoDosSons(Usuario user01, Usuario user02){
		List<String> perfilMusicalUser01 = getPerfilMusical(user01.getLogin());
		List<String> perfilMusicalUser02 = getPerfilMusical(user02.getLogin());

		if(perfilMusicalUser01 != null){
			auxiliaAddSonsVisao(user02,perfilMusicalUser01);
		}
		if(perfilMusicalUser02 != null){
			auxiliaAddSonsVisao(user01,perfilMusicalUser02);
		}
		
	}
	
	public void auxiliaAddSonsVisao(Usuario user, List<String> perfilMusical){
		int sizeList = perfilMusical.size();
		for(int i=0; i<sizeList; i++){
			user.addEmVisaoDosSons(perfilMusical.get(i));
		}
	}
	
	public List<String> getVisaoDosSons(Usuario user) {
		return user.getVisaoDosSons();
	}
	
}