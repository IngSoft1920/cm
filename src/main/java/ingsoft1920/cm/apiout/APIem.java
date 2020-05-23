package ingsoft1920.cm.apiout;

import java.sql.Date;
import java.util.Properties;
import com.google.gson.JsonObject;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.ProfesionDAO;

public class APIem {
	
	private static final int PUERTO = 7002;
	private static JsonObject json;
	
	public static void resultadoAusencia(int id_ausencia,
										 String resultado,
										 String motivo)
	{
		json = new JsonObject();
		  json.addProperty("id_ausencia", id_ausencia);
		  json.addProperty("resultado", resultado);
		  json.addProperty("motivo", motivo);
		APIout.enviar(json.toString(), PUERTO, "/resultadoAusencia");
	}
	
	public static void enviarEmpleado(Empleado em,int hotel_id,Date fecha_contratacion)
	{
		String nombreProfesion = new ProfesionDAO().getByID(em.getProfesion_id()).getNombre();
		json = new JsonObject();
  	      json.addProperty("id",em.getId());
  	      json.addProperty("nombre",em.getNombre());
  	      json.addProperty("telefono",em.getTelefono());
  	      json.addProperty("email",em.getEmail());
  	      json.addProperty("ocupacion",nombreProfesion);
  	      json.addProperty("valor",em.getSueldo());
  	      json.addProperty("id_hotel", hotel_id);
  	      json.addProperty("fecha_contratacion",fecha_contratacion.toString());
  	      json.addProperty("dias_libres",em.getDias_libres());
  	      json.addProperty("superior",em.getSuperior());
  	    	  
  	      // Esto habría que cambiarlo (o no)
  	      json.addProperty("contrasenia", "12345");
  	      
  	    System.out.println( json.toString() );
  	    APIout.enviar(json.toString(),PUERTO,"/creaEmpleado");
	}
	
	public static void eliminarEmpleado(int empleadoID) {
		json = new JsonObject();
		  json.addProperty("id",empleadoID);
	      
	    APIout.enviar(json.toString(), PUERTO, "/eliminarEmpleado");
	}
	
	public static void editarEmpleado(Empleado em)
	{
		eliminarEmpleado(em.getId());
		Properties hotelDondeTrabaja = new EmpleadoDAO().hotelDondeTrabaja(em.getId());
		enviarEmpleado(em,
					   (int) hotelDondeTrabaja.get("hotel_id"),
					   (Date) hotelDondeTrabaja.get("fecha_contratacion"));
	}
	

}
