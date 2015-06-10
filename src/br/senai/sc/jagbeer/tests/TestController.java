package br.senai.sc.jagbeer.tests;

import javax.swing.JOptionPane;

import br.senai.sc.jagbeer.controller.ClienteController;
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Produto;

public class TestController {

	public static void main(String[] args) {

		// ProdutoController controller = new ProdutoController();

		// Produto produto = new Produto("cerveja", 5.50, 10.0, "bebida");

		// controller.salvar(produto);
		// teste

		ClienteController clienteControl = new ClienteController();
		 Cliente c1 = new Cliente("Zacarias", "048 9999-0001",
		 "zacarias@trapalhoes.com.br");
		// Cliente c2 = new Cliente("Mussum", "048 9999-0002",
		// "mussum@trapalhoes.com.br");
		// Cliente c3 = new Cliente("Didi", "048 9999-0003",
		// "didi@trapalhoes.com.br");

		
		// Cliente c4 = (Cliente) clienteControl.getPorId(4);
		// Cliente c5 = (Cliente) clienteControl.getPorId(5);
		// Cliente c6 = (Cliente) clienteControl.getPorId(6);
		// Cliente c7 = (Cliente) clienteControl.getPorId(7);
		// Cliente c8 = (Cliente) clienteControl.getPorId(8);

		clienteControl.salvar(c1);
		// clienteControl.salvar(c2);
		// clienteControl.salvar(c3);
		// clienteControl.editar(c3);

		// clienteControl.excluir(c4);
		// clienteControl.excluir(c5);
		// clienteControl.excluir(c6);
		// clienteControl.excluir(c7);
		// clienteControl.excluir(c8);
		
		/**
		 * Métodos salvar e excluir funcionando 
		 */

	}

}
