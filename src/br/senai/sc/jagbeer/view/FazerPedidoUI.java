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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	private JLabel lblCliente;
	private JComboBox cmbCliente;
	private JPanel panelProduto;
	private JComboBox cmbClassificacao;
	private JLabel lblClassificao;
	private JLabel lblProduto;
	private JComboBox cmbProduto;
	private JLabel lblQuantidade;
	private JTable table;
	private JComboBox cmbMesa;
	private JLabel lblMesa;
	private JSpinner spinnerQtde;
	private JButton btnAdicionarProduto;
	private GroupLayout gl_panelProduto;

	public FazerPedidoUI() {

		setTitle("Fazer Pedido");
		setClosable(true);
		setBounds(100, 100, 497, 479);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 464,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(142, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cmbPedido = new JComboBox();
		cmbPedido.setMaximumRowCount(2);

		lblPedido = new JLabel("Pedido:");

		lblCliente = new JLabel("Cliente:");

		cmbCliente = new JComboBox();

		panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		lblMesa = new JLabel("Mesa:");

		cmbMesa = new JComboBox();

		table = new JTable();
		groupLayoutProduto = new GroupLayout(panel);
		groupLayoutProduto
				.setHorizontalGroup(groupLayoutProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								groupLayoutProduto
										.createSequentialGroup()
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayoutProduto
																		.createSequentialGroup()
																		.addGap(194)
																		.addComponent(
																				btnSalvar,
																				GroupLayout.PREFERRED_SIZE,
																				126,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnCancelar,
																				GroupLayout.DEFAULT_SIZE,
																				133,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayoutProduto
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayoutProduto
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								table,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								436,
																								Short.MAX_VALUE)
																						.addComponent(
																								panelProduto,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								482,
																								Short.MAX_VALUE)
																						.addGroup(
																								groupLayoutProduto
																										.createSequentialGroup()
																										.addGroup(
																												groupLayoutProduto
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblPedido)
																														.addComponent(
																																lblMesa))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												groupLayoutProduto
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																cmbMesa,
																																Alignment.LEADING,
																																0,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																cmbPedido,
																																Alignment.LEADING,
																																0,
																																71,
																																Short.MAX_VALUE))
																										.addGap(30)
																										.addComponent(
																												lblCliente)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												cmbCliente,
																												0,
																												248,
																												Short.MAX_VALUE)))))
										.addGap(96)));
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
														.addComponent(
																lblCliente)
														.addComponent(
																cmbCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cmbMesa,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMesa))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(panelProduto,
												GroupLayout.PREFERRED_SIZE,
												107, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(table,
												GroupLayout.PREFERRED_SIZE,
												183, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayoutProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnCancelar)
														.addComponent(btnSalvar))
										.addContainerGap()));

		cmbClassificacao = new JComboBox();
		cmbClassificacao.setModel(new DefaultComboBoxModel(new String[] { "",
				"Alimentos", "Bebidas" }));
		cmbClassificacao.setSelectedIndex(0);
		cmbClassificacao.setMaximumRowCount(3);

		lblClassificao = new JLabel("Classificação:");

		lblProduto = new JLabel("Produto:");

		cmbProduto = new JComboBox();

		lblQuantidade = new JLabel("Quantidade:");

		spinnerQtde = new JSpinner();
		spinnerQtde.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));

		btnAdicionarProduto = new JButton("Adicionar Produto");
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
																				42,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnAdicionarProduto))
										.addContainerGap(77, Short.MAX_VALUE)));
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
																btnAdicionarProduto))
										.addContainerGap(43, Short.MAX_VALUE)));
		panelProduto.setLayout(gl_panelProduto);

		panel.setLayout(groupLayoutProduto);
		getContentPane().setLayout(groupLayout);

	}
}
