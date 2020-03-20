package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.bean.auxiliares.Hotel_Empleado;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class EmpleadoDAO {


    @Autowired
    private QueryRunner runner = new QueryRunner();

    private ConectorBBDD conector = new ConectorBBDD();

    public List<Empleado> getEmpleados() {

        BeanListHandler<Empleado> beanListHandler = new BeanListHandler<>(Empleado.class);
        QueryRunner runner = new QueryRunner();

        String getEmpleados = "SELECT * FROM Empleado";

        List<Empleado> empleados = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            empleados = runner.query(conn, getEmpleados, beanListHandler);
        }
        catch(Exception e) { e.printStackTrace(); }
        return empleados;
    }

    public void asignarSueldo(BigInteger id, int sueldo){

        String asignaSueldo = "UPDATE Empleado SET sueldo = ? WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, asignaSueldo, sueldo, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public int anadirEmpleado(Empleado empleado, Hotel_Empleado hotelEmpleado){

        String anadeEmpleado = "INSERT INTO Empleado "
        					  +"(nombre,apellidos,email,telefono,sueldo,profesion_id) "
        					  +"VALUES (?, ?, ?, ?, ?, ?);";
        
        String anadeEmpleadoaHotel = "INSERT INTO Hotel_Empleado "
        						 	+"(empleado_id, hotel_id, fecha_contratacion) "
        						 	+"VALUES (?, ?, ?)";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        BigInteger idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, anadeEmpleado, handler,
            						   empleado.getNombre(),
            						   empleado.getApellidos(),
            						   empleado.getEmail(),
            						   empleado.getTelefono(),
            						   empleado.getSueldo(),
            						   empleado.getProfesion_id()
            						  );
            
            runner.insert(conn, anadeEmpleadoaHotel, handler,
            			  idGenerado,
            			  hotelEmpleado.getHotel_id(),
            			  hotelEmpleado.getFecha_contratacion()
            			 );
        }
        catch(Exception e) { e.printStackTrace(); }
        
        // Se ha insertado correctamente, lo mandamos a em
        if( idGenerado != null ) {
        	ProfesionDAO pdao = new ProfesionDAO();
        	String nombreProfesion = pdao.getByID(empleado.getProfesion_id()).getNombre();
        	        	
        	JsonObject json = new JsonObject();
        	  json.addProperty("id",idGenerado.intValue());
        	  json.addProperty("nombre",empleado.getNombre());
        	  json.addProperty("telefono",empleado.getTelefono());
        	  json.addProperty("email",empleado.getEmail());
        	  json.addProperty("ocupacion",nombreProfesion);
        	  json.addProperty("valor",empleado.getSueldo());
        	  json.addProperty("id_hotel",hotelEmpleado.getHotel_id());
        	  json.addProperty("fecha_contratacion",hotelEmpleado.getFecha_contratacion().toString());
        	  
        	APIout.enviar(json.toString(),7002,"/creaEmpleado");
        }

        return ( idGenerado != null ? idGenerado.intValue() : -1 );
    }
    
    public static void main(String[] args) {
    	EmpleadoDAO dao = new EmpleadoDAO();
    	   	
		Empleado pepe = new Empleado(4, "Pepe", "Dominguez Perez", "pepe@gmail.com", "123456", 1500, 1);
		//Hotel_Empleado he = new Hotel_Empleado(-1, 1, Date.valueOf("2020-02-01"));
		//dao.cambiarNombre1(pepe, "juan");
		//System.out.println( dao.anadirEmpleado(pepe,he) );
    	
		//dao.eliminarEmpleado( pepe );
    	
	}
    
	public Empleado obtenerEmpleadoPorId(long id) {
		Empleado res = new Empleado();
		BeanHandler<Empleado> handler = new BeanHandler<>(Empleado.class);
		String query = "SELECT * FROM Empleado as e " + "WHERE e.id=?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
    public void eliminarEmpleado(Empleado empleado){

	    String eliminaEmpleado = "DELETE FROM Empleado WHERE id = ?";
	
	    try( Connection conn = conector.getConn() )
	    {
	        runner.update(conn, eliminaEmpleado, empleado.getId());
	    }
	    catch(Exception e) { e.printStackTrace(); }
	    
	    // Avisamos a em del borrado del empleado:
	    JsonObject json = new JsonObject();
	      json.addProperty("id",empleado.getId());
	      
	    APIout.enviar(json.toString(), 7002, "/eliminarEmpleado");
    }
    
    public void editar(Empleado empleado){

        String cambiaEmail = "UPDATE Empleado SET nombre = ?, apellidos = ?, email = ?, telefono = ?, sueldo = ? WHERE id = ?";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEmail, handler, empleado.getNombre(),empleado.getApellidos(),empleado.getEmail(),empleado.getTelefono(),empleado.getSueldo(), empleado.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void cambiarNombre(Empleado empleado, String nombre){

        String cambiaEmail = "UPDATE Empleado SET nombre = ? WHERE id = ?";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEmail, handler, nombre, empleado.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    

        String cambiaApellidos = "UPDATE Empleado SET apellidos = ? WHERE email = ?";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaApellidos, handler, nuevosApellidos,
            		empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarProfesion(Empleado empleado, Profesion nuevaProfesion){

        String cambiaProfesion = "UPDATE Empleado SET profesion_id = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaProfesion, handler, nuevaProfesion, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void editar(Empleado empleado){
        String cambiaEmail = "UPDATE Empleado SET nombre = ?, apellidos = ?, email = ?, telefono = ?, sueldo = ? WHERE id = ?";
        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEmail, empleado.getNombre(),empleado.getApellidos(),empleado.getEmail(),empleado.getTelefono(),empleado.getSueldo(), empleado.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void cambiarNombre1(Empleado empleado, String nombre){

        String cambiaEmail = "UPDATE Empleado SET nombre = ? WHERE id = ?";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEmail, nombre, empleado.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
