package ingsoft1920.cm.bean;

import org.springframework.stereotype.Component;

@Component
public class Hotel {

	private int id;
	private String nombre;
	private String continente;
	private String pais;
	private String ciudad;
	private String direccion;

    public Hotel() {
    }

    public Hotel(int id, String nombre, String continente, String pais, String ciudad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.continente = continente;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
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
	public String getContinente() {
		return continente;
	}
	public void setContinente(String continente) {
		this.continente = continente;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", continente=" + continente + ", pais=" + pais + ", ciudad="
				+ ciudad + ", direccion=" + direccion + "]";
	}



}
