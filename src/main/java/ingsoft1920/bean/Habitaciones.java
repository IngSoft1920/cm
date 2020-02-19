package ingsoft1920.bean;

public class Habitaciones {

	public enum Tipo { normal , premium }

	private int hotel_id;
	private Tipo tipo;
	private int num_disponibles;
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public int getNum_disponibles() {
		return num_disponibles;
	}
	public void setNum_disponibles(int num_disponibles) {
		this.num_disponibles = num_disponibles;
	}


}
