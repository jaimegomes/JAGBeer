package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class PedidoAberto extends Entidade {

	private int pedido;
	private String cliente;
	private double valorParcial;

	public PedidoAberto() {

	}

	public PedidoAberto(int pedido, String cliente) {
		this.pedido = pedido;
		this.cliente = cliente;
	}

	public PedidoAberto(int pedido, String cliente, double valorParcial) {
		this.pedido = pedido;
		this.cliente = cliente;
		this.valorParcial = valorParcial;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public void setId(int id) {

	}

	public double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(double valorParcial) {
		this.valorParcial = valorParcial;
	}

}
