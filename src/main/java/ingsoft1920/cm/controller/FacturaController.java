package ingsoft1920.cm.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.dao.FacturaDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;

public class FacturaController {

	@Autowired
	FacturaDAO dao;

	/*
		[
		  {
		   "importe": "int",
		   "pagado": "boolean",  //true o false
		   "cantidad_consumida": "int",
		   "nombre_servicio": "String",
		   "reserva_id": "int"
		  },
		  { //Ejemplo
		  "importe": 100,
		  "pagado": true,
		  "cantidad_consumida": 2,
		  "nombre_servicio": "piscina",
		  "reserva_id": 12
		  }
		]
	 */
	@PostMapping("/facturas")
	@ResponseBody
	public void volcarFacturas(@RequestBody String json) {

		JsonArray jsonA = JsonParser.parseString(json).getAsJsonArray();
		Factura factura = new Factura();
		JsonObject jsonO;
		
		// Dho nos envía esto una vez al día, son las facturas de ese día
		Date fecha = Date.valueOf( LocalDate.now() );

		for (JsonElement jsonE : jsonA) {
			if (jsonE.isJsonObject()) {

				jsonO = jsonE.getAsJsonObject();
				  factura.setFecha(fecha);
				  factura.setImporte(jsonO.get("importe").getAsInt());
				  factura.setPagado(jsonO.get("pagado").getAsBoolean());
				  factura.setCantidad_consumida(jsonO.get("cantidad_consumida").getAsInt());
				  factura.setServicio_id(new TipoHabitacionDAO().getByNombre(jsonO.get("nombre_servicio").getAsString()).getId());
				  factura.setReserva_id(jsonO.get("reserva_id").getAsInt());

				dao.anadir(factura);
			}
		}
	}

	/*
	 * [ { "reserva_id" : 12 }, { "reserva_id" : 16 } ]
	 */
	@GetMapping("/pagar")
	@ResponseBody
	public void pagarFacturas(@RequestBody String json) {

		JsonArray jsonA = JsonParser.parseString(json).getAsJsonArray();

		JsonObject jsonO;

		for (JsonElement jsonE : jsonA) {
			if (jsonE.isJsonObject()) {

				jsonO = jsonE.getAsJsonObject();

				dao.pagar(jsonO.get("reserva_id").getAsInt());

			}
		}
	}
	
}
