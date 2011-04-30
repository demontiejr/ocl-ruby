package xmiParser.util;

import java.util.ArrayList;

public class Classe implements Entidade{
	
	private String name;
	private String visibility;
	private String idClassePai;
	private Classe classePai;
	
	private ArrayList<Atributo> atributos = new ArrayList<Atributo>();
	private ArrayList<Operacao> operacoes = new ArrayList<Operacao>();
	
	
	public Classe(String n, String v) {
		this.name = n;
		this.visibility = v;
		this.idClassePai = "";
		this.classePai = null;
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

	public String getIdClassePai() {
		return idClassePai;
	}

	public void setIdClassePai(String idClassePai) {
		this.idClassePai = idClassePai;
	}
	
	@Override
	public String toString() {
		String heranca = "";
		if(idClassePai!=null)
			heranca = idClassePai;
		if(classePai!=null)
			heranca = classePai.getName();
		return " "+name+"("+heranca+") ";
	}

	public void addAtributo(Atributo att) throws Exception {
		this.atributos.add(att);
	}

	public Classe getClassePai() {
		return classePai;
	}

	public void setClassePai(Classe classePai) {
		this.classePai = classePai;
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<Atributo> atributos) {
		this.atributos = atributos;
	}

	public ArrayList<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	public void addOperacao(Operacao op) {
		this.operacoes.add(op);
	}
}
