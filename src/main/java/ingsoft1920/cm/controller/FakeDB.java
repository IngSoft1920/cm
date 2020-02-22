package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Factura;

@Component
public class FakeDB {
	
	private ArrayList<Cliente> clientes;
	private ArrayList<Factura> facturas;

	public FakeDB() {
		facturas = new ArrayList<>();
		rellenarFacturas();
		
		clientes = new ArrayList<>();
		rellenarClientes();
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
	
	List<Factura> facturasCliente(int id_cliente){
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

}
