package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ClienteDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Cliente;

public class ClienteController implements IController {

	ClienteDAO dao = new ClienteDAO();
	Cliente cliente = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		cliente = (Cliente) entidade;

		if (cliente == null)
			throw new Exception("Cliente nao pode ser nulo.");

		if (cliente.getNome().trim().equals(""))
			throw new Exception("Nome do Cliente obrigatorio");

		dao.salvar(cliente);

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		cliente = (Cliente) entidade;

		if (cliente == null)
			throw new Exception("Selecione ao menos um cliente.");

		dao.excluir(cliente);

	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		cliente = (Cliente) entidade;

		if (cliente == null)
			throw new Exception("Cliente nao pode ser nulo.");

		if (cliente.getNome().trim().equals(""))
			throw new Exception("Nome do Cliente obrigatorio");

		dao.salvar(cliente);

	}

	@Override
	public List<Entidade> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		if (id <= 0)
			throw new Exception("Id deve ser maior que zero.");

		return (Cliente) dao.getPorId(id);
	}

	public List<Entidade> getPorNome(String clientePesquisar) throws Exception {

		return dao.getPorNome(clientePesquisar);
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
		dao.atualizaTabela(table);
	}

}
