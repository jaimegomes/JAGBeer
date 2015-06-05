package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.PedidoDAO;
import br.senai.sc.jagbeer.interfaces.IController;

public class PedidoController implements IController {
	
	PedidoDAO dao = new PedidoDAO();

	@Override
	public void salvar(Entidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Entidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Entidade entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entidade> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade getPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizaTabela(JTable table) {
		dao.atualizaTabela(table);
		
	}

}
