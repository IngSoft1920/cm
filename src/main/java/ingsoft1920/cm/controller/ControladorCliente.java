package ingsoft1920.cm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorCliente {

	final static Logger logger = LogManager.getLogger(ControladorCliente.class.getName());


	@GetMapping("/home-client")
	public String homeCliente(Model m) {
		return "home-client.jsp";
	}

	@GetMapping("/home-client/realizar-reserva")
	public String anadirHotelForm() {
		return "home-client/realizar-reserva.jsp";
	}

	@GetMapping("/home-client/registro")
	public String registro() {
		return "/home-client/registro.jsp";
	}

	@GetMapping("/home-client/login")
	public String iniciarSesion() {
		return "/home-client/login.jsp";
	}

}
