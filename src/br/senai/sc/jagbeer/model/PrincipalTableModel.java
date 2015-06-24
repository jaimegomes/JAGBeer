package br.senai.sc.jagbeer.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a tabela da view PrincipalUI
 * 
 * @author Jaime Gomes
 *
 */
public class PrincipalTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_CLIENTE = 1;
	private static final int COL_VALOR_PARCIAL = 2;
	private static final int COL_DATA_PEDIDO = 3;

	private List<Entidade> valores;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public PrincipalTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	/**
	 * Retorna a quantidade de linhas da lista.
	 * 
	 * @return valores.size();
	 */
	@Override
	public int getRowCount() {
		// numero de linhas
		return valores.size();
	}

	/**
	 * Retorna a quantidade de colunas, deve ser setado manualmente.
	 * 
	 * @return 4
	 */
	public int getColumnCount() {
		// numero de colunas
		return 4;
	}

	/**
	 * Retorna o nome da coluna passada como par�metro.
	 * 
	 * @param int colunm
	 */
	public String getColumnName(int column) {
		if (column == COL_PEDIDO)
			return "Pedido";
		if (column == COL_CLIENTE)
			return "Cliente";
		if (column == COL_VALOR_PARCIAL)
			return "Valor Parcial";
		if (column == COL_DATA_PEDIDO)
			return "Data";
		return "";
	}

	/**
	 * Retorna o objeto que est� na linha e coluna indicada como par�metros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		Pedido pedido = (Pedido) valores.get(row);

		if (pedido != null) {
			if (column == COL_PEDIDO)
				return pedido.getId();
			else if (column == COL_CLIENTE)
				if (pedido.getCliente() != null) {
					return pedido.getCliente().getNome();
				} else {
					return "";
				}
			else if (column == COL_VALOR_PARCIAL)
				return pedido.getValor();
			else if (column == COL_DATA_PEDIDO)
				return sdf.format(pedido.getDataPedido());
		}
		return "";
	}

	/**
	 * Atribui valor ao objeto que est� na linha e coluna que s�o passados como
	 * par�metros.
	 * 
	 * @param Object
	 *            aValue
	 * @param int rowIndex
	 * @param int columnIndex
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pedido pedido = (Pedido) valores.get(rowIndex);

		if (pedido != null) {
			if (columnIndex == COL_PEDIDO)
				pedido.setId(Integer.parseInt(aValue.toString()));
			else if (columnIndex == COL_CLIENTE)
				pedido.getCliente().setNome(aValue.toString());
			else if (columnIndex == COL_VALOR_PARCIAL)
				pedido.setValor(Double.parseDouble(aValue.toString()));
			else if (columnIndex == COL_DATA_PEDIDO)
				try {
					pedido.setDataPedido(sdf.parse(aValue.toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * Retorna a classe da coluna passada como par�metro, caso tenha mais de um
	 * tipo de par�metro fazer um if como em setValueAt para verificar qual a
	 * columnIndex se trata e retornar o tipo da classe.
	 * 
	 * @param int columnIndex
	 * @return Class<?>
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	/**
	 * Verifica se a c�lula passada como par�metro � edit�vel.
	 * 
	 * @param int rowIndex
	 * @param int columnIndex
	 * @return boolean true
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Retorna o objeto que est� na linha passada como par�metro.
	 * 
	 * @param int row
	 * @return Produto valores.get(row)
	 */
	public Pedido get(int row) {
		return (Pedido) valores.get(row);
	}

}
