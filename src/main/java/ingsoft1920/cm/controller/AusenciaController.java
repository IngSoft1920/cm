package ingsoft1920.cm.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Ausencia;
import ingsoft1920.cm.dao.AusenciaDAO;

@Controller
public class AusenciaController {

	@Autowired
	private AusenciaDAO dao;

	/*
	 * { 
	 * "ausencia_id" : 21,
	 * “motivo” : “vacaciones”, //Siempre será “vacaciones” o bien “baja”
	 * “fecha_inicio” : “2020-03-01”,
	 * “fecha_fin” : “2020-03-08”,
	 * “empleado_id” : 21 }
	 */
	@PostMapping("/ausencia")
	@ResponseBody
	public void recibirAusencia(@RequestBody String json) {
		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();
		Ausencia a = new Ausencia(jsonO.get("ausencia_id").getAsInt(),
								  jsonO.get("motivo").getAsString(),
								  Date.valueOf(jsonO.get("fecha_inicio").getAsString()),
								  Date.valueOf(jsonO.get("fecha_fin").getAsString()),
								  Ausencia.Estado.pendiente,
								  jsonO.get("empleado_id").getAsInt());
		dao.anadir(a);
	}

}
