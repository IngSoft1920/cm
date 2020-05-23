package ingsoft1920.cm.bean;

public class Producto {
	
	private int id;
	private String nombre;
	private int precio_maximo;
	private String unidad_medida;
	
	public Producto() {}
	
	public Producto(int id, String nombre, int precio_maximo, String unidad_medida) {
		super();
		this.id = id;
        this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
		this.precio_maximo = precio_maximo;
		this.unidad_medida = unidad_medida;
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
        this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
	}
	public int getPrecio_maximo() {
		return precio_maximo;
	}
	public void setPrecio_maximo(int precio_maximo) {
		this.precio_maximo = precio_maximo;
	}
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio_maximo=" + precio_maximo + ", unidad_medida="
				+ unidad_medida + "]";
	}

}
