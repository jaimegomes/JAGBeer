package br.senai.sc.jagbeer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;

import br.senai.sc.jagbeer.abstracts.Entidade;
import br.senai.sc.jagbeer.abstracts.GenericDAO;
import br.senai.sc.jagbeer.conexao.Conexao;
import br.senai.sc.jagbeer.controller.ClienteController;
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
	private Pedido pedido = null;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void salvar(Entidade entidade) throws SQLException {

		try {
			Date data = new Date();
			pedido = (Pedido) entidade;

			if (pedido.getMesa() == null) {
				String sql = "INSERT INTO pedido (idCliente, dataPedido, status) values(?,?,?)";

				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setObject(1, pedido.getCliente().getId());
				pstmt.setDate(2, new java.sql.Date(data.getTime()));
				pstmt.setInt(3, pedido.getStatus());

				pstmt.execute();
				con.commit();
				pstmt.close();

			} else {
				String sql = "INSERT INTO pedido (idMesa, idCliente, dataPedido, status) values(?,?,?)";

				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setObject(1, pedido.getMesa().getId());
				pstmt.setObject(2, pedido.getCliente().getId());
				pstmt.setDate(3, new java.sql.Date(data.getTime()));
				pstmt.setInt(4, pedido.getStatus());
				pstmt.execute();

				con.commit();
				pstmt.close();

			}

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

			pedido = (Pedido) entidade;
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
			pedido = (Pedido) entidade;
			Date dataAtual = new Date();

			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, pedido.getMesa().getId());
			pstm.setInt(2, pedido.getCliente().getId());
			pstm.setDate(3, (java.sql.Date) dataAtual);
			pstm.setInt(4, pedido.getStatus());
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

		List<Entidade> listaPedidos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM pedido";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) mesaDAO
							.getPorId(result.getInt("idMesa"));
					Cliente cliente = (Cliente) new ClienteController()
							.getPorId(result.getInt("idCliente"));

					Pedido p = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getInt("estado"));

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

		mesaDAO = new MesaDAO();

		String sql = "SELECT * FROM pedido WHERE id = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) mesaDAO
							.getPorId(result.getInt("idMesa"));

					Cliente cliente = (Cliente) new ClienteController()
							.getPorId(result.getInt("idCliente"));

					pedido = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getInt("status"));

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar pedido por id.  "
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

	public List<Entidade> getPedidosAbertos() {

		Date data = new Date();

		mesaDAO = new MesaDAO();

		List<Entidade> listPedidos = new ArrayList<Entidade>();

		// Pedido aberto estado = 1, pedido fechado estado = 0
		String sql = "SELECT * FROM pedido WHERE dataPedido = ? AND status = 1";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(data.getTime()));

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = null;
					if (result.getInt("idMesa") > 0) {

						mesa = (Mesa) mesaDAO.getPorId(result.getInt("idMesa"));

					}

					Cliente cliente = null;

					if (result.getInt("idCliente") > 0) {

						cliente = (Cliente) new ClienteController()
								.getPorId(result.getInt("idCliente"));

					}

					pedido = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getInt("status"));

					listPedidos.add(pedido);

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar pedido aberto. "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[PedidoDAO] - Erro ao buscar pedido aberto.\n"
					+ e.getMessage());
		}

		return listPedidos;
	}
}
