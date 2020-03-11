package ingsoft1920.cm.controller;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.dao.HotelDAO;


@Controller
public class HotelController {
	
	@Autowired
	HotelDAO dao;
	
	@GetMapping("/hotel/ge")
	@ResponseBody
	public String hotelesGE() {
		Gson jsonMaker = new Gson();
		JsonArray res = new JsonArray();
		List<Hotel> hoteles = dao.hoteles();
		
		JsonObject hotelJson;
		for( Hotel h : hoteles ) {
			
			hotelJson = jsonMaker.toJsonTree(h, Hotel.class).getAsJsonObject();
			hotelJson.add("categorias",
						  jsonMaker.toJsonTree(dao.categoriasHotel( h.getId() ))
						 );
			
			res.add(hotelJson);
		}
		
		return res.toString();
	}
	
	@GetMapping("/hotel/servicios/{hotel_id}")
	@ResponseBody
	public String serviciosHotel(@PathVariable int hotel_id) {
		
		Map<Servicio,Hotel_Servicio> servicios = dao.serviciosHotel(hotel_id);
		
		if(servicios==null) {
			JsonObject error = new JsonObject();
			error.addProperty("error","Ha habido un problema buscando los servicios");
			return error.toString();
		}
		
		JsonArray res = new JsonArray();
		JsonObject elem;
		for(Entry<Servicio,Hotel_Servicio> entrada : servicios.entrySet()) {
			elem = new JsonObject();
			
			elem.addProperty("id",entrada.getKey().getId());
			elem.addProperty("nombre",entrada.getKey().getNombre());
			elem.addProperty("precio",entrada.getValue().getPrecio());
			elem.addProperty("unidad",entrada.getValue().getUnidad_medida());
			
			res.add(elem);
		}
		return res.toString();
	}

}
