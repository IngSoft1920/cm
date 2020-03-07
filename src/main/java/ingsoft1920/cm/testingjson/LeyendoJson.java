package ingsoft1920.cm.testingjson;

import java.util.ArrayList;
import java.util.List;	

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;

public class LeyendoJson {
	
//	private static Gson jsonMaker = new Gson();
//	
//	public static void main(String[] args) {
//		String jsonString = getJson();
//		
//		JsonObject json = JsonParser.parseString( jsonString ).getAsJsonObject();
//		
//		Hotel h = new Hotel(json.get("id").getAsInt(),
//							json.get("nombre").getAsString(),
//							json.get("continente").getAsString(),
//							json.get("pais").getAsString(),
//							json.get("ciudad").getAsString(),
//							json.get("direccion").getAsString());
//		
//		List<Habitaciones> habs = new ArrayList<>();
//		
//		JsonObject jsonObjHab;
//		for(JsonElement elem : json.getAsJsonArray("habitaciones")) {
//			jsonObjHab = elem.getAsJsonObject();
//			
//			habs.add( new Habitaciones(h.getId(),
//									   Tipo.valueOf( jsonObjHab.get("tipo").getAsString() ),
//									   jsonObjHab.get("disponibles").getAsInt() ));
//		}
//		System.out.println(h + "\n" + habs);
//	}
//	
//	// Aquí tenemos lo mejor de los dos mundos
//	public static String getJson() {
//		
//		Hotel h = new Hotel(1, "Sol Creciente", "Europa",
//							"España", "Madrid", "Calle Gran Vía,21");
//		JsonObject hJson = jsonMaker.toJsonTree(h).getAsJsonObject();
//		
//		List<Habitaciones> habs = List.of(new Habitaciones(1, Tipo.normal,50),
//										  new Habitaciones(1,Tipo.premium,10));
//		JsonArray habsJson = new JsonArray();
//		JsonObject elem;
//		for(Habitaciones hs : habs) {
//			elem = new JsonObject();
//			elem.addProperty("tipo",hs.getTipo().toString());
//			elem.addProperty("disponibles",hs.getNum_disponibles());
//			
//			habsJson.add(elem);
//		}
//		hJson.add("habitaciones",habsJson);
//		String jsonFinal = hJson.toString();
//		
//		return jsonFinal;
//	}

}
