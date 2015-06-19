package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;

public class MesaDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private Mesa mesa = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

		String query = "INSERT INTO mesa (numeroMesa, lugares) VALUES (?,?)";

		try {
			// transforma a entidade passada no parâmetro para o objeto Produto

			mesa = (Mesa) entidade;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumeroMesa());
			pstmt.setInt(2, mesa.getLugares());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao salvar Mesa\n" + se.getMessage());
		}

	}

	@Override
	public void excluir(Entidade entidade) throws Exception {

		String query = "DELETE FROM mesa WHERE id = ? ";

		try {
			mesa = (Mesa) entidade;
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
			mesa = (Mesa) entidade;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumeroMesa());
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
		
		List<Entidade> listaMesas = new ArrayList<Entidade>();
		
		String query = "SELECT * FROM mesa";
		
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			
			while (result.next()) {
				Mesa m = new Mesa(result.getInt("id"),
						result.getInt("numeroMesa"), result.getInt("lugares"));
				listaMesas.add(m);
			}
			pstmt.close();
			
		}catch (SQLException se){
			System.out.println("Erro ao listar Mesa.\n" + se.getMessage());
		}
		
		return listaMesas;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		String query = "SELECT * FROM mesa WHERE id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				mesa = new Mesa(result.getInt("id"), result.getInt("numero"),
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

	
	public boolean verificarNumeroMesa(int numeroMesa) {

		String query = "SELECT * FROM mesa WHERE numero = ?";

		try {

			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, numeroMesa);

			ResultSet result = pstmt.executeQuery();

			if (result.next())
				return true;

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao verificar numero da Mesa. "
					+ se.getMessage());
		}

		return false;
	}
	
	public List<Entidade> getPorNumeroMesa(int numeroMesa) {
		Mesa mesa = null;
		List<Entidade> listMesa = new ArrayList<Entidade>();
		String sql = "SELECT * FROM mesa WHERE numeroMesa = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numeroMesa);
			
			ResultSet result = pstmt.executeQuery(sql);
			

			while (result.next()) {
				mesa = new Mesa(result.getInt("numeroMesa"), result.getInt("lugares"));
				listMesa.add(mesa);
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Mesa por Numero "
					+ se.getMessage());
		}

		return listMesa;
	}

}
