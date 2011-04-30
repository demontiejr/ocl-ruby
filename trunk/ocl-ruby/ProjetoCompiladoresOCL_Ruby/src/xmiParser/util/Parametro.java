package xmiParser.util;

public class Parametro {
	private String nome;
	private String idTipo;
	private Entidade tipo;
	
	public Parametro(String n, String t) {
		this.nome = n;
		this.idTipo = t;
		this.tipo = null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
}
