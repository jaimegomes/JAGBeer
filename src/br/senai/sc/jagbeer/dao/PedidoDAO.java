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
 * Classe DAO, responsável pela manipulação dos dados dos Pedidos no banco.
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
			double valor = 0;

			String sql = "INSERT INTO pedido (idMesa, idCliente, dataPedido, status, valor) values(?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			if (pedido.getMesa() != null) {
				pstmt.setInt(1, pedido.getMesa().getId());

			} else {
				pstmt.setNull(1, java.sql.Types.NULL);
			}

			if (pedido.getCliente() != null) {
				pstmt.setInt(2, pedido.getCliente().getId());

			} else {
				pstmt.setNull(2, java.sql.Types.NULL);
			}
			pstmt.setDate(3, new java.sql.Date(data.getTime()));
			pstmt.setInt(4, pedido.getStatus());

			if (pedido.getValor() == null) {
				valor = 0;

			}

			pstmt.setDouble(5, valor);

			pstmt.execute();

			con.commit();
			pstmt.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao salvar pedido.\n"
					+ e.getMessage());
		} finally {
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
		} finally {
			con.close();
		}

	}

	@Override
	public void editar(Entidade entidade) throws SQLException {

		String sql = "UPDATE pedido SET idMesa = ?, idCliente = ?, dataPedido = ?, status = ?, valor = ?  WHERE id = ?";
		try {
			pedido = (Pedido) entidade;
			Date dataAtual = new Date();

			PreparedStatement pstm = con.prepareStatement(sql);

			if (pedido.getMesa() != null) {
				pstm.setInt(1, pedido.getMesa().getId());

			} else {
				pstm.setNull(1, java.sql.Types.NULL);
			}

			if (pedido.getCliente() != null) {
				pstm.setInt(2, pedido.getCliente().getId());

			} else {
				pstm.setNull(2, java.sql.Types.NULL);
			}

			pstm.setDate(3, new java.sql.Date(dataAtual.getTime()));
			pstm.setInt(4, pedido.getStatus());
			pstm.setDouble(5, pedido.getValor());
			pstm.setInt(6, pedido.getId());

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao alterar pedido.\n"
					+ e.getMessage());
		} finally {
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
							result.getInt("status"), result.getDouble("valor"));

					listaPedidos.add(p);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao listar pedidos.\n"
					+ e.getMessage());
		} finally {
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
							result.getInt("status"), result.getDouble("valor"));

				} catch (Exception e) {

				}

			}

			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao buscar pedido por id.  "
					+ e.getMessage());
		} finally {
			con.close();
		}

		return pedido;
	}

	@Override
	public void atualizaTabela(JTable table) throws Exception {
		table.setModel(new ProdutoTableModel(listar()));
	}

	/**
	 * Método que retorna uma lista de pedidos com status = 1
	 * 
	 * @return List<Entidade> listPedidosAberto
	 * @throws Exception
	 */
	public List<Entidade> getListPedidosEmAberto() throws Exception {

		List<Entidade> listPedidosEmAberto = new ArrayList<Entidade>();

		String sql = "SELECT * FROM pedido WHERE status = 1 AND dataPedido = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(new Date().getTime()));

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
							result.getInt("status"), result.getDouble("valor"));

					listPedidosEmAberto.add(pedido);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out
					.println("[PedidoDAO] - Erro ao buscar lista de pedidos em aberto.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return listPedidosEmAberto;
	}

	public List<Entidade> getPorData(Date dataInicio, Date dataFim)
			throws Exception {

		List<Entidade> listPedidos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM pedido WHERE dataPedido BETWEEN ? AND ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(dataInicio.getTime()));
			pstm.setDate(2, new java.sql.Date(dataFim.getTime()));

			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				Pedido pedido = new Pedido(result.getInt("id"),
						result.getDate("dataPedido"), result.getDouble("valor"));

				listPedidos.add(pedido);

			}

			pstm.close();

		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();

		} finally {
			con.close();
		}

		return listPedidos;

	}

	public Entidade getPedidoAbertoPorIdCliente(int id) throws Exception {

		Pedido pedido = null;
		String sql = "SELECT * FROM pedido WHERE status = 1 AND idCliente = ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);

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
							result.getInt("status"), result.getDouble("valor"));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		} finally {
			con.close();
		}

		return pedido;
	}

}