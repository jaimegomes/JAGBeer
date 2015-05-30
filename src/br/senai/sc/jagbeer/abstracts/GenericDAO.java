package br.senai.sc.jagbeer.abstracts;

import java.util.List;

import javax.swing.JTable;

public abstract class GenericDAO {

	/**
	 * Método responsável por persistir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void salvar(Entidade entidade) throws Exception;

	/**
	 * Método responsável por excluir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void excluir(Entidade entidade) throws Exception;

	/**
	 * Método responsável por editar a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void editar(Entidade entidade) throws Exception;

	/**
	 * Método que retorna uma lista de entidade
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @return List<Entidade>
	 */
	public abstract List<Entidade> listar() throws Exception;

	/**
	 * Método que retorna a entidade referente ao id passado como parâmetro
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract Entidade getPorId(int id) throws Exception;

	/**
	 * Método responsável por atualizar a tabela passada como parâmetro.
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void atualizaTabela(JTable table) throws Exception;

}
