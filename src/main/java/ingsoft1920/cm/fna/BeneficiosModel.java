package ingsoft1920.cm.fna;

public class BeneficiosModel {

	private String nombreHotel;
	private int sumaReservas;
	private int sumaFacturas;
	
	public BeneficiosModel(String nombreHotel, int sumaReservas, int sumaFacturas) {
		this.nombreHotel = nombreHotel;
		this.sumaReservas = sumaReservas;
		this.sumaFacturas = sumaFacturas;
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

	@Override
	public String toString() {
		return "BeneficiosModel [nombreHotel=" + nombreHotel + ", sumaReservas=" + sumaReservas + ", sumaFacturas="
				+ sumaFacturas + "]";
	}

}
