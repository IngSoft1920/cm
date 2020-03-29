package ingsoft1920.cm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.ProfesionDAO;
import ingsoft1920.cm.dao.ProveedorDAO;

@Controller
public class HomeController {

	@Autowired
	public HotelDAO hotelDao;
	@Autowired
	public EmpleadoDAO empleadoDao;
	@Autowired
	public ProveedorDAO proveedorDao;

	// Pagina de inicio
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

	// anadir hotel
	@GetMapping("/anadir-hotel")
	public ModelAndView anadirForm() {

		return new ModelAndView("corp-hotel/anadir-hotel.jsp");
	}

	// Anadir-categoria
	@GetMapping("/anadir-hotel/anadir-categoria")
	public ModelAndView anadirCategoriaForm() {

		return new ModelAndView("corp-hotel/anadir-categoria.jsp");
	}

	// Anadir-servicio
	@GetMapping("/anadir-hotel/anadir-servicios")
	public ModelAndView anadirServicioForm() {

		return new ModelAndView("corp-hotel/anadir-servicios.jsp");
	}

	// Hotel/Ver-hotel
	@GetMapping("/ver-hotel/{id}")
	public ModelAndView verHotelForm(@PathVariable(name = "id") int id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.getByID(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/ver-hotel.jsp", "hotel", hotel);
	}

	// Hotel/editar-hotel
	@GetMapping("/editar-hotel/{id}")
	public ModelAndView editarHotelForm(@PathVariable(name = "id") int id) {

		System.out.println("Recuperando datos del hotel: " + id);
		Hotel hotel = hotelDao.getByID(id);
		System.out.println("Recuperando datos del hotel: " + hotel);

		return new ModelAndView("corp-hotel/editar-hotel.jsp", "hotel", hotel);
	}

	// eliminar hotel
	@GetMapping("/hoteles/eliminar-hotel/{id}")
	public ModelAndView eliminarForm(@PathVariable(name = "id") int id) {
		hotelDao.eliminar(id);

		return new ModelAndView("redirect:/hoteles");
	}

	// Pagina de empleados
	@GetMapping("/empleados")
	public ModelAndView empleadosForm() {
		List<Empleado> empleados = new EmpleadoDAO().empleados();
		return new ModelAndView("corp-empleado/empleados.jsp", "empleados", empleados);
	}
	
	// Pagina de añadir empleados
	@GetMapping("/anadir-empleado")
	public ModelAndView anadirEmpleadoForm() {
		List<Profesion> profesiones = new ProfesionDAO().profesiones();
		return new ModelAndView("corp-empleado/anadir-empleado.jsp","profesiones",profesiones);
	}
	
	@PostMapping("/anadir-empleado")
	public String recibirEmpleado(String firstName,
										String lastNames,
										String email,
										String telefono,
										Integer sueldo,
										Integer profesionID) {
		
		Empleado em = new Empleado();
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  
		Properties info = new Properties();
		  info.put("fecha_contratacion",Date.valueOf( LocalDate.now() ));
		  //TODO: cambiar esto
		  info.put("hotel_id", 1);
		   
		empleadoDao.anadir(em, info);		
		return "redirect:/empleados";
	}


	// Anadir profesion
	@GetMapping("/anadir-empleado/anadir-profesion")
	public ModelAndView anadirProfesionForm() {
		return new ModelAndView("corp-empleado/anadir-profesion.jsp");
	}
	
	// Anadir profesion
	@PostMapping("/anadir-empleado/anadir-profesion")
	public String recibirProfesionForm(String profesion) {
		Profesion p = new Profesion();
		  p.setNombre(profesion);
		
		new ProfesionDAO().anadir(p);
		return "redirect:/anadir-empleado";
	}

	// ver empleado
	@GetMapping("/ver-empleado/{id}")
	public ModelAndView verEmpleadoForm(@PathVariable(name = "id") int id) {
		Empleado empleado = new EmpleadoDAO().getByID(id);
		String nombreProfesion = new ProfesionDAO().getByID(empleado.getProfesion_id()).getNombre();
		
		ModelAndView mav = new ModelAndView("corp-empleado/ver-empleado.jsp");
		  mav.addObject("empleado", empleado);
		  mav.addObject("nombreProf",nombreProfesion);

		return mav;
	}

	// editar-empleado GET
	@GetMapping("/editar-empleado/{id}")
	public ModelAndView geditarEmpleadoForm(@PathVariable(name = "id") int id, String firstName) {
		
		ModelAndView mav = new ModelAndView("corp-empleado/editar-empleado.jsp");
		  mav.addObject("empleado", new EmpleadoDAO().getByID(id));
		  mav.addObject("profesiones",new ProfesionDAO().profesiones());
		
		return mav;
	}

	// editar-empleado POST
	@PostMapping("/editar-empleado/{id}")
	public ModelAndView editarEmpleadoForm(@PathVariable(name = "id") int id,
										   String firstName,
										   String lastNames,
										   String email,
										   String telefono, 
										   Double sueldo,
										   Integer profesionID) {
	
		Empleado em = new Empleado();
		  em.setId(id);
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  
		new EmpleadoDAO().editar(em);
		
		ModelAndView mav = new ModelAndView("corp-empleado/ver-empleado.jsp");
		  mav.addObject("empleado", em);
		  mav.addObject("nombreProf",new ProfesionDAO().getByID(profesionID).getNombre());

		return mav;
	}

	// Eliminar empleado
	@GetMapping("/eliminar-empleado/{id}")
	public ModelAndView eliminarEmpleadoForm(@PathVariable(name = "id") int id) {
		empleadoDao.eliminar(id);
		return new ModelAndView("redirect:/empleados");
	}

	// Pagina de Proveedores
	@GetMapping("/proveedores")
	public ModelAndView proveedoresForm() {

		List<Proveedor> proveedores = new ProveedorDAO().proveedores();

		return new ModelAndView("corp-proveedor/proveedores.jsp", "proveedores", proveedores);
		// return new ModelAndView("corp-proveedor/proveedores.jsp");
	}

	// Anadir proveedor
	@GetMapping("/anadir-proveedor")
	public String anadirProveedorForm() {

		return "corp-proveedor/anadir-proveedor.jsp";
	}

	// Anadir producto
	@GetMapping("/anadir-proveedor/anadir-producto")
	public String anadirProductoForm() {

		return "corp-proveedor/anadir-producto.jsp";
	}

	// ver proveedor
	@GetMapping("/proveedores/ver-proveedor/{id}")
	public ModelAndView verProveedorPorId(@PathVariable(name = "id") int id) {

		Proveedor proveedor = new ProveedorDAO().getByID(id);

		return new ModelAndView("corp-proveedor/ver-proveedor.jsp", "proveedor", proveedor);
	}

	// editar-proveedor GET
	@GetMapping("/proveedores/editar-proveedor/{id}")
	public ModelAndView geditarProveedorForm(@PathVariable(name = "id") int id, String empresa) {

		Proveedor proveedor = new ProveedorDAO().getByID(id);

		return new ModelAndView("corp-proveedor/editar-proveedor.jsp", "proveedor", proveedor);
	}

	// editar-proveedor POST
	@PostMapping("/proveedores/editar-proveedor/{id}")
	public ModelAndView editarProveedorForm(@PathVariable(name = "id") int id, String empresa, String CIF) {

		Proveedor proveedor = new Proveedor(id, empresa, CIF);

		proveedorDao.editar(proveedor);

		return new ModelAndView("corp-proveedor/ver-proveedor.jsp", "proveedor", proveedor);
	}

	// Eliminar proveedor
	@GetMapping("/proveedores/eliminar-proveedor/{id}")
	public ModelAndView eliminarProveedorForm(@PathVariable(name = "id") int id) {

		// Proveedor proveedor = new ProveedorDAO().obtenerProveedorPorId(id);
		new ProveedorDAO().eliminar(id);

		return new ModelAndView("redirect:/proveedores");

	}

	// Pagina de facturacion
	@GetMapping("/facturacion")
	public String facturacionForm() {
		return "fna/beneficio.jsp";
	}

}
