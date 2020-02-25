package ingsoft1920.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ingsoft1920.cm.dao.ProveedoresDAO;

@Controller
@RequestMapping("/proveedor")
@SessionAttributes(names = { "hotel_id" })
public class ProveedorController {

	private ProveedoresDAO proveedorDao = new ProveedoresDAO();


	@GetMapping("/{hotel_id}")
	public String homeProveedor(@PathVariable("hotel_id") int hotel_id, Model m) {

		m.addAttribute("proveedores", proveedorDao.proveedoresDeUnHotel(hotel_id) );
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
		proveedorDao.anadirProveedor(empresa,producto,hotel_id);
		return "redirect:/proveedor/"+hotel_id;
	}

	@GetMapping("/eliminar")
	public String elegirProveedorAEliminar(@ModelAttribute("hotel_id") int hotel_id,
										   Model m) {
		m.addAttribute("proveedores", proveedorDao.proveedoresDeUnHotel(hotel_id));
		return "/corp-proveedor/eliminar.jsp";
	}

	@PostMapping("/eliminar")
	public String recibirEmpleadoAEliminar(@ModelAttribute("hotel_id") int hotel_id,
										   int proveedor_id) {
		proveedorDao.borrarProveedor(proveedor_id);
		return "redirect:/proveedor/"+hotel_id;
	}

}
