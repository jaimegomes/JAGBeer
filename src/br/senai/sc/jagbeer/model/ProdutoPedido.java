package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class ProdutoPedido extends Entidade {

	private int id;
	private Integer idProduto;
	private Integer idPedido;

	public ProdutoPedido() {
	}

	public ProdutoPedido(int id) {
		this.id = id;
	}

	public ProdutoPedido(Integer idProduto, Integer idPedido) {
		this.idProduto = idProduto;
		this.idPedido = idPedido;
	}

	public ProdutoPedido(int id, Integer idProduto, Integer idPedido) {
		this.id = id;
		this.idProduto = idProduto;
		this.idPedido = idPedido;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	/**
	 * @return the idProduto
	 */
	public Integer getIdProduto() {
		return idProduto;
	}

	/**
	 * @param idProduto
	 *            the idProduto to set
	 */
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	/**
	 * @return the idPedido
	 */
	public Integer getIdPedido() {
		return idPedido;
	}

	/**
	 * @param idPedido
	 *            the idPedido to set
	 */
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

}
