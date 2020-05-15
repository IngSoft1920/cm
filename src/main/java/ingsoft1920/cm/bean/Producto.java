package ingsoft1920.cm.bean;

public class Producto {
	
	private int id;
	private String nombre;
	public double getPrecio_maximo() {
		return precio_maximo;
	}
	public void setPrecio_maximo(double precio_maximo) {
		this.precio_maximo = precio_maximo;
	}
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	private double precio_maximo;
	private String unidad_medida;
	
	
	public Producto() {}
	public Producto(int id, String nombre) {
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
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}
}
