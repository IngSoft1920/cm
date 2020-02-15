package ingsoft1920.bean;

import org.springframework.stereotype.Component;

@Component
public class Hotel {
	
	String name;
	String ubicacion;
		
	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		return "Hotel [nombre=" + name + ", ubicacion=" + ubicacion + "]";
	}
	

}
