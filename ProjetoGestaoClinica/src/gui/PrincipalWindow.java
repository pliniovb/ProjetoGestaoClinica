package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PrincipalWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JMenuBar menuBar;
	private JMenu menuArquivo;
	private JMenuItem itemSair;
	private JButton btnPacientes;
	private JButton btnMedicos;
	private JButton btnExames;
	private JButton btnEspecialidades;
	private JButton btnPedidos;
	private JButton btnAgendamentos;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalWindow frame = new PrincipalWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalWindow() {
		
		this.initComponents();
	}
	
	private void abrirJanelaPaciente() {

		PacienteWindow janelaPaciente = new PacienteWindow();
		janelaPaciente.setVisible(true);

		this.setVisible(false);
	}
	
	private void abrirJanelaMedico() {

		MedicoWindow janelaMedico = new MedicoWindow();
		janelaMedico.setVisible(true);

		this.setVisible(false);
	}
	
	private void abrirJanelaExame() {

		ExameWindow janelaExame = new ExameWindow();
		janelaExame.setVisible(true);

		this.setVisible(false);
	}
	
	private void abrirJanelaEspecialidade() {

		EspecialidadeWindow janelaEspecialidade = new EspecialidadeWindow();
		janelaEspecialidade.setVisible(true);

		this.setVisible(false);
	}
	
	private void abrirJanelaPedido() {

		PedidoWindow janelaPedido = new PedidoWindow();
		janelaPedido.setVisible(true);

		this.setVisible(false);
	}
	
	private void abrirJanelaAgendamento() {

		AgendamentoWindow janelaAgendamento = new AgendamentoWindow();
		janelaAgendamento.setVisible(true);

		this.setVisible(false);
	}
	
	private void finalizarAplicacao() {

		System.exit(0);
	}
	
	public void initComponents() {
		
		setTitle("Gestão Clínica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 754);
		
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
		
		lblTitulo = new JLabel("Gestão Clínica");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 27));
		lblTitulo.setBounds(109, 10, 202, 76);
		contentPane.add(lblTitulo);
		
		btnPacientes = new JButton("Cadastrar pacientes");
		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaPaciente();
			}
		});
		btnPacientes.setFont(new Font("Arial", Font.PLAIN, 17));
		btnPacientes.setBounds(99, 91, 230, 47);
		contentPane.add(btnPacientes);
		
		btnMedicos = new JButton("Cadastrar médicos");
		btnMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaMedico();
			}
		});
		btnMedicos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnMedicos.setBounds(99, 164, 230, 47);
		contentPane.add(btnMedicos);
		
		btnExames = new JButton("Cadastrar exames");
		btnExames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaExame();
			}
		});
		btnExames.setFont(new Font("Arial", Font.PLAIN, 17));
		btnExames.setBounds(99, 237, 230, 47);
		contentPane.add(btnExames);
		
		btnEspecialidades = new JButton("Cadastrar especialidades");
		btnEspecialidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaEspecialidade();
			}
		});
		btnEspecialidades.setFont(new Font("Arial", Font.PLAIN, 17));
		btnEspecialidades.setBounds(99, 303, 230, 47);
		contentPane.add(btnEspecialidades);
		
		btnPedidos = new JButton("Agendar pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaPedido();
			}
		});
		btnPedidos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnPedidos.setBounds(99, 384, 230, 47);
		contentPane.add(btnPedidos);
		
		btnAgendamentos = new JButton("Agendar consultas");
		btnAgendamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirJanelaAgendamento();
			}
		});
		btnAgendamentos.setFont(new Font("Arial", Font.PLAIN, 17));
		btnAgendamentos.setBounds(99, 465, 230, 47);
		contentPane.add(btnAgendamentos);
	}
}
