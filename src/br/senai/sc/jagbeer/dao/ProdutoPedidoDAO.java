package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.ProdutoPedido;

public class ProdutoPedidoDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private ProdutoPedido produtoPedido = null;

	@Override
	public void salvar(Entidade entidade) throws Exception {

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

	@Override
	public void atualizaTabela(JTable table) throws Exception {

	}

	/**
	 * Método que retorna uma lista de ProdutoPedido de acordo com o id do
	 * pedido passado como parâmetro.
	 * 
	 * @param idPedido
	 * @return List<Entidade> listProdutosPedido
	 * @throws Exception
	 */
	public List<Entidade> getPorIdPedido(int idPedido) throws Exception {

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

	public Entidade getPorNomeQtdeIdPedido(int idProduto, int qtde, int idPedido)
			throws Exception {

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
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por nome, quantidade e número do pedido.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return produtoPedido;
	}

}
