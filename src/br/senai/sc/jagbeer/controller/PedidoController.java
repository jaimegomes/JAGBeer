package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Pedido;

public class PedidoController implements IController {

	PedidoDAO dao = new PedidoDAO();
	Pedido pedido = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido nao pode ser nulo.");

		if (pedido.getCliente() == null)
			throw new Exception("Cliente nao pode ser nulo.");

		if (pedido.getDataPedido() == null)
			throw new Exception("Data do pedido nao pode ser nula.");

		dao.salvar(pedido);

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido nao pode ser nulo.");

		dao.excluir(pedido);
	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		pedido = (Pedido) entidade;

		if (pedido == null)
			throw new Exception("Pedido nao pode ser nulo.");

		dao.editar(pedido);
	}

	@Override
	public List<Entidade> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception {
		return (Pedido) dao.getPorId(id);
	}

	@Override
	public void atualizaTabela(JTable table) {
		dao.atualizaTabela(table);

	}

}
