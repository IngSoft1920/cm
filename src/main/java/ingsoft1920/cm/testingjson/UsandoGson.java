package ingsoft1920.cm.testingjson;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;

public class UsandoGson {
	
//	private static Gson jsonMaker = new Gson();
//	
//	public static void main(String[] args) {
//		
//		Hotel h = new Hotel(1, "Sol Creciente", "Europa",
//							"España", "Madrid", "Calle Gran Vía,21");
//		JsonObject hJson = jsonMaker.toJsonTree(h).getAsJsonObject();
//		
//		List<Habitaciones> habs = List.of(new Habitaciones(1, Tipo.normal,50),
//										  new Habitaciones(1,Tipo.premium,10));
//		JsonArray habsJson = jsonMaker.toJsonTree(habs).getAsJsonArray();
//		
//		
//		hJson.add("habitaciones",habsJson);
//		String jsonFinal = hJson.toString();
//		
//		// Es un poco redundante poner hotel_id en cada habitación...
//		System.out.println( jsonFinal );
//	}

}
