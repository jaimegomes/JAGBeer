package br.senai.sc.jagbeer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a tabela da view RelatorioFaturamentoUI
 * 
 * @author Jaime Gomes
 *
 */
public class RelatorioPedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_DATA = 1;
	private static final int COL_VALOR = 2;
	private static final int COL_STATUS = 3;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Entidade> valores;

	public RelatorioPedidoTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	/**
	 * Retorna a quantidade de linhas da lista.
	 * 
	 * @return valores.size();
	 */
	@Override
	public int getRowCount() {
		return valores.size();
	}

	/**
	 * Retorna a quantidade de colunas, deve ser setado manualmente.
	 * 
	 * @return 4
	 */
	@Override
	public int getColumnCount() {
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
		if (column == COL_DATA)
			return "Data";
		if (column == COL_VALOR)
			return "Valor";
		if (column == COL_STATUS)
			return "Status";
		return "";
	}

	/**
	 * Retorna o objeto que est� na linha e coluna indicada como par�metros.
	 * 
	 * @param int row
	 * @param int column
	 */
	@Override
	public Object getValueAt(int row, int column) {
		Pedido pedido = (Pedido) valores.get(row);
		try {

			if (column == COL_PEDIDO)
				return pedido.getId();
			else if (column == COL_VALOR)
				return pedido.getValor();
			else if (column == COL_DATA)
				return sdf.format(pedido.getDataPedido());
			else if (column == COL_STATUS) {
				if (pedido.getStatus() == 1) {
					return "Aberto";
				} else {
					return "Fechado";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * Atribui valor ao objeto que est� na linha e coluna que são passados como
	 * par�metros.
	 * 
	 * @param Object
	 *            aValue
	 * @param int rowIndex
	 * @param int columnIndex
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pedido pedido = (Pedido) valores.get(rowIndex);
		try {

			if (columnIndex == COL_PEDIDO)
				pedido.setId(Integer.parseInt(aValue.toString()));
			else if (columnIndex == COL_VALOR)
				pedido.setValor(Double.parseDouble(aValue.toString()));
			else if (columnIndex == COL_DATA)
				pedido.setDataPedido(sdf.parse(aValue.toString()));
			else if (columnIndex == COL_STATUS)
				pedido.setStatus(Integer.parseInt(aValue.toString()));
		} catch (Exception e) {
			e.printStackTrace();
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

}
