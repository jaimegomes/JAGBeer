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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe que contém a tela de consulta de produto
 * 
 * @author Jaime Gomes
 *
 */
public class ConsultaProdutoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private GroupLayout groupLayout;
	private JLabel lblNome;
	private JTextField jtfNome;
	private JLabel lblClassificacao;
	private JComboBox cmbClassificacao;
	private JButton btnEditarInserir;
	private GroupLayout gl_panel;
	private JButton btnPesquisar;
	private JButton btnExcluir;
	private JButton btnLimpar;
	private JTable table;

	private List<Entidade> listProduto = new ArrayList<Entidade>();

	/**
	 * Create the frame.
	 */
	public ConsultaProdutoUI() {

		setTitle("Consulta de Produtos");
		setClosable(true);
		setBounds(100, 100, 650, 450);

		try {
			table = new JTable(new ProdutoTableModel(listProduto));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Consulta de Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 497,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 391,
								Short.MAX_VALUE).addContainerGap()));

		lblNome = new JLabel("Nome:");

		jtfNome = new JTextField();
		jtfNome.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					String classificacao = "";

					if (cmbClassificacao.getSelectedIndex() == 1)
						classificacao = "Alimento";

					if (cmbClassificacao.getSelectedIndex() == 2)
						classificacao = "Bebida";

					// tudo em branco
					if (cmbClassificacao.getSelectedIndex() <= 0
							&& jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController().listar();
						table.setModel(new ProdutoTableModel(listProduto));
					}

					// campo nome preenchido
					else if (!jtfNome.getText().isEmpty()
							&& cmbClassificacao.getSelectedIndex() <= 0) {

						listProduto = new ProdutoController()
								.getListNomesProdutos(jtfNome.getText());

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(null,
											"Nao existem produtos com este nome cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campo classificaï¿½ï¿½o preenchido
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.getPorClassificacao(classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(null,
											"Nao existem produtos com esta classificacao cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campos nome e classificaï¿½ï¿½o preenchidos
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& !jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.buscaPorNomeClassificacao(jtfNome.getText(),
										classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(
											null,
											"Nao existem produtos com este nome e classificacao cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow();

				if (linhaSelecionada > -1) {

					int opcao = JOptionPane.showConfirmDialog(null,
							"Deseja excluir?");

					if (opcao == 0) {
						try {

							String nomeProduto = table.getValueAt(
									linhaSelecionada, 0).toString();

							Double precoVenda = Double
									.parseDouble(table.getValueAt(
											linhaSelecionada, 2).toString());

							String classificacao = table.getValueAt(
									linhaSelecionada, 3).toString();

							Produto produtoExcluir = (Produto) new ProdutoController()
									.buscaCompleta(nomeProduto, precoVenda,
											classificacao);

							List<Entidade> listProdutosPedidos = new ProdutoPedidoController()
									.getPorIdProduto(produtoExcluir.getId());

							List<Entidade> listPedidosAbertos = new PedidoController()
									.getListPedidosEmAberto();

							for (Entidade en : listPedidosAbertos) {

								Pedido pedidoAberto = (Pedido) en;

								for (Entidade ent : listProdutosPedidos) {
									ProdutoPedido produtoPedido = (ProdutoPedido) ent;

									if (pedidoAberto.getId() == produtoPedido
											.getIdPedido()) {

										Produto p = (Produto) new ProdutoController()
												.getPorId(produtoPedido
														.getIdProduto());

										pedidoAberto.setValor(pedidoAberto
												.getValor()
												- (p.getPrecoVenda() * produtoPedido
														.getQtde()));

										new PedidoController()
												.editar(pedidoAberto);
									}
								}

							}

							new ProdutoController().excluir(produtoExcluir);

							JOptionPane.showMessageDialog(null,
									"Produto excluido com Sucesso!");

							// Atualiza tabela
							table.setModel(new ProdutoTableModel(
									new ProdutoController().listar()));

							PrincipalUI
									.getInstancia()
									.getTablePedidoAberto()
									.setModel(
											new PrincipalTableModel(
													new PedidoController()
															.getListPedidosEmAberto()));

						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}
					}
				}
			}
		});

		lblClassificacao = new JLabel("Classificação:");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfNome.setText("");
				cmbClassificacao.setSelectedIndex(-1);

			}
		});

		cmbClassificacao = new JComboBox();
		cmbClassificacao.setModel(new DefaultComboBoxModel(new String[] { "",
				"Alimentos", "Bebidas" }));
		cmbClassificacao.setMaximumRowCount(3);

		btnEditarInserir = new JButton("Editar / Inserir");
		btnEditarInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CadastroProdutoUI cadProdutoUI;
				try {

					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada > -1) {

						String nomeProduto = table.getValueAt(linhaSelecionada,
								0).toString();

						Double precoVenda = Double.parseDouble(table
								.getValueAt(linhaSelecionada, 2).toString());

						String classificacao = table.getValueAt(
								linhaSelecionada, 3).toString();

						Produto produtoEditar = (Produto) new ProdutoController()
								.buscaCompleta(nomeProduto, precoVenda,
										classificacao);

						cadProdutoUI = new CadastroProdutoUI(produtoEditar,
								table);

					} else {

						cadProdutoUI = new CadastroProdutoUI(null, table);
					}

					PrincipalUI.getInstancia().getContentPane()
							.add(cadProdutoUI, 0);
					cadProdutoUI.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						lblNome)
																				.addComponent(
																						lblClassificacao))
																.addGap(8)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						jtfNome,
																						GroupLayout.PREFERRED_SIZE,
																						175,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmbClassificacao,
																						GroupLayout.PREFERRED_SIZE,
																						130,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						btnLimpar,
																						GroupLayout.DEFAULT_SIZE,
																						150,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnPesquisar,
																						GroupLayout.DEFAULT_SIZE,
																						150,
																						Short.MAX_VALUE))
																.addGap(22)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																				.addComponent(
																						btnExcluir,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnEditarInserir,
																						GroupLayout.DEFAULT_SIZE,
																						150,
																						Short.MAX_VALUE)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(10)
																.addComponent(
																		scrollPane,
																		GroupLayout.DEFAULT_SIZE,
																		592,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(14)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfNome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNome)
												.addComponent(btnEditarInserir)
												.addComponent(btnPesquisar))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														cmbClassificacao,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblClassificacao)
												.addComponent(btnLimpar)
												.addComponent(btnExcluir))
								.addGap(18)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 285,
										Short.MAX_VALUE).addContainerGap()));

		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
