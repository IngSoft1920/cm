package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
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
			res = runner.insert(conn, queryP, handler, p.getEmpresa(), p.getCIF());

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

	// Proveedor
	public Proveedor proveedor(int id) {
		Proveedor res = null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE Proveedor.id == " + id;

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// Lista de proveedores
	public List<Proveedor> proveedores() {

		// List<Proveedor> res = new ArrayList<>();

		List<Proveedor> proveedores = new LinkedList<>();

		BeanListHandler<Proveedor> handler = new BeanListHandler<>(Proveedor.class);

		String query = "SELECT * FROM Proveedor";

		try (Connection conn = conector.getConn()) {
			proveedores = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return proveedores;
	}

	// Ver proveedor por id
	public Proveedor obtenerProveedorPorId(long id) {
		Proveedor res = new Proveedor();
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor as e " + "WHERE e.id=?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	// Editar proveedor
	public void editar(Proveedor proveedor) {
		String editarProveedor = "UPDATE Proveedor SET empresa = ?, CIF = ? WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			runner.update(conn, editarProveedor, proveedor.getEmpresa(), proveedor.getCIF());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Eliminar empleado
	public void eliminarProveedor(Proveedor proveedor) {

		String eliminarProveedor = "DELETE FROM Proveedor WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			runner.update(conn, eliminarProveedor, proveedor.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Avisamos a en del borrado del proveedor:
		JsonObject json = new JsonObject();
		json.addProperty("id", proveedor.getId());

		APIout.enviar(json.toString(), 7002, "/eliminar-proveedor");
	}
	
	
	/**
	 * Eliminar proveedor por ID
	 * 
	 * @param id
	 */
	public void eliminarProveedorPorId(long id) {

		String query = "DELETE FROM Proveedor WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			runner.update(conn, query, id);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}