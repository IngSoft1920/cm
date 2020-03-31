package ingsoft1920.cm.bean;

public class Tipo_Habitacion {

	private int id;
	private String nombre_tipo;

	public Tipo_Habitacion() {
	}

	public Tipo_Habitacion(int id, String nombre_tipo) {
		this.id = id;
		this.nombre_tipo = nombre_tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_tipo() {
		return nombre_tipo;
	}

	public void setNombre_tipo(String nombre_tipo) {
		this.nombre_tipo = nombre_tipo;
	}

	@Override
	public String toString() {
		return "Tipo_Habitacion [id=" + id + ", nombre_tipo=" + nombre_tipo + "]";
	}

}
