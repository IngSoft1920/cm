package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Precio;
import ingsoft1920.cm.bean.Reserva;

@Controller
@SessionAttributes(names = { "cliente", "seleccionHabitaciones", "fecha_entrada", "fecha_salida" })
public class ControladorCliente {
	
	/*

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
	public String registroForm(Cliente c, Model m) {

		int id = fake.anadirCliente(c.getNombre(), c.getDNI(), c.getEmail(), c.getPassword());
		c.setId(id);
		m.addAttribute("cliente", c);

		return "redirect:/home-client/main";
	}

	@GetMapping("/home-client/login")
	public String iniciarSesion() {
		return "/home-client/login.jsp";
	}

	@PostMapping("/home-client/login")
	public String iniciarSesionForm(String email, String password, Model m) throws Exception {
		// TODO llamar dao

		Cliente c = fake.login(email, password);

		if (c == null) {
			throw new Exception("Estoy hay que mejorarlo: usuario no existe");
		}

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
	public String reservarForm(String continente, String pais, String ciudad, Date fecha_entrada, Date fecha_salida,
			Model m) {

		int numDias = (int) ((fecha_salida.getTime() - fecha_entrada.getTime()) / 86_400_000);

		// TODO dao
		Map<Hotel, List<Precio>> seleccionHabitaciones = fake.habitacionesReserva(continente, pais, ciudad,
				fecha_entrada, fecha_salida);

		Map<Hotel, Map<Tipo, Double>> opciones = new HashMap<>();

		for (Entry<Hotel, List<Precio>> entrada : seleccionHabitaciones.entrySet()) {
			Map<Tipo, Double> preciosNocheHotel = new HashMap<>();

			double precioNormales = entrada.getValue().stream().filter(p -> p.getTipo() == Tipo.normal)
					.mapToDouble(p -> p.getPrecio()).sum() / numDias;
			preciosNocheHotel.put(Tipo.normal, Math.floor(precioNormales * 100) / 100);

			double precioPremiums = entrada.getValue().stream().filter(p -> p.getTipo() == Tipo.premium)
					.mapToDouble(p -> p.getPrecio()).sum() / numDias;
			preciosNocheHotel.put(Tipo.premium, Math.floor(precioPremiums * 100) / 100);

			opciones.put(entrada.getKey(), preciosNocheHotel);
		}

		m.addAttribute("seleccionHabitaciones", opciones);
		m.addAttribute("fecha_entrada", fecha_entrada);
		m.addAttribute("fecha_salida", fecha_salida);
		return "redirect:/home-client/main/reservar-resultados";
	}

	@GetMapping("/home-client/main/reservar-resultados")
	public String resultadosBusqueda() {
		return "/home-client/main/reservar-resultados.jsp";
	}

	@PostMapping("/home-client/main/reservar-resultados")
	public String recibirReserva(@ModelAttribute("cliente") Cliente clienteSesion,
			@ModelAttribute("fecha_entrada") Date fecha_entrada, @ModelAttribute("fecha_salida") Date fecha_salida,
			int hotel_id, double importe, Tipo tipo) {

		// TODO dao
		fake.anadirReserva(fecha_entrada, fecha_salida, importe, hotel_id, tipo, clienteSesion.getId());

		return "redirect:/home-client/main";
	}

	@GetMapping("/home-client/main/cancelar-reserva")
	public String cancelarReserva(@ModelAttribute("cliente") Cliente clienteSesion, Model m) {

		// TODO dao
		Map<Reserva, Hotel> reservasCliente = fake.reservasCliente(clienteSesion.getId());
		m.addAttribute("reservasMap", reservasCliente);

		return "/home-client/main/cancelar-reserva.jsp";
	}

	@PostMapping("/home-client/main/cancelar-reserva")
	public String recibirCancelacionReserva(int reserva_id) {
		System.out.println("recibido " + reserva_id);

		// TODO dao
		fake.cancelarReserva(reserva_id);
		return "redirect:/home-client/main";
	}

	@GetMapping("/home-client/main/visualizar-reservas")
	public String visualizarReservas(@ModelAttribute("cliente") Cliente clienteSesion, Model m) {

		// TODO llamar dao
		Map<Reserva, Hotel> reservasCliente = fake.reservasCliente(clienteSesion.getId());
		m.addAttribute("reservasMap", reservasCliente);

		return "/home-client/main/visualizar-reservas.jsp";
	}

	@GetMapping("/home-client/main/visualizar-facturas")
	public String visualizarFacturas(@ModelAttribute("cliente") Cliente clienteSesion, Model m) {

		// TODO llamar dao
		List<Factura> facturasCliente = fake.facturasCliente(clienteSesion.getId());
		m.addAttribute("facturas", facturasCliente);

		return "/home-client/main/visualizar-facturas.jsp";
	}

	@GetMapping("/home-client/main/feedback")
	public String realizarValoracion(Model m) {
		// TODO llamar dao

		List<Hotel> hoteles = fake.hoteles();
		m.addAttribute("hoteles", hoteles);

		return "/home-client/main/feedback.jsp";
	}

	@GetMapping("/home-client/main/feedback-escribir/{hotel_id}")
	public String escribirValoracion() {
		return "/home-client/main/feedback-escribir.jsp";
	}

	@PostMapping("/home-client/main/feedback-escribir/{hotel_id}")
	public String recibirValoracion(@PathVariable int hotel_id, @ModelAttribute("cliente") Cliente c, String cabecera,
			String cuerpo, double nota) {

		fake.anadirValoracion(cabecera, cuerpo, nota, c.getId(), hotel_id);

		System.out.println(fake.feedback());
		return "redirect:/home-client/main";
	}
	
	*/

}
