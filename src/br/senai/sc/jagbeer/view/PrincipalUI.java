package br.senai.sc.jagbeer.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PedidoAberto;
import br.senai.sc.jagbeer.model.PedidoAbertoTableModel;

/**
 * 
 * @author Bazzi
 *
 */
public class PrincipalUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtfPedido;
	private JTextField jtfCliente;
	private JTable tablePedidoAberto;

	private List<Entidade> listPedidoAberto = new ArrayList<Entidade>();

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
		setBounds(100, 100, 900, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);

		JMenuItem mntmCadastroCliente = new JMenuItem("Cadastro Cliente");
		mntmCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteUI cadClienteUI = new CadastroClienteUI(null,
						null);
				cadClienteUI.requestFocus(true);
				cadClienteUI.setFocusable(true);
				cadClienteUI.moveToFront();
				getContentPane().add(cadClienteUI, 0);
				cadClienteUI.setVisible(true);

			}
		});
		mnCliente.add(mntmCadastroCliente);

		JMenuItem mntmConsulta = new JMenuItem("Consulta Cliente");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaClienteUI consClienteUI = new ConsultaClienteUI();
				consClienteUI.requestFocus(true);
				consClienteUI.setFocusable(true);
				consClienteUI.moveToFront();
				getContentPane().add(consClienteUI, 0);
				consClienteUI.setVisible(true);
			}
		});
		mnCliente.add(mntmConsulta);

		
		JMenu mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);

		JMenuItem mntmCadastroProduto = new JMenuItem("Cadastro Produto");
		mntmCadastroProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				CadastroProdutoUI cadProdutoUI = new CadastroProdutoUI(null,
						null);
				cadProdutoUI.requestFocus(true);
				cadProdutoUI.setFocusable(true);
				cadProdutoUI.moveToFront();
				getContentPane().add(cadProdutoUI, 0);
				cadProdutoUI.setVisible(true);

			}
		});
		mnProduto.add(mntmCadastroProduto);

		JMenuItem mntmConsultaProduto = new JMenuItem("Consulta Produto");
		mntmConsultaProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultaProdutoUI conProdutoUI = new ConsultaProdutoUI();
				conProdutoUI.requestFocus(true);
				conProdutoUI.setFocusable(true);
				conProdutoUI.moveToFront();
				getContentPane().add(conProdutoUI, 0);
				conProdutoUI.setVisible(true);

			}
		});
		mnProduto.add(mntmConsultaProduto);

		
		JMenu mnMesa = new JMenu("Mesa");
		menuBar.add(mnMesa);

		JMenuItem mntmCadastroMesa = new JMenuItem("Cadastro Mesa");
		mntmCadastroMesa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CadastroMesaUI cadMesaUI = new CadastroMesaUI(null, null);
				cadMesaUI.requestFocus(true);
				cadMesaUI.setFocusable(true);
				cadMesaUI.moveToFront();
				getContentPane().add(cadMesaUI, 0);
				cadMesaUI.setVisible(true);				
			}
		});		
		mnMesa.add(mntmCadastroMesa);
		
		JMenuItem mntmConsultaMesa = new JMenuItem("Consulta Mesa");
		mntmConsultaMesa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsultaMesaUI conMesaUI = new ConsultaMesaUI();
				conMesaUI.requestFocus(true);
				conMesaUI.setFocusable(true);
				conMesaUI.moveToFront();
				getContentPane().add(conMesaUI, 0);
				conMesaUI.setVisible(true);				
			}
		});		
		mnMesa.add(mntmConsultaMesa);

		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);

		
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Cliente:");

		jtfPedido = new JTextField();
		jtfPedido.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Pedidos", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		jtfCliente = new JTextField();
		jtfCliente.setColumns(10);

		JLabel lblPedido = new JLabel("Pedido:");

		JButton btnPesquisar = new JButton("Pesquisar");

		JButton btnAlterar = new JButton("Editar Pedido");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JButton btnNovo = new JButton("Novo Pedido");
		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FazerPedidoUI fazerPedido = new FazerPedidoUI();
				fazerPedido.requestFocus(true);
				fazerPedido.setFocusable(true);
				fazerPedido.moveToFront();
				getContentPane().add(fazerPedido, 0);
				fazerPedido.setVisible(true);
			}
		});

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
																Alignment.LEADING,
																false)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																524,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblPedido,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jtfPedido,
																				GroupLayout.PREFERRED_SIZE,
																				87,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(45)
																		.addComponent(
																				lblNome)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jtfCliente,
																				GroupLayout.PREFERRED_SIZE,
																				259,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnNovo,
																GroupLayout.DEFAULT_SIZE,
																147,
																Short.MAX_VALUE)
														.addComponent(
																btnAlterar,
																GroupLayout.DEFAULT_SIZE,
																147,
																Short.MAX_VALUE)
														.addComponent(
																btnPesquisar,
																GroupLayout.DEFAULT_SIZE,
																147,
																Short.MAX_VALUE)
														.addComponent(
																btnEncerrar,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblPedido)
														.addComponent(
																btnPesquisar)
														.addComponent(
																jtfPedido,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNome)
														.addComponent(
																jtfCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				panel,
																				GroupLayout.DEFAULT_SIZE,
																				473,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(27)
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
																		.addGap(113)
																		.addComponent(
																				btnEncerrar,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)))
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

		tablePedidoAberto = new JTable();

		List<Entidade> listPedido = new PedidoController().getPedidosAbertos();

		System.out.println(listPedido);
		for (Entidade ent : listPedido) {

			System.out.println("for");
			Pedido pedido = (Pedido) ent;

			// alterar valorParcial do pedidoAberto
			PedidoAberto pedidoAberto = new PedidoAberto(pedido.getId(), pedido
					.getCliente().getNome(), 0);
			listPedidoAberto.add(pedidoAberto);
			System.out.println(listPedidoAberto.size());
		}

		tablePedidoAberto
				.setModel(new PedidoAbertoTableModel(listPedidoAberto));
		scrollPane.setViewportView(tablePedidoAberto);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
