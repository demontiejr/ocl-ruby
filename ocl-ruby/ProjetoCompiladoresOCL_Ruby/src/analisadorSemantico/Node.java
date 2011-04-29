package analisadorSemantico;

import java.util.ArrayList;
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
	private List<String> attributesTypes;
	
	public Node(Object value, String type) {
		this.value = value;
		this.type = type;
		this.attributesTypes = new ArrayList<String>();
	}

	public Node(Object value) {
		this.value = value;
		this.attributesTypes = new ArrayList<String>();
	}
	
	public Node() {
		this.attributesTypes = new ArrayList<String>();
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
	
	public void addAttributesTypes(String attribute){
		attributesTypes.add(attribute);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
