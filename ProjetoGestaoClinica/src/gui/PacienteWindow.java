package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Paciente;
import service.PacienteService;

import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PacienteWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JMenu menuAjuda;
	private JMenuItem itemSobre;
	private JTextField txtNome;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JComboBox cbFormaPagamento;
	private JComboBox cbUf;
	private JLabel lblNome;
	private JLabel lblDataNascimento;
	private JLabel lblTelefone;
	private JLabel lblFormaPagamento;
	private JLabel lblLogradouro;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JLabel lblUf;
	private JPanel painelSexo;
	private JPanel painelEndereco;
	private JFormattedTextField txtDataNascimento;
	private JFormattedTextField txtTelefone;
	private JSeparator separator;
	private JButton btnCadastrar;
	private JButton btnLimparCampos;
	private JPanel painelPacientes;
	private JTable tblPacientes;
	private JScrollPane scrollPane;
	
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraTelefone;
	private PacienteService pacienteService;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacienteWindow frame = new PacienteWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PacienteWindow() {
		
		this.criarMascaraData();
		this.criarMascaraTelefone();
		this.initComponents();
		
		this.pacienteService = new PacienteService();
		
		this.buscarPacientes();
		this.limparComponentes();
	}
	
	private void limparComponentes() {

		this.txtNome.setText("");
		this.txtDataNascimento.setText("");
		this.txtLogradouro.setText("");
		this.txtBairro.setText("");
		this.txtCidade.setText("");
		this.cbUf.setSelectedIndex(0);
		this.txtNumero.setText("");
		this.txtTelefone.setText("");
		this.cbFormaPagamento.setSelectedIndex(0);
	}
	
	
	private void criarMascaraData() {

		try {

			this.mascaraData = new MaskFormatter("##/##/####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	private void criarMascaraTelefone() {

		try {

			this.mascaraTelefone = new MaskFormatter("(##)#####-####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	private void buscarPacientes() {

		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
	
			List<Paciente> pacientes = this.pacienteService.buscarTodos();
	
			for (Paciente paciente : pacientes) {
	
				modelo.addRow(new Object[] { 
					paciente.getNome(),
					paciente.getSexo(),
					formato.format(paciente.getDataNascimento()),
					paciente.getLogradouro(),
					paciente.getBairro(),
					paciente.getCidade(),
					paciente.getUf(),
					paciente.getNumero(),
					paciente.getTelefone(),
					paciente.getFormaPagamento()
				});
			}
		
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos pacientes.", "Busca de Pacientes", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	private void cadastrarPaciente() {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Paciente paciente = new Paciente();

			paciente.setNome(this.txtNome.getText());
			paciente.setSexo(verificarSelecaoRadioButtonSexo());
			paciente.setDataNascimento(new java.sql.Date(sdf.parse(this.txtDataNascimento.getText()).getTime()));

			paciente.setLogradouro(this.txtLogradouro.getText());
			paciente.setBairro(this.txtBairro.getText());
			paciente.setCidade(this.txtCidade.getText());
			
			paciente.setUf(this.cbUf.getSelectedItem());
			paciente.setNumero(Integer.parseInt(this.txtNumero.getText()));
			paciente.setTelefone(this.txtTelefone.getText());
			paciente.setFormaPagamento(this.cbFormaPagamento.getSelectedItem());

			this.pacienteService.cadastrar(paciente);
			this.buscarPacientes();

		} catch (SQLException | IOException | ParseException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo paciente.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private String verificarSelecaoRadioButtonSexo() {

		if (this.rbMasculino.isSelected()) {
			return this.rbMasculino.getText();
		} else {
			return this.rbFeminino.getText();
		} 
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 768);
		
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
		
		menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);
		
		itemSobre = new JMenuItem("Sobre");
		menuAjuda.add(itemSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(10, 21, 79, 29);
		contentPane.add(lblNome);
		
		lblDataNascimento = new JLabel("Data de nascimento");
		lblDataNascimento.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDataNascimento.setBounds(429, 24, 148, 23);
		contentPane.add(lblDataNascimento);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTelefone.setBounds(318, 377, 63, 19);
		contentPane.add(lblTelefone);
		
		lblFormaPagamento = new JLabel("Forma de pagamento");
		lblFormaPagamento.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFormaPagamento.setBounds(10, 377, 148, 19);
		contentPane.add(lblFormaPagamento);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setBounds(57, 24, 362, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtDataNascimento = new JFormattedTextField(mascaraData);
		txtDataNascimento.setBounds(573, 25, 88, 23);
		contentPane.add(txtDataNascimento);
		
		cbFormaPagamento = new JComboBox();
		cbFormaPagamento.setModel(new DefaultComboBoxModel(new String[] {"", "Dinheiro", "Crédito", "Boleto", "Pix", "Débito"}));
		cbFormaPagamento.setBounds(161, 376, 131, 23);
		contentPane.add(cbFormaPagamento);
		
		painelSexo = new JPanel();
		painelSexo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 160)));
		painelSexo.setBounds(10, 79, 153, 123);
		contentPane.add(painelSexo);
		painelSexo.setLayout(null);
		
		rbMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rbMasculino);
		rbMasculino.setFont(new Font("Arial", Font.PLAIN, 15));
		rbMasculino.setBounds(6, 35, 122, 23);
		painelSexo.add(rbMasculino);
		
		rbFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rbFeminino);
		rbFeminino.setFont(new Font("Arial", Font.PLAIN, 15));
		rbFeminino.setBounds(6, 75, 103, 21);
		painelSexo.add(rbFeminino);
		
		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setBounds(380, 376, 116, 23);
		contentPane.add(txtTelefone);
		
		painelEndereco = new JPanel();
		painelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 160)));
		painelEndereco.setBounds(10, 224, 631, 123);
		contentPane.add(painelEndereco);
		painelEndereco.setLayout(null);
		
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 35, 78, 18);
		painelEndereco.add(lblLogradouro);
		lblLogradouro.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(98, 34, 364, 23);
		painelEndereco.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		lblNumero = new JLabel("Número");
		lblNumero.setBounds(472, 38, 79, 13);
		painelEndereco.add(lblNumero);
		lblNumero.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtNumero = new JTextField();
		txtNumero.setBounds(539, 34, 63, 23);
		painelEndereco.add(txtNumero);
		txtNumero.setColumns(10);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 83, 45, 13);
		painelEndereco.add(lblBairro);
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtBairro = new JTextField();
		txtBairro.setBounds(56, 79, 218, 23);
		painelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(284, 83, 63, 13);
		painelEndereco.add(lblCidade);
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtCidade = new JTextField();
		txtCidade.setBounds(339, 79, 123, 23);
		painelEndereco.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(478, 83, 45, 13);
		painelEndereco.add(lblUf);
		lblUf.setFont(new Font("Arial", Font.PLAIN, 15));
		
		cbUf = new JComboBox();
		cbUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbUf.setToolTipText("");
		cbUf.setBounds(509, 79, 63, 23);
		painelEndereco.add(cbUf);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarPaciente();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCadastrar.setBounds(140, 440, 152, 35);
		contentPane.add(btnCadastrar);
		
		separator = new JSeparator();
		separator.setBounds(10, 419, 667, 14);
		contentPane.add(separator);
		
		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();
			}
		});
		btnLimparCampos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLimparCampos.setBounds(349, 440, 152, 35);
		contentPane.add(btnLimparCampos);
		
		painelPacientes = new JPanel();
		painelPacientes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 160)));
		painelPacientes.setBounds(10, 486, 677, 213);
		contentPane.add(painelPacientes);
		painelPacientes.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 657, 183);
		painelPacientes.add(scrollPane);
		
		tblPacientes = new JTable();
		scrollPane.setViewportView(tblPacientes);
		tblPacientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Sexo", "Data de nascimento", "Logradouro", "Bairro", "Cidade", "UF", "N\u00FAmero", "Telefone", "Forma de pagamento"
			}
		));
	}
}
