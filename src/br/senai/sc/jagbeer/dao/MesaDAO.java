package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.Mesa;

public class MesaDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private Mesa mesa = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {
	
		String query = "INSERT INTO mesa (numeromesa, lugares) VALUES (?,?)";
		
		try {

			mesa = (Mesa) entidade;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumeroMesa());
			pstmt.setInt(2, mesa.getLugares());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[MesaDAO] - Erro ao salvar mesa.\n"
					+ e.getMessage());
		} finally {
			con.close();

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
			se.printStackTrace();
			System.out.println("Erro ao excluir Mesa.\n" + se.getMessage());
		} finally {
			con.close();
		}

	}

	@Override
	public void editar(Entidade entidade) throws Exception {

		String query = "UPDATE mesa SET numeromesa = ?, lugares = ? WHERE id = ? ";

		try {
			mesa = (Mesa) entidade;

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, mesa.getNumeroMesa());
			pstmt.setInt(2, mesa.getLugares());
			pstmt.setInt(3, mesa.getId());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao alterar Mesa.\n" + se.getMessage());
		} finally {
			con.close();
		}

	}

	@Override
	public List<Entidade> listar() throws Exception {

		List<Entidade> listaMesas = new ArrayList<Entidade>();

		String query = "SELECT * FROM mesa";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Mesa m = new Mesa(result.getInt("id"),
						result.getInt("numeroMesa"), result.getInt("lugares"));
				listaMesas.add(m);
			}
			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Mesa.\n" + se.getMessage());
		} finally {
			con.close();
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
				mesa = new Mesa(result.getInt("id"),
						result.getInt("numeromesa"), result.getInt("lugares"));
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao pegar mesa por id." + se.getMessage());
		} finally {
			con.close();
		}

		return mesa;
	}

	/**
	 * Método retorna o numero da mesa
	 * @param numeroMesa
	 * @return
	 * @throws Exception
	 */
	public boolean verificarNumeroMesa(int numeroMesa) throws Exception {

		String query = "SELECT * FROM mesa WHERE numeroMesa = ?";

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
		} finally {
			con.close();
		}

		return false;
	}

	/**
	 * Método retorna o numero da mesa
	 * @param numeroMesa
	 * @return
	 * @throws Exception
	 */
	public Entidade getPorNumeroMesa(int numeroMesa) throws Exception {
		Mesa mesa = null;
		String sql = "SELECT * FROM mesa WHERE numeroMesa = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numeroMesa);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				mesa = new Mesa(result.getInt("id"),
						result.getInt("numeroMesa"), result.getInt("lugares"));
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Mesa por Numero "
					+ se.getMessage());
		} finally {
			con.close();
		}

		return mesa;
	}

}
