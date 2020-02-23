package ingsoft1920.cm.controller.homecorp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ingsoft1920.cm.controller.FakeDB;

@Controller
@RequestMapping("/proveedor")
@SessionAttributes(names = {"hotel_id"})
public class ProveedorController {
	
	@Autowired
	FakeDB fake;
	
	
	@GetMapping("/{hotel_id}")
	public String homeProveedor(@PathVariable("hotel_id") int hotel_id, Model m) {
		
		//TODO dao
		m.addAttribute("proveedores", fake.proveedoresHotel(hotel_id) );
		m.addAttribute("hotel_id", hotel_id);
		
		return "/corp-proveedor/home.jsp";
	}
	
	@GetMapping("/anadir")
	public String formularioAnadirProveedor() {
		return "/corp-proveedor/anadir.jsp";
	}
	
	@PostMapping("/anadir")
	public String recibirProveedorFormulario(@ModelAttribute("hotel_id") int hotel_id,
											 String empresa,String producto)
	{
		//TODO dao
		fake.anadirProveedor(hotel_id, empresa,producto);
		return "redirect:/proveedor/"+hotel_id;
	}
	

}
