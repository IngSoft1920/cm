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
	
	public Proveedor getByID(int id) {
		Proveedor res=null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE Proveedor.id == ?;";

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
	
    public void actualizar(Proveedor p){

        String actualiza = "UPDATE Proveedor SET empresa = ?, CIF = ? WHERE id = ?";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, actualiza, handler, p.getEmpresa(),p.getCIF(), p.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarProveedor(Empleado empleado){

	    String eliminaEmpleado = "DELETE FROM Proveedor WHERE id = ?";
	
	    try( Connection conn = conector.getConn() )
	    {
	        runner.update(conn, eliminaEmpleado, empleado.getId());
	    }
	    catch(Exception e) { e.printStackTrace(); }
	    
	    // Avisamos a em del borrado del empleado:
	    JsonObject json = new JsonObject();
	      json.addProperty("id",empleado.getId());
	      
	    APIout.enviar(json.toString(), 7002, "/eliminarEmpleado");
    }

}