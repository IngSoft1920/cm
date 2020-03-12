package ingsoft1920.cm.fna;

public class BeneficiosGastosModel {

	private String nombreHotel;
	private int sumaReservas;
	private int sumaFacturas;
	private int sueldoEmpleados;
	private int gastoComida;
	
	public BeneficiosGastosModel (String nombreHotel, int sumaReservas, int sumaFacturas,int sueldoEmpleados,int gastoComida) {
		this.nombreHotel = nombreHotel;
		this.sumaReservas = sumaReservas;
		this.sumaFacturas = sumaFacturas;
		this.sueldoEmpleados=sueldoEmpleados;
		this.gastoComida=gastoComida;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public int getSumaReservas() {
		return sumaReservas;
	}

	public void setSumaReservas(int sumaReservas) {
		this.sumaReservas = sumaReservas;
	}

	public int getSumaFacturas() {
		return sumaFacturas;
	}

	public void setSumaFacturas(int sumaFacturas) {
		this.sumaFacturas = sumaFacturas;
	}

	public int getSueldoEmpleados() {
		return sueldoEmpleados;
	}

	public void setSueldoEmpleados(int sueldoEmpleados) {
		this.sueldoEmpleados = sueldoEmpleados;
	}

	public int getGastoComida() {
		return gastoComida;
	}

	public void setGastoComida(int gastoComida) {
		this.gastoComida = gastoComida;
	}

	@Override
	public String toString() {
		return "BeneficiosGastosModel [nombreHotel=" + nombreHotel + ", sumaReservas=" + sumaReservas
				+ ", sumaFacturas=" + sumaFacturas + ", sueldoEmpleados=" + sueldoEmpleados + ", gastoComida="
				+ gastoComida + "]";
	}


}
