package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Produto;

public class CadastroProdutoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private GroupLayout groupLayout;
	private JTextField jtfNome;
	private JTextField jtfValorCusto;
	private JTextField jtfValor;
	private JLabel lblNome;
	private JLabel lblValor;
	private JLabel lblValorCusto;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JComboBox cmbClassificacao;
	private JLabel lblClassificao;
	private GroupLayout gl_panel;

	/**
	 * Create the frame.
	 */
	public CadastroProdutoUI() {

		setTitle("Cadastro de Produto");
		setClosable(true);
		setBounds(100, 100, 628, 247);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Cadastro Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 594,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(13, Short.MAX_VALUE)));

		lblNome = new JLabel("Nome:");

		jtfNome = new JTextField();
		jtfNome.setHorizontalAlignment(SwingConstants.LEFT);
		jtfNome.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produto produto = new Produto();

				produto.setNome(jtfNome.getText());
				produto.setPrecoVenda(Double.parseDouble(jtfValor.getText()));
				produto.setPrecoCusto(Double.parseDouble(jtfValorCusto
						.getText()));

				if (cmbClassificacao.getSelectedIndex() == 0)
					produto.setClassificacao("Alimento");

				else if (cmbClassificacao.getSelectedIndex() == 1)
					produto.setClassificacao("Bebida");

				try {
					new ProdutoController().salvar(produto);
					JOptionPane.showMessageDialog(null,
							"Produto Cadastrado com Sucesso!");
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblValor = new JLabel("Valor:");

		lblValorCusto = new JLabel("Valor Custo:");

		jtfValorCusto = new JTextField();
		jtfValorCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		jtfValorCusto.setColumns(10);

		jtfValor = new JTextField();
		jtfValor.setHorizontalAlignment(SwingConstants.RIGHT);
		jtfValor.setColumns(10);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfNome.setText("");
				jtfValor.setText("");
				jtfValorCusto.setText("");

			}
		});

		cmbClassificacao = new JComboBox();
		cmbClassificacao.setModel(new DefaultComboBoxModel(new String[] { "",
				"Alimento", "Bebida" }));
		cmbClassificacao.setMaximumRowCount(3);

		lblClassificao = new JLabel("Classificação:");
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
																				Alignment.TRAILING)
																				.addComponent(
																						lblNome)
																				.addComponent(
																						lblValor)
																				.addComponent(
																						lblValorCusto)
																				.addComponent(
																						lblClassificao))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						cmbClassificacao,
																						GroupLayout.PREFERRED_SIZE,
																						116,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jtfNome,
																						GroupLayout.DEFAULT_SIZE,
																						362,
																						Short.MAX_VALUE)
																				.addGroup(
																						gl_panel.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																								.addComponent(
																										jtfValor,
																										Alignment.LEADING)
																								.addComponent(
																										jtfValorCusto,
																										Alignment.LEADING)))
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										btnSalvar,
																										GroupLayout.DEFAULT_SIZE,
																										99,
																										Short.MAX_VALUE))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										btnLimpar,
																										GroupLayout.DEFAULT_SIZE,
																										99,
																										Short.MAX_VALUE))))
												.addComponent(btnCancelar,
														Alignment.TRAILING))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNome)
												.addComponent(
														jtfNome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSalvar))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblValor)
												.addComponent(
														jtfValor,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnLimpar))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfValorCusto,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblValorCusto))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														cmbClassificacao,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblClassificao))
								.addGap(18).addComponent(btnCancelar)
								.addGap(13)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
