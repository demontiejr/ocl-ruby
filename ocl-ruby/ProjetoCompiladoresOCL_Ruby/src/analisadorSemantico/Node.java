package analisadorSemantico;

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
	
	public Node(Object value, String type) {
		this.value = value;
		this.type = type;
	}

	public Node(Object value) {
		this.value = value;
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
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
