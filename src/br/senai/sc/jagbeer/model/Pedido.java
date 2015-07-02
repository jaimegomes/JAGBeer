package br.senai.sc.jagbeer.model;

import java.util.Date;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a entidade Pedido do banco de dados
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
	private Double valor;

	/**
	 * Construtor padrão.
	 * 
	 */
	public Pedido() {
	}

	/**
	 * Construtor que recebe como parâmetro o id, a data e o valor
	 * 
	 * @param int id
	 * @param Date
	 *            dataPedido
	 * @param Double
	 *            valor
	 * @param int status
	 */
	public Pedido(int id, Date dataPedido, Double valor, int status) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.valor = valor;
		this.status = status;
	}

	/**
	 * Construtor que recebe como parâmetro o id, a data e o valor
	 * 
	 * @param int id
	 * @param Date
	 *            dataPedido
	 * @param Double
	 *            valor
	 */
	public Pedido(int id, Date dataPedido, Double valor) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.valor = valor;
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
	 * e o status, valor.
	 * 
	 * @param Mesa
	 *            mesa
	 * @param Cliente
	 *            cliente
	 * @param Date
	 *            dataPedido
	 * @param boolean Status
	 * 
	 * @param Double
	 *            valor
	 * 
	 */
	public Pedido(Mesa mesa, Cliente cliente, Date dataPedido, int status,
			Double valor) {
		this.mesa = mesa;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.status = status;
		this.valor = valor;
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
	 * @param Double
	 *            valor
	 * 
	 */
	public Pedido(int id, Mesa mesa, Cliente cliente, Date dataPedido,
			int status, Double valor) {
		this.id = id;
		this.mesa = mesa;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
		this.status = status;
		this.valor = valor;
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

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
