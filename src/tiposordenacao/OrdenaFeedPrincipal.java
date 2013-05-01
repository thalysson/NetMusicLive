package tiposordenacao;

import java.util.List;

import model.Som;
import model.Usuario;

/**
 * Interface dos tipos de ordenacao do feed principal do {@link Usuario}.
 */
public interface OrdenaFeedPrincipal {


	public List<Som> ordena(List<Usuario> fontesDeSom, List<Som> sonsFavoritos);
}
