package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class ProdutoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_PRECO_CUSTO = 1;
	private static final int COL_PRECO_VENDA = 2;
	private static final int COL_CLASSIFICACAO = 3;

	private List<Entidade> valores;

	public ProdutoTableModel(List<Entidade> list) {
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
	 * @return 4
	 */
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Retorna o nome da coluna passada como parâmetro.
	 * 
	 * @param int colunm
	 */
	public String getColumnName(int column) {
		if (column == COL_NOME)
			return "Nome";
		if (column == COL_PRECO_CUSTO)
			return "Preco Custo";
		if (column == COL_PRECO_VENDA)
			return "Preco Venda";
		if (column == COL_CLASSIFICACAO)
			return "Classificacao";
		return "";
	}

	/**
	 * Retorna o objeto que está na linha e coluna indicada como parâmetros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		Produto produto = (Produto) valores.get(row);
		if (column == COL_NOME)
			return produto.getNome();
		else if (column == COL_PRECO_CUSTO)
			return produto.getPrecoCusto();
		else if (column == COL_PRECO_VENDA)
			return produto.getPrecoVenda();
		else if (column == COL_CLASSIFICACAO)
			return produto.getClassificacao();
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
		Produto produto = (Produto) valores.get(rowIndex);

		if (columnIndex == COL_NOME)
			produto.setNome(aValue.toString());
		else if (columnIndex == COL_PRECO_CUSTO)
			produto.setPrecoCusto(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_PRECO_VENDA)
			produto.setPrecoVenda(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_CLASSIFICACAO)
			produto.setClassificacao(aValue.toString());
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
