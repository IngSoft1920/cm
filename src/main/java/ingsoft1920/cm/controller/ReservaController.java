package ingsoft1920.cm.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.dao.ReservaDAO;

@Controller
public class ReservaController {
	
	@Autowired
	ReservaDAO dao;
	
	/*
	 {
	 "fecha_entrada" : “2020-02-10”,
	 "fecha_salida" : “2020-02-15”,
	 "importe" : 400,
	 "regimen" : “no_aplica”, // Valores posibles:"no_aplica","media_pension","pension_completa","todo_incluido"
	 "cliente_id" : null,     // Si la reserva es anónima ponéis null sino pues el id del cliente
	 "hotel_id" : 11,
	 "tipo_hab_id" : 9,
	 "numero_acompanantes" : 21
	}
	 */
	@PostMapping("/reserva")
	@ResponseBody
	public String recibirReserva(@RequestBody String json) {
		
		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();
		
		// Aquí controlamos si la reserva es anónima o no
		JsonElement clienteIdField = jsonO.get("cliente_id");
		Integer cliente_id = !clienteIdField.isJsonNull() ? clienteIdField.getAsInt() : null;
		
		Reserva r = new Reserva(-1,
								Date.valueOf( jsonO.get("fecha_entrada").getAsString() ),
								Date.valueOf( jsonO.get("fecha_salida").getAsString() ),
								jsonO.get("importe").getAsInt(),
								Reserva.Regimen.valueOf( jsonO.get("regimen").getAsString() ),
								jsonO.get("numero_acompanantes").getAsInt(),
								jsonO.get("hotel_id").getAsInt(),
								cliente_id,
								jsonO.get("tipo_hab_id").getAsInt()
							    );
		
		int id = dao.anadir(r);
		JsonObject res = new JsonObject();
		
		if( id == -1 )
			res.addProperty("error", "Se ha producido un error al añadir la reserva");
		else
			res.addProperty("id",id);
				
		return res.toString();
	}

}
