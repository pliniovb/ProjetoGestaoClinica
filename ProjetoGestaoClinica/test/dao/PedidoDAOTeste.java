package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entities.Exame;
import entities.Medico;
import entities.Paciente;
import entities.Pedido;

public class PedidoDAOTeste {
	
	public static void cadastrarPedidoTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Exame exame = new Exame();
		exame.setNome("Exame de sangue");
		exame.setValor(60);
		exame.setOrientacoes("Fazer jejum");
		
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

		Pedido pedido = new Pedido();
		pedido.setExame(exame);
		pedido.setPaciente(paciente);
		pedido.setMedico(medico);
		pedido.setDataRealizacao(new java.sql.Date(sdf.parse("21/12/2020").getTime()));
		pedido.setValorPago(250);

		Connection conn = BancoDados.conectar();
		new PedidoDAO(conn).cadastrar(pedido);

		System.out.println("Cadastro efetuado com sucesso.");
	}

	public static void buscarTodosPedidosTeste() throws SQLException, IOException {

		Connection conn = BancoDados.conectar();
		List<Pedido> listaPedidos = new PedidoDAO(conn).buscarTodos();

		for (Pedido pedido : listaPedidos) {

			System.out.println(pedido.getExame().getCodigo() + " - " + pedido.getPaciente().getNome() + " - " + pedido.getMedico().getCrm() + " - " + pedido.getDataRealizacao() + " - " + pedido.getValorPago());
		}
	}

	public static void buscarPorCodigoTeste() throws SQLException, IOException {

		int codigoExame = 5;

		Connection conn = BancoDados.conectar();
		Pedido pedido = new PedidoDAO(conn).buscarPorCodigo(codigoExame);

		if (pedido != null) {

			System.out.println(pedido.getExame().getCodigo() + " - " + pedido.getPaciente().getNome() + " - " + pedido.getMedico().getCrm() + " - " + pedido.getDataRealizacao() + " - " + pedido.getValorPago());

		} else {

			System.out.println("Código não encontrado.");
		}
	}

	public static void atualizarPedidoTeste() throws SQLException, IOException, ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Exame exame = new Exame();
		exame.setNome("Exame de sangue");
		exame.setValor(60);
		exame.setOrientacoes("Fazer jejum");
		
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

		Pedido pedido = new Pedido();
		pedido.setExame(exame);
		pedido.setPaciente(paciente);
		pedido.setMedico(medico);
		pedido.setDataRealizacao(new java.sql.Date(sdf.parse("21/12/2020").getTime()));
		pedido.setValorPago(250);


		Connection conn = BancoDados.conectar();
		new PedidoDAO(conn).atualizar(pedido);

		System.out.println("Dados do pedido atualizados com sucesso.");
	}

	public static void excluirPedidoTeste() throws SQLException, IOException {

		int codigoExame = 6;

		Connection conn = BancoDados.conectar();
		int linhasManipuladas = new PedidoDAO(conn).excluir(codigoExame);

		if (linhasManipuladas > 0) {

			System.out.println("Pedido excluído com sucesso.");

		} else {

			System.out.println("Nenhum registro foi encontrado.");
		}

	}

	public static void main(String[] args) {

		try {
			
			//PedidoDAOTeste.cadastrarPedidoTeste();
			//PedidoDAOTeste.buscarPorCodigoTeste();
			PedidoDAOTeste.buscarTodosPedidosTeste();
			//PedidoDAOTeste.atualizarPedidoTeste();
			//PedidoDAOTeste.excluirPedidoTeste();

		} catch (SQLException | IOException e) {

			System.out.println(e.getMessage());
		}
	}

}
