package br.senai.sc.jagbeer.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ProdutoController;

public class RelatorioFaturamentoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_PEDIDO = 0;
	private static final int COL_VALOR = 1;
	private static final int COL_DATA = 2;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Entidade> valores;

	public RelatorioFaturamentoTableModel(List<Entidade> list) {
		this.valores = new ArrayList<Entidade>(list);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	public String getColumnName(int column) {
		if (column == COL_PEDIDO)
			return "Pedido";
		if (column == COL_VALOR)
			return "Valor";
		if (column == COL_DATA)
			return "Data";
		return "";
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(row);
		Pedido pedido = (Pedido) valores.get(row);
		Produto produto;
		try {
			produto = (Produto) new ProdutoController().getPorId(produtoPedido
					.getIdProduto());

			if (column == COL_PEDIDO)
				return produtoPedido.getIdPedido();
			else if (column == COL_VALOR)
				return produto.getPrecoVenda();
			else if (column == COL_DATA)
				return pedido.getDataPedido();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(rowIndex);
		Pedido pedido = (Pedido) valores.get(rowIndex);
		Produto produto;
		try {
			produto = (Produto) new ProdutoController().getPorId(produtoPedido
					.getIdProduto());

			if (columnIndex == COL_PEDIDO)
				pedido.setId(Integer.parseInt(aValue.toString()));
			else if (columnIndex == COL_VALOR)
				produto.setPrecoVenda(Double.parseDouble(aValue.toString()));
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
		return true;
	}

	public Cliente get(int row) {
		return (Cliente) valores.get(row);
	}

}
