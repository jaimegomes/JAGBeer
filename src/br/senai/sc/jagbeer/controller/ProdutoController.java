package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ProdutoDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Produto;

public class ProdutoController implements IController {

	ProdutoDAO dao = new ProdutoDAO();
	Produto produto = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		produto = (Produto) entidade;

		if (produto == null)
			throw new Exception("Produto não pode ser nulo.");

		if (produto.getNome().trim().equals(""))
			throw new Exception("Nome do produto obrigatório.");

		if (produto.getPrecoVenda() == null || produto.getPrecoVenda() == 0)
			throw new Exception(
					"Valor do produto é obrigatorio e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("classificaóóo do produto obrigatória.");

		dao.salvar(produto);

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		produto = (Produto) entidade;

		if (produto == null)
			throw new Exception("Selecione um produto.");

		dao.excluir(produto);

	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		produto = (Produto) entidade;

		if (produto == null)
			throw new Exception("Produto nóo pode ser nulo.");

		if (produto.getNome().trim().equals(""))
			throw new Exception("Nome do produto obrigatório.");

		if (produto.getPrecoVenda() == null || produto.getPrecoVenda() == 0)
			throw new Exception(
					"Valor do produto ó obrigatório e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("classificaóóo do produto obrigatória.");

		dao.editar(produto);

	}

	@Override
	public List<Entidade> listar() throws Exception {

		List<Entidade> listEntidade = null;

		listEntidade = dao.listar();
		return listEntidade;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		produto = null;

		if (id < 0)
			throw new Exception("id nóo pode ser menor que zero.");

		produto = (Produto) dao.getPorId(id);

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception{
		dao.atualizaTabela(table);

	}

	/**
	 * Mótodo responsóvel por buscar todos os produtos de determinada
	 * classificaóóo passada como parómetro.
	 * 
	 * @param classificacao
	 * @return dao.getPorClassificacao(classificacao)
	 * @throws Exception
	 */
	public List<Entidade> getPorClassificacao(String classificacao)
			throws Exception {

		if (classificacao == null || classificacao.trim().equals(""))
			throw new Exception(
					"A classificaóóo nóo pode ser nula ou em branco.");

		return dao.getPorClassificacao(classificacao);
	}

	/**
	 * Mótodo responsóvel por buscar todos os produtos de determinado nome
	 * passado como parómetro.
	 * 
	 * @param classificacao
	 * @return dao.getPorNome(nome)
	 * @throws Exception
	 */
	public List<Entidade> getListNomesProdutos(String nome) throws Exception {

		if (nome == null || nome.trim().equals(""))
			throw new Exception("O nome nóo pode ser nulo ou em branco.");

		return dao.getListNomesProdutos(nome);
	}

	/**
	 * Mótodo responsóvel por fazer a busca dos produtos de acordo com o nome e
	 * a classificaóóo passados como parómetro.
	 * 
	 * @param nome
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 */
	public List<Entidade> buscaPorNomeClassificacao(String nome,
			String classificacao) throws Exception {

		return dao.buscaPorNomeClassificacao(nome, classificacao);

	}

	/**
	 * Mótodo responsóvel por fazer a busca dos produtos de acordo com o nome, a
	 * classificaóóo e o valor de venda passados como parómetro.
	 * 
	 * @param nome
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 */
	public Entidade buscaCompleta(String nome, Double precoVenda,
			String classificacao) throws Exception {

		return dao.buscaCompleta(nome, precoVenda, classificacao);

	}

	/**
	 * Método responsóvel por buscar o produto com o nome passado como
	 * parâmetro.
	 * 
	 * @param String nome
	 * @throws Exception
	 */
	public Entidade getPorNome(String nome) throws Exception {

		return dao.getPorNome(nome);
	}

}
