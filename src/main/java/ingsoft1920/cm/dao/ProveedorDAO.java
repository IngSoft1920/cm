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

import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ProveedorDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	
	// Cada Properties da la info de cada producto
	// -producto_id: int
	public int anadir(Proveedor p, List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryProv = "INSERT INTO Proveedor "
						  +"(empresa, CIF) "
						  +"VALUES (?,?);";
		
		String queryPro = "INSERT INTO Proveedor_Producto "
						 +"(proveedor_id,producto_id) "
						 +"VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryProv, handler, p.getEmpresa(),p.getCIF());
	
			batch = new ArrayList<>();
			for (Properties prod : info) {
				batch.add(new Object[] { res.intValue(),
										 prod.get("producto_id")
									   });
			}
			runner.batch(conn, queryPro, batch.toArray(new Object[info.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	// Not used yet
	public Proveedor getByID(int proveedorID) {
		Proveedor res=null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE Proveedor.id=?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,proveedorID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	// Lista de proveedores
	public List<Proveedor> proveedores() {

		List<Proveedor> res = new ArrayList<>();

		BeanListHandler<Proveedor> handler = new BeanListHandler<>(Proveedor.class);

		String query = "SELECT * FROM Proveedor";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;

	}

	// Eliminar empleado
	public void eliminar(int proveedorID) {

		String eliminarProveedor = "DELETE FROM Proveedor WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			runner.update(conn, eliminarProveedor, proveedorID);
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	
//	public static void main(String[] args) {
//		Proveedor prov = new Proveedor(-1, "Almacenes Juan", "123456");
//		
//		Properties prod1 = new Properties();
//		  prod1.put("producto_id",1);
//		  
//		Properties prod2 = new Properties();
//		  prod2.put("producto_id",2);
//		  
//		List<Properties> info = List.of(prod1,prod2);
//		
//		new ProveedorDAO().anadir(prov, info);
//	}
	

	
	

}