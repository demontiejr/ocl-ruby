package analisadorSemantico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xmiParser.ManipuladorXMI;
import xmiParser.util.Atributo;
import xmiParser.util.Operacao;
import xmiParser.util.Parametro;
import excecoes.RelationalErrorException;
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

	private String contextClass;
	private String contextMethod;
	private String contextType;

	private String stereotype;
	private String[] collectionOperations = {"forAll", "exists", "includes", "excludes",
			"including", "size", "excluding", "select", "empty", "first"};
	private String opID;
	private Set<String> logErros = new HashSet<String>();
	
	public Set<String> getLogErros() {
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

	public String getContextClass() {
		return contextClass;
	}

	private void setContextClass(String contextClass) {
		this.contextClass = contextClass;
	}

	public String getContextMethod() {
		return contextMethod;
	}

	private void setContextMethod(String contextMethod) {
		this.contextMethod = contextMethod;
	}
	
	public String getContextType() {
		return contextType;
	}

	private void setContextType(String contextType) {
		this.contextType = contextType;
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
	
	public Node checkTypesOpArithmetic(Node rule1, Node rule2, int line ){
		Node node = new Node(); 
        if (rule2 == null)
        	node = (Node)rule1;
        else{
        	Object value;
        	String type = maxType(((Node)rule1).getType(), ((Node)rule2).getType(), line);
        	if (type == null){
        		type = ((Node)rule2).getType();
        	    value = ((Node)rule2).getValue(); //TODO: testar isso
        	}else{
        		value = calcArithmeticValue((Node)rule1, (Node)rule2, rule2.getOperation(), type);
        		System.err.println("value no aux: " + value + "  " + value.getClass());
        	}
 			node.setType(type);
    	 	node.setValue(value);
        }
        return node;
	}
	
	public Node checkTypesOpArithmeticAux(Node rule1, Node rule2, String operator, int line) {
		Node node = new Node();
		String type;
		if (rule2 == null) {
			type = ((Node) rule1).getType();
			if (!(type.equals("Float") || type.equals("Integer"))) {
				error(line, "operador ' " + operator
						+ " ' indefinido para o tipo " + type);

			}
			node.setType(type);
			node.setValue(((Node) rule1).getValue());
		} else {
			Object value;
			type = maxType(((Node) rule1).getType(), ((Node) rule2).getType(),
					line);
			if (type == null) {
				type = ((Node) rule2).getType();
				value = ((Node) rule2).getValue(); // TODO: testar isso
			} else {
				value = calcArithmeticValue((Node) rule1, (Node) rule2,
						rule2.getOperation(), type);
			}
			node.setType(type);
			node.setValue(value);
		}
		node.setOperation(operator);
		System.err.println(node.getValue().getClass());
		return node;
	}
	
	/**
	 * TODO: extender para aceitar double e long
	 */
	private Object calcArithmeticValue(Node rule1, Node rule2, String operator, String type) {
		Float v1 = 0f, v2 = 0f, result = 0f;
		if (rule1.getType().equals("Float"))
			v1 = (Float) rule1.getValue();
		else if (rule1.getType().equals("Integer"))
			v1 = ((Integer) rule1.getValue()).floatValue();

		if (rule2.getType().equals("Float"))
			v2 = (Float) rule2.getValue();
		else if (rule2.getType().equals("Integer"))
			v2 = ((Integer) rule2.getValue()).floatValue();

		//calculando as expressoes
		if (operator.equals("+"))
			result = v1 + v2;
		else if (operator.equals("-"))
			result = v1 - v2;
		else if (operator.equals("/"))
			result = v1/v2;
		else if (operator.equals("*"))
			result = v1 * v2;
		
		if (type.equals("Float"))
			return result;
		else
			return (Integer)result.intValue();
	}
	
	public Boolean calcRelationalValue(Node rule1, Node rule2, String operator, String type) {
		if (type == null)
			return false;
		if (rule1.getValue() == null || rule2.getValue() == null)
			return true; //TODO: olhar se eh a melhor forma
		if (type.equals("Boolean") || type.equals("String")){
			Object v1 = rule1.getValue();
			Object v2 = rule2.getValue();
			if (operator.equals("="))
				return v1.equals(v2);
			else if (operator.equals("<>"))
				return !v1.equals(v2);
			else
				throw new RelationalErrorException("o operador " + operator + " nao pode ser usado para comparar valores do tipo " + type);
		}
		if (type.equals("Float") || type.equals("Integer")) {
			Float v1 = 0f, v2 = 0f;
			if (rule1.getType().equals("Float"))
				v1 = (Float) rule1.getValue();
			else if (rule1.getType().equals("Integer"))
				v1 = ((Integer) rule1.getValue()).floatValue();
			if (rule2.getType().equals("Float"))
				v2 = (Float) rule2.getValue();
			else if (rule2.getType().equals("Integer"))
				v2 = ((Integer) rule2.getValue()).floatValue();
			
			if (operator.equals("="))
				return v1.equals(v2);
			else if (operator.equals(">"))
				return v1 > v2;
			else if (operator.equals("<"))
				return v1 < v2;
			else if (operator.equals(">="))
				return v1 >= v2;
			else if (operator.equals("<="))
				return v1 <= v2;
			else if (operator.equals("<>"))
				return !(v1.equals(v2));
		}
		return false;
	}
	
	public Boolean calcLogicalValue(Boolean value1, Boolean value2, String operator) {
		if (value1 == null || value2 == null)
			return true; //TODO: olhar se eh a melhor forma
		if(operator.equals("and"))
			return value1 && value2;
		else if(operator.equals("or"))
			return value1 || value2;
		else if(operator.equals("implies"))
			return !value1 || value2;
		else // xor
			return value1 ^ value2;
		
	}
	
	public void setContext(String exp) throws SemanticErrorException {
		String[] separate = exp.split("::");
		
		String classe = separate[separate.length-2];

		String metodo = separate[separate.length-1];

		System.out.println("Classe: "+classe);
		System.out.println("Método: "+metodo);
		
		setContextClass(classe);
		
		try {
			Operacao op = ManipuladorXMI.contemFuncao(classe,classe,metodo);
			setContextMethod(metodo);
			String type = op.getReturnType();
			if (type == null)
				type = "Void";
			setContextType(type);
		} catch (Exception e) {
			throw new SemanticErrorException(e.getMessage());
		}
	}
	
	public Node checkFeatureCall(String classe, Node elemento, int role, int line){
		Node node = new Node();
		try {
			String type = null;
			if (role == Node.FUNCTION){
				Operacao op = ManipuladorXMI.contemFuncao(classe,classe,(String)elemento.getValue());
				type = op.getReturnType();
				checkParams(op, elemento.getElements(), line);
			} else if (role == Node.VARIABLE){
				Atributo at = ManipuladorXMI.contemAtributo(classe, classe, (String)elemento.getValue());
				type = at.getIdTipo();
			}
			if (type == null)
				type = "Void";
			node.setType(type);
		} catch (Exception e){
			error(line, e.getMessage());
		}
		return node;
	}

	private void checkParams(Operacao op, List<Node> elements, int line) {
		ArrayList<Parametro> params = op.getParametros();
		if (params.size() != elements.size())
			error(line, "numero errado de parametros na chamada a funcao " + op.getNome() + ".\nDevem ser passados " +
					params.size() + " parametros, mas foram passados " + elements.size());
		for (int i=0; i<params.size(); i++){
			if (!params.get(i).getIdTipo().equals(elements.get(i).getType()))
				error(line, "tipo de parametro errado na chamada a funcao " + op.getNome() + ".\nO " + (i+1) + "º parametro"
					+ " deveria ser um " + params.get(i).getIdTipo() + ", mas foi passado um " + elements.get(i).getType());
		}
		
	}
	
}
