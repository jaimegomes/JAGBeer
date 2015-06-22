package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.controller.ProdutoController;

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
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(row);

		Produto produto;
		try {
			produto = (Produto) new ProdutoController().getPorId(produtoPedido
					.getIdProduto());

			if (column == COL_PRODUTO)
				return produto.getNome();
			else if (column == COL_VALOR)
				return produto.getPrecoVenda();
			else if (column == COL_QUANTIDADE)
				return produtoPedido.getQtde();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ProdutoPedido produtoPedido = (ProdutoPedido) valores.get(rowIndex);

		Produto produto;
		try {
			produto = (Produto) new ProdutoController().getPorId(produtoPedido
					.getIdProduto());

			if (columnIndex == COL_PRODUTO)
				produto.setNome(aValue.toString());
			else if (columnIndex == COL_VALOR)
				produto.setPrecoVenda(Double.parseDouble(aValue.toString()));
			else if (columnIndex == COL_QUANTIDADE)
				produtoPedido.setQtde(Integer.parseInt(aValue.toString()));
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
