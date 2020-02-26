package ingsoft1920.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.dao.EmpleadoDAO;

@Controller
@RequestMapping("/empleado")
@SessionAttributes(names = { "hotel_id" })
public class EmpleadoController {
	
	private EmpleadoDAO empleadoDao = new EmpleadoDAO();
	
	@GetMapping("/{hotel_id}")
	public String homeEmpleado(@PathVariable("hotel_id") int hotel_id, Model m) {
		
		m.addAttribute("hotel_id",hotel_id);
		m.addAttribute("empleados", empleadoDao.empleadosDeUnHotel(hotel_id) );
		
		return "/corp-empleado/home.jsp";
	}

	@GetMapping("/anadir")
	public String formularioAnadirEmpleado() {
		return "/corp-empleado/anadir.jsp";
	}

	@PostMapping("/anadir")
	public String recibirEmpleadoFormulario(@ModelAttribute("hotel_id") int hotel_id,
											String nombre,String apellidos,String email,
											String telefono, String ocupacion,double nomina) 
	{
		int id = empleadoDao.anadirEmpleado(nombre, apellidos, email, telefono, ocupacion, hotel_id);
		Empleado anadido = new Empleado(id, nombre, apellidos, email, telefono, ocupacion);
		
		APIout.enviarEmpleado(anadido);
		APIout.asignarNomina(anadido.getId(), nomina);

		return "redirect:/empleado/" + hotel_id;
	}

	@GetMapping("/eliminar")
	public String elegirEmpleadoAEliminar(@ModelAttribute("hotel_id") int hotel_id,
										  Model m) {
		m.addAttribute("empleados", empleadoDao.empleadosDeUnHotel(hotel_id));
		return "/corp-empleado/eliminar.jsp";
	}

	@PostMapping("/eliminar")
	public String recibirEmpleadoAEliminar(@ModelAttribute("hotel_id") int hotel_id,
										   int empleado_id) {
		//TODO provisional, pedir que el método devuelva el empleado:
		Empleado eliminado = empleadoDao
									.empleados()
									.stream()
									.filter(e->e.getId()==empleado_id)
									.findAny()
									.get();
		
		
		empleadoDao.borrarEmpleado(empleado_id);
		APIout.eliminarEmpleado(eliminado.getEmail());

		return "redirect:/empleado/" + hotel_id;
	}

}
