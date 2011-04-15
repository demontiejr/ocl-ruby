package excecoes;

public class FatalErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public FatalErrorException(String message){
		super("Erro fatal " + message);
	}
	
}
