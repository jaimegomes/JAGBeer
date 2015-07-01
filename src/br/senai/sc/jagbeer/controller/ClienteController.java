package br.senai.sc.jagbeer.controller;

import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ClienteDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Cliente;

/**
 * Classe de controle da entidade Cliente.
 * 
 * @author Bazzi
 *
 */
public class ClienteController implements IController {

	ClienteDAO dao = new ClienteDAO();
	Cliente cliente = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		cliente = (Cliente) entidade;

		if (cliente == null)
			throw new Exception("Cliente não pode ser nulo.");

		if (cliente.getNome().trim().equals(""))
			throw new Exception("Nome do Cliente obrigatório.");

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
			throw new Exception("Cliente não pode ser nulo.");

		if (cliente.getNome().trim().equals(""))
			throw new Exception("Nome do Cliente obrigatório.");

		dao.editar(cliente);

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

	public List<Entidade> getListClientesPorNome(String clientePesquisar)
			throws Exception {

		return dao.getListClientesPorNome(clientePesquisar);
	}

	public Entidade getNomeSelecionado(String nome) throws Exception {
		return dao.getNomeSelecionado(nome);
	}

	public List<Entidade> getPorNome(String nome) throws Exception {
		return dao.getPorNome(nome);
	}

	public Entidade buscaCompleta(String nome, String telefone, String email)
			throws Exception {
		return dao.buscaCompleta(nome, telefone, email);
	}

}
