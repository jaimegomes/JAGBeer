package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class Mesa extends Entidade {
	
	private int id;
	private int numeroMesa;
	private int numeroLugares;
	
	public Mesa() {

	}

	public Mesa(int id, int numeroMesa, int numeroLugares) {
		this.id = id;
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}	

	public Mesa(int numeroMesa, int numeroLugares) {
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}

	public Mesa(int numeroMesa) {
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

	public int getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getLugares() {
		return numeroLugares;
	}

	public void setLugares(int numeroLugares) {
		this.numeroLugares = numeroLugares;
	}

}
