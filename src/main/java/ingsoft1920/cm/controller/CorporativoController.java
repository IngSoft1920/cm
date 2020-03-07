package ingsoft1920.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ingsoft1920.cm.dao.HotelDAO;

@Controller
@RequestMapping("/home-corp")
public class CorporativoController {
	
	/*
	private HotelDAO hotelDao = new HotelDAO();
	
	@GetMapping
	public String homeCorporativo() {
		return "/corp/home-corp.jsp";
	}

	@GetMapping("/select-hotel/{accion}")
	public String seleccionarHotel(Model m) {
		m.addAttribute("hoteles",hotelDao.hoteles());
		return "/corp/select-hotel.jsp";
	}

	@PostMapping("/select-hotel/{accion}")
	public String recibirHotelSeleccionado(@PathVariable("accion") String accion, int hotel_id) {

		String res = "redirect:";

		switch (accion) {

		case "empleado":
			res += "/empleado/" + hotel_id;
			break;

		case "proveedor":
			res += "/proveedor/" + hotel_id;
			break;

		case "editar":
			res += "/hotel/editar/" + hotel_id;
			break;

		case "eliminar":
			hotelDao.eliminarHotel(hotel_id);
			res+="/hotel";
			break;

		case "facturacion":
			res += "/hotel/facturacion/" + hotel_id;
			break;
		}

		return res;
	}
	*/

}
