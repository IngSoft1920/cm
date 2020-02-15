package ingsoft1920.dbhulio;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ingsoft1920.bean.Hotel;

// Instrucciones de DBUtils: https://www.baeldung.com/apache-commons-dbutils

// Sólo podemos usar esta vía cuando nuestra query nos devuelve
// JUSTO los campos de un bean, de lo contrario petará. Si necesitamos
// hacer dicha query pues lo más conveniente es usar un MapHandler
public class PruebaDBUsandoDBUtilsConBeans {
	
	public static void main(String[] args) {
		
		BeanListHandler<Hotel> handler = new BeanListHandler<>(Hotel.class);
		QueryRunner runner = new QueryRunner();
		
		String query = "SELECT * FROM hotel";
		
		// Aquí el handler es más 'complejo' pero en cambio la 
		// respuesta es mucho más simple:
		List<Hotel> resultado = null;
		
		try { resultado = runner.query(Conexion.getConn(), query, handler); }
		catch(SQLException e) { e.printStackTrace(); }
		
		for( Hotel hotel : resultado ) 
			System.out.println(hotel);
		
	}

}
