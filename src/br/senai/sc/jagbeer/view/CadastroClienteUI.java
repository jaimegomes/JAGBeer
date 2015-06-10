package br.senai.sc.jagbeer.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import javax.swing.border.EtchedBorder;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.model.Cliente;

import java.awt.Color;

public class CadastroClienteUI extends JInternalFrame {
	private JTextField JtfNomeCliente;

	private Cliente cliente;
	private JTextField JtfEmailCliente;
	private JTextField JtfTelefoneCliente;

	// ATUALIZAÇAO
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteUI frame = new CadastroClienteUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroClienteUI() {
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

		JLabel lblNmero = new JLabel("Nome:");

		JtfNomeCliente = new JTextField();
		JtfNomeCliente.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Falta Try Catch
				Cliente cliente = new Cliente();

				cliente.setNome(JtfNomeCliente.getText());
				cliente.setTelefone(JtfTelefoneCliente.getText());
				cliente.setEmail(JtfEmailCliente.getText());

				new ClienteController().salvar(cliente);

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel lblQtdLugares = new JLabel("Telefone:");

		JLabel lblEmail = new JLabel("E-mail:");

		JtfEmailCliente = new JTextField();
		JtfEmailCliente.setColumns(10);

		JtfTelefoneCliente = new JTextField();
		JtfTelefoneCliente.setColumns(10);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JtfNomeCliente.setText("");
				JtfTelefoneCliente.setText("");
				JtfEmailCliente.setText("");

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
																						lblNmero)
																				.addComponent(
																						lblQtdLugares)
																				.addComponent(
																						lblEmail))
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						JtfTelefoneCliente,
																						GroupLayout.PREFERRED_SIZE,
																						138,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						JtfEmailCliente,
																						GroupLayout.DEFAULT_SIZE,
																						317,
																						Short.MAX_VALUE)
																				.addComponent(
																						JtfNomeCliente,
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
												.addComponent(lblNmero)
												.addComponent(
														JtfNomeCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblQtdLugares)
												.addComponent(
														JtfTelefoneCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblEmail)
												.addComponent(
														JtfEmailCliente,
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
