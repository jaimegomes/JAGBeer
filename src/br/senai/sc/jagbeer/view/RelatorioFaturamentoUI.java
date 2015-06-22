package br.senai.sc.jagbeer.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.RelatorioFaturamentoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RelatorioFaturamentoUI extends JInternalFrame {

	private JTable tableRelatorioFaturamento;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private JLabel lblPeriodo;
	private JLabel lblPeriodo1;
	private JLabel lblPeriodo2;
	private JLabel lblAte;
	private JButton btnFechar;
	private JTable table;

	/**
	 * Create the frame.
	 */
	// recebe como parametro table ou lista?
	public RelatorioFaturamentoUI() {
		setTitle("Relatorio Faturamento");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 495, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGap(0, 525, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);
		setClosable(true);
		setTitle("Relatorio Faturamento");
		setBounds(100, 100, 475, 550);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Relatorio Faturamento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(19)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 432,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 494,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(51, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblTotal = new JLabel("Total R$");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblValortotal = new JLabel("ValorTotal");
		lblValortotal.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblPeriodo2 = new JLabel("Periodo2");

		JLabel lblAte = new JLabel("Ate");

		JLabel lblPeriodo1 = new JLabel("Periodo1");

		JLabel lblPeriodo = new JLabel("Periodo:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValortotal)
							.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
							.addComponent(btnFechar))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblPeriodo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblPeriodo1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblAte)
								.addGap(18)
								.addComponent(lblPeriodo2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPeriodo2)
						.addComponent(lblPeriodo)
						.addComponent(lblPeriodo1)
						.addComponent(lblAte))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotal)
							.addComponent(lblValortotal))
						.addComponent(btnFechar))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num. Pedido", "Data", "Valor"
			}
		));
		scrollPane.setViewportView(table);

		tableRelatorioFaturamento = new JTable();
		try {
			tableRelatorioFaturamento
					.setModel(new RelatorioFaturamentoTableModel(
							new PedidoController().listar()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		// acho que vai precisar do metodo..
		// public List<Entidade> getListPedido(Date dataInicio, Date dataFim)
		// throws Exception {
		// // return new PedidoController().getPorData(dataInicio, dataFim);
		// //
		// // }
	}
}
