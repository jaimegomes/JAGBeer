package br.senai.sc.jagbeer.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Produto;

public class CadastroProdutoUI extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	// conteudo geral da janela
	private JPanel panel;
	private GroupLayout groupLayout;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLimpar;

	// itens do formulário
	private GroupLayout grouLayoutItensForm;
	private JLabel lblNome;
	private JTextField jtfNome;
	private JLabel lblValor;
	private JTextField jtfValor;
	private JLabel lblValorCusto;
	private JTextField jtfValorCusto;
	private JLabel lblClassificacao;
	private JComboBox<String> cbxClassificacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					System.out.println("entrou");
					CadastroProdutoUI frame = new CadastroProdutoUI();
					frame.setVisible(true);
					
					System.out.println("fim");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroProdutoUI() {

		System.out.println("oi");
		setTitle("Cadastro de Produtos");
		setClosable(true);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Cadastro Produto", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtfNome.setText("");
				jtfValor.setText("");
				jtfValorCusto.setText("");
				cbxClassificacao.setSelectedIndex(-1);
			}

		});

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String classificacao = "";

				try {

					if (cbxClassificacao.getSelectedIndex() == -1)
						throw new Exception("A classificacao e obrigatoria.");

					else if (cbxClassificacao.getSelectedIndex() == 0)
						classificacao = "Alimentos";

					else if (cbxClassificacao.getSelectedIndex() == 1)
						classificacao = "Bebidas";

					Produto produto = new Produto(jtfNome.getText(), Double
							.parseDouble(jtfValor.getText()), Double
							.parseDouble(jtfValorCusto.getText()),
							classificacao);

					new ProdutoController().salvar(produto);
					JOptionPane.showMessageDialog(null,
							"Produto salvo com sucesso.");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Erro ao salvar produto. " + e1.getMessage());
				}
			}

		});
		groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(105)
								.addComponent(btnSalvar,
										GroupLayout.PREFERRED_SIZE, 96,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnLimpar,
										GroupLayout.PREFERRED_SIZE, 92,
										GroupLayout.PREFERRED_SIZE).addGap(18)
								.addComponent(btnCancelar)
								.addContainerGap(15, Short.MAX_VALUE))
				.addGroup(
						Alignment.LEADING,
						groupLayout
								.createSequentialGroup()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 434,
										Short.MAX_VALUE).addGap(6)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 175,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnSalvar)
												.addComponent(btnLimpar)
												.addComponent(btnCancelar))
								.addContainerGap(50, Short.MAX_VALUE)));

		lblNome = new JLabel("Nome:");
		jtfNome = new JTextField();

		lblValor = new JLabel("Valor:");
		jtfValor = new JTextField();
		jtfValor.setToolTipText("");
		jtfValor.setColumns(10);

		lblValorCusto = new JLabel("Valor Custo :");

		jtfValorCusto = new JTextField();
		jtfValorCusto.setColumns(10);

		lblClassificacao = new JLabel("Classificacao:");

		cbxClassificacao = new JComboBox<String>();

		grouLayoutItensForm = new GroupLayout(panel);
		grouLayoutItensForm.setHorizontalGroup(
			grouLayoutItensForm.createParallelGroup(Alignment.LEADING)
				.addGroup(grouLayoutItensForm.createSequentialGroup()
					.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.LEADING)
						.addGroup(grouLayoutItensForm.createSequentialGroup()
							.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.LEADING)
								.addComponent(lblValor)
								.addComponent(lblNome))
							.addGap(59)
							.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.LEADING)
								.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
								.addGroup(grouLayoutItensForm.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.LEADING, false)
										.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(jtfValorCusto, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
											.addComponent(jtfValor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
										.addComponent(cbxClassificacao, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(lblValorCusto)
						.addComponent(lblClassificacao))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		grouLayoutItensForm.setVerticalGroup(
			grouLayoutItensForm.createParallelGroup(Alignment.LEADING)
				.addGroup(grouLayoutItensForm.createSequentialGroup()
					.addContainerGap()
					.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValor)
						.addComponent(jtfValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblValorCusto)
						.addComponent(jtfValorCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grouLayoutItensForm.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClassificacao)
						.addComponent(cbxClassificacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel.setLayout(grouLayoutItensForm);

		getContentPane().setLayout(groupLayout);

	}
}
