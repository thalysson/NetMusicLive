package tiposordenacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mainclasses.Som;
import mainclasses.Usuario;

/**
 * Representa um tipo de ordenacao.
 * Deverao aparecer primeiro os sons postados pelas suas fontes, os quais
 * estarao ordenados pelo numero de favoritos que recebeu de todos
 * os usuarios do sistema. Quando dois sons tem o mesmo numero de favoritos,
 * a ordem de entrada na pilha eh preservada.
 *
 */
public class SonsMaisFavoritadosSistema implements OrdenaFeedPrincipal{

	@Override
	public List<Som> ordena(List<Usuario> fontesDeSom, List<Som> sonsFavoritos) {
		OrdenaFeedPrincipal ordenacaoAux = new SonsMaisRecentes();
		List<Som> listaAux = ordenacaoAux.ordena(fontesDeSom, sonsFavoritos); 
		Collections.reverse(listaAux);

		List<Som> favoritosDecres = new ArrayList<Som>();
		List<Integer> numeroFavoritos = new ArrayList<Integer>();

		for (Som som :listaAux){
			numeroFavoritos.add(som.getNumeroDeFavoritos());
		}

		int indexMaisFav = 0;
		for (int i = 0;  i < listaAux.size(); i++ ){
			indexMaisFav = numeroFavoritos.indexOf(Collections.max(numeroFavoritos));
			numeroFavoritos.set(indexMaisFav, -1);
			favoritosDecres.add(listaAux.get(indexMaisFav));
		}
		Collections.reverse(favoritosDecres);
		return favoritosDecres;
	}

}
