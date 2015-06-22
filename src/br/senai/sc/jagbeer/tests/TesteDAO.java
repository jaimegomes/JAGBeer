package br.senai.sc.jagbeer.tests;

import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.dao.MesaDAO;
import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.model.Mesa;

public class TesteDAO {

	public static void main(String[] args) throws Exception {

		PedidoDAO dao = new PedidoDAO();
		MesaDAO mDAO = new MesaDAO();

		// ClienteDAO cdao = new ClienteDAO();
		//
		// Cliente c = (Cliente) cdao.getPorId(1);
		//
		// Pedido p = new Pedido(null, c, new Date(), 1);

		Mesa mesa = new Mesa(33, 4);
//		mDAO.salvar(mesa);
		
		new MesaController().salvar(mesa);


	}
}
