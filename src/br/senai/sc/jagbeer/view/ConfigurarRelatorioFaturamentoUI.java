package br.senai.sc.jagbeer.view;

import java.awt.EventQueue;

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
import javax.swing.JTextField;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.RelatorioFaturamentoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ConfigurarRelatorioFaturamentoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfDataInicio;
	private JTextField jtfDataFim;
	private JTable tableRelatorioFaturamento;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public ConfigurarRelatorioFaturamentoUI() {
		setClosable(true);
		setTitle("Configurar Relatorio");
		setBounds(100, 100, 380, 210);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Relat\u00F3rio de Faturamento",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 354,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 162,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblDataInicio = new JLabel("Data Inicio:");

		jtfDataInicio = new JTextField();
		jtfDataInicio.setColumns(10);
		jtfDataInicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321 -/";

				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		JLabel lblDataFim = new JLabel("Data Fim");

		jtfDataFim = new JTextField();
		jtfDataFim.setColumns(10);
		jtfDataFim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321 -/";

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
				Pedido pedido = null;

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
				jtfDataInicio.setText("");
				jtfDataFim.setText("");
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblDataFim,
																										GroupLayout.PREFERRED_SIZE,
																										55,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jtfDataFim))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblDataInicio)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										jtfDataInicio,
																										GroupLayout.PREFERRED_SIZE,
																										112,
																										GroupLayout.PREFERRED_SIZE)))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						btnLimpar,
																						GroupLayout.PREFERRED_SIZE,
																						107,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						btnGerarRelatorio)
																				.addComponent(
																						btnCancelar,
																						GroupLayout.PREFERRED_SIZE,
																						107,
																						GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(90)
																.addComponent(
																		lblDdmmyyyy)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(11)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblDataInicio)
												.addComponent(
														jtfDataInicio,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnGerarRelatorio))
								.addGap(3)
								.addComponent(lblDdmmyyyy)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfDataFim,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDataFim)
												.addComponent(btnLimpar))
								.addPreferredGap(ComponentPlacement.RELATED,
										26, Short.MAX_VALUE)
								.addComponent(btnCancelar).addContainerGap()));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getJtfDataInicio() {
		return jtfDataInicio;
	}

	public void setJtfDataInicio(JTextField jtfDataInicio) {
		this.jtfDataInicio = jtfDataInicio;
	}

	public JTextField getJtfDataFim() {
		return jtfDataFim;
	}

	public void setJtfDataFim(JTextField jtfDataFim) {
		this.jtfDataFim = jtfDataFim;
	}
}
