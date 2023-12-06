package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Exame;
import service.ExameService;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ExameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JTextField txtNomeExame;
	private JTextField txtValor;
	private JTextArea txtOrientacoes;
	private JLabel lblNomeExame;
	private JLabel lblValor;
	private JLabel lblOrientacoes;
	private JButton btnCadastrar;
	private JButton btnLimparCampos;
	
	private ExameService exameService;
	private JPanel painelExames;
	private JTable tblExames;
	private JScrollPane scrollPane;
	
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
					ExameWindow frame = new ExameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ExameWindow() {
		
		this.initComponents();
		
		this.exameService = new ExameService();
		
		this.buscarExames();
		this.limparComponentes();
		
		this.principalWindow = principalWindow;
		
	}
	
	private void limparComponentes() {

		this.txtNomeExame.setText("");
		this.txtValor.setText("");
		this.txtOrientacoes.setText("");
	}
	
	private void buscarExames() {
		try {
			
			DefaultTableModel modelo = (DefaultTableModel) tblExames.getModel();
			modelo.fireTableDataChanged();
			modelo.setRowCount(0);
	
			List<Exame> exames = this.exameService.buscarTodos();
	
			for (Exame exame : exames) {
	
				modelo.addRow(new Object[] { 
					exame.getCodigo(),
					exame.getNome(),
					exame.getValor(),
					exame.getOrientacoes()
				});
			}
		
		} catch (SQLException | IOException e) {

			JOptionPane.showMessageDialog(null, "Erro ao carregar os dados dos exames.", "Busca de Exames", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cadastrarExame() {

		try {

			Exame exame = new Exame();

			exame.setNome(this.txtNomeExame.getText());
			exame.setValor(Double.parseDouble(this.txtValor.getText()));
			exame.setOrientacoes(this.txtOrientacoes.getText());

			this.exameService.cadastrar(exame);
			this.buscarExames();

		} catch (SQLException | IOException | NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Erro ao cadastrar um novo exame.", "Cadastro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Exame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 685);
		
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
		
		lblNomeExame = new JLabel("Nome do exame");
		lblNomeExame.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNomeExame.setBounds(24, 26, 112, 19);
		contentPane.add(lblNomeExame);
		
		lblValor = new JLabel("Valor do exame");
		lblValor.setFont(new Font("Arial", Font.PLAIN, 15));
		lblValor.setBounds(24, 64, 112, 19);
		contentPane.add(lblValor);
		
		lblOrientacoes = new JLabel("Orientações");
		lblOrientacoes.setFont(new Font("Arial", Font.PLAIN, 15));
		lblOrientacoes.setBounds(24, 93, 96, 24);
		contentPane.add(lblOrientacoes);
		
		txtOrientacoes = new JTextArea();
		txtOrientacoes.setFont(new Font("Arial", Font.PLAIN, 13));
		txtOrientacoes.setBounds(24, 122, 350, 131);
		contentPane.add(txtOrientacoes);
		
		txtNomeExame = new JTextField();
		txtNomeExame.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNomeExame.setBounds(142, 25, 232, 23);
		contentPane.add(txtNomeExame);
		txtNomeExame.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Arial", Font.PLAIN, 12));
		txtValor.setBounds(135, 63, 96, 23);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarExame();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCadastrar.setBounds(35, 279, 152, 35);
		contentPane.add(btnCadastrar);
		
		btnLimparCampos = new JButton("Limpar campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparComponentes();
			}
		});
		btnLimparCampos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLimparCampos.setBounds(214, 279, 152, 35);
		contentPane.add(btnLimparCampos);
		
		painelExames = new JPanel();
		painelExames.setBorder(new TitledBorder(null, "Exames", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelExames.setBounds(24, 346, 422, 270);
		contentPane.add(painelExames);
		painelExames.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 402, 239);
		painelExames.add(scrollPane);
		
		tblExames = new JTable();
		scrollPane.setViewportView(tblExames);
		tblExames.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome do exame", "Valor do exame", "Observa\u00E7\u00F5es"
			}
		));
		
	}
}
