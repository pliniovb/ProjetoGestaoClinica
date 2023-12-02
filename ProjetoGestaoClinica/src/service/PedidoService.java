package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.PedidoDAO;
import entities.Exame;
import entities.Medico;
import entities.Paciente;
import entities.Pedido;

public class PedidoService {
	
public PedidoService() {
		
	}
	
	public List<Pedido> buscarTodos() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		List <Pedido> pedidos = new PedidoDAO(conn).buscarTodos();
		
		ExameService exameService = new ExameService();
		PacienteService pacienteService = new PacienteService();
		MedicoService medicoService = new MedicoService();
		
		for (Pedido pedido : pedidos) {
						
			Exame exame = exameService.buscarPorCodigo(pedido.getExame().getCodigo());
			Paciente paciente = pacienteService.buscarPorNome(pedido.getPaciente().getNome());
			Medico medico = medicoService.buscarPorCrm(pedido.getMedico().getCrm());
			pedido.setExame(exame);
			pedido.setPaciente(paciente);
			pedido.setMedico(medico);
		}
		
		return pedidos;
	}
	
	public void cadastrar(Pedido pedido) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new PedidoDAO(conn).cadastrar(pedido);
	}

}
