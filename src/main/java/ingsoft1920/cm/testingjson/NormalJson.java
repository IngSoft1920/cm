package ingsoft1920.cm.testingjson;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;

public class NormalJson {
//	
//	public static void main(String[] args) {
//		
//		Hotel h = new Hotel(1, "Sol Creciente", "Europa",
//							"España", "Madrid", "Calle Gran Vía,21");
//		
//		List<Habitaciones> habs = List.of(new Habitaciones(1, Tipo.normal,50),
//										  new Habitaciones(1,Tipo.premium,10));
//		
//		JsonObject hotelHabs = new JsonObject();
//		  hotelHabs.addProperty("id", h.getId());
//		  hotelHabs.addProperty("nombre", h.getNombre());
//		  hotelHabs.addProperty("continente", h.getContinente());
//		  hotelHabs.addProperty("pais", h.getPais());
//		  hotelHabs.addProperty("ciudad", h.getCiudad());
//		  hotelHabs.addProperty("direccion", h.getDireccion());
//		  
//		  JsonArray capHabs = new JsonArray();
//		  JsonObject capHabitacion;
//		  for(Habitaciones hab : habs) {
//			  capHabitacion = new JsonObject();
//			  capHabitacion.addProperty("tipo",hab.getTipo().toString());
//			  capHabitacion.addProperty("disponibles",hab.getNum_disponibles());
//			  
//			  capHabs.add(capHabitacion);
//		  }	  
//		  hotelHabs.add("habitaciones", capHabs);
//		System.out.println(hotelHabs);
//	}

}
