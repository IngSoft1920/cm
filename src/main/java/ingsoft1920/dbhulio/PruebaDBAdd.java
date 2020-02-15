package ingsoft1920.dbhulio;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class PruebaDBAdd {
	
	public static void main(String[] args) {
		
		QueryRunner runner = new QueryRunner();
		String query = "INSERT INTO hotel (nombre,ubicacion) VALUES (?,?)";
		
		Object[][] aInsertar = {
			{"Hotel Azul","Calle Naipe,87"},
			{"La Cascada","Calle Caja,1"}
		};
				
		try { runner.batch(Conexion.getConn(), query,aInsertar); }
		catch(SQLException e) { e.printStackTrace(); }
	}

}
