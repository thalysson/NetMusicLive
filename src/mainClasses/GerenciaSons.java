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

	public String getPerfilMusical(String loginUser) {
		String retorno = "{";
		try {
			List<String> listaIDsSons = mapaLoginIDSons.get(loginUser);
			int sizeList = listaIDsSons.size();
			for(int i=0;i<sizeList;i++){
				retorno = retorno + listaIDsSons.get(i);
				if(i < (sizeList-1)){
					retorno = retorno + ",";
				}
			}
		} catch (Exception e) {}
		retorno = retorno +"}";
		return retorno;
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
}