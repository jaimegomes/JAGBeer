package br.senai.sc.jagbeer.controller;

import java.util.Date;
import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Pedido;


/**
 * Classe de controle da entidade Pedido.
 * 
 * @author Jaime Gomes
 *
 */
public class PedidoController implements IController {

	PedidoDAO dao = new PedidoDAO();
	Pedido pedido = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido não pode ser nulo.");

		if (pedido.getCliente() == null)
			throw new Exception("Cliente não pode ser nulo.");

		if (pedido.getDataPedido() == null)
			throw new Exception("Data do pedido não pode ser nula.");

		dao.salvar(pedido);

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido não pode ser nulo.");

		dao.excluir(pedido);
	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido não pode ser nulo.");

		dao.editar(pedido);
	}

	@Override
	public List<Entidade> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		if (id <= 0)
			throw new Exception("Id deve ser maior que zero.");

		return (Pedido) dao.getPorId(id);
	}

	public List<Entidade> getListPedidosEmAberto() throws Exception {
		return dao.getListPedidosEmAberto();
	}

	public List<Entidade> getPorData(Date dataInicio, Date dataFim)
			throws Exception {
		return dao.getPorData(dataInicio, dataFim);
	}
	
	public List<Entidade> getPorDataStatus(Date dataInicio, Date dataFim, int status)
			throws Exception {
		return dao.getPorDataStatus(dataInicio, dataFim, status);
	}
	
	public Entidade getPedidoAbertoPorIdCliente(int id) throws Exception {
		return dao.getPedidoAbertoPorIdCliente(id);
	}

	public Entidade getPorIdCliente(int id) throws Exception {
		return dao.getPorIdCliente(id);
	}
}
