package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JLabel lblStatus;
	private JComboBox cmbStatus;

	/**
	 * Create the frame.
	 */
	public FiltroRelatorioUI(final String flag) {
		setClosable(true);
		setTitle("Configurar Relatório");
		setBounds(550, 180, 441, 197);

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

		if (flag.equals("ped")) {

		}

		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date dataIni = null;
				Date dataFim = null;

				if (flag.equals("ped")) {

					try {
						dataIni = sdf
								.parse(jtfDataInicio.getValue().toString());
						dataFim = sdf.parse(jtfDataFim.getValue().toString());

						if (dataIni.before(dataFim)) {

							if (cmbStatus.getSelectedIndex() == 1) {

								RelatorioPedidosUI relatorioFaturamentoUI = new RelatorioPedidosUI(
										dataIni, dataFim, 1);

								PrincipalUI.getInstancia().getContentPane()
										.add(relatorioFaturamentoUI, 0);
								relatorioFaturamentoUI.setVisible(true);

							} else if (cmbStatus.getSelectedIndex() == 2) {

								RelatorioPedidosUI relatorioFaturamentoUI = new RelatorioPedidosUI(
										dataIni, dataFim, 0);

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

					} catch (Exception e2) {
						JOptionPane
								.showMessageDialog(null,
										"Data inválida, favor selecionar um período válido.");

					}

					if (dataIni.before(dataFim) || dataIni.equals(dataFim)) {

						RelatorioProdutosMaisVendidosUI relProdMaisVend;
						try {
							relProdMaisVend = new RelatorioProdutosMaisVendidosUI(
									dataIni, dataFim);

							PrincipalUI.getInstancia().getContentPane()
									.add(relProdMaisVend, 0);
							relProdMaisVend.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}

					} else {
						JOptionPane
								.showMessageDialog(null,
										"A data inicial deve ser menor ou igual a data final.");
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

		lblStatus = new JLabel("Status");
		cmbStatus = new JComboBox();
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] { "",
				"Abertos", "Encerrados" }));
		if (flag.equals("ped")) {

			lblStatus.setVisible(true);
			cmbStatus.setVisible(true);

		} else {
			lblStatus.setVisible(false);
			cmbStatus.setVisible(false);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtro de Relatório",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 407,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 141,
								Short.MAX_VALUE).addContainerGap()));

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
														gl_panel.createSequentialGroup()
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
																										ComponentPlacement.RELATED))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblStatus)
																								.addGap(42)))
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						cmbStatus,
																						0,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jtfDataInicio)))
												.addComponent(btnGerarRelatorio))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
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
												.addComponent(
														btnCancelar,
														GroupLayout.PREFERRED_SIZE,
														143,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
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
												Alignment.TRAILING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.BASELINE)
																				.addComponent(
																						lblStatus)
																				.addComponent(
																						cmbStatus,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(34))
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		btnCancelar)
																.addComponent(
																		btnGerarRelatorio)))));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
