package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;

/**
 * Classe que contém a tela principal do sistema
 * 
 * @author Jaime Gomes
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
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setForeground(Color.BLUE);
		setTitle("JAGBeer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);

		JMenuItem mntmCadastroCliente = new JMenuItem("Cadastro Cliente");
		mntmCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroClienteUI cadClienteUI = new CadastroClienteUI(null,
						table);
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

		JMenu mnRelatorio = new JMenu("Relatório");
		menuBar.add(mnRelatorio);

		JMenuItem mntmFaturamento = new JMenuItem("Faturamento");
		mntmFaturamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FiltroRelatorioUI configRelFat = new FiltroRelatorioUI("fat");
				configRelFat.requestFocus(true);
				configRelFat.setFocusable(true);
				configRelFat.moveToFront();
				getContentPane().add(configRelFat, 0);
				configRelFat.setVisible(true);
			}
		});
		mnRelatorio.add(mntmFaturamento);
		
		JMenuItem mntmProdutosMaisVendido = new JMenuItem("Produtos Mais Vendidos");
		mntmFaturamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FiltroRelatorioUI configRelFat = new FiltroRelatorioUI("prod");
				configRelFat.requestFocus(true);
				configRelFat.setFocusable(true);
				configRelFat.moveToFront();
				getContentPane().add(configRelFat, 0);
				configRelFat.setVisible(true);
			}
		});
		mnRelatorio.add(mntmProdutosMaisVendido);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pedidos Abertos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblJagbeer = new JLabel("");
		lblJagbeer.setIcon(new ImageIcon("images/chopp1.jpg"));
		lblJagbeer.setForeground(UIManager.getColor("textText"));
		lblJagbeer.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("JAGBeer");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJagbeer)
						.addComponent(lblNewLabel))
					.addContainerGap(621, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(lblJagbeer)
							.addGap(38)
							.addComponent(lblNewLabel)))
					.addContainerGap(69, Short.MAX_VALUE))
		);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(table);
		
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

						FazerPedidoUI fazerPedido = new FazerPedidoUI(null, pedido);
						fazerPedido.requestFocus(true);
						fazerPedido.setFocusable(true);
						fazerPedido.moveToFront();
						getContentPane().add(fazerPedido, 0);
						fazerPedido.setVisible(true);
					}
				});
		
				JButton btnEncerrarEditar = new JButton("Encerrar Pedido");
				btnEncerrarEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						EncerrarEditarPedidoUI encerrarPedidoUI;
						try {

							int linhaSelecionada = table.getSelectedRow();

							if (linhaSelecionada > -1) {

								int idPedido = Integer.parseInt(table.getValueAt(
										linhaSelecionada, 0).toString());

								Pedido pedido = (Pedido) new PedidoController()
										.getPorId(idPedido);

								encerrarPedidoUI = new EncerrarEditarPedidoUI(pedido);

								getContentPane().add(encerrarPedidoUI, 0);
								encerrarPedidoUI.requestFocus(true);
								encerrarPedidoUI.setFocusable(true);
								encerrarPedidoUI.moveToFront();
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
		
				JLabel lblPedido = new JLabel("Pedido:");
		
				jtfPedido = new JTextField();
				jtfPedido.setColumns(10);
		
				JLabel lblNome = new JLabel("Cliente:");
		
				jtfCliente = new JTextField();
				jtfCliente.setColumns(10);
		
				JButton btnPesquisar = new JButton("Pesquisar");
				btnPesquisar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							List<Entidade> listPedidosAbertos = null;

							// campo cliente preenchido
							if (!jtfCliente.getText().isEmpty()
									&& jtfPedido.getText().isEmpty()) {

								listPedidosAbertos = new ArrayList<Entidade>();

								try {
									List<Entidade> listClientes = new ClienteController()
											.getPorNome(jtfCliente.getText());

									for (Entidade ent : listClientes) {
										Cliente cliente = (Cliente) ent;

										Pedido pedido = (Pedido) new PedidoController()
												.getPedidoAbertoPorIdCliente(cliente
														.getId());

										if (pedido != null) {
											if (pedido.getStatus() == 1) {

												listPedidosAbertos.add(pedido);

											}

										}
									}

									if (listPedidosAbertos.isEmpty()) {
										JOptionPane
												.showMessageDialog(null,
														"Não foram encontrados pedidos abertos para o cliente solicitado.");
									}

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
											.getPorId(Integer.parseInt(jtfPedido
													.getText()));

									if (pedido != null) {

										if (pedido.getStatus() == 1) {

											listPedidosAbertos.add(pedido);

										}

										if (listPedidosAbertos.isEmpty()) {
											JOptionPane
													.showMessageDialog(null,
															"Não foram encontrados pedidos abertos para o cliente solicitado.");
										}
									} else {
										JOptionPane
												.showMessageDialog(null,
														"Não foram encontrados pedidos abertos para o cliente solicitado.");
									}

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

								table.setModel(new PrincipalTableModel(
										new PedidoController().getListPedidosEmAberto()));

								// tudo preenchido
							} else if (!jtfCliente.getText().isEmpty()
									&& !jtfPedido.getText().isEmpty()) {

								listPedidosAbertos = new ArrayList<Entidade>();
								try {

									Pedido pedido = (Pedido) new PedidoController()
											.getPorId(Integer.parseInt(jtfPedido
													.getText()));

									List<Entidade> listClientes = new ClienteController()
											.getPorNome(jtfCliente.getText());

									if (!listClientes.isEmpty()) {
										if (pedido.getCliente().getId() == listClientes
												.get(0).getId()) {

											if (pedido.getStatus() == 1) {
												listPedidosAbertos.add(pedido);

											} else {
												JOptionPane
														.showMessageDialog(null,
																"Não foram encontrados pedidos abertos para o filtro selecionado.");

											}

										} else {
											JOptionPane
													.showMessageDialog(null,
															"Não foram encontrados pedidos abertos para o filtro selecionado.");

										}
									} else {
										JOptionPane
												.showMessageDialog(null,
														"Não foram encontrados pedidos abertos para o filtro selecionado.");

									}

									table.setModel(new PrincipalTableModel(
											listPedidosAbertos));

								} catch (NumberFormatException e1) {
									e1.printStackTrace();
								} catch (Exception e1) {
									e1.printStackTrace();
								}

							}

						} catch (Exception e3) {
							e3.printStackTrace();
						}

					}

				});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPedido)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfPedido, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnFazerPedido, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
							.addComponent(btnEncerrarEditar, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPedido)
						.addComponent(jtfPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome)
						.addComponent(jtfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFazerPedido, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEncerrarEditar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

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
