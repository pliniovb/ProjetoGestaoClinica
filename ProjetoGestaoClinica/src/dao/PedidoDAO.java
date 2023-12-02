package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Pedido;

public class PedidoDAO {
	
	private Connection conn;

	public PedidoDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Pedido pedido) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into pedidos (codigo_exame, nome_paciente, crm_medico, data_realizacao, valor_pago) values (?, ?, ?, ?, ?)");

			st.setInt(1, pedido.getExame().getCodigo());
			st.setString(2, pedido.getPaciente().getNome());
			st.setInt(3, pedido.getMedico().getCrm());
			st.setDate(4, pedido.getDataRealizacao());
			st.setDouble(5, pedido.getValorPago());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Pedido> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from pedidos order by nome");

			rs = st.executeQuery();

			List<Pedido> listaPedidos = new ArrayList<>();

			while (rs.next()) {

				Pedido pedido = new Pedido();
				
				pedido.getExame().setCodigo(rs.getInt("codigo_exame"));
				pedido.getPaciente().setNome(rs.getString("nome_paciente"));
				pedido.getMedico().setCrm(rs.getInt("crm_medico"));
				pedido.setDataRealizacao(rs.getDate("data_realizacao"));
				pedido.setValorPago(rs.getDouble("valor_pago"));

				listaPedidos.add(pedido);
			}

			return listaPedidos;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public Pedido buscarPorCodigo(int codigoExame) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from pedidos where codigo_exame = ?");

			st.setInt(1, codigoExame);

			rs = st.executeQuery();

			if (rs.next()) {

				Pedido pedido = new Pedido();
				
				pedido.getExame().setCodigo(rs.getInt("codigo_exame"));
				pedido.getPaciente().setNome(rs.getString("nome_paciente"));
				pedido.getMedico().setCrm(rs.getInt("crm_medico"));
				pedido.setDataRealizacao(rs.getDate("data_realizacao"));
				pedido.setValorPago(rs.getDouble("valor_pago"));

				return pedido;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Pedido pedido) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update pedidos set nome_paciente = ?, crm_medico = ?, data_realizacao = ?, valor_pago = ? where codigo_exame = ?");

			st.setString(1, pedido.getPaciente().getNome());
			st.setInt(2, pedido.getMedico().getCrm());
			st.setDate(3, pedido.getDataRealizacao());
			st.setDouble(4, pedido.getValorPago());
			st.setInt(5, pedido.getExame().getCodigo());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(int codigoExame) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from pedidos where codigo_exame = ?");

			st.setInt(1, codigoExame);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
