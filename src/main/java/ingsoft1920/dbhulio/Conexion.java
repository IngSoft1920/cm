package ingsoft1920.dbhulio;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class Conexion {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String SERVIDOR = "localhost";
	private static final String BBDD = "hulio";
	private static final String URL = "jdbc:mysql://"+SERVIDOR+"/"+BBDD;
	
	private static final String USUARIO = "root";
	private static final String PASSWD = "Lurdlurd!321";
	
	
	private Connection conn;
	
	public void conectar() {
		// Si conn no es null implica
		// que ya estamos conectados
		if(conn!=null)
			return;
	
		try {
			
			// Registramos el driver para que DriverManager
			// tenga en su lista el driver que queremos cargar
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USUARIO, PASSWD);
			
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public String realizarConsulta(String query,Object... params) {
		
		
		return null;
	}

	
	
	
	
	
	
	
}
