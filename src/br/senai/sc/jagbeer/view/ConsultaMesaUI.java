package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.MesaTableModel;

/**
 * Classe que contém a tela de consulta de mesa
 * 
 * @author Gabriela
 *
 */
public class ConsultaMesaUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField numeroMesa;
	private JTable tableMesa;
	private JScrollPane scrollPane;

	private List<Entidade> listaMesa = new ArrayList<Entidade>();

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public ConsultaMesaUI() {
		setClosable(true);
		setTitle("Consulta de Mesas");
		setBounds(610, 255, 650, 420);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Consulta de Mesa",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		
				JButton btnEditar = new JButton("Editar / Inserir");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						CadastroMesaUI cadMesaUI;
						try {
							int linhaSelecionada = tableMesa.getSelectedRow();

							if (linhaSelecionada > -1) {

								int numeroMesa = Integer.parseInt(tableMesa.getValueAt(
										linhaSelecionada, 0).toString());

								Mesa editarMesa = (Mesa) new MesaController()
										.getPorNumeroMesa(numeroMesa);

								cadMesaUI = new CadastroMesaUI(editarMesa, tableMesa);

								PrincipalUI.getInstancia().getContentPane()
										.add(cadMesaUI, 0);

								cadMesaUI.setVisible(true);
							} else {
								cadMesaUI = new CadastroMesaUI(null, tableMesa);
							}

							PrincipalUI.getInstancia().getContentPane()
									.add(cadMesaUI, 0);
							cadMesaUI.setVisible(true);

						} catch (Exception e1) {
							e1.printStackTrace();
						}

					}
				});
		
				JButton btnExcluir = new JButton("Excluir");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {

							Mesa excluirMesa = new MesaTableModel(new MesaController()
									.listar()).get(tableMesa.getSelectedRow());

							new MesaController().excluir(excluirMesa);
							JOptionPane.showMessageDialog(null,
									"Mesa excluída com Sucesso! ");

							// Atualiza tabela
							tableMesa.setModel(new MesaTableModel(new MesaController()
									.listar()));

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}

					}
				});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(20))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)))
		);

		JLabel lblNome = new JLabel("Número Mesa:");

		numeroMesa = new JTextField();
		numeroMesa.setColumns(10);
		
		//Apenas numeros na consulta
		numeroMesa.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent kv) {

				String caracteres = "0987654321";

				if (!caracteres.contains(kv.getKeyChar() + "")) {
					kv.consume();
				}
			}
		});

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					if (numeroMesa.getText().isEmpty()
							|| numeroMesa.getText() == null) {
						listaMesa = new MesaController().listar();
						tableMesa.setModel(new MesaTableModel(listaMesa));
					}

					if (!numeroMesa.getText().isEmpty()) {
						listaMesa.clear();
						Mesa mesa = (Mesa) new MesaController()
								.getPorNumeroMesa(Integer.parseInt(numeroMesa
										.getText()));
						if (mesa == null) {
							JOptionPane.showMessageDialog(null,
									"Número mesa não cadastrado");
							listaMesa.clear();
						} else {
							listaMesa.add(mesa);
							tableMesa.setModel(new MesaTableModel(listaMesa));
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Erro ao pesquisar mesas. ");
				}
			}
		});

		scrollPane = new JScrollPane();

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numeroMesa.setText(null);
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(numeroMesa, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(numeroMesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpar)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
		);

		tableMesa = new JTable();
		tableMesa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		try {
			tableMesa
					.setModel(new MesaTableModel(new MesaController().listar()));
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
