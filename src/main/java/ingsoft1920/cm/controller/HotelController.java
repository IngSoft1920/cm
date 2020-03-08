package ingsoft1920.cm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Hotel;
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

}
