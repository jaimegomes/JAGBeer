package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.ItemPedido;
import br.senai.sc.jagbeer.model.ItemPedidoTableModel;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.Produto;

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
	private JTable table;

	public FazerPedidoUI() {

		setTitle("Fazer Pedido");
		setClosable(true);
		setBounds(100, 0, 535, 472);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

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

		lblPedido = new JLabel("Pedido:");

		cmbPedido = new JComboBox();
		cmbPedido.setMaximumRowCount(8);

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

		try {
			List<Entidade> listMesas = new PedidoController()
					.getPedidosAbertos();

			if (listMesas.size() > 0) {
				for (Entidade e : listMesas) {
					Mesa mesa = (Mesa) e;
					cmbMesa.addItem(mesa.getNumeroMesa());

				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
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

		lblQuantidade = new JLabel("Quantidade:");

		spinnerQtde = new JSpinner();
		spinnerQtde.setModel(new SpinnerNumberModel(new Integer(0),
				new Integer(0), null, new Integer(1)));

		btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ItemPedido itemPedido = new ItemPedido((Produto) cmbProduto
						.getSelectedItem(), Integer.parseInt(spinnerQtde
						.getValue().toString()));

				listItensPedido.add(itemPedido);
			}
		});

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 496,
								Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 433,
								Short.MAX_VALUE)));

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
																		.addGap(53)
																		.addComponent(
																				lblMesa)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cmbMesa,
																				GroupLayout.PREFERRED_SIZE,
																				76,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayoutProduto
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnSalvar,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnCancelar))
														.addGroup(
																groupLayoutProduto
																		.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																		.addComponent(
																				scrollPane,
																				Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				panelProduto,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				495,
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
														.addComponent(
																cmbMesa,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblMesa)
														.addComponent(lblPedido)
														.addComponent(
																cmbPedido,
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
																Alignment.LEADING,
																false)
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
														.addComponent(
																btnAdicionarProduto,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(65, Short.MAX_VALUE)));
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
