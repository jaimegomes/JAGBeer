package br.senai.sc.jagbeer.controller;

import java.util.Date;
import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ProdutoPedidoDAO;
import br.senai.sc.jagbeer.interfaces.IController;

/**
 * Classe de controle da entidade ProdutoPedido.
 * 
 * @author Jaime Gomes
 *
 */
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

	/**
	 * Método que retorna uma lista de ProdutoPedido de acordo com o id do
	 * pedido passado como parâmetro.
	 * 
	 * @param idPedido
	 * @return List<Entidade> listProdutosPedido
	 * @throws Exception
	 */
	public List<Entidade> getPorIdPedido(int idPedido) throws Exception {
		return dao.getPorIdPedido(idPedido);
	}

	/**
	 * Método que retorna uma lista de ProdutoPedido de acordo com o id do
	 * produto passado como parâmetro.
	 * 
	 * @param idPedido
	 * @return List<Entidade> listProdutosPedido
	 * @throws Exception
	 */
	public List<Entidade> getPorIdProduto(int idProduto) throws Exception {
		return dao.getPorIdProduto(idProduto);
	}

	/**
	 * Retorna um produtopedido referente ao id, qtde e idPedido passados como
	 * parâmetro
	 * 
	 * @param idProduto
	 * @param qtde
	 * @param idPedido
	 * @return produto
	 * @throws Exception
	 */
	public Entidade buscaCompleta(int idProduto, int qtde, int idPedido)
			throws Exception {
		return dao.buscaCompleta(idProduto, qtde, idPedido);
	}

	/**
	 * Método que seleciona todos os produtos pedidos em um determinado período.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return listProdMaisVend
	 * @throws Exception
	 */
	public List<Integer> getIdProdutosPedidoPorPeriodo(Date dataInicio,
			Date dataFim) throws Exception {
		return dao.getIdProdutosPedidoPorPeriodo(dataInicio, dataFim);
	}

	/**
	 * Método que retorna a soma do total de produtos pedidos de acordo com o id
	 * do produto passado como parâmetro.
	 * 
	 * @param idProduto
	 * @return qtde total de produtos
	 * @throws Exception
	 */
	public int getQtdeProduto(int idProduto) throws Exception {
		return dao.getQtdeProduto(idProduto);
	}
}
