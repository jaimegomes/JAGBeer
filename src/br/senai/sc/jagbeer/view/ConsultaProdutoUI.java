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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

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
		setBounds(100, 100, 652, 449);

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
											"N�o existem produtos com este nome cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campo classifica��o preenchido
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.getPorClassificacao(classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(null,
											"N�o existem produtos com esta classifica��o cadastrados no banco de dados.");

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campos nome e classifica��o preenchidos
					else if (cmbClassificacao.getSelectedIndex() > 0
							&& !jtfNome.getText().isEmpty()) {

						listProduto = new ProdutoController()
								.buscaPorNomeClassificacao(jtfNome.getText(),
										classificacao);

						if (listProduto.size() == 0)
							JOptionPane
									.showMessageDialog(
											null,
											"N�o existem produtos com este nome e classifica��o cadastrados no banco de dados.");

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

				try {

					ProdutoController controller = new ProdutoController();

					int linhaSelecionada = table.getSelectedRow();

					if (linhaSelecionada > -1) {

						String nomeProduto = table.getValueAt(linhaSelecionada,
								0).toString();

						Double precoVenda = Double.parseDouble(table
								.getValueAt(linhaSelecionada, 2).toString());

						String classificacao = table.getValueAt(
								linhaSelecionada, 3).toString();

						Produto produtoExcluir = (Produto) controller
								.buscaCompleta(nomeProduto, precoVenda,
										classificacao);

						controller.excluir(produtoExcluir);
						JOptionPane.showMessageDialog(null,
								"Produto exclu�do com sucesso.");

						table.setModel(new ProdutoTableModel(controller
								.listar()));

					} else {
						JOptionPane.showMessageDialog(null,
								"Selecione um produto.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		lblClassificacao = new JLabel("Classifica��o:");

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

					PrincipalUI.obterInstancia().getContentPane()
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
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						lblClassificacao)
																				.addComponent(
																						lblNome))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						jtfNome,
																						GroupLayout.PREFERRED_SIZE,
																						173,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmbClassificacao,
																						GroupLayout.PREFERRED_SIZE,
																						127,
																						GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						btnPesquisar,
																						GroupLayout.DEFAULT_SIZE,
																						133,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnLimpar,
																						GroupLayout.DEFAULT_SIZE,
																						133,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						btnExcluir,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnEditarInserir,
																						GroupLayout.DEFAULT_SIZE,
																						144,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		23,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(10)
																.addComponent(
																		scrollPane,
																		GroupLayout.DEFAULT_SIZE,
																		584,
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
												.addComponent(btnPesquisar)
												.addComponent(btnEditarInserir))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblClassificacao)
												.addComponent(
														cmbClassificacao,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnLimpar)
												.addComponent(btnExcluir))
								.addGap(18)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 282,
										Short.MAX_VALUE)));

		try {
			table = new JTable(new ProdutoTableModel(listProduto));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
