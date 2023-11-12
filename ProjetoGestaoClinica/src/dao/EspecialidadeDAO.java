package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Especialidade;


public class EspecialidadeDAO {
	
	private Connection conn;

	public EspecialidadeDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Especialidade especialidade) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into especialidade (nome) values (?)");

			st.setString(1, especialidade.getNome());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Especialidade> buscarTodas() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from especialidade order by nome");

			rs = st.executeQuery();

			List<Especialidade> listaEspecialidades = new ArrayList<>();

			while (rs.next()) {

				Especialidade especialidade = new Especialidade();

				especialidade.setCodigo(rs.getInt("codigo"));
				especialidade.setNome(rs.getString("nome"));

				listaEspecialidades.add(especialidade);
			}

			return listaEspecialidades;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	
	public Especialidade buscarPorCodigo(int codigoEspecialidade) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from especialidade where codigo = ?");
			
			st.setInt(1, codigoEspecialidade);

			rs = st.executeQuery();

			if (rs.next()) {

				Especialidade especialidade = new Especialidade();

				especialidade.setCodigo(rs.getInt("codigo"));
				especialidade.setNome(rs.getString("nome"));
				
				return especialidade;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Especialidade especialidade) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update especialidade set nome = ? where codigo = ?");

			st.setString(1, especialidade.getNome());
			st.setInt(2, especialidade.getCodigo());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(int codigo) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from especialidade where codigo = ?");

			st.setInt(1, codigo);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
