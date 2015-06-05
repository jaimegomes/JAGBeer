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

	@Override
	public void salvar(Entidade entidade) throws SQLException {

		String sql = "INSERT INTO produto (nomeproduto, precocusto, precovenda, classificacao) values(?,?,?,?)";

		try {

			// transforma a entidade passada no parâmetro para o objeto Produto
			Produto produto = (Produto) entidade;

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setDouble(2, produto.getPrecoCusto());
			pstm.setDouble(3, produto.getPrecoVenda());
			pstm.setString(4, produto.getClassificacao());

			pstm.execute();
			con.commit();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[ProdutoDAO] - Erro ao salvar produto.\n" + e.getMessage());
		}
	}

	@Override
	public void excluir(Entidade entidade) throws SQLException {

		String sql = "DELETE FROM produto WHERE id=?";
		try {

			Produto produto = (Produto) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, produto.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[ProdutoDAO] - Erro ao excluir produto.\n" + e.getMessage());
		}

	}

	@Override
	public void editar(Entidade entidade) throws SQLException {

		String sql = "UPDATE produto SET nomeproduto = ? , precocusto = ?, precovenda = ?, classificacao = ? WHERE id=?";
		try {
			Produto produto = (Produto) entidade;
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
			System.out.println("[ProdutoDAO] - Erro ao alterar produto.\n" + e.getMessage());
		}

	}

	@Override
	public List<Entidade> listar() {

		List<Entidade> listaProdutos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM produto";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			// para executar consulta utilizar executeQuery() pois retorna um
			// resultSet
			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				Produto p = new Produto(result.getInt("id"),
						result.getString("nomeProduto"),
						result.getDouble("precoCusto"),
						result.getDouble("precoVenda"),
						result.getString("classificacao"));

				listaProdutos.add(p);
			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[ProdutoDAO] - Erro ao listar produtos.\n" + e.getMessage());
		}
		return listaProdutos;
	}

	@Override
	public Entidade getPorId(int id) {

		Produto produto = null;

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
			System.out.println("[ProdutoDAO] - Erro ao buscar produto por id.\n"
					+ e.getMessage());
		}

		return produto;
	}

	@Override
	public void atualizaTabela(JTable table) {
		table.setModel(new ProdutoTableModel(listar()));
	}

}
