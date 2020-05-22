package ingsoft1920.cm.bean;

import java.sql.Date;

public class Pedido {
	
	private int id;
	private Date fecha;
	private int proveedor_id;
	private int hotel_id;
	
	public Pedido() {}
	
	public Pedido(int id, Date fecha, int proveedor_id, int hotel_id) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.proveedor_id = proveedor_id;
		this.hotel_id = hotel_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getProveedor_id() {
		return proveedor_id;
	}
	public void setProveedor_id(int proveedor_id) {
		this.proveedor_id = proveedor_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", proveedor_id=" + proveedor_id + ", hotel_id=" + hotel_id
				+ "]";
	}

}
