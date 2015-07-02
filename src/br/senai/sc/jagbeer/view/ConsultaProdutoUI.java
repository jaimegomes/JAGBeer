package br.senai.sc.jagbeer.view;

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
	private JButton btnEditar;
	private GroupLayout gl_panel;
	private JButton btnPesquisar;
	private JButton btnExcluir;
	private JButton btnLimpar;
	private JTable table;
	private JScrollPane scrollPane;

	private List<Entidade> listProduto = new ArrayList<Entidade>();
	private JButton btnInserir;

	/**
	 * Create the frame.
	 */
	public ConsultaProdutoUI() {

		setClosable(true);
		setTitle("Consulta Produto");
		setBounds(610, 255, 650, 420);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consulta Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblNome = new JLabel("Nome:");

		jtfNome = new JTextField();
		jtfNome.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					String classificacao = "";

					if (cmbClassificacao.getSelectedIndex() == 1)
						classificacao = "Bebidas";

					if (cmbClassificacao.getSelectedIndex() == 2)
						classificacao = "Drinks";

					if (cmbClassificacao.getSelectedIndex() == 3)
						classificacao = "Lanches";

					if (cmbClassificacao.getSelectedIndex() == 4)
						classificacao = "Porções";

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
											"Não existem produtos com este nome cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campo classificação preenchido
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.getPorClassificacao(classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(null,
											"Não existem produtos com esta classificação cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campos nome e classificação preenchidos
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& !jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.buscaPorNomeClassificacao(jtfNome.getText(),
										classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(
											null,
											"Não existem produtos com este nome e classificação cadastrados no banco de dados.");

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
									"Produto excluído com Sucesso!");

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
				"Bebidas", "Drinks", "Lanches", "Porções" }));
		cmbClassificacao.setMaximumRowCount(5);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
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

						PrincipalUI.getInstancia().getContentPane()
								.add(cadProdutoUI, 0);
						cadProdutoUI.setVisible(true);

						// Atualiza tabela
						table.setModel(new ProdutoTableModel(
								new ProdutoController().listar()));

					} else {

						JOptionPane.showMessageDialog(null,
								"Você deve selecionar um produto da lista.");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					CadastroProdutoUI cadProdutoUI = new CadastroProdutoUI(
							null, table);

					PrincipalUI.getInstancia().getContentPane()
							.add(cadProdutoUI, 0);

					cadProdutoUI.setVisible(true);

					// Atualiza tabela
					table.setModel(new ProdutoTableModel(
							new ProdutoController().listar()));

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap(182, Short.MAX_VALUE)
								.addComponent(btnInserir,
										GroupLayout.PREFERRED_SIZE, 134,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnEditar,
										GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnExcluir,
										GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE).addGap(18))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 616,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 322,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnExcluir)
												.addComponent(btnEditar)
												.addComponent(btnInserir))
								.addGap(17)));

		scrollPane = new JScrollPane();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(lblNome)
												.addComponent(lblClassificacao))
								.addPreferredGap(ComponentPlacement.RELATED,
										42, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING, false)
												.addComponent(
														cmbClassificacao,
														0,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jtfNome,
														GroupLayout.DEFAULT_SIZE,
														181, Short.MAX_VALUE))
								.addGap(28)
								.addComponent(btnPesquisar,
										GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnLimpar,
										GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 604,
						Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNome)
												.addComponent(btnLimpar)
												.addComponent(btnPesquisar)
												.addComponent(
														jtfNome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblClassificacao)
												.addComponent(
														cmbClassificacao,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 224,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		try {
			table = new JTable(new ProdutoTableModel(
					new ProdutoController().listar()));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(277);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}