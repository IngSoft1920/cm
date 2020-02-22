package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Reserva;

@Controller
@SessionAttributes(names = {"cliente"})
public class ControladorCliente {

	final static Logger logger = LogManager.getLogger(ControladorCliente.class.getName());

	@Autowired
	FakeDB fake;

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
	public String registroForm(Cliente c,Model m) {
		
		int id = fake.anadirCliente(c.getNombre(),
						   			c.getDNI(),
						   			c.getEmail(),
						   			c.getPassword());
		c.setId(id);
		m.addAttribute("cliente", c);
		
		return "redirect:/home-client/main";
	}

	@GetMapping("/home-client/login")
	public String iniciarSesion() {
		return "/home-client/login.jsp";
	}

	@PostMapping("/home-client/login")
	public String iniciarSesionForm(String email,String password,Model m) throws Exception {
		//TODO llamar dao
		
		Cliente c = fake.login(email, password);
		
		if(c==null)
			throw new Exception("Estoy hay que mejorarlo: usuario no existe");
		
		m.addAttribute("cliente", c);
		
		return "home-client/main.jsp";
	}

	@GetMapping("/home-client/main")
	public String mainCliente() {
		return "/home-client/main.jsp";
	}

	@GetMapping("/home-client/main/reservar-buscar")
	public String reservarBuscar() {
		return "/home-client/main/reservar-buscar.jsp";
	}

	@PostMapping("/home-client/main/reservar-buscar")
	public String reservarForm(String continente,String pais,String ciudad,
							   Date fecha_entrada,Date fecha_salida) {

		String texto =
				String.format("Recibido: continente:%s,pais:%s,ciudad:%s,fechaIn:%s,fechaFin:%s",
							     continente,
							     pais,
							     ciudad,
							     fecha_entrada,
							     fecha_salida);

		logger.info(texto);

		return "/home-client/main/reservar-resultados.jsp";
	}


	@GetMapping("/home-client/main/cancelar-reserva")
	public String cancelarReserva() {
		return "/home-client/main/cancelar-reserva.jsp";
	}

	@GetMapping("/home-client/main/visualizar-reservas")
	public String visualizarReservas(@ModelAttribute("cliente") Cliente clienteSesion,
									 Model m) {
		
		// TODO llamar dao
		Map<Reserva,Hotel> reservasCliente = fake.reservasCliente(clienteSesion.getId());
		m.addAttribute("reservasMap",reservasCliente);
		
		return "/home-client/main/visualizar-reservas.jsp";
	}

	@GetMapping("/home-client/main/visualizar-facturas")
	public String visualizarFacturas(@ModelAttribute("cliente") Cliente clienteSesion,
									 Model m) {

		// TODO llamar dao
		List<Factura> facturasCliente = fake.facturasCliente( clienteSesion.getId() );
		m.addAttribute("facturas", facturasCliente);
		
		return "/home-client/main/visualizar-facturas.jsp";
	}

	@GetMapping("/home-client/main/feedback")
	public String realizarValoracion(Model m) {
		//TODO llamar dao
		
		List<Hotel> hoteles = fake.hoteles();
		m.addAttribute("hoteles", hoteles);
		
		return "/home-client/main/feedback.jsp";
	}
	
	// Aquí recibimos el hotel que ha seleccionado 
	// el usuario para hacer una valoración
	@PostMapping("/home-client/main/feedback/{hotel_id}")
	public String realizarValoracionForm(@PathVariable int hotel_id) {
		return "redirect:/home-client/main/feedback-escribir/"+hotel_id;
	}
	
	
	@GetMapping("/home-client/main/feedback-escribir/{hotel_id}")
	public String escribirValoracion(@PathVariable int hotel_id,Model m) {
		m.addAttribute("hotelValoradoID", hotel_id);
		return "/home-client/main/feedback-escribir.jsp";
	}
	
	@PostMapping("/home-client/main/feedback-escribir/{hotel_id}")
	public String recibirValoracion(@PathVariable int hotel_id,
									@ModelAttribute("cliente") Cliente c,
									String cabecera,String cuerpo,double nota) {
		
		fake.anadirValoracion(cabecera, cuerpo, nota, c.getId(), hotel_id);
		
		System.out.println( fake.feedback() );
		
		return "redirect:/home-client/main";
	}


}














