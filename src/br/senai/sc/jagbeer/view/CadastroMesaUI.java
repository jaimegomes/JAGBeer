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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Mesa;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CadastroMesaUI extends JInternalFrame {
	
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
		setBounds(100, 100, 344, 203);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Mesa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	
		//Botão salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
									
				//Verifica se o objeto é nulo
				if(mesaEdicao == null ){
					Mesa mesa = new Mesa();
					mesa.setNumeroMesa(Integer.parseInt(mesaNumero.getText()));
					mesa.setLugares(Integer.parseInt(qtdLugares.getText()));
					
					try{
						new MesaController().salvar(mesa);
						JOptionPane.showMessageDialog(null, "Mesa cadastrada com sucesso!");
						dispose();
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					
				}else{
					//Se não for nulo ele edita
					mesaEdicao.setNumeroMesa(Integer.parseInt(mesaNumero.getText()));
					mesaEdicao.setLugares(Integer.parseInt(qtdLugares.getText()));
					try{
						new MesaController().editar(mesaEdicao);
						JOptionPane.showMessageDialog(null, "Mesa editada com sucesso!");
						dispose();
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}				
			}
		});
		
		//Botão limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesaNumero.setText("");
				qtdLugares.setText("");
			}
		});
				
		//Botão cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnLimpar)
						.addComponent(btnSalvar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		
		mesaNumero = new JTextField();
		mesaNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kv) {
				
				String caracteres= "0987654321";
				
				if(!caracteres.contains(kv.getKeyChar()+""))
				{
					kv.consume();
				}
			}
		});
		
		mesaNumero.setColumns(10);
		
		qtdLugares = new JTextField();
		qtdLugares.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres= "0987654321";
				
				if(!caracteres.contains(e.getKeyChar()+""))
				{
					e.consume();
				}
			}
		});
		qtdLugares.setColumns(10);
		
		JLabel lblQtdLugares = new JLabel("Qtd Lugares");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQtdLugares)
						.addComponent(lblNmero))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(mesaNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(qtdLugares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(mesaNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQtdLugares)
						.addComponent(qtdLugares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		
		if(m != null){
			mesaNumero.setText(m.getNumeroMesa().toString());
			qtdLugares.setText(m.getLugares().toString());
		}
	}
	
	
}
