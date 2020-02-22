package ingsoft1920.cm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ingsoft1920.cm.bean.Cliente;

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

	@PostMapping("/home-client/registro")
	public String registroForm(Cliente c) {
		logger.info("Recibido: "+c);
		return "/home-client/main.jsp";
	}

	@GetMapping("/home-client/login")
	public String iniciarSesion() {
		return "/home-client/login.jsp";
	}

	@PostMapping("/home-client/login")
	public String iniciarSesionForm(String email,String password) {
		logger.info("Recibido: email-"+email+", passwd-"+password);
		return "home-client/main.jsp";
	}

	@GetMapping("/home-client/main")
	public String mainCliente() {
		return "/home-client/main.jsp";
	}

	@GetMapping("/home-client/main/reservar")
	public String reservar() {
		return "/home-client/main/reservar.jsp";
	}

	@GetMapping("/home-client/main/cancelar-reserva")
	public String cancelarReserva() {
		return "/home-client/main/cancelar-reserva.jsp";
	}

	@GetMapping("/home-client/main/visualizar-reservas")
	public String visualizarReservas() {
		return "/home-client/main/visualizar-reservas.jsp";
	}

	@GetMapping("/home-client/main/feedback")
	public String realizarValoracion() {
		return "/home-client/main/feedback.jsp";
	}




}
