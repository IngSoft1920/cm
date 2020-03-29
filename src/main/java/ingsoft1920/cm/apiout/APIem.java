package ingsoft1920.cm.apiout;

import java.sql.Date;
import java.util.Properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Empleado;
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
	
	// *Properties
	// -empleado: Empleado
	// -id_hotel: int
	// -fecha_contratacion: Date
	public static void enviarEmpleado(Empleado em,int hotel_id,Date fecha_contratacion)
	{
		json = new JsonObject();
  	      json.addProperty("id",em.getId());
  	      json.addProperty("nombre",em.getNombre());
  	      json.addProperty("telefono",em.getTelefono());
  	      json.addProperty("email",em.getEmail());
  	      json.addProperty("ocupacion",new ProfesionDAO().getByID(em.getProfesion_id()).getNombre());
  	      json.addProperty("valor",em.getSueldo());
  	      json.addProperty("id_hotel", hotel_id);
  	      json.addProperty("fecha_contratacion",fecha_contratacion.toString());
  	    
  	      // Esto habría que cambiarlo:
  	      json.addProperty("contrasenia", "12345");
  	      JsonArray diasLibres = new JsonArray();
  	        diasLibres.add(5);
  	        diasLibres.add(6);
  	      json.add("dias_libres",diasLibres);
 
  	    APIout.enviar(json.toString(),PUERTO,"/creaEmpleado");
	}
	
	public static void eliminarEmpleado(int empleadoID) {
		json = new JsonObject();
		  json.addProperty("id",empleadoID);
	      
	    APIout.enviar(json.toString(), PUERTO, "/eliminarEmpleado");
	}

}
