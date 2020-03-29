package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class PedidoDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	// Cada Properties representa un producto en el pedido:
	// -producto_id: int
	// -cantidad: int
	public int anadir(Pedido p,List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryPedido = "INSERT INTO Pedido "
							+"(fecha,hotel_id) "
							+"VALUES (?,?);";
		
		String queryProductos = "INSERT INTO Pedido_Producto "
							   +"(pedido_id,producto_id,cantidad) "
							   +"VALUES (?,?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryPedido, handler, p.getFecha(),p.getHotel_id());
	
			batch = new ArrayList<>();
			for (Properties prod : info) {
				batch.add(new Object[] { res.intValue(),
										 prod.get("producto_id"),
										 prod.get("cantidad")
									   });
			}
			runner.batch(conn, queryProductos, batch.toArray(new Object[info.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	public Pedido getByID(int pedidoID) {
		Pedido res=null;
		BeanHandler<Pedido> handler = new BeanHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,pedidoID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Pedido> pedidos() {
		List<Pedido> res = new ArrayList<>();
		BeanListHandler<Pedido> handler = new BeanListHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public static void main(String[] args) {
		Pedido p = new Pedido(-1, Date.valueOf("2020-02-02"), 1);
		
		Properties prod1 = new Properties();
		  prod1.put("producto_id",1);
		  prod1.put("cantidad",100);
		  
		Properties prod2 = new Properties();
		  prod2.put("producto_id",2);
		  prod2.put("cantidad",50);
		
		List<Properties> info = List.of(prod1,prod2);
		
		new PedidoDAO().anadir(p, info);
	}

}