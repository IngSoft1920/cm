package ingsoft1920.cm.bean;

import java.sql.Date;

public class Pedido_Producto {
	
	private int pedido_id;
	private int producto_id;
	private int cantidad;
	
	public Pedido_Producto() {}

	public Pedido_Producto(int pedido_id, int producto_id, int cantidad) {
		super();
		this.pedido_id = pedido_id;
		this.producto_id = producto_id;
		this.cantidad = cantidad;
	}

	public int getPedido_id() {
		return pedido_id;
	}

	public void setPedido_id(int pedido_id) {
		this.pedido_id = pedido_id;
	}

	public int getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(int producto_id) {
		this.producto_id = producto_id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Pedido_Producto [pedido_id=" + pedido_id + ", producto_id=" + producto_id + ", cantidad=" + cantidad
				+ "]";
	}
	
}

	