package ingsoft1920.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		
		
		return "";
	}

}
