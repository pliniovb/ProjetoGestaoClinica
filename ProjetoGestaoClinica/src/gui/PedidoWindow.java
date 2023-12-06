package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import entities.Exame;
import entities.Medico;
import entities.Paciente;
import entities.Pedido;
import service.ExameService;
import service.MedicoService;
import service.PacienteService;
import service.PedidoService;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class PedidoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JMenu menuAjuda;
	private JMenuItem itemSobre;
	private JLabel lblCodigoExame;
	private JLabel lblNomePaciente;
	private JLabel lblCrmMedico;
	private JLabel lblDataRealizacao;
	private JLabel lblValorPago;
	private JComboBox<Exame> cbExames;
	private JComboBox<Paciente> cbPacientes;
	private JComboBox<Medico> cbMedicos;
	private JTextField txtValorPago;
	private JFormattedTextField txtDataRealizacao;
	private JButton btnCadastrar;
	private JButton btnLimparCampos;
	private JSeparator separator;
	private JPanel painelPedidos;
	private JTable tblPedidos;
	private JScrollPane scrollPane;
	
	private MaskFormatter mascaraData;
	private ExameService exameService;
	private MedicoService medicoService;
	private PacienteService pacienteService;
	private PedidoService pedidoService;
	

	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PedidoWindow frame = new PedidoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public PedidoWindow() {
		
		this.criarMascaraData();
		this.initComponents();
		
		this.pedidoService = new PedidoService();
		this.exameService = new ExameService();
		this.pacienteService = new PacienteService();
		this.medicoService = new MedicoService();
		
		this.buscarExames();
		this.buscarPacientes();
		this.buscarMedicos();
		this.buscarPedidos();
		this.limparComponentes();
		
	}
	
	private void limparComponentes() {

		this.cbExames.setSelectedIndex(0);
		this.cbPacientes.setSelectedIndex(0);
		this.cbMedicos.setSelectedIndex(0);
		this.txtValorPago.setText("");
		this.txtDataRealizacao.setText("");
		
	}
	
	
	private void criarMascaraData() {

		try {

			this.mascaraData = new MaskFormatter("##/##/####");

		} catch (ParseException e) {

			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public void buscarPedidos() {
		
		try {
		
		DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);

		List<Pedido> pedidos = this.pedidoService.buscarTodos();

		for (Pedido pedido : pedidos) {

			modelo.addRow(new Object[] { 
				pedido.getExame().getCodigo(),
				pedido.getPaciente().getNome(),
				pedido.getMedico().getCrm(),
				pedido.getDataRealizacao(),
				pedido.getValorPago()
			});
		}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos pedidos.", "Busca de Pedidos", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public void buscarExames() {
		
		try {
			
			List<Exame> exames = this.exameService.buscarTodos();
	
			for (Exame exame : exames) {
	
				this.cbExames.addItem(exame);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados dos exames.", "Busca de Exames", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarPacientes() {
		
		try {
			
			List<Paciente> pacientes = this.pacienteService.buscarTodos();
	
			for (Paciente paciente : pacientes) {
	
				this.cbPacientes.addItem(paciente);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados dos pacientes.", "Busca de Pacientes", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buscarMedicos() {
		
		try {
			
			List<Medico> medicos = this.medicoService.buscarTodos();
	
			for (Medico medico : medicos) {
	
				this.cbMedicos.addItem(medico);
			}
	
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao buscar os dados dos médicos.", "Busca de Médicos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cadastrarPedido() {
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Pedido pedido = new Pedido();
			
			pedido.setExame((Exame) this.cbExames.getSelectedItem());
			pedido.setPaciente((Paciente) this.cbPacientes.getSelectedItem());
			pedido.setMedico((Medico) this.cbMedicos.getSelectedItem());
			pedido.setDataRealizacao(new java.sql.Date(sdf.parse(this.txtDataRealizacao.getText()).getTime()));
			pedido.setValorPago(Double.parseDouble(this.txtValorPago.getText()));

			this.pedidoService.cadastrar(pedido);
			this.buscarPedidos();

		} catch (SQLException | IOException | ParseException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo pedido.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 607);
		
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
		
		lblCodigoExame = new JLabel("Código do exame");
		lblCodigoExame.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCodigoExame.setBounds(10, 21, 127, 18);
		contentPane.add(lblCodigoExame);
		
		lblNomePaciente = new JLabel("Nome do paciente");
		lblNomePaciente.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomePaciente.setBounds(10, 132, 127, 24);
		contentPane.add(lblNomePaciente);
		
		lblCrmMedico = new JLabel("CRM do médico");
		lblCrmMedico.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCrmMedico.setBounds(10, 73, 117, 30);
		contentPane.add(lblCrmMedico);
		
		lblDataRealizacao = new JLabel("Data de realização");
		lblDataRealizacao.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDataRealizacao.setBounds(367, 76, 144, 24);
		contentPane.add(lblDataRealizacao);
		
		lblValorPago = new JLabel("Valor pago");
		lblValorPago.setFont(new Font("Arial", Font.PLAIN, 15));
		lblValorPago.setBounds(367, 18, 116, 24);
		contentPane.add(lblValorPago);
		
		cbPacientes = new JComboBox<Paciente>();
		cbPacientes.setBounds(139, 134, 242, 23);
		contentPane.add(cbPacientes);
		
		cbMedicos = new JComboBox<Medico>();
		cbMedicos.setBounds(137, 79, 184, 23);
		contentPane.add(cbMedicos);
		
		cbExames = new JComboBox<Exame>();
		cbExames.setBounds(139, 21, 182, 23);
		contentPane.add(cbExames);
		
		txtValorPago = new JTextField();
		txtValorPago.setBounds(447, 20, 145, 23);
		contentPane.add(txtValorPago);
		txtValorPago.setColumns(10);
		
		txtDataRealizacao = new JFormattedTextField(mascaraData);
		txtDataRealizacao.setBounds(500, 78, 92, 23);
		contentPane.add(txtDataRealizacao);
		
		separator = new JSeparator();
		separator.setBounds(10, 190, 629, 2);
		contentPane.add(separator);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarPedido();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCadastrar.setBounds(119, 218, 152, 35);
		contentPane.add(btnCadastrar);

		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();
			}
		});
		btnLimparCampos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLimparCampos.setBounds(350, 218, 152, 35);
		contentPane.add(btnLimparCampos);
		
		painelPedidos = new JPanel();
		painelPedidos.setBorder(new TitledBorder(null, "Pedidos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelPedidos.setBounds(10, 283, 631, 255);
		contentPane.add(painelPedidos);
		painelPedidos.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 17, 611, 228);
		painelPedidos.add(scrollPane);
		
		tblPedidos = new JTable();
		scrollPane.setViewportView(tblPedidos);
		tblPedidos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo do exame", "Nome do paciente", "CRM do m\u00E9dico", "Data de realiza\u00E7\u00E3o", "Valor pago"
			}
		));
	}
}
