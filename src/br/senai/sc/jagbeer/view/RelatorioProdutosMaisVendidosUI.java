package br.senai.sc.jagbeer.view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.dao.ProdutoPedidoDAO;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.ProdutoPedido;
import br.senai.sc.jagbeer.model.ProdutosMaisVendidosTableModel;

/**
 * Classe que contém a tela de relatório de faturamento
 * 
 * @author Jaime Gomes
 *
 */
public class RelatorioProdutosMaisVendidosUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblRelatrioDeFaturamento;
	private JPanel panel;
	private GroupLayout groupLayout;
	private GroupLayout gl_panel;
	private JLabel lblPeriodo;
	private JLabel lblDataInicio;
	private JLabel lblAte;
	private JLabel lblDataFim;
	private JTable table;
	private JScrollPane scrollPane;
	private double valorTotal = 0;
	private JLabel lblTotalDePedidos;
	private JLabel lblQtdPedidos;

	private List<Entidade> listProdutoPedido = new ArrayList<Entidade>();
	private List<Entidade> listPedidos = new ArrayList<Entidade>();

	public RelatorioProdutosMaisVendidosUI(Date dataInicio, Date dataFinal) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			listPedidos = new PedidoController().getPorData(dataInicio,
					dataFinal);
			for (Entidade entidade : listPedidos) {
				Pedido pedido = (Pedido) entidade;
				ProdutoPedido produtoPedido = (ProdutoPedido) new ProdutoPedidoController()
						.getPorIdPedido(pedido.getId());
				listProdutoPedido.add(produtoPedido);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Relatório Mais Vendidos");
		setClosable(true);
		setBounds(660, 0, 650, 600);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		lblRelatrioDeFaturamento = new JLabel(
				"Relat\u00F3rio Produtos Mais Vendidos");
		lblRelatrioDeFaturamento.setFont(new Font("Dialog", Font.BOLD, 24));

		lblPeriodo = new JLabel("Período de");

		lblDataInicio = new JLabel(sdf.format(dataInicio));

		lblAte = new JLabel("até");

		lblDataFim = new JLabel(sdf.format(dataFinal));

		lblTotalDePedidos = new JLabel("Total de Pedidos Listados:");

		lblQtdPedidos = new JLabel("" + listPedidos.size());
		lblQtdPedidos.setHorizontalAlignment(SwingConstants.RIGHT);

		groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																606,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblTotalDePedidos)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblQtdPedidos,
																				GroupLayout.PREFERRED_SIZE,
																				41,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(144)
																		.addComponent(
																				lblPeriodo)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDataInicio)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblAte)
																		.addGap(18)
																		.addComponent(
																				lblDataFim,
																				GroupLayout.PREFERRED_SIZE,
																				81,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(89, Short.MAX_VALUE)
										.addComponent(lblRelatrioDeFaturamento,
												GroupLayout.PREFERRED_SIZE,
												410, GroupLayout.PREFERRED_SIZE)
										.addGap(135)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(lblRelatrioDeFaturamento,
												GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDataFim)
														.addComponent(lblAte)
														.addComponent(
																lblDataInicio)
														.addComponent(
																lblPeriodo))
										.addGap(18)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												442, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblQtdPedidos)
														.addComponent(
																lblTotalDePedidos))
										.addGap(36)));

		scrollPane = new JScrollPane();

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								440, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(35, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new ProdutosMaisVendidosTableModel(listProdutoPedido));
		scrollPane.setViewportView(table);

		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
