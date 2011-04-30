package xmiParser.util;

public class EnumValue {
	
	private String name;
	private String visibility;
	
	public EnumValue(String n, String v) {
		this.name = n;
		this.visibility = v;
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

}
