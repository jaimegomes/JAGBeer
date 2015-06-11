package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.dao.MesaDAO;
import br.senai.sc.jagbeer.interfaces.IController;
import br.senai.sc.jagbeer.model.Mesa;

public class MesaController implements IController {
	
	MesaDAO dao = new MesaDAO();

	@Override
	public void salvar(Entidade entidade) {
		try {
			
			Mesa mesa = (Mesa) entidade;			
			
			if(mesa == null){
				throw new Exception("Mesa não pode ser nula.");
			}
			
			if(mesa.getNumeroMesa() < 0 & mesa.getNumeroMesa() == 0){
				throw new Exception("Número da mesa não pode ser menor ou igual a zero.");
			}	
			
//			if(dao.verificarNumeroMesa(mesa.getNumeroMesa()) ){
//				throw new Exception("Número da mesa já cadastrado.");
//			}
//			
			dao.salvar(mesa);				
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"[MesaController] - Erro ao Salvar Mesa");
		}		
	}

	@Override
	public void excluir(Entidade entidade) {
		try {
			Mesa mesa = (Mesa) entidade;

			if (mesa == null){
				throw new Exception("Selecione uma mesa.");
			}

			dao.excluir(mesa);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "[MesaController] - Erro ao Excluir Mesa");
		}
	}

	@Override
	public void editar(Entidade entidade) {
		try {
			Mesa mesa = (Mesa) entidade;

			if (mesa == null)
				throw new Exception("Mesa não pode ser nula.");

			if (mesa.getNumeroMesa() < 0 & mesa.getNumeroMesa() == 0)
				throw new Exception("Número da mesa não pode ser menor ou igual a zero");
			
			if(dao.verificarNumeroMesa(mesa.getNumeroMesa()) ){
				throw new Exception("Número da mesa já cadastrado.");
			}

			dao.salvar(mesa);

		} catch (Exception e) {
			System.out.println("[ClienteController] - Erro ao Editar Cliente"
					+ e.getMessage());
			JOptionPane.showMessageDialog(null,
					"[ClienteController] - Erro ao Editar Cliente");
		}
	}

	@Override
	public List<Entidade> listar() {
		return null;
	}

	@Override
	public Entidade getPorId(int id) {
		return null;
	}

	@Override
	public void atualizaTabela(JTable table) {
		
	}

}
