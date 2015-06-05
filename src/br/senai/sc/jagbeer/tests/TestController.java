package br.senai.sc.jagbeer.tests;

import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Produto;

public class TestController {

	public static void main(String[] args) {

		ProdutoController controller = new ProdutoController();

		Produto produto = new Produto("cerveja", 5.50, 10.0, "bebida");
		
		controller.salvar(produto);
	}

}
