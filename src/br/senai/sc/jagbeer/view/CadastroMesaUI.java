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
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

/**
 * Classe que contém a tela de cadastro de mesa
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
		setBounds(775, 0, 425,250);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastro Mesa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblNmero = new JLabel("Número:");

		mesaNumero = new JTextField();
		// Apenas permite digitar numero no campo mesa numero	
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

		JLabel lblQtdLugares = new JLabel("Lugares:");

		if (mesaEdicao != null) {
			mesaNumero.setText(mesaEdicao.getNumeroMesa().toString());

			if (mesaEdicao.getLugares() > 0) {
				qtdLugares.setText(mesaEdicao.getLugares().toString());
			}
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 389, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addContainerGap())
		);

		// Botão salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				salvarEditarMesa(mesaEdicao);

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

		// Botão limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mesaNumero.setText(null);
				qtdLugares.setText(null);
			}
		});

		// Botão cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNmero)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mesaNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblQtdLugares)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(qtdLugares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(mesaNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQtdLugares)
						.addComponent(qtdLugares, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimpar)
						.addComponent(btnCancelar))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Método que verifica a existência da mesa no banco de dados, caso não
	 * exista ele adiciona uma nova, caso exista ele edita.
	 * 
	 * @param mesa
	 */
	private void salvarEditarMesa(Mesa mesa) {
		if (mesa == null) {

			int numMesa = 0;
			Integer lugares = 0;
			if (!mesaNumero.getText().isEmpty()) {
				numMesa = Integer.parseInt(mesaNumero.getText());

			} else {
				JOptionPane.showMessageDialog(null,
						"Número da mesa obrigatório.");
			}

			if (!qtdLugares.getText().isEmpty()) {
				lugares = Integer.parseInt(qtdLugares.getText());

			}

			Mesa mesaNova = new Mesa(numMesa, lugares);

			try {
				new MesaController().salvar(mesaNova);
				JOptionPane.showMessageDialog(null,
						"Mesa cadastrada com sucesso!");

				mesaNumero.setText("");
				qtdLugares.setText("");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

		} else {
			// Se não for nulo ele edita
			mesa.setNumeroMesa(Integer.parseInt(mesaNumero.getText()));
			mesa.setLugares(Integer.parseInt(qtdLugares.getText()));
			try {
				new MesaController().editar(mesa);
				JOptionPane
						.showMessageDialog(null, "Mesa editada com sucesso!");
				dispose();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
}
