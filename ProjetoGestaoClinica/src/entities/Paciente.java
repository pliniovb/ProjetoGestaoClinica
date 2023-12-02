package entities;

import java.sql.Date;

public class Paciente {
	
	private String nome;
	private String sexo;
	private Date dataNascimento;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	private int numero;
	private String telefone;
	private String formaPagamento;
	
	public Paciente() {
		
	}
	
	public Paciente(String nome, String sexo, Date dataNascimento, String logradouro, String bairro, String cidade, String uf, int numero, String telefone, String formaPagamento) {
		
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.numero = numero;
		this.telefone = telefone;
		this.formaPagamento = formaPagamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Object object) {
		this.formaPagamento = (String) object;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(Object object) {
		this.uf = (String) object;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		return this.getNome();
	}

}