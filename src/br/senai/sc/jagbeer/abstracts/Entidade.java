package br.senai.sc.jagbeer.abstracts;

/**
 * Classe abstrata Entidade, toda classe model deve estender est√° classe.
 * 
 * @author Jaime Gomes
 * 
 */
public abstract class Entidade {

	/**
	 * MÈtodo que retorna o id da entidade.
	 * 
	 */
	public abstract int getId();

	/**
	 * MÈtodo respons·vel°vel por atribuir valor ao id da entidade.
	 * 
	 * @param int id
	 */
	public abstract void setId(int id);

}
