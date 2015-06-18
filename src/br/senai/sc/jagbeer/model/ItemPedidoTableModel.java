package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class ItemPedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME_PRODUTO = 0;
	private static final int COL_QTDE = 1;

	private List<Entidade> valores;

	public ItemPedidoTableModel(List<Entidade> list) {
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
	 * @return 2
	 */
	public int getColumnCount() {
		return 2;
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
		return "";
	}

	/**
	 * Retorna o objeto que está na linha e coluna indicada como parâmetros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		ItemPedido itemPedido = (ItemPedido) valores.get(row);
		if (column == COL_NOME_PRODUTO)
			return itemPedido.getProduto().getNome();
		else if (column == COL_QTDE)
			return itemPedido.getQtde();
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
		
		ItemPedido itemPedido = (ItemPedido) valores.get(rowIndex);
		if (columnIndex == COL_NOME_PRODUTO)
			itemPedido.getProduto().setNome(aValue.toString());
		else if (columnIndex == COL_QTDE)
			itemPedido.setQtde(Integer.parseInt(aValue.toString()));
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
	 * Verifica se a célula passada como parâmetro Ã© editÃ¡vel.
	 * 
	 * @param int rowIndex
	 * @param int columnIndex
	 * @return boolean true
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	/**
	 * Retorna o objeto que está na linha passada como parâmetro.
	 * 
	 * @param int row
	 * @return Aluno valores.get(row)
	 */
	public Produto get(int row) {
		return (Produto) valores.get(row);
	}

}
