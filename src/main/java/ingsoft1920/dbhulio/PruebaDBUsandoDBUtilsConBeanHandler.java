package ingsoft1920.dbhulio;

import java.sql.SQLException;	

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ingsoft1920.bean.Hotel;

// Instrucciones de DBUtils: https://www.baeldung.com/apache-commons-dbutils

// Este handler se usa cuando la respuesta de nuestra query
// es únicamente una fila (o solo queremos la primera entrada de una
// query que devuelva un bean)
public class PruebaDBUsandoDBUtilsConBeanHandler{
	
	private static final int ID_A_BUSCAR = 2;
	
	public static void main(String[] args) {
		
		BeanHandler<Hotel> handler = new BeanHandler<>(Hotel.class);
		QueryRunner runner = new QueryRunner();
		
		String query = "SELECT * FROM hotel WHERE hotel_id = ?";
		Object resultado = null;
		
		try { resultado = runner.query(Conexion.getConn(), query, handler,ID_A_BUSCAR); }
		catch(SQLException e) { e.printStackTrace(); }
	
		System.out.println(resultado);
		
	}

}
