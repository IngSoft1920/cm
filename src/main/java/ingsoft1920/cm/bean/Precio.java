package ingsoft1920.cm.bean;

import java.sql.Date;

public class Precio {

	private int hotel_id;
	private Habitaciones.Tipo tipo;
	private Date fecha;
	private double precio;

    public Precio() {
    }

    public Precio(int hotel_id, Habitaciones.Tipo tipo, Date fecha, double precio) {
        this.hotel_id = hotel_id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Habitaciones.Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Habitaciones.Tipo tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Precio [hotel_id=" + hotel_id + ", tipo=" + tipo + ", fecha=" + fecha + ", precio=" + precio + "]";
	}



}
