package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class Mesa extends Entidade {
	
	public int id;
	public int numeroMesa;
	public int numeroLugares;
	//ATUALIZAÇAO
	public Mesa() {

	}

	public Mesa(int id, int numeroMesa, int numeroLugares) {
		this.id = id;
		this.numeroMesa = numeroMesa;
		this.numeroLugares = numeroLugares;
	}	

	public Mesa(int numeroMesa, int numeroLugares) {
		super();
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

	public int getNumero() {
		return numeroMesa;
	}

	public void setNumero(int numeroMesa) {
		this.numeroMesa = numeroMesa;
	}

	public int getLugares() {
		return numeroLugares;
	}

	public void setLugares(int numeroLugares) {
		this.numeroLugares = numeroLugares;
	}
	
	

}
