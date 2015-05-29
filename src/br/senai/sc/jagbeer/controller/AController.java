package br.senai.sc.jagbeer.controller;

import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.model.Entidade;

public abstract class AController {
	
	/**
	 * Método responsável por persistir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void salvar(Entidade entidade);

	/**
	 * Método responsável por excluir a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void excluir(Entidade entidade);

	/**
	 * Método responsável por editar a entidade no banco
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void editar(Entidade entidade);

	/**
	 * Método que retorna uma lista de entidade
	 * 
	 * @param Entidade
	 *            entidade
	 * 
	 * @return List<Entidade>
	 */
	public abstract List<Entidade> listar();

	/**
	 * Método que retorna a entidade referente ao id passado como parâmetro
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract Entidade getPorId(int id);

	/**
	 * Método responsável por atualizar a tabela passada como parâmetro.
	 * 
	 * @param Entidade
	 *            entidade
	 */
	public abstract void atualizaTabela(JTable table);


}
