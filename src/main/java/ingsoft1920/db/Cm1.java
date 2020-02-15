package ingsoft1920.db;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class Cm1 {
	private static Connection conn = null;
	private static String servidor, baseDeDatos, usuario, password;
	public static void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://"+ servidor +"/"+baseDeDatos+"?" + 
		       "user="+usuario+"&password="+password+ "&use");
		}catch (SQLException ex){
			System.out.println("SQLexception " + ex.getMessage());
			System.out.println("SQLState " +ex.getSQLState());
			System.out.println("VenderError: "+ex.getErrorCode());
		}
	}
	
	//Establece los atributos para la conexion. Si hay una sesion abierta, la cierra
	public static void init (String servidor, String usuario, String password, String baseDeDatos){
		
		Cm1.servidor = servidor;
		Cm1.usuario = usuario; 
		Cm1.password = password;
		Cm1.baseDeDatos = baseDeDatos;
		
		
		if (conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	//Lee el CSV e inserta su contenido en la BD. Para info en un formato concreto
	public static void insertFromCSV(String csv, String table) {
		File file = new File(csv);
		PreparedStatement stmt=null;
		ResultSet rs=null;
		String st;
		String nombreParametros;
		String columnas[];
		try {
		BufferedReader br = new BufferedReader(new FileReader(file));
		nombreParametros=br.readLine();
		nombreParametros=nombreParametros.replace("\"", "");
		nombreParametros=nombreParametros.replace(";", ",");
		while((st=br.readLine()) != null){
				st=st.replace("\"", "");
				columnas=st.split(";");
				stmt = conn.prepareStatement("INSERT INTO "+ table+" ("+nombreParametros+") "+"values("+numParam(columnas)+")");
				for(int i=1; i<=columnas.length;i++) {
					System.out.println(columnas[i-1]);
					if(columnas[i-1].equalsIgnoreCase("NULL"))
						stmt.setObject(i, null); //constante para ponerlo a null 
					else
						stmt.setObject(i, columnas[i-1]);
					
				}
				stmt.executeUpdate(); //solo execute
				
			
			}
		}catch(Exception e) {
				e.printStackTrace();
		}finally { 
		if (rs != null) { 
			try { rs.close(); 
			} catch (SQLException sqlEx) { 
			} rs = null; 
		} 
		if (stmt != null) { 
			try { stmt.close(); 
			} catch (SQLException sqlEx) { }  
			stmt = null; 
		} 
	}
	}
	
	
	public static String habitacionesDisponibles(Date fecha) {
		if(conn==null)
			conectar();
		ResultSet rs=null;
		String resultado="HOTEL;    UBICACION;      TARIFA;     TIPO; \n";
		PreparedStatement st=null;
		try {
			st=conn.prepareStatement("Select ho.name, ho.ubicacion, hab.tarifa, hab.tipo from habitacion as hab join hotel "
					+ "as ho on hab.habitacion_hotel_id=ho.hotel_id\n" + 
					"join reserva as res on res.reserva_hotel_id=ho.hotel_id where res.fecha_inicio >="+ fecha+
					"AND res.fecha_fin<="+ fecha );
			rs=st.executeQuery();
			while(rs.next()) {
				resultado+=rs.getString("ho.name")+"    "+rs.getString("ho.ubicacion") +"    "+rs.getDouble("hab.tarifa")+
						"    "+rs.getString("hab.tipo")+"\n";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (st != null) { try { st.close(); } catch (SQLException sqlEx) { }  st = null; } 
		}
		return resultado;
	}
}