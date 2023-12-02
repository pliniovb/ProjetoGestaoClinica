package entities;

import java.sql.Date;

public class Pedido {

	private Exame exame;
	private Paciente paciente;
	private Medico medico;
	private Date dataRealizacao;
	private double valorPago;
	
	public Pedido() {
		
		this.exame = new Exame();
		this.paciente = new Paciente();
		this.medico = new Medico();
	}
	
	public Pedido(Exame exame, Paciente paciente, Medico medico, Date dataRealizacao, double valorPago) {
		
		this.exame = exame;
		this.paciente = paciente;
		this.medico = medico;
		this.dataRealizacao = dataRealizacao;
		this.valorPago = valorPago;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
}
