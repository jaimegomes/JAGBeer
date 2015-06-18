package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ClienteTableModel;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.MesaTableModel;



public class ConsultaMesaUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField numeroMesa;
	private JTable tableMesa;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public ConsultaMesaUI() {
		setTitle("Consulta Mesa");
		setBounds(100, 100, 510, 360);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consulta Mesa",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 482,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(12, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(16, Short.MAX_VALUE)));

		JLabel lblNome = new JLabel("Numero Mesa:");

		numeroMesa = new JTextField();
		numeroMesa.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
									
					//tableMesa.setModel(new MesaTableModel(
							//new MesaController().getPorId(numeroMesa).getText()));

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao pesquisar mesas. ");
				}
			}
		});

		scrollPane = new JScrollPane();

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Mesa mesaEditar = new MesaTableModel(
							new MesaController().listar())
							.get(tableMesa.getSelectedRow());

					CadastroMesaUI cadMesaUI = new CadastroMesaUI(
							mesaEditar, tableMesa);

					getContentPane().add(cadMesaUI, 0);
					cadMesaUI.setVisible(true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja excluir?");
				if (opcao == 0) {
					try {

						Mesa excluirMesa = new MesaTableModel(
								new MesaController().listar())
								.get(tableMesa.getSelectedRow());

						new MesaController().excluir(excluirMesa);
						JOptionPane.showMessageDialog(null,
								"Mesa excluida com Sucesso! ");

						// Atualiza tabela
						tableMesa.setModel(new MesaTableModel(
								new MesaController().listar()));

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNome)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		numeroMesa,
																		GroupLayout.PREFERRED_SIZE,
																		230,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		104,
																		Short.MAX_VALUE)
																.addComponent(
																		btnPesquisar))
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														449,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnEditar,
																		GroupLayout.PREFERRED_SIZE,
																		81,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		112,
																		Short.MAX_VALUE)
																.addComponent(
																		btnExcluir,
																		GroupLayout.PREFERRED_SIZE,
																		81,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(95)
																.addComponent(
																		btnFechar,
																		GroupLayout.PREFERRED_SIZE,
																		81,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNome)
												.addComponent(
														numeroMesa,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPesquisar))
								.addGap(18)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 188,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnFechar)
												.addComponent(btnEditar)
												.addComponent(btnExcluir))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		tableMesa = new JTable();
		try {
			tableMesa.setModel(new MesaTableModel(
					new MesaController().listar()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		scrollPane.setViewportView(tableMesa);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public JTable getTableConsultaCliente() {
		return tableMesa;
	}
}
