package ingsoft1920.cm.fna;

import java.util.HashMap;

public class BeneficiosGastosModel {

	private String nombreHotel;
	// key=tipo_habitacion, value= dinero total por tipo_habitacion
	private HashMap <String, Double> sumaReservas;
	// key=tipo_servicio, value= dinero total por tipo_servicio
	private HashMap <String, Double> sumaFacturas;
	// key=tipo_empleado (rol), value= dinero total por rol
	private HashMap <String, Double> sueldoEmpleados;
	private double gastoComida;
	private double total;
	
	public BeneficiosGastosModel(String nombreHotel, double gastoComida) {
		this.nombreHotel = nombreHotel;
		this.sumaReservas = new HashMap <String, Double> ();
		this.sumaFacturas = new HashMap <String, Double> ();
		this.gastoComida = gastoComida;
		this.sueldoEmpleados = new HashMap <String, Double> ();
		this.total=0;
	}
	public String getNombreHotel() {
		return nombreHotel;
	}
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public double getGastoComida() {
		return gastoComida;
	}
	public void setGastoComida(double gastoComida) {
		this.gastoComida = gastoComida;
	}
	public HashMap<String, Double> getSueldoEmpleados() {
		return sueldoEmpleados;
	}
	public void setSueldoEmpleados(HashMap<String, Double> sueldoEmpleados) {
		this.sueldoEmpleados = sueldoEmpleados;
	}
	public HashMap<String, Double> getSumaReservas() {
		return sumaReservas;
	}
	public void setSumaReservas(HashMap<String, Double> sumaReservas) {
		this.sumaReservas = sumaReservas;
	}
	public HashMap<String, Double> getSumaFacturas() {
		return sumaFacturas;
	}
	public void setSumaFacturas(HashMap<String, Double> sumaFacturas) {
		this.sumaFacturas = sumaFacturas;
	}
	@Override
	public String toString() {
		return "BeneficiosGastosModel [nombreHotel=" + nombreHotel + ", sumaReservas=" + sumaReservas
				+ ", sumaFacturas=" + sumaFacturas + ", sueldoEmpleados=" + sueldoEmpleados + ", gastoComida="
				+ gastoComida + "]";
	}
	
	public double getTotal() {
		return 0;
	}
	
	
	


}
