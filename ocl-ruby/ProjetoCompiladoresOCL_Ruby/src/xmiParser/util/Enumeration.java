package xmiParser.util;

import java.util.ArrayList;

public class Enumeration implements Entidade{
	private String name;
	private String visibility;
	private ArrayList<EnumValue> valores;
	
	public Enumeration(String n, String v) {
		this.name = n;
		this.visibility = v;
		this.valores = new ArrayList<EnumValue>();
	}

	public ArrayList<EnumValue> getValores() {
		return valores;
	}

	public void setValores(ArrayList<EnumValue> valores) {
		this.valores = valores;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public void addValue(String nodeValue,String v) {
		this.valores.add(new EnumValue(nodeValue,v));
	}
}
