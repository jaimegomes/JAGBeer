package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class EncerrarPedido extends Entidade {

	private int pedido;
	private String cliente;
	private double valor;
	private int quantidade;

	public EncerrarPedido() {

	}

	public EncerrarPedido(int pedido, String cliente) {
		super();
		this.pedido = pedido;
		this.cliente = cliente;
	}

	public EncerrarPedido(int pedido, String cliente, double valor,
			int quantdade) {
		super();
		this.pedido = pedido;
		this.cliente = cliente;
		this.valor = valor;
		this.quantidade = quantdade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantdade() {
		return quantidade;
	}

	public void setQuantdade(int quantdade) {
		this.quantidade = quantdade;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public void setId(int id) {

	}

}
