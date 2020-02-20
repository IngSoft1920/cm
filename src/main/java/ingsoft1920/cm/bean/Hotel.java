package ingsoft1920.cm.bean;

import org.springframework.stereotype.Component;

@Component
public class Hotel {

	private int id;
	private String nombre;
	private String continente;
	private String pais;
	private String direccion;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", continente=" + continente + ", pais=" + pais
				+ ", direccion=" + direccion + "]";
	}




}
