package xmiParser.util;

public class Atributo {
	
	private String idTipo;
	private Entidade tipo;
	private String visibilidade;
	private String nome;
	
	//Atributos que vão me informar se o atributo eh uma colecao
	//Ex1.: Relacionamento 0..* vai ter qtdMin e qtdMax = -1
	//Ex2.: Relacionamento 1..* tem qtdMin = 1 e qtdMax = -1
	//Ex3.: Relacionamento 1..5 tem qtdMin = 1 e qtdMax = 5
	private int qtdMax;
	private int qtdMin;

	
	public Atributo(String id, String v, String n, int qtdM, int qtdMi) {
		this.idTipo = id;
		this.visibilidade = v;
		this.nome = n;
		this.qtdMax = qtdM;
		this.qtdMin = qtdMi;
		this.tipo = null;
	}


	public String getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(String idTipo) {
		this.idTipo = idTipo;
	}


	public Entidade getTipo() {
		return tipo;
	}


	public void setTipo(Entidade tipo) {
		this.tipo = tipo;
	}


	public String getVisibilidade() {
		return visibilidade;
	}


	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}


	public String getNome() {
		if(nome!=null)
			return nome;
		else{
			String aux = tipo.getName();
			if(aux.length()>=1);
				aux = (""+aux.charAt(0)).toLowerCase()+aux.substring(1);
			return ehColecao()?aux+"s":aux;
		}
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getQtdMax() {
		return qtdMax;
	}


	public void setQtdMax(int qtdMax) {
		this.qtdMax = qtdMax;
	}


	public int getQtdMin() {
		return qtdMin;
	}


	public void setQtdMin(int qtdMin) {
		this.qtdMin = qtdMin;
	}
	
	@Override
	public String toString() {
		String tipo = this.tipo==null?this.idTipo:this.tipo.getName();
		return " "+getNome()+" ("+tipo+") ["+qtdMin+" "+qtdMax+"] ";
	}


	public boolean ehColecao() {
		return (qtdMax>1 || qtdMax<0);
	}
}
