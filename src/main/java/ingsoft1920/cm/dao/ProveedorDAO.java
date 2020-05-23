package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
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
	
	public void asignar_producto_proveedor(int proveedor_id, int producto_id, int PrecioVenta) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		
		String queryPro = "INSERT INTO Proveedor_Producto "
						 +"(proveedor_id,producto_id,precio_venta) "
						 +"VALUES (?,?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryPro, handler,proveedor_id,producto_id,PrecioVenta);
		} catch (Exception e) { e.printStackTrace(); }

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
	public Proveedor getByCIF(String proveedorCIF) {
		Proveedor res=null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE Proveedor.CIF=?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,proveedorCIF);

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
	
	// null si las credenciales no son correctas
	public Proveedor login(String usuario,String password) {
		Proveedor res = null;
		BeanHandler<Proveedor> handler = new BeanHandler<>(Proveedor.class);
		String query = "SELECT * FROM Proveedor WHERE usuario=? AND password=?;";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler, usuario, password);

		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}
	
	public boolean tryLogin(String usuario,String password) {
		return login(usuario,password) != null;
	}

	// Eliminar empleado
	public void eliminar(int proveedorID) {

		String eliminarProveedor = "DELETE FROM Proveedor WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			runner.update(conn, eliminarProveedor, proveedorID);
			
		} catch (Exception e) { e.printStackTrace(); }
	}
	
   public List<Proveedor> proveedoresPorHotel(int id) {
        List<Proveedor> proveedores = new LinkedList<>();
        BeanListHandler<Proveedor> beanListHandler = new BeanListHandler<>(Proveedor.class);
        String getProveedores = "SELECT p.* "
        					   +"FROM Proveedor p "
        					   +"JOIN Hotel_Proveedor_Producto hp ON p.id=hp.proveedor_id "
        					   +"JOIN Hotel h ON hp.hotel_id=h.id "
        					   +"WHERE h.id=? group by p.id ";

        try( Connection conn = conector.getConn() )
        {
            proveedores = runner.query(conn, getProveedores, beanListHandler,id);
        }
        catch(Exception e) { e.printStackTrace(); }
        return proveedores;
    }

   
    // Faltaría controlar la edición de los productos que vende
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
    
    // Cada Properties encapsula productos:
    // -producto_id: int
    // -precio: int
    // -unidad_medida: String
    public void asignarHotel(int hotel_id,int proveedor_id,Integer[] productos_ids) {

		String query = "INSERT INTO Hotel_Proveedor_Producto "
					  +"(hotel_id, proveedor_id, producto_id) "
					  +"VALUES (?,?,?);";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) 
		{
			batch = new ArrayList<>();
			for(int prodID : productos_ids) {
				batch.add(new Object[] {
							hotel_id,
							proveedor_id,
							prodID
						});
			}
			runner.batch(conn,query,batch.toArray(new Object[batch.size()][]));

		} catch (Exception e) { e.printStackTrace(); }
    }
    
    // Cada Properties tiene:
    // -id: int
    // -nombre: String
    // -precio_venta: int
    // -unidad_medida: String
    public List<Properties> productos(int proveedor_id) {
    	List<Properties> res = new ArrayList<>();
    	List<Map<String,Object>> resConsulta = null;
    	MapListHandler handler = new MapListHandler();
        String query = "SELECT p.id, p.nombre, pp.precio_venta, p.unidad_medida "
        			  +"FROM Producto p "
        			  +"JOIN Proveedor_Producto pp ON p.id = pp.producto_id "
        			  +"WHERE pp.proveedor_id = ?";
        
        try( Connection conn = conector.getConn() )
        {
            resConsulta = runner.query(conn,query,handler,proveedor_id);
        }
        catch(Exception e) { e.printStackTrace(); }
        
        if( resConsulta != null ) {
        	Properties aux;
        	for( Map<String,Object> fila : resConsulta ) {
        		aux = new Properties();
        		  aux.put("id",fila.get("id"));
        		  aux.put("nombre",fila.get("nombre"));
        		  aux.put("precio_venta",fila.get("precio_venta"));
        		  aux.put("unidad_medida",fila.get("unidad_medida"));
        		res.add(aux);
        	}
        }
        
        return res;
    }
    
    public void actualizarPrecioVenta(int producto_id, int proveedor_id, int precioVenta){

        String actualiza = "UPDATE Proveedor_Producto SET precio_venta = ? WHERE proveedor_id = ? and producto_id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, actualiza, precioVenta, proveedor_id, producto_id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<Hotel> hotelesNoAsignados(int proveedor_id) {
    	List<Hotel> res = new ArrayList<>();
    	BeanListHandler<Hotel> handler = new BeanListHandler<>(Hotel.class);
    	String query = "SELECT * "
    				  +"FROM Hotel "
    				  +"WHERE id NOT IN ( SELECT hotel_id "
    				  						+"FROM Hotel_Proveedor_Producto "
    				  						+"WHERE proveedor_id = ? "
    				  						+"GROUP BY hotel_id "
    				  				  +")";
    	
    	try ( Connection conn = conector.getConn() )
    	{
    		res = runner.query(conn,query,handler,proveedor_id);
    		
    	} catch( Exception e ) { e.printStackTrace(); }
    	
    	return res;
    }
    

}