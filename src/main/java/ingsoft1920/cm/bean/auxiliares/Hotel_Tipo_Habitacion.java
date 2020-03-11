package ingsoft1920.cm.bean.auxiliares;

public class Hotel_Tipo_Habitacion {
	private int hotel_id;
	private int tipo_hab_id;
	private int num_disponibles;
	
	public Hotel_Tipo_Habitacion() {}
	public Hotel_Tipo_Habitacion(int hotel_id, int tipo_hab_id, int num_disponibles) {
		this.hotel_id = hotel_id;
		this.tipo_hab_id = tipo_hab_id;
		this.num_disponibles = num_disponibles;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public int getTipo_hab_id() {
		return tipo_hab_id;
	}
	public void setTipo_hab_id(int tipo_hab_id) {
		this.tipo_hab_id = tipo_hab_id;
	}
	public int getNum_disponibles() {
		return num_disponibles;
	}
	public void setNum_disponibles(int num_disponibles) {
		this.num_disponibles = num_disponibles;
	}
	@Override
	public String toString() {
		return "Hotel_Tipo_Habitacion [hotel_id=" + hotel_id + ", tipo_hab_id=" + tipo_hab_id + ", num_disponibles="
				+ num_disponibles + "]";
	}
}
