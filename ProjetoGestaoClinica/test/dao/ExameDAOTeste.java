package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entities.Exame;

public class ExameDAOTeste {
	
	public static void cadastrarExameTeste() throws SQLException, IOException {

		Exame exame = new Exame();
		exame.setNome("Exame de sangue");
		exame.setValor(60);
		exame.setOrientacoes("Fazer jejum");

		Connection conn = BancoDados.conectar();
		new ExameDAO(conn).cadastrar(exame);

		System.out.println("Cadastro efetuado com sucesso.");
	}
	
	public static void buscarTodosExamesTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Exame> listaExames = new ExameDAO(conn).buscarTodos();

		for (Exame exame : listaExames) {

			System.out.println(exame.getCodigo() + " - " + exame.getNome() + " - " + exame.getValor() + " - "
					+ exame.getOrientacoes());
		}
	}
	
	public static void buscarPorCodigoTeste() throws SQLException, IOException {

		int codigoExame = 1;

		Connection conn = BancoDados.conectar();
		Exame exame = new ExameDAO(conn).buscarPorCodigo(codigoExame);

		if (exame != null) {

			System.out.println(exame.getCodigo() + " - " + exame.getNome() + " - " + exame.getValor() + " - "
					+ exame.getOrientacoes());

		} else {

			System.out.println("Código não encontrado.");
		}
	}
	
	public static void atualizarExameTeste() throws SQLException, IOException {

		Exame exame = new Exame();
		exame.setCodigo(2);
		exame.setNome("Exame de urina");
		exame.setValor(45);
		exame.setOrientacoes("Beber 2L de água");

		Connection conn = BancoDados.conectar();
		new ExameDAO(conn).atualizar(exame);

		System.out.println("Dados do exame atualizados com sucesso.");
	}
	
	public static void excluirExameTeste() throws SQLException, IOException {

		int codigoExame = 1;

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new ExameDAO(conn).excluir(codigoExame);

		if (linhasManipuladas > 0) {

			System.out.println("Exame excluído com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) {

		try {

			ExameDAOTeste.cadastrarExameTeste();
			//ExameDAOTeste.buscarTodosExamesTeste();
			//ExameDAOTeste.buscarPorCodigoTeste();
			//ExameDAOTeste.atualizarExameTeste();
			//ExameDAOTeste.excluirExameTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
