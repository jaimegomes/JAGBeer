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
			e.printStackTrace();
			System.out.println("[MesaDAO] - Erro ao salvar mesa.\n"
					+ e.getMessage());
			con.rollback();

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
			se.printStackTrace();
			System.out.println("Erro ao excluir Mesa.\n" + se.getMessage());
			con.rollback();

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
			se.printStackTrace();
			System.out.println("Erro ao alterar Mesa.\n" + se.getMessage());
			con.rollback();

		}

	}

	@Override
	public List<Entidade> listar() throws Exception {

		List<Entidade> listaMesas = new ArrayList<Entidade>();

		String query = "SELECT m.id, m.numeroMesa, m.lugares FROM mesa m ORDER BY m.numeroMesa";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Mesa m = new Mesa(result.getInt("id"),
						result.getInt("numeroMesa"), result.getInt("lugares"));
				listaMesas.add(m);
			}

			result.close();
			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Mesa.\n" + se.getMessage());
			se.printStackTrace();
		}

		return listaMesas;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		String query = "SELECT m.id, m.numeroMesa, m.lugares FROM mesa m WHERE id = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				mesa = new Mesa(result.getInt("id"),
						result.getInt("numeromesa"), result.getInt("lugares"));
			}

			result.close();
			pstmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Erro ao pegar mesa por id." + se.getMessage());

		}

		return mesa;
	}

	/**
	 * Método a entidade mesa de acordo com o número da mesa passado como
	 * parâmetro
	 * 
	 * @param numeroMesa
	 * @return
	 * @throws Exception
	 */
	public Entidade getPorNumeroMesa(int numeroMesa) throws Exception {
		Mesa mesa = null;
		String sql = "SELECT m.id, m.numeroMesa, m.lugares FROM mesa m WHERE numeroMesa = ? ORDER BY numeroMesa";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numeroMesa);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				mesa = new Mesa(result.getInt("id"),
						result.getInt("numeroMesa"), result.getInt("lugares"));
			}

			result.close();
			pstmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Erro ao listar Mesa por Numero "
					+ se.getMessage());
		}

		return mesa;
	}

}
