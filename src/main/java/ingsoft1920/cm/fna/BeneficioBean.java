package ingsoft1920.cm.fna;

import java.util.HashMap;

public class BeneficioBean {

	private String[] nombresHotel;
	// key=tipo_habitacion, value= dinero total por tipo_habitacion
	private String [] tiposHabitacion;
	// key=tipo_servicio, value= dinero total por tipo_servicio
	private String[] tiposServicio;
	// key=tipo_empleado (rol), value= dinero total por rol
	private String[] tiposEmpleados;
	private boolean comida;

	
	 public String[] getNombresHotel() {
	      return this.nombresHotel;
	   }
	   
	 public void setNombresHotel(String [] h) {
	      this.nombresHotel = h;
	   }
	 
	 public String[] getTiposHabitacion() {
		 return this.tiposHabitacion;
	 }
	 
	 public void setTiposHabitacion(String [] h) {
		 this.tiposHabitacion=h;
	 }
	 
	 public String[] getTiposServicio() {
		 return this.tiposServicio;
	 }
	 
	 public void setTiposServicios(String [] h) {
		 this.tiposServicio=h;
	 }
	 
	 public String[] getTiposEmpleados() {
		 return this.tiposEmpleados;
	 }
	 
	 public void setTiposEmpleado(String [] h) {
		 this.tiposEmpleados=h;
	 }
	 
	 public boolean getComida() {
		 return this.comida;
	 }
	 
	 public void setComida(boolean h) {
		 this.comida=h;
	 }
	 
	 
	 
	 
	 
	 
}
