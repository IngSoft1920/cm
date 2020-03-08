package ingsoft1920.cm.controller;

import java.util.HashMap;	
import java.util.Map;

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
	
	/*

	@Autowired
	FakeDB fake;
	
	private HotelDAO hotelDao = new HotelDAO();

	@GetMapping
	public String homeHotel(Model m) {
		m.addAttribute("hoteles", hotelDao.hoteles());
		return "/corp-hotel/home.jsp";
	}

	@GetMapping("/anadir")
	public String formAnadirHotel() {
		return "corp-hotel/anadir.jsp";
	}

	@PostMapping("/anadir")
	public String formAnadirHotel(String nombre,String continente,String pais,
								  String ciudad, String direccion,int numNormales,
								  int numPremium)
	{
		
		Map<Habitaciones.Tipo,Integer> habs = new HashMap<>();
		  habs.put(Habitaciones.Tipo.normal,numNormales);
		  habs.put(Habitaciones.Tipo.premium,numPremium);
		
		hotelDao.anadirHotel(nombre, continente, pais, ciudad, direccion, habs);
		return "redirect:/hotel";
	}

	@GetMapping("/editar/{hotel_id}")
	public String formEditarHotel() {
		return "/corp-hotel/editar.jsp";
	}

	@PostMapping("/editar/{hotel_id}")
	public String recibirInfoParaEditar(@PathVariable("hotel_id") int hotel_id,
										String nombre)
	{
		
		// TODO: cambiar. Lo que está ahora es provisional, que
		// no dio tiempo a pedirles un getHotelByID. Además, habría
		// que decirles que editar no cambia la ubicación del hotel, si acaso
		// cambia el nombre y la denominación de las habitaciones
		Hotel h = hotelDao
					   .hoteles()
					   .stream()
					   .filter( hotel -> hotel.getId() == hotel_id )
					   .findAny()
					   .get();	
		Map<Habitaciones.Tipo,Integer> habs = new HashMap<>();
		  habs.put(Habitaciones.Tipo.normal,10);
		  habs.put(Habitaciones.Tipo.premium,10);
		  
		hotelDao.editarHotel(hotel_id,
							 nombre,
							 h.getContinente(),
							 h.getPais(),
							 h.getCiudad(),
							 h.getDireccion(),
							 habs);
		
		return "redirect:/hotel";
	}

	@GetMapping("/facturacion/{hotel_id}")
	public String mostrarFacturacionHotel(@PathVariable("hotel_id") int hotel_id,
										  Model m) {
		m.addAttribute("facturas", hotelDao.facturacionHotel(hotel_id));
		return "/corp-hotel/facturacion.jsp";
	}
	
	*/

}
