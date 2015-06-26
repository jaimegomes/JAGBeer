package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.Cliente;

/**
 * Classe DAO, responsável pela manipulação dos dados dos Pedidos no banco.
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
			System.out.println("[ClienteDAO] - Erro ao salvar Cliente.\n" + se.getMessage());
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
			System.out.println("[ClienteDAO] - Erro ao excluir Cliente.\n" + se.getMessage());
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
			System.out.println("[ClienteDAO] - Erro ao alterar Cliente.\n" + se.getMessage());
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
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Cliente c = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
				listaClientes.add(c);
			}
			pstmt.close();

		} catch (SQLException se) {
			System.out.println("[ClienteDAO] - Erro ao listar Cliente.\n" + se.getMessage());
		} finally {
			con.close();
		}
		return listaClientes;
	}

	/**
	 * Método retorna uma lista de cliente procurados por nome em ordem
	 * alfabetica
	 * 
	 * @param clientePesquisar
	 * @return list clientes
	 * @throws Exception
	 */
	public List<Entidade> getListClientesPorNome(String clientePesquisar)
			throws Exception {
		Cliente cliente = null;
		List<Entidade> listCliente = new ArrayList<Entidade>();
		String sql = "SELECT * FROM cliente WHERE UPPER(nome) LIKE UPPER('%"
				+ clientePesquisar + "%')ORDER BY nome";

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
			System.out.println("[ClienteDAO] - Erro ao listar Cliente por Nome.\n"
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
			System.out.println("[ClienteDAO] - Erro ao listar Cliente por ID.\n"
					+ se.getMessage());
		} finally {
			con.close();
		}

		return cliente;
	}

	/**
	 * Método retorna apenas um cliente de acordo com o nome passado como
	 * parâmetro
	 * 
	 * @param clientePesquisar
	 * @return cliente
	 * @throws Exception
	 */
	public Entidade getNomeSelecionado(String clientePesquisar)
			throws Exception {
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE UPPER(nome) LIKE UPPER('"
				+ clientePesquisar + "')";

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
			System.out.println("[ClienteDAO] - Erro ao buscar cliente por nome selecionado.\n"
					+ se.getMessage());
		} finally {
			con.close();
		}

		return cliente;
	}

	/**
	 * Método retorna um lista de clientes pesquisados por nome ou pedaço do
	 * nome passado como parâmetro
	 * 
	 * @param clientePesquisar
	 * @return list clientes
	 * @throws Exception
	 */
	public List<Entidade> getPorNome(String clientePesquisar) throws Exception {
		List<Entidade> listClientes = new ArrayList<Entidade>();
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE UPPER(nome) LIKE UPPER('%"
				+ clientePesquisar + "%')";

		try {
			Statement pstmt = con.createStatement();

			ResultSet result = pstmt.executeQuery(sql);

			while (result.next()) {
				cliente = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));

				listClientes.add(cliente);
			}

			pstmt.close();

		} catch (SQLException se) {
			System.out.println("[ClienteDAO] - Erro ao buscar cliente por Nome.\n"
					+ se.getMessage());
		} finally {
			con.close();
		}

		return listClientes;
	}

	public Entidade buscaCompleta(String nome, String telefone, String email)
			throws SQLException {

		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE UPPER(nome) LIKE UPPER('"
				+ nome + "') AND telefone LIKE '" + telefone
				+ "' AND email LIKE '" + email + "'";

		try {
			Statement pstmt = con.createStatement();

			ResultSet result = pstmt.executeQuery(sql);

			while (result.next()) {
				cliente = new Cliente(result.getInt("id"),
						result.getString("nome"), result.getString("telefone"),
						result.getString("email"));
			}

			System.out.println(cliente.getId());
			pstmt.close();

		} catch (SQLException se) {
			System.out.println("[ClienteDAO] - Erro ao buscar cliente por nome selecionado./n"
					+ se.getMessage());
		} finally {
			con.close();
		}

		return cliente;
	}

}
