package ingsoft1920.cm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

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
import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.bean.Producto;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.Tipo_Habitacion;
import ingsoft1920.cm.dao.AusenciaDAO;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.EmpleadoDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.PedidoDAO;
import ingsoft1920.cm.dao.ProductoDAO;
import ingsoft1920.cm.dao.ProfesionDAO;
import ingsoft1920.cm.dao.ProveedorDAO;
import ingsoft1920.cm.dao.ServicioDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;
import ingsoft1920.cm.dao.ValoracionDAO;
import ingsoft1920.cm.fna.FacturaDAO;

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
	@Autowired
	public PedidoDAO pedidoDao;
	

	@GetMapping("/inicio")
	public ModelAndView homeCorporativo() {
		Integer balance = (int)FacturaDAO.balanceTotal();
		return new ModelAndView("index.jsp", "balance", balance);
	}
	
	//Index para dividir la pagina entre corporativo y proveedores
	@GetMapping("/inicio2")
	public String home() {
		return "index2.jsp";
	}
	
	@GetMapping("/login")
	public String loginCorporativo() {
		return "login.jsp";
	}
	
	
	
	//New Proveedores Vistas
	
	@GetMapping("/login-proveedores")
	public String loginProveedoresForm() {
		return "login-proveedores.jsp";
	}
	
	@PostMapping("login-proveedores")
	public String recibirCredenciales(String usuario,String password) {
		Proveedor p = proveedorDao.login(usuario,password);
		return p != null ? "redirect:/new-proveedores/"+p.getId() : "redirect:/login-proveedores";
	}
	
	@GetMapping("/new-proveedores/{proveedor_id}")
	public ModelAndView paginaProveedores(@PathVariable int proveedor_id) {
		Proveedor p = proveedorDao.getByID(proveedor_id);
		List<Properties> productos = proveedorDao.productos(proveedor_id);
		
		ModelAndView mav = new ModelAndView("proveedores/new-proveedores.jsp");
		  mav.addObject("proveedor",p);
		  mav.addObject("productos",productos);
		
		return mav;
	}
	
	
	@GetMapping("/new-proveedores/escoger-producto/{proveedor_id}")
	public ModelAndView vistaEscogerProductos(@PathVariable(name="proveedor_id") int proveedor_id) {
		ModelAndView mav = new ModelAndView("proveedores/escoger-producto.jsp");
		  mav.addObject("proveedor_id",proveedor_id);
		  mav.addObject("productos",productoDao.productosQueNoVendeProveedor(proveedor_id));
		return mav;
	}
	
	@GetMapping("/new-proveedores/anadir-producto/{proveedor_id}/{producto_id}")
	public ModelAndView anadirProductoForm(@PathVariable(name = "proveedor_id") int proveedor_id,
								  @PathVariable(name = "producto_id") int producto_id) 
	{
		ModelAndView mav = new ModelAndView("proveedores/anadir-producto-from-proveedor.jsp");
		  mav.addObject("producto",productoDao.getByID(producto_id));
		return mav;
	}
	
	@PostMapping("/new-proveedores/anadir-producto/{proveedor_id}/{producto_id}")
	public String recibirProductoProveedorForm(@PathVariable(name = "proveedor_id") int proveedor_id,
											   @PathVariable(name = "producto_id") int producto_id,
											   Integer precioVenta)
	{
		proveedorDao.asignar_producto_proveedor(proveedor_id, producto_id, precioVenta);
		return "redirect:/new-proveedores/"+proveedor_id;
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
	
	

	@GetMapping("/tarifas-ocupaciones/{hotel_id}")
	public ModelAndView tarifasOcupacionesHotel(@PathVariable(name = "hotel_id") int hotel_id,
												@PathParam("fecha") String fecha) {	
		
		List<Properties> estadisticas = 
				hotelDao.estadisticasHotelDia(hotel_id, Date.valueOf(fecha)); 
				
		ModelAndView mav = new ModelAndView("corp-hotel/tarifas-ocupaciones.jsp");
		  mav.addObject("fecha",Date.valueOf( fecha ));
		  mav.addObject("hotel_id",hotel_id);
		  mav.addObject("estadisticas",estadisticas);
		 
		return mav;
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
								  Integer[] diasLibres,
								  String superior)
	{

		// Comprobamos si existe el superior y si no recargamos la página,
		// invalidando el registro
		if( superior != null && !superior.equals("") && !empleadoDao.existeSuperior(id,superior) )
			return "redirect:/anadir-empleado/"+id;
		
		Empleado em = new Empleado();
		  em.setNombre(firstName);
		  em.setApellidos(lastNames); 	
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  em.setDias_libres(diasLibres != null ? Arrays.toString(diasLibres) : "[]");
		  em.setSuperior(superior);
		  
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
										   Integer[] diasLibres,
										   String superior) {
		
		if( superior != null && !superior.equals("") && !empleadoDao.existeSuperior(id,superior) )
			return "redirect:/editar-empleado/"+id;
		
		Empleado em = new Empleado();
		  em.setId(id);
		  em.setNombre(firstName);
		  em.setApellidos(lastNames);
		  em.setEmail(email);
		  em.setTelefono(telefono);
		  em.setSueldo(sueldo);
		  em.setProfesion_id(profesionID);
		  em.setDias_libres(diasLibres != null ? Arrays.toString(diasLibres) : "[]");
		  em.setSuperior(superior);
		  
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
		List<Properties> productos = proveedorDao.productos(proveedor_id);
		
		ModelAndView modelAndView = new ModelAndView("corp-proveedor/asignar-proveedor-hotel.jsp");
    	  modelAndView.addObject("productos", productos);
		  modelAndView.addObject("proveedor", proveedor);
		
		return modelAndView;
	}
	
	@PostMapping("/asignar-proveedor-hotel/{proveedor_id}/{hotel_id}")
	public String asignarProveedorPost(@PathVariable(name = "proveedor_id") int proveedor_id,
									   @PathVariable(name = "hotel_id")int hotel_id,
									   Integer[] productosIDs)
	{		
		if( productosIDs != null )
			proveedorDao.asignarHotel(hotel_id, proveedor_id, productosIDs);
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
									   String usuario,
									   String contraseña) {
		
		Proveedor p = new Proveedor();
		  p.setEmpresa(empresa);
		  p.setCIF(cif);
		  p.setNombre(usuario);
		  p.setPassword(contraseña);
		proveedorDao.anadir(p);
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

	//Pedidos
	@GetMapping("/proveedores/pedidos/{proveedor_id}")
	public ModelAndView pedidosProveedor(@PathVariable(name = "proveedor_id") int proveedor_id) {
		
		List<Properties> pedidos = pedidoDao.pedidosProveedor(proveedor_id);
		
		ModelAndView mav = new ModelAndView("corp-proveedor/pedidos.jsp");
		  mav.addObject("pedidos",pedidos);
		
		return mav;
	}

		
	//Select hoteles para asignar un proveedor-producto
	
	@GetMapping("/corp-proveedor/select-hoteles-prov/{proveedor_id}")
	public ModelAndView selectHotelFormProv(@PathVariable(name = "proveedor_id") int proveedorId) {
		List<Hotel> hoteles = proveedorDao.hotelesNoAsignados(proveedorId);
				
		ModelAndView modelAndView = new ModelAndView("corp-proveedor/select-hoteles-prov.jsp");
		  modelAndView.addObject("hoteles", hoteles);
		  modelAndView.addObject("proveedor_id", proveedorId);
		return modelAndView;
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
		
		List<Properties> ausenciasTotal = ausenciaDao.ausenciasConEmpleado();
		List<Properties> ausenciasPendientes = 
				ausenciasTotal
					.stream()
					.filter( a -> ((Ausencia) a.get("ausencia")).getEstado() == Ausencia.Estado.pendiente )
					.collect( Collectors.toList() );
		

		
		ModelAndView mav = new ModelAndView("corp-ausencias/ausencias.jsp");
		  mav.addObject("ausenciasTotalProp",ausenciasTotal);
		  mav.addObject("ausenciasPendientesProp",ausenciasPendientes);
		
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
	
	@GetMapping("/editar-producto/{id}")
	public ModelAndView editarProductoForm(@PathVariable(name = "id") int id) {
		
		ModelAndView mav = new ModelAndView("corp-proveedor/editar-producto.jsp");
		Producto producto = productoDao.getByID(id);
		mav.addObject("producto",producto);
		 
		return mav;
	}

	// editar-producto POST
	@PostMapping("/editar-producto/{id}")
	public String recibirEditarProductoForm(@PathVariable(name = "id") int id,
													String nombre,
													Integer precioMax,
													String unidadDeMedida) {

		Producto producto = productoDao.getByID(id);
		producto.setNombre(nombre);
		producto.setPrecio_maximo(precioMax);
		producto.setUnidad_medida(unidadDeMedida);
		
		productoDao.editarProducto(producto);
		return "redirect:/productos";
	}

	@GetMapping("/productos")
	public ModelAndView productos() {
		List<Producto> productos = productoDao.productos();
		
		ModelAndView mav = new ModelAndView("corp-proveedor/productos.jsp");
		  mav.addObject("productos",productos);
		 
		return mav;
	}
	
	@GetMapping("/proveedores/productos/{id}")
	public ModelAndView proveedoresProductos(@PathVariable(name = "id") int id) {
		List<Producto> productos = productoDao.productosProveedor(id);
		
		ModelAndView mav = new ModelAndView("corp-proveedor/productos-proveedor.jsp");
		  mav.addObject("productos",productos);
		 
		return mav;
	}
	
	@GetMapping("/editar-precio-venta/{producto_id}/{proveedor_id}")
	public ModelAndView editarPrecioForm(@PathVariable(name = "proveedor_id") int proveedor_id, @PathVariable(name = "producto_id") int producto_id) {
		ModelAndView mav = new ModelAndView("corp-proveedor/editar-precio-venta.jsp");
		  mav.addObject("producto",productoDao.getByID(producto_id));
		  
		  int precioActual = (int) proveedorDao
								  	.productos(proveedor_id)
								  	.stream()
								  	.filter( prod -> (int) prod.get("id") == producto_id )
								  	.findAny()
								  	.get()
								  	.get("precio_venta");
		  
		 mav.addObject("precioActual",precioActual);
		  
		return mav;
	}
	
	@PostMapping("/editar-precio-venta/{producto_id}/{proveedor_id}")
	public String recibirEditarProductoForm(@PathVariable(name = "proveedor_id") int proveedor_id,
											@PathVariable(name = "producto_id") int producto_id,
											int precioVenta) {
		proveedorDao.actualizarPrecioVenta(producto_id, proveedor_id, precioVenta);
		return "redirect:/new-proveedores/{proveedor_id}";
	}
	
	@GetMapping("/eliminar-proveedor-producto/{producto_id}/{proveedor_id}")
	public String EliminarProveedorProducto(@PathVariable(name = "proveedor_id") int proveedor_id, @PathVariable(name = "producto_id") int producto_id) {
		
		productoDao.eliminarProductoProveedor(proveedor_id, producto_id);
		return "redirect:/new-proveedores/{proveedor_id}";
	}
	
	// PRODUCTOS
	@GetMapping("/anadir-producto")
	public String anadirProductoForm() {
		return "conf/anadir-producto.jsp";
	}
	
	@PostMapping("/anadir-producto")
	public String recibirProductoForm(String nombre,Integer precioMax,String unidadMedida) {
		Producto p = new Producto();
		  p.setNombre(nombre);
		  p.setPrecio_maximo(precioMax);
		  p.setUnidad_medida(unidadMedida);
		  if(productoDao.anadir(p)<0) {
	            return "redirect:/anadir-producto";}
	        else {
	            return "redirect:/productos";}
		
	}

	@GetMapping("/eliminar-producto/{id}")
	public ModelAndView eliminarProducto(@PathVariable(name = "id") int id) {
		productoDao.eliminarProducto(id);
		return new ModelAndView("redirect:/productos");
	}
}
