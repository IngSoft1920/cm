package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

	// Cada Properties da la info del proveedor
	// -proveedor_id: int
	public int anadir(Producto p, List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryProd = "INSERT INTO Producto "
						  +"(nombre) "
						  +"VALUES (?);";
		
		String queryProv = "INSERT INTO Proveedor_Producto "
						  +"(producto_id,proveedor_id) "
						  +"VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryProd, handler, p.getNombre());
	
			batch = new ArrayList<>();
			for (Properties prov : info) {
				batch.add(new Object[] { 
										res.intValue(),
										prov.get("proveedor_id") 
									   });
			}
			runner.batch(conn, queryProv, batch.toArray(new Object[info.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	// Not used yet
	public Producto getByID(int productoID) {
		Producto res=null;
		BeanHandler<Producto> handler = new BeanHandler<>(Producto.class);
		String query = "SELECT * FROM Producto WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,productoID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Producto> productos() {
		List<Producto> res = new ArrayList<>();
		BeanListHandler<Producto> handler = new BeanListHandler<>(Producto.class);
		String query = "SELECT * FROM Producto";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public static void main(String[] args) {
		Producto prod = new Producto(-1, "Bananas");
		
		Properties prov1 = new Properties();
		  prov1.put("proveedor_id", 1);
		  
		Properties prov2 = new Properties();
		prov2.put("proveedor_id", 2);
		
		List<Properties> info = List.of(prov1,prov2);
		
		new ProductoDAO().anadir(prod, info);
	}
	

}