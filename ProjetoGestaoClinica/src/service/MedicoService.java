package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.MedicoDAO;
import entities.Especialidade;
import entities.Medico;

public class MedicoService {
	
	public MedicoService() {
		
	}
	
	public List<Medico> buscarTodos() throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		List <Medico> medicos = new MedicoDAO(conn).buscarTodos();
		
		EspecialidadeService especialidadeService = new EspecialidadeService();
		
		for (Medico medico : medicos) {
						
			Especialidade especialidade = especialidadeService.buscarPorCodigo(medico.getEspecialidade().getCodigo());
			medico.setEspecialidade(especialidade);
		}
		
		return medicos;
	}
	
	public void cadastrar(Medico medico) throws SQLException, IOException {
		
		Connection conn = BancoDados.conectar();
		new MedicoDAO(conn).cadastrar(medico);
	}
	
	public Medico buscarPorCrm(int crm) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new MedicoDAO(conn).buscarPorCrm(crm);
	}
	
	public Medico buscarPorNome(String nomeMedico) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new MedicoDAO(conn).buscarPorNome(nomeMedico);
	}

}
