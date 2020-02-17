package ingsoft1920.bean;

import org.springframework.stereotype.Component;

@Component
public class Hotel {

	private int id;
	private String nombre;
	private String ubicacion;

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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}


}
