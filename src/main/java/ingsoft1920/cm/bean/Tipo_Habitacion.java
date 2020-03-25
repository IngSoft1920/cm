package ingsoft1920.cm.bean;

public class Tipo_Habitacion {
	
	private int id;
	private String nombre;
	
	public Tipo_Habitacion() {}
	
	public Tipo_Habitacion(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Tipo_Habitacion [id=" + id + ", nombre=" + nombre + "]";
	}

}
