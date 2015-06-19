package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ItemPedido;
import br.senai.sc.jagbeer.model.ItemPedidoTableModel;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;

/**
 * Classe View FazerPedidoUI
 * 
 * @author Jaime Gomes
 *
 */
public class FazerPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private GroupLayout groupLayout;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JComboBox cmbPedido;
	private JLabel lblPedido;
	private GroupLayout groupLayoutProduto;
	private JPanel panelProduto;
	private JComboBox cmbClassificacao;
	private JLabel lblClassificao;
	private JLabel lblProduto;
	private JComboBox cmbProduto;
	private JLabel lblQuantidade;
	private JComboBox cmbMesa;
	private JLabel lblMesa;
	private JSpinner spinnerQtde;
	private JButton btnAdicionarProduto;
	private GroupLayout gl_panelProduto;
	private List<Entidade> listItensPedido = new ArrayList<Entidade>();
	private List<Entidade> listNomesProdutos = new ArrayList<Entidade>();
	private List<Entidade> listClientes = new ArrayList<Entidade>();
	private JTable table;
	private JButton btnExcluir;
	private JComboBox cmbCliente;

	public FazerPedidoUI() {

		setTitle("Fazer Pedido");
		setClosable(true);
		setBounds(100, 0, 625, 471);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		lblPedido = new JLabel("Pedido:");

		cmbPedido = new JComboBox();
		cmbPedido.setMaximumRowCount(8);
		cmbPedido.addItem("");
		cmbPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int idPedido = 0;

				if (cmbPedido.getSelectedItem() != null
						&& !cmbPedido.getSelectedItem().equals("")) {

					idPedido = Integer.parseInt(cmbPedido.getSelectedItem()
							+ "");

				} else {
					cmbCliente.setSelectedIndex(-1);
					cmbMesa.setSelectedIndex(-1);
				}

				try {

					if (idPedido > 0) {
						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

						if (pedido != null) {
							cmbCliente.setSelectedItem(pedido.getCliente()
									.getNome());

							if (pedido.getMesa() != null) {
								cmbMesa.setSelectedItem(pedido.getMesa()
										.getNumeroMesa());
							}

						}

					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}

			}

		});

		try {
			List<Entidade> listIdPedidos = new PedidoController()
					.getPedidosAbertos();

			if (listIdPedidos.size() > 0) {
				for (Entidade e : listIdPedidos) {
					Pedido pedido = (Pedido) e;
					cmbPedido.addItem(pedido.getId());

				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}

		panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		lblMesa = new JLabel("Mesa:");

		cmbMesa = new JComboBox();
		cmbMesa.setMaximumRowCount(8);
		cmbMesa.addItem("");

		try {
			List<Entidade> listMesas = new MesaController().listar();

			if (listMesas.size() > 0) {
				for (Entidade e : listMesas) {
					Mesa mesa = (Mesa) e;

					cmbMesa.addItem(mesa.getNumeroMesa());

				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JLabel lblCliente = new JLabel("Cliente:");

		cmbCliente = new JComboBox();
		cmbCliente.setMaximumRowCount(8);
		cmbCliente.addItem("");
		cmbCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nomeCliente = cmbCliente.getSelectedItem() + "";

				Cliente cliente = null;
				try {

					if (!nomeCliente.equals("") && nomeCliente != null) {

						cliente = (Cliente) new ClienteController()
								.getPorNome(nomeCliente);

						if (cliente != null) {
							int idCliente = cliente.getId();

							Pedido pedido = (Pedido) new PedidoController()
									.getPorId(idCliente);
							cmbPedido.setSelectedItem(pedido.getId());

							if (pedido.getMesa() != null) {
								cmbMesa.setSelectedItem(pedido.getMesa()
										.getNumeroMesa());
							}

						}
					} else {
						cmbPedido.setSelectedIndex(-1);
						cmbMesa.setSelectedIndex(-1);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		try {
			List<Entidade> listClientes = new ArrayList<Entidade>();

			for (Entidade ent : new PedidoController().getPedidosAbertos()) {
				Pedido pedido = (Pedido) ent;

				listClientes.add(new ClienteController().getPorId(pedido
						.getCliente().getId()));
			}

			for (Entidade en : listClientes) {
				Cliente cliente = (Cliente) en;
				cmbCliente.addItem(cliente.getNome());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		lblClassificao = new JLabel("Classifica\u00E7\u00E3o:");

		cmbClassificacao = new JComboBox();
		cmbClassificacao.setModel(new DefaultComboBoxModel(new String[] { "",
				"Alimentos", "Bebidas" }));
		cmbClassificacao.setSelectedIndex(0);
		cmbClassificacao.setMaximumRowCount(3);
		cmbClassificacao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					cmbProduto.removeAllItems();

					if (cmbClassificacao.getSelectedIndex() == 1) {
						listNomesProdutos = new ProdutoController()
								.getPorClassificacao("Alimento");

					} else if (cmbClassificacao.getSelectedIndex() == 2) {
						listNomesProdutos = new ProdutoController()
								.getPorClassificacao("Bebida");
					}

					if (listNomesProdutos.size() > 0) {
						for (Entidade ent : listNomesProdutos) {

							Produto produto = (Produto) ent;
							cmbProduto.addItem(produto.getNome());

						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		lblProduto = new JLabel("Produto:");

		cmbProduto = new JComboBox();
		cmbProduto.setMaximumRowCount(8);
		cmbProduto.addItem("");

		lblQuantidade = new JLabel("Quantidade:");

		spinnerQtde = new JSpinner();
		spinnerQtde.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));

		btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nomeProduto = cmbProduto.getSelectedItem() + "";

				try {
					Produto produto = (Produto) new ProdutoController()
							.getPorNome(nomeProduto);

					ItemPedido itemPedido = new ItemPedido(produto, Integer
							.parseInt(spinnerQtde.getValue().toString()));

					listItensPedido.add(itemPedido);
					table.setModel(new ItemPedidoTableModel(listItensPedido));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Pedido pedido = null;
				Mesa mesa = null;

				try {

					if (cmbPedido.getSelectedIndex() > 0) {

						int idPedido = Integer.parseInt(cmbPedido
								.getSelectedItem() + "");

						pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

					} else {
						pedido = new Pedido();
					}

					for (Entidade e : listItensPedido) {
						ItemPedido itemPedido = (ItemPedido) e;

						ProdutoPedido produtoPedido = new ProdutoPedido();
						produtoPedido.setIdPedido(pedido.getId());
						produtoPedido.setIdProduto(itemPedido.getProduto()
								.getId());

						new ProdutoPedidoController().salvar(produtoPedido);
					}

					JOptionPane.showMessageDialog(null,
							"Produtos inseridos com sucesso.");

					dispose();

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnExcluir = new JButton("Excluir Produto");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada > -1) {

						listItensPedido.remove(linhaSelecionada);

						table.setModel(new ItemPedidoTableModel(listItensPedido));

					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um item do pedido.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 591,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 424,
								Short.MAX_VALUE).addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();

		groupLayoutProduto = new GroupLayout(panel);
		groupLayoutProduto
				.setHorizontalGroup(groupLayoutProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayoutProduto
										.createSequentialGroup()
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayoutProduto
																		.createParallelGroup(
																				Alignment.TRAILING)
																		.addGroup(
																				groupLayoutProduto
																						.createSequentialGroup()
																						.addComponent(
																								btnSalvar,
																								GroupLayout.PREFERRED_SIZE,
																								107,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED)
																						.addComponent(
																								btnCancelar))
																		.addGroup(
																				groupLayoutProduto
																						.createSequentialGroup()
																						.addGap(6)
																						.addComponent(
																								lblPedido)
																						.addGap(4)
																						.addComponent(
																								cmbPedido,
																								GroupLayout.PREFERRED_SIZE,
																								71,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addComponent(
																								lblMesa)
																						.addGap(3)
																						.addComponent(
																								cmbMesa,
																								GroupLayout.PREFERRED_SIZE,
																								76,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addComponent(
																								lblCliente)
																						.addGap(4)
																						.addComponent(
																								cmbCliente,
																								GroupLayout.PREFERRED_SIZE,
																								210,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(5)))
														.addComponent(
																panelProduto,
																GroupLayout.PREFERRED_SIZE,
																575,
																Short.MAX_VALUE)
														.addGroup(
																groupLayoutProduto
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				scrollPane,
																				GroupLayout.DEFAULT_SIZE,
																				575,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayoutProduto
				.setVerticalGroup(groupLayoutProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayoutProduto
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblPedido)
														.addComponent(
																cmbPedido,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMesa)
														.addComponent(
																cmbMesa,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCliente)
														.addComponent(
																cmbCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(panelProduto,
												GroupLayout.PREFERRED_SIZE,
												107, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(scrollPane,
												GroupLayout.PREFERRED_SIZE,
												234, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnCancelar)
														.addComponent(btnSalvar))
										.addContainerGap()));

		table = new JTable();
		scrollPane.setViewportView(table);

		gl_panelProduto = new GroupLayout(panelProduto);
		gl_panelProduto
				.setHorizontalGroup(gl_panelProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelProduto
										.createSequentialGroup()
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblClassificao)
														.addComponent(
																lblProduto))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																cmbProduto,
																0,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																cmbClassificacao,
																0, 130,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelProduto
																		.createSequentialGroup()
																		.addComponent(
																				lblQuantidade)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				spinnerQtde,
																				GroupLayout.PREFERRED_SIZE,
																				67,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panelProduto
																		.createSequentialGroup()
																		.addComponent(
																				btnAdicionarProduto)
																		.addGap(18)
																		.addComponent(
																				btnExcluir,
																				GroupLayout.PREFERRED_SIZE,
																				128,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panelProduto
				.setVerticalGroup(gl_panelProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelProduto
										.createSequentialGroup()
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cmbClassificacao,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblClassificao)
														.addComponent(
																lblQuantidade)
														.addComponent(
																spinnerQtde,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblProduto)
														.addComponent(
																cmbProduto,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnAdicionarProduto)
														.addComponent(
																btnExcluir))
										.addContainerGap(17, Short.MAX_VALUE)));
		panelProduto.setLayout(gl_panelProduto);
		panel.setLayout(groupLayoutProduto);
		table.setModel(new ItemPedidoTableModel(listItensPedido));
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * @return the listItensPedido
	 */
	public List<Entidade> getListItensPedido() {
		return listItensPedido;
	}

	/**
	 * @param listItensPedido
	 *            the listItensPedido to set
	 */
	public void setListItensPedido(List<Entidade> listItensPedido) {
		this.listItensPedido = listItensPedido;
	}
}
