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
		}

		return produtoPedido;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {

	}

	public Entidade getPorIdPedido(int idPedido) {

		String sql = "SELECT * FROM produtopedido WHERE idpedido = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idPedido);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produtoPedido = new ProdutoPedido(result.getInt("id"),
						result.getInt("idproduto"), result.getInt("idpedido"),
						result.getInt("quantidade"));

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoPedidoDAO] - Erro ao buscar produto do pedido por id do pedido.\n"
							+ e.getMessage());
		}

		return produtoPedido;
	}

}
