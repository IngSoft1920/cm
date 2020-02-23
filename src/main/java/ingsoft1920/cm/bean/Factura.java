package ingsoft1920.cm.bean;

import java.sql.Date;

public class Factura {

	private int id;
	private double importe;
	private boolean pagado;
	private String descripcion;
	private Date fecha;
	private int cliente_id;

    public Factura() {
    }

    public Factura(int id, double importe, boolean pagado, String descripcion, Date fecha, int cliente_id) {
        this.id = id;
        this.importe = importe;
        this.pagado = pagado;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cliente_id = cliente_id;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public boolean getPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	@Override
	public String toString() {
		return "Factura [id=" + id + ", importe=" + importe + ", pagado=" + pagado + ", descripcion=" + descripcion
				+ ", fecha=" + fecha + ", cliente_id=" + cliente_id + "]";
	}



}
