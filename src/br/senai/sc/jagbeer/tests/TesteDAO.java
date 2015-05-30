package br.senai.sc.jagbeer.tests;

import java.sql.SQLException;

import br.senai.sc.jagbeer.dao.ProdutoDAO;
import br.senai.sc.jagbeer.model.Produto;

public class TesteDAO {

	public static void main(String[] args) {

		ProdutoDAO dao = new ProdutoDAO();

		Produto produto = new Produto("cerveja", 5.50, 10.0, "bebida");
		// Produto produto = (Produto) dao.getPorId(1);

		try {
			dao.salvar(produto);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
