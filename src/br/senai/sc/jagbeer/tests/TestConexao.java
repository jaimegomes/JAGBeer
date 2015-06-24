package br.senai.sc.jagbeer.tests;

import br.senai.sc.jagbeer.conexao.Conexao;

/**
 * Classe para testar a conex� com o banco de dados, caso de erro verifique a
 * classe conex�o se o driver escolhido � o correto e tamb�m em Build Path e
 * verifique se o arquivo .jar do banco de dados escolhido est� relacionado.
 * 
 * @author Jaime Gomes
 * 
 */
public class TestConexao {

	public static void main(String[] args) {

		Conexao.getConnection();
	}

}
