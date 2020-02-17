package ingsoft1920.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.bean.Habitacion;
import ingsoft1920.bean.Hotel;

@Component
public class HotelDao {

	@Autowired
	private QueryRunner runner;

	public void anadirHotel(Hotel h) {

		String query = "INSERT INTO hotel (nombre,ubicacion) VALUES (?,?)";

		// El handler es para recibir el id que se le asigna
		// al hotel que estamos añadiendo aquí
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		BigInteger idGenerado = null;

		try( Connection conn = Conexion.getConn() )
		{
			idGenerado = runner.insert(conn,query,handler,h.getNombre(),h.getUbicacion());
		}
		catch(Exception e) { e.printStackTrace(); }

		// Le seteamos al bean el id para que el matcheo con
		// la base de datos sea total. Ahora mismo no es útil
		// para nada pero bueno:
		h.setId(idGenerado.intValue());
	}
	
	public List<Hotel> obtenerHoteles(){
		String query = "SELECT * FROM hotel";
		BeanListHandler<Hotel> handler = new BeanListHandler<>(Hotel.class);
		
		List<Hotel> res = null;
		try( Connection conn = Conexion.getConn() )
		{
			res = runner.query(conn, query,handler);
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	public Hotel obtenerPorNombreYUbicacion(String nombre,String ubicacion) {
		String query = "SELECT * FROM hotel WHERE nombre=? AND ubicacion=?";
		
		Hotel res=null;
		try( Connection conn = Conexion.getConn() )
		{
			res = runner.query(conn,
							   query,
							   new BeanHandler<>(Hotel.class),
							   nombre,
							   ubicacion);
		}
		catch(Exception e) { e.printStackTrace(); }
		return res;
	}

}
