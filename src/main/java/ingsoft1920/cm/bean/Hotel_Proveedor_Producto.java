package ingsoft1920.cm.bean.auxiliares;

public class Hotel_Proveedor_Producto {
	private int hotel_id;
	private int producto_id;
	private int proveedor_id;
	private double precio;
	private String unidad_medida;
	
	public Hotel_Proveedor_Producto() {}
	
	public Hotel_Proveedor_Producto(int hotel_id, int producto_id, int proveedor_id, double precio,
			String unidad_medida) {
		this.hotel_id = hotel_id;
		this.producto_id = producto_id;
		this.proveedor_id = proveedor_id;
		this.precio = precio;
		this.unidad_medida = unidad_medida;
	}
	
	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public int getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}

	public int getProveedor_id() {
		return proveedor_id;
	}

	public void setProveedor_id(int proveedor_id) {
		this.proveedor_id = proveedor_id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUnidad_medida() {
		return unidad_medida;
	}

	public void setunidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	
}
