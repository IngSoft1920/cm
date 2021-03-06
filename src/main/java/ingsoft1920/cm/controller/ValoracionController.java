package ingsoft1920.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Valoracion;
import ingsoft1920.cm.dao.ValoracionDAO;

@Controller
public class ValoracionController {

	@Autowired
	ValoracionDAO dao;

	/*
	 * {
	 * "cliente_id" :1,
	 * "hotel_id" : 2,
	 * "cabecera" : “Muy bueno”,
	 * "cuerpo" : “Maravilloso…”,
	 * "nota" : 5
	 * }
	 */
	@PostMapping("/valoracion")
	@ResponseBody
	public void recibirReserva(@RequestBody String json) {

		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();

		Valoracion val = new Valoracion();
			val.setCliente_id( jsonO.get("cliente_id").getAsInt() );
			val.setHotel_id( jsonO.get("hotel_id").getAsInt() );
			val.setCabecera( jsonO.get("cabecera").getAsString() );
			val.setCuerpo( jsonO.get("cuerpo").getAsString() );
			val.setNota( jsonO.get("nota").getAsInt() );
			
		dao.anadir(val);
	}

}
