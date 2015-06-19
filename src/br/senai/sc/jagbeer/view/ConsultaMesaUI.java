package br.senai.sc.jagbeer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.MesaTableModel;



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
						
					if(numeroMesa.getText().isEmpty() || numeroMesa.getText() == null){
						listaMesa = new MesaController().listar();
						tableMesa.setModel(new MesaTableModel(listaMesa));
					}
					
					if(!numeroMesa.getText().isEmpty()){
						Mesa mesa = (Mesa) new MesaController().getPorNumeroMesa(Integer.parseInt(numeroMesa.getText()));
						listaMesa.add(mesa);
						tableMesa.setModel(new MesaTableModel(listaMesa));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Erro ao pesquisar mesas. ");
				}
			}
		});

		scrollPane = new JScrollPane();

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadastroMesaUI cadMesaUI;
				try {
					int linhaSelecionada = tableMesa.getSelectedRow();
					
					if(linhaSelecionada > -1){
						
						int numeroMesa = Integer.parseInt(tableMesa.getValueAt(linhaSelecionada, 0).toString());
						
						Mesa editarMesa =(Mesa) new MesaController().getPorNumeroMesa(numeroMesa);
						
						cadMesaUI = new CadastroMesaUI(editarMesa, tableMesa);
						
						getContentPane().add(cadMesaUI, 0);
						cadMesaUI.setVisible(true);
					}else{
						cadMesaUI = new CadastroMesaUI(null, tableMesa);
					}
					
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
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					CadastroMesaUI cadMesa = new CadastroMesaUI(null, tableMesa);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numeroMesa, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
							.addComponent(btnPesquisar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
							.addComponent(btnAdicionar)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(numeroMesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnExcluir)
						.addComponent(btnAdicionar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		tableMesa = new JTable();
//		try {
//			tableMesa.setModel(new DefaultTableModel(
//				new Object[][] {
//				},
//				new String[] {
//					"Numero Mesa", "Quantidade Lugares"
//				}
//			) {
//				boolean[] columnEditables = new boolean[] {
//					false, true
//				};
//				public boolean isCellEditable(int row, int column) {
//					return columnEditables[column];
//				}
//			});
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

		
		scrollPane.setViewportView(tableMesa);
		//tableMesa.isCe
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	public JTable getTableConsultaCliente() {
		return tableMesa;
	}
}
