package ingsoft1920.cm.bean;

public class Profesion {

	private int id;
	private String nombre;
	
	public Profesion() {}
	public Profesion(int id, String nombre) {
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
		return "Profesion [id=" + id + ", nombre=" + nombre + "]";
	}


}
