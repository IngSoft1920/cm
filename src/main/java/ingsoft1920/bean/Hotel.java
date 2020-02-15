package ingsoft1920.bean;

import org.springframework.stereotype.Component;

@Component
public class Hotel {
	
	int hotel_id;
	String nombre;
	String ubicacion;
		
	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
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
		return "Hotel [hotel_id=" + hotel_id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	

}
