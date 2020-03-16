package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.bean.auxiliares.Pedido_Producto;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class PedidoDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Pedido p, List<Pedido_Producto> productos) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryS = "INSERT INTO Pedido " + "(fecha,hotel_id) " + "VALUES (?,?);";

		String queryPro = "INSERT INTO Pedido_Producto " + "(producto_id,pedido_id,cantidad) " + "VALUES (?,?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryS, handler, p.getFecha(),p.getHotel_id());
	
			batch = new ArrayList<>();
			for (Pedido_Producto pro : productos) {
				batch.add(new Object[] { res.intValue(), pro.getProducto_id() });
			}
			runner.batch(conn, queryPro, batch.toArray(new Object[productos.size()][]));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (res != null ? res.intValue() : -1);
	}
	
	public Pedido pedido(int id) {
		Pedido res=null;
		BeanHandler<Pedido> handler = new BeanHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido WHERE Pedido.id == "+ id;

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Pedido> pedidos() {
		List<Pedido> res = new ArrayList<>();
		BeanListHandler<Pedido> handler = new BeanListHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

}