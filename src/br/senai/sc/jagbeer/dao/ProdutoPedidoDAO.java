package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.ProdutoPedido;

public class ProdutoPedidoDAO extends GenericDAO {

	private Connection con = null;
	private ProdutoPedido produtoPedido = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {
		con = Conexao.getConnection();
		String sql = "INSERT INTO produtopedido (idproduto, idpedido, quantidade) values(?,?,?)";

		try {

			produtoPedido = (ProdutoPedido) entidade;

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, produtoPedido.getIdProduto());
			pstmt.setInt(2, produtoPedido.getIdPedido());
			pstmt.setInt(3, produtoPedido.getQtde());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException e) {
			con.rollback();
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao salvar produto do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void excluir(Entidade entidade) throws Exception {
		con = Conexao.getConnection();
		String sql = "DELETE FROM produtopedido WHERE id=?";
		try {

			produtoPedido = (ProdutoPedido) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, produtoPedido.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao excluir produto do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void editar(Entidade entidade) throws Exception {
		con = Conexao.getConnection();
		String sql = "UPDATE produtopedido SET idproduto = ? , idpedido = ?, quantidade = ? WHERE id=?";
		try {
			produtoPedido = (ProdutoPedido) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, produtoPedido.getIdProduto());
			pstm.setInt(2, produtoPedido.getIdProduto());
			pstm.setInt(3, produtoPedido.getQtde());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao alterar produto do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

	}

	@Override
	public List<Entidade> listar() throws Exception {
		con = Conexao.getConnection();
		List<Entidade> listaProdutosPedido = new ArrayList<Entidade>();
		String sql = "SELECT * FROM produtopedido";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

				listaProdutosPedido.add(produtoPedido);
			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao listar produtos do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return listaProdutosPedido;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {
		con = Conexao.getConnection();
		String sql = "SELECT * FROM produtopedido WHERE id = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por id.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return produtoPedido;
	}

	/**
	 * M�todo que retorna uma lista de ProdutoPedido de acordo com o id do
	 * pedido passado como par�metro.
	 * 
	 * @param idPedido
	 * @return List<Entidade> listProdutosPedido
	 * @throws Exception
	 */
	public List<Entidade> getPorIdPedido(int idPedido) throws Exception {
		con = Conexao.getConnection();
		List<Entidade> listProdutosPedido = new ArrayList<Entidade>();
		String sql = "SELECT * FROM produtopedido WHERE idpedido = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idPedido);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

				listProdutosPedido.add(produtoPedido);

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por id do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return listProdutosPedido;
	}

	/**
	 * M�todo que retorna uma lista de ProdutoPedido de acordo com o id do
	 * produto passado como par�metro.
	 * 
	 * @param idPedido
	 * @return List<Entidade> listProdutosPedido
	 * @throws Exception
	 */
	public List<Entidade> getPorIdProduto(int idProduto) throws Exception {
		con = Conexao.getConnection();
		List<Entidade> listProdutosPedido = new ArrayList<Entidade>();
		String sql = "SELECT * FROM produtopedido WHERE idProduto = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idProduto);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

				listProdutosPedido.add(produtoPedido);

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por id do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return listProdutosPedido;
	}

	/**
	 * Retorna um produtopedido referente ao id, qtde e idPedido passados como
	 * par�metro
	 * 
	 * @param idProduto
	 * @param qtde
	 * @param idPedido
	 * @return produto
	 * @throws Exception
	 */
	public Entidade buscaCompleta(int idProduto, int qtde, int idPedido)
			throws Exception {
		con = Conexao.getConnection();
		String sql = "SELECT * FROM produtopedido WHERE idProduto = ? AND quantidade = ? AND idPedido = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idProduto);
			pstm.setInt(2, qtde);
			pstm.setInt(3, idPedido);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por nome, quantidade e n�mero do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return produtoPedido;
	}

	/**
	 * M�todo que seleciona todos os produtos pedidos em um determinado per�odo.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return listProdMaisVend
	 * @throws Exception
	 */
	public List<Integer> getIdProdutosPedidoPorPeriodo(Date dataInicio,
			Date dataFim) throws Exception {

		con = Conexao.getConnection();

		List<Integer> listProdutosPedidos = new ArrayList<Integer>();

		String sql = "SELECT DISTINCT pp.idProduto FROM produtopedido pp "
				+ "JOIN pedido ped ON pp.idPedido = ped.id JOIN produto prod ON pp.idProduto = prod.id "
				+ "WHERE ped.dataPedido BETWEEN ? AND ? ";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(dataInicio.getTime()));
			pstm.setDate(2, new java.sql.Date(dataFim.getTime()));

			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				listProdutosPedidos.add(result.getInt("idProduto"));

			}

			pstm.close();

		} catch (Exception se) {
			System.out
					.println("[PodutoPedidoDAO] - Erro ao buscar produto mais vendido.\n"
							+ se.getMessage());
			se.printStackTrace();
		} finally {
			con.close();
		}

		return listProdutosPedidos;
	}

	/**
	 * M�todo que retorna a soma do total de produtos pedidos de acordo com o id
	 * do produto passado como par�metro.
	 * 
	 * @param idProduto
	 * @return qtde total de produtos
	 * @throws Exception
	 */
	public int getQtdeProduto(int idProduto) throws Exception {

		int qtde = 0;

		con = Conexao.getConnection();

		String sql = "SELECT SUM(pp.quantidade) AS quantidade FROM produtopedido pp WHERE pp.idProduto = ? ORDER BY quantidade";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idProduto);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				qtde = result.getInt("quantidade");
			}

			pstm.close();

		} catch (Exception se) {
			System.out
					.println("[PodutoPedidoDAO] - Erro ao buscar quantidade do produto.\n"
							+ se.getMessage());
			se.printStackTrace();
		} finally {
			con.close();
		}

		return qtde;
	}

}
