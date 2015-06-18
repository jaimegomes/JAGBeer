package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.senai.sc.jagbeer.abstracts.Entidade;

public class MesaTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private static final int COL_NUMEROMESA = 0;
	private static final int COL_LUGARES = 1;
	
	private List<Entidade> valores;
	
	public MesaTableModel(List<Entidade> list){
		this.valores = new ArrayList<Entidade>(list);
	}
	
	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.
		return valores.size();
	}

	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 2.
		return 2;
	}
	
	public String getColumnName(int column) {
		//Qual é o nome das nossas colunas?
		if (column == COL_NUMEROMESA) return "Numero Mesa";
		if (column == COL_LUGARES) return "Lugares";
		return ""; //Nunca deve ocorrer
	}
	
	public Object getValueAt(int row, int column) {
		//Precisamos retornar o valor da coluna column e da linha row.
		Mesa mesa = (Mesa) valores.get(row);
		if (column == COL_NUMEROMESA){
			return mesa.getNumeroMesa();
		}

		else if (column == COL_LUGARES){
			return mesa.getLugares();
		}
		
		return "";
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Mesa mesa = (Mesa) valores.get(rowIndex);
		if (columnIndex == COL_NUMEROMESA)
			mesa.setNumeroMesa(columnIndex);
		else if (columnIndex == COL_LUGARES)
			mesa.setLugares(columnIndex);

	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Mesa get(int row) {
		return (Mesa) valores.get(row);
	}

}
