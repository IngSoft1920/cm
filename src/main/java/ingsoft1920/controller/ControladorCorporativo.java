package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ingsoft1920.bean.Hotel;

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
	
	// Spring automáticamente encapsula los datos mandados
	// por el formulario en el objeto Hotel
	@PostMapping("/home-corp/anadir-hotel")
	public String anadirHotelTratamientoSolicitud(Hotel h) {		
		System.out.println("Datos recibidos correctamente:\n"+h);		
		return "home-corp/anadir-hotel.jsp";
	}

}
