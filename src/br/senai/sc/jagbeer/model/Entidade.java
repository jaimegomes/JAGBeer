package br.senai.sc.jagbeer.model;

public abstract class Entidade {

	/**
	 * Método que retorna o id da entidade.
	 * 
	 */
	public abstract int getId();

	/**
	 * Método responsável por atribuir valor ao id da entidade.
	 * 
	 * @param int id
	 */
	public abstract void setId(int id);

}
