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
import br.senai.sc.jagbeer.model.ProdutoTableModel;

public class ConsultaProdutoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private GroupLayout groupLayout;
	private JLabel lblNome;
	private JTextField jtfNome;
	private JLabel lblClassificacao;
	private JComboBox cmbClassificacao;
	private JButton btnAdicionarProduto;
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
					if (!jtfNome.getText().equals("") || jtfNome.getText() != null 
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

		btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProdutoUI cadProdutoUI = new CadastroProdutoUI();
				cadProdutoUI.requestFocus(true);
				cadProdutoUI.setFocusable(true);
				cadProdutoUI.moveToFront();
				getContentPane().add(cadProdutoUI, 0);
				cadProdutoUI.setVisible(true);

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
																.addComponent(
																		lblNome)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jtfNome,
																		GroupLayout.PREFERRED_SIZE,
																		174,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		lblClassificacao)
																.addGap(9)
																.addComponent(
																		cmbClassificacao,
																		GroupLayout.PREFERRED_SIZE,
																		129,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		btnAdicionarProduto))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		scrollPane,
																		GroupLayout.PREFERRED_SIZE,
																		433,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														btnPesquisar,
														Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE,
														115, Short.MAX_VALUE)
												.addComponent(
														btnLimpar,
														GroupLayout.DEFAULT_SIZE,
														115, Short.MAX_VALUE)
												.addComponent(
														btnCancelar,
														GroupLayout.DEFAULT_SIZE,
														115, Short.MAX_VALUE))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING, false)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		lblNome)
																.addComponent(
																		jtfNome,
																		GroupLayout.PREFERRED_SIZE,
																		25,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblClassificacao))
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		cmbClassificacao,
																		GroupLayout.PREFERRED_SIZE,
																		24,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		btnPesquisar)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														btnAdicionarProduto)
												.addComponent(btnLimpar))
								.addPreferredGap(ComponentPlacement.RELATED,
										14, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(btnCancelar)
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														289,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));

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
