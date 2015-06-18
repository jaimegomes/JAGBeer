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
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe DAO, responsável pela manipulação dos dados dos Produtos no banco.
 * 
 * @author Jaime Gomes
 * 
 */
public class ProdutoDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private Produto produto = null;

	@Override
	public void salvar(Entidade entidade) throws SQLException {

		String sql = "INSERT INTO produto (nomeproduto, precocusto, precovenda, classificacao) values(?,?,?,?)";

		try {

			produto = (Produto) entidade;

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, produto.getNome());
			pstmt.setDouble(2, produto.getPrecoCusto());
			pstmt.setDouble(3, produto.getPrecoVenda());
			pstmt.setString(4, produto.getClassificacao());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[ProdutoDAO] - Erro ao salvar produto.\n"
					+ e.getMessage());
		}
	}

	@Override
	public void excluir(Entidade entidade) throws SQLException {

		String sql = "DELETE FROM produto WHERE id=?";
		try {

			produto = (Produto) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, produto.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[ProdutoDAO] - Erro ao excluir produto.\n"
					+ e.getMessage());
		}

	}

	@Override
	public void editar(Entidade entidade) throws SQLException {

		String sql = "UPDATE produto SET nomeproduto = ? , precocusto = ?, precovenda = ?, classificacao = ? WHERE id=?";
		try {
			produto = (Produto) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getPrecoCusto());
			pstm.setDouble(3, produto.getPrecoVenda());
			pstm.setString(4, produto.getClassificacao());
			pstm.setInt(5, produto.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[ProdutoDAO] - Erro ao alterar produto.\n"
					+ e.getMessage());
		}

	}

	@Override
	public List<Entidade> listar() {

		List<Entidade> listaProdutos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM produto";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

				listaProdutos.add(produto);
			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[ProdutoDAO] - Erro ao listar produtos.\n"
					+ e.getMessage());
		}
		return listaProdutos;
	}

	@Override
	public Entidade getPorId(int id) {

		String sql = "SELECT * FROM produto WHERE id = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por id.\n"
							+ e.getMessage());
		}

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) {
		table.setModel(new ProdutoTableModel(listar()));
	}

	/**
	 * Método responsável por buscar todos os produtos de determinada
	 * classificação passada como parâmetro.
	 * 
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 * @throws Exception
	 */
	public List<Entidade> getPorClassificacao(String classificacao) {

		List<Entidade> listProduto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM produto WHERE UPPER(classificacao) LIKE UPPER('"
				+ classificacao + "')";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				Produto produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

				listProduto.add(produto);

			}

			pstm.close();
		} catch (Exception e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por classificação.\n"
							+ e.getMessage());
		}

		return listProduto;

	}

	/**
	 * Método responsável por buscar todos os produtos de determinado nome
	 * passado como parâmetro.
	 * 
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 * @throws Exception
	 */
	public List<Entidade> getPorNome(String nome) {

		List<Entidade> listProduto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM produto WHERE nomeProduto LIKE  '%" + nome
				+ "%'";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				Produto produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

				listProduto.add(produto);

			}

			pstm.close();
		} catch (Exception e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por nome.\n"
							+ e.getMessage());
		}

		return listProduto;

	}

	/**
	 * Método responsável por fazer a busca dos produtos de acordo com o nome e
	 * a classificação passados como parâmetro.
	 * 
	 * @param nome
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 */
	public List<Entidade> buscaCompleta() throws Exception {

		List<Entidade> listProduto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM produto";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				Produto produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

				listProduto.add(produto);

			}

			pstm.close();
		} catch (Exception e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por nome.\n"
							+ e.getMessage());
		}

		return listProduto;

	}

}
