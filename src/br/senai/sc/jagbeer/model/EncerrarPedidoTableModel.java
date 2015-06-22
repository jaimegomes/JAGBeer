package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class EncerrarPedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_CLIENTE = 1;
	private static final int COL_VALOR = 2;
	private static final int COL_QUANTIDADE = 3;

	private List<Entidade> valores;

	public EncerrarPedidoTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	@Override
	public int getRowCount() {
		// numero de linhas
		return valores.size();
	}

	public int getColumnCount() {
		// numero de colunas
		return 4;
	}

	public String getColumnName(int column) {
		if (column == COL_PEDIDO)
			return "Pedido";
		if (column == COL_CLIENTE)
			return "Cliente";
		if (column == COL_VALOR)
			return "Valor";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		return "";
	}

	public Object getValueAt(int row, int column) {
		EncerrarPedido encerrarPedido = (EncerrarPedido) valores.get(row);
		if (column == COL_PEDIDO)
			return encerrarPedido.getPedido();
		else if (column == COL_CLIENTE)
			return encerrarPedido.getCliente();
		else if (column == COL_VALOR)
			return encerrarPedido.getValor();
		else if (column == COL_QUANTIDADE)
			return encerrarPedido.getQuantdade();
		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		EncerrarPedido encerrarPedido = (EncerrarPedido) valores.get(rowIndex);
		if (columnIndex == COL_PEDIDO)
			encerrarPedido.setPedido(Integer.parseInt(aValue.toString()));
		else if (columnIndex == COL_CLIENTE)
			encerrarPedido.setCliente(aValue.toString());
		else if (columnIndex == COL_VALOR)
			encerrarPedido.setValor(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_QUANTIDADE)
			encerrarPedido.setQuantdade(Integer.parseInt(aValue.toString()));
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
