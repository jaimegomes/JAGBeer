package br.senai.sc.jagbeer.model;

import java.util.Date;

import br.senai.sc.jagbeer.abstracts.Entidade;

public class Pedido extends Entidade {

	private int id;
	private Integer idMesa;
	private Integer idCliente;
	private Date dataPedido;
	private boolean status;

	public Pedido() {
	}

	public Pedido(int id) {
		this.id = id;
	}

	public Pedido(Integer idMesa, Integer idCliente, Date dataPedido,
			boolean status) {
		this.idMesa = idMesa;
		this.idCliente = idCliente;
		this.dataPedido = dataPedido;
		this.status = status;
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
	 * @return the idMesa
	 */
	public Integer getIdMesa() {
		return idMesa;
	}

	/**
	 * @param idMesa
	 *            the idMesa to set
	 */
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}

	/**
	 * @return the idCliente
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the dataPedido
	 */
	public Date getDataPedido() {
		return dataPedido;
	}

	/**
	 * @param dataPedido
	 *            the dataPedido to set
	 */
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}
