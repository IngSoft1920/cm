package ingsoft1920.cm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Proveedor;;
//import ingsoft1920.dao.EmpleadoDao;
//import ingsoft1920.dao.ProveedorDao;

@Controller
public class ControladorCorporativo {

	final static Logger logger = LogManager.getLogger(ControladorCorporativo.class.getName());

//	@GetMapping("/home-corp")
//	public String homeCorporativo() {
//		return "home-corp.jsp";
//	}
	
	/*

	@GetMapping("/home-corp/anadir-hotel")
	public String anadirHotelForm() {
		return "home-corp/anadir-hotel.jsp";
	}

	// Spring automáticamente encapsula los datos mandados
	// por el formulario en el objeto Hotel
	@PostMapping("/home-corp/anadir-hotel")
	public String anadirHotelTratamientoSolicitud(Hotel h) {
		System.out.println("Añadido "+h+" a la base de datos correctamente.");
		return "home-corp/anadir-hotel.jsp";
	}

	@GetMapping("/home-corp/home-empleado-hotel")
	public String hotelEmpleadoForm() {
		return "home-corp/home-empleado-hotel.jsp";
	}
	@PostMapping("/home-corp/home-empleado-hotel")
	public String hotelEmpleadoTratamientoSolicitud(Hotel h) {
		System.out.println("Datos guardados correctamente.");
		return "home-corp/home-empleado-hotel/anadir-empleado.jsp";
	}

	@GetMapping("/home-corp/home-empleado-hotel/anadir-empleado")
	public String anadirEmpleadoForm() {
		return "home-corp/home-empleado-hotel.jsp";
	}

	@PostMapping("/home-corp/home-empleado-hotel/anadir-empleado")
	public String anadirEmpleadoTratamientoSolicitud(Empleado e) {
		System.out.println("Añadido "+e+" a la base de datos correctamente.");
		return "home-corp/home-empleado-hotel/anadir-empleado.jsp";
	}

	@GetMapping("/home-corp/anadir-proveedor")
	public String anadirProveedorForm() {
		return "home-corp/anadir-proveedor.jsp";
	}
	@PostMapping("/home-corp/anadir-proveedor")
	public String anadirProveedorTratamientoSolicitud(Proveedor p) {
		System.out.println("Añadido "+p+" a la base de datos correctamente.");
		return "home-corp/anadir-proveedor.jsp";
	}
*/
}
