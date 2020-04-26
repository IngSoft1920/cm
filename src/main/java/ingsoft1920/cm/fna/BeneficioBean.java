package ingsoft1920.cm.fna;



public class BeneficioBean {

	//private String[] nombresHotel;
	// key=tipo_habitacion, value= dinero total por tipo_habitacion
	private boolean reserva;
	// key=tipo_servicio, value= dinero total por tipo_servicio
	private boolean servicios;
	// key=tipo_empleado (rol), value= dinero total por rol
	private boolean empleados;
	private boolean comida;

	/*
	 public String[] getNombresHotel() {
	      return this.nombresHotel;
	   }
	   
	 public void setNombresHotel(String [] h) {
	      this.nombresHotel = h;
	   }
	 */
	 public boolean getIfReserva() {
		 return this.reserva;
	 }
	 
	 public void setIfReserva(boolean r) {
		 this.reserva=r;
	 }
	 
	 public boolean getIfServicios() {
		 return this.servicios;
	 }
	 
	 public void setIfServicios(boolean s) {
		 this.servicios=s;
	 }
	 
	 public boolean getIfEmpleados() {
		 return this.empleados;
	 }
	 
	 public void setIfEmpleados(boolean h) {
		 this.empleados=h;
	 }
	 
	 public boolean getIfComida() {
		 return this.comida;
	 }
	 
	 public void setIfComida(boolean h) {
		 this.comida=h;
	 }
	 
	 
	 
	 
	 
	 
}
