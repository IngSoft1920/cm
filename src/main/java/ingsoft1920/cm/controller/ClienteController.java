package ingsoft1920.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	{
  	"nombre" : "Juan",
  	"apellidos" : "Rodríguez López",
  	"DNI" : "51265703F",
  	"email" : "juan@gmail.com",
  	“telefono” : “1234567”,
  	“nacionalidad” : “España”,
  	"password" : "patata"
	} 
	*/
	@PostMapping("/cliente")
	@ResponseBody
	public String recibirCliente(@RequestBody String json) {
		JsonObject jsonO = JsonParser.parseString( json ).getAsJsonObject();
		Cliente c = new Cliente(-1,
								jsonO.get("nombre").getAsString(),
								jsonO.get("apellidos").getAsString(),
								jsonO.get("DNI").getAsString(),
								jsonO.get("nacionalidad").getAsString(),
								jsonO.get("password").getAsString(),
								jsonO.get("email").getAsString(),
								jsonO.get("password").getAsString()
							   );
		
		int id = dao.anadir(c);
		
		JsonObject res = new JsonObject();
		
		if( id == -1 )
			res.addProperty("error", "Se ha producido un error al registrar al usuario");
		else
			res.addProperty("id",id);
				
		return res.toString();
	}

}
