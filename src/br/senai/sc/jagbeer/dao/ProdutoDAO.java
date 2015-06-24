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

			if (produto.getPrecoCusto() == null)
				produto.setPrecoCusto(0.0);

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
		} finally {
			con.close();

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
		} finally {
			con.close();
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
		} finally {
			con.close();

		}

	}

	@Override
	public List<Entidade> listar() throws Exception {

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
		} finally {
			con.close();

		}
		return listaProdutos;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

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
		} finally {
			con.close();

		}

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
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
	public List<Entidade> getPorClassificacao(String classificacao)
			throws Exception {

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
		} finally {
			con.close();

		}

		return listProduto;

	}

	/**
	 * Método responsável por buscar todos os produtos que contenham o nome
	 * passado como parâmetro.
	 * 
	 * @param String
	 *            nome
	 * @return List<Entidade> listProduto
	 * @throws Exception
	 * @throws Exception
	 */
	public List<Entidade> getListNomesProdutos(String nome) throws Exception {

		List<Entidade> listProduto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM produto WHERE UPPER(nomeProduto) LIKE  UPPER('%"
				+ nome + "%')";

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
		} finally {
			con.close();

		}

		return listProduto;

	}

	/**
	 * Mátodo responsável por fazer a busca dos produtos de acordo com o nome e
	 * a classificação passados como parâmetro.
	 * 
	 * @param nome
	 * @param classificacao
	 * @return List<Entidade> listProduto
	 */
	public List<Entidade> buscaPorNomeClassificacao(String nome,
			String classificacao) throws Exception {

		List<Entidade> listProduto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM produto WHERE UPPER(nomeProduto) LIKE UPPER('%"
				+ nome
				+ "%') AND UPPER(classificacao) LIKE UPPER('"
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
					.println("[ProdutoDAO] - Erro ao buscar produto por nome e classificação.\n"
							+ e.getMessage());
		} finally {
			con.close();

		}

		return listProduto;

	}

	/**
	 * Mátodo responsável por fazer a busca dos produtos de acordo com o nome e
	 * a classificação passados como parâmetro.
	 * 
	 * @param nome
	 * @param classificacao
	 * @param precoVenda
	 * @return List<Entidade> listProduto
	 */
	public Entidade buscaCompleta(String nome, Double precoVenda,
			String classificacao) throws Exception {

		Produto produto = null;

		String sql = "SELECT * FROM produto WHERE UPPER(nomeProduto) LIKE UPPER('%"
				+ nome
				+ "%') AND UPPER(classificacao) LIKE UPPER('%"
				+ classificacao + "%') AND precoVenda = ?";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDouble(1, precoVenda);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

			}

			pstm.close();
		} catch (Exception e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por nome e classificação.\n"
							+ e.getMessage());
		} finally {
			con.close();

		}

		return produto;

	}

	/**
	 * Mátodo responsável por buscar o produto com o nome passado como
	 * parâmetro.
	 * 
	 * @return String nome
	 * @throws Exception
	 */
	public Entidade getPorNome(String nome) throws Exception {

		Produto produto = null;
		String sql = "SELECT * FROM produto WHERE UPPER(nomeProduto) LIKE  UPPER('"
				+ nome + "')";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				produto = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

			}

			pstm.close();
		} catch (Exception e) {
			System.out
					.println("[ProdutoDAO] - Erro ao buscar produto por nome.\n"
							+ e.getMessage());
		} finally {
			con.close();

		}

		return produto;

	}

}
