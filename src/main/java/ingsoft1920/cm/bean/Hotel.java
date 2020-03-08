package ingsoft1920.cm.bean;

public class Hotel {
	
	private int id;
	private String nombre;
	private String continente;
	private String pais;
	private String ciudad;
	private String direccion;
	private int estrellas;
	private String descripcion;
	
	public Hotel() {}
	
	public Hotel(int id, String nombre, String continente, String pais, String ciudad, String direccion, int estrellas,
			String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.continente = continente;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.estrellas = estrellas;
		this.descripcion = descripcion;
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
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nombre=" + nombre + ", continente=" + continente + ", pais=" + pais + ", ciudad="
				+ ciudad + ", direccion=" + direccion + ", estrellas=" + estrellas + ", descripcion=" + descripcion
				+ "]";
	}

}
