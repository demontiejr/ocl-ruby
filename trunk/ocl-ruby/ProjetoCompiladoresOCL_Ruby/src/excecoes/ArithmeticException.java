package excecoes;

public class ArithmeticException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ArithmeticException(String message) {
		super("Arithmetic Exception: " + message);
	}

}
