package ingsoft1920.bean;

import org.springframework.stereotype.Component;

@Component
public class Habitacion {
	public enum Tipo {normal,premium};

	private int id;
	private double precio;
	private Tipo tipo;
	private int hotel_id;
	private boolean ocupada;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public boolean isOcupada() {
		return ocupada;
	}
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", precio=" + precio + ", tipo=" + tipo + ", hotel_id=" + hotel_id
				+ ", ocupada=" + ocupada + "]";
	}
	

}
