package br.senai.sc.jagbeer.abstracts;

import java.util.List;

import javax.swing.JTable;

/**
 * Classe abstrata DAO que contém os métodos necessários para a manipulação das
 * Entidades no banco de dados.
 * 
 * @author Jaime Gomes
 * 
 */
public abstract class GenericDAO {

	/**
	 * Método responsável por persistir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @throws Exception
	 */
	public abstract void salvar(Entidade entidade) throws Exception;

	/**
	 * Método responsável por excluir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @throws Exception
	 */
	public abstract void excluir(Entidade entidade) throws Exception;

	/**
	 * Método responsável por editar a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @throws Exception
	 */
	public abstract void editar(Entidade entidade) throws Exception;

	/**
	 * Método que retorna uma lista de entidade
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @throws Exception
	 * 
	 * @return List<Entidade>
	 */
	public abstract List<Entidade> listar() throws Exception;

	/**
	 * Método que retorna a entidade referente ao id passado como parâmetro
	 * 
	 * @param int id
	 * 
	 * @return Entidade
	 * 
	 * @throws Exception
	 */
	public abstract Entidade getPorId(int id) throws Exception;


}
