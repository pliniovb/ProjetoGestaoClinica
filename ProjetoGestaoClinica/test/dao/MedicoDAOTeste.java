package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import entities.Especialidade;
import entities.Medico;

public class MedicoDAOTeste {
	
	public static void cadastrarMedicoTeste() throws SQLException, IOException {
		
		Especialidade especialidade = new Especialidade();
		especialidade.setNome("Pediatra");

		Medico medico = new Medico();
		medico.setCrm(12333);
		medico.setNome("Maria Clara da Silva");
		medico.setLogradouro("Avenida Monteiro Lobato");
		medico.setBairro("Jardim Carvalho");
		medico.setCidade("Ponta Grossa");
		medico.setUf("PR");
		medico.setNumero(195);
		medico.setTelefone("(11)91234-5555");
		medico.getEspecialidade().setCodigo(2);
		
		Connection conn = BancoDados.conectar();
		new MedicoDAO(conn).cadastrar(medico);

		System.out.println("Cadastro efetuado com sucesso.");
	}

	public static void buscarTodosMedicosTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Medico> listaMedicos = new MedicoDAO(conn).buscarTodos();

		for (Medico medico : listaMedicos) {

			System.out.println(medico.getCrm() + " - " + medico.getNome() + " - " + medico.getLogradouro() + " - " + medico.getBairro() + " - " + medico.getCidade() + " - " + medico.getUf() + " - " + medico.getNumero() + " - " + medico.getTelefone() + " - " + medico.getEspecialidade().getCodigo());
		}
	}

	public static void buscarPorCrmTeste() throws SQLException, IOException {

		int crm = 1;

		Connection conn = BancoDados.conectar();
		Medico medico = new MedicoDAO(conn).buscarPorCrm(crm);

		if (medico != null) {

			System.out.println(medico.getCrm() + " - " + medico.getNome() + " - " + medico.getLogradouro() + " - " + medico.getBairro() + " - " + medico.getCidade() + " - " + medico.getUf() + " - " + medico.getNumero() + " - " + medico.getTelefone() + " - " + medico.getEspecialidade().getCodigo());

		} else {

			System.out.println("CRM não encontrado.");
		}
	}
	
	public static void buscarPorNomeTeste() throws SQLException, IOException {

		String nomeMedico = "João Paulo";

		Connection conn = BancoDados.conectar();
		Medico medico = new MedicoDAO(conn).buscarPorNome(nomeMedico);

		if (medico != null) {

			System.out.println(medico.getCrm() + " - " + medico.getNome() + " - " + medico.getLogradouro() + " - " + medico.getBairro() + " - " + medico.getCidade() + " - " + medico.getUf() + " - " + medico.getNumero() + " - " + medico.getTelefone() + " - " + medico.getEspecialidade().getCodigo());

		} else {

			System.out.println("CRM não encontrado.");
		}
	}

	public static void atualizarMedicoTeste() throws SQLException, IOException, ParseException {

		
		Especialidade especialidade = new Especialidade();
		especialidade.setNome("Neurologista");

		Medico medico = new Medico();
		medico.setCrm(12333);
		medico.setNome("Maria Clara da Silva");
		medico.setLogradouro("Avenida Bonifácio Vilela");
		medico.setBairro("Centro");
		medico.setCidade("Ponta Grossa");
		medico.setUf("PR");
		medico.setNumero(2011);
		medico.setTelefone("(11)91234-5555");
		medico.getEspecialidade().setCodigo(2);

		Connection conn = BancoDados.conectar();
		new MedicoDAO(conn).atualizar(medico);

		System.out.println("Dados do médico atualizados com sucesso.");
	}

	public static void excluirMedicoTeste() throws SQLException, IOException {

		int crm = 444;

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new MedicoDAO(conn).excluir(crm);

		if (linhasManipuladas > 0) {

			System.out.println("Médico excluído com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) throws ParseException {

		try {
			
			//MedicoDAOTeste.cadastrarMedicoTeste();
			MedicoDAOTeste.buscarTodosMedicosTeste();
			//MedicoDAOTeste.buscarPorCrmTeste();
			//MedicoDAOTeste.buscarPorNomeTeste();
			//MedicoDAOTeste.atualizarMedicoTeste();
			//MedicoDAOTeste.excluirMedicoTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
