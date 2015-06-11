package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class ItemPedido extends Entidade {

	private Produto produto;
	private int qtde;

	public ItemPedido() {

	}

	public ItemPedido(Produto produto, int qtde) {
		this.produto = produto;
		this.qtde = qtde;
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto
	 *            the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the qtde
	 */
	public int getQtde() {
		return qtde;
	}

	/**
	 * @param qtde
	 *            the qtde to set
	 */
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub

	}

}
