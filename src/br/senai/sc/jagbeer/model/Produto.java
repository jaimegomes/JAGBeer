package br.senai.sc.jagbeer.model;

import br.senai.sc.jagbeer.abstracts.Entidade;

/**
 * Classe que representa a entidade Produto do banco de dados
 * 
 * @author Jaime Gomes
 * 
 */
public class Produto extends Entidade {

	private int id;
	private String nome;
	private Double precoCusto;
	private Double precoVenda;
	private String classificacao;

	/**
	 * Construtor padrão
	 */
	public Produto() {
	}

	/**
	 * Construtor que recebe o id como parâmetro
	 * 
	 * @param id
	 */
	public Produto(int id) {

	}

	/**
	 * Contrutor que recebe como parâmetro o nome, precoCusto, precoVenda e
	 * classificação
	 * 
	 * @param nome
	 * @param precoCusto
	 * @param precoVenda
	 * @param classificacao
	 */
	public Produto(String nome, Double precoCusto, Double precoVenda,
			String classificacao) {
		this.nome = nome;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.classificacao = classificacao;
	}

	/**
	 * Contrutor que recebe como parâmetro o id, nome, precoCusto, precoVenda e
	 * classificação
	 * 
	 * @param id
	 * @param nome
	 * @param precoCusto
	 * @param precoVenda
	 * @param classificacao
	 */
	public Produto(int id, String nome, Double precoCusto, Double precoVenda,
			String classificacao) {
		this.id = id;
		this.nome = nome;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.classificacao = classificacao;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	/**
	 * Retorna o nome do produto.
	 * 
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui valor ao produto
	 * 
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the precoCusto
	 */
	public Double getPrecoCusto() {
		return precoCusto;
	}

	/**
	 * @param precoCusto
	 *            the precoCusto to set
	 */
	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	/**
	 * @return the precoVenda
	 */
	public Double getPrecoVenda() {
		return precoVenda;
	}

	/**
	 * @param precoVenda
	 *            the precoVenda to set
	 */
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	/**
	 * @return the classificacao
	 */
	public String getClassificacao() {
		return classificacao;
	}

	/**
	 * @param classificacao
	 *            the classificacao to set
	 */
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

}
