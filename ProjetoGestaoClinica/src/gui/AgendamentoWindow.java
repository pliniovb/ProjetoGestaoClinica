package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Agendamento;
import entities.Especialidade;
import entities.Medico;
import entities.Paciente;
import service.AgendamentoService;
import service.MedicoService;
import service.PacienteService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class AgendamentoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataConsulta;
	private JTextField txtHoraConsulta;
	private JComboBox<Medico> cbMedico;
	private JComboBox<Paciente> cbPaciente;
	
	private AgendamentoService agendamentoService;
	private MedicoService medicoService;
	private PacienteService pacienteService;
	private MaskFormatter mascaraData;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendamentoWindow frame = new AgendamentoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AgendamentoWindow() {
			
			this.criarMascaraData();
			this.initComponents();
			
			this.agendamentoService = new AgendamentoService();
			this.medicoService = new MedicoService();
			this.pacienteService = new PacienteService();
			this.buscarMedicos();
			this.buscarPacientes();
			this.limparComponentes();
		}
	
	private void limparComponentes() {

		this.cbMedico.setSelectedIndex(0);
		this.cbPaciente.setSelectedIndex(0);
		this.txtDataConsulta.setText("");
		this.txtHoraConsulta.setText("");
	}
	
	private void agendarConsulta() {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			
			Agendamento agendamento = new Agendamento();
			agendamento.setPaciente((Paciente)this.cbPaciente.getSelectedItem());
			agendamento.setMedico((Medico)this.cbMedico.getSelectedItem());
			agendamento.setDataConsulta(new java.sql.Date(sdf.parse(this.txtDataConsulta.getText()).getTime()));
			agendamento.setHora(txtHoraConsulta.getText());

			

			this.agendamentoService.cadastrar(agendamento);

		} catch (SQLException | IOException | ParseException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo agendamento.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarMedicos() {
		
		try {
			
			List<Medico> medicos = this.medicoService.buscarTodos();
	
			for (Medico medico : medicos) {
	
				this.cbMedico.addItem(medico);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados das especialidades.", "Busca de Especialidades", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarPacientes() {
		
		try {
			
			List<Paciente> pacientes = this.pacienteService.buscarTodos();
	
			for (Paciente paciente : pacientes) {
	
				this.cbPaciente.addItem(paciente);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados das especialidades.", "Busca de Especialidades", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void criarMascaraData() {

		try {

			this.mascaraData = new MaskFormatter("##/##/####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomePaciente = new JLabel("Paciente");
		lblNomePaciente.setBounds(14, 38, 69, 14);
		contentPane.add(lblNomePaciente);
		
		this.cbMedico = new JComboBox<Medico>();
		cbMedico.setBounds(103, 66, 211, 22);
		contentPane.add(cbMedico);
		
		JLabel lblMedico = new JLabel("Medico");
		lblMedico.setBounds(14, 70, 46, 14);
		contentPane.add(lblMedico);
		
		txtDataConsulta = new JFormattedTextField(mascaraData);
		txtDataConsulta.setBounds(103, 102, 211, 20);
		contentPane.add(txtDataConsulta);
		txtDataConsulta.setColumns(10);
		
		JLabel lblDataConsulta = new JLabel("Data da Consulta");
		lblDataConsulta.setBounds(14, 105, 86, 14);
		contentPane.add(lblDataConsulta);
		
		this.cbPaciente = new JComboBox<Paciente>();
		cbPaciente.setBounds(103, 34, 211, 22);
		contentPane.add(cbPaciente);
		
		txtHoraConsulta = new JTextField();
		txtHoraConsulta.setBounds(103, 133, 211, 20);
		contentPane.add(txtHoraConsulta);
		txtHoraConsulta.setColumns(10);
		
		JLabel lblHoraConsulta = new JLabel("Horário");
		lblHoraConsulta.setBounds(14, 136, 46, 14);
		contentPane.add(lblHoraConsulta);
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendarConsulta();
			}
		});
		btnAgendar.setBounds(14, 227, 89, 23);
		contentPane.add(btnAgendar);
		
		JButton btnLimparCampos = new JButton("LimparCampos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();;
			}
		});
		btnLimparCampos.setBounds(195, 176, 119, 23);
		contentPane.add(btnLimparCampos);
	}
}