package br.senai.sc.jagbeer.view;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PedidoAberto;
import br.senai.sc.jagbeer.model.PedidoAbertoTableModel;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;

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

	private static PrincipalUI instancia;

	// public static PrincipalUI obterInstancia() {
	//
	// if (instancia == null) {
	// instancia = new PrincipalUI();
	//
	// }
	// return instancia;
	// }

	private List<Entidade> listPedidoAberto = new ArrayList<Entidade>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// PrincipalUI frame = obterInstancia();
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
	 * 
	 * @throws Exception
	 */
	public PrincipalUI() throws Exception {

		setForeground(Color.BLUE);

		setTitle("JAGBeer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

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

		JMenu mnRelatorio = new JMenu("Relatorio");
		menuBar.add(mnRelatorio);

		JMenuItem mntmFaturamento = new JMenuItem("Faturamento");
		mntmFaturamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RelatorioFaturamentoUI relFaturamento = new RelatorioFaturamentoUI();
				relFaturamento.requestFocus(true);
				relFaturamento.setFocusable(true);
				relFaturamento.moveToFront();
				getContentPane().add(relFaturamento, 0);
				relFaturamento.setVisible(true);
			}
		});
		mnRelatorio.add(mntmFaturamento);

		JMenu mnSair = new JMenu("Sair");
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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

		tablePedidoAberto = new JTable();

		JLabel lblPedido = new JLabel("Pedido:");

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// campo cliente preenchido
				if (!jtfCliente.getText().isEmpty()
						&& jtfPedido.getText().isEmpty()) {

					Pedido pedido;
					try {
						Cliente cliente = (Cliente) new ClienteController()
								.getPorNome(jtfCliente.getText());

						pedido = (Pedido) new PedidoController()
								.getPorIdCliente(cliente.getId());

						listPedidoAberto.add(new ProdutoPedidoController()
								.getPorIdPedido(pedido.getId()));
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					if (listPedidoAberto.isEmpty()) {
						JOptionPane
								.showMessageDialog(null,
										"Não foram encontrados pedidos abertos para esse número de pedido..");
					} else {
						tablePedidoAberto.setModel(new PedidoAbertoTableModel(
								listPedidoAberto));
					}

					// campo pedido preenchido
				} else if (jtfCliente.getText().isEmpty()
						&& !jtfPedido.getText().isEmpty()) {

					try {
						System.out.println("id: " + jtfPedido.getText());
						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(Integer.parseInt(jtfPedido.getText()));

						listPedidoAberto.add(new ProdutoPedidoController()
								.getPorIdPedido(pedido.getId()));

						if (listPedidoAberto.isEmpty()) {
							JOptionPane
									.showMessageDialog(null,
											"Não foram encontrados pedidos abertos para esse cliente.");
							tablePedidoAberto
									.setModel(new PedidoAbertoTableModel(
											listPedidoAberto));
						}

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else if (jtfCliente.getText().isEmpty()
						&& jtfPedido.getText().isEmpty()) {

					try {
						tablePedidoAberto.setModel(new PedidoAbertoTableModel(
								new PedidoController().getPedidosAbertos()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		JButton btnNovo = new JButton("Fazer Pedido");
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
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int linhaSelecionada = tablePedidoAberto.getSelectedRow();

					if (linhaSelecionada > -1) {

						int idPedido = Integer.parseInt(tablePedidoAberto
								.getValueAt(linhaSelecionada, 0).toString());

						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

						pedido.setStatus(0);

						new PedidoController().editar(pedido);

					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um produto.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

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
																				lblPedido)
																		.addGap(4)
																		.addComponent(
																				jtfPedido,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(40)
																		.addComponent(
																				lblNome)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				63,
																				Short.MAX_VALUE)
																		.addComponent(
																				jtfCliente,
																				GroupLayout.PREFERRED_SIZE,
																				259,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(27))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				546,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																btnEncerrar,
																GroupLayout.DEFAULT_SIZE,
																169,
																Short.MAX_VALUE)
														.addComponent(
																btnNovo,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnPesquisar,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(143, Short.MAX_VALUE)));
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
														.addComponent(lblNome)
														.addComponent(
																jtfPedido,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jtfCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnPesquisar))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(30)
																		.addComponent(
																				btnNovo,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnEncerrar,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				452,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(39, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(tablePedidoAberto);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								435, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		List<Entidade> listPedido = new PedidoController().getPedidosAbertos();

		double valorParcial = 0;

		for (Entidade ent : listPedido) {

			Pedido pedido = (Pedido) ent;

			try {
				List<Entidade> listProdutosPedido = new ProdutoPedidoController()
						.listar();
				for (Entidade e : listProdutosPedido) {
					ProdutoPedido produtoPedido = (ProdutoPedido) e;

					Pedido p = (Pedido) new PedidoController()
							.getPorId(produtoPedido.getIdPedido());

					Cliente cliente = p.getCliente();

					if (produtoPedido.getIdPedido() == pedido.getId()
							&& cliente.getId() == pedido.getCliente().getId()) {
						Produto prod = (Produto) new ProdutoController()
								.getPorId(produtoPedido.getIdProduto());

						valorParcial += prod.getPrecoVenda()
								* produtoPedido.getQtde();

					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			PedidoAberto pedidoAberto = new PedidoAberto(pedido.getId(), pedido
					.getCliente().getNome(), valorParcial);

			listPedidoAberto.add(pedidoAberto);
		}

		tablePedidoAberto
				.setModel(new PedidoAbertoTableModel(listPedidoAberto));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	public static PrincipalUI getInstancia() {
		return instancia;
	}

	public static void setInstancia(PrincipalUI instancia) {
		PrincipalUI.instancia = instancia;
	}
}
