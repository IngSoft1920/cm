package ingsoft1920.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;

import ingsoft1920.cm.bean.auxiliares.Hotel_Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Tipo_Habitacion;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;

@Controller
public class HomeController {

	@Autowired
	public HotelDAO hotelDao;
	public EmpleadoDAO empleadoDao;

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

		List<Hotel> hoteles = new HotelDAO().hoteles();

		return new ModelAndView("corp-hotel/hoteles.jsp", "hoteles", hoteles);
	}

	@GetMapping("/anadir-hotel")
	public ModelAndView anadirForm() {

		return new ModelAndView("corp-hotel/anadir-hotel.jsp");
	}

	// Anadir-hotel Test
//	@PostMapping("/anadir-hotel")
//	public ModelAndView anadirHotelCompletoForm(Hotel hotel, List<Hotel_Tipo_Habitacion> numeroHabitaciones,
//			List<Hotel_Servicio> servicios, List<Hotel_Categoria> categorias) {
//
//		
//		int hotelCompleto = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		int numeroHabitacionesHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		int serviciosHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		int categoriasHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		
//		
//		Hotel hotelCompleto = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		List<Hotel_Tipo_Habitacion> numeroHabitacionesHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		List<Hotel_Servicio> serviciosHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		List<Hotel_Categoria> categoriasHotel = new HotelDAO().anadir(hotel, numeroHabitaciones, servicios, categorias);
//		
//		System.out.println("Recuperando datos del hotel: " + hotelCompleto);
//		System.out.println("Recuperando datos del tipo habitacion: " + numeroHabitacionesHotel);
//		System.out.println("Recuperando datos del servicios: " + serviciosHotel);
//		System.out.println("Recuperando datos del categorias: " + categoriasHotel);
//
//		return new ModelAndView("corp-hotel/ver-hotel.jsp");
//	}

	// Ver-hotel
	@GetMapping("/ver-hotel/{id}")
	public ModelAndView verHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = new HotelDAO().obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/ver-hotel.jsp", "hotel", hotel);
	}

	// editar-hotel
	@GetMapping("/editar-hotel/{id}")
	public ModelAndView editarHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = new HotelDAO().obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/editar-hotel.jsp", "hotel", hotel);
	}

	@GetMapping("/login-corp")
	public String loginForm() {
		return "login.jsp";
	}

	//@GetMapping("/empleados")
	//public String empleadosForm() {
		//return "corp-empleado/empleados.jsp";
	//}
	
	//ver empleados
	@GetMapping("/empleados")
		public ModelAndView empleadosForm() {

			List<Empleado> empleados = new EmpleadoDAO().getEmpleados();

			return new ModelAndView("corp-empleado/empleados.jsp", "empleados", empleados);
		}
	
	
	//ver empleado 
	// Ver-hotel
	@GetMapping("/ver-empleado/{id}")
	public ModelAndView verEmpleadoForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		System.out.println("Recuperando datos del empleado: " + empleado);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", empleado);
	}
	//editar-hotel
	@GetMapping("/editar-empleado/{id}")
	public ModelAndView geditarEmpleadoForm(@PathVariable(name = "id") long id,String firstName) {

		//System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		//sets
		//System.out.println("Recuperando datos del empleado: " + empleado);
		return new ModelAndView("corp-empleado/editar-empleado.jsp", "empleado", empleado);
	}
	@PostMapping("/editar-empleado/{id}")
	public ModelAndView editarEmpleadoForm(@PathVariable(name = "id") long id,String firstName, String lastNames, 
			String email, String telefono) {

		//System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		System.out.println("antes");
		empleadoDao.cambiarNombre(empleado, firstName);
		System.out.println("despues");
		//System.out.println("Recuperando datos del empleado: " + empleado);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", empleado);
	}
	
	
	@GetMapping("/proveedores")
	public String proveedoresForm() {
		return "corp-proveedor/proveedores.jsp";
	}

}
