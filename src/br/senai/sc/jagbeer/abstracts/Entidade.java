package br.senai.sc.jagbeer.abstracts;

/**
 * Classe abstrata Entidade, toda classe model deve estender está classe.
 * 
 * @author Jaime Gomes
 * 
 */
public abstract class Entidade {

	/**
	 * Método que retorna o id da entidade.
	 * 
	 */
	public abstract int getId();

	/**
	 * Método responsável¡vel por atribuir valor ao id da entidade.
	 * 
	 * @param int id
	 */
	public abstract void setId(int id);

}
