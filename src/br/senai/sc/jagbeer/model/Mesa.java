package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;


/**
 * Classe que representa a entidade Mesa do banco de dados
 * 
 * @author Gabriela
 *
 */
public class Mesa extends Entidade {
	
	private Integer id;
	private Integer numeroMesa;
	private Integer numeroLugares;
	
	/**
	 * Construtor padrão
	 */
	public Mesa() {

	}

	/**
	 * Construtor que recebe como parâmetro o id, o número da mesa e o número de lugares
	 * @param id
	 * @param numeroMesa
	 * @param numeroLugares
	 */
	public Mesa(Integer id, Integer numeroMesa, Integer numeroLugares) {
		this.id = id;
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}	

	/**
	 * Construtor que recebe como parâmetro  o número da mesa e o número de lugares
	 * @param numeroMesa
	 * @param numeroLugares
	 */
	public Mesa(Integer numeroMesa, Integer numeroLugares) {
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}

	@Override
	public int getId() {		
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public Integer getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public Integer getLugares() {
		return numeroLugares;
	}

	public void setLugares(Integer numeroLugares) {
		this.numeroLugares = numeroLugares;
	}

}
