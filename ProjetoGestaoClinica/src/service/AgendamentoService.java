package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AgendamentoDAO;
import dao.BancoDados;
import entities.Agendamento;
import entities.Medico;
import entities.Paciente;

public class AgendamentoService {
	
public AgendamentoService() {
		
	}
	
	public List<Agendamento> buscarTodos() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		List <Agendamento> agendamentos = new AgendamentoDAO(conn).buscarTodos();
		
		PacienteService pacienteService = new PacienteService();
		MedicoService medicoService = new MedicoService();
		
		for (Agendamento agendamento : agendamentos) {
						
			Paciente paciente = pacienteService.buscarPorNome(agendamento.getPaciente().getNome());
			Medico medico = medicoService.buscarPorNome(agendamento.getMedico().getNome());
			agendamento.setPaciente(paciente);
			agendamento.setMedico(medico);
		}
		
		return agendamentos;
	}
	
	public void cadastrar(Agendamento agendamento) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new AgendamentoDAO(conn).cadastrar(agendamento);
	}

}
