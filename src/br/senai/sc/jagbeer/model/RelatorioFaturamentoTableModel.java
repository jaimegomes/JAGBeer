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
public class RelatorioFaturamentoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_DATA = 1;
	private static final int COL_VALOR = 2;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Entidade> valores;

	public RelatorioFaturamentoTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	@Override
	public int getRowCount() {
		return valores.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_PEDIDO)
			return "Pedido";
		if (column == COL_DATA)
			return "Data";
		if (column == COL_VALOR)
			return "Valor";
		return "";
	}

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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pedido pedido = (Pedido) valores.get(rowIndex);
		try {

			if (columnIndex == COL_PEDIDO)
				pedido.setId(Integer.parseInt(aValue.toString()));
			else if (columnIndex == COL_VALOR)
				pedido.setValor(Double.parseDouble(aValue.toString()));
			else if (columnIndex == COL_DATA)
				pedido.setDataPedido(sdf.parse(aValue.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
