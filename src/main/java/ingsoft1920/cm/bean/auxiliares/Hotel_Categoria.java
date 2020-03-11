package ingsoft1920.cm.bean.auxiliares;

public class Hotel_Categoria {
	
	private int hotel_id;
	private int categoria_id;
	
	public Hotel_Categoria() {}
	public Hotel_Categoria(int hotel_id, int categoria_id) {
		this.hotel_id = hotel_id;
		this.categoria_id = categoria_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public int getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}
	@Override
	public String toString() {
		return "Hotel_Categoria [hotel_id=" + hotel_id + ", categoria_id=" + categoria_id + "]";
	}
	

}
