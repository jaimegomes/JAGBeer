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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

public class CadastroProdutoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private GroupLayout groupLayout;
	private JTextField jtfNome;
	private JTextField jtfValorCusto;
	private JTextField jtfValor;
	private JLabel lblNome;
	private JLabel lblValor;
	private JLabel lblValorCusto;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JComboBox cmbClassificacao;
	private JLabel lblClassificao;
	private GroupLayout gl_panel;

	private Produto produtoEdicao;

	/**
	 * Create the frame.
	 */
	public CadastroProdutoUI(Produto produto, final JTable table) {

		produtoEdicao = produto;

		setTitle("Cadastro de Produto");
		setClosable(true);
		setBounds(100, 100, 506, 242);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Cadastro Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addContainerGap())
		);

		lblNome = new JLabel("Nome:");

		jtfNome = new JTextField();

		if (!produtoEdicao.getNome().equals("")
				|| produtoEdicao.getNome() != null)
			jtfNome.setText(produtoEdicao.getNome());

		jtfNome.setHorizontalAlignment(SwingConstants.LEFT);
		jtfNome.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String classificacao = "";

				if (cmbClassificacao.getSelectedIndex() == 1)
					classificacao = "Alimento";

				if (cmbClassificacao.getSelectedIndex() == 2)
					classificacao = "Bebida";

				if (produtoEdicao == null) {

					Produto produto = new Produto();

					produto.setNome(jtfNome.getText());
					produto.setPrecoVenda(Double.parseDouble(jtfValor.getText()
							.replaceAll(",", ".")));
					produto.setPrecoCusto(Double.parseDouble(jtfValorCusto
							.getText().replaceAll(",", ".")));
					produto.setClassificacao(classificacao);

					try {
						new ProdutoController().salvar(produto);
						JOptionPane.showMessageDialog(null,
								"Produto Cadastrado com Sucesso!");
						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				} else {

					produtoEdicao.setNome(jtfNome.getText());
					produtoEdicao.setPrecoVenda(Double.parseDouble(jtfValor
							.getText()));
					produtoEdicao.setPrecoCusto(Double
							.parseDouble(jtfValorCusto.getText()));
					produtoEdicao.setClassificacao(classificacao);

					// if(jtfValorCusto != null &&
					// !jtfValorCusto.getText().equals("")) {
					//
					// }

					try {
						new ProdutoController().editar(produtoEdicao);
						JOptionPane.showMessageDialog(null,
								"Produto Editado com Sucesso! ");

						if (table != null) {
							table.setModel(new ProdutoTableModel(
									new ProdutoController().listar()));
						}

						dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblValor = new JLabel("Valor:");

		lblValorCusto = new JLabel("Valor Custo:");

		jtfValorCusto = new JTextField();

		if (!produtoEdicao.getPrecoCusto().equals("")
				|| produtoEdicao.getPrecoCusto() != null) {
			jtfValorCusto.setText(produtoEdicao.getPrecoCusto().toString());
		}
		jtfValorCusto.setHorizontalAlignment(SwingConstants.RIGHT);
		jtfValorCusto.setColumns(10);

		jtfValor = new JTextField();

		if (!produtoEdicao.getPrecoVenda().equals("")
				|| produtoEdicao.getPrecoVenda() != null) {
			jtfValor.setText(produtoEdicao.getPrecoVenda().toString());
		}

		jtfValor.setHorizontalAlignment(SwingConstants.RIGHT);
		jtfValor.setColumns(10);

		cmbClassificacao = new JComboBox();

		if (!produtoEdicao.getClassificacao().equals("")
				|| produtoEdicao.getClassificacao() != null) {

			if (produtoEdicao.getClassificacao().equals("Alimento"))
				cmbClassificacao.setSelectedIndex(1);

			if (produtoEdicao.getClassificacao().equals("Bebida"))
				cmbClassificacao.setSelectedIndex(2);
		}

		cmbClassificacao.setModel(new DefaultComboBoxModel(new String[] { "",
				"Alimento", "Bebida" }));
		cmbClassificacao.setMaximumRowCount(3);

		lblClassificao = new JLabel("Classificação:");

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfNome.setText("");
				jtfValor.setText("");
				jtfValorCusto.setText("");
				cmbClassificacao.setSelectedIndex(0);

			}
		});

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValorCusto)
								.addComponent(lblNome)
								.addComponent(lblValor))
							.addGap(22)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(jtfValorCusto)
									.addComponent(jtfValor))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblClassificao)
									.addGap(18)
									.addComponent(cmbClassificacao, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addGap(266))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfValorCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValorCusto))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmbClassificacao, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClassificao))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimpar)
						.addComponent(btnCancelar)))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}
}
