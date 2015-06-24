package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a entidade Produto do banco de dados
 * 
 * @author Jaime Gomes
 * 
 */
public class ProdutoPedido extends Entidade {

	private int id;
	private Integer idProduto;
	private Integer idPedido;
	private Integer qtde;

	/**
	 * Construtor padrão
	 */
	public ProdutoPedido() {
	}

	/**
	 * Construtor padrão que recebe o id como parâmetro
	 * 
	 * @param id
	 */
	public ProdutoPedido(int id) {
		this.id = id;
	}

	/**
	 * Construtor que recebe como parâmetro o id do produto, id do pedido e
	 * quantidade
	 * 
	 * @param idProduto
	 * @param idPedido
	 * @param qtde
	 */
	public ProdutoPedido(Integer idProduto, Integer idPedido, Integer qtde) {
		this.idProduto = idProduto;
		this.idPedido = idPedido;
		this.qtde = qtde;
	}

	/**
	 * Construtor que recebe como parâmetro o id, id do produto, id do pedido e
	 * quantidade
	 * 
	 * @param id
	 * @param idProduto
	 * @param idPedido
	 * @param qtde
	 */
	public ProdutoPedido(int id, Integer idProduto, Integer idPedido,
			Integer qtde) {
		this.id = id;
		this.idProduto = idProduto;
		this.idPedido = idPedido;
		this.qtde = qtde;
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

	/**
	 * @return the qtde
	 */
	public Integer getQtde() {
		return qtde;
	}

	/**
	 * @param qtde
	 *            the qtde to set
	 */
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

}
