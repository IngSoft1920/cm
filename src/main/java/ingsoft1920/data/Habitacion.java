package ingsoft1920.data;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import ingsoft1920.data.RmProcesadorDatos;

@Component
public class Habitacion {
	
	private double precio = 100;
	private String localizacion;	//precio medio en funcion de zona y continente
	//private String pais;
	//private String zona; // N = 0 S = 1
	private int tipo; // normal = 0 suite = 1
	//private int tipo1; // individual = 1 doble = 2
	private String estacion;
	private int ocupacion; //hallar porcentaje ocupacion hotel
	private int numHabs;
	private boolean evento;
	private double puntuacion;
	
	
	/*public Habitacion(String localizacion, int tipo, String estacion, int ocupacion, int numHabs, boolean evento,
			double puntuacion) {
		this.localizacion = localizacion;
		this.tipo = tipo;
		this.estacion = estacion;
		this.ocupacion = ocupacion;
		this.numHabs = numHabs;
		this.evento = evento;
		this.puntuacion = puntuacion;
	}*/


	public Habitacion(String localizacion) {
		// TODO Auto-generated constructor stub
		localizacion = null;
	}

	public double precioFinal1() {
		precioLocalizacion(this.localizacion);
		return precio;
	}
	
	public double precioFinal() {
		precioLocalizacion(this.localizacion);
		precioOcupacion(this.ocupacion, this.numHabs);
		precioTipo(this.tipo);				// Se supone que estas
		precioEstacion(this.estacion);		// tres estan incluidas
		precioPuntuacion(this.puntuacion);  // en precioLocalizacion...
		precioEvento(this.evento);
		return precio;
	}


	public void precioLocalizacion(String localizacion) { 
		//Devuelve una subida o bajada de hasta 50% segun la diferencia entre el precio base y el precio de la competencia
		double precioCompetencia = RmProcesadorDatos.procesar(/*localizacion*/);
		double mod = Math.atan((precioCompetencia - precio) / 50) / Math.PI; 
		precio = precio * (1+mod);
	}

	public void precioOcupacion(int ocupacion, int numeroHabs) {
		double porcentajeOcupacion = ocupacion/numeroHabs;//aproximado porque hay habitaciones ind dobles
		double ocu  = 0.5 - porcentajeOcupacion;
		precio = precio*(1-ocu);
	}
	
	public void precioEvento(boolean evento) {
		if (evento) precio=precio*1.5;
	}
	
	public void precioEstacion(String estacion) {	
		
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		
		if (month > 5 && month <= 8)
			precio=precio*2;
		else if (month > 2 && month <= 5)
			precio=precio*1.2;
		else if (month > 8)
			precio=precio*(0.8);
		else if (month <= 2)
			precio=precio*0.5;
	}

	public void precioTipo(int tipo) {
		if (tipo == 1)
			precio*=2;
	}

	public void precioPuntuacion(double puntuacion) {//nota media cada hab resena
		double nota = 5-puntuacion;
		precio = precio*(1-nota/10);
	}

	public String toString() {
		return "Precio Habitación: " + precioFinal1();
	}
	
}

