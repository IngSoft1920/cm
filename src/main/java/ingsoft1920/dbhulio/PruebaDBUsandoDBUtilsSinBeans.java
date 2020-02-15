package ingsoft1920.dbhulio;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

// Instrucciones de DBUtils: https://www.baeldung.com/apache-commons-dbutils

public class PruebaDBUsandoDBUtilsSinBeans {
	
	public static void main(String[] args) {
		
		MapListHandler handler = new MapListHandler();
		QueryRunner runner = new QueryRunner();
		
		String query = "SELECT * FROM hotel";
		
		// Tenemos un map por cada fila de la consulta.
		// La 'key' es justamente el nombre de la columna
		List<Map<String,Object>> resultado = null;
		
		try { resultado = runner.query(Conexion.getConn(), query, handler); }
		catch(SQLException e) { e.printStackTrace(); }
		
		for( Map<String,Object> fila : resultado ) 
			System.out.println(fila);
		
	}

}
