package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;

public class FazerPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	// conteudo da janela
	private final JPanel panel;
	private GroupLayout groupLayout;

	// itens do topo
	private JComboBox cbxPedido;
	private JLabel lblPedido;
	private JComboBox cbxCliente;
	private JLabel lblCliente;
	private JLabel lblMesa;
	private JComboBox cbxMesa;

	// itens do grupo Produto
	private JPanel panelProduto;
	private GroupLayout glPanelProduto;
	private JComboBox cbxTipo;
	private JLabel lblTipo;
	private JComboBox cbxProduto;
	private JLabel lblProduto;
	private JComboBox cbxQtde;
	private JLabel lblQtde;
	private JButton btnAdicionarProduto;

	// tabela
	private JTable tableItemPedido;

	// botoes
	private JButton btnCancelar;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FazerPedidoUI frame = new FazerPedidoUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FazerPedidoUI() {

		setTitle("Fazer Pedido");
		setClosable(true);

		// conteudo geral da janela
		panel = new JPanel();

		// Itens do topo
		cbxPedido = new JComboBox<Pedido>();
		lblPedido = new JLabel("Pedido");
		lblCliente = new JLabel("Cliente");
		cbxCliente = new JComboBox<Cliente>();
		lblMesa = new JLabel("Mesa");
		cbxMesa = new JComboBox<Mesa>();

		// itens do grupo Produto
		panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));

		// tabela ItemPedido
		tableItemPedido = new JTable();
		// tableItemPedido.setModel(new ItemPedidoTableModel());

		cbxTipo = new JComboBox();
		lblTipo = new JLabel("Tipo");
		cbxProduto = new JComboBox();
		lblProduto = new JLabel("Produto");
		cbxQtde = new JComboBox();
		lblQtde = new JLabel("Qtde");
		btnAdicionarProduto = new JButton("Adicionar Produto");

		btnCancelar = new JButton("Cancelar");
		btnSalvar = new JButton("Salvar");

		glPanelProduto = new GroupLayout(panelProduto);
		glPanelProduto
				.setHorizontalGroup(glPanelProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								glPanelProduto
										.createSequentialGroup()
										.addGroup(
												glPanelProduto
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																glPanelProduto
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblTipo))
														.addComponent(
																lblProduto))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												glPanelProduto
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																cbxProduto,
																Alignment.LEADING,
																0,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																cbxTipo,
																Alignment.LEADING,
																0, 121,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblQtde)
										.addGap(10)
										.addComponent(cbxQtde,
												GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 23,
												Short.MAX_VALUE)
										.addComponent(btnAdicionarProduto)
										.addContainerGap()));
		glPanelProduto
				.setVerticalGroup(glPanelProduto
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								glPanelProduto
										.createSequentialGroup()
										.addGroup(
												glPanelProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cbxTipo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTipo))
										.addPreferredGap(
												ComponentPlacement.RELATED, 13,
												Short.MAX_VALUE)
										.addGroup(
												glPanelProduto
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cbxProduto,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblProduto)
														.addComponent(
																cbxQtde,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblQtde)
														.addComponent(
																btnAdicionarProduto))
										.addContainerGap()));
		panelProduto.setLayout(glPanelProduto);

		groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(12)
																										.addComponent(
																												lblPedido))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addContainerGap()
																										.addComponent(
																												lblMesa)))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								cbxMesa,
																								0,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								cbxPedido,
																								0,
																								63,
																								Short.MAX_VALUE))
																		.addGap(52)
																		.addComponent(
																				lblCliente,
																				GroupLayout.PREFERRED_SIZE,
																				57,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(2)
																		.addComponent(
																				cbxCliente,
																				0,
																				294,
																				Short.MAX_VALUE))
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnSalvar,
																				GroupLayout.PREFERRED_SIZE,
																				95,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnCancelar))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				tableItemPedido,
																				GroupLayout.DEFAULT_SIZE,
																				529,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				panelProduto,
																				GroupLayout.DEFAULT_SIZE,
																				529,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblPedido))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(17)
																		.addComponent(
																				lblCliente))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				cbxCliente,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				cbxPedido,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblMesa)
														.addComponent(
																cbxMesa,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(15)
										.addComponent(panelProduto,
												GroupLayout.PREFERRED_SIZE, 96,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(tableItemPedido,
												GroupLayout.PREFERRED_SIZE,
												155, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnCancelar)
														.addComponent(btnSalvar))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		getContentPane().setLayout(groupLayout);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
