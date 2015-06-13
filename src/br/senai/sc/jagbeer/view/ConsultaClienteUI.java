package br.senai.sc.jagbeer.view;

import java.awt.EventQueue;
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
import br.senai.sc.jagbeer.dao.ClienteDAO;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ClienteTableModel;

//essa caralha que não funcionar
public class ConsultaClienteUI extends JInternalFrame {
	private JTextField jtfNomeCliente;
	private JTable tableConsultaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteUI frame = new ConsultaClienteUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public ConsultaClienteUI() {
		setTitle("Consulta Cliente");
		setBounds(100, 100, 510, 360);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consulta Cliente",
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

		JLabel lblNome = new JLabel("Nome:");

		jtfNomeCliente = new JTextField();
		jtfNomeCliente.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					new ClienteController().getPorNome(jtfNomeCliente.getText());

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Erro ao pesquisar clientes. ");
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente clienteEditar = null;
				try {
					clienteEditar = new ClienteTableModel(new ClienteDAO()
							.listar()).get(tableConsultaCliente
							.getSelectedRow());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(
							null,
							"[ConsultaClienteUI - Erro Editar] "
									+ e1.getMessage());
				}
				CadastroClienteUI cadClienteUI = new CadastroClienteUI(
						clienteEditar);
				cadClienteUI.setVisible(true);

				try {
					// Atualiza tabela
					tableConsultaCliente.setModel(new ClienteTableModel(
							new ClienteDAO().listar()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"[ConsultaClienteUI - Erro Atualizar após Editar] "
									+ e2.getMessage());
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

						Cliente clienteExcluir = new ClienteTableModel(
								new ClienteDAO().listar())
								.get(tableConsultaCliente.getSelectedRow());

						new ClienteController().excluir(clienteExcluir);
						JOptionPane.showMessageDialog(null,
								"Aluno excluído com Sucesso! ");

						// Atualiza tabela
						tableConsultaCliente.setModel(new ClienteTableModel(
								new ClienteDAO().listar()));

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
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNome)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		jtfNomeCliente,
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
														jtfNomeCliente,
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

		tableConsultaCliente = new JTable();
		try {
			tableConsultaCliente.setModel(new ClienteTableModel(
					new ClienteController().listar()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		scrollPane.setViewportView(tableConsultaCliente);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
