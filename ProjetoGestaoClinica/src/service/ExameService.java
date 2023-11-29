package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.ExameDAO;
import entities.Exame;

public class ExameService {
	
	public ExameService() {

	}

	public void cadastrar(Exame exame) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		new ExameDAO(conn).cadastrar(exame);
	}

	public List<Exame> buscarTodos() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new ExameDAO(conn).buscarTodos();
	}

	public Exame buscarPorCodigo(int codigoExame) throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		return new ExameDAO(conn).buscarPorCodigo(codigoExame);
	}

}
