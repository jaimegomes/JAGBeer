package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class EncerrarPedidoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PRODUTO = 0;
	private static final int COL_VALOR = 1;
	private static final int COL_QUANTIDADE = 2;

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
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_PRODUTO)
			return "Produto";
		if (column == COL_VALOR)
			return "Valor";
		if (column == COL_QUANTIDADE)
			return "Quantidade";
		return "";
	}

	public Object getValueAt(int row, int column) {
		EncerrarPedido encerrarPedido = (EncerrarPedido) valores.get(row);
		if (column == COL_PRODUTO)
			return encerrarPedido.getNomeProduto();
		else if (column == COL_VALOR)
			return encerrarPedido.getValor();
		else if (column == COL_QUANTIDADE)
			return encerrarPedido.getQuantidade();
		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		EncerrarPedido encerrarPedido = (EncerrarPedido) valores.get(rowIndex);
		if (columnIndex == COL_PRODUTO)
			encerrarPedido.setNomeProduto(aValue.toString());
		else if (columnIndex == COL_VALOR)
			encerrarPedido.setValor(Double.parseDouble(aValue.toString()));
		else if (columnIndex == COL_QUANTIDADE)
			encerrarPedido.setQuantidade(Integer.parseInt(aValue.toString()));
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
