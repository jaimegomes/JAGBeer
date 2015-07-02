package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.controller.ProdutoController;

/**
 * Classe que representa a view de produtos mais vendidos
 * 
 * @author Bazzi
 *
 */
public class ProdutosMaisVendidosTableModel extends AbstractTableModel {

	private static long serialVersionUID = 1L;
	private static final int COL_PRODUTO = 0;
	private static final int COL_QUANTIDADE = 1;

	private List<ProdutoPedido> valores;

	public ProdutosMaisVendidosTableModel(List<ProdutoPedido> list) {
		this.valores = new ArrayList<ProdutoPedido>(list);
	}

	/**
	 * Retorna a quantidade de linhas
	 * 
	 * @return valores.size();
	 */
	public int getRowCount() {
		return valores.size();
	}

	/**
	 * Retorna a quantidade de Colunas, deve ser setado manualmente
	 * 
	 * @return 2
	 */
	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int column) {
		if (column == COL_PRODUTO)
			return "Produto";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		return "";
	}

	/**
	 * Verifica onde esta a linha e cluna indicada como parametro
	 * 
	 * @param int rowIndex
	 * @param int columnIndex
	 * 
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(rowIndex);

		if (columnIndex == COL_PRODUTO) {
			int idProduto = produtoPedido.getIdProduto();
			try {
				Produto p = (Produto) new ProdutoController()
						.getPorId(idProduto);

				return p.getNome();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (columnIndex == COL_QUANTIDADE) {
			return produtoPedido.getQtde();
		}
		return "";
	}

	/**
	 * Atribui valor ao objeto que está na linha e coluna que são passados como
	 * parâmetros
	 * 
	 * @param aValue
	 * @param rowIndex
	 * @param columnIndex
	 */
	public void setValuesAt(Object aValue, int rowIndex, int columnIndex) {
		// não utilizado
	}

}
