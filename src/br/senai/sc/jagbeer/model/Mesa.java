package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class Mesa extends Entidade {
	
	private Integer id;
	private Integer numeroMesa;
	private Integer numeroLugares;
	
	public Mesa() {

	}

	public Mesa(Integer id, Integer numeroMesa, Integer numeroLugares) {
		this.id = id;
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}	

	public Mesa(Integer numeroMesa, Integer numeroLugares) {
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}

	public Mesa(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
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
