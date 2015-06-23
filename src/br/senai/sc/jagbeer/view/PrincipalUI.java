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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;
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
	private JTable table;

	private static PrincipalUI instancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					PrincipalUI frame = getInstancia();
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
						table, "principal");
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
				ConfigurarRelatorioFaturamentoUI configRelFat = new ConfigurarRelatorioFaturamentoUI();
				configRelFat.requestFocus(true);
				configRelFat.setFocusable(true);
				configRelFat.moveToFront();
				getContentPane().add(configRelFat, 0);
				configRelFat.setVisible(true);
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

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblPedido = new JLabel("Pedido:");

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Entidade> listPedidosAbertos = new ArrayList<Entidade>();
				try {
					listPedidosAbertos = new PedidoController()
							.getListPedidosEmAberto();

				} catch (Exception e3) {
					e3.printStackTrace();
				}

				// campo cliente preenchido
				if (!jtfCliente.getText().isEmpty()
						&& jtfPedido.getText().isEmpty()) {

					listPedidosAbertos = new ArrayList<Entidade>();

					try {
						Cliente cliente = (Cliente) new ClienteController()
								.getNomeSelecionado(jtfCliente.getText());

						Pedido pedido = (Pedido) new PedidoController()
								.getPorIdCliente(cliente.getId());

						listPedidosAbertos.add(pedido);

						table.setModel(new PrincipalTableModel(
								listPedidosAbertos));

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					// campo pedido preenchido
				} else if (jtfCliente.getText().isEmpty()
						&& !jtfPedido.getText().isEmpty()) {

					listPedidosAbertos = new ArrayList<Entidade>();
					try {
						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(Integer.parseInt(jtfPedido.getText()));
						listPedidosAbertos.add(pedido);

						table.setModel(new PrincipalTableModel(
								listPedidosAbertos));

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					// em branco
				} else if (jtfCliente.getText().isEmpty()
						&& jtfPedido.getText().isEmpty()) {

					table.setModel(new PrincipalTableModel(listPedidosAbertos));

					// tudo preenchido
				} else if (!jtfCliente.getText().isEmpty()
						&& !jtfPedido.getText().isEmpty()) {

					listPedidosAbertos = new ArrayList<Entidade>();
					try {

						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(Integer.parseInt(jtfPedido.getText()));

						Cliente cliente = (Cliente) new ClienteController()
								.getNomeSelecionado(jtfCliente.getText());

						if (pedido.getCliente().getId() == cliente.getId()) {
							listPedidosAbertos.add(pedido);

							table.setModel(new PrincipalTableModel(
									listPedidosAbertos));

						} else {
							JOptionPane
									.showMessageDialog(null,
											"Não foram encontrados pedidos abertos para os dados do filtro.");
						}

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}

		});

		JButton btnFazerPedido = new JButton("Fazer Pedido");
		btnFazerPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Pedido pedido = null;
				int linhaSelecionada = table.getSelectedRow();

				if (linhaSelecionada > -1) {

					int idPedido = Integer.parseInt(table.getValueAt(
							linhaSelecionada, 0).toString());

					try {
						pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

				FazerPedidoUI fazerPedido = new FazerPedidoUI(table, pedido);
				fazerPedido.requestFocus(true);
				fazerPedido.setFocusable(true);
				fazerPedido.moveToFront();
				getContentPane().add(fazerPedido, 0);
				fazerPedido.setVisible(true);
			}
		});

		JButton btnEncerrarEditar = new JButton("Encerrar / Editar Pedido");
		btnEncerrarEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Entidade> listProdutoPedido = new ArrayList<Entidade>();
				EncerrarEditarPedidoUI encerrarPedidoUI;
				ProdutoPedido produtoPedido = null;
				try {

					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada > -1) {

						int idPedido = Integer.parseInt(table.getValueAt(
								linhaSelecionada, 0).toString());

						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

						List<Entidade> listProdutos = new ProdutoPedidoController()
								.getPorIdPedido(idPedido);

						if (!listProdutos.isEmpty()) {
							for (Entidade ent : listProdutos) {

								produtoPedido = (ProdutoPedido) ent;
								listProdutoPedido.add(produtoPedido);

							}
						}

						encerrarPedidoUI = new EncerrarEditarPedidoUI(table,
								pedido);

						getContentPane().add(encerrarPedidoUI, 0);
						encerrarPedidoUI.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um pedido.");
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
																				ComponentPlacement.RELATED)
																		.addComponent(
																				jtfCliente,
																				GroupLayout.PREFERRED_SIZE,
																				259,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																546,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnPesquisar,
																GroupLayout.DEFAULT_SIZE,
																164,
																Short.MAX_VALUE)
														.addComponent(
																btnFazerPedido,
																GroupLayout.DEFAULT_SIZE,
																215,
																Short.MAX_VALUE)
														.addComponent(
																btnEncerrarEditar,
																GroupLayout.DEFAULT_SIZE,
																215,
																Short.MAX_VALUE))
										.addContainerGap(577,
												GroupLayout.PREFERRED_SIZE)));
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
																btnPesquisar,
																GroupLayout.PREFERRED_SIZE,
																34,
																GroupLayout.PREFERRED_SIZE)
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
																		.addGap(30)
																		.addComponent(
																				btnFazerPedido,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnEncerrarEditar,
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
										.addContainerGap(181, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
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

		table.setModel(new PrincipalTableModel(new PedidoController()
				.getListPedidosEmAberto()));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	public static PrincipalUI getInstancia() throws Exception {

		if (instancia == null) {
			instancia = new PrincipalUI();
		}

		return instancia;
	}

	public static void setInstancia(PrincipalUI instancia) {
		PrincipalUI.instancia = instancia;
	}

	public JTable getTablePedidoAberto() {
		return table;
	}

	public void setTablePedidoAberto(JTable tablePedidoAberto) {
		this.table = tablePedidoAberto;
	}

}
