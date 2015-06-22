package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.MesaDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Mesa;

public class MesaController implements IController {

	MesaDAO dao = new MesaDAO();

	@Override
	public void salvar(Entidade entidade) throws Exception {

		Mesa mesa = (Mesa) entidade;

		if (mesa.getNumeroMesa() == null) {
			throw new Exception("Número da mesa obrigatório.");
		}

		if (mesa.getNumeroMesa() < 1) {
			throw new Exception(
					"Número da mesa não pode ser menor ou igual a zero.");
		}

		dao.salvar(mesa);
	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		Mesa mesa = (Mesa) entidade;

		if (mesa == null) {
			throw new Exception("Selecione uma mesa.");
		}

		dao.excluir(mesa);
	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		Mesa mesa = (Mesa) entidade;

		if (mesa == null) {
			throw new Exception("Mesa não pode ser nula.");
		}

		if (mesa.getNumeroMesa() < 0 || mesa.getNumeroMesa() == 0) {
			throw new Exception(
					"Número da mesa não pode ser menor ou igual a zero");
		}

		dao.editar(mesa);
	}

	@Override
	public List<Entidade> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception {
		return dao.getPorId(id);
	}

	public Entidade getPorNumeroMesa(int numeroMesa) throws Exception {
		return dao.getPorNumeroMesa(numeroMesa);
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
		dao.atualizaTabela(table);
	}

}
