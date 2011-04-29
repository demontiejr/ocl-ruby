package analisadorSemantico;

import excecoes.SemanticErrorException;

/**
 * Classe com funcoes uteis para a analise semantica.
 * 
 * @author Demontie Junior
 * @author Izabela Vanessa
 * @author Savyo Igor
 *
 */
public class SemanticAnalyzer {

	private String stereotype;
	private String[] collectionOperations = {"forAll", "exists", "includes", "excludes",
			"including", "size", "excluding", "select", "empty", "first"};
	private String opID;
	
	public String getOpID() {
		return opID;
	}

	public void setOpID(String collectionOpID) {
		this.opID = collectionOpID;
	}

	public void checkCollectionOperation(String operation, String type) throws SemanticErrorException {
		if (!checkCollectionOpName(operation))
			throw new SemanticErrorException(operation + " nao eh uma operacao de collection " +
			"definida pela linguagem");
		checkCollectionOpParams();
	}
	
	private boolean checkCollectionOpName(String operation) {
		for (String op : collectionOperations)
			if (op.equals(operation))
				return true;
		return false;
	}
	
	private void checkCollectionOpParams() {
		// TODO Auto-generated method stub
		
	}
	
	public void checkStereotype(String token) throws SemanticErrorException {
		if (stereotype.equals("pre"))
			throw new SemanticErrorException(token + " nao deve aparecer em pre condicoes.");
	}
	
	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
	
}
