package ingsoft1920.cm.fna;

public class BeneficiosGastosModel {

	private String nombreHotel;
	private double sumaReservas;
	private double sumaFacturas;
	private double sueldoEmpleados;
	private double gastoComida;
	
	public BeneficiosGastosModel(String nombreHotel, double sumaReservas, double sumaFacturas, double sueldoEmpleados,
			double gastoComida) {
		this.nombreHotel = nombreHotel;
		this.sumaReservas = sumaReservas;
		this.sumaFacturas = sumaFacturas;
		this.sueldoEmpleados = sueldoEmpleados;
		this.gastoComida = gastoComida;
	}
	public String getNombreHotel() {
		return nombreHotel;
	}
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	public double getSumaReservas() {
		return sumaReservas;
	}
	public void setSumaReservas(double sumaReservas) {
		this.sumaReservas = sumaReservas;
	}
	public double getSumaFacturas() {
		return sumaFacturas;
	}
	public void setSumaFacturas(double sumaFacturas) {
		this.sumaFacturas = sumaFacturas;
	}
	public double getSueldoEmpleados() {
		return sueldoEmpleados;
	}
	public void setSueldoEmpleados(double sueldoEmpleados) {
		this.sueldoEmpleados = sueldoEmpleados;
	}
	public double getGastoComida() {
		return gastoComida;
	}
	public void setGastoComida(double gastoComida) {
		this.gastoComida = gastoComida;
	}
	@Override
	public String toString() {
		return "BeneficiosGastosModel [nombreHotel=" + nombreHotel + ", sumaReservas=" + sumaReservas
				+ ", sumaFacturas=" + sumaFacturas + ", sueldoEmpleados=" + sueldoEmpleados + ", gastoComida="
				+ gastoComida + "]";
	}
	
	


}
