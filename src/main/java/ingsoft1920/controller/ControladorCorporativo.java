package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControladorCorporativo {

	@GetMapping("/home-corp")
	public String homeCorporativo() {
		return "home-corp.jsp";
	}
	
	@GetMapping("/home-corp/anadir-hotel")
	public String anadirHotelForm() {
		return "home-corp/anadir-hotel.jsp";
	}
	
	@PostMapping("/home-corp/anadir-hotel")
	public String anadirHotelTratamientoSolicitud(
			@RequestParam("nombreHotel") String nombreHotel ,
			@RequestParam("ubicacionHotel") String ubiHotel) {
		
		System.out.printf("\nDatos recibidos correctamente... \n nombre: %s \n ubicación: %s\n",
							nombreHotel,ubiHotel);
		return "home-corp/anadir-hotel.jsp";
	}

}
