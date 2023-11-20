package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Paciente;

public class PacienteDAO {
	
	private Connection conn;

	public PacienteDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Paciente paciente) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"insert into paciente (nome, sexo, dataNascimento, logradouro, bairro, cidade, uf, numero, telefone, formaPagamento) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, paciente.getNome());
			st.setString(2, paciente.getSexo());
			st.setDate(3, paciente.getDataNascimento());
			st.setString(4, paciente.getLogradouro());
			st.setString(5, paciente.getBairro());
			st.setString(6, paciente.getCidade());
			st.setString(7, paciente.getUf());
			st.setInt(8, paciente.getNumero());
			st.setString(9, paciente.getTelefone());
			st.setString(10, paciente.getFormaPagamento());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Paciente> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from paciente order by nome");

			rs = st.executeQuery();

			List<Paciente> listaPacientes = new ArrayList<>();

			while (rs.next()) {

				Paciente paciente = new Paciente();

				paciente.setNome(rs.getString("nome"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setDataNascimento(rs.getDate("dataNascimento"));
				paciente.setLogradouro(rs.getString("logradouro"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setNumero(rs.getInt("numero"));
				paciente.setTelefone(rs.getString("telefone"));
				paciente.setFormaPagamento(rs.getString("formaPagamento"));

				listaPacientes.add(paciente);
			}

			return listaPacientes;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public Paciente buscarPorNome(String nome) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from paciente where nome = ?");

			st.setString(1, nome);

			rs = st.executeQuery();

			if (rs.next()) {

				Paciente paciente = new Paciente();
				
				paciente.setNome(rs.getString("nome"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setDataNascimento(rs.getDate("dataNascimento"));
				paciente.setLogradouro(rs.getString("logradouro"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setNumero(rs.getInt("numero"));
				paciente.setTelefone(rs.getString("telefone"));
				paciente.setFormaPagamento(rs.getString("formaPagamento"));


				return paciente;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Paciente paciente) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update paciente set sexo = ?, dataNascimento = ?, logradouro = ?, bairro = ?, cidade = ?, uf = ?, numero = ?, telefone = ?, formaPagamento = ? where nome = ?");

			st.setString(1, paciente.getSexo());
			st.setDate(2, paciente.getDataNascimento());
			st.setString(3, paciente.getLogradouro());
			st.setString(4, paciente.getBairro());
			st.setString(5, paciente.getCidade());
			st.setString(6, paciente.getUf());
			st.setInt(7, paciente.getNumero());
			st.setString(8, paciente.getTelefone());
			st.setString(9, paciente.getFormaPagamento());
			st.setString(10, paciente.getNome());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(String nome) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from paciente where nome = ?");

			st.setString(1, nome);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
