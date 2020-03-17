package ingsoft1920.cm.data;

/*
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ingsoft1920.cm.bean.DatosPrecio;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.bean.auxiliares.Precio_Habitacion;
import ingsoft1920.cm.dao.DatosPrecioDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.PeticionDAO;
import ingsoft1920.cm.dao.Precio_HabitacionDAO;


public class PrecioHabitacion {

	private double precio = 100; // Precio base
	private String ciudad;
	//private String direccion;
	private int tipoHabitacion; // normal = 0 suite = 1
	private int ocupacion; // % ocupacion
	//private int numHabs;
	private int regimenComidas;
	private int evento; // con recogida de datos de eventos con uipath mas adelante
	private double puntuacion; //1-10
	private int politica; // % max subidas/bajadas
	private ArrayList<DatosPrecio> dtPrecios; // datos de la competencia
	

	public PrecioHabitacion(double precio, String ciudad, int tipoHabitacion, int ocupacion, int regimenComidas,
			double puntuacion, int politica, ArrayList<DatosPrecio> dtPrecios) {
		this.precio = precio;
		this.ciudad = ciudad;
		this.tipoHabitacion = tipoHabitacion;
		this.ocupacion = ocupacion;
		this.regimenComidas = regimenComidas;
		//this.evento = evento;
		this.puntuacion = puntuacion;
		this.politica = politica;
		this.dtPrecios = dtPrecios;
	}

	public double precioFinal() {
		precioTipoHabitacion();
		precioRegimenComidas();
		precioCiudad();
		precioOcupacion();
		//precioEvento();
		precioPuntuacion();
		return precio;
	}

	public void precioCiudad() {
		//Devuelve una subida o bajada de hasta 50% segun la diferencia entre el precio base y el precio de la competencia
		double precioCompetencia = procesar(this.dtPrecios);
		double variacion = 0.5 * this.politica * (1/100);
		double mod = Math.atan((precioCompetencia - precio) / 50) * (2 / Math.PI) * variacion;
		precio = precio * (1 + mod);
	}

	public void precioOcupacion() { //sube o baja un % maximo que depende de la politica
		if (this.ocupacion == -1) {
			return;
		}
		double variacion = 0.4 * this.politica * (1/100);
		precio = precio * (1 + ((this.ocupacion - 0.5) / 0.5) * variacion);
	}

	public void precioRegimenComidas() {
		if (this.regimenComidas == -1) {
			return;
		}
		precio = precio * this.regimenComidas; //TODO
	}

	public void precioEvento() {
		if (this.evento == -1) return;
		if (this.evento > 10) precio = precio*1.5;
	}

	public void precioTipoHabitacion() {
		if (this.tipoHabitacion == 1) {
			precio *= 1.5;
		}
	}

	public void precioPuntuacion() { //sube o baja un % maximo que depende de la politica
		if (this.puntuacion == -1) {
			return;
		}
		double variacion = 0.1 * this.politica * (1/100);
		precio = precio * (1 + ((this.puntuacion-5) / 5) * variacion);
	}

	@Override
	public String toString() {
		return "Precio Habitación: " + precioFinal();
	}

	public double procesar(ArrayList<DatosPrecio> datosPrecio) {
		ArrayList<Double> precios = new ArrayList<Double>();
		ArrayList<Double> puntuaciones = new ArrayList<Double>();

		for (DatosPrecio data : datosPrecio) {
			precios.add(data.getPrecio());
			puntuaciones.add(data.getPuntuacion());
		}

		//Media ponderada segun puntuaciones
		int media = 0;
		int suma = 0;
		for (int i = 0; i < precios.size(); i++) {
			media += precios.get(i) * puntuaciones.get(i);
			suma += puntuaciones.get(i);
		}
		media = media / suma;

		return media;
	}


	public static void main(String[] args) {
		
		ArrayList<DatosPrecio> datosPrecio;
		
		HotelDAO hotelDAO = new HotelDAO();
		PeticionDAO peticionDAO = new PeticionDAO();
		DatosPrecioDAO datosPrecioDAO = new DatosPrecioDAO();
		Precio_HabitacionDAO precio_HabitacionDAO = new Precio_HabitacionDAO();
		
		
		for (DatosHotel dt : BBDD.getDatosHotel()) {
			
			//Fijamos los precios para un rango de 30 dias
			LocalDate fechas [] = new LocalDate[30];
			for (int i = 0; i < 30; i++) {
				fechas[i] = LocalDate.now().plusDays(i);
			}

			for (LocalDate fecha : fechas) { 
				
				datosPrecio.clear();
				
				int idPeticion = peticionDAO.add(new Peticion(dt.ciudad, 0, fecha, 
						fecha.plusDays(1), fecha.getDayOfMonth(), dt.tipo_habitacion));

				// En este momento el equipo de UIpath lee de Peticion, (cambia el estado de la peticion procesada) 
				// y actualiza la tabla DatosPrecio
				do {
					List<DatosPrecio> dataset = datosPrecioDAO.get(idPeticion);
					for (DatosPrecio data : dataset) {
						datosPrecio.add(data);
					}
				} while (datosPrecio.size() == 0); //Hasta que consiga los datos

				PrecioHabitacion hab = new PrecioHabitacion(dt.ciudad, dt.tipo_habitacion, dt.ocupacion,
						dt.regimenComidas, dt.nota_feedback, dt.politica_hotel, datosPrecio);
				
				precio_HabitacionDAO.setPrecio(new Precio_Habitacion(dt.idHotel, dt.tipo_habitacion, fecha, hab.precioFinal()));
			}
			
		}
	}
}
*/

