package br.senai.sc.jagbeer.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.FazerPedidoTableModel;
import br.senai.sc.jagbeer.model.Pedido;

public class NotaFiscalUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tableNotaFiscal;
	private JLabel lblNomeCliente;
	private JLabel lblNome;
	private JLabel lblPedido;
	private JLabel lblNumeroPedido;
	private JScrollPane scrollPane;
	private JLabel lblDdmmyyyy;
	private JLabel lblData;
	private JLabel lblTotal;
	private JLabel lblValor;
	private GroupLayout groupLayout;
	private GroupLayout gl_panel;
	private JPanel panel;
	private List<Entidade> listProdutos = new ArrayList<Entidade>();
	private JLabel lblTotalDeItens;
	private JLabel lblQtdItens;

	/**
	 * Create the frame.
	 */
	public NotaFiscalUI(final Pedido pedido) {

		try {
			listProdutos = new ProdutoPedidoController().getPorIdPedido(pedido
					.getId());

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		setTitle("Nota Fiscal");
		setClosable(true);
		setBounds(400, 50, 650, 600);

		panel = new JPanel();

		panel.setBorder(new TitledBorder(null, "Encerrar Pedido",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeCliente.setText(pedido.getCliente().getNome());

		lblNome = new JLabel("Cliente:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPedido = new JLabel("Pedido:");
		lblPedido.setFont(new Font("Dialog", Font.BOLD, 15));

		lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setText("" + pedido.getId());
		lblNumeroPedido.setFont(new Font("Dialog", Font.BOLD, 15));

		scrollPane = new JScrollPane();

		lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		lblDdmmyyyy.setText("" + sdf.format(pedido.getDataPedido()));

		lblData = new JLabel("Data:");

		lblTotal = new JLabel("Valor Total: R$");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 15));

		lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Dialog", Font.BOLD, 15));

		try {
			lblValor.setText("" + pedido.getValor());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JLabel lblNotaFiscal = new JLabel("NOTA FISCAL");
		lblNotaFiscal.setFont(new Font("Dialog", Font.BOLD, 24));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout.createSequentialGroup()
								.addContainerGap(235, Short.MAX_VALUE)
								.addComponent(lblNotaFiscal).addGap(228))
				.addGroup(
						Alignment.LEADING,
						groupLayout
								.createSequentialGroup()
								.addGap(18)
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 608,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(14, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNotaFiscal)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		lblTotalDeItens = new JLabel("Total de itens:");

		lblQtdItens = new JLabel("" + listProdutos.size());

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPedido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumeroPedido)
									.addGap(16)))
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(lblNomeCliente)
									.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblData)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addComponent(lblDdmmyyyy))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
							.addComponent(lblTotalDeItens)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQtdItens, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeCliente))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPedido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroPedido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDdmmyyyy)
						.addComponent(lblData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(lblTotalDeItens)
						.addComponent(lblValor)
						.addComponent(lblQtdItens))
					.addContainerGap(15, Short.MAX_VALUE))
		);

		tableNotaFiscal = new JTable();
		tableNotaFiscal.setCellSelectionEnabled(false);
		try {
			tableNotaFiscal.setModel(new FazerPedidoTableModel(listProdutos));
		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane.setViewportView(tableNotaFiscal);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public void setLblValor(JLabel lblValor) {
		this.lblValor = lblValor;
	}
}
