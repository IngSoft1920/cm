package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
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

import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ServicioDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Servicio s) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Servicio "
					  +"(nombre) "
					  +"VALUES (?);";

		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, query, handler, s.getNombre());

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	public Servicio getByID(int servicioID) {
		Servicio res=null;
		BeanHandler<Servicio> handler = new BeanHandler<>(Servicio.class);
		String query = "SELECT * FROM Servicio WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,servicioID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public Servicio getByNombre(String nombre) {
		Servicio res=null;
		BeanHandler<Servicio> handler = new BeanHandler<>(Servicio.class);
		String query = "SELECT * FROM Servicio WHERE nombre = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,nombre);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Servicio> servicios() {
		List<Servicio> res = new ArrayList<>();
		BeanListHandler<Servicio> handler = new BeanListHandler<>(Servicio.class);
		String query = "SELECT * FROM Servicio";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}	
	
	// Cada Properties será así:
	// -id: int
	// -nombre: String
	// -precio: Integer
	// -unidad_medida: String
	// -num_instalaciones: int
	public List<Properties> serviciosHotel(int hotelID) {
		List<Map<String, Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		String query = "SELECT s.*,hs.precio,hs.unidad_medida,hs.num_instalaciones "
					  +"FROM Servicio s "
					  +"JOIN Hotel_Servicio hs ON s.id = hs.servicio_id "
					  +"WHERE hs.hotel_id = ?";

		try (Connection conn = conector.getConn()) {
			resConsulta = runner.query(conn, query, handler,hotelID);

		} catch (Exception e) { e.printStackTrace(); }
		
		List<Properties> res = new ArrayList<>();
		Properties aux;
		for( Map<String,Object> fila : resConsulta ) {
			aux = new Properties();
			aux.put("id", fila.get("id") );
			aux.put("nombre", fila.get("nombre") );
			
			// Estos campos podrían ser null
			if( fila.get("precio")!=null ) aux.put("precio", fila.get("precio"));
			if( fila.get("unidad_medida")!=null ) aux.put("unidad_medida", fila.get("unidad_medida"));
			if( fila.get("num_instalaciones")!=null ) aux.put("num_instalaciones", fila.get("num_instalaciones"));
			
			res.add(aux);
		}
		return res;
	}	
	
	public static void main(String[] args) {
		new ServicioDAO().serviciosHotel(1).forEach( p -> System.out.println(p + "\n") );; 
	}

}
