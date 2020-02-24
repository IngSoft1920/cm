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

@Controller
@RequestMapping("/empleado")
@SessionAttributes(names = { "hotel_id" })
public class EmpleadoController {

	@Autowired
	FakeDB fake;

	@GetMapping("/{hotel_id}")
	public String homeEmpleado(@PathVariable("hotel_id") int hotel_id, Model m) {

		// TODO dao
		m.addAttribute("empleados", fake.empleadosHotel(hotel_id));
		m.addAttribute("hotel_id", hotel_id);

		return "/corp-empleado/home.jsp";
	}

	@GetMapping("/anadir")
	public String formularioAnadirEmpleado() {
		return "/corp-empleado/anadir.jsp";
	}

	@PostMapping("/anadir")
	public String recibirEmpleadoFormulario(@ModelAttribute("hotel_id") int hotel_id, String nombre, String apellidos,
			String email, String telefono, String ocupacion, double nomina) {
		// TODO dao
		Empleado anadido = fake.anadirEmpleado(hotel_id, nombre, apellidos, email, telefono, ocupacion);
		APIout.enviarEmpleado(anadido);
		APIout.asignarNomina(anadido.getId(), nomina);

		return "redirect:/empleado/" + hotel_id;
	}

	@GetMapping("/eliminar")
	public String elegirEmpleadoAEliminar(Model m) {
		// TODO dao
		m.addAttribute("empleados", fake.empleados());
		return "/corp-empleado/eliminar.jsp";
	}

	@PostMapping("/eliminar")
	public String recibirEmpleadoAEliminar(@ModelAttribute("hotel_id") int hotel_id, int empleado_id) {
		// TODO dao
		Empleado eliminado = fake.eliminarEmpleado(empleado_id);
		APIout.eliminarEmpleado(eliminado.getEmail());

		return "redirect:/empleado/" + hotel_id;
	}

}
