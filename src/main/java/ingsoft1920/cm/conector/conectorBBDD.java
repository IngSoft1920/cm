package ingsoft1920.cm.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conectorBBDD {

    private Connection conn = null;
    private static String servidor, usuario, contrasena, baseDeDatos;

    public conectorBBDD(String servidor, String usuario, String contrasena, String baseDeDatos) {
        init(servidor, usuario, contrasena, baseDeDatos);
    }

    /**
     * @return the conn
     */
    public  Connection getConn() {
        return conn;
    }

    public void conectar (){

        if (conn==null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

                conn = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+baseDeDatos+"?rewriteBatchedStatements=TRUE&" +
                        "user="+usuario+"&password=" + contrasena +
                        "&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");

            } catch (SQLException ex) {

                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void init (String servidor, String usuario, String contrasena, String baseDeDatos){

        conectorBBDD.servidor = servidor;
        conectorBBDD.usuario = usuario;
        conectorBBDD.contrasena = contrasena;
        conectorBBDD.baseDeDatos = baseDeDatos;

        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    public boolean isConnected() {
        return conn != null;
    }

    public void closeConn() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }

}
