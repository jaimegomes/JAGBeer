package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.Mesa;

public class MesaDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	
	@Override
	public void salvar(Entidade entidade) throws Exception {
		
		String query = "INSERT TO mesa (numero, numeroLugares) VALUES (?, ?)";
		
		try {
			// transforma a entidade passada no parâmetro para o objeto Produto

			Mesa mesa = (Mesa) entidade;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumero());
			pstmt.setInt(2, mesa.getLugares());

			pstmt.execute();
			con.commit();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao salvar Mesa\n" + se.getMessage());
		}

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {
		
		String query = "DELETE FROM mesa WHERE id = ? ";
		
		try {
			Mesa mesa = (Mesa) entidade;
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, mesa.getId());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao excluir Mesa.\n" + se.getMessage());
		}
		

	}

	@Override
	public void editar(Entidade entidade) throws Exception {
		
		String query = "UPDATE mesa SET numero = ?, numeroLugares = ? WHERE id = ? ";
		
		try {
			Mesa mesa = (Mesa) entidade;
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumero());
			pstmt.setInt(2, mesa.getLugares());
			pstmt.setInt(4, mesa.getId());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao alterar Mesa.\n" + se.getMessage());
		}

	}

	@Override
	public List<Entidade> listar() throws Exception {
		return null;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {
		
		Mesa mesa = null;
		
		String query = "SELECT * FROM mesa WHERE id = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				mesa = new Mesa
						(result.getInt("id"), 
						 result.getInt("numero"), 
						 result.getInt("lugares"));
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Mesa" + se.getMessage());
		}

		return mesa;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {

	}

}
