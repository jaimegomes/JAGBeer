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
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.RelatorioPedidoTableModel;
import javax.swing.SwingConstants;

/**
 * Classe que cont�m a tela de relat�rio de faturamento
 * 
 * @author Jaime Gomes
 *
 */
public class RelatorioPedidosUI extends JInternalFrame {

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
	private JLabel lblValorTotal;
	private JLabel label;
	private double valorTotal = 0;
	private JLabel lblTotalDePedidos;
	private JLabel lblQtdPedidos;

	public RelatorioPedidosUI(Date dataInicio, Date dataFinal, int status) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		List<Entidade> listPedidos = new ArrayList<Entidade>();

		try {
			listPedidos = new PedidoController().getPorDataStatus(dataInicio,
					dataFinal, status);

			for (Entidade e : listPedidos) {
				Pedido pedido = (Pedido) e;

				valorTotal += pedido.getValor();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Relat�rio Pedido");
		setClosable(true);
		setBounds(660, 0, 650, 600);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		lblRelatrioDeFaturamento = new JLabel("Relat\u00F3rio de Pedidos");
		lblRelatrioDeFaturamento.setFont(new Font("Dialog", Font.BOLD, 24));

		lblPeriodo = new JLabel("Per�odo de");

		lblDataInicio = new JLabel(sdf.format(dataInicio));

		lblAte = new JLabel("at�");

		lblDataFim = new JLabel(sdf.format(dataFinal));

		lblValorTotal = new JLabel("Valor Total ");

		label = new JLabel("R$ " + valorTotal);
		label.setHorizontalAlignment(SwingConstants.RIGHT);

		lblTotalDePedidos = new JLabel("Total de Pedidos Listados:");

		lblQtdPedidos = new JLabel("" + listPedidos.size());
		lblQtdPedidos.setHorizontalAlignment(SwingConstants.RIGHT);

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblValorTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
							.addComponent(lblTotalDePedidos)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblQtdPedidos, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRelatrioDeFaturamento, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPeriodo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDataInicio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAte)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDataFim, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblRelatrioDeFaturamento, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataInicio)
						.addComponent(lblPeriodo)
						.addComponent(lblAte)
						.addComponent(lblDataFim))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValorTotal)
						.addComponent(label)
						.addComponent(lblQtdPedidos)
						.addComponent(lblTotalDePedidos))
					.addGap(36))
		);

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
		table.setModel(new RelatorioPedidoTableModel(listPedidos));
		scrollPane.setViewportView(table);

		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
