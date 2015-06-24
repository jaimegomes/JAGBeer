package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a tabela da view ConsultaMesaUI
 * 
 * @author Gabriela
 *
 */
public class MesaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NUMEROMESA = 0;
	private static final int COL_LUGARES = 1;

	private List<Entidade> valores;

	public MesaTableModel(List<Entidade> list) {
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
		if (column == COL_NUMEROMESA)
			return "Numero Mesa";
		if (column == COL_LUGARES)
			return "Lugares";
		return "";
	}

	/**
	 * Retorna o objeto que está na linha e coluna indicada como parâmetros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		Mesa mesa = (Mesa) valores.get(row);
		if (column == COL_NUMEROMESA) {
			return mesa.getNumeroMesa();
		}

		else if (column == COL_LUGARES) {
			return mesa.getLugares();
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
		Mesa mesa = (Mesa) valores.get(rowIndex);
		if (columnIndex == COL_NUMEROMESA)
			mesa.setNumeroMesa(columnIndex);
		else if (columnIndex == COL_LUGARES)
			mesa.setLugares(columnIndex);

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
	public Mesa get(int row) {
		return (Mesa) valores.get(row);
	}

}
