package ingsoft1920.cm.data;

import java.util.ArrayList;


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
	private ArrayList<DatosPrecios> dtPrecios; // datos de la competencia

	public PrecioHabitacion(double precio, String ciudad, int tipoHabitacion, int ocupacion, int regimenComidas, /*int evento, */
			double puntuacion, int politica, ArrayList<DatosPrecios> dtPrecios) {
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
		precioCiudad();
		precioTipoHabitacion();	
		precioOcupacion();
		precioRegimenComidas();
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
		if (this.ocupacion == -1) return;
		double variacion = 0.4 * this.politica * (1/100);
		precio = precio * (1 + ((this.ocupacion - 0.5) / 0.5) * variacion);
	}
	
	public void precioRegimenComidas() {
		if (this.regimenComidas == -1) return;
		precio = precio * this.regimenComidas; //TODO
	}
	
	/*public void precioEvento() {
		if (this.evento == -1) return;
		if (this.evento > 10) precio = precio*1.5;
	}*/
	
	public void precioTipoHabitacion() {
		if (this.tipoHabitacion == 1) precio *= 1.5;
	}

	public void precioPuntuacion() { //sube o baja un % maximo que depende de la politica
		if (this.puntuacion == -1) return;
		double variacion = 0.1 * this.politica * (1/100);
		precio = precio * (1 + ((this.puntuacion-5) / 5) * variacion); 
	}

	public String toString() {
		return "Precio Habitación: " + precioFinal();
	}
	
	public double procesar(ArrayList<DatosPrecios> dtPrecios) { 
		ArrayList<Double> precios = new ArrayList<Double>();
		ArrayList<Double> puntuaciones = new ArrayList<Double>();

		for (DatoPrecios dt : dtPrecios) {
			precios.add(dt.getPrecio());
			puntuaciones.add(dt.getPuntuacion);	
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
	
	
	public static void main(String[]args) {
		
		for (DatosHotel dt : BBDD.getDatosHotel()) {
			// for (Fecha fecha : Fechas) { ...
			idPeticion = Peticion.add(dt.ciudad, fecha, dt.tipo_habitacion, 0);
			
			//En este momento el equipo de uipath estara actualizando la tabla DatosPrecios
			do { 
				datosPrecios = DatosPrecios.get(idPeticion);
			} while (datosPrecios == null); //Hasta que consiga la linea
				
			if (datosPrecios.estado != 1) {
				Habitacion hab = new Habitacion(dt.ciudad, dt.tipo_habitacion, dt.ocupacion, 
						dt.regimenComidas, /*evento, */ dt.nota_feedback, dt.politica_hotel, datosPrecios);
				DatosPrecios.cambiarEstado();
			}	
			
			TablaPrecios.setPrecio(dt.idHotel, dt.tipo_habitacion, fecha, hab.precioFinal());
			// }
		}
	}
	
}
