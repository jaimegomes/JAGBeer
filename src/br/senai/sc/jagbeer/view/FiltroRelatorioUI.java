package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private JRadioButton rbtnAberto;
	private JRadioButton rbtnEncerrado;

	/**
	 * Create the frame.
	 */
	public FiltroRelatorioUI(final String flag) {
		setClosable(true);
		setTitle("Configurar Relatório");
		setBounds(550, 180, 389, 198);

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		JLabel lblDataInicio = new JLabel("Período de");

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

		JLabel lblDataFim = new JLabel("até");

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

		if (flag.equals("fat")) {
			rbtnAberto = new JRadioButton("Aberto");
			rbtnAberto.setSelected(true);

			rbtnEncerrado = new JRadioButton("Encerrado");

			rbtnAberto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					rbtnEncerrado.setSelected(false);
				}
			});

			rbtnEncerrado.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					rbtnAberto.setSelected(false);
				}
			});

		}

		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date dataIni = null;
				Date dataFim = null;

				if (flag.equals("fat")) {

					try {
						dataIni = sdf
								.parse(jtfDataInicio.getValue().toString());
						dataFim = sdf.parse(jtfDataFim.getValue().toString());

						if (dataIni.before(dataFim)) {

							if (rbtnAberto.isSelected()) {

								RelatorioFaturamentoUI relatorioFaturamentoUI = new RelatorioFaturamentoUI(
										dataIni, dataFim, 1);

								PrincipalUI.getInstancia().getContentPane()
										.add(relatorioFaturamentoUI, 0);
								relatorioFaturamentoUI.setVisible(true);

							} else if (rbtnEncerrado.isSelected()) {

								RelatorioFaturamentoUI relatorioFaturamentoUI = new RelatorioFaturamentoUI(
										dataIni, dataFim, 1);

								PrincipalUI.getInstancia().getContentPane()
										.add(relatorioFaturamentoUI, 0);
								relatorioFaturamentoUI.setVisible(true);

							}

						} else {
							JOptionPane
									.showMessageDialog(null,
											"A data inicial deve ser menor que a data final.");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Data inválida.");
					}
				} else if (flag.equals("prod")) {

					try {
						dataIni = sdf
								.parse(jtfDataInicio.getValue().toString());
						dataFim = sdf.parse(jtfDataFim.getValue().toString());

						if (dataIni.before(dataFim)) {

							RelatorioProdutosMaisVendidosUI relProdMaisVend = new RelatorioProdutosMaisVendidosUI(
									dataIni, dataFim);

							PrincipalUI.getInstancia().getContentPane()
									.add(relProdMaisVend, 0);
							relProdMaisVend.setVisible(true);

						} else {
							JOptionPane
									.showMessageDialog(null,
											"A data inicial deve ser menor que a data final.");
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Data inválida.");

					}
					dispose();

				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtro de Relatório",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 351,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 138,
								Short.MAX_VALUE).addContainerGap()));

		JRadioButton rdbtnAberto = new JRadioButton("Aberto");

		JRadioButton rdbtnEncerrado = new JRadioButton("Encerrado");

		JLabel lblStatus = new JLabel("Status");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
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
																		lblStatus)
																.addGap(18)
																.addComponent(
																		rdbtnAberto))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblDataInicio,
																		GroupLayout.DEFAULT_SIZE,
																		58,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jtfDataInicio,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(btnGerarRelatorio))
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
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(rdbtnEncerrado)
												.addComponent(
														btnCancelar,
														GroupLayout.PREFERRED_SIZE,
														105,
														GroupLayout.PREFERRED_SIZE))
								.addGap(126)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblDataInicio)
												.addComponent(
														jtfDataInicio,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataFim)
												.addComponent(
														jtfDataFim,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblStatus)
												.addComponent(rdbtnAberto)
												.addComponent(rdbtnEncerrado))
								.addGap(9)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnGerarRelatorio)
												.addComponent(btnCancelar))));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
