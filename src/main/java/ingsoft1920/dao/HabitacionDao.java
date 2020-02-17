package ingsoft1920.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.bean.Habitacion;
import ingsoft1920.bean.Hotel;

@Component
public class HabitacionDao {

	@Autowired
	private QueryRunner runner;
	
	
	public List<Habitacion> obtenerHabitaciones(){
		String query = "SELECT * FROM habitacion";
		
		BeanListHandler<Habitacion> handler = new BeanListHandler<>(Habitacion.class);
		
		List<Habitacion> res = null;
		try( Connection conn = Conexion.getConn() )
		{
			res = runner.query(conn, query,handler);
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public void reservarHabitacion(Hotel h,Habitacion.Tipo t) {
		// Primero sacaremos el id de una habitación 
		// aleatoria del hotel h, que sea de tipo t
		String queryRandomRoom = "SELECT id "+
								 "FROM habitacion "+
								 "WHERE hotel_id=? AND tipo=? "+
								 "LIMIT 1";
		
		// Luego simplemente la marcaremos como ocupada:
		String querySetOccupied = "UPDATE habitacion "+
								  "SET ocupada = true "+
								  "WHERE id = ?";
		
		Integer habID;
		try( Connection conn = Conexion.getConn() )
		{
			habID = runner.query(conn,
								 queryRandomRoom,
								 new ScalarHandler<Integer>(),
								 h.getId(),
								 t.toString()); // El toString de t es necesario para no mandar el objeto de tipo enumerado a la query
			
			runner.update(conn,
						  querySetOccupied,
						  habID);
		} catch(Exception e) { e.printStackTrace(); }
	
	}

}
