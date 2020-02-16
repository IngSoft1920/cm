package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ingsoft1920.data.Habitacion;

@Controller
public class ControladorData {

	@GetMapping("/home-corp/precio-hab")
	public String precioHome() {
		return "home-corp/precio-hab.jsp";
	}	
	
	@PostMapping("/home-corp/precio-hab")
	public String precioHabForm(Habitacion hab) {		
		System.out.println("Precio Habitacion:"+ hab.precioFinal1());		
		return "home-corp/precio-hab.jsp";
	}
	
}

