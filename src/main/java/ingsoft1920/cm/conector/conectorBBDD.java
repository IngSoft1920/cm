package ingsoft1920.cm.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conectorBBDD {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	private static final String SERVIDOR = "piedrafita.ls.fi.upm.es:8000";
	private static final String BBDD = "cm";
	private static final String URL = "jdbc:mysql://"+SERVIDOR+"/"+BBDD;

	private static final String USUARIO = "cm1";
	private static final String PASSWD = "ingSoft20cm1.711";


    private Connection conn = null;

	public void conectar() {
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USUARIO, PASSWD);
		} catch(Exception e) { e.printStackTrace(); }
	}

    public  Connection getConn() { return conn; }
    public boolean isConnected() { return conn != null; }

    public void closeConn() {
        if(conn != null) {
            try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            conn = null;
        }
    }

}
