package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.model.Cliente;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

/**
 * Classe View FazerPedidoUI
 * 
 * @author Jaime Gomes
 *
 */
public class FazerPedidoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private GroupLayout groupLayout;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JComboBox cmbPedido;
	private JLabel lblPedido;
	private GroupLayout groupLayoutProduto;
	private JLabel lblCliente;
	private JComboBox comboBox;
	private JPanel panelProduto;
	private JComboBox comboBox_1;
	private JLabel lblClassificao;
	private JLabel lblProduto;
	private JComboBox comboBox_2;
	private JLabel lblQuantidade;
	private JTable table;

	public FazerPedidoUI() {

		setTitle("Fazer Pedido");
		setClosable(true);
		setBounds(100, 100, 628, 489);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addContainerGap())
		);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		cmbPedido = new JComboBox();
		cmbPedido.setMaximumRowCount(2);

		lblPedido = new JLabel("Pedido:");
		
		lblCliente = new JLabel("Cliente:");
		
		comboBox = new JComboBox();
		
		panelProduto = new JPanel();
		panelProduto.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("Mesa:");
		
		JComboBox comboBox_3 = new JComboBox();
		
		table = new JTable();
		groupLayoutProduto = new GroupLayout(panel);
		groupLayoutProduto.setHorizontalGroup(
			groupLayoutProduto.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayoutProduto.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayoutProduto.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayoutProduto.createSequentialGroup()
							.addComponent(panelProduto, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayoutProduto.createSequentialGroup()
							.addGroup(groupLayoutProduto.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPedido)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayoutProduto.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(comboBox_3, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmbPedido, Alignment.LEADING, 0, 71, Short.MAX_VALUE))
							.addGap(30)
							.addComponent(lblCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 248, Short.MAX_VALUE)
							.addGap(96))))
				.addGroup(groupLayoutProduto.createSequentialGroup()
					.addGap(182)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayoutProduto.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayoutProduto.setVerticalGroup(
			groupLayoutProduto.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayoutProduto.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayoutProduto.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPedido)
						.addComponent(cmbPedido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCliente)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayoutProduto.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelProduto, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayoutProduto.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSalvar))
					.addContainerGap())
		);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Alimentos", "Bebidas"}));
		comboBox_1.setMaximumRowCount(2);
		
		lblClassificao = new JLabel("Classificação:");
		
		lblProduto = new JLabel("Produto:");
		
		comboBox_2 = new JComboBox();
		
		lblQuantidade = new JLabel("Quantidade:");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JButton btnAdicionarProduto = new JButton("Adicionar Produto");
		GroupLayout gl_panelProduto = new GroupLayout(panelProduto);
		gl_panelProduto.setHorizontalGroup(
			gl_panelProduto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProduto.createSequentialGroup()
					.addGroup(gl_panelProduto.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClassificao)
						.addComponent(lblProduto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProduto.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox_2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, 130, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProduto.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProduto.createSequentialGroup()
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAdicionarProduto))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_panelProduto.setVerticalGroup(
			gl_panelProduto.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProduto.createSequentialGroup()
					.addGroup(gl_panelProduto.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClassificao)
						.addComponent(lblQuantidade)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelProduto.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduto)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionarProduto))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panelProduto.setLayout(gl_panelProduto);
		
		
		panel.setLayout(groupLayoutProduto);
		getContentPane().setLayout(groupLayout);

	}
}
