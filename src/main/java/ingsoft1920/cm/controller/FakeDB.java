package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.bean.Feedback;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Precio;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.bean.Reserva;

@Component
public class FakeDB {

	private ArrayList<Cliente> clientes;
	private ArrayList<Factura> facturas;
	private ArrayList<Reserva> reservas;
	private ArrayList<Hotel> hoteles;
	private ArrayList<Feedback> valoraciones;
	private ArrayList<Precio> precios;
	private ArrayList<Empleado> empleados;
	private ArrayList<Proveedor> proveedores;

	public FakeDB() {
		facturas = new ArrayList<>();
		rellenarFacturas();

		clientes = new ArrayList<>();
		rellenarClientes();

		reservas = new ArrayList<>();
		rellenarReservas();

		hoteles = new ArrayList<>();
		rellenarHoteles();

		valoraciones = new ArrayList<>();

		precios = new ArrayList<>();
		rellenarPrecios();

		empleados = new ArrayList<>();
		rellenarEmpleados();

		proveedores = new ArrayList<>();
		rellenarProveedores();
	}

	private void rellenarProveedores() {
		Proveedor p1 = new Proveedor();
		p1.setId(1);
		p1.setEmpresa("Vegetales SA");
		p1.setProducto("tomates");
		proveedores.add(p1);

		Proveedor p2 = new Proveedor();
		p2.setId(2);
		p2.setEmpresa("Manteles SA");
		p2.setProducto("sábanas");
		proveedores.add(p2);
	}

	private void rellenarEmpleados() {
		Empleado e1 = new Empleado();
		e1.setId(1);
		e1.setNombre("Juan");
		e1.setApellidos("Cuesta");
		e1.setTelefono("123456");
		e1.setOcupacion("camarero");
		e1.setEmail("j@gmail.com");
		empleados.add(e1);

		Empleado e2 = new Empleado();
		e2.setId(2);
		e2.setNombre("Pedro");
		e2.setApellidos("Kurtz");
		e2.setTelefono("300300300");
		e2.setOcupacion("chef");
		e2.setEmail("p@gmail.com");
		empleados.add(e2);
	}

	private void rellenarPrecios() {
		Random r = new Random();
		Precio p;
		for (Hotel h : hoteles) {
			p = new Precio();
			p.setHotel_id(h.getId());
			p.setPrecio(r.nextInt(100) + 50);
			p.setFecha(Date.valueOf("2020-10-" + (r.nextInt(25) + 1)));
			p.setTipo(Tipo.normal);
			precios.add(p);

			p = new Precio();
			p.setHotel_id(h.getId());
			p.setPrecio(r.nextInt(200) + 150);
			p.setFecha(Date.valueOf("2020-10-" + (r.nextInt(25) + 1)));
			p.setTipo(Tipo.premium);
			precios.add(p);
		}
	}

	private void rellenarHoteles() {
		Hotel h1 = new Hotel();
		h1.setId(1);
		h1.setNombre("Hotel Sol");
		h1.setContinente("Europa");
		h1.setPais("España");
		h1.setCiudad("Madrid");
		h1.setDireccion("Calle Gran vía,12");
		hoteles.add(h1);

		Hotel h2 = new Hotel();
		h2.setId(2);
		h2.setNombre("Hotel Azúcar");
		h2.setContinente("América");
		h2.setPais("Cuba");
		h2.setCiudad("La Habana");
		h2.setDireccion("Calle Rueda,100");
		hoteles.add(h2);
	}

	private void rellenarReservas() {
		Reserva r1 = new Reserva();
		r1.setId(1);
		r1.setFecha_entrada(Date.valueOf("2020-12-20"));
		r1.setFecha_salida(Date.valueOf("2020-12-25"));
		r1.setImporte(150.0);
		r1.setHotel_id(1);
		r1.setTipo(Tipo.normal);
		r1.setCliente_id(1);
		reservas.add(r1);

		Reserva r2 = new Reserva();
		r2.setId(2);
		r2.setFecha_entrada(Date.valueOf("2020-10-1"));
		r2.setFecha_salida(Date.valueOf("2020-10-3"));
		r2.setImporte(30.0);
		r2.setHotel_id(2);
		r2.setTipo(Tipo.premium);
		r2.setCliente_id(1);
		reservas.add(r2);
	}

	private void rellenarClientes() {
		Cliente c1 = new Cliente();
		c1.setId(1);
		c1.setNombre("Chano");
		c1.setDNI("1234");
		c1.setEmail("chano@gmail.com");
		c1.setPassword("vipera");
		clientes.add(c1);
	}

	private void rellenarFacturas() {
		Factura f1 = new Factura();
		f1.setId(1);
		f1.setImporte(100);
		f1.setPagado(false);
		f1.setDescripcion("restaurante");
		f1.setFecha(Date.valueOf("2020-12-31"));
		f1.setCliente_id(2);
		facturas.add(f1);

		Factura f2 = new Factura();
		f2.setId(2);
		f2.setImporte(50);
		f2.setPagado(true);
		f2.setDescripcion("bar");
		f2.setFecha(Date.valueOf("2020-02-10"));
		f2.setCliente_id(2);
		facturas.add(f2);

		Factura f3 = new Factura();
		f3.setId(3);
		f3.setImporte(25);
		f3.setPagado(true);
		f3.setDescripcion("masaje");
		f3.setFecha(Date.valueOf("2020-11-10"));
		f3.setCliente_id(3);
		facturas.add(f3);
	}

	public List<Factura> facturasCliente(int id_cliente) {
		System.out.println(facturas);
		return facturas.stream().filter(f -> f.getCliente_id() == id_cliente).collect(Collectors.toList());
	}

	public int anadirCliente(String nombre, String dni, String email, String password) {
		int id = clientes.get(clientes.size() - 1).getId() + 1;

		Cliente nuevo = new Cliente();
		nuevo.setId(id);
		nuevo.setNombre(nombre);
		nuevo.setDNI(dni);
		nuevo.setEmail(email);
		nuevo.setPassword(password);
		clientes.add(nuevo);

		return id;
	}

	public Cliente login(String email, String password) {
		Optional<Cliente> o = clientes.stream()
				.filter(c -> c.getEmail().equals(email) && c.getPassword().equals(password)).findFirst();

		return (o.isPresent() ? o.get() : null);
	}

	// Decirles a cm1 que hay que cambiar este método
	public Map<Reserva, Hotel> reservasCliente(int cliente_id) {
		Map<Reserva, Hotel> res = new HashMap<>();
		List<Reserva> reservasCliente = reservas.stream().filter(r -> r.getCliente_id() == cliente_id)
				.collect(Collectors.toList());
		for (Reserva r : reservasCliente) {

			Hotel hCorrespondiente = hoteles.stream().filter(h -> h.getId() == r.getHotel_id()).findFirst().get();
			res.put(r, hCorrespondiente);
		}

		return res;
	}

	public List<Hotel> hoteles() {
		return hoteles;
	}

	public void anadirValoracion(String cabecera, String cuerpo, double nota, int cliente_id, int hotel_id) {
		int id = -1;
		if (valoraciones.isEmpty()) {
			id = 1;
		} else {
			id = valoraciones.get(valoraciones.size() - 1).getId() + 1;
		}

		Feedback f = new Feedback();
		f.setId(id);
		f.setCabecera(cabecera);
		f.setCuerpo(cuerpo);
		f.setNota(nota);
		f.setCliente_id(cliente_id);
		f.setHotel_id(hotel_id);
		valoraciones.add(f);
	}

	public List<Feedback> feedback() {
		return valoraciones;
	}

	public void cancelarReserva(int reserva_id) {
		for (int i = 0; i < reservas.size(); i++) {
			if (reservas.get(i).getId() == reserva_id) {
				reservas.remove(i);
				break;
			}
		}
	}

	public Map<Hotel, List<Precio>> habitacionesReserva(String continente, String pais, String ciudad,
			Date fecha_entrada, Date fecha_salida) {
		Map<Hotel, List<Precio>> res = new HashMap<>();

		for (Hotel h : hoteles) {
			List<Precio> ph = precios.stream().filter(p -> p.getHotel_id() == h.getId()).collect(Collectors.toList());
			res.put(h, ph);
		}

		return res;
	}

	public List<Precio> precios() {
		return precios;
	}

	public void anadirReserva(Date fecha_entrada, Date fecha_salida, double importe, int hotel_id, Tipo tipo,
			int cliente_id) {
		int id = -1;
		if (reservas.size() == 0) {
			id = 1;
		} else {
			id = reservas.get(reservas.size() - 1).getId() + 1;
		}

		Reserva r = new Reserva();
		r.setId(id);
		r.setFecha_entrada(fecha_entrada);
		r.setFecha_salida(fecha_salida);
		r.setImporte(importe);
		r.setHotel_id(hotel_id);
		r.setTipo(tipo);
		r.setCliente_id(cliente_id);
		reservas.add(r);
	}

	public List<Empleado> empleadosHotel(int hotel_id) {
		return empleados;
	}

	public List<Proveedor> proveedoresHotel(int hotel_id) {
		return proveedores;
	}

	public Empleado anadirEmpleado(int hotel_id, String nombre, String apellidos, String email, String telefono,
			String ocupacion) {
		int id = -1;
		if (empleados.isEmpty()) {
			id = 1;
		} else {
			id = empleados.get(empleados.size() - 1).getId() + 1;
		}

		Empleado e = new Empleado();
		e.setId(id);
		e.setNombre(nombre);
		e.setApellidos(apellidos);
		e.setEmail(email);
		e.setTelefono(telefono);
		e.setOcupacion(ocupacion);
		empleados.add(e);
		return e;
	}

	public void anadirProveedor(int hotel_id, String empresa, String producto) {

		int id = -1;
		if (proveedores.isEmpty()) {
			id = 1;
		} else {
			id = proveedores.get(proveedores.size() - 1).getId() + 1;
		}

		Proveedor p = new Proveedor();
		p.setId(id);
		p.setEmpresa(empresa);
		p.setProducto(producto);
		proveedores.add(p);
	}

	public List<Empleado> empleados() {
		return empleados;
	}

	public List<Proveedor> proveedores() {
		return proveedores;
	}

	public Empleado eliminarEmpleado(int empleado_id) {
		Empleado res = null;
		for (int i = 0; i < empleados.size(); i++) {
			if (empleados.get(i).getId() == empleado_id) {
				res = empleados.get(i);
				empleados.remove(i);
				break;
			}
		}
		return res;
	}

	public void eliminarProveedor(int proveedor_id) {
		for (int i = 0; i < proveedores.size(); i++) {
			if (proveedores.get(i).getId() == proveedor_id) {
				proveedores.remove(i);
				break;
			}
		}
	}

	public void anadirHotel(String nombre, String continente, String pais, String ciudad, String direccion) {
		int id = -1;
		if (hoteles.isEmpty()) {
			id = 1;
		} else {
			id = hoteles.get(hoteles.size() - 1).getId() + 1;
		}

		Hotel h = new Hotel();
		h.setId(id);
		h.setNombre(nombre);
		h.setContinente(continente);
		h.setPais(pais);
		h.setCiudad(ciudad);
		h.setDireccion(direccion);
		hoteles.add(h);

	}

	public void eliminarHotel(int hotel_id) {
		for (int i = 0; i < hoteles.size(); i++) {
			if (hoteles.get(i).getId() == hotel_id) {
				hoteles.remove(i);
				break;
			}
		}
	}

	public void editarHotel(int hotel_id, String nombre) {
		for (int i = 0; i < hoteles.size(); i++) {
			if (hoteles.get(i).getId() == hotel_id) {
				hoteles.get(i).setNombre(nombre);
				break;
			}
		}
	}

	public List<Factura> facturacionHotel(int hotel_id) {
		return facturas;
	}

}
