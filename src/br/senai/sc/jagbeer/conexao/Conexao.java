package br.senai.sc.jagbeer.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que faz a conexão com o banco de dados.
 * 
 * @author Jaime Gomes
 * 
 */
public class Conexao {

	static Connection con = null;

	/**
	 * Método que inicia uma conexão com o banco de dados.
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		try {

			// ConexãoMySQLWorkbench
			// Class.forName("com.mysql.jdbc.Driver");
			// String url = "jdbc:mysql://localhost:3306/jagbeer";
			// con = DriverManager.getConnection(url, "root","2905");
			// //alexandre "root","2905"
			// con.setAutoCommit(false);

			// Conexão Postgres
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/jagbeer", "postgres",
					"postgres");
			con.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Driver não encontrado.");
			throw new RuntimeException(e);
		}
		return con;

	}

	/**
	 * Método que encerra a conexão com o banco de dados
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {

		con.close();
		System.out.println("Conexao encerrada.");
	}
}
