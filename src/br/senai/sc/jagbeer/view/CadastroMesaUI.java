package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.MesaTableModel;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe que cont�m a tela de cadastro de mesa
 * 
 * @author Gabriela
 *
 */
public class CadastroMesaUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField mesaNumero;
	private JTextField qtdLugares;
	private Mesa mesaEdicao;

	/**
	 * Create the frame.
	 */
	public CadastroMesaUI(Mesa m, final JTable table) {

		mesaEdicao = m;

		setTitle("Cadastro de Mesa");
		setClosable(true);
		setBounds(850, 20, 344, 203);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Mesa", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JLabel lblNmero = new JLabel("N\u00FAmero");

		// Apenas permite digitar numero no campo mesa numero
		mesaNumero = new JTextField();
		mesaNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kv) {

				String caracteres = "0987654321";

				if (!caracteres.contains(kv.getKeyChar() + "")) {
					kv.consume();
				}
			}
		});

		mesaNumero.setColumns(10);

		// Apenas permite digitar numero no campo quantidade lugares
		qtdLugares = new JTextField();
		qtdLugares.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0987654321";

				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		qtdLugares.setColumns(10);

		JLabel lblQtdLugares = new JLabel("Qtd Lugares");

		if (mesaEdicao != null) {
			mesaNumero.setText(mesaEdicao.getNumeroMesa().toString());
			qtdLugares.setText(mesaEdicao.getLugares().toString());
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 308,
								Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 151,
								Short.MAX_VALUE).addContainerGap()));

		// Bot�o salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Verifica se o objeto � nulo
				if (mesaEdicao == null) {

					int numMesa = 0;
					Integer lugares = 0;
					if (!mesaNumero.getText().isEmpty()) {
						numMesa = Integer.parseInt(mesaNumero.getText());

					} else {
						JOptionPane.showMessageDialog(null,
								"N�mero da mesa obrigat�rio.");
					}

					if (!qtdLugares.getText().isEmpty()) {
						lugares = Integer.parseInt(mesaNumero.getText());

					}

					Mesa mesa = new Mesa(numMesa, lugares);

					try {
						new MesaController().salvar(mesa);
						JOptionPane.showMessageDialog(null,
								"Mesa cadastrada com sucesso!");

						mesaNumero.setText("");
						qtdLugares.setText("");

						if (table != null) {
							table.setModel(new ProdutoTableModel(
									new MesaController().listar()));
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				} else {
					// Se n�o for nulo ele edita
					mesaEdicao.setNumeroMesa(Integer.parseInt(mesaNumero
							.getText()));
					mesaEdicao.setLugares(Integer.parseInt(qtdLugares.getText()));
					try {
						new MesaController().editar(mesaEdicao);
						JOptionPane.showMessageDialog(null,
								"Mesa editada com sucesso!");
						dispose();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}

				if (table != null) {
					try {
						table.setModel(new MesaTableModel(new MesaController()
								.listar()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		// Bot�o limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesaNumero.setText(null);
				qtdLugares.setText(null);
			}
		});

		// Bot�o cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
																						lblQtdLugares)
																				.addComponent(
																						lblNmero))
																.addGap(18)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addComponent(
																						mesaNumero,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						qtdLugares,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnSalvar,
																		GroupLayout.PREFERRED_SIZE,
																		79,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnLimpar)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnCancelar)))
								.addContainerGap(57, Short.MAX_VALUE)));
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
														mesaNumero,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblQtdLugares)
												.addComponent(
														qtdLugares,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED,
										24, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSalvar)
												.addComponent(btnLimpar)
												.addComponent(btnCancelar))));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
