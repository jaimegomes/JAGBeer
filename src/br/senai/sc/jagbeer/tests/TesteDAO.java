package br.senai.sc.jagbeer.tests;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ClienteDAO;
import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.model.PedidoAberto;

public class TesteDAO {

	public static void main(String[] args) throws Exception {

//		PedidoDAO dao = new PedidoDAO();
//
//		for (Entidade e : dao.getListPedidosEmAberto()) {
//			PedidoAberto p = (PedidoAberto) e;
//
//			System.out.println(p.getCliente());
//			System.out.println(p.getValorParcial());
//
//		}
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.getNomeSelecionado("")

	}
}
