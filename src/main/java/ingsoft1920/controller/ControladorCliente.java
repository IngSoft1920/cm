package ingsoft1920.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ingsoft1920.bean.Habitacion;
import ingsoft1920.bean.Hotel;
import ingsoft1920.dao.HabitacionDao;
import ingsoft1920.dao.HotelDao;

@Controller
public class ControladorCliente {

	@Autowired
	HabitacionDao habitacionDao;
	
	@Autowired
	HotelDao hotelDao;

	@GetMapping("/home-client")
	public String homeCliente() {
		return "home-client.jsp";
	}

	@GetMapping("/home-client/realizar-reserva")
	public String anadirHotelForm(Model m) {

		List<Hotel> hoteles = hotelDao.obtenerHoteles();
		m.addAttribute("hoteles", hoteles);
		
		return "home-client/realizar-reserva.jsp";
	}
	
	@PostMapping("/home-client/realizar-reserva")
	public String seleccionarHotel(Hotel h) {
		// Esto lo hacemos porque el hotel h que nos está llegando viene de la web
		// (NO de la base de datos). Luego, el campo id NO está relleno, así que 
		// tenemos que hacerlo para poder continuar con la reserva
		h = hotelDao.obtenerPorNombreYUbicacion(h.getNombre(), h.getUbicacion());
		System.out.println(h);
		
		// Hardcoded
		habitacionDao.reservarHabitacion(h, Habitacion.Tipo.normal);
		
		return "home-client/realizar-reserva.jsp"; 
	}

}
