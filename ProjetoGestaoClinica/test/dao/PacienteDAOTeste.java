package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entities.Paciente;

public class PacienteDAOTeste {
	
	public static void cadastrarPacienteTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Paciente paciente = new Paciente();
		paciente.setNome("Isabella Novaes2");
		paciente.setSexo("Feminino");
		paciente.setDataNascimento(new java.sql.Date(sdf.parse("25/06/2003").getTime()));
		paciente.setLogradouro("Rua Coronel Dulcídio");
		paciente.setBairro("Centro");
		paciente.setCidade("Ponta Grossa");
		paciente.setUf("PR");
		paciente.setNumero(1602);
		paciente.setTelefone("(11)95329-5838");
		paciente.setFormaPagamento("Débito");

		Connection conn = BancoDados.conectar();
		new PacienteDAO(conn).cadastrar(paciente);

		System.out.println("Cadastro efetuado com sucesso.");
	}

	public static void buscarTodosPacientesTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Paciente> listaPacientes = new PacienteDAO(conn).buscarTodos();

		for (Paciente paciente : listaPacientes) {

			System.out.println(paciente.getNome() + " - " + paciente.getSexo() + " - " + paciente.getDataNascimento() + " - " + 
								paciente.getLogradouro() + " - " + paciente.getBairro() + " - " + paciente.getCidade() + " - " + 
								paciente.getUf() + " - " + paciente.getNumero() + " - " + paciente.getTelefone() + " - " + paciente.getFormaPagamento());
		}
	}

	public static void buscarPorNomeTeste() throws SQLException, IOException {

		String nome = "Isabella Novaes";

		Connection conn = BancoDados.conectar();
		Paciente paciente = new PacienteDAO(conn).buscarPorNome(nome);

		if (paciente != null) {

			System.out.println(paciente.getNome() + " - " + paciente.getSexo() + " - " + paciente.getDataNascimento() + " - " + 
								paciente.getLogradouro() + " - " + paciente.getBairro() + " - " + paciente.getCidade() + " - " + 
								paciente.getUf() + " - " + paciente.getNumero() + " - " + paciente.getTelefone() + " - " + paciente.getFormaPagamento());

		} else {

			System.out.println("Nome não encontrado.");
		}
	}

	public static void atualizarPacienteTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Paciente paciente = new Paciente();
		paciente.setNome("Ana Vieira");
		paciente.setSexo("Feminino");
		paciente.setDataNascimento(new java.sql.Date(sdf.parse("10/05/2003").getTime()));
		paciente.setLogradouro("Avenida Júlio de Castilho");
		paciente.setBairro("Centro");
		paciente.setCidade("Ponta Grossa");
		paciente.setUf("PR");
		paciente.setNumero(625);
		paciente.setTelefone("(11)94444-9494");
		paciente.setFormaPagamento("Crédito");

		Connection conn = BancoDados.conectar();
		new PacienteDAO(conn).atualizar(paciente);

		System.out.println("Dados do paciente atualizados com sucesso.");
	}

	public static void excluirPacienteTeste() throws SQLException, IOException {

		String nome = "Isabella Novaes";

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new PacienteDAO(conn).excluir(nome);

		if (linhasManipuladas > 0) {

			System.out.println("Paciente excluído com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) throws ParseException{

		try {
			
			PacienteDAOTeste.cadastrarPacienteTeste();
			//PacienteDAOTeste.buscarTodosPacientesTeste();
			//PacienteDAOTeste.buscarPorNomeTeste();
			//PacienteDAOTeste.atualizarPacienteTeste();
			//PacienteDAOTeste.excluirPacienteTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
