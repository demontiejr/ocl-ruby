package excecoes;

public class RelationalErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RelationalErrorException(String message) {
		super("Relational Error Exception: " + message);
	}

}
