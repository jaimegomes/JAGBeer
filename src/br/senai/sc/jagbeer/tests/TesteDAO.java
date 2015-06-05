package br.senai.sc.jagbeer.tests;

import java.util.Date;

import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;

public class TesteDAO {

	public static void main(String[] args) {

		PedidoDAO dao = new PedidoDAO();

		// Produto produto = new Produto("cerveja", 5.50, 10.0, "bebida");

		Mesa mesa = new Mesa();
		mesa.setId(1);

		Cliente cliente = new Cliente();
		cliente.setId(1);

		try {

			Pedido pedido = new Pedido(mesa, cliente, new Date(), true);
			dao.salvar(pedido);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
