package ingsoft1920.cm.bean;

import java.sql.Date;

public class Hotel_Empleado {
	
	private int empleado_id;
	private int hotel_id;
	private Date fecha_contratacion;
	
	public Hotel_Empleado() {}

	public Hotel_Empleado(int empleado_id, int hotel_id, Date fecha_contratacion) {
		super();
		this.empleado_id = empleado_id;
		this.hotel_id = hotel_id;
		this.fecha_contratacion = fecha_contratacion;
	}

	public int getEmpleado_id() {
		return empleado_id;
	}

	public void setEmpleado_id(int empleado_id) {
		this.empleado_id = empleado_id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Date getFecha_contratacion() {
		return fecha_contratacion;
	}

	public void setFecha_contratacion(Date fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}

	@Override
	public String toString() {
		return "Hotel_Empleado [empleado_id=" + empleado_id + ", hotel_id=" + hotel_id + ", fecha_contratacion="
				+ fecha_contratacion + "]";
	}
	
		
	

}
