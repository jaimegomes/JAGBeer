
package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ProdutoController;

/**
 * Classe que representa a tabela da view FazerPedidoUI
 * 
 * @author Jaime Gomes
 *
 */
public class FazerPedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_QTDE = 1;
	private static final int COL_VALOR = 2;

	private List<Entidade> valores;

	public FazerPedidoTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	/**
	 * Retorna a quantidade de linhas da lista.
	 * 
	 * @return valores.size();
	 */
	public int getRowCount() {
		return valores.size();
	}

	/**
	 * Retorna a quantidade de colunas, deve ser setado manualmente.
	 * 
	 * @return 3
	 */
	public int getColumnCount() {
		return 3;
	}

	/**
	 * Retorna o nome da coluna passada como parâmetro.
	 * 
	 * @param int colunm
	 */
	public String getColumnName(int column) {
		if (column == COL_NOME_PRODUTO)
			return "Produto";
		if (column == COL_QTDE)
			return "Quantidade";
		if (column == COL_VALOR)
			return "Valor Unitário";
		return "";
	}

	/**
	 * Retorna o objeto que está na linha e coluna indicada como parâmetros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(row);

		try {
			Produto produto = (Produto) new ProdutoController()
					.getPorId(produtoPedido.getIdProduto());

			if (column == COL_NOME_PRODUTO) {

				if (produto != null) {
					return produto.getNome();
				} else {
					return "";
				}
			} else if (column == COL_QTDE)
				return produtoPedido.getQtde();
			else if (column == COL_VALOR) {
				if (produto != null) {
					return produto.getPrecoVenda();
				} else {
					return "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Atribui valor ao objeto que está na linha e coluna que são passados como
	 * parâmetros.
	 * 
	 * @param Object
	 *            aValue
	 * @param int rowIndex
	 * @param int columnIndex
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(rowIndex);
		Produto produto = null;
		try {
			produto = (Produto) new ProdutoController().getPorId(produtoPedido
					.getIdProduto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (columnIndex == COL_NOME_PRODUTO)
			produto.setNome(aValue.toString());
		else if (columnIndex == COL_QTDE)
			produtoPedido.setQtde(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_VALOR)
			produto.setPrecoVenda(Double.parseDouble(aValue.toString()));
	}

	/**
	 * Retorna a classe da coluna passada como parâmetro, caso tenha mais de um
	 * tipo de parâmetro fazer um if como em setValueAt para verificar qual a
	 * columnIndex se trata e retornar o tipo da classe.
	 * 
	 * @param int columnIndex
	 * @return Class<?>
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	/**
	 * Verifica se a célula passada como parâmetro é editável.
	 * 
	 * @param int rowIndex
	 * @param int columnIndex
	 * @return boolean true
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Retorna o objeto que está na linha passada como parâmetro.
	 * 
	 * @param int row
	 * @return Produto valores.get(row)
	 */
	public Produto get(int row) {
		return (Produto) valores.get(row);
	}

}
