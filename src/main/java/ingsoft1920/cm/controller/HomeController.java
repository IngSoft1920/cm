package ingsoft1920.cm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.ProfesionDAO;
import ingsoft1920.cm.bean.Proveedor;
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
	public ModelAndView recibirEmpleado(String firstName,
										String lastName,
										String email,
										String telefono,
										Integer[] profesion) {
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(telefono);
		System.out.println( Arrays.toString(profesion) );
		
		List<Profesion> profesiones = new ProfesionDAO().profesiones();
		return new ModelAndView("corp-empleado/anadir-empleado.jsp","profesiones",profesiones);
	}

//	// Anadir empleado
//	@GetMapping("/anadir-empleado")
//	public ModelAndView anadirEmpleadosForm() {
//
//		return new ModelAndView("corp-empleado/anadir-empleado.jsp");
//	}

	// Anadir profesion
	@GetMapping("/anadir-empleado/anadir-profesion")
	public ModelAndView anadirProfesionForm() {

		return new ModelAndView("corp-empleado/anadir-profesion.jsp");
	}

	// ver empleado
	@GetMapping("/ver-empleado/{id}")
	public ModelAndView verEmpleadoForm(@PathVariable(name = "id") int id) {

		System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().getByID(id);
		System.out.println("Recuperando datos del empleado: " + empleado);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", empleado);
	}

	// editar-empleado GET
	@GetMapping("/editar-empleado/{id}")
	public ModelAndView geditarEmpleadoForm(@PathVariable(name = "id") int id, String firstName) {

		// System.out.println("Recuperando datos del empleado: " + id);
		Empleado empleado = new EmpleadoDAO().getByID(id);
		// sets
		// System.out.println("Recuperando datos del empleado: " + empleado);
		return new ModelAndView("corp-empleado/editar-empleado.jsp", "empleado", empleado);
	}

	// editar-empleado POST
	@PostMapping("/editar-empleado/{id}")
	public ModelAndView editarEmpleadoForm(@PathVariable(name = "id") int id, String firstName, String lastNames,
			String email, String telefono, Double sueldo) {

		// System.out.println("Recuperando datos del empleado: " + id);
//		System.out.println(sueldo);
//		Empleado empleado = new Empleado(id, firstName, lastNames, email, telefono, sueldo, 1);
//		System.out.println("antes");
//		empleadoDao.editar(empleado);
//		System.out.println("despues");
//		// empleado.setNombre(firstName);
//		// Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(id);
//		// System.out.println("Recuperando datos del empleado: " + empleado);
		
		Empleado em = new Empleado();
		  em.setId((int) id);
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(1);
		  
		new EmpleadoDAO().editar(em);

		return new ModelAndView("corp-empleado/ver-empleado.jsp", "empleado", em);
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
	@GetMapping("/beneficio")
	public String facturacionForm() {
		return "fna/beneficio.jsp";
	}

}
