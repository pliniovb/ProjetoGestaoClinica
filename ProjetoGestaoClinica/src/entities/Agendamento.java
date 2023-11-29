package entities;

import java.sql.Date;

public class Agendamento {
	
	private Paciente paciente;
	private Medico medico;
	private Date dataConsulta;
	private String hora;
	
	public Agendamento() {
		this.paciente = new Paciente();
		this.medico = new Medico();
	}
	
	public Agendamento(Paciente paciente, Medico medico, Date dataConsulta, String hora) {
		
		this.paciente = paciente;
		this.medico = medico;
		this.dataConsulta = dataConsulta;
		this.hora = hora;
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

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	

}
