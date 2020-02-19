package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCliente {



	@GetMapping("/home-client")
	public String homeCliente() {
		return "home-client.jsp";
	}

	@GetMapping("/home-client/realizar-reserva")
	public String anadirHotelForm(Model m) {
		return "home-client/realizar-reserva.jsp";
	}

}
