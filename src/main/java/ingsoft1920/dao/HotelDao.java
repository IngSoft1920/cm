package ingsoft1920.dao;

import java.math.BigInteger;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		h.setHotel_id(idGenerado.intValue());
	}

}
