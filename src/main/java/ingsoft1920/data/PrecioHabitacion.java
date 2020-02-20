package ingsoft1920.data;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import ingsoft1920.data.RmProcesadorDatos;



public class Habitacion {
	
	private double precio = 100;
	private Calendar calendar;
	private String localizacion; //precio medio en funcion de continente y estacion
	//private String pais; para precisar la localizacion mas adelante
	//private String direccion;
	private int tipo; // normal = 0 suite = 1
	private int ocupacion; //hallar porcentaje ocupacion hotel
	private int numHabs;
	private int regimenComidas;
	private int evento; //con recogida de datos de eventos con uipath mas adelante
	private double puntuacion; //si recogemos feedback del hotel mas adelante
	
	
	public Habitacion(Calendar calendar, String localizacion, int tipo, int ocupacion, int numHabs, int regimenComidas,
			int evento, double puntuacion) {
		this.calendar = calendar;
		this.localizacion = localizacion;
		this.tipo = tipo;
		this.ocupacion = ocupacion;
		this.numHabs = numHabs;
		this.regimenComidas = regimenComidas;
		this.evento = evento;
		this.puntuacion = puntuacion;
	}

	public Habitacion(Calendar calendar, String localizacion, int tipo) {
		this.calendar = calendar;
		this.localizacion = localizacion;
		this.tipo = tipo;
		this.ocupacion = -1;
		this.numHabs = -1;
		this.regimenComidas = -1;
		this.evento = -1;
		this.puntuacion = -1;
	}

	
	public double precioFinal() {
		precioLocalizacion();
		precioTipo();	
		precioOcupacion();
		precioRegimenComidas();
		precioEvento();
		precioPuntuacion();  
		return precio;
	}


	public void precioLocalizacion() { 
		//Devuelve una subida o bajada de hasta 50% segun la diferencia entre el precio base y el precio de la competencia
		double precioCompetencia = RmProcesadorDatos.procesar(this.calendar, this.localizacion);
		double mod = Math.atan((precioCompetencia - precio) / 50) / Math.PI; 
		precio = precio * (1+mod);
	}

	public void precioOcupacion() {
		if (this.ocupacion == -1) return;
		double porcentajeOcupacion = this.ocupacion / this.numHabs; //aproximado porque hay habitaciones ind dobles
		double ocu  = 0.5 - porcentajeOcupacion;
		precio = precio*(1-ocu);
	}
	
	public void precioRegimenComidas() {
		if (evento == -1) return;
		//TODO precio = precio * this.regimenComidas
	}
	
	public void precioEvento() {
		if (this.evento == -1) return;
		if (this.evento > 10) precio = precio*1.5;
	}
	
	public void precioTipo() {
		if (this.tipo == 1) precio *= 1.3;
	}

	public void precioPuntuacion() { //si las notas del hotel son buenas?
		if (this.puntuacion == -1) return;
		double nota = 5 - this.puntuacion;
		precio = precio*(1-nota/10);
	}

	public String toString() {
		return "Precio Habitación: " + precioFinal();
	}
	
	public static void main(String[]args) {
		Calendar calendar = Calendar.getInstance();
		
		Hotel listaHoteles = BaseDatos.getUbicacionHoteles(); //Devuelve id - ubicacion de los hoteles
		for (Hotel hot : listaHoteles) {
			for (String tipoHab : BaseDatos.getTipoHabitaciones(hot.id))
				Habitacion habitacion = new Habitacion(calendar, hot.ubicacion, tipoHab);
				BaseDatos.setPrecio(hot.id, tipo, habitacion.precioFinal(), /*calendar.getFecha()*/)
		}
	
		//Ejemplo de prueba:
		//Habitacion habitacion = new Habitacion(calendar, "AMNN", 0);
		//System.out.println(habitacion.precioFinal());
	}
	
}
