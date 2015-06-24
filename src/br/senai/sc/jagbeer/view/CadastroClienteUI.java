package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

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
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.ClienteTableModel;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;

/**
 * Classe que contém a tela de cadastro de cliente
 * 
 * @author Bazzi
 *
 */
public class CadastroClienteUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTextField jtfNomeCliente;
	private JTextField jtfEmailCliente;
	private JTextField jtfTelefoneCliente;

	private Cliente clienteEdicao;

	/**
	 * Create the frame.
	 */
	public CadastroClienteUI(final Cliente c, final JTable table) {

		clienteEdicao = c;

		setTitle("Cadastro de Cliente");
		setClosable(true);
		setBounds(100, 100, 425, 275);

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
						.addComponent(panel, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 227,
								Short.MAX_VALUE).addContainerGap()));

		JLabel lblNome = new JLabel("Nome:");

		jtfNomeCliente = new JTextField();

		jtfNomeCliente.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		JLabel lblEmail = new JLabel("E-mail:");

		jtfEmailCliente = new JTextField();
		jtfEmailCliente.setColumns(10);

		jtfTelefoneCliente = new JTextField();
		jtfTelefoneCliente.setColumns(10);

		// APENA NUMEROS NO CAPO TELEFONE
		jtfTelefoneCliente = new JTextField();
		jtfTelefoneCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321 -()";

				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		if (c != null) {

			jtfNomeCliente.setText(clienteEdicao.getNome());
			jtfEmailCliente.setText(clienteEdicao.getEmail());
			jtfTelefoneCliente.setText(clienteEdicao.getTelefone());
		}

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (c == null) {

					Cliente cliente = new Cliente();
					cliente.setNome(jtfNomeCliente.getText());
					cliente.setTelefone(jtfTelefoneCliente.getText());
					cliente.setEmail(jtfEmailCliente.getText());

					try {
						new ClienteController().salvar(cliente);
						JOptionPane.showMessageDialog(null,
								"Cliente Cadastrado com Sucesso!");

						Cliente c = (Cliente) new ClienteController()
								.getNomeSelecionado(jtfNomeCliente.getText());

						Pedido pedido = new Pedido();
						pedido.setCliente(c);
						pedido.setStatus(1);
						pedido.setDataPedido(new Date());
						pedido.setMesa(null);
						pedido.setValor(0.00);

						new PedidoController().salvar(pedido);

						jtfNomeCliente.setText("");
						jtfEmailCliente.setText("");
						jtfTelefoneCliente.setText("");

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

						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}

				try {
					if (table != null) {

						table.setModel(new ClienteTableModel(
								new ClienteController().listar()));

					}

					PrincipalUI
							.getInstancia()
							.getTablePedidoAberto()
							.setModel(
									new PrincipalTableModel(
											new PedidoController()
													.getListPedidosEmAberto()));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

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
																				Alignment.LEADING)
																				.addComponent(
																						lblTelefone)
																				.addComponent(
																						lblNome)
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
																		100,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		btnLimpar,
																		GroupLayout.PREFERRED_SIZE,
																		100,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		btnCancelar,
																		GroupLayout.PREFERRED_SIZE,
																		100,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(22)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														jtfNomeCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNome))
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
												.addComponent(
														jtfEmailCliente,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmail))
								.addGap(47)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSalvar)
												.addComponent(btnLimpar)
												.addComponent(btnCancelar))
								.addGap(27)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

}