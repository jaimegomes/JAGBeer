package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

/**
 * Classe que contém a tela de filtro de relatório
 * 
 * @author Jaime Gomes
 *
 */
public class FiltroRelatorioUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField jtfDataInicio;
	private JFormattedTextField jtfDataFim;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Create the frame.
	 */
	public FiltroRelatorioUI() {
		setClosable(true);
		setTitle("Configurar Relatorio");
		setBounds(100, 100, 438, 163);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtro de Relatório",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 397,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(44, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 110,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblDataInicio = new JLabel("Per\u00EDodo de");

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

				try {

					String dataInicio = jtfDataInicio.getValue().toString();
					String dataFinal = jtfDataFim.getValue().toString();

					if (dataInicio != null && dataFinal != null) {

						RelatorioFaturamentoUI relatorioFaturamentoUI = new RelatorioFaturamentoUI(
								sdf.parse(dataInicio), sdf.parse(dataFinal));

						PrincipalUI.getInstancia().getContentPane()
								.add(relatorioFaturamentoUI, 0);
						relatorioFaturamentoUI.setVisible(true);

						dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"Favor selecionar uma data válida");

					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblDataInicio,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jtfDataInicio,
																		GroupLayout.PREFERRED_SIZE,
																		100,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														btnGerarRelatorio,
														Alignment.TRAILING))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblDataFim)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		jtfDataFim,
																		GroupLayout.PREFERRED_SIZE,
																		102,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														btnCancelar,
														GroupLayout.PREFERRED_SIZE,
														141,
														GroupLayout.PREFERRED_SIZE))
								.addGap(94)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		lblDataInicio)
																.addComponent(
																		jtfDataInicio,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		lblDataFim))
												.addComponent(
														jtfDataFim,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(btnGerarRelatorio)
												.addComponent(btnCancelar))
								.addGap(14)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
