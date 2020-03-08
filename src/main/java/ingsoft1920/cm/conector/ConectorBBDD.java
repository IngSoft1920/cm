package ingsoft1920.cm.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

public class ConectorBBDD {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String SERVIDOR = "piedrafita.ls.fi.upm.es:8000";
	private static final String BBDD = "cm";
	private static final String URL = "jdbc:mysql://"+SERVIDOR+"/"+BBDD;

	private static final String USUARIO = "cm1";
	private static final String PASSWD = "ingSoft20cm1.711";

    private Connection conn = null;
    
	private void conectar() {
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USUARIO, PASSWD);
		} catch(Exception e) { e.printStackTrace(); }
	}

    public Connection getConn() {
	    if ( !isConnected() )
	        conectar();
        
	    return conn;
	}

    private boolean isConnected() { 
    	
    	boolean res = false;
    	
    	try { res = conn != null && !conn.isClosed(); }
    	catch( SQLException e ) { e.printStackTrace(); }
    	
    	return res;
    }

}
