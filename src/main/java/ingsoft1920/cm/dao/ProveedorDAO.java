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

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.bean.auxiliares.Proveedor_Producto;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ProveedorDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Proveedor p, List<Proveedor_Producto> productos) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryP = "INSERT INTO Proveedor " + "(empresa, CIF) " + "VALUES (?,?);";

		String queryPro = "INSERT INTO Proveedor_Producto " + "(proveedor_id,producto_id) " + "VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryP, handler, p.getEmpresa(),p.getCIF());
	
			batch = new ArrayList<>();
			for (Proveedor_Producto pro : productos) {
				batch.add(new Object[] { res.intValue(), pro.getProducto_id() });
			}
			runner.batch(conn, queryPro, batch.toArray(new Object[productos.size()][]));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (res != null ? res.intValue() : -1);
	}
	
	
	public Proveedor proveedor(int id) {
		Proveedor res=null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE Proveedor.id == "+ id;

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<Proveedor> proveedores() {
		List<Proveedor> res = new ArrayList<>();
		BeanListHandler<Proveedor> handler = new BeanListHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

}