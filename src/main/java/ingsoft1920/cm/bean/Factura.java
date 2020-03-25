package ingsoft1920.cm.bean;

import java.sql.Date;

public class Factura {
	
	private int id;
	private int importe;
	private Date fecha;
	private boolean pagado;
	private int cantidad_consumida;
	private int reserva_id;
	private int servicio_id;
	
	public Factura() {}
	
	public Factura(int id, int importe, Date fecha, boolean pagado, int cantidad_consumida, int reserva_id,
			int servicio_id) {
		this.id = id;
		this.importe = importe;
		this.fecha = fecha;
		this.pagado = pagado;
		this.cantidad_consumida = cantidad_consumida;
		this.reserva_id = reserva_id;
		this.servicio_id = servicio_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public int getCantidad_consumida() {
		return cantidad_consumida;
	}
	public void setCantidad_consumida(int cantidad_consumida) {
		this.cantidad_consumida = cantidad_consumida;
	}
	public int getReserva_id() {
		return reserva_id;
	}
	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
	}
	public int getServicio_id() {
		return servicio_id;
	}
	public void setServicio_id(int servicio_id) {
		this.servicio_id = servicio_id;
	}
	@Override
	public String toString() {
		return "Factura [id=" + id + ", importe=" + importe + ", fecha=" + fecha + ", pagado=" + pagado
				+ ", cantidad_consumida=" + cantidad_consumida + ", reserva_id=" + reserva_id + ", servicio_id="
				+ servicio_id + "]";
	}
}
