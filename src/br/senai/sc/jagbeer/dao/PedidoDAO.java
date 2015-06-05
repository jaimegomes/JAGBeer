package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe DAO, responsável pela manipulação dos dados dos Pedidos no banco.
 * 
 * @author Jaime Gomes
 * 
 */
public class PedidoDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private MesaDAO mesaDAO = null;
	private ClienteDAO clienteDAO = null;

	@Override
	public void salvar(Entidade entidade) throws SQLException {

		String sql = "INSERT INTO pedido (idMesa, idCliente, dataPedido, estado) values(?,?,?,?)";

		try {

			// transforma a entidade passada no parâmetro para o objeto Produto
			Pedido pedido = (Pedido) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, pedido.getMesa().getId());
			pstm.setInt(2, pedido.getCliente().getId());
			pstm.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			pstm.setBoolean(4, pedido.isStatus());

			pstm.execute();
			con.commit();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao salvar pedido.\n"
					+ e.getMessage());
		}
	}

	@Override
	public void excluir(Entidade entidade) throws SQLException {

		String sql = "DELETE FROM pedido WHERE id=?";
		try {

			Pedido pedido = (Pedido) entidade;
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, pedido.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao excluir pedido.\n"
					+ e.getMessage());
		}

	}

	@Override
	public void editar(Entidade entidade) throws SQLException {

		String sql = "UPDATE pedido SET idMesa = ?, idCliente = ?, dataPedido = ?, estado = ?  WHERE id= ?";
		try {
			Pedido pedido = (Pedido) entidade;
			Date dataAtual = new Date();

			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, pedido.getMesa().getId());
			pstm.setInt(2, pedido.getCliente().getId());
			pstm.setDate(3, (java.sql.Date) dataAtual);
			pstm.setBoolean(4, pedido.isStatus());
			pstm.setInt(5, pedido.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao alterar pedido.\n"
					+ e.getMessage());
		}

	}

	@Override
	public List<Entidade> listar() {

		mesaDAO = new MesaDAO();
		clienteDAO = new ClienteDAO();

		List<Entidade> listaPedidos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM pedidos";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			// para executar consulta utilizar executeQuery() pois retorna um
			// resultSet
			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) mesaDAO
							.getPorId(result.getInt("idMesa"));
					Cliente cliente = (Cliente) clienteDAO.getPorId(result
							.getInt("idCliente"));

					Pedido p = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getBoolean("estado"));

					listaPedidos.add(p);

				} catch (Exception e) {
					System.out.println("[PedidoDAO] - Erro ao listar pedidos. "
							+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[PedidoDAO] - Erro ao listar pedidos.\n"
					+ e.getMessage());
		}
		return listaPedidos;
	}

	@Override
	public Entidade getPorId(int id) {

		Pedido pedido = null;

		String sql = "SELECT * FROM pedido WHERE id = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) mesaDAO
							.getPorId(result.getInt("idMesa"));
					Cliente cliente = (Cliente) clienteDAO.getPorId(result
							.getInt("idCliente"));

					pedido = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getBoolean("estado"));

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar pedido por id. "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[PedidoDAO] - Erro ao buscar pedido por id.\n"
					+ e.getMessage());
		}

		return pedido;
	}

	@Override
	public void atualizaTabela(JTable table) {
		table.setModel(new ProdutoTableModel(listar()));
	}

}
