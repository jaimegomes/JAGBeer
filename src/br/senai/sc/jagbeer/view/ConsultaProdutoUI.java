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
	private JButton btnCancelar;
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

					System.out.println("jtfNome.getText(): "
							+ jtfNome.getText());

					// tudo em branco
					if ((cmbClassificacao.getSelectedIndex() == -1 || cmbClassificacao
							.getSelectedIndex() == 0)
							&& (jtfNome.getText() == null || jtfNome.getText()
									.equals(""))) {

						listProduto = new ProdutoController().listar();
						table.setModel(new ProdutoTableModel(listProduto));
					}

					// campo nome preenchido
					if (!jtfNome.getText().equals("")
							|| jtfNome.getText() != null
							&& (cmbClassificacao.getSelectedIndex() == -1 || cmbClassificacao
									.getSelectedIndex() == 0)) {

						listProduto = new ProdutoController()
								.getPorNome(jtfNome.getText());

						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campo classificação preenchido
					if (cmbClassificacao.getSelectedIndex() > 0
							&& (jtfNome.getText() == null || jtfNome.getText()
									.equals(""))) {

						listProduto = new ProdutoController()
								.getPorClassificacao(classificacao);
						table.setModel(new ProdutoTableModel(listProduto));

					}

					// campos nome e classificação preenchidos
					if (cmbClassificacao.getSelectedIndex() > 0
							&& (jtfNome.getText() != null || !jtfNome.getText()
									.equals(""))) {

						listProduto = new ProdutoController().buscaCompleta();
						table.setModel(new ProdutoTableModel(listProduto));

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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

				Produto produtoEditar;
				try {
					produtoEditar = new ProdutoTableModel(
							new ProdutoController().listar()).get(table
							.getSelectedRow());
					CadastroProdutoUI cadProdutoUI = new CadastroProdutoUI(
							produtoEditar, table);

					getContentPane().add(cadProdutoUI, 0);
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
																						173,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnLimpar,
																						GroupLayout.DEFAULT_SIZE,
																						173,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING)
																				.addComponent(
																						btnEditarInserir,
																						GroupLayout.PREFERRED_SIZE,
																						167,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						btnCancelar,
																						GroupLayout.DEFAULT_SIZE,
																						145,
																						Short.MAX_VALUE)))
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
												.addComponent(btnCancelar))
								.addGap(18)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 300,
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
