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
	private List<String> list;
	
	public Node(Object value, String type) {
		this.value = value;
		this.type = type;
		this.list = new ArrayList<String>();
	}

	public Node(Object value) {
		this.value = value;
		this.list = new ArrayList<String>();
	}
	
	public Node() {
		this.list = new ArrayList<String>();
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
	
	public void addElement(String element){
		list.add(0, element);
	}
	
	public Iterator<String> iterator(){
		return list.iterator();
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
