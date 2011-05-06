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
	
	private boolean declarator = false;
	private List<Node> declaratedIDs = new ArrayList<Node>();
	
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
	
	public void declareID(Node node, int line) {
		try {
			Atributo at = ManipuladorXMI.contemAtributo(getContextClass(), getContextClass(), (String)node.getValue());
			if (at != null)
				error(line, "O identificador " + node.getValue() + " ja foi declarado no XMI.");
		} catch (Exception e) {
			for (Node n : declaratedIDs){
				if (((String)n.getValue()).equals((String)node.getValue())){
					error(line, "O identificador " + n.getValue() + " ja foi declarado anteriormente.");
					break;
				}
			}
		}
		declaratedIDs.add(node);
	}
	
	public List<Node> getDeclaratedIDs() {
		return this.declaratedIDs;
	}
	
	public void checkCollectionOperation(String operation, String parameterType, int line) throws SemanticErrorException {
		if (!checkCollectionOpName(operation))
			throw new SemanticErrorException(operation + " (na linha " + (line+1) + ") nao eh uma operacao de collection " +
					"definida pela linguagem.");
		if (!checkCollectionOpParams(operation, parameterType))
			error(line, "tipo de parametro errado para a operacao " + operation);
	}
	
	public Node getReturnType(String type, String operation) {
		Node node = new Node();
		if (operation.equals("size"))
			node.setType("Integer");
		else if (operation.equals("empty") || operation.equals("forAll") || operation.equals("exists") 
				|| operation.equals("includes") || operation.equals("excludes"))
			node.setType("Boolean");
		if (operation.equals("select") || operation.equals("first"))
			node.setType(type);
		if (operation.equals("including") || operation.equals("excluding")){
		   node.setType(type);
		   node.setCollection(true);
		}
		return node;
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
	
	public boolean getDeclarator() {
		return declarator;
	}

	public void setDeclarator(boolean declarator) {
		this.declarator = declarator;
	}

	public String maxType(String type1, String type2, int line){
		if (type1.equalsIgnoreCase("void") || type2.equalsIgnoreCase("void"))
			error(line,"Impossivel realizar operacao com o tipo void");
		String type = null;
		try{
			type = ManipuladorXMI.maxType(type1, type2);
		} catch (Exception e) {
			error(line,"Impossivel realizar operacao entre " + type1 + " e " + type2);
		}
		return type;
	}
	
	public Node checkTypesOpArithmetic(Node rule1, Node rule2, int line ){
		Node node = new Node(); 
        if (rule2 == null)
        	node = (Node)rule1;
        else{
        	String type = maxType(((Node)rule1).getType(), ((Node)rule2).getType(), line);
        	if (type == null){
        		type = ((Node)rule2).getType();
        	}
 			node.setType(type);
        }
        return node;
	}
	
	public Node checkTypesOpArithmeticAux(Node rule1, Node rule2, String operator, int line) {
		Node node = new Node();
		String type;
		if (rule2 == null) {
			type = ((Node) rule1).getType();
			if (!isNumeric(type)) {
				error(line, "operador ' " + operator
						+ " ' indefinido para o tipo " + type);
			}
			node.setType(type);
		} else {
			type = maxType(((Node) rule1).getType(), ((Node) rule2).getType(),
					line);
			if (type == null) 
				type = ((Node) rule2).getType();
			node.setType(type);
		}
		node.setOperation(operator);
		return node;
	}
	
	public void checkRelationalOp(String operator, String type) {
		if (type == null)
			return;
		if (!isNumeric(type)){
			if (!operator.equals("=") && !operator.equals("<>"))
				throw new RelationalErrorException("o operador " + operator + " nao pode ser usado para comparar valores do tipo " + type);
		}
	}
	
	public void checkOperationContext(Node rule1, Node params, Node rule2,int line) throws SemanticErrorException{
		setContext(((Node)rule1).toString(), params, line); 
		if (!getContextType().equals(((Node)rule2).toString()))
	 			throw new excecoes.SemanticErrorException("tipo especificado no contexto diferente do tipo da operacao na linha " 
			 	+ (line+1) + ".\n\t" + ((Node)rule2).toString() + " deve ser substituido por " + getContextType());
	}
	
	public void setContext(String exp, Node params, int line) throws SemanticErrorException {
		String[] separate = exp.split("::");
		
		String classe = separate[separate.length-2];

		String metodo = separate[separate.length-1];

		System.out.println("Classe: "+classe);
		System.out.println("Método: "+metodo);
		
		setContextClass(classe);
		
		try {
			Operacao op = ManipuladorXMI.contemFuncao(classe,classe,metodo);
			checkParamsContext(op, params.getElements(), line);
			setContextMethod(metodo);
			String type;
			if (op.getReturnClass() != null)
				type = op.getReturnClass().getName();
			else
				type = op.getReturnType();
			if (type == null)
				type = "Void";
			setContextType(type);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemanticErrorException(e.getMessage() + " Linha " + (line+1));
		}
	}
	
	private void checkParamsContext(Operacao op, List<Node> elements, int line) {
		ArrayList<Parametro> params = op.getParametros();
		if (params.size() != elements.size())
			error(line, "numero errado de parametros para a funcao " + op.getNome() + " na assinatura do contexto.\n\tDeve(m) ser passado(s) " +
					params.size() + " parametro(s), mas foi(foram) passado(s) " + elements.size());
		for (int i=0; i<params.size(); i++){
			String type = null;
			if (params.get(i).getTipo() != null)
				type = params.get(i).getTipo().getName();
			else
				type = params.get(i).getIdTipo();
			if (!type.equals(elements.get(i).getType()))
				error(line, "tipo de parametro errado na chamada a funcao " + op.getNome() + ".\n\tO " + (i+1) + "º parametro"
					+ " deveria ser um " + type + ", mas foi passado um " + elements.get(i).getType());
		}
		
	}
	
	public Node checkFeatureCall(String classe, Node elemento, int line) throws SemanticErrorException{
		Node node = new Node();
		boolean isCollection = false;
		try {
			String type = null;
			if (elemento.getRole() == Node.FUNCTION){
				Operacao op = ManipuladorXMI.contemFuncao(getContextClass(),classe,(String)elemento.getValue());
				if (op.getReturnClass() != null)
					type = op.getReturnClass().getName();
				else
					type = op.getReturnType();
				checkParams(op, elemento.getElements(), line);
			} else if (elemento.getRole() == Node.VARIABLE){
				Atributo at = ManipuladorXMI.contemAtributo(getContextClass(), classe, (String)elemento.getValue());
				if (at.getTipo() != null)
					type = at.getTipo().getName();
				else
					type = at.getIdTipo();
				isCollection = at.ehColecao();
			}
			if (type == null)
				type = "Void";
			node.setType(type);
			node.setValue(elemento.getValue());
			node.setCollection(isCollection);
		} catch (Exception e){
			if ((elemento.getRole() == Node.VARIABLE) && classe.equals(getContextClass())){
				for (Node n : getDeclaratedIDs()){
					if (((String)n.getValue()).equals((String)elemento.getValue())){
						return n;
					}
				}
			}
			throw new SemanticErrorException(e.getMessage() + " Linha " + (line+1));
		}
		return node;
	}

	private void checkParams(Operacao op, List<Node> elements, int line) {
		ArrayList<Parametro> params = op.getParametros();
		if (params.size() != elements.size())
			error(line, "numero errado de parametros na chamada a funcao " + op.getNome() + ".\n\tDeve(m) ser passado(s) " +
					params.size() + " parametro(s), mas foi(foram) passado(s) " + elements.size());
		for (int i=0; i<params.size(); i++){
			String type = null;
			if (params.get(i).getTipo() != null)
				type = params.get(i).getTipo().getName();
			else
				type = params.get(i).getIdTipo();
			if (!type.equals(elements.get(i).getType()))
				error(line, "tipo de parametro errado na chamada a funcao " + op.getNome() + ".\n\tO " + (i+1) + "º parametro"
					+ " deveria ser um " + params.get(i).getTipo().getName() + ", mas foi passado um " + elements.get(i).getType());
		}
		
	}
	
	private boolean isNumeric(String type){
		return (type.equalsIgnoreCase("Float") || type.equalsIgnoreCase("Integer") 
				|| type.equalsIgnoreCase("Double") || type.equalsIgnoreCase("Long"));
	}
	
}
