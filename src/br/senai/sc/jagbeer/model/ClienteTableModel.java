package br.senai.sc.jagbeer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a tabela de cliente
 * 
 * @author Bazzi
 *
 */
public class ClienteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
	private static final int COL_TELEFONE = 1;
	private static final int COL_EMAIL = 2;

	private List<Entidade> valores;

	public ClienteTableModel(List<Entidade> list) {
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
	 * @return 3
	 */
	public int getColumnCount() {
		return 3;
	}

	/**
	 * Retorna o nome da coluna passada como parâmetro.
	 * 
	 * @param int colunm
	 */
	public String getColumnName(int column) {
		if (column == COL_NOME)
			return "Nome";
		if (column == COL_TELEFONE)
			return "Telefone";
		if (column == COL_EMAIL)
			return "E-mail";
		return "";
	}

	/**
	 * Retorna o objeto que está na linha e coluna indicada como parâmetros.
	 * 
	 * @param int row
	 * @param int column
	 */
	public Object getValueAt(int row, int column) {
		Cliente cliente = (Cliente) valores.get(row);
		if (column == COL_NOME)
			return cliente.getNome();
		else if (column == COL_TELEFONE)
			return cliente.getTelefone();
		else if (column == COL_EMAIL)
			return cliente.getEmail();
		return "";
	}

	/**
	 * Atribui valor ao objeto que está na linha e coluna que são passados como
	 * parâmetros.
	 * 
	 * @param Object
	 *            aValue
	 * @param int rowIndex
	 * @param int columnIndex
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = (Cliente) valores.get(rowIndex);
		if (columnIndex == COL_NOME)
			cliente.setNome(aValue.toString());
		else if (columnIndex == COL_TELEFONE)
			cliente.setTelefone(aValue.toString());
		else if (columnIndex == COL_EMAIL)
			cliente.setEmail(aValue.toString());
	}

	/**
	 * Retorna a classe da coluna passada como parâmetro, caso tenha mais de um
	 * tipo de parâmetro fazer um if como em setValueAt para verificar qual a
	 * columnIndex se trata e retornar o tipo da classe.
	 * 
	 * @param int columnIndex
	 * @return Class<?>
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	/**
	 * Verifica se a célula passada como parâmetro é editável.
	 * 
	 * @param int rowIndex
	 * @param int columnIndex
	 * @return boolean true
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Retorna o objeto que está na linha passada como parâmetro.
	 * 
	 * @param int row
	 * @return Cliente valores.get(row)
	 */
	public Cliente get(int row) {
		return (Cliente) valores.get(row);
	}

}
