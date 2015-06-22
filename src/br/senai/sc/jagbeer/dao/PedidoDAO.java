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
import br.senai.sc.jagbeer.controller.ProdutoController;
import br.senai.sc.jagbeer.controller.ProdutoPedidoController;
import br.senai.sc.jagbeer.model.Cliente;
import br.senai.sc.jagbeer.model.Mesa;
import br.senai.sc.jagbeer.model.Pedido;
import br.senai.sc.jagbeer.model.PedidoAberto;
import br.senai.sc.jagbeer.model.Produto;
import br.senai.sc.jagbeer.model.ProdutoPedido;
import br.senai.sc.jagbeer.model.ProdutoTableModel;

/**
 * Classe DAO, responsï¿½vel pela manipulaï¿½ï¿½o dos dados dos Pedidos no
 * banco.
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
		} finally {
			con.close();
		}

		return pedido;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Entidade getPorIdStatusAberto(int id) throws Exception {

		String sql = "SELECT * FROM pedido WHERE id = ? AND status = 1";
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
							result.getInt("status"));

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar pedido por id com status em aberto.  "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out.println("[PedidoDAO] - Erro ao buscar pedido por id.\n"
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
	 * Método que retorna uma lista da entidade PedidoAberto
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
							result.getInt("status"));

					listPedidosEmAberto.add(pedido);

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar lista de pedidos com status em aberto.  "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[PedidoDAO] - Erro ao buscar lista de pedidos em aberto.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		List<Entidade> listRetorno = getListPedidoAberto(listPedidosEmAberto);

		return listRetorno;
	}

	/**
	 * Método que retorna uma entidade Pedido de acordo com o id do cliente
	 * passado como parâmetro.
	 * 
	 * @param idCliente
	 * @return Entidade pedido
	 * @throws Exception
	 */
	public Entidade getPorCliente(Entidade entidade) throws Exception {

		Cliente cliente = (Cliente) entidade;

		String sql = "SELECT * FROM pedido WHERE idcliente = ? AND dataPedido = ?";
		try {

			Date dataAtual = new Date();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, cliente.getId());
			pstm.setDate(2, new java.sql.Date(dataAtual.getTime()));

			ResultSet result = pstm.executeQuery();

			while (result.next()) {

				try {
					Mesa mesa = null;

					if (result.getInt("idMesa") > 1) {
						mesa = (Mesa) new MesaController().getPorId(result
								.getInt("idMesa"));

					}

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
		} finally {
			con.close();
		}

		return pedido;

	}

	/**
	 * Método responsável por encerrar o pedido que tem o id igual ao passado
	 * como parâmetro
	 * 
	 * @param idPedidoEncerrar
	 * @throws Exception
	 */
	public void encerrarPedido(int idPedidoEncerrar) throws Exception {
		String sql = "UPDATE pedido SET status = 0 WHERE id = ?";

		try {

			PreparedStatement pstm = con.prepareStatement(sql);

			pstm.setInt(1, idPedidoEncerrar);

			pstm.execute();
			con.commit();
			pstm.close();

		} catch (SQLException e) {
			con.rollback();
			System.out.println("[PedidoDAO] - Erro ao Encerrar pedido.\n"
					+ e.getMessage());
		} finally {
			con.close();
		}

	}

	public List<Entidade> getListPedidoAberto(List<Entidade> listPedidosEmAberto)
			throws Exception {

		List<Entidade> listPedidosAbertos = new ArrayList<Entidade>();
		List<Entidade> listProdutoPedido = new ProdutoPedidoController()
				.listar();

		for (Entidade e : listPedidosEmAberto) {

			Pedido pedido = (Pedido) e;

			PedidoAberto pedidoAberto = new PedidoAberto();
			pedidoAberto.setCliente(pedido.getCliente().getNome());
			pedidoAberto.setPedido(pedido.getId());

			listPedidosAbertos.add(pedidoAberto);
		}

		for (Entidade ent : listPedidosAbertos) {

			PedidoAberto pedidoAberto = (PedidoAberto) ent;

			double valorParcial = 0;

			for (Entidade en : listProdutoPedido) {

				ProdutoPedido produtoPedido = (ProdutoPedido) en;
				Produto produto = (Produto) new ProdutoController()
						.getPorId(produtoPedido.getIdProduto());

				if (pedidoAberto.getPedido() == produtoPedido.getIdPedido()) {

					valorParcial += produto.getPrecoVenda()
							* produtoPedido.getQtde();

				}

				pedidoAberto.setValorParcial(valorParcial);
			}
		}

		return listPedidosAbertos;
	}

	public List<Entidade> getPorData(Date dataInicio, Date dataFim)
			throws Exception {

		Pedido pedido = null;
		List<Entidade> listPedidos = new ArrayList<Entidade>();
		String sql = "SELECT * FROM pedido WHERE dataPedido BETWEEN ? AND ?";
		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, new java.sql.Date(dataInicio.getTime()));
			pstm.setDate(2, new java.sql.Date(dataFim.getTime()));
			

			ResultSet result = pstm.executeQuery();
			

			while (result.next()) {
				pedido = new Pedido(result.getInt("id"),
						result.getDate("dataPedido"));

				listPedidos.add(pedido);
			}
			pstm.close();

		} catch (Exception e) {
			System.out
					.println("[PedidoDAO] - Erro ao buscar pedido entre datas selecionada.  "
							+ e.getMessage());

		} finally {
			con.close();
		}

		return listPedidos;

	}

	/**
	 * Retorna uma lista de Pedidos
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Entidade> getAbertosHoje() throws Exception {

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
							result.getInt("status"));

					listPedidosEmAberto.add(pedido);

				} catch (Exception e) {
					System.out
							.println("[PedidoDAO] - Erro ao buscar lista de pedidos com status em aberto.  "
									+ e.getMessage());
				}

			}

			pstm.close();

		} catch (SQLException e) {
			System.out
					.println("[PedidoDAO] - Erro ao buscar lista de pedidos em aberto.\n"
							+ e.getMessage());
		} finally {
			con.close();
		}

		return listPedidosEmAberto;
	}
}