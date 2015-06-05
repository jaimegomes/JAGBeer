package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ProdutoDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Produto;

public class ProdutoController implements IController {

	ProdutoDAO dao = new ProdutoDAO();

	@Override
	public void salvar(Entidade entidade) {

		try {
			Produto produto = (Produto) entidade;

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

		} catch (Exception e) {
			System.out.println("[ProdutoController] - Erro ao salvar produto. "
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ProdutoController] - Erro ao salvar produto. ");
		}
	}

	@Override
	public void excluir(Entidade entidade) {

		try {

			Produto produto = (Produto) entidade;

			if (produto == null)
				throw new Exception("Selecione um produto.");

			dao.excluir(produto);
		} catch (Exception e) {
			System.out
					.println("[ProdutoController] - Erro ao excluir produto. "
							+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ProdutoController] - Erro ao excluir produto. ");
		}

	}

	@Override
	public void editar(Entidade entidade) {
		try {
			Produto produto = (Produto) entidade;

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

		} catch (Exception e) {
			System.out.println("[ProdutoController] - Erro ao editar produto. "
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ProdutoController] - Erro ao editar produto. ");
		}

	}

	@Override
	public List<Entidade> listar() {
		List<Entidade> listEntidade = null;
		try {

			listEntidade = dao.listar();
		} catch (Exception e) {
			System.out
					.println("[ProdutoController] - Erro ao listar produtos. "
							+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ProdutoController] - Erro ao listar produtos. ");
		}
		return listEntidade;
	}

	@Override
	public Entidade getPorId(int id) {
		Produto produto = null;

		try {
			if (id < 0)
				throw new Exception("id não pode ser menor que zero.");

			produto = (Produto) dao.getPorId(id);

		} catch (Exception e) {
			System.out
					.println("[ProdutoController] - Erro ao buscar produto por id. "
							+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ProdutoController] - Erro ao buscar produto por id. ");
		}
		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) {
		dao.atualizaTabela(table);

	}

}
