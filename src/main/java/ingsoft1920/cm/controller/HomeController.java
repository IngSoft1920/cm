package ingsoft1920.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//Controlador para DEMO del 16/03
	
	@GetMapping("/inicio")
	public String homeCorporativo() {
		return "index.jsp";
	}
	
	@GetMapping("/anadir-hotel")
	public String anadirHotelForm() {
		return "corp-hotel/anadir-hotel.jsp";
	}
	@GetMapping("/hoteles")
	public String HotelForm() {
		return "corp-hotel/hoteles.jsp";
	}
	@GetMapping("/ver-hotel")
	public String verHotelForm() {
		return "corp-hotel/ver-hotel.jsp";
	}
	@GetMapping("/editar-hotel")
	public String editarHotelForm() {
		return "corp-hotel/editar-hotel.jsp";
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

}
