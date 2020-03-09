package ingsoft1920.cm.fna;

public class FacturaDAO {
	
	//IMPORTANTE: para poder ver como tienen distribuida la base de datos meteros a slack, en el canal company-approach julio paso un enlace
	//a la wiki de cm en github, dentro del enlace seleccionais Tablas cm: enlace. Ahi teneis toda la informacion para poder hacer las consultas
	
	
	//Dado el id de un hotel, devolver la suma de todos los salarios de ese hotel

	public static int getSalario (int hotel_id) {
		if (conexion==null) 
			conexion.conectar();
		
		java.sql.PreparedStatement stmt = null; 
		ResultSet rs = null; 
		try {
	
			stmt = conexion.getConexion().prepareStatement("SELECT SUM(sueldo) FROM Hotel_Empleado,Empleado  WHERE empleado.id=Hotel_Empleado.empleado_id && Hotel_Empleado.id=hotel_id");		
			double a =stmt.executeUpdate();
			
			
		}
		catch (SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
		} finally { // it is a good idea to release resources in a finally block 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		
	}
	}	
		return a;
	}
	//Dado el id de un hotel, por cada reserva que haya en el hotel, sumar el importe de la reserva y el precio de las facturas asociadas 
	//a dicha reserva. Teneis que devolver el total de todas las reservas del hotel
	public static int getBeneficios (int hotel_id) {
		return 0;
	}
	
//Dado el id de un hotel, devolver el total invertido en la compra de los alimentos. Es decir, hacer una consulta que por cada producto_id distinto
//Reciba el precio (individual) de dicho producto, y la cantidad total pedida en los pedidos y multiplicarla. 
//Esta consulta es la más jodida de las tres por como tienen hecha la base de datos
	public static int getAlimentos (int hotel_id) {
		return 0;
	}


}