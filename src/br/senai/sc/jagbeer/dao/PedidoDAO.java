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
import br.senai.sc.jagbeer.controller.MesaController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe DAO, respons�vel pela manipula��o dos dados dos Pedidos no banco.
 * 
 * @author Jaime Gomes
 * 
 */
public class PedidoDAO extends GenericDAO {

	private Connection con = Conexao.getConnection();
	private Pedido pedido = null;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void salvar(Entidade entidade) throws SQLException {

		try {
			Date data = new Date();
			pedido = (Pedido) entidade;

			if (pedido.getMesa() == null) {
				String sql = "INSERT INTO pedido (idCliente, dataPedido, status, idMesa) values(?,?,?,?)";

				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setObject(1, pedido.getCliente().getId());
				pstmt.setDate(2, new java.sql.Date(data.getTime()));
				pstmt.setInt(3, pedido.getStatus());
				pstmt.setNull(4, java.sql.Types.NULL);

				pstmt.execute();
				con.commit();
				pstmt.close();

			} else {
				
				String sql = "INSERT INTO pedido (idMesa, idCliente, dataPedido, status) values(?,?,?,?)";

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
		}finally {
			con.close();
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
		}finally {
			con.close();
		}

	}

	@Override
	public void editar(Entidade entidade) throws SQLException {

		String sql = "UPDATE pedido SET idMesa = ?, idCliente = ?, dataPedido = ?, status = ?  WHERE id= ?";
		try {
			pedido = (Pedido) entidade;
			Date dataAtual = new Date();

			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, pedido.getMesa().getId());
			pstm.setInt(2, pedido.getCliente().getId());
			pstm.setDate(3, new java.sql.Date(dataAtual.getTime()));
			pstm.setInt(4, pedido.getStatus());
			pstm.setInt(5, pedido.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao alterar pedido.\n"
					+ e.getMessage());
		}finally {
			con.close();
		}

	}

	@Override
	public List<Entidade> listar() throws Exception {

		List<Entidade> listaPedidos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM pedido";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) new MesaController().getPorId(result
							.getInt("idMesa"));
					Cliente cliente = (Cliente) new ClienteController()
							.getPorId(result.getInt("idCliente"));

					Pedido p = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getInt("status"));

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
		}finally {
			con.close();
		}
		return listaPedidos;
	}

	@Override
	public Entidade getPorId(int id) throws Exception {

		String sql = "SELECT * FROM pedido WHERE id = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) new MesaController().getPorId(result
							.getInt("idMesa"));

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
		}finally {
			con.close();
		}

		return pedido;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
		table.setModel(new ProdutoTableModel(listar()));
	}

	public List<Entidade> getPedidosAbertos() throws Exception{

		Date data = new Date();

		List<Entidade> listPedidos = new ArrayList<Entidade>();

		// Pedido aberto status = 1, pedido fechado status = 0
		String sql = "SELECT * FROM pedido WHERE dataPedido = ? AND status = 1";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(data.getTime()));

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = null;
					if (result.getInt("idMesa") > 0) {

						mesa = (Mesa) new MesaController().getPorId(result
								.getInt("idMesa"));

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
				}finally {
					con.close();
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[PedidoDAO] - Erro ao buscar pedido aberto.\n"
					+ e.getMessage());
		}

		return listPedidos;
	}

	public Entidade getPorIdCliente(int idCliente) throws Exception {

		String sql = "SELECT * FROM pedido WHERE idcliente = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, idCliente);

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = (Mesa) new MesaController().getPorId(result
							.getInt("idMesa"));

					Cliente cliente = (Cliente) new ClienteController()
							.getPorId(result.getInt("idCliente"));

					pedido = new Pedido(result.getInt("id"), mesa, cliente,
							result.getDate("dataPedido"),
							result.getInt("status"));

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar pedido por id do cliente.  "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[PedidoDAO] - Erro ao buscar pedido por id do cliente.\n"
							+ e.getMessage());
		}finally {
			con.close();
		}
		

		return pedido;

	}
}
