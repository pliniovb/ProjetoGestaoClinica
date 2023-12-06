package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.Especialidade;
import service.EspecialidadeService;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class EspecialidadeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JTextField txtNomeEspecialidade;
	private JLabel lblNomeEspecialidade;
	private JButton btnCadastrar;
	private JButton btnLimparCampo;
	private JPanel painelEspecialidades;
	private JTable tblEspecialidades;
	private JScrollPane scrollPane;
	
	private EspecialidadeService especialidadeService;
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
					EspecialidadeWindow frame = new EspecialidadeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EspecialidadeWindow() {
		
		this.initComponents();
		
		this.especialidadeService = new EspecialidadeService();
		
		this.buscarEspecialidades();
		this.limparComponente();
		
		this.principalWindow = principalWindow;
		
	}
	
	private void limparComponente() {

		this.txtNomeEspecialidade.setText("");

	}
	
	private void buscarEspecialidades() {

		try {
			
			
			DefaultTableModel modelo = (DefaultTableModel) tblEspecialidades.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
	
			List<Especialidade> especialidades = this.especialidadeService.buscarTodas();
	
			for (Especialidade especialidade : especialidades) {
	
				modelo.addRow(new Object[] { 
					especialidade.getCodigo(),
					especialidade.getNome()
				});
			}
		
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados das especialidades.", "Busca de Especialidades", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cadastrarEspecialidade() {

		try {

			Especialidade especialidade = new Especialidade();

			especialidade.setNome(this.txtNomeEspecialidade.getText());

			this.especialidadeService.cadastrar(especialidade);
			this.buscarEspecialidades();

		} catch (SQLException | IOException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar uma nova especialidade.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Especialidade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 510);
		
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
		
		txtNomeEspecialidade = new JTextField();
		txtNomeEspecialidade.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNomeEspecialidade.setBounds(175, 30, 203, 23);
		contentPane.add(txtNomeEspecialidade);
		txtNomeEspecialidade.setColumns(10);
		
		lblNomeEspecialidade = new JLabel("Nome da especialidade");
		lblNomeEspecialidade.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomeEspecialidade.setBounds(10, 32, 203, 16);
		contentPane.add(lblNomeEspecialidade);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarEspecialidade();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCadastrar.setBounds(45, 97, 152, 35);
		contentPane.add(btnCadastrar);
		
		painelEspecialidades = new JPanel();
		painelEspecialidades.setBorder(new TitledBorder(null, "Especialidades", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelEspecialidades.setBounds(10, 163, 397, 278);
		contentPane.add(painelEspecialidades);
		painelEspecialidades.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 377, 247);
		painelEspecialidades.add(scrollPane);
		
		tblEspecialidades = new JTable();
		scrollPane.setViewportView(tblEspecialidades);
		tblEspecialidades.setModel(new DefaultTableModel(
			new Object[][] {},new String[] {"C\u00F3digo", "Especialidade"}));
		
		btnLimparCampo = new JButton("Limpar campo");
		btnLimparCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponente();
			}
		});
		btnLimparCampo.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLimparCampo.setBounds(226, 97, 152, 35);
		contentPane.add(btnLimparCampo);
		
	}
}
