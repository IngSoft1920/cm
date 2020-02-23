package ingsoft1920.cm.controller.homecorp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ingsoft1920.cm.controller.FakeDB;

@Controller
@RequestMapping("/home-corp")
public class CorporativoController {
	@Autowired
	FakeDB fake;
	
	@GetMapping
	public String homeCorporativo() {
		return "/corp/home-corp.jsp";
	}
	
	@GetMapping("/select-hotel/{accion}")
	public String seleccionarHotel(Model m) {
		
		// accion está en { "empleado" , "proveedor" }. Nos permite reutilizar el 
		// formulario para elegir el hotel para ambas acciones. Esto es
		// útil en el PostMapping
					
		//TODO dao
		m.addAttribute("hoteles",fake.hoteles());
		
		return "/corp/select-hotel.jsp";
	}
	
	@PostMapping("/select-hotel/{accion}")
	public String recibirHotelSeleccionado(@PathVariable("accion") String accion,
										   int hotel_id) {
		String res = "redirect:";
		
		switch(accion) {
		
		case "empleado":
			res+="/empleado";
			break;
			
		case "proveedor":
			res+="/proveedor";
			break;
		
		}
		
		return res+"/"+hotel_id;
	}
	


	
	
}
