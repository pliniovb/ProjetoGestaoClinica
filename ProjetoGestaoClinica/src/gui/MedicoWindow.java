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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Especialidade;
import entities.Medico;
import service.EspecialidadeService;
import service.MedicoService;

import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;

public class MedicoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JMenu menuAjuda;
	private JMenuItem itemSobre;
	private JTextField txtCrm;
	private JTextField txtNome;
	private JTextField txtLogradouro;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JFormattedTextField txtTelefone;
	private JLabel lblCrm;
	private JLabel lblNome;
	private JLabel lblLogradouro;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblNumero;
	private JLabel lblUf;
	private JLabel lblTelefone;
	private JLabel lblEspecialidade;
	private JComboBox<Especialidade> cbEspecialidade;
	private JComboBox cbUf;
	private JPanel painelEndereco;
	private JButton btnCadastrar;
	private JButton btnLimparCampos;
	private JSeparator separator;
	private JPanel painelMedicos;
	private JTable tblMedicos;
	private JScrollPane scrollPane;
	
	private MaskFormatter mascaraTelefone;
	private MedicoService medicoService;
	private EspecialidadeService especialidadeService;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicoWindow frame = new MedicoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MedicoWindow() {
		
		this.criarMascaraTelefone();
		this.initComponents();
		
		this.medicoService = new MedicoService();
		this.especialidadeService = new EspecialidadeService();
		
		this.buscarEspecialidades();
		this.buscarMedicos();
		this.limparComponentes();
		
	}
	
	private void limparComponentes() {

		this.txtCrm.setText("");
		this.txtNome.setText("");
		this.txtLogradouro.setText("");
		this.txtBairro.setText("");
		this.txtCidade.setText("");
		this.cbUf.setSelectedIndex(0);
		this.txtNumero.setText("");
		this.txtTelefone.setText("");
		this.cbEspecialidade.setSelectedIndex(0);
	}
	
	private void criarMascaraTelefone() {

		try {

			this.mascaraTelefone = new MaskFormatter("(##)#####-####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void buscarMedicos() {
		
			try {
			
			DefaultTableModel modelo = (DefaultTableModel) tblMedicos.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
	
			List<Medico> medicos = this.medicoService.buscarTodos();
	
			for (Medico medico : medicos) {
	
				modelo.addRow(new Object[] { 
					medico.getCrm(), 
					medico.getNome(), 
					medico.getLogradouro(),
					medico.getBairro(),
					medico.getCidade(),
					medico.getUf(),
					medico.getNumero(),
					medico.getTelefone(),
					medico.getEspecialidade().getCodigo()
				});
			}
		
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos médicos.", "Busca de Médicos", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void buscarEspecialidades() {
		
		try {
			
			List<Especialidade> especialidades = this.especialidadeService.buscarTodas();
	
			for (Especialidade especialidade : especialidades) {
	
				this.cbEspecialidade.addItem(especialidade);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados das especialidades.", "Busca de Especialidades", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cadastrarMedico() {
		
		try {

			Medico medico = new Medico();
			
			medico.setCrm(Integer.parseInt(this.txtCrm.getText()));
			medico.setNome(this.txtNome.getText());
			medico.setLogradouro(this.txtLogradouro.getText());
			medico.setBairro(this.txtBairro.getText());
			medico.setCidade(this.txtCidade.getText());
			medico.setUf(this.cbUf.getSelectedItem());
			medico.setNumero(Integer.parseInt(this.txtNumero.getText()));
			medico.setTelefone(this.txtTelefone.getText());
			medico.setEspecialidade((Especialidade) this.cbEspecialidade.getSelectedItem());

			this.medicoService.cadastrar(medico);
			this.buscarMedicos();

		} catch (SQLException | IOException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo médico.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Médico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 769);
		
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
		
		lblCrm = new JLabel("CRM");
		lblCrm.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCrm.setBounds(25, 28, 45, 23);
		contentPane.add(lblCrm);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNome.setBounds(25, 72, 45, 23);
		contentPane.add(lblNome);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTelefone.setBounds(25, 272, 67, 23);
		contentPane.add(lblTelefone);
		
		lblEspecialidade = new JLabel("Especialidade");
		lblEspecialidade.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEspecialidade.setBounds(352, 275, 102, 17);
		contentPane.add(lblEspecialidade);
		
		txtCrm = new JTextField();
		txtCrm.setBounds(65, 29, 510, 23);
		contentPane.add(txtCrm);
		txtCrm.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(71, 73, 504, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		painelEndereco = new JPanel();
		painelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 160)));
		painelEndereco.setBounds(25, 129, 631, 123);
		contentPane.add(painelEndereco);
		painelEndereco.setLayout(null);
		
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 34, 84, 23);
		painelEndereco.add(lblLogradouro);
		lblLogradouro.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 72, 45, 23);
		painelEndereco.add(lblBairro);
		lblBairro.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(97, 35, 356, 23);
		painelEndereco.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(61, 73, 199, 23);
		painelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(270, 72, 55, 23);
		painelEndereco.add(lblCidade);
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtCidade = new JTextField();
		txtCidade.setBounds(323, 73, 130, 23);
		painelEndereco.add(txtCidade);
		txtCidade.setColumns(10);
		
		lblNumero = new JLabel("Número");
		lblNumero.setBounds(463, 34, 67, 23);
		painelEndereco.add(lblNumero);
		lblNumero.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtNumero = new JTextField();
		txtNumero.setBounds(525, 35, 75, 23);
		painelEndereco.add(txtNumero);
		txtNumero.setColumns(10);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(463, 72, 45, 23);
		painelEndereco.add(lblUf);
		lblUf.setFont(new Font("Arial", Font.PLAIN, 15));
		
		cbUf = new JComboBox();
		cbUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbUf.setBounds(496, 73, 67, 23);
		painelEndereco.add(cbUf);
		
		cbEspecialidade = new JComboBox<Especialidade>();
		cbEspecialidade.setBounds(452, 273, 183, 23);
		contentPane.add(cbEspecialidade);
		
		separator = new JSeparator();
		separator.setBounds(25, 320, 631, 2);
		contentPane.add(separator);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarMedico();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCadastrar.setBounds(121, 332, 152, 35);
		contentPane.add(btnCadastrar);
		
		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();
			}
		});
		btnLimparCampos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLimparCampos.setBounds(376, 332, 152, 35);
		contentPane.add(btnLimparCampos);
		
		painelMedicos = new JPanel();
		painelMedicos.setBorder(new TitledBorder(null, "M\u00E9dicos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelMedicos.setBounds(25, 377, 649, 323);
		contentPane.add(painelMedicos);
		painelMedicos.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 629, 293);
		painelMedicos.add(scrollPane);
		
		tblMedicos = new JTable();
		scrollPane.setViewportView(tblMedicos);
		tblMedicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CRM", "Nome", "Logradouro", "Bairro", "Cidade", "UF", "N\u00FAmero", "Telefone", "Especialidade"
			}
		));
		
		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setBounds(87, 273, 160, 23);
		contentPane.add(txtTelefone);
	}
}
