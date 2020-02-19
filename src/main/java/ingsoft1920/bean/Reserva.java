package ingsoft1920.bean;

import java.sql.Date;

public class Reserva {

	private int id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private double importe;
	private int hotel_id;
	private Habitaciones.Tipo tipo;
	private int cliente_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
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
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida
				+ ", importe=" + importe + ", hotel_id=" + hotel_id + ", tipo=" + tipo + ", cliente_id=" + cliente_id
				+ "]";
	}



}
