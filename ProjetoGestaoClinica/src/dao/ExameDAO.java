package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Exame;

public class ExameDAO {
	
	private Connection conn;

	public ExameDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Exame exame) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into exame (nome, valor, orientacoes) values (?, ?, ?)");

			st.setString(1, exame.getNome());
			st.setDouble(2, exame.getValor());
			st.setString(3, exame.getOrientacoes());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Exame> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from exame order by nome");

			rs = st.executeQuery();

			List<Exame> listaExames = new ArrayList<>();

			while (rs.next()) {

				Exame exame = new Exame();

				exame.setCodigo(rs.getInt("codigo"));
				exame.setNome(rs.getString("nome"));
				exame.setValor(rs.getDouble("valor"));
				exame.setOrientacoes(rs.getString("orientacoes"));

				listaExames.add(exame);
			}

			return listaExames;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}
	
	public Exame buscarPorCodigo(int codigoExame) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from exame where codigo = ?");
			
			st.setInt(1, codigoExame);

			rs = st.executeQuery();

			if (rs.next()) {

				Exame exame = new Exame();

				exame.setCodigo(rs.getInt("codigo"));
				exame.setNome(rs.getString("nome"));
				exame.setValor(rs.getDouble("valor"));
				exame.setOrientacoes(rs.getString("orientacoes"));
				
				return exame;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Exame exame) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update exame set nome = ?, valor = ?, orientacoes = ? where codigo = ?");

			st.setString(1, exame.getNome());
			st.setDouble(2, exame.getValor());
			st.setString(3, exame.getOrientacoes());
			st.setInt(4, exame.getCodigo());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(int codigo) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from exame where codigo = ?");

			st.setInt(1, codigo);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
