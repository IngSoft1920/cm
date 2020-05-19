package ingsoft1920.cm.bean;

public class Proveedor_Producto {
	
	private int producto_id;
	private int proveedor_id;
	private String precio_venta;
	
	public Proveedor_Producto() {}
	public Proveedor_Producto(int producto_id, int proveedor_id, String precio_venta) {
		super();
		this.producto_id = producto_id;
		this.proveedor_id = proveedor_id;
		this.precio_venta = precio_venta;
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
	public String getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(String precio_venta) {
		this.precio_venta = precio_venta;
	}
	
	
}