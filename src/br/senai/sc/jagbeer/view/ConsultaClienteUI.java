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
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ClienteTableModel;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;

public class ConsultaClienteUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNomeCliente;
	private JTable tableConsultaCliente;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public ConsultaClienteUI() {
		setClosable(true);
		setTitle("Consulta Cliente");
		setBounds(100, 100, 656, 450);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consulta Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(25, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 402,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblNome = new JLabel("Nome:");

		jtfNomeCliente = new JTextField();
		jtfNomeCliente.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					tableConsultaCliente.setModel(new ClienteTableModel(
							new ClienteController()
									.getListClientesPorNome(jtfNomeCliente
											.getText())));

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao pesquisar clientes. ");
				}
			}
		});

		scrollPane = new JScrollPane();

		JButton btnFechar = new JButton("Limpar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNomeCliente.setText("");
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroClienteUI cadClienteUI;
				try {
					int linhaSelecionada = tableConsultaCliente
							.getSelectedRow();

					if (linhaSelecionada > -1) {

						String nome = tableConsultaCliente.getValueAt(
								linhaSelecionada, 0).toString();

						Cliente clienteEditar = (Cliente) new ClienteController()
								.getNomeSelecionado(nome);

						cadClienteUI = new CadastroClienteUI(clienteEditar,
								tableConsultaCliente);

					} else {
						cadClienteUI = new CadastroClienteUI(null,
								tableConsultaCliente);

					}

					PrincipalUI.getInstancia().getContentPane()
							.add(cadClienteUI, 0);
					cadClienteUI.setVisible(true);

					PrincipalUI
							.getInstancia()
							.getTablePedidoAberto()
							.setModel(
									new PrincipalTableModel(
											new PedidoController()
													.getListPedidosEmAberto()));

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"[ConsultaClienteUI] - Erro ao editar cliente."
									+ e1.getMessage());
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

						int linhaSelecionada = tableConsultaCliente
								.getSelectedRow();

						String nome = tableConsultaCliente.getValueAt(
								linhaSelecionada, 0).toString();

						Cliente clienteExcluir = (Cliente) new ClienteController()
								.getNomeSelecionado(nome);

						Pedido pedido = (Pedido) new PedidoController()
								.getPedidoAbertoPorIdCliente(clienteExcluir.getId());

						pedido.setStatus(0);

						new PedidoController().editar(pedido);

						new ClienteController().excluir(clienteExcluir);

						JOptionPane.showMessageDialog(null,
								"Cliente excluido com Sucesso! ");

						tableConsultaCliente.setModel(new ClienteTableModel(
								new ClienteController().listar()));

						PrincipalUI
								.getInstancia()
								.getTablePedidoAberto()
								.setModel(
										new PrincipalTableModel(
												new PedidoController()
														.getListPedidosEmAberto()));

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
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
												.addComponent(
														scrollPane,
														GroupLayout.PREFERRED_SIZE,
														583,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNome)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jtfNomeCliente,
																		GroupLayout.PREFERRED_SIZE,
																		218,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING,
																				false)
																				.addComponent(
																						btnPesquisar,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnFechar,
																						GroupLayout.DEFAULT_SIZE,
																						150,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						btnEditar,
																						GroupLayout.PREFERRED_SIZE,
																						150,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						btnExcluir,
																						GroupLayout.PREFERRED_SIZE,
																						150,
																						GroupLayout.PREFERRED_SIZE))))
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
														jtfNomeCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPesquisar)
												.addComponent(btnEditar))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnFechar)
												.addComponent(btnExcluir))
								.addGap(18)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 276,
										Short.MAX_VALUE).addContainerGap()));

		tableConsultaCliente = new JTable();
		tableConsultaCliente
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// try {
		// tableConsultaCliente.setModel(new ClienteTableModel(
		// new ClienteController().listar()));
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		scrollPane.setViewportView(tableConsultaCliente);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	// public JTable getTableConsultaCliente() {
	// return tableConsultaCliente;
	// }
}