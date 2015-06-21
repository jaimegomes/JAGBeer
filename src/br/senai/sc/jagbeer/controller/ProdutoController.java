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
			throw new Exception("Produto n�o pode ser nulo.");

		if (produto.getNome().trim().equals(""))
			throw new Exception("Nome do produto obrigat�rio.");

		if (produto.getPrecoVenda() == null || produto.getPrecoVenda() == 0)
			throw new Exception(
					"Valor do produto � obrigat�rio e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("classifica��o do produto obrigat�ria.");

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
			throw new Exception("Produto n�o pode ser nulo.");

		if (produto.getNome().trim().equals(""))
			throw new Exception("Nome do produto obrigat�rio.");

		if (produto.getPrecoVenda() == null || produto.getPrecoVenda() == 0)
			throw new Exception(
					"Valor do produto � obrigat�rio e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("classifica��o do produto obrigat�ria.");

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
			throw new Exception("id n�o pode ser menor que zero.");

		produto = (Produto) dao.getPorId(id);

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception{
		dao.atualizaTabela(table);

	}

	/**
	 * M�todo respons�vel por buscar todos os produtos de determinada
	 * classifica��o passada como par�metro.
	 * 
	 * @param classificacao
	 * @return dao.getPorClassificacao(classificacao)
	 * @throws Exception
	 */
	public List<Entidade> getPorClassificacao(String classificacao)
			throws Exception {

		if (classificacao == null || classificacao.trim().equals(""))
			throw new Exception(
					"A classifica��o n�o pode ser nula ou em branco.");

		return dao.getPorClassificacao(classificacao);
	}

	/**
	 * M�todo respons�vel por buscar todos os produtos de determinado nome
	 * passado como par�metro.
	 * 
	 * @param classificacao
	 * @return dao.getPorNome(nome)
	 * @throws Exception
	 */
	public List<Entidade> getListNomesProdutos(String nome) throws Exception {

		if (nome == null || nome.trim().equals(""))
			throw new Exception("O nome n�o pode ser nulo ou em branco.");

		return dao.getListNomesProdutos(nome);
	}

	/**
	 * M�todo respons�vel por fazer a busca dos produtos de acordo com o nome e
	 * a classifica��o passados como par�metro.
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
	 * M�todo respons�vel por fazer a busca dos produtos de acordo com o nome, a
	 * classifica��o e o valor de venda passados como par�metro.
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
	 * M�todo respons�vel por buscar o produto com o nome passado como
	 * par�metro.
	 * 
	 * @param String nome
	 * @throws Exception
	 */
	public Entidade getPorNome(String nome) throws Exception {

		return dao.getPorNome(nome);
	}

}
