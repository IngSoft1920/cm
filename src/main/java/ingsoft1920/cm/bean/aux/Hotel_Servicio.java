package ingsoft1920.cm.bean.aux;

public class Hotel_Servicio {
	
	private int hotel_id;
	private int servicio_id;
	private int precio;
	private String unidad_medida;
	
	public Hotel_Servicio() {}
	public Hotel_Servicio(int hotel_id, int servicio_id, int precio, String unidad_medida) {
		this.hotel_id = hotel_id;
		this.servicio_id = servicio_id;
		this.precio = precio;
		this.unidad_medida = unidad_medida;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public int getServicio_id() {
		return servicio_id;
	}
	public void setServicio_id(int servicio_id) {
		this.servicio_id = servicio_id;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	@Override
	public String toString() {
		return "Hotel_Servicio [hotel_id=" + hotel_id + ", servicio_id=" + servicio_id + ", precio=" + precio
				+ ", unidad_medida=" + unidad_medida + "]";
	}

}
