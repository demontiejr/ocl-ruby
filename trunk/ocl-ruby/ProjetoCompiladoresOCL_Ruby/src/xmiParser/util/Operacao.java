package xmiParser.util;

import java.util.ArrayList;

public class Operacao {
	
	private String nome;
	private String returnType;
	private Entidade returnClass;
	private String visibility;
	
	private ArrayList<Parametro> parametros;

	public Operacao(String n, String retorno, String v, ArrayList<Parametro> params) {
		this.nome = n;
		this.returnType = retorno;
		this.visibility = v;
		this.returnClass = null;
		this.parametros = params;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public Entidade getReturnClass() {
		return returnClass;
	}

	public void setReturnClass(Entidade returnClass) {
		this.returnClass = returnClass;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	@Override
	public String toString() {
		String imp = " "+this.nome+"(";
		String params = "";
		String sep = "";
		for (Parametro p : parametros) {
			params+= sep+p.getNome();
			sep = ",";
		}
		imp+=params+")";
		String ret = "";
		if(returnType==null)
			ret = "void";
		if(returnClass!=null)
			ret = returnClass.getName();
		imp+=":"+ret;
		return imp;
	}
}
