package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entities.Agendamento;
import entities.Medico;
import entities.Paciente;

public class AgendamentoDAOTeste {
	
	public static void cadastrarAgendamentoTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Paciente paciente = new Paciente();
		paciente.setNome("Isabella Novaes");
		paciente.setSexo("Feminino");
		paciente.setDataNascimento(new java.sql.Date(sdf.parse("25/06/2003").getTime()));
		paciente.setLogradouro("Rua Coronel Dulcídio");
		paciente.setBairro("Centro");
		paciente.setCidade("Ponta Grossa");
		paciente.setUf("PR");
		paciente.setNumero(1602);
		paciente.setTelefone("(11)95329-5838");
		paciente.setFormaPagamento("Débito");
		
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

		Agendamento agendamento = new Agendamento();
		agendamento.setPaciente(paciente);
		agendamento.setMedico(medico);
		agendamento.setDataConsulta(new java.sql.Date(sdf.parse("22/06/2022").getTime()));
		agendamento.setHora("12:00");

		Connection conn = BancoDados.conectar();
		new AgendamentoDAO(conn).cadastrar(agendamento);

		System.out.println("Cadastro efetuado com sucesso.");
	}

	public static void buscarTodosAgendamentosTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Agendamento> listaAgendamentos = new AgendamentoDAO(conn).buscarTodos();

		for (Agendamento agendamento : listaAgendamentos) {

			System.out.println(agendamento.getPaciente().getNome() + " - " + agendamento.getMedico().getNome() + " - " + agendamento.getDataConsulta() + " - " + agendamento.getHora());
		}
	}

	public static void buscarPorNomeTeste() throws SQLException, IOException {

		String nomePaciente = "Paula";

		Connection conn = BancoDados.conectar();
		Agendamento agendamento = new AgendamentoDAO(conn).buscarPorNome(nomePaciente);

		if (agendamento != null) {

			System.out.println(agendamento.getPaciente().getNome() + " - " + agendamento.getMedico().getNome() + " - " + agendamento.getDataConsulta() + " - " + agendamento.getHora());

		} else {

			System.out.println("Nome não encontrado.");
		}
	}

	public static void atualizarAgendamentoTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Paciente paciente = new Paciente();
		paciente.setNome("Isabella Novaes");
		paciente.setSexo("Feminino");
		paciente.setDataNascimento(new java.sql.Date(sdf.parse("25/06/2003").getTime()));
		paciente.setLogradouro("Rua Coronel Dulcídio");
		paciente.setBairro("Centro");
		paciente.setCidade("Ponta Grossa");
		paciente.setUf("PR");
		paciente.setNumero(1602);
		paciente.setTelefone("(11)95329-5838");
		paciente.setFormaPagamento("Débito");
		
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

		Agendamento agendamento = new Agendamento();
		agendamento.setPaciente(paciente);
		agendamento.setMedico(medico);
		agendamento.setDataConsulta(new java.sql.Date(sdf.parse("22/06/2022").getTime()));
		agendamento.setHora("12:00");

		Connection conn = BancoDados.conectar();
		new AgendamentoDAO(conn).atualizar(agendamento);

		System.out.println("Dados do agendamento atualizados com sucesso.");
	}

	public static void excluirAgendamentoTeste() throws SQLException, IOException {

		String nomePaciente = "João";

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new AgendamentoDAO(conn).excluir(nomePaciente);

		if (linhasManipuladas > 0) {

			System.out.println("Agendamento excluído com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) {

		try {
			
			//AgendamentoDAOTeste.cadastrarAgendamentoTeste();
			//AgendamentoDAOTeste.buscarPorNomeTeste();
			AgendamentoDAOTeste.buscarTodosAgendamentosTeste();
			//AgendamentoDAOTeste.atualizarAgendamentoTeste();
			//AgendamentoDAOTeste.excluirAgendamentoTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
