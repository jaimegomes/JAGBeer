package br.senai.sc.jagbeer.model;

import java.util.Date;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe Pedido. Atributos: int id, Mesa mesa, Cliente cliente, Date
 * dataPedido, int status;
 * 
 * @author Jaime Gomes
 * 
 */
public class Pedido extends Entidade {

	private int id;
	private Mesa mesa;
	private Cliente cliente;
	private Date dataPedido;
	private int status;

	/**
	 * Construtor padrão.
	 * 
	 * 
	 */
	public Pedido() {
	}

	/**
	 * Construtor que recebe como parâmetro o id.
	 * 
	 * @param int id
	 * 
	 */
	public Pedido(int id) {
		this.id = id;
	}

	/**
	 * Construtor que recebe como parâmetro a mesa, o cliente, a data do pedido
	 * e o status.
	 * 
	 * @param Mesa
	 *            mesa
	 * @param Cliente
	 *            cliente
	 * @param Date
	 *            dataPedido
	 * @param boolean Status
	 * 
	 */
	public Pedido(Mesa mesa, Cliente cliente, Date dataPedido, int status) {
		this.mesa = mesa;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.status = status;
	}

	/**
	 * Construtor que recebe todos os atributos como parâmetros.
	 * 
	 * @param int id
	 * @param Mesa
	 *            mesa
	 * @param Cliente
	 *            cliente
	 * @param Date
	 *            dataPedido
	 * @param boolean Status
	 * 
	 */
	public Pedido(int id, Mesa mesa, Cliente cliente, Date dataPedido,
			int status) {
		this.id = id;
		this.mesa = mesa;
		this.cliente = cliente;
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
	public Mesa getMesa() {
		return mesa;
	}

	/**
	 * @param idMesa
	 *            the idMesa to set
	 */
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	/**
	 * @return the idCliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param idCliente
	 *            the idCliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
