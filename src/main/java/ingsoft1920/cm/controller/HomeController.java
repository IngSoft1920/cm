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
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;

@Controller
public class HomeController {

	@Autowired
	public HotelDAO hotelDao;
	@Autowired
	public EmpleadoDAO empleadoDao;

	// Controlador para DEMO del 16/03
	// Pruebas para conectar vista-controlador

	// index.html
	@GetMapping("/inicio")
	public String homeCorporativo() {
		return "index.jsp";
	}

	// login
	@GetMapping("/login-corp")
	public String loginForm() {
		return "login.jsp";
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

	// Hotel/Ver-hotel
	@GetMapping("/ver-hotel/{id}")
	public ModelAndView verHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/ver-hotel.jsp", "hotel", hotel);
	}

	// Hotel/editar-hotel
	@GetMapping("/editar-hotel/{id}")
	public ModelAndView editarHotelForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.obtenerHotelPorId(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/editar-hotel.jsp", "hotel", hotel);
	}

	// eliminar hotel
	@GetMapping("/hoteles/eliminar-hotel/{id}")
	public ModelAndView eliminarForm(@PathVariable(name = "id") long id) {
		hotelDao.eliminarHotelPorId(id);

		return new ModelAndView("redirect:/hoteles");
	}

	// Pagina de empleados
	@GetMapping("/empleados")
	public ModelAndView empleadosForm() {

		List<Empleado> empleados = new EmpleadoDAO().getEmpleados();

		return new ModelAndView("corp-empleado/empleados.jsp", "empleados", empleados);
	}

	// ver empleado
	@GetMapping("/ver-empleado/{id}")
	public ModelAndView verEmpleadoForm(@PathVariable(name = "id") long id) {

		System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		System.out.println("Recuperando datos del empleado: " + empleado);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", empleado);
	}

	// editar-empleado GET
	@GetMapping("/editar-empleado/{id}")
	public ModelAndView geditarEmpleadoForm(@PathVariable(name = "id") long id, String firstName) {

		// System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		// sets
		// System.out.println("Recuperando datos del empleado: " + empleado);
		return new ModelAndView("corp-empleado/editar-empleado.jsp", "empleado", empleado);
	}

	// editar-empleado POST
	@PostMapping("/editar-empleado/{id}")
	public ModelAndView editarEmpleadoForm(@PathVariable(name = "id") int id, String firstName, String lastNames,
			String email, String telefono, Double sueldo) {

		// System.out.println("Recuperando datos del empleado: " + id);
		System.out.println(sueldo);
		Empleado empleado = new Empleado(id, firstName, lastNames, email, telefono, sueldo, 1);
		System.out.println("antes");
		empleadoDao.editar(empleado);
		System.out.println("despues");
		// empleado.setNombre(firstName);
		// Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		// System.out.println("Recuperando datos del empleado: " + empleado);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", empleado);
	}
	
	// Eliminar empleado
	@GetMapping("/eliminar-empleado/{id}")
	public ModelAndView eliminarEmpleadoForm(@PathVariable(name = "id") long id) {

		Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
		empleadoDao.eliminarEmpleado(empleado);

		return new ModelAndView("redirect:/empleados");

	}

	// Pagina de Proveedores
	@GetMapping("/proveedores")
	public String proveedoresForm() {
		return "corp-proveedor/proveedores.jsp";
	}

}
