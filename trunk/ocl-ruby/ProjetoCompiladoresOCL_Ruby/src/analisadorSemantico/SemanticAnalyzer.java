package analisadorSemantico;

import java.util.ArrayList;

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
	private ArrayList<String> logErros = new ArrayList<String>();
	
	public void error(int line, String message){
		logErros.add("Erro semantico na linha " + (line+1) + ": " + message);
	}
	
	public String getOpID() {
		return opID;
	}

	public void setOpID(String collectionOpID) {
		this.opID = collectionOpID;
	}

	public void checkCollectionOperation(String operation, String parameterType) throws SemanticErrorException {
		if (!checkCollectionOpName(operation))
			throw new SemanticErrorException(operation + " nao eh uma operacao de collection " +
			"definida pela linguagem");
		if (!checkCollectionOpParams(operation, parameterType))
			throw new SemanticErrorException("tipo de parametro errado para a operacao " + operation);
	}
	
	private boolean checkCollectionOpName(String operation) {
		for (String op : collectionOperations)
			if (op.equals(operation))
				return true;
		return false;
	}
	
	private boolean checkCollectionOpParams(String operation, String parameterType) {
		if (operation.equals("size") || operation.equals("empty") || operation.equals("first")){
			return parameterType.equals("void");
		}
		return false;
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
