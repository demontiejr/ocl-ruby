package excecoes;

public class SemanticErrorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SemanticErrorException(String message){
		super("Erro semantico: " + message);
	}

}
