package ingsoft1920.cm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.dao.ClienteDAO;

// link api: https://github.com/IngSoft1920/cm/wiki/API-ge

@Controller
public class ClienteController {

	@Autowired
	ClienteDAO dao;

	/*
	 * {
	 * "nombre" : "Juan",
	 * "apellidos" : "Rodríguez López",
	 * "DNI" : "51265703F",
	 * "email" : "juan@gmail.com",
	 * “telefono” : “1234567”,
	 * “nacionalidad” : “España”,
	 * "password" : "patata" }
	 */
	@PostMapping("/cliente")
	@ResponseBody
	public String recibirCliente(@RequestBody String json) {
		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();
		
		System.out.println(jsonO.toString());
		
		Cliente cliente = new Cliente();
		  cliente.setNombre( jsonO.get("nombre").getAsString() );
		  cliente.setApellidos( jsonO.get("apellidos").getAsString() );
		  cliente.setDNI( jsonO.get("DNI").getAsString() );
		  cliente.setEmail( jsonO.get("email").getAsString() );
		  cliente.setTelefono( jsonO.get("telefono").getAsString() );
		  cliente.setNacionalidad( jsonO.get("nacionalidad").getAsString() );
		  cliente.setPassword( jsonO.get("password").getAsString() );

		int id = dao.anadir(cliente);

		JsonObject res = new JsonObject();
		  res.addProperty("id", id);

		return res.toString();
	}

	/*
	 * {
	 * "email" : "juan@gmail.com",
	 * "password" : "patata"
	 * }
	 */
	@PostMapping("/cliente/login")
	@ResponseBody
	public String login(@RequestBody String json) {
		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();

		Cliente cliente = dao.login(jsonO.get("email").getAsString(),
									jsonO.get("password").getAsString());
		if (cliente == null) {
			JsonObject res = new JsonObject();
			res.addProperty("error", "No se ha podido iniciar sesión");
			return res.toString();
		} else {
			return new Gson().toJson(cliente, Cliente.class).toString();
		}
	}
	
	@GetMapping("/clientes")
	@ResponseBody
	public String clientesIds() {
		List<Cliente> listaClientes = dao.clientes();


		JsonArray res = new JsonArray();
		JsonObject elem;
		int i=0;
		for (Cliente c : listaClientes) {
			elem = new JsonObject();
			elem.addProperty("id",c.getId());
			elem.addProperty("nombre", c.getNombre());
			res.add(elem);
		}
		
		return res.toString();
	}
	
	@GetMapping("/nombre-clientes")
	@ResponseBody
	public String clientesNombres() {
		List<Cliente> listaClientes = dao.clientes();
		List<String> listaNombres = new ArrayList<>();

		JsonArray res = new JsonArray();
		JsonObject elem;
		for (Cliente c : listaClientes) {
			listaNombres.add(c.getNombre());
		}
		elem = new JsonObject();
		elem.addProperty("nombre-clientes", listaNombres.toString());
		res.add(elem);

		return res.toString();
	}
	
	
	@GetMapping("/preferencias/{cliente_id}")
	@ResponseBody
	public String preferenciasCliente(@PathVariable int cliente_id) {
		JsonObject res = new JsonObject();
		  res.addProperty("preferencias", dao.preferenciasCliente(cliente_id) );

		return res.toString();
	}
	
	@PostMapping("preferencias/{cliente_id}")
	@ResponseBody
	public String recibirPreferenciasCliente(@PathVariable int cliente_id,
										   @RequestBody String json)
	{
		String nuevasPreferencias = JsonParser
										.parseString(json)
										.getAsJsonObject()
										.get("preferencias")
										.getAsString();
		
		dao.anadirPreferenciasCliente(cliente_id, nuevasPreferencias);
		
		return "ok";
	}

}
