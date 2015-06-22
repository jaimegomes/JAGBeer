package br.senai.sc.jagbeer.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.EncerrarPedidoTableModel;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.ProdutoPedido;

public class EncerrarPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	Cliente cliente = new Cliente();
	private JTable tableEncerraPedido;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EncerrarPedidoUI(final List<Entidade> listProdutos, JTable table) {

		ProdutoPedido produtoPedido = (ProdutoPedido) listProdutos.get(1);
		Pedido pedido = null;
		Cliente cliente = null;
		double valorTotal = 0;
		List<Entidade> listProdutosPedido = new ArrayList<Entidade>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (Entidade e : listProdutos) {
			ProdutoPedido prodPedido = (ProdutoPedido) e;

			listProdutosPedido.add(prodPedido);
		}

		try {
			pedido = getPedido(produtoPedido);

			cliente = getCliente(pedido.getCliente().getId());

		} catch (Exception e) {
			System.out
					.println("[EncerrarPedidoUI] - Erro ao pegar pedido e cliente. "
							+ e.getMessage());
		}

		setTitle("Encerrar Pedido");
		setClosable(true);
		setBounds(100, 100, 650, 600);

		JPanel panel = new JPanel();

		panel.setBorder(new TitledBorder(null, "Encerrar Pedido",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeCliente.setText(cliente.getNome());

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblPedido = new JLabel("Pedido:");
		lblPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setText("" + pedido.getId());
		lblNumeroPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 608,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(19)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 536,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		lblDdmmyyyy.setText("" + sdf.format(pedido.getDataPedido()));

		JLabel lblData = new JLabel("Data:");

		JButton btnEncerrar = new JButton("Encerrar");

		JButton btnExcluirItem = new JButton("Excluir Item");

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblValor = new JLabel("Valor");
		lblValor.setText("" + valorTotal);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														Alignment.LEADING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		scrollPane,
																		GroupLayout.PREFERRED_SIZE,
																		574,
																		GroupLayout.PREFERRED_SIZE)
																.addContainerGap())
												.addGroup(
														Alignment.LEADING,
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						lblNome,
																						Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblPedido,
																						Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblNumeroPedido)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										289,
																										Short.MAX_VALUE)
																								.addComponent(
																										lblData)
																								.addGap(18)
																								.addComponent(
																										lblDdmmyyyy))
																				.addComponent(
																						lblNomeCliente))
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnExcluirItem)
																.addGap(18)
																.addComponent(
																		btnEncerrar)
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblTotal)
																.addGap(18)
																.addComponent(
																		lblValor)
																.addContainerGap(
																		484,
																		Short.MAX_VALUE)))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblNome,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNomeCliente))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblPedido,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblNumeroPedido,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDdmmyyyy)
												.addComponent(lblData))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 368,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblTotal)
												.addComponent(lblValor))
								.addPreferredGap(ComponentPlacement.RELATED, 9,
										Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnEncerrar)
												.addComponent(btnExcluirItem))
								.addContainerGap()));

		tableEncerraPedido = new JTable();
		try {
			tableEncerraPedido.setModel(new EncerrarPedidoTableModel(
					listProdutos));
		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane.setViewportView(tableEncerraPedido);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	private Pedido getPedido(ProdutoPedido produtoPedido) throws Exception {
		return (Pedido) new PedidoController().getPorId(produtoPedido
				.getIdPedido());
	}

	public Cliente getCliente(int idPedido) throws Exception {

		Pedido pedido = (Pedido) new PedidoController().getPorId(idPedido);

		Cliente cliente = null;
		if (pedido.getCliente() != null) {
			return (Cliente) new ClienteController().getPorId(pedido
					.getCliente().getId());
		}

		return null;

	}

}
