package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.ServicioDAO;

@Controller
public class HotelController {

	@Autowired
	HotelDAO dao = new HotelDAO();

	@GetMapping("/hotel/ge")
	@ResponseBody
	public String hotelesGE() {
		Gson jsonMaker = new Gson();
		JsonArray res = new JsonArray();
		List<Hotel> hoteles = dao.hoteles();

		JsonObject hotelJson;
		for (Hotel h : hoteles) {

			hotelJson = jsonMaker.toJsonTree(h, Hotel.class).getAsJsonObject();
			hotelJson.add("categorias", jsonMaker.toJsonTree(new CategoriaDAO().categoriasHotel(h.getId())));

			res.add(hotelJson);
		}

		return res.toString();
	}

	@GetMapping("/hotel/servicios/{hotel_id}")
	@ResponseBody
	public String serviciosHotel(@PathVariable int hotel_id) {
		return new Gson().toJsonTree( new ServicioDAO().serviciosHotel(hotel_id) ).toString();
	}

	@GetMapping("/hotel/disponibles")
	@ResponseBody
	public String disponibles(@RequestParam Date fecha_inicio, @RequestParam Date fecha_fin) {		
		return new Gson().toJsonTree( dao.disponibles(fecha_inicio, fecha_fin) ).toString();
	}
	

}
