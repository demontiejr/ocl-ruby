package analisadorSemantico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que serve para guardar os atributos
 * da gramatica anotada.
 * 
 * @author Demontie Junior
 * @author Izabela Vanessa
 * @author Savyo Igor
 *
 */
public class Node {

	private Object value;
	private String type;
	private String operation;
	private boolean collection;
	private List<Node> list;
	
	public static final int VALUE = 0;
	public static final int VARIABLE = 1;
	public static final int FUNCTION = 2;
	
	private int role;

	//gerador de codigo
	private String code = "";
	//para saber se eh ifExpression
	public boolean isIfExpression = false;
	//para saber se eh atributo de classe
	public boolean isClassAtribute = false;
	
	//para implies
	public boolean isImplies = false;
	
	public Node(Object value, String type) {
		this.value = value;
		this.type = type;
		this.list = new ArrayList<Node>();
	}

	public Node(Object value) {
		this.value = value;
		this.list = new ArrayList<Node>();
	}
	
	public Node() {
		this.list = new ArrayList<Node>();
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void addElement(Node element){
		list.add(0, element);
	}
	
	public void addAllElements(List<Node> list){
		this.list.addAll(0, list);
	}
	
	public Iterator<Node> iterator(){
		return list.iterator();
	}
	
	public List<Node> getElements(){
		return this.list;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
