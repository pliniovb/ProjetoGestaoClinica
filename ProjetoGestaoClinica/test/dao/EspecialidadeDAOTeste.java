package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entities.Especialidade;


public class EspecialidadeDAOTeste {
	
	public static void cadastrarEspecialidadeTeste() throws SQLException, IOException {

		Especialidade especialidade = new Especialidade();
		especialidade.setNome("Otorrino");

		Connection conn = BancoDados.conectar();
		new EspecialidadeDAO(conn).cadastrar(especialidade);

		System.out.println("Cadastro efetuado com sucesso.");
	}
	
	public static void buscarTodasEspecialidadesTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Especialidade> listaEspecialidades = new EspecialidadeDAO(conn).buscarTodas();

		for (Especialidade especialidade : listaEspecialidades) {

			System.out.println(especialidade.getCodigo() + " - " + especialidade.getNome());
		}
	}
	
	public static void buscarPorCodigoTeste() throws SQLException, IOException {

		int codigoEspecialidade = 10;

		Connection conn = BancoDados.conectar();
		Especialidade especialidade = new EspecialidadeDAO(conn).buscarPorCodigo(codigoEspecialidade);

		if (especialidade != null) {

			System.out.println(especialidade.getCodigo() + " - " + especialidade.getNome());

		} else {

			System.out.println("Código não encontrado.");
		}
	}
	
	public static void atualizarEspecialidadeTeste() throws SQLException, IOException {

		Especialidade especialidade = new Especialidade();
		especialidade.setCodigo(3);
		especialidade.setNome("Cirurgião");

		Connection conn = BancoDados.conectar();
		new EspecialidadeDAO(conn).atualizar(especialidade);

		System.out.println("Dados da especialidade atualizados com sucesso.");
	}
	
	public static void excluirCursoTeste() throws SQLException, IOException {

		int codigoEspecialidade = 3;

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new EspecialidadeDAO(conn).excluir(codigoEspecialidade);

		if (linhasManipuladas > 0) {

			System.out.println("Especialidade excluída com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) {

		try {

			EspecialidadeDAOTeste.cadastrarEspecialidadeTeste();
			//EspecialidadeDAOTeste.buscarTodasEspecialidadesTeste();
			//EspecialidadeDAOTeste.buscarPorCodigoTeste();
			//EspecialidadeDAOTeste.atualizarEspecialidadeTeste();
			//EspecialidadeDAOTeste.excluirCursoTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
