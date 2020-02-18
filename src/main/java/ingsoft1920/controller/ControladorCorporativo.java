package ingsoft1920.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ingsoft1920.bean.Hotel;
import ingsoft1920.bean.Empleado;
import ingsoft1920.dao.HotelDao;
import ingsoft1920.bean.Proveedor;;
//import ingsoft1920.dao.EmpleadoDao;
//import ingsoft1920.dao.ProveedorDao;

@Controller
public class ControladorCorporativo {

	@Autowired
	private HotelDao hotelDao;

	@GetMapping("/home-corp")
	public String homeCorporativo() {
		return "home-corp.jsp";
	}

	@GetMapping("/home-corp/anadir-hotel")
	public String anadirHotelForm() {
		return "home-corp/anadir-hotel.jsp";
	}

	// Spring automáticamente encapsula los datos mandados
	// por el formulario en el objeto Hotel
	@PostMapping("/home-corp/anadir-hotel")
	public String anadirHotelTratamientoSolicitud(Hotel h) {
		hotelDao.anadirHotel(h);

		System.out.println("Añadido "+h+" a la base de datos correctamente.");

		return "home-corp/anadir-hotel.jsp";
	}
	
	@GetMapping("/home-corp/anadir-empleado")
	public String anadirEmpleadoForm() {
		return "home-corp/anadir-empleado.jsp";
	}
	
	@PostMapping("/home-corp/anadir-empleado")
	public String anadirEmpleadoTratamientoSolicitud(Empleado e) {
		//empleadoDao.anadirEmpleado(e);

		System.out.println("Añadido "+e+" a la base de datos correctamente.");

		return "home-corp/anadir-empleado.jsp";
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

}
