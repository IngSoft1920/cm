package ingsoft1920.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.auxiliares.Hotel_Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Tipo_Habitacion;
import ingsoft1920.cm.dao.HotelDAO;

@Controller
public class HomeController {

	@Autowired
	public HotelDAO hotelDao;

	// Controlador para DEMO del 16/03
	// Pruebas para conectar vista-controlador

	// index.html
	@GetMapping("/inicio")
	public String homeCorporativo() {
		return "index.jsp";
	}

	// Pagina de Hoteles
	@GetMapping("/hoteles")
	public ModelAndView HotelForm() {

		List<Hotel> hoteles = hotelDao.hoteles();

		return new ModelAndView("corp-hotel/hoteles.jsp", "hoteles", hoteles);
	}

	@GetMapping("/anadir-hotel")
	public ModelAndView anadirForm() {

		return new ModelAndView("corp-hotel/anadir-hotel.jsp");
	}

	// Ver-hotel
	@GetMapping("/ver-hotel/{id}")
	public ModelAndView verHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/ver-hotel.jsp", "hotel", hotel);
	}

	// editar-hotel
	@GetMapping("/editar-hotel/{id}")
	public ModelAndView editarHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/editar-hotel.jsp", "hotel", hotel);
	}

	@GetMapping("/login-corp")
	public String loginForm() {
		return "login.jsp";
	}

	@GetMapping("/empleados")
	public String empleadosForm() {
		return "corp-empleado/empleados.jsp";
	}

	@GetMapping("/proveedores")
	public String proveedoresForm() {
		return "corp-proveedor/proveedores.jsp";
	}
	
	@GetMapping("/hoteles/eliminar-hotel/{id}")
	public ModelAndView eliminarForm(@PathVariable( name ="id") long id) {
		
		System.out.println("Eliminando el hotel: " + id);
		hotelDao.eliminarHotelPorId(id);
		
		return new ModelAndView("redirect:/hoteles");
	}

	
	

}
