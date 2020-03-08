package ingsoft1920.cm.bean;

import java.sql.Date;

public class Ausencia {
	
	public enum Estado { denegada , aprobada , pendiente }
	
	private int id;
	private String motivo;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Estado estado;
	private int empleado_id;
	
	
	public Ausencia() {}
	
	public Ausencia(int id, String motivo, Date fecha_inicio, Date fecha_fin, Estado estado, int empleado_id) {
		this.id = id;
		this.motivo = motivo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.estado = estado;
		this.empleado_id = empleado_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(int empleado_id) {
		this.empleado_id = empleado_id;
	}
	@Override
	public String toString() {
		return "Ausencia [id=" + id + ", motivo=" + motivo + ", fecha_inicio=" + fecha_inicio + ", fecha_fin="
				+ fecha_fin + ", estado=" + estado + ", empleado_id=" + empleado_id + "]";
	}
	

}
