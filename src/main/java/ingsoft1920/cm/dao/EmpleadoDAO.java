package ingsoft1920.cm.dao;

import java.math.BigInteger;	
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.apiout.APIem;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.conector.ConectorBBDD;

import java.sql.Date;

@Component
public class EmpleadoDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();

    @Autowired
    private ConectorBBDD conector = new ConectorBBDD();

    public List<Empleado> empleados() {
        List<Empleado> empleados = new LinkedList<>();
        BeanListHandler<Empleado> beanListHandler = new BeanListHandler<>(Empleado.class);
        String getEmpleados = "SELECT * FROM Empleado";

        try( Connection conn = conector.getConn() )
        {
            empleados = runner.query(conn, getEmpleados, beanListHandler);
        }
        catch(Exception e) { e.printStackTrace(); }
        return empleados;
    }

    // *Properties
    // -hotel_id: int
    // -fecha_contratacion: Date
    public int anadir(Empleado empleado,Properties hotelEmpleado){
    	BigInteger idGenerado = null;
        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        String queryEmpleado = "INSERT INTO Empleado "
        					  +"(nombre,apellidos,email,telefono,sueldo,profesion_id,dias_libres) "
        					  +"VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        String queryHotelEmpleado = "INSERT INTO Hotel_Empleado "
        						   +"(empleado_id, hotel_id, fecha_contratacion) "
        						   +"VALUES (?, ?, ?)";

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, queryEmpleado, handler,
            						   empleado.getNombre(),
            						   empleado.getApellidos(),
            						   empleado.getEmail(),
            						   empleado.getTelefono(),
            						   empleado.getSueldo(),
            						   empleado.getProfesion_id(),
            						   empleado.getDias_libres()
            						  );
            
            runner.insert(conn, queryHotelEmpleado, handler,
            			  idGenerado,
            			  hotelEmpleado.get("hotel_id"),
            			  hotelEmpleado.get("fecha_contratacion")
            			 );
        }
        catch(Exception e) { e.printStackTrace(); }
        
        // Se ha insertado correctamente, lo mandamos a em
        if( idGenerado != null ) {
        	empleado.setId( idGenerado.intValue() );
        	APIem.enviarEmpleado(empleado,
        						 (int) hotelEmpleado.get("hotel_id"),
        						 (Date) hotelEmpleado.get("fecha_contratacion"));
        }

        return ( idGenerado != null ? idGenerado.intValue() : -1 );
    }
	
    public void eliminar(int empleadoID){
	    String query = "DELETE FROM Empleado WHERE id = ?";
	
	    try( Connection conn = conector.getConn() )
	    {
	        runner.update(conn, query, empleadoID);
	        
	    }
	    catch(Exception e) { e.printStackTrace(); }
	    
	    // Avisamos a em del borrado del empleado:
	    APIem.eliminarEmpleado(empleadoID);
    }
    
    // A la entrada de la base de datos con el empleado_id correspondiente
    // le seteamos toda la información del bean
    public void editar(Empleado info){
        String query = "UPDATE Empleado SET "
        			  +"nombre = ?, apellidos = ?, email = ?,"
        			  +"telefono = ?, sueldo = ?, profesion_id = ?,"
        			  +"dias_libres=? "
        			  +"WHERE id = ?";
        
        try( Connection conn = conector.getConn() )
        {
        	runner.update(conn,query,
        				  info.getNombre(),
        				  info.getApellidos(),
        				  info.getEmail(),
        				  info.getTelefono(),
        				  info.getSueldo(),
        				  info.getProfesion_id(),
        				  info.getDias_libres(),
        				  info.getId()
        				 );
        }
        
        catch(Exception e) { e.printStackTrace(); }
        
        // Notificamos a em
    	APIem.editarEmpleado(info);
    }
    
   	public Empleado getByID(int id) {
   		Empleado res = new Empleado();
   		BeanHandler<Empleado> handler = new BeanHandler<>(Empleado.class);
   		String query = "SELECT * FROM Empleado WHERE id=?;";

   		try (Connection conn = conector.getConn()) {
   			res = runner.query(conn, query, handler, id);

   		} catch (Exception e) { e.printStackTrace(); }
   		
   		return res;
   	}
   	
   	// TODO: Considerar que un empleado puede trabajar en varios hoteles
   	// (devolvería un List<Properties>
   	// El Properties tendrá:
   	// -hotel_id: int
   	// -fecha_contratacion: Date
   	public Properties hotelDondeTrabaja(int empleadoID) {
   		Properties res = null;
   		List<Map<String,Object>> resConsulta = null;
   		MapListHandler handler = new MapListHandler();
   		String query = "SELECT * FROM Hotel_Empleado WHERE empleado_id = ?";
   		
   		try( Connection conn = conector.getConn() )
   		{
   			resConsulta = runner.query(conn,query,handler,empleadoID);
   			
   		} catch(Exception e) { e.printStackTrace(); }
   		
   		if( resConsulta != null ) {
   			res = new Properties();
   			res.put("hotel_id",1/*resConsulta.get(0).get("hotel_id")*/);
   			res.put("fecha_contratacion",Date.valueOf("2020-01-01")/*resConsulta.get(0).get("fecha_contratacion")*/);
   		}
   		return res;
   	}
    

    public static void main(String[] args) {
//    	Empleado test = new Empleado(8, "Pepe", "Gonzalez", "pepe@gmail.com", "600600600", 1500, 1,new Integer[] {5,6});
//    	Properties hotel = new Properties();
//    	  hotel.put("hotel_id",1);
//    	  hotel.put("fecha_contratacion",Date.valueOf("2020-02-01"));
//    	
//    	new EmpleadoDAO().anadir(test,hotel);
//    	//new EmpleadoDAO().editar(test);
    }

}
