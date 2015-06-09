package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.ClienteDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Cliente;

public class ClienteController implements IController {

	ClienteDAO dao = new ClienteDAO();

	@Override
	public void salvar(Entidade entidade) {

		// Fazer a verificação se foi digitado numeros no nome na classe view
		try {
			Cliente cliente = (Cliente) entidade;

			if (cliente == null)
				throw new Exception("Cliente não pode ser nulo.");

			if (cliente.getNome().trim().equals(""))
				throw new Exception("Nome do Cliente obrigatório");

			dao.salvar(cliente);

		} catch (Exception e) {
			System.out.println("[ClienteController] - Erro ao Salvar Cliente"
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao Salvar Cliente");
		}

	}

	@Override
	public void excluir(Entidade entidade) {

		try {
			Cliente cliente = (Cliente) entidade;

			if (cliente == null)
				throw new Exception("Selecione ao menos um cliente.");
			dao.excluir(cliente);
		} catch (Exception e) {
			System.out.println("[ClienteController] - Erro ao Excluir Cliente"
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao Salvar Cliente");
		}

	}

	@Override
	public void editar(Entidade entidade) {

		try {
			Cliente cliente = (Cliente) entidade;

			if (cliente == null)
				throw new Exception("Cliente não pode ser nulo.");

			if (cliente.getNome().trim().equals(""))
				throw new Exception("Nome do Cliente obrigatório");

			dao.salvar(cliente);

		} catch (Exception e) {
			System.out.println("[ClienteController] - Erro ao Editar Cliente"
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao Editar Cliente");
		}

	}

	@Override
	public List<Entidade> listar() {
		List<Entidade> listEntidade = null;
		try {
			listEntidade = dao.listar();
		} catch (Exception e) {
			System.out
					.println("[ClienteController] - Erro ao listar clientes. "
							+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao listar clientes. ");
		}

		return listEntidade;
	}

	@Override
	public Entidade getPorId(int id) {
		Cliente cliente = null;

		try {
			if (id < 0)
				throw new Exception("Id não pode ser menor que zero");

			cliente = (Cliente) dao.getPorId(id);

		} catch (Exception e) {
			System.out
					.println("[ClienteController] - Erro ao bucar Cliente por ID."
							+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao buscar Cliente por ID.");
		}

		return cliente;
	}

	@Override
	public void atualizaTabela(JTable table) {
		try {
			dao.atualizaTabela(table);
		} catch (Exception e) {
			System.out.println("[ClienteController] - Erro ao Listar Clientes."
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao Listar Clientes.");
		}
	}

}
