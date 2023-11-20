package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.PacienteDAO;
import entities.Paciente;

public class PacienteService {
	
	public PacienteService() {

	}

	public void cadastrar(Paciente paciente) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		new PacienteDAO(conn).cadastrar(paciente);
	}

	public List<Paciente> buscarTodos() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new PacienteDAO(conn).buscarTodos();
	}

	public Paciente buscarPorNome(String nome) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new PacienteDAO(conn).buscarPorNome(nome);
	}

}
