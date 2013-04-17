package exception;

/**
 * Chamada quando atributo e igual a "" ou null. 
 *
 */
public class AtributoInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AtributoInvalidoException(){
		super("Atributo inv�lido");
	}

}
