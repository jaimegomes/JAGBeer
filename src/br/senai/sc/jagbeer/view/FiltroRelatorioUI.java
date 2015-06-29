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

	/**
	 * Create the frame.
	 */
	public FiltroRelatorioUI() {
		setClosable(true);
		setTitle("Configurar Relatório");
		setBounds(550, 180, 493, 163);

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtro de Relatório",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 459, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

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

		JButton btnGerarRelatorio = new JButton("Gerar Relatório");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Date dataIni = null;
				Date dataFim = null;
				try {

					try {
						dataIni = sdf
								.parse(jtfDataInicio.getValue().toString());
						dataFim = sdf.parse(jtfDataFim.getValue().toString());

						if (dataIni.before(dataFim)) {

							RelatorioFaturamentoUI relatorioFaturamentoUI = new RelatorioFaturamentoUI(
									dataIni, dataFim);

							PrincipalUI.getInstancia().getContentPane()
									.add(relatorioFaturamentoUI, 0);
							relatorioFaturamentoUI.setVisible(true);

							dispose();

						} else {
							JOptionPane
									.showMessageDialog(null,
											"A data inicial deve ser menor que a data final.");

						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Data inválida.");
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
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDataInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jtfDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDataFim)
							.addGap(12)
							.addComponent(jtfDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(56))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnGerarRelatorio)
							.addGap(18)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataInicio)
						.addComponent(jtfDataInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfDataFim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataFim))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnGerarRelatorio))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
