package br.senai.sc.jagbeer.interfaces;

import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Interface Controller, a classe que estender esta interface deve sobrescrever
 * todos os métodos que forem usados.
 * 
 * @author Jaime Gomes
 * 
 */
public interface IController {

	/**
	 * Método responsável por persistir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public void salvar(Entidade entidade) throws Exception;

	/**
	 * Método responsável por excluir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public void excluir(Entidade entidade) throws Exception;

	/**
	 * Método responsável por editar a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public void editar(Entidade entidade) throws Exception;

	/**
	 * Método que retorna uma lista de entidade
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @return List<Entidade>
	 */
	public List<Entidade> listar() throws Exception;

	/**
	 * Método que retorna a entidade referente ao id passado como parâmetro
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public Entidade getPorId(int id) throws Exception;

}