package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.List;

import ingsoft1920.cm.apiout.APIout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.dao.ClienteDAO;
import ingsoft1920.cm.dao.ReservaDAO;

@Controller
public class ReservaController {

	ReservaDAO dao = new ReservaDAO();

	/*
	 * Reserva NO ANÓNIMA 
	 * {
	 * "fecha_entrada" : “2020-02-10”,
	 * "fecha_salida" : “2020-02-15”, 
	 * "importe" : 400,
	 * "regimen" : “no_aplica”, // Valores posibles:"no_aplica","media_pension","pension_completa","todo_incluido"
	 * "cliente_id" : 14,
	 * "hotel_id" : 11,
	 * "tipo_hab_id" : 9,
	 * "numero_acompanantes": 21 
	 * }
	 */
	@PostMapping("/reserva")
	@ResponseBody
	public String recibirReserva(@RequestBody String json) {

		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();

		Reserva reserva = new Reserva();
		  reserva.setFecha_entrada( Date.valueOf(jsonO.get("fecha_entrada").getAsString()) );
		  reserva.setFecha_salida( Date.valueOf(jsonO.get("fecha_salida").getAsString() ));
		  reserva.setImporte( jsonO.get("importe").getAsInt() );
		  reserva.setRegimen_comida( Reserva.Regimen.valueOf(jsonO.get("regimen").getAsString() ));
		  reserva.setCliente_id( jsonO.get("cliente_id").getAsInt() );
		  reserva.setHotel_id( jsonO.get("hotel_id").getAsInt() );
		  reserva.setTipo_hab_id( jsonO.get("tipo_hab_id").getAsInt() );
		  reserva.setNumero_acompanantes( jsonO.get("numero_acompanantes").getAsInt());
		
		int id = dao.anadir(reserva);
		
		JsonObject res = new JsonObject();
		  res.addProperty("id", id);

		return res.toString();
	}

	/*
	 * Reserva ANÓNIMA:
	 * {
	 * "fecha_entrada" : “2020-02-10”,
	 * "fecha_salida" : “2020-02-15”,
	 * "importe" : 400,
	 * "regimen" : “no_aplica”, // Valores posibles:"no_aplica","media_pension","pension_completa","todo_incluido"
	 * "email_cliente" : "pepe@gmail.com",
	 * "hotel_id" : 11,
	 * "tipo_hab_id" : 9,
	 * "numero_acompanantes" : 21
	 * }
	 */
	@PostMapping("/reserva/anonima")
	@ResponseBody
	public String recibirReservaAnonima(@RequestBody String json) {

		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();

		// Primero cogemos el email y obtenemos el id del cliente correspondiente
		// (si ya está registrado el email se coge el id sino se añade y se coge el id generado)
		int cliente_id = new ClienteDAO()
								.getClienteIdAnonimo(jsonO.get("email_cliente").getAsString());

		// Luego añadimos la reserva:
		Reserva reserva = new Reserva();
		  reserva.setFecha_entrada( Date.valueOf(jsonO.get("fecha_entrada").getAsString()) );
		  reserva.setFecha_salida( Date.valueOf(jsonO.get("fecha_salida").getAsString() ));
		  reserva.setImporte( jsonO.get("importe").getAsInt() );
		  reserva.setRegimen_comida( Reserva.Regimen.valueOf(jsonO.get("regimen").getAsString() ));
		  reserva.setCliente_id( cliente_id );
		  reserva.setHotel_id( jsonO.get("hotel_id").getAsInt() );
		  reserva.setTipo_hab_id( jsonO.get("tipo_hab_id").getAsInt() );
		  reserva.setNumero_acompanantes( jsonO.get("numero_acompanantes").getAsInt());

		int id = dao.anadir(reserva);

		JsonObject res = new JsonObject();
		  res.addProperty("id_reserva", id);
		  res.addProperty("cliente_id", cliente_id);

		return res.toString();
	}

	@GetMapping("/reserva/cliente/{cliente_id}")
	@ResponseBody
	public String reservasCliente(@PathVariable int cliente_id) {

		List<Reserva> reservasCliente = dao.reservasDeUnCliente(cliente_id);

		JsonArray res = new JsonArray();
		JsonObject elem;
		for (Reserva r : reservasCliente) {
			elem = new JsonObject();
			elem.addProperty("reserva_id", r.getId());
			elem.addProperty("hotel_id", r.getHotel_id());
			elem.addProperty("tipo_hab_id", r.getTipo_hab_id());
			elem.addProperty("regimen", r.getRegimen_comida().name());
			elem.addProperty("importe", r.getImporte());
			elem.addProperty("fecha_entrada", r.getFecha_entrada().toString());
			elem.addProperty("fecha_salida", r.getFecha_salida().toString());

			res.add(elem);
		}

		return res.toString();
	}

	@PostMapping("/reserva/eliminar/{reserva_id}")
	@ResponseBody
	public void eliminarReserva(@PathVariable int reserva_id) {
		dao.eliminarReserva(reserva_id);
	}

	@GetMapping("/reserva/getCliente/{reserva_id}")
	@ResponseBody
	public String clienteReserva(@PathVariable int reserva_id) {

		Cliente cliente = dao.getCliente(reserva_id);

		JsonObject res = new JsonObject();

		res.addProperty("cliente_id", cliente.getId());
		res.addProperty("nombre", cliente.getNombre());
		res.addProperty("apellidos", cliente.getApellidos());
		res.addProperty("DNI", cliente.getDNI());
		res.addProperty("email", cliente.getEmail());
		res.addProperty("password", cliente.getPassword());
		res.addProperty("nacionalidad", cliente.getNacionalidad());
		res.addProperty("telefono", cliente.getTelefono());

		return res.toString();
	}

}
