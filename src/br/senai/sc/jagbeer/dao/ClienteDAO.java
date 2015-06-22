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

/**
 * 
 * @author Bazzi
 *
 */

public class ClienteDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();

	@Override
	public void salvar(Entidade entidade) throws Exception {

		String sql = "INSERT INTO cliente (nome, telefone, email) VALUES (?,?,?)";
		try {

			Cliente cliente = (Cliente) entidade;

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getTelefone());
			pstmt.setString(3, cliente.getEmail());

			pstmt.execute();
			con.commit();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao salvar Cliente\n" + se.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void excluir(Entidade entidade) throws Exception {
		String sql = "DELETE FROM cliente WHERE id=?";

		try {
			Cliente cliente = (Cliente) entidade;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cliente.getId());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao excluir Cliente.\n" + se.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public void editar(Entidade entidade) throws Exception {
		String sql = "UPDATE cliente SET nome = ?, telefone = ?, email = ? WHERE id=?";

		try {
			Cliente cliente = (Cliente) entidade;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getTelefone());
			pstmt.setString(3, cliente.getEmail());
			pstmt.setInt(4, cliente.getId());

			pstmt.execute();
			con.commit();
			pstmt.close();

		} catch (SQLException se) {
			con.rollback();
			System.out.println("Erro ao alterar Cliente.\n" + se.getMessage());
		} finally {
			con.close();
		}
	}

	@Override
	public List<Entidade> listar() throws Exception {
		List<Entidade> listaClientes = new ArrayList<Entidade>();
		String sql = "SELECT * FROM cliente ORDER BY nome";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			// para executar consulta utilizar executeQuery() pois retorna um
			// resultSet
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Cliente c = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
				listaClientes.add(c);
			}
			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Cliente.\n" + se.getMessage());
		} finally {
			con.close();
		}
		return listaClientes;
	}

	public List<Entidade> getListClientesPorNome(String clientePesquisar)
			throws Exception {
		Cliente cliente = null;
		List<Entidade> listCliente = new ArrayList<Entidade>();
		String sql = "SELECT * FROM cliente WHERE nome LIKE '%"
				+ clientePesquisar + "%'ORDER BY nome";

		try {
			Statement pstmt = con.createStatement();

			ResultSet result = pstmt.executeQuery(sql);

			while (result.next()) {
				cliente = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
				listCliente.add(cliente);
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Cliente por Nome "
					+ se.getMessage());
		} finally {
			con.close();
		}

		return listCliente;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				cliente = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Cliente por ID "
					+ se.getMessage());
		} finally {
			con.close();
		}

		return cliente;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
	}

	public Entidade getNomeSelecionado(String clientePesquisar)
			throws Exception {
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE nome LIKE '"
				+ clientePesquisar + "'ORDER BY nome";

		try {
			Statement pstmt = con.createStatement();

			ResultSet result = pstmt.executeQuery(sql);

			while (result.next()) {
				cliente = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("Erro ao listar Cliente por Nome "
					+ se.getMessage());
		} finally {
			con.close();
		}

		return cliente;
	}

}
