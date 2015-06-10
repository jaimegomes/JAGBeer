package br.senai.sc.jagbeer.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Bazzi
 *
 */
public class PrincipalUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		setTitle("JAGBeer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 720, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);

		JMenuItem mntmCadastro = new JMenuItem("Cadastro Cliente");
		mntmCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteUI cadClienteUI = new CadastroClienteUI();
				cadClienteUI.requestFocus(true);
				cadClienteUI.setFocusable(true);
				cadClienteUI.moveToFront();
				getContentPane().add(cadClienteUI, 0);
				cadClienteUI.setVisible(true);
			}
		});
		mnCliente.add(mntmCadastro);

		JMenuItem mntmConsulta = new JMenuItem("Consulta Cliente");
		mnCliente.add(mntmConsulta);

		JMenu mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);

		JMenuItem mntmCadastroProduto = new JMenuItem("Cadastro Produto");
		mnProduto.add(mntmCadastroProduto);

		JMenuItem mntmConsultaProduto = new JMenuItem("Consulta Produto");
		mnProduto.add(mntmConsultaProduto);

		JMenu mnMesa = new JMenu("Mesa");
		menuBar.add(mnMesa);

		JMenuItem mntmCadastroMesa = new JMenuItem("Cadastro Mesa");
		mnMesa.add(mntmCadastroMesa);

		JMenuItem mntmConsultaMesa = new JMenuItem("Consulta Mesa");
		mnMesa.add(mntmConsultaMesa);

		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);

		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Cliente:");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pedidos", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblPedido = new JLabel("Pedido:");

		JButton btnPesquisar = new JButton("Pesquisar");

		JButton btnAlterar = new JButton("Editar Pedido");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton btnNovo = new JButton("Novo Pedido");

		JButton btnEncerrar = new JButton("Encerrar Pedido");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				524,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								btnNovo,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnEncerrar,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnAlterar,
																								GroupLayout.PREFERRED_SIZE,
																								133,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblPedido,
																				GroupLayout.PREFERRED_SIZE,
																				45,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textField_1,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				lblNome)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textField,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnPesquisar)))
										.addContainerGap(99, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(64)
																		.addComponent(
																				btnAlterar,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnNovo,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				358,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnEncerrar,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblNome)
																						.addComponent(
																								textField,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnPesquisar)
																						.addComponent(
																								lblPedido)
																						.addComponent(
																								textField_1,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addComponent(
																				panel,
																				GroupLayout.DEFAULT_SIZE,
																				481,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								514, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								448, Short.MAX_VALUE).addContainerGap()));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"Pedido", "Nome", "Valor Parcial" }));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
