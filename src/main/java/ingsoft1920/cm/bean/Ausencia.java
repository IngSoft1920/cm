package ingsoft1920.cm.bean;

import java.math.BigInteger;
import java.sql.Date;

public class Ausencia {
	
	public enum Estado { denegada , aprobada , pendiente }
	
	private BigInteger id;
	private String motivo;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Estado estado;
	private BigInteger empleado_id;
	
	
	public Ausencia() {}
	
	public Ausencia(BigInteger id, String motivo, Date fecha_inicio,
			Date fecha_fin, Estado estado, BigInteger empleado_id) {
		this.id = id;
		this.motivo = motivo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.estado = estado;
		this.empleado_id = empleado_id;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public BigInteger getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(BigInteger empleado_id) {
		this.empleado_id = empleado_id;
	}
	@Override
	public String toString() {
		return "Ausencia [id=" + id + ", motivo=" + motivo + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
				+ fecha_fin + ", estado=" + estado + ", empleado_id=" + empleado_id + "]";
	}
	

}
