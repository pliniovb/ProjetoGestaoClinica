package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agendamento;

public class AgendamentoDAO {
	
	private Connection conn;

	public AgendamentoDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Agendamento agendamento) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into agendamento (nome_paciente, nome_medico, data_consulta, horario_consulta) values (?, ?, ?, ?)");

			st.setString(1, agendamento.getPaciente().getNome());
			st.setString(2, agendamento.getMedico().getNome());
			st.setDate(3, agendamento.getDataConsulta());
			st.setString(4, agendamento.getHora());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public List<Agendamento> buscarTodos() throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from agendamento order by nome_paciente");

			rs = st.executeQuery();

			List<Agendamento> listaAgendamentos = new ArrayList<>();

			while (rs.next()) {

				Agendamento agendamento = new Agendamento();
				
				agendamento.getPaciente().setNome(rs.getString("nome_paciente"));
				agendamento.getMedico().setNome(rs.getString("nome_medico"));
				agendamento.setDataConsulta(rs.getDate("data_consulta"));
				agendamento.setHora(rs.getString("horario_consulta"));

				listaAgendamentos.add(agendamento);
			}

			return listaAgendamentos;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public Agendamento buscarPorNome(String nomePaciente) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("select * from agendamento where nome_paciente = ?");

			st.setString(1, nomePaciente);

			rs = st.executeQuery();

			if (rs.next()) {

				Agendamento agendamento = new Agendamento();
				
				agendamento.getPaciente().setNome(rs.getString("nome_paciente"));
				agendamento.getMedico().setNome(rs.getString("nome_medico"));
				agendamento.setDataConsulta(rs.getDate("data_consulta"));
				agendamento.setHora(rs.getString("horario_consulta"));

				return agendamento;
			}

			return null;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
			BancoDados.desconectar();
		}
	}

	public void atualizar(Agendamento agendamento) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("update aluno set nome_medico = ?, data_consulta = ?, horario_consulta = ? where nome_paciente = ?");

			st.setString(1, agendamento.getMedico().getNome());
			st.setDate(2, agendamento.getDataConsulta());
			st.setString(3, agendamento.getHora());
			st.setString(4, agendamento.getPaciente().getNome());

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

	public int excluir(String nomePaciente) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("delete from agendamento where nome_paciente = ?");

			st.setString(1, nomePaciente);

			int linhasManipuladas = st.executeUpdate();
			
			return linhasManipuladas;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}

}
