package exception;
/**
 * Chamada quando o login ja existe. 
 *
 */
public class LoginDuplicadoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LoginDuplicadoException(){
		super("J� existe um usu�rio com este login");
	}

}
