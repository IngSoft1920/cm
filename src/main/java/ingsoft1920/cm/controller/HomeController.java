package ingsoft1920.cm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ingsoft1920.cm.bean.Ausencia;
import ingsoft1920.cm.bean.Ausencia.Estado;
import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Producto;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.Tipo_Habitacion;
import ingsoft1920.cm.dao.AusenciaDAO;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.ProductoDAO;
import ingsoft1920.cm.dao.ProfesionDAO;
import ingsoft1920.cm.dao.ProveedorDAO;
import ingsoft1920.cm.dao.ServicioDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;
import ingsoft1920.cm.dao.ValoracionDAO;

// Controlador del FE
@Controller
public class HomeController {

	@Autowired
	public HotelDAO hotelDao;
	@Autowired
	public EmpleadoDAO empleadoDao;
	@Autowired
	public ProveedorDAO proveedorDao;
	@Autowired
	public ServicioDAO servicioDao;
	@Autowired
	public TipoHabitacionDAO habsDao;
	@Autowired
	public CategoriaDAO categoriaDao;
	@Autowired
	public ProfesionDAO profesionDao;
	@Autowired
	public ProductoDAO productoDao;
	@Autowired
	public AusenciaDAO ausenciaDao;
	@Autowired
	public ValoracionDAO valoracionDao;
	
	

	@GetMapping("/inicio")
	public String homeCorporativo() {
		return "index.jsp";
	}

	// -------------------HOTELES-----------------------
	
	// Pagina de Hoteles
	@GetMapping("/hoteles")
	public ModelAndView HotelForm() {
		List<Hotel> hoteles = hotelDao.hoteles();
		return new ModelAndView("corp-hotel/hoteles.jsp", "hoteles", hoteles);
	}

	// anadir hotel
	@GetMapping("/anadir-hotel")
	public ModelAndView anadirForm() {
		ModelAndView mav = new ModelAndView("corp-hotel/anadir-hotel.jsp");
		  mav.addObject("servicios", new ServicioDAO().servicios());
		  mav.addObject("categorias", new CategoriaDAO().categorias());
		  mav.addObject("habs", new TipoHabitacionDAO().tipos());
		
		return mav;
	}
	
	
	@PostMapping("/anadir-hotel")
	public String recibirHotel(String nombre,
							 String continente,
							 String pais,
							 String ciudad,
							 String direccion,
							 Integer estrellas,
							 String descripcion,
							 Integer[] categoriasIDs,
					 		 Integer[] serviciosIDs, Integer[] numInstalaciones, Integer[] precios, String[] unidadesMedida, // están mapeados
							 Integer[] habsIDs, Integer[] numDisponibles) // están mapeados 
	{		
		
		Hotel hotel = new Hotel();
		  hotel.setNombre(nombre);
		  hotel.setContinente(continente);
		  hotel.setPais(pais);
		  hotel.setCiudad(ciudad);
		  hotel.setDireccion(direccion);
		  hotel.setEstrellas(estrellas);
		  hotel.setDescripcion(descripcion);	
		  
		Properties aux;  
		  
		List<Properties> cats = new ArrayList<>();
		// Si es null entonces no se ha elegido ninguna
		if( categoriasIDs != null ) {
			for(Integer id : categoriasIDs) {
				aux = new Properties();
				  aux.put("categoria_id",id);
				  
				cats.add(aux);
			}
		}
		
		List<Properties> servs = new ArrayList<>();
		for(int i=0;i<serviciosIDs.length;i++) {
			if( numInstalaciones[i] != null && numInstalaciones[i] > 0 ) {
				aux = new Properties();
				  aux.put("servicio_id",serviciosIDs[i]);
				  aux.put("num_instalaciones",numInstalaciones[i]);
				  if( precios[i] != null) aux.put("precio",precios[i]);
				  if( !unidadesMedida[i].equals("") ) aux.put("unidad_medida",unidadesMedida[i]);
				  
				servs.add(aux);
			}
		}	
		
		List<Properties> habs = new ArrayList<>();		
		for(int i=0;i<numDisponibles.length;i++) {
			
			// Solo si se ha introducido un valor > 0 lo tomamos en cuenta:
			if( numDisponibles[i] != null && numDisponibles[i] > 0 ) {
				aux = new Properties();
				  aux.put("tipo_hab_id",habsIDs[i]);
				  aux.put("num_disponibles",numDisponibles[i]);
				  
				habs.add(aux);
			}
			
		}
		
		hotelDao.anadir(hotel, habs, servs, cats);
		
		return "redirect:/hoteles";
	}

	// Hotel/Ver-hotel
	@GetMapping("/ver-hotel/{id}")
	public ModelAndView verHotel(@PathVariable(name = "id") int id) {

		Hotel hotel = hotelDao.getByID(id);
		List<Properties> servicios = servicioDao.serviciosHotel(id);
		List<Properties> habs = habsDao.habsHotel(id);
		List<Categoria> cats = categoriaDao.categoriasHotel(id);
		
		ModelAndView mav = new ModelAndView("corp-hotel/ver-hotel.jsp");
		  mav.addObject("hotel", hotel);
		  mav.addObject("servicios",servicios);
		  mav.addObject("habs",habs);
		  mav.addObject("categorias",cats);
		return mav;
	}

	// Hotel/editar-hotel
	@GetMapping("/editar-hotel/{id}")
	public ModelAndView editarHotelForm(@PathVariable(name = "id") int id) {

		Hotel hotel = hotelDao.getByID(id);
		List<Servicio> servicios = servicioDao.servicios();
		List<Tipo_Habitacion> habs = habsDao.tipos();
		List<Categoria> cats = categoriaDao.categorias();
		
		ModelAndView mav = new ModelAndView("corp-hotel/editar-hotel.jsp");
		  mav.addObject("hotel", hotel);
		  mav.addObject("servicios",servicios);
		  mav.addObject("habs",habs);
		  mav.addObject("categorias",cats);

		return mav;
	}
	
	@PostMapping("/editar-hotel/{id}")
	public String recibirHotelEditar(@PathVariable(name = "id") int id,
									 String nombre,
							 		 String continente,
							 		 String pais,
							 		 String ciudad,
							 		 String direccion,
							 		 Integer estrellas,
							 		 String descripcion,
							 		 Integer[] categoriasIDs,
							 		 Integer[] serviciosIDs, Integer[] numInstalaciones, Integer[] precios, String[] unidadesMedida, // están mapeados
							 		 Integer[] habsIDs, Integer[] numDisponibles) // están mapeados 
	{
		// De momento es la manera más fácil de hacerlo:
		hotelDao.eliminar(id);
		recibirHotel(nombre, continente, pais, ciudad, direccion, estrellas, descripcion, categoriasIDs, serviciosIDs, numInstalaciones, precios, unidadesMedida, habsIDs, numDisponibles);
		return "redirect:/hoteles";
	}

	// eliminar hotel
	@GetMapping("/hoteles/eliminar-hotel/{id}")
	public ModelAndView eliminarForm(@PathVariable(name = "id") int id) {
		hotelDao.eliminar(id);

		return new ModelAndView("redirect:/hoteles");
	}
	
	// -------------------EMPLEADOS-----------------------
	//Pagina para selecionar hotel
	@GetMapping("/select-hoteles")
	public ModelAndView selectHotelForm() {
		List<Hotel> hoteles = hotelDao.hoteles();
		return new ModelAndView("corp-hotel/select-hoteles.jsp", "hoteles", hoteles);
	}

	
	@GetMapping("/corp-proveedor/select-hoteles-prov/{proveedor_id}")
	public ModelAndView selectHotelFormProv(@PathVariable(name = "proveedor_id") int proveedorId) {
		List<Hotel> hoteles = hotelDao.hoteles();
		
		ModelAndView modelAndView = new ModelAndView("corp-proveedor/select-hoteles-prov.jsp");
		  modelAndView.addObject("hoteles", hoteles);
		  modelAndView.addObject("proveedor_id", proveedorId);
		return modelAndView;
	}

		
	//Pagina para selecionar hotel
	@GetMapping("/select/empleados/{id}")
	public ModelAndView selectEmpleadosForm(@PathVariable(name = "id") int id) {
		
		List<Empleado> empleados = new EmpleadoDAO().empleadosPorHotel(id);
		
		ModelAndView modelAndView = new ModelAndView("corp-empleado/empleados.jsp");
		
		modelAndView.addObject("empleados", empleados);
		modelAndView.addObject("id", id);
		
		return modelAndView;
	}
	
	@GetMapping("/select/proveedores/{id}")
	public ModelAndView selectProveedoresForm(@PathVariable(name = "id") int id) {
		
		List<Proveedor> proveedores = new ProveedorDAO().proveedoresPorHotel(id);
		
		return new ModelAndView("corp-proveedor/proveedores.jsp", "proveedores", proveedores);
	}
	

	// Pagina de añadir empleados
	@GetMapping("/anadir-empleado/{id}")
	public ModelAndView anadirEmpleadoForm(@PathVariable(name = "id") int id) {
		List<Profesion> profesiones = profesionDao.profesionesHotel(id);
		ModelAndView modelAndView = new ModelAndView("corp-empleado/anadir-empleado.jsp");
		  modelAndView.addObject("profesiones", profesiones);
		  modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	
	
	@PostMapping("/anadir-empleado/{id}")
	public String recibirEmpleado(String firstName,
								  String lastNames,
								  String email,
								  String telefono,
								  Integer sueldo,
								  Integer profesionID, 
								  @PathVariable(name = "id") int id,
								  Integer[] diasLibres) {
				
		Empleado em = new Empleado();
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  em.setDias_libres(diasLibres != null ? Arrays.toString(diasLibres) : "[]");
		  
		Properties info = new Properties();
		  info.put("fecha_contratacion",Date.valueOf( LocalDate.now() ));
		  info.put("hotel_id", id);
		   
		empleadoDao.anadir(em, info);		
		return "redirect:/select/empleados/"+id;
	}

	// ver empleado
	@GetMapping("/ver-empleado/{empleado_id}")
	public ModelAndView verEmpleadoForm(@PathVariable(name = "empleado_id") int empleado_id) {
		Empleado empleado = new EmpleadoDAO().getByID(empleado_id);
		String nombreProfesion = new ProfesionDAO().getByID(empleado.getProfesion_id()).getNombre();
		
		ModelAndView mav = new ModelAndView("corp-empleado/ver-empleado.jsp");
		  mav.addObject("empleado", empleado);
		  mav.addObject("nombreProf",nombreProfesion);
		  mav.addObject("hotel_id",empleadoDao.hotelDondeTrabaja(empleado_id).get("hotel_id"));
		  mav.addObject("diasLibres",empleado.getDiasLibresString());

		return mav;
	}

	// editar-empleado GET
	@GetMapping("/editar-empleado/{id}")
	public ModelAndView geditarEmpleadoForm(@PathVariable(name = "id") int id, String firstName) {
		
		ModelAndView mav = new ModelAndView("corp-empleado/editar-empleado.jsp");
		  mav.addObject("empleado", new EmpleadoDAO().getByID(id));
		  mav.addObject("profesiones",new ProfesionDAO().profesiones());
		  mav.addObject("hotel_id",empleadoDao.hotelDondeTrabaja(id).get("hotel_id"));	
		
		return mav;
	}

	// editar-empleado POST
	@PostMapping("/editar-empleado/{id}")
	public String editarEmpleadoForm(@PathVariable(name = "id") int id,
										   String firstName,
										   String lastNames,
										   String email,
										   String telefono, 
										   Double sueldo,
										   Integer profesionID,
										   Integer[] diasLibres) {
	
		Empleado em = new Empleado();
		  em.setId(id);
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  em.setDias_libres(diasLibres != null ? Arrays.toString(diasLibres) : "[]");
		  
		empleadoDao.editar(em);
		
		return "redirect:/select/empleados/"+empleadoDao.hotelDondeTrabaja(id).get("hotel_id");
	}

	// Eliminar empleado
	@GetMapping("/eliminar-empleado/{id}")
	public String eliminarEmpleadoForm(@PathVariable(name = "id") int id) {
		int hotelId = (int) empleadoDao.hotelDondeTrabaja(id).get("hotel_id");
		empleadoDao.eliminar(id);
		return "redirect:/select/empleados/"+hotelId;
	}
	
	// -------------------PROVEEDOR-----------------------

	// Pagina de Proveedores
	@GetMapping("/proveedores")
	public ModelAndView proveedoresForm() {
		List<Proveedor> proveedores = new ProveedorDAO().proveedores();
		return new ModelAndView("corp-proveedor/proveedores.jsp", "proveedores", proveedores);
	}
	
	
	// Asignar proveedor-hotel
	@GetMapping("/asignar-proveedor-hotel/{proveedor_id}/{hotel_id}")
	public ModelAndView asignarProveedorHotel(@PathVariable(name = "hotel_id")int hotel_id, @PathVariable(name = "proveedor_id") int proveedor_id) {
		
		Proveedor proveedor = proveedorDao.getByID(proveedor_id);
		List<Producto> productos = productoDao.productosProveedor(proveedor_id);
		
		ModelAndView modelAndView = new ModelAndView("corp-proveedor/asignar-proveedor-hotel.jsp");
    	  modelAndView.addObject("productos", productos);
		  modelAndView.addObject("proveedor", proveedor);
		
		return modelAndView;
	}
	
	@PostMapping("/asignar-proveedor-hotel/{proveedor_id}/{hotel_id}")
	public String asignarProveedorPost(@PathVariable(name = "proveedor_id") int proveedor_id,
									   @PathVariable(name = "hotel_id")int hotel_id,
									   Integer[] productosIDs,
									   Integer[] precios,
									   String [] unidadesMedida)
	{
		
		List<Properties> info = new ArrayList<>();
		Properties aux;
		for(int i=0;i<productosIDs.length;i++) {
			if(precios[i] != null && !unidadesMedida[i].equals("")) {
				aux = new Properties();
				  aux.put("producto_id", productosIDs[i]);
				  aux.put("precio",precios[i]);
				  aux.put("unidad_medida",unidadesMedida[i]);
				  
				info.add(aux);
			}
		}
		
		proveedorDao.asignarHotel(hotel_id, proveedor_id, info);
		return "redirect:/proveedores";
	}
		
		

	// Anadir proveedor
	@GetMapping("/anadir-proveedor")
	public ModelAndView anadirProveedorForm() {
		List<Producto> productos = productoDao.productos();
		return new ModelAndView("corp-proveedor/anadir-proveedor.jsp","productos",productos);
	}
	
	@PostMapping("/anadir-proveedor")
	public String recibirProveedorForm(String empresa,
									   String cif,
									   Integer[] productosIDs) {
		
		Proveedor p = new Proveedor();
		  p.setEmpresa(empresa);
		  p.setCIF(cif);
		  
		List<Properties> info = new ArrayList<>();
		
		// Si productosIDs es null es que no se
		// ha seleccionado ninguno
		if( productosIDs != null ) {
			Properties aux;
			for(Integer id:productosIDs) {
				aux = new Properties();
				  aux.put("producto_id",id);
				  
				info.add(aux);
			}
		}
		proveedorDao.anadir(p, info);
		return "redirect:/proveedores";
	}
	
	// ver proveedor
	@GetMapping("/proveedores/ver-proveedor/{id}")
	public ModelAndView verProveedorPorId(@PathVariable(name = "id") int id) {
		Proveedor proveedor = new ProveedorDAO().getByID(id);
		List<Producto> productos = productoDao.productosProveedor(id);
		
		ModelAndView mav = new ModelAndView("corp-proveedor/ver-proveedor.jsp");
		  mav.addObject("proveedor",proveedor);
		  mav.addObject("productos",productos);
		 
		return mav;
	}

	// editar-proveedor GET
	@GetMapping("/proveedores/editar-proveedor/{id}")
	public ModelAndView editarProveedorForm(@PathVariable(name = "id") int id) {
		
		ModelAndView mav = new ModelAndView("corp-proveedor/editar-proveedor.jsp");
		  mav.addObject("proveedor",proveedorDao.getByID(id));
		  mav.addObject("productos",productoDao.productos());
		  mav.addObject("productosProveedor",productoDao.productosProveedor(id));
		 
		return mav;
	}

	// editar-proveedor POST
	@PostMapping("/proveedores/editar-proveedor/{id}")
	public String recibirEditarProveedorForm(@PathVariable(name = "id") int id,
													String empresa,
													String cif,
													Integer[] productosIDs) {

		proveedorDao.eliminar(id);
		recibirProveedorForm(empresa, cif, productosIDs);
		return "redirect:/proveedores";
	}

	// Eliminar proveedor
	@GetMapping("/eliminar-proveedor/{id}")
	public ModelAndView eliminarProveedorForm(@PathVariable(name = "id") int id) {
		proveedorDao.eliminar(id);
		return new ModelAndView("redirect:/proveedores");
	}
	
	// -------------------FACTURACIÓN-------------------------

	// Pagina de facturacion
	@GetMapping("/facturacion")
	public String facturacionForm() {
		return "fna/beneficio.jsp";
	}
	
	// -------------------CONFIGURACIÓN-----------------------
	@GetMapping("/configuracion")
	public String paginaConf() {
		return "conf/config.jsp";
	}
	
	// CATEGORIAS
	@GetMapping("/anadir-categoria")
	public ModelAndView anadirCategoriaForm() {
		return new ModelAndView("conf/anadir-categoria.jsp");
	}
	
	@PostMapping("/anadir-categoria")
	public String recibirCategoriaForm(String nombre) {
		Categoria c = new Categoria();
		  c.setNombre(nombre);
		
		categoriaDao.anadir(c);
		return "redirect:/configuracion";
	}
	
	// SERVICIOS
	@GetMapping("/anadir-servicios")
	public ModelAndView anadirServicioForm() {
		return new ModelAndView("conf/anadir-servicios.jsp");
	}

	@PostMapping("/anadir-servicios")
	public String recibirServicioForm(String nombre) {
		Servicio s = new Servicio();
		  s.setNombre(nombre);
		
		servicioDao.anadir(s);
		return "redirect:/configuracion";
	}
	
	// PROFESIONES
	@GetMapping("/anadir-profesion")
	public ModelAndView anadirProfesionForm() {
		return new ModelAndView("conf/anadir-profesion.jsp","servicios",servicioDao.servicios());
	}
	
	@PostMapping("/anadir-profesion")
	public String recibirProfesionForm(String profesion,
									   Integer[] serviciosIDs) {
		Profesion p = new Profesion();
		  p.setNombre(profesion);
		  
		List<Properties> infoServs = new ArrayList<>();
		
		// Si serviciosIDs es null es que no se ha 
		// seleccionado ninguno en el formulario
		if( serviciosIDs != null ) {
			Properties aux;
			for(Integer id : serviciosIDs) {
				aux = new Properties();
				  aux.put("servicio_id",id);
				  
				infoServs.add(aux);
			}
		}
		
		profesionDao.anadir(p,infoServs);
		return "redirect:/configuracion";
	}
	
	// PRODUCTOS
	@GetMapping("/anadir-producto")
	public String anadirProductoForm() {
		return "conf/anadir-producto.jsp";
	}
	
	@PostMapping("/anadir-producto")
	public String recibirProductoForm(String nombre) {
		Producto p = new Producto();
		  p.setNombre(nombre);
		
		productoDao.anadir(p);
		return "redirect:/configuracion";
	}
	
	
	
	// TIPOS DE HABITACIÓN
	@GetMapping("/anadir-tipos-hab")
	public String anadirTipoHabForm() {
		return "conf/anadir-tipos-hab.jsp";
	}
	
	@PostMapping("/anadir-tipos-hab")
	public String recibirTipoHabForm(String nombre) {
		Tipo_Habitacion th = new Tipo_Habitacion();
		  th.setNombre_tipo(nombre);
		
		habsDao.anadir(th);
		return "redirect:/configuracion";
	}
	

	@GetMapping("/ausencias")
	public ModelAndView todasAusencias() {
		
		List<Ausencia> ausenciasTotal = ausenciaDao.ausencias();
		List<Ausencia> ausenciasPendientes = ausenciasTotal
												.stream()
												.filter( a -> a.getEstado() == Ausencia.Estado.pendiente )
												.collect( Collectors.toList() );
		
		ModelAndView mav = new ModelAndView("corp-ausencias/ausencias.jsp");
		  mav.addObject("ausenciasTotal",ausenciasTotal);
		  mav.addObject("ausenciasPendientes",ausenciasPendientes);
		
		return mav;
	}
	
	
	@GetMapping("/ausencias-aceptar/{id}")
	public String ausenciasAceptar(@PathVariable(name = "id") int id) {
		Ausencia ausencia = ausenciaDao.getById(id);
		ausenciaDao.resultadoAusencia(ausencia,Estado.aprobada);
		
		return "redirect:/ausencias";
	}

	@GetMapping("/ausencias-denegar/{id}")
	public String ausenciasDenegar(@PathVariable(name = "id") int id) {
		Ausencia ausencia = ausenciaDao.getById(id);
		ausenciaDao.resultadoAusencia(ausencia,Estado.denegada);
		
		return "redirect:/ausencias";
	}
	
}
