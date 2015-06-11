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
					"Preço de venda do produto é obrigatório e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("Classificação do produto obrigatório.");

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
			throw new Exception("Produto não pode ser nulo.");

		if (produto.getNome().trim().equals(""))
			throw new Exception("Nome do produto obrigatório.");

		if (produto.getPrecoVenda() == null || produto.getPrecoVenda() == 0)
			throw new Exception(
					"Preço de venda do produto é obrigatório e deve ser maior que zero.");

		if (produto.getClassificacao().trim().equals(""))
			throw new Exception("Classificação do produto obrigatório.");

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
			throw new Exception("id não pode ser menor que zero.");

		produto = (Produto) dao.getPorId(id);

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) {
		dao.atualizaTabela(table);

	}

}
