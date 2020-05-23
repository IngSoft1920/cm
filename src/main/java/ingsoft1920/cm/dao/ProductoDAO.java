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

import ingsoft1920.cm.bean.Producto;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ProductoDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Producto p) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Producto (nombre) VALUES (?);";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.insert(conn, query, handler, p.getNombre());

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	// Not used yet
	public Producto getByID(int productoID) {
		Producto res=null;
		BeanHandler<Producto> handler = new BeanHandler<>(Producto.class);
		String query = "SELECT * FROM Producto WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler,productoID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Producto> productos() {
		List<Producto> res = new ArrayList<>();
		BeanListHandler<Producto> handler = new BeanListHandler<>(Producto.class);
		String query = "SELECT * FROM Producto";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public List<Producto> productosProveedor(int id) {
		List<Producto> res = new ArrayList<>();
		BeanListHandler<Producto> handler = new BeanListHandler<>(Producto.class);
		String query = "SELECT p.* "
					  +"FROM Producto p "
					  +"JOIN Proveedor_Producto pp ON p.id=pp.producto_id "
					  +"JOIN Proveedor pr ON pp.proveedor_id=pr.id "
					  +"WHERE pr.id=? ";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler,id);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public int infoproducto(int producto_id, int proveedor_id) {

        Integer res = -1;
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
		String query = "SELECT precio_venta "
					  +"FROM Proveedor_Producto "
					  +"WHERE producto_id=? AND proveedor_id=? ";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, scalarHandler,producto_id, proveedor_id);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

}