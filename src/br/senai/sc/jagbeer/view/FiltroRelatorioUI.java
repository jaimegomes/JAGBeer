package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Pedido;

public class FiltroRelatorioUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField jtfDataInicio;
	private JFormattedTextField jtfDataFim;
	private JTable tableRelatorioFaturamento;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public FiltroRelatorioUI() {
		setClosable(true);
		setTitle("Configurar Relatorio");
		setBounds(100, 100, 439, 152);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtro de Relatório",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, Short.MAX_VALUE)
					.addContainerGap())
		);

		JLabel lblDataInicio = new JLabel("De:");

		MaskFormatter mascara;
		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');

			jtfDataInicio = new JFormattedTextField(mascara);
			jtfDataFim = new JFormattedTextField(mascara);

		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		jtfDataInicio.setColumns(10);
		jtfDataInicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";

				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		JLabel lblDataFim = new JLabel("at\u00E9");

		jtfDataFim.setColumns(10);
		jtfDataFim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";

				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		JButton btnGerarRelatorio = new JButton("Gerar Relatorio");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Entidade> listPedidos = new ArrayList<Entidade>();
				RelatorioFaturamentoUI relatorioFaturamentoUI;

				try {

					listPedidos = (List<Entidade>) new PedidoController()
							.getPorData(sdf.parse(jtfDataInicio.getText()),
									sdf.parse(jtfDataFim.getText()));

					System.out.println(listPedidos.size());

					relatorioFaturamentoUI = new RelatorioFaturamentoUI(
							tableRelatorioFaturamento, sdf.parse(jtfDataInicio
									.getText()),
							sdf.parse(jtfDataFim.getText()));
					PrincipalUI.getInstancia().getContentPane()
							.add(relatorioFaturamentoUI, 0);
					relatorioFaturamentoUI.setVisible(true);

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfDataInicio.setText("__/__/____");
				jtfDataFim.setText("__/__/____");
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfDataInicio, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDataFim)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfDataFim, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnGerarRelatorio)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
					.addGap(36))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataInicio)
						.addComponent(jtfDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataFim)
						.addComponent(jtfDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGerarRelatorio)
						.addComponent(btnCancelar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

}
