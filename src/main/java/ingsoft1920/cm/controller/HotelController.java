package ingsoft1920.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	FakeDB fake;

	@GetMapping
	public String homeHotel() {
		return "/corp-hotel/home.jsp";
	}

	@GetMapping("/anadir")
	public String formAnadirHotel() {
		return "corp-hotel/anadir.jsp";
	}

	@PostMapping("/anadir")
	public String formAnadirHotel(String nombre, String continente, String pais, String ciudad, String direccion) {
		// TODO dao
		fake.anadirHotel(nombre, continente, pais, ciudad, direccion);
		return "redirect:/hotel";
	}

	@GetMapping("/editar/{hotel_id}")
	public String formEditarHotel() {
		return "/corp-hotel/editar.jsp";
	}

	@PostMapping("/editar/{hotel_id}")
	public String recibirInfoParaEditar(@PathVariable("hotel_id") int hotel_id, String nombre) {
		// TODO dao
		fake.editarHotel(hotel_id, nombre);
		return "redirect:/hotel";
	}

	@GetMapping("/facturacion/{hotel_id}")
	public String mostrarFacturacionHotel(@PathVariable("hotel_id") int hotel_id, Model m) {
		// TODO dao
		m.addAttribute("facturas", fake.facturacionHotel(hotel_id));
		return "/corp-hotel/facturacion.jsp";
	}

}
