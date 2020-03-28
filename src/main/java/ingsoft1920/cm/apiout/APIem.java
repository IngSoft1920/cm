package ingsoft1920.cm.apiout;

import com.google.gson.JsonObject;

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

}
