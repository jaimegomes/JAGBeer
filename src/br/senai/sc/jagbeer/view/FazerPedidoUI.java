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
import javax.swing.ListSelectionModel;
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
import br.senai.sc.jagbeer.model.FazerPedidoTableModel;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;

/**
 * Classe que contém a tela de fazer pedido
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
	private JTable tableFazerPedido;
	private JButton btnExcluir;
	private JComboBox cmbCliente;

	private List<Entidade> listProdutoPedido = new ArrayList<Entidade>();

	public FazerPedidoUI(final JTable table, Pedido pedido) {

		setTitle("Fazer Pedido");
		setClosable(true);
		setBounds(400, 150, 808, 499);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		lblPedido = new JLabel("Pedido:");

		cmbPedido = new JComboBox();
		cmbPedido.setMaximumRowCount(8);
		cmbPedido.addItem("");

		try {
			for (Entidade e : new PedidoController().getListPedidosEmAberto()) {

				Pedido pedidosAbertos = (Pedido) e;

				cmbPedido.addItem(pedidosAbertos.getId());
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		cmbPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int idPedido = 0;

				if (cmbPedido.getSelectedItem() != null
						&& cmbPedido.getSelectedIndex() > 0) {

					idPedido = Integer.parseInt(cmbPedido.getSelectedItem()
							+ "");

				} else {
					// seta os valores nos combox Mesa e Cliente
					cmbCliente.setSelectedIndex(-1);
					cmbMesa.setSelectedIndex(-1);
				}

				try {

					// seta os valores nos combox Mesa e Cliente
					if (idPedido > 0) {
						Pedido pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

						if (pedido.getCliente() != null) {
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
								.getNomeSelecionado(nomeCliente);

						if (cliente != null) {

							Pedido pedidoCliente = (Pedido) new PedidoController()
									.getPedidoAbertoPorIdCliente(cliente
											.getId());

							cmbPedido.setSelectedItem(pedidoCliente.getId());

							if (pedidoCliente.getMesa() != null) {
								cmbMesa.setSelectedItem(pedidoCliente.getMesa()
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

			for (Entidade ent : new PedidoController().getListPedidosEmAberto()) {
				Pedido pedidoAberto = (Pedido) ent;

				cmbCliente.addItem(pedidoAberto.getCliente().getNome());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// caso o pedido passado como parâmetro seja != null, carrega os valores
		// na tela de fazer pedido
		if (pedido != null) {
			cmbCliente.setSelectedItem(pedido.getCliente().getNome());
			cmbPedido.setSelectedItem(pedido.getId());

			if (pedido.getMesa() != null) {
				cmbMesa.setSelectedItem(pedido.getMesa().getNumeroMesa());

			}
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

				List<Entidade> listNomesProdutos = new ArrayList<Entidade>();
				try {

					cmbProduto.removeAllItems();

					// verifica a classificação escolhida
					if (cmbClassificacao.getSelectedIndex() == 1) {
						listNomesProdutos = new ProdutoController()
								.getPorClassificacao("Alimento");

					} else if (cmbClassificacao.getSelectedIndex() == 2) {
						listNomesProdutos = new ProdutoController()
								.getPorClassificacao("Bebida");
					}

					// adiciona os produtos ao combobox
					if (listNomesProdutos.size() > 0) {
						for (Entidade ent : listNomesProdutos) {

							Produto produto = (Produto) ent;
							cmbProduto.addItem(produto.getNome());

						}
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Não existem produtos com esta classificação cadastrados no sistema.");
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
				ProdutoPedido produtoPedido = null;

				try {
					Produto produto = (Produto) new ProdutoController()
							.getPorNome(nomeProduto);

					int idPedido = (int) cmbPedido.getSelectedItem();

					if (Integer.parseInt(spinnerQtde.getValue().toString()) > 0) {

						produtoPedido = new ProdutoPedido(produto.getId(),
								idPedido, Integer.parseInt(spinnerQtde
										.getValue().toString()));

						listProdutoPedido.add(produtoPedido);
						tableFazerPedido.setModel(new FazerPedidoTableModel(
								listProdutoPedido));
					} else {
						listProdutoPedido.remove(produtoPedido);
						JOptionPane.showMessageDialog(null,
								"Você deve selecionar a quantidade desejada.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Pedido pedido = null;

				try {

					if (cmbPedido.getSelectedIndex() > 0) {

						int idPedido = (int) cmbPedido.getSelectedItem();

						pedido = (Pedido) new PedidoController()
								.getPorId(idPedido);

					} else {
						JOptionPane.showMessageDialog(null,
								"Você deve selecionar um pedido.");
					}

					if (!listProdutoPedido.isEmpty()) {

						double valor = 0;
						for (Entidade e : listProdutoPedido) {
							ProdutoPedido produtoPedido = (ProdutoPedido) e;

							int idProduto = produtoPedido.getIdProduto();

							Produto produto = (Produto) new ProdutoController()
									.getPorId(idProduto);

							valor += produto.getPrecoVenda()
									* produtoPedido.getQtde();

							new ProdutoPedidoController().salvar(produtoPedido);

						}

						// caso o pedido não tenha mesa setada e no momento do
						// pedido for selecionada uma mesa, atualiza a entidade
						// pedido no banco de dados com o valor do id da mesa
						if (pedido.getMesa() == null) {
							int idMesa = 0;

							if (cmbMesa.getSelectedIndex() > 0) {

								idMesa = (int) cmbMesa.getSelectedItem();
							}

							if (idMesa > 0) {

								Mesa mesa = (Mesa) new MesaController()
										.getPorId(idMesa);
								pedido.setMesa(mesa);
							}
						}

						// atualizar o valor do pedido
						pedido.setValor(pedido.getValor() + valor);
						new PedidoController().editar(pedido);

						PrincipalUI
								.getInstancia()
								.getTablePedidoAberto()
								.setModel(
										new PrincipalTableModel(
												new PedidoController()
														.getListPedidosEmAberto()));

						if (table != null) {

							table.setModel(new FazerPedidoTableModel(
									new ProdutoPedidoController()
											.getPorIdPedido(pedido.getId())));

							EncerrarEditarPedidoUI encerrarPedidoUI = new EncerrarEditarPedidoUI(
									pedido);
							getContentPane().add(encerrarPedidoUI, 0);
							encerrarPedidoUI.requestFocus(true);
							encerrarPedidoUI.setFocusable(true);
							encerrarPedidoUI.moveToFront();
							PrincipalUI.getInstancia().getContentPane()
									.add(encerrarPedidoUI, 0);
							encerrarPedidoUI.setVisible(true);

						}

						JOptionPane.showMessageDialog(null,
								"Produtos inseridos com sucesso ao pedido de número: "
										+ pedido.getId());

						dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"Você deve adicionar produtos ao pedido.");
					}

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

					int linhaSelecionada = tableFazerPedido.getSelectedRow();

					if (linhaSelecionada > -1) {

						listProdutoPedido.remove(linhaSelecionada);

						tableFazerPedido.setModel(new FazerPedidoTableModel(
								listProdutoPedido));

					} else {
						JOptionPane
								.showMessageDialog(null,
										"Selecione um produto do pedido que deseja excluir.");
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
										.addComponent(panelProduto,
												GroupLayout.DEFAULT_SIZE, 758,
												Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								groupLayoutProduto
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																746,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																groupLayoutProduto
																		.createSequentialGroup()
																		.addComponent(
																				btnSalvar,
																				GroupLayout.PREFERRED_SIZE,
																				100,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnCancelar,
																				GroupLayout.PREFERRED_SIZE,
																				100,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayoutProduto
																		.createSequentialGroup()
																		.addComponent(
																				lblPedido)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cmbPedido,
																				GroupLayout.PREFERRED_SIZE,
																				71,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				lblMesa)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cmbMesa,
																				GroupLayout.PREFERRED_SIZE,
																				76,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				lblCliente)
																		.addGap(18)
																		.addComponent(
																				cmbCliente,
																				0,
																				369,
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

		tableFazerPedido = new JTable();
		tableFazerPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableFazerPedido);

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
																cmbClassificacao,
																0,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																cmbProduto, 0,
																277,
																Short.MAX_VALUE))
										.addGap(18)
										.addGroup(
												gl_panelProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panelProduto
																		.createSequentialGroup()
																		.addComponent(
																				btnAdicionarProduto)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnExcluir,
																				GroupLayout.PREFERRED_SIZE,
																				163,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panelProduto
																		.createSequentialGroup()
																		.addComponent(
																				lblQuantidade)
																		.addGap(18)
																		.addComponent(
																				spinnerQtde,
																				GroupLayout.PREFERRED_SIZE,
																				67,
																				GroupLayout.PREFERRED_SIZE)))
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
																btnExcluir)
														.addComponent(
																btnAdicionarProduto))
										.addContainerGap(17, Short.MAX_VALUE)));
		panelProduto.setLayout(gl_panelProduto);
		panel.setLayout(groupLayoutProduto);
		tableFazerPedido.setModel(new FazerPedidoTableModel(listProdutoPedido));
		getContentPane().setLayout(groupLayout);

	}

	public JComboBox getCmbCliente() {
		return cmbCliente;
	}

	public void setCmbCliente(JComboBox cmbCliente) {
		this.cmbCliente = cmbCliente;
	}

	public JComboBox getCmbMesa() {
		return cmbMesa;
	}

	public void setCmbMesa(JComboBox cmbMesa) {
		this.cmbMesa = cmbMesa;
	}

	public JComboBox getCmbPedido() {
		return cmbPedido;
	}

	public void setCmbPedido(JComboBox cmbPedido) {
		this.cmbPedido = cmbPedido;
	}

}
