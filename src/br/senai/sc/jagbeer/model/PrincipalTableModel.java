package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class PrincipalTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_CLIENTE = 1;
	private static final int COL_VALOR_PARCIAL = 2;

	private List<Entidade> valores;

	public PrincipalTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	@Override
	public int getRowCount() {
		// numero de linhas
		return valores.size();
	}

	public int getColumnCount() {
		// numero de colunas
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_PEDIDO)
			return "Pedido";
		if (column == COL_CLIENTE)
			return "Cliente";
		if (column == COL_VALOR_PARCIAL)
			return "Valor Parcial";
		return "";
	}

	public Object getValueAt(int row, int column) {
		Pedido pedido = (Pedido) valores.get(row);
		if (column == COL_PEDIDO)
			return pedido.getId();
		else if (column == COL_CLIENTE)
			return pedido.getCliente().getNome();
		else if (column == COL_VALOR_PARCIAL)
			return pedido.getValor();
		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pedido pedido = (Pedido) valores.get(rowIndex);
		if (columnIndex == COL_PEDIDO)
			pedido.setId(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_CLIENTE)
			pedido.getCliente().setNome(aValue.toString());
		else if (columnIndex == COL_VALOR_PARCIAL)
			pedido.setValor(Double.parseDouble(aValue.toString()));
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Cliente get(int row) {
		return (Cliente) valores.get(row);
	}

}
