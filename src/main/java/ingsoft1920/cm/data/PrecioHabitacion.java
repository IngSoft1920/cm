package ingsoft1920.cm.data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import ingsoft1920.cm.bean.DatosPrecio;
import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.bean.auxiliares.Precio_Habitacion;
import ingsoft1920.cm.dao.DatosPrecioDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.PeticionDAO;
import ingsoft1920.cm.dao.Precio_HabitacionDAO;


public class PrecioHabitacion {

	private double precio; // Precio base
	//private String direccion;
	private int tipoHabitacion; // normal = 0 suite = 1
	private double ocupacion; // % ocupacion
	private int regimenComidas;
	private int evento; // con recogida de datos de eventos con uipath mas adelante
	private double puntuacion; //1-10
	private double politica; // % max subidas/bajadas
	private ArrayList<DatosPrecio> dtPrecios; // datos de la competencia


	public PrecioHabitacion(int tipoHabitacion, double ocupacion, int regimenComidas,
			double puntuacion, double politica, ArrayList<DatosPrecio> dtPrecios) {
		this.precio = 100; //Cuando este disponible, hay que cambiarlo por el coste
		this.tipoHabitacion = tipoHabitacion;
		this.ocupacion = ocupacion;
		this.regimenComidas = regimenComidas;
		//this.evento = evento;
		this.puntuacion = puntuacion;
		this.politica = politica;
		if (politica == -1) this.politica = 50;
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
		//Devuelve una subida o bajada de hasta politica% segun la diferencia entre el precio base y el precio de la competencia
		double precioCompetencia = procesar(this.dtPrecios);
		double variacion = 0.5 * this.politica * 0.01;
		double mod = Math.atan((precioCompetencia - precio) / 50) * (2 / Math.PI) * variacion;
		precio = precio * (1 + mod);
	}

	public void precioOcupacion() { //sube o baja un % maximo que depende de la politica
		if (this.ocupacion == -1) {
			return;
		}
		double variacion = 0.4 * this.politica * 0.01;
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
		if (this.evento > 10) precio = precio * 1.5;
	}

	public void precioTipoHabitacion() {
		if (this.tipoHabitacion == 1) {
			precio *= 1;
		} else if (this.tipoHabitacion == 2) {
			precio *= 1.25;
		} else if (this.tipoHabitacion == 3) {
			precio *= 1.5;
		}
	}

	public void precioPuntuacion() { //sube o baja un % maximo que depende de la politica
		if (this.puntuacion == -1) {
			return;
		}
		double variacion = 0.1 * this.politica * 0.01;
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
			double precio;
			double puntuacion;
			try {
				precio = Double.parseDouble(data.getPrecio().replaceAll("[^\\d.]", "")); //Elimina el simbolo euros
				puntuacion = Double.parseDouble(data.getPuntuacion().replaceAll(",",".")); //Cambia , por .
			} catch (Exception e){
				//System.out.println("precio: " + data.getPrecio() + " punt: " + data.getPuntuacion());
				continue;
			}
			precios.add(precio);
			puntuaciones.add(puntuacion); 
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

		ArrayList<DatosPrecio> datosPrecio = new ArrayList<DatosPrecio>();

		HotelDAO hotelDAO = new HotelDAO();
		PeticionDAO peticionDAO = new PeticionDAO();
		DatosPrecioDAO datosPrecioDAO = new DatosPrecioDAO();
		Precio_HabitacionDAO precio_HabitacionDAO = new Precio_HabitacionDAO();

		//Fijamos los precios para un rango de 30 dias
		LocalDate fechas [] = new LocalDate[30];
		for (int i = 0; i < 30; i++) {
			fechas[i] = LocalDate.now().plusDays(i);
		}
		
		int idPeticion;
		//int n = peticioninical;
		
		List<Properties> listaProperties = hotelDAO.getDataRM();
		for (Properties dt : listaProperties) {
			
			for (LocalDate fecha : fechas) { 

				idPeticion = peticionDAO.add(new Peticion(0, (String) dt.get("ciudad"), false, Date.valueOf(fecha), 
						Date.valueOf(fecha.plusDays(1)),  1));
				//int idPeticion = n;
				
				// En este momento el equipo de UIpath lee de Peticion, (cambia el estado de la peticion procesada) 
				// y actualiza la tabla DatosPrecio

				do {
					List<DatosPrecio> dataset = datosPrecioDAO.get(idPeticion);
					for (DatosPrecio data : dataset) {
						datosPrecio.add(data);
					}
				} while (datosPrecio.size() == 0); //Hasta que consiga los datos

				for (Properties p_hab : (List<Properties>) dt.get("habitaciones")) { //Para cada tipo de habitacion

					PrecioHabitacion hab = new PrecioHabitacion((int) p_hab.get("id"), 
							((Map<Date,Double>) dt.get("ocupacion")).get(Date.valueOf(fecha)),
							-1, (double) dt.get("nota"), -1, datosPrecio);

					precio_HabitacionDAO.setPrecio(new Precio_Habitacion( (int) dt.get("id"), 
							(int) p_hab.get("id"), Date.valueOf(fecha), hab.precioFinal()));
				}
				
				datosPrecio.clear();
				//n++;
			}	
		}
	}
}


