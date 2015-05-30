package br.senai.sc.jagbeer.tests;

import br.senai.sc.jagbeer.conexao.Conexao;

/**
 * Classe para testar a conexão com o banco de dados, caso de erro verifique a
 * classe conexão se o driver escolhido é o correto e também em Build Path e
 * verifique se o arquivo .jar do banco de dados escolhido está relacionado.
 * 
 * @author Administrador
 * 
 */
public class TestConexao {

	public static void main(String[] args) {

		Conexao.getConnection();
	}

}
