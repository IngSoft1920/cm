package ingsoft1920.cm.bean.auxiliares;

import java.sql.Date;

public class Precio_Habitacion {
	
	private int hotel_id;
	private int tipo_hab_id;
	private Date fecha;
	private double precio_por_noche;
	
	public Precio_Habitacion() {}
	
	public Precio_Habitacion(int hotel_id, int tipo_hab_id, Date fecha, double precio_por_noche) {
		super();
		this.hotel_id = hotel_id;
		this.tipo_hab_id = tipo_hab_id;
		this.fecha = fecha;
		this.precio_por_noche = precio_por_noche;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getPrecio_por_noche() {
		return precio_por_noche;
	}
	public void setPrecio_por_noche(double precio_por_noche) {
		this.precio_por_noche = precio_por_noche;
	}
	@Override
	public String toString() {
		return "Precio_Habitacion [hotel_id=" + hotel_id + ", tipo_hab_id=" + tipo_hab_id + ", fecha=" + fecha
				+ ", precio_por_noche=" + precio_por_noche + "]";
	}
	
	

}
