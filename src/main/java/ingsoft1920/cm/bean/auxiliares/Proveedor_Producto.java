package ingsoft1920.cm.bean.auxiliares;

public class Proveedor_Producto {
	private int producto_id;
	private int proveedor_id;
	
	
	public Proveedor_Producto() {}
	
	public Proveedor_Producto(int producto_id, int proveedor_id) {
		super();
		this.producto_id = producto_id;
		this.proveedor_id = proveedor_id;
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
	
	
}
