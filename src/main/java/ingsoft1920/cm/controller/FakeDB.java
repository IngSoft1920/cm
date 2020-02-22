package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.bean.Feedback;
import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.bean.Hotel;

@Component
public class FakeDB {
	
	private ArrayList<Cliente> clientes;
	private ArrayList<Factura> facturas;
	private ArrayList<Reserva> reservas;
	private ArrayList<Hotel> hoteles;
	private ArrayList<Feedback> valoraciones;

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
	
	public List<Factura> facturasCliente(int id_cliente){
		System.out.println(facturas);
		return facturas
				 .stream()
				 .filter( f -> f.getCliente_id()==id_cliente )
				 .collect(Collectors.toList());
	}
	
	public int anadirCliente(String nombre,String dni,String email,String password) {
		int id = clientes.get( clientes.size()-1 ).getId() +1;
		
		Cliente nuevo = new Cliente();
		  nuevo.setId( id );
		  nuevo.setNombre(nombre);
		  nuevo.setDNI(dni);
		  nuevo.setEmail(email);
		  nuevo.setPassword(password);
		clientes.add(nuevo);
		
		return id;
	}
	
	public Cliente login(String email,String password) {
		Optional<Cliente> o =  clientes
									.stream()
									.filter( c -> c.getEmail().equals(email) && c.getPassword().equals(password) )
									.findFirst();
		
		return ( o.isEmpty() ? null : o.get() );
	}	
	
	// Decirles a cm1 que hay que cambiar este método
	public Map<Reserva,Hotel> reservasCliente(int cliente_id){
		Map<Reserva,Hotel> res = new HashMap<>();
		for(int i=0;i<Math.min(hoteles.size(), reservas.size());i++)
			res.put(reservas.get(i), hoteles.get(i));
		return res;
	}
	
	public List<Hotel> hoteles(){
		return hoteles;
	}
	
	public void anadirValoracion(String cabecera,String cuerpo,double nota,
								 int cliente_id,int hotel_id)
	{
		int id = -1;
		if( valoraciones.isEmpty() )
			id = 1;
		else
			id = valoraciones.get( valoraciones.size()-1 ).getId()+1;
		
		
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

}
