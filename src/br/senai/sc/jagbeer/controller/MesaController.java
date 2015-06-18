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
	public void salvar(Entidade entidade) throws Exception{
			
			Mesa mesa = (Mesa) entidade;			
			
			if(mesa == null){
				throw new Exception("Mesa nao pode ser nula.");
			}
			
			if(mesa.getNumeroMesa() < 0 & mesa.getNumeroMesa() == 0){
				throw new Exception("Numero da mesa nao pode ser menor ou igual a zero.");
			}	
			
//			if(dao.verificarNumeroMesa(mesa.getNumeroMesa()) ){
//				throw new Exception("N�mero da mesa j� cadastrado.");
//			}
//			
			dao.salvar(mesa);				
			
	}

	@Override
	public void excluir(Entidade entidade) throws Exception{

		Mesa mesa = (Mesa) entidade;

			if (mesa == null){
				throw new Exception("Selecione uma mesa.");
			}

			dao.excluir(mesa);
			
	}

	@Override
	public void editar(Entidade entidade) throws Exception{
			
			Mesa mesa = (Mesa) entidade;

			if (mesa == null)
				throw new Exception("Mesa n�o pode ser nula.");

			if (mesa.getNumeroMesa() < 0 & mesa.getNumeroMesa() == 0)
				throw new Exception("Numero da mesa nao pode ser menor ou igual a zero");
			
			if(dao.verificarNumeroMesa(mesa.getNumeroMesa()) ){
				throw new Exception("Numero da mesa ja cadastrado.");
			}

			dao.salvar(mesa);

	}

	@Override
	public List<Entidade> listar() throws Exception{
		return dao.listar();
	}

	@Override
	public Entidade getPorId(int id) throws Exception{
		return dao.getPorId(id);
	}

	public List<Entidade> getPorNumeroMesa(String numeroMesa) throws Exception {

		return dao.getPorNumeroMesa(numeroMesa);
	}
	
	@Override
	public void atualizaTabela(JTable table) throws Exception{
		dao.atualizaTabela(table);
	}

}
