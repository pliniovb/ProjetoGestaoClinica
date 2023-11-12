package entities;

public class Exame {
	
	private int codigo;
	private String nome;
	private double valor;
	private String orientacoes;
	
	public Exame() {
		
	}
	
	public Exame(int codigo, String nome, double valor, String orientacoes) {
		
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.orientacoes = orientacoes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

}