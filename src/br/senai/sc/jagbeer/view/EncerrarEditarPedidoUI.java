package br.senai.sc.jagbeer.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.PedidoController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.FazerPedidoTableModel;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PrincipalTableModel;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;

/**
 * Classe que contém a tela de encerramento e edição de pedido
 * 
 * @author Jaime Gomes
 *
 */
public class EncerrarEditarPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tableEncerraPedido;
	private JLabel lblNomeCliente;
	private JLabel lblNome;
	private JLabel lblPedido;
	private JLabel lblNumeroPedido;
	private JScrollPane scrollPane;
	private JLabel lblDdmmyyyy;
	private JLabel lblData;
	private JButton btnEncerrar;
	private JButton btnExcluirItem;
	private JLabel lblTotal;
	private JLabel lblValor;
	private JButton btnAdicionarProduto;
	private GroupLayout groupLayout;
	private GroupLayout gl_panel;
	private JPanel panel;
	private List<Entidade> listProdutos = new ArrayList<Entidade>();

	/**
	 * Create the frame.
	 */
	public EncerrarEditarPedidoUI(final Pedido pedido) {

		try {
			listProdutos = new ProdutoPedidoController().getPorIdPedido(pedido
					.getId());

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		setTitle("Encerrar Pedido");
		setClosable(true);
		setBounds(660, 0, 650, 600);

		panel = new JPanel();

		panel.setBorder(new TitledBorder(null, "Encerrar Pedido",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNomeCliente.setText(pedido.getCliente().getNome());

		lblNome = new JLabel("Cliente:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPedido = new JLabel("Pedido:");
		lblPedido.setFont(new Font("Dialog", Font.BOLD, 15));

		lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setText("" + pedido.getId());
		lblNumeroPedido.setFont(new Font("Dialog", Font.BOLD, 15));

		scrollPane = new JScrollPane();

		lblDdmmyyyy = new JLabel("dd/MM/yyyy");
		lblDdmmyyyy.setText("" + sdf.format(pedido.getDataPedido()));

		lblData = new JLabel("Data:");

		lblTotal = new JLabel("Total R$");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 15));

		lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Dialog", Font.BOLD, 15));

		try {
			lblValor.setText("" + pedido.getValor());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		btnEncerrar = new JButton("Encerrar Pedido");
		btnEncerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int opcao = JOptionPane.showConfirmDialog(null,
						"Deseja encerrar o pedido?");

				if (opcao == 0) {

					pedido.setStatus(0);

					try {
						new PedidoController().editar(pedido);

						PrincipalUI
								.getInstancia()
								.getTablePedidoAberto()
								.setModel(
										new PrincipalTableModel(
												new PedidoController()
														.getListPedidosEmAberto()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null,
							"Pedido encerrado com sucesso.");

					NotaFiscalUI notaFiscalUI = new NotaFiscalUI(pedido);
					notaFiscalUI.requestFocus(true);
					notaFiscalUI.setFocusable(true);
					notaFiscalUI.moveToFront();
					notaFiscalUI.setVisible(true);

					try {
						PrincipalUI.getInstancia().getContentPane()
								.add(notaFiscalUI, 0);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					dispose();

				}
			}
		});

		btnExcluirItem = new JButton("Excluir Produto");
		btnExcluirItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int linhaSelecionada = tableEncerraPedido.getSelectedRow();

					if (linhaSelecionada > -1) {

						int opcao = JOptionPane.showConfirmDialog(null,
								"Deseja excluir?");

						if (opcao == 0) {

							String nomeProduto = tableEncerraPedido.getValueAt(
									linhaSelecionada, 0).toString();

							int qtde = (int) tableEncerraPedido.getValueAt(
									linhaSelecionada, 1);

							Produto produto = (Produto) new ProdutoController()
									.getPorNome(nomeProduto);

							ProdutoPedido produtoPedido = (ProdutoPedido) new ProdutoPedidoController()
									.buscaCompleta(produto.getId(), qtde,
											pedido.getId());

							new ProdutoPedidoController()
									.excluir(produtoPedido);

							double valor = pedido.getValor()
									- (produto.getPrecoVenda() * produtoPedido
											.getQtde());

							pedido.setValor(valor);

							new PedidoController().editar(pedido);

							listProdutos.remove(linhaSelecionada);

							lblValor.setText("" + valor);
							tableEncerraPedido
									.setModel(new FazerPedidoTableModel(
											listProdutos));

							PrincipalUI
									.getInstancia()
									.getTablePedidoAberto()
									.setModel(
											new PrincipalTableModel(
													new PedidoController()
															.getListPedidosEmAberto()));

						}
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Selecione um produto do pedido que deseja excluir.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnAdicionarProduto = new JButton("Adicionar Produto");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					FazerPedidoUI fazerPedido = new FazerPedidoUI(
							tableEncerraPedido, pedido);
					fazerPedido.requestFocus(true);
					fazerPedido.setFocusable(true);
					fazerPedido.moveToFront();

					PrincipalUI.getInstancia().getContentPane()
							.add(fazerPedido, 0);

					fazerPedido.setVisible(true);

					PrincipalUI
							.getInstancia()
							.getTablePedidoAberto()
							.setModel(
									new PrincipalTableModel(
											new PedidoController()
													.getListPedidosEmAberto()));

					dispose();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(18)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 608,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(19)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 536,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(19, Short.MAX_VALUE)));

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAdicionarProduto, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addGap(91)
							.addComponent(btnExcluirItem, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(244))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPedido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNumeroPedido)
											.addGap(16)))
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(18)
											.addComponent(lblNomeCliente)
											.addPreferredGap(ComponentPlacement.RELATED, 170, Short.MAX_VALUE))
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblData)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addComponent(lblDdmmyyyy, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblTotal)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblValor, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnEncerrar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeCliente))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPedido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumeroPedido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDdmmyyyy)
						.addComponent(lblData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(lblValor))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionarProduto)
						.addComponent(btnExcluirItem)
						.addComponent(btnEncerrar))
					.addContainerGap())
		);

		tableEncerraPedido = new JTable();
		try {
			tableEncerraPedido
					.setModel(new FazerPedidoTableModel(listProdutos));
		} catch (Exception e) {
			e.printStackTrace();
		}

		scrollPane.setViewportView(tableEncerraPedido);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public void setLblValor(JLabel lblValor) {
		this.lblValor = lblValor;
	}

}
