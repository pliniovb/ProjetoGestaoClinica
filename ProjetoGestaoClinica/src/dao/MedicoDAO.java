package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Medico;

public class MedicoDAO {
	
	private Connection conn;

	public MedicoDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Medico medico) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into medico (crm, nome, logradouro, bairro, cidade, uf, numero, telefone, codigo_especialidade) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			st.setInt(1, medico.getCrm());
			st.setString(2, medico.getNome());
			st.setString(3, medico.getLogradouro());
			st.setString(4, medico.getBairro());
			st.setString(5, medico.getCidade());
			st.setString(6, medico.getUf());
			st.setInt(7, medico.getNumero());
			st.setString(8, medico.getTelefone());
			st.setInt(9, medico.getEspecialidade().getCodigo());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Medico> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from medico order by nome");

			rs = st.executeQuery();

			List<Medico> listaMedicos = new ArrayList<>();

			while (rs.next()) {

				Medico medico = new Medico();

				medico.setCrm(rs.getInt("crm"));
				medico.setNome(rs.getString("nome"));
				medico.setLogradouro(rs.getString("logradouro"));
				medico.setBairro(rs.getString("bairro"));
				medico.setCidade(rs.getString("cidade"));
				medico.setUf(rs.getString("uf"));
				medico.setNumero(rs.getInt("numero"));
				medico.setTelefone(rs.getString("telefone"));
				medico.getEspecialidade().setCodigo(rs.getInt("codigo_especialidade"));

				listaMedicos.add(medico);
			}

			return listaMedicos;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public Medico buscarPorCRM(int crm) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from medico where crm = ?");

			st.setInt(1, crm);

			rs = st.executeQuery();

			if (rs.next()) {

				Medico medico = new Medico();
				
				medico.setCrm(rs.getInt("crm"));
				medico.setNome(rs.getString("nome"));
				medico.setLogradouro(rs.getString("logradouro"));
				medico.setBairro(rs.getString("bairro"));
				medico.setCidade(rs.getString("cidade"));
				medico.setUf(rs.getString("uf"));
				medico.setNumero(rs.getInt("numero"));
				medico.setTelefone(rs.getString("telefone"));
				medico.getEspecialidade().setCodigo(rs.getInt("codigo_especialidade"));

				return medico;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Medico medico) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update medico set nome = ?, logradouro = ?, bairro = ?, cidade = ?, uf = ?, numero = ?, telefone = ?, codigo_especialidade = ? where crm = ?");

			st.setString(1, medico.getNome());
			st.setString(2, medico.getLogradouro());
			st.setString(3, medico.getBairro());
			st.setString(4, medico.getCidade());
			st.setString(5, medico.getUf());
			st.setInt(6, medico.getNumero());
			st.setString(7, medico.getTelefone());
			st.setInt(8, medico.getEspecialidade().getCodigo());
			st.setInt(9, medico.getCrm());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(int crm) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from medico where crm = ?");

			st.setInt(1, crm);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
