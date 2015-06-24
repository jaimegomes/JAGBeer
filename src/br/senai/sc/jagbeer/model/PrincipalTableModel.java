package br.senai.sc.jagbeer.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

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
		if (column == COL_VALOR_PARCIAL)
			return "Valor Parcial";
		if (column == COL_DATA_PEDIDO)
			return "Data";
		return "";
	}

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
