package analisadorSemantico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xmiParser.XMIManager;
import xmiParser.util.Atributo;
import xmiParser.util.OperacaoMaior;
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

	private String actualContextClass;
	private String contextMethod;
	private String contextType;
	
	private String declaratedContextClass;

	private String stereotype;
	private String[] collectionOperations = {"forAll", "exists", "includes", "excludes",
			"including", "size", "excluding", "select", "empty", "first"};
	private String opID;
	private Set<String> logErros = new HashSet<String>();
	
	private List<Node> declaratedIDs = new ArrayList<Node>();
	
	public boolean opCollection = false;
	private boolean declarator = false;
	private List<Node> declaratorAux = new ArrayList<Node>();
	
	public List<Node> auxList;
	public boolean estaEmOpCollection = false;
	
	public String tipoPrimary;
	
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
		return actualContextClass;
	}

	public void setContextClass(String contextClass) {
		this.actualContextClass = contextClass;
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
	
	public String getDeclaratedContextClass() {
		return declaratedContextClass;
	}

	private void setDeclaratedContextClass(String declaratedContextClass) {
		this.declaratedContextClass = declaratedContextClass;
	}
	
	public void declareID(Node node, int line) {
		try {
			Atributo at = XMIManager.containsAttribute(getContextClass(), getContextClass(), (String)node.getValue());
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
		if (operation.equals("first"))
			node.setType(type);
		if (operation.equals("select") || operation.equals("including") || operation.equals("excluding")){
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
			return parameterType.equals(tipoPrimary);
		}else if (operation.equals("including") || operation.equals("excluding")){
		   //TODO
		   //including(object : T) -> retorna o mesmo tipo da colecao
		   //excluding(object : T) -> retorna o mesmo tipo da colecao
			return parameterType.equals(tipoPrimary);
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
	
	public void setDeclaratorAux(List<Node> declaratorAux) {
		this.declaratorAux = declaratorAux;
	}

	public List<Node> getDeclaratorAux() {
		return declaratorAux;
	}

	public String maxType(String type1, String type2, int line){
		if (type1.equalsIgnoreCase("void") || type2.equalsIgnoreCase("void"))
			error(line,"Impossivel realizar operacao com o tipo void");
		String type = null;
		try{
			type = XMIManager.maxType(type1, type2);
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
		
		setContextClass(classe);
		setDeclaratedContextClass(classe);
		
		try {
			OperacaoMaior op = XMIManager.containsFunction(classe,classe,metodo);
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
			throw new SemanticErrorException(e.getMessage() + " Linha " + (line+1));
		}
	}
	
	private void checkParamsContext(OperacaoMaior op, List<Node> elements, int line) {
		boolean found = false;
		outer:for (ArrayList<Parametro> params : op.getListaParametros()) {
			if (params.size() != elements.size()) {
				continue;
			}
			for (int i = 0; i < params.size(); i++) {
				String type = null;
				if (params.get(i).getTipo() != null)
					type = params.get(i).getTipo().getName();
				else
					type = params.get(i).getIdTipo();
				if (!type.equals(elements.get(i).getType()))
					continue outer;
			}
			found = true;
			break;
		}
		if (!found)
			error(line, "lista de parametros errada para o metodo " + op.getNome());
	}
	
	public Node checkFeatureCall(String c, Node elemento, int line) throws SemanticErrorException{
		Node node = new Node();
		boolean isCollection = false;
		String classe = c;
		System.err.println(opCollection);
		System.err.println("  " + tipoPrimary);
		System.err.println("  " + (String)elemento.getValue());
		if (opCollection)
			classe = tipoPrimary;
		try {
			String type = null;
			if (elemento.getRole() == Node.FUNCTION){
				OperacaoMaior op = XMIManager.containsFunction(getContextClass(),classe,(String)elemento.getValue());
				if (op.getReturnClass() != null)
					type = op.getReturnClass().getName();
				else
					type = op.getReturnType();
				checkParams(op, elemento.getElements(), line);
				String aux = isCollection(type);
				if (aux != null) {
					type = aux;
					isCollection = true;
				}
			} else if (elemento.getRole() == Node.VARIABLE){
				Atributo at = XMIManager.containsAttribute(getContextClass(), classe, (String)elemento.getValue());
				if (at.getTipo() != null)
					type = at.getTipo().getName();
				else
					type = at.getIdTipo();
				isCollection = at.ehColecao();
				if (!at.ehColecao()){
					String aux = isCollection(type);
					if (aux != null){
						type = aux;
						isCollection = true;
					}
				}
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
				if (getDeclarator()) {
					for (Node n : getDeclaratorAux()) {
						if (((String) n.getValue()).equals((String) elemento
								.getValue())) {
							n.setType(tipoPrimary);
							return n;
						}
					}
				}
			}
			System.err.println("classe: " + classe);
			throw new SemanticErrorException(e.getMessage() + " Linha " + (line+1));
		}
		return node;
	}

	private void checkParams(OperacaoMaior op, List<Node> elements, int line) {
		boolean found = false;
		outer:for (ArrayList<Parametro> params : op.getListaParametros()) {
			if (params.size() != elements.size()) {
				continue;
			}
			for (int i = 0; i < params.size(); i++) {
				String type = null;
				if (params.get(i).getTipo() != null)
					type = params.get(i).getTipo().getName();
				else
					type = params.get(i).getIdTipo();
				if (!ehSubtipo(elements.get(i).getType(), type))
					continue outer;
			}
			found = true;
			break;
		}
		if (!found)
			error(line, "lista de parametros errada para o metodo " + op.getNome());
	}
	
	public boolean ehSubtipo(String sub, String sup){
		boolean ret = false;
		try{
			String type = XMIManager.maxType(sub, sup);
			ret = type.equals(sup);;
		} catch (Exception e) {
		}
		return ret;
	}
	
	private boolean isNumeric(String type){
		return (type.equalsIgnoreCase("Float") || type.equalsIgnoreCase("Integer") 
				|| type.equalsIgnoreCase("Double") || type.equalsIgnoreCase("Long"));
	}
	
	private String isCollection(String entrada){
		if(entrada != null){
			String[] lista = entrada.split("<");
			if(lista.length > 1){
				String[] listaAux = (lista[lista.length-1]).split(">");
				if(listaAux.length == 1){
					return listaAux[0];
				}
			}
		}		
		return null;
	}
	
}
