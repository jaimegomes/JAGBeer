package br.senai.sc.jagbeer.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.EncerrarPedidoTableModel;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PedidoAbertoTableModel;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;

public class EncerrarEditarPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente = null;
	private JTable tableEncerraPedido;
	private JLabel lblNomeCliente;
	private JLabel lblNome;
	private JLabel lblPedido;
	private JLabel lblNumeroPedido;
	private JScrollPane scrollPane;
	private JLabel lblDdmmyyyy;
	private JLabel lblData;
	private JButton btnEncerrar;
	private JButton btnExcluirItem;
	private JLabel lblTotal;
	private JLabel lblValor;
	private JButton btnAdicionarProduto;
	private GroupLayout groupLayout;
	private GroupLayout gl_panel;

	/**
	 * Create the frame.
	 */
	public EncerrarEditarPedidoUI(JTable table, final Pedido pedido) {

		cliente = pedido.getCliente();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		setTitle("Encerrar Pedido");
		setClosable(true);
		setBounds(100, 100, 650, 600);

		JPanel panel = new JPanel();

		panel.setBorder(new TitledBorder(null, "Encerrar Pedido",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeCliente.setText(cliente.getNome());

		lblNome = new JLabel("Cliente:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPedido = new JLabel("Pedido:");
		lblPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setText("" + pedido.getId());
		lblNumeroPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollPane = new JScrollPane();

		lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		lblDdmmyyyy.setText("" + sdf.format(pedido.getDataPedido()));

		lblData = new JLabel("Data:");

		btnEncerrar = new JButton("Encerrar");

		btnExcluirItem = new JButton("Excluir Item");
		btnExcluirItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int linhaSelecionada = tableEncerraPedido.getSelectedRow();

					if (linhaSelecionada > -1) {

						int opcao = JOptionPane.showConfirmDialog(null,
								"Deseja excluir o produto selecionado?");

						if (opcao == 0) {

							String nomeProduto = tableEncerraPedido.getValueAt(
									linhaSelecionada, 0).toString();

							Produto produto = (Produto) new ProdutoController()
									.getPorNome(nomeProduto);

							int qtdeProduto = Integer
									.parseInt(tableEncerraPedido.getValueAt(
											linhaSelecionada, 2).toString());

							ProdutoPedido produtoPedido = (ProdutoPedido) new ProdutoPedidoController()
									.getPorNomeQtdeIdPedido(produto.getId(),
											qtdeProduto, pedido.getId());

							new ProdutoPedidoController()
									.excluir(produtoPedido);

							tableEncerraPedido
									.setModel(new EncerrarPedidoTableModel(
											getListProdutos(pedido.getId())));

							lblValor.setText(""
									+ getValorTotal(getListProdutos(pedido
											.getId())));

							PrincipalUI
									.obterInstancia()
									.getTablePedidoAberto()
									.setModel(
											new PedidoAbertoTableModel(
													new PedidoController()
															.getListPedidosAbertos()));

							JOptionPane.showMessageDialog(null,
									"Produto excluído com sucesso.");
						}

					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um item do pedido.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnAdicionarProduto = new JButton("Add Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();

				try {

					FazerPedidoUI fazerPedido = new FazerPedidoUI(
							tableEncerraPedido);
					fazerPedido.getCmbPedido().setSelectedItem(pedido.getId());

					if (pedido.getCliente() != null) {
						fazerPedido.getCmbCliente().setSelectedItem(
								pedido.getCliente().getNome());
					}

					if (pedido.getMesa() != null) {
						fazerPedido.getCmbMesa().setSelectedItem(
								pedido.getMesa().getNumeroMesa());
					}

					fazerPedido.requestFocus(true);
					fazerPedido.setFocusable(true);
					fazerPedido.moveToFront();

					PrincipalUI.obterInstancia().getContentPane()
							.add(fazerPedido, 0);
					fazerPedido.setVisible(true);

					// PrincipalUI
					// .obterInstancia()
					// .getTablePedidoAberto()
					// .setModel(
					// new PedidoAbertoTableModel(
					// new PedidoController()
					// .getListPedidosAbertos()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		lblTotal = new JLabel("Total: R$");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblValor = new JLabel("Valor");
		try {
			lblValor.setText(""
					+ getValorTotal(getListProdutos(pedido.getId())));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 608,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(19)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 536,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						lblNome,
																						Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblPedido,
																						Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblNumeroPedido)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										322,
																										Short.MAX_VALUE)
																								.addComponent(
																										lblData)
																								.addPreferredGap(
																										ComponentPlacement.UNRELATED)
																								.addComponent(
																										lblDdmmyyyy)
																								.addGap(6))
																				.addComponent(
																						lblNomeCliente)))
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														574,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblTotal,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		lblValor)
																.addGap(99)
																.addComponent(
																		btnAdicionarProduto,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		btnExcluirItem,
																		GroupLayout.PREFERRED_SIZE,
																		120,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(12)
																.addComponent(
																		btnEncerrar,
																		GroupLayout.PREFERRED_SIZE,
																		123,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblNome,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNomeCliente))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblPedido,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblNumeroPedido,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblData)
												.addComponent(lblDdmmyyyy))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 368,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblTotal)
												.addComponent(lblValor)
												.addComponent(btnEncerrar)
												.addComponent(btnExcluirItem)
												.addComponent(
														btnAdicionarProduto))
								.addContainerGap(35, Short.MAX_VALUE)));

		tableEncerraPedido = new JTable();
		try {
			tableEncerraPedido.setModel(new EncerrarPedidoTableModel(
					getListProdutos(pedido.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane.setViewportView(tableEncerraPedido);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	private double getValorTotal(final List<Entidade> listProdutos)
			throws Exception {

		double valorTotal = 0;

		if (!listProdutos.isEmpty()) {
			for (Entidade e : listProdutos) {
				ProdutoPedido prodPedido = (ProdutoPedido) e;

				Produto prod = (Produto) new ProdutoController()
						.getPorId(prodPedido.getIdProduto());
				valorTotal += prod.getPrecoVenda() * prodPedido.getQtde();

			}

		}
		return valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Retorna uma lista de entidade ProdutoPedido que tenham o id do pedido
	 * igual ao passado como parâmetro.
	 * 
	 * @return new ProdutoPedidoController().getPorIdPedido(idPedido)
	 * @throws Exception
	 */
	public List<Entidade> getListProdutos(int idPedido) throws Exception {
		return new ProdutoPedidoController().getPorIdPedido(idPedido);

	}

}
