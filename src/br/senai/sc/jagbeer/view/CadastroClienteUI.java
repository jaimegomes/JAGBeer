package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ClienteTableModel;

public class CadastroClienteUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTextField jtfNomeCliente;
	private JTextField jtfEmailCliente;
	private JTextField jtfTelefoneCliente;

	private Cliente clienteEdicao;

	/**
	 * Create the frame.
	 */
	public CadastroClienteUI(Cliente c, final JTable table) {

		clienteEdicao = c;

		setTitle("Cadastro de Cliente");
		setClosable(true);
		setBounds(100, 100, 425, 241);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Cadastro Cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 336,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));

		JLabel lblNome = new JLabel("Nome:");

		jtfNomeCliente = new JTextField();

		if (!clienteEdicao.getNome().equals("")
				|| clienteEdicao.getNome() != null)
			jtfNomeCliente.setText(clienteEdicao.getNome());

		jtfNomeCliente.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (clienteEdicao == null) {

					Cliente cliente = new Cliente();
					cliente.setNome(jtfNomeCliente.getText());
					cliente.setTelefone(jtfTelefoneCliente.getText());
					cliente.setEmail(jtfEmailCliente.getText());

					try {
						new ClienteController().salvar(cliente);
						JOptionPane.showMessageDialog(null,
								"Cliente Cadastrado com Sucesso!");

						ConsultaClienteUI consultaClienteUI = new ConsultaClienteUI();
						consultaClienteUI.requestFocus(true);
						consultaClienteUI.setFocusable(true);
						consultaClienteUI.moveToFront();
						getContentPane().add(consultaClienteUI, 0);
						consultaClienteUI.setVisible(true);

						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				} else {

					clienteEdicao.setNome(jtfNomeCliente.getText());
					clienteEdicao.setTelefone(jtfTelefoneCliente.getText());
					clienteEdicao.setEmail(jtfEmailCliente.getText());

					try {
						new ClienteController().editar(clienteEdicao);
						JOptionPane.showMessageDialog(null,
								"Cliente Editado com Sucesso! ");

						if (table != null) {
							table.setModel(new ClienteTableModel(
									new ClienteController().listar()));
						}

						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblTelefone = new JLabel("Telefone:");

		JLabel lblEmail = new JLabel("E-mail:");

		jtfEmailCliente = new JTextField();
		jtfEmailCliente.setColumns(10);

		if (!clienteEdicao.getEmail().equals("")
				|| clienteEdicao.getEmail() != null) {
			jtfEmailCliente.setText(clienteEdicao.getEmail());
		}

		jtfTelefoneCliente = new JTextField();
		jtfTelefoneCliente.setColumns(10);

		if (!clienteEdicao.getTelefone().equals("")
				|| clienteEdicao.getTelefone() != null)
			jtfTelefoneCliente.setText(clienteEdicao.getTelefone());

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfNomeCliente.setText("");
				jtfTelefoneCliente.setText("");
				jtfEmailCliente.setText("");

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
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING)
																				.addComponent(
																						lblNome)
																				.addComponent(
																						lblTelefone)
																				.addComponent(
																						lblEmail))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						jtfTelefoneCliente,
																						GroupLayout.PREFERRED_SIZE,
																						138,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jtfEmailCliente,
																						GroupLayout.DEFAULT_SIZE,
																						317,
																						Short.MAX_VALUE)
																				.addComponent(
																						jtfNomeCliente,
																						GroupLayout.DEFAULT_SIZE,
																						317,
																						Short.MAX_VALUE)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnSalvar,
																		GroupLayout.PREFERRED_SIZE,
																		79,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED,
																		70,
																		Short.MAX_VALUE)
																.addComponent(
																		btnLimpar,
																		GroupLayout.PREFERRED_SIZE,
																		79,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(62)
																.addComponent(
																		btnCancelar)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(22)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNome)
												.addComponent(
														jtfNomeCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblTelefone)
												.addComponent(
														jtfTelefoneCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblEmail)
												.addComponent(
														jtfEmailCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(32)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSalvar)
												.addComponent(btnCancelar)
												.addComponent(btnLimpar))
								.addContainerGap(20, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
