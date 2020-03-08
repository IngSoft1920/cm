package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.conector.ConectorBBDD;

public class HotelDAO {
	
	@Autowired
    private QueryRunner runner = new QueryRunner();
	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();

	
	// Devuelve el id generado
	public int add(Hotel h) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Hotel "
					  +"(nombre,continente,pais,ciudad,direccion,estrellas,descripcion) "
					  +"VALUES (?,?,?,?,?,?,?);";
		
		try( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn,query,handler,
								h.getNombre(),
								h.getContinente(),
							    h.getPais(),
								h.getCiudad(),
								h.getDireccion(),
								h.getEstrellas(),
								h.getDescripcion()
								);
		} catch(Exception e) { e.printStackTrace(); }
		
		return ( res != null ? res.intValue() : -1 );
	}

}
