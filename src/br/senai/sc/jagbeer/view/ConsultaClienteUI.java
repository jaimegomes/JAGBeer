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

/**
 * Classe que contém a tela de consulta de cliente
 * 
 * @author Bazzi
 *
 */
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
		setBounds(610, 255, 650, 420);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consulta Cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
				JButton btnExcluir = new JButton("Excluir");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int opcao = JOptionPane.showConfirmDialog(null,
								"Deseja excluir cliente?");
						if (opcao == 0) {
							try {

								int linhaSelecionada = tableConsultaCliente
										.getSelectedRow();

								String nome = tableConsultaCliente.getValueAt(
										linhaSelecionada, 0).toString();

								String telefone = tableConsultaCliente.getValueAt(
										linhaSelecionada, 1).toString();

								String email = tableConsultaCliente.getValueAt(
										linhaSelecionada, 2).toString();

								Cliente clienteExcluir = (Cliente) new ClienteController()
										.buscaCompleta(nome, telefone, email);

								Pedido pedido = (Pedido) new PedidoController()
										.getPorIdCliente(clienteExcluir.getId());

								if (pedido.getStatus() == 0) {
									
									new ClienteController().excluir(clienteExcluir);

									JOptionPane.showMessageDialog(null,
											"Cliente excluído com Sucesso! ");

									tableConsultaCliente
											.setModel(new ClienteTableModel(
													new ClienteController().listar()));

									PrincipalUI
											.getInstancia()
											.getTablePedidoAberto()
											.setModel(
													new PrincipalTableModel(
															new PedidoController()
																	.getListPedidosEmAberto()));
								} else {
									JOptionPane
											.showMessageDialog(null,
													"Não é possível excluir clientes com pedido em aberto.");
								}
							} catch (Exception e2) {
								System.out.println(" aqui 4");
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
						}
					}
				});
		
				JButton btnEditar = new JButton("Editar / Inserir");
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(330, Short.MAX_VALUE)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnEditar))
					.addGap(17))
		);

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

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtfNomeCliente.setText("");
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtfNomeCliente, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtfNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpar)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
		);

		tableConsultaCliente = new JTable();
		try {
			tableConsultaCliente.setModel(new ClienteTableModel(
					new ClienteController().listar()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		tableConsultaCliente.getColumnModel().getColumn(0)
				.setPreferredWidth(277);
		tableConsultaCliente.getColumnModel().getColumn(1)
				.setPreferredWidth(90);
		tableConsultaCliente.getColumnModel().getColumn(2)
				.setPreferredWidth(182);
		tableConsultaCliente
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(tableConsultaCliente);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}