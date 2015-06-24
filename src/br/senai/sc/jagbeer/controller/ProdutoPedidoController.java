package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ProdutoPedidoDAO;
import br.senai.sc.jagbeer.interfaces.IController;

public class ProdutoPedidoController implements IController {

	private ProdutoPedidoDAO dao = new ProdutoPedidoDAO();

	@Override
	public void salvar(Entidade entidade) throws Exception {
		dao.salvar(entidade);

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {
		dao.excluir(entidade);

	}

	@Override
	public void editar(Entidade entidade) throws Exception {
		dao.editar(entidade);

	}

	@Override
	public List<Entidade> listar() throws Exception {

		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception {
		return dao.getPorId(id);
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {

	}

	public List<Entidade> getPorIdPedido(int idPedido) throws Exception {
		return dao.getPorIdPedido(idPedido);
	}

	public Entidade buscaCompleta(int idProduto, int qtde, int idPedido)
			throws Exception {
		return dao.buscaCompleta(idProduto, qtde, idPedido);
	}

}
