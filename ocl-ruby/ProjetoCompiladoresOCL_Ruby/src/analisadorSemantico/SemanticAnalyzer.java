package analisadorSemantico;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> logErros = new ArrayList<String>();
	
	public List<String> getLogErros() {
		return logErros;
	}

	public void error(int line, String message){
		logErros.add("Erro semantico na linha " + (line+1) + ": " + message);
	}
	
	public String getOpID() {
		return opID;
	}

	public void setOpID(String collectionOpID) {
		this.opID = collectionOpID;
	}

	public void checkCollectionOperation(String operation, String parameterType, int line) throws SemanticErrorException {
		if (!checkCollectionOpName(operation))
			error(line, operation + " nao eh uma operacao de collection " +
			"definida pela linguagem");
		if (!checkCollectionOpParams(operation, parameterType))
			error(line, "tipo de parametro errado para a operacao " + operation);
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
		else if (operation.equals("forAll") || operation.equals("exists")){
			return parameterType.equals("Boolean");
			//BOOLEAN
			//Collection -> forAll (v: Type | expressão booleana com v)
		}else if (operation.equals("select")){
			return parameterType.equals("Boolean");
		    //collection -> select (expressão booleana)
		}else if (operation.equals("includes") || operation.equals("excludes")){
		   //TODO	
		   //Collection::includes(object : T) : Boolean 
		   //Collection::excludes(object : T) : Boolean
		}else if (operation.equals("including") || operation.equals("excluding")){
		   //TODO
		   //including(object : T) -> retorna o mesmo tipo da colecao
		   //excluding(object : T) -> retorna o mesmo tipo da colecao
		}
		return false;
	}
	
	public void checkStereotype(String token, int line) throws SemanticErrorException {
		if (stereotype.equals("pre"))
			error(line, token + " nao deve aparecer em pre condicoes.");
	}
	
	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
	
	public String maxType(String type1, String type2, int line){
		if(type1.equalsIgnoreCase(type2)){
			return type1;
		}else{
			if(type1.equalsIgnoreCase("Float") && type2.equalsIgnoreCase("Integer")){
				return type1;
			}else if(type2.equalsIgnoreCase("Float") && type1.equalsIgnoreCase("Integer")){
				return type2;
			}else{
				error(line,"Impossivel realizar operacao entre " + type1 + " e " + type2);
			}
		}
		return null;
	}
}
