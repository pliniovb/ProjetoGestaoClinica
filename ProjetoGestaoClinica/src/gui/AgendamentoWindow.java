package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import entities.Agendamento;
import entities.Medico;
import entities.Paciente;
import service.AgendamentoService;
import service.MedicoService;
import service.PacienteService;

import javax.swing.UIManager;
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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AgendamentoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JLabel lblNomePaciente;
	private JLabel lblMedico;
	private JLabel lblDataConsulta;
	private JLabel lblHoraConsulta;
	private JFormattedTextField txtDataConsulta;
	private JFormattedTextField txtHoraConsulta;
	private JComboBox<Medico> cbMedico;
	private JComboBox<Paciente> cbPaciente;
	private JButton btnAgendar;
	private JButton btnLimparCampos;
	
	private AgendamentoService agendamentoService;
	private MedicoService medicoService;
	private PacienteService pacienteService;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraHora;

	private PrincipalWindow principalWindow;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
			this.criarMascaraHora();
			this.initComponents();
			
			this.agendamentoService = new AgendamentoService();
			this.medicoService = new MedicoService();
			this.pacienteService = new PacienteService();
			
			this.buscarMedicos();
			this.buscarPacientes();
			this.limparComponentes();
			
			this.principalWindow = principalWindow;
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
			agendamento.setHora(this.txtHoraConsulta.getText());

			

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
	
	private void criarMascaraHora() {

		try {

			this.mascaraHora = new MaskFormatter("##:##");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Agendamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 338);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		itemSair = new JMenuItem("Sair");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarAplicacao();
			}
		});
		menuArquivo.add(itemSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNomePaciente = new JLabel("Paciente");
		lblNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomePaciente.setBounds(14, 26, 69, 14);
		contentPane.add(lblNomePaciente);
		
		cbMedico = new JComboBox<Medico>();
		cbMedico.setBounds(93, 66, 211, 23);
		contentPane.add(cbMedico);
		
		lblMedico = new JLabel("Medico");
		lblMedico.setFont(new Font("Arial", Font.PLAIN, 15));
		lblMedico.setBounds(14, 67, 69, 18);
		contentPane.add(lblMedico);
		
		txtDataConsulta = new JFormattedTextField(mascaraData);
		txtDataConsulta.setBounds(147, 104, 157, 23);
		contentPane.add(txtDataConsulta);
		txtDataConsulta.setColumns(10);
		
		lblDataConsulta = new JLabel("Data da Consulta");
		lblDataConsulta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDataConsulta.setBounds(14, 107, 124, 14);
		contentPane.add(lblDataConsulta);
		
		cbPaciente = new JComboBox<Paciente>();
		cbPaciente.setBounds(93, 23, 211, 23);
		contentPane.add(cbPaciente);
		
		lblHoraConsulta = new JLabel("Horário");
		lblHoraConsulta.setFont(new Font("Arial", Font.PLAIN, 15));
		lblHoraConsulta.setBounds(14, 145, 69, 17);
		contentPane.add(lblHoraConsulta);
		
		btnAgendar = new JButton("Agendar");
		btnAgendar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnAgendar.setBounds(14, 207, 152, 35);
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendarConsulta();
			}
		});
		contentPane.add(btnAgendar);
		
		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLimparCampos.setBounds(207, 208, 152, 35);
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();;
			}
		});
		contentPane.add(btnLimparCampos);
		
		txtHoraConsulta = new JFormattedTextField(mascaraHora);
		txtHoraConsulta.setBounds(93, 143, 210, 23);
		contentPane.add(txtHoraConsulta);
	}
}