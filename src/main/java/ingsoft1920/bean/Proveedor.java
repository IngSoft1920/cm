package ingsoft1920.bean;

public class Proveedor {
	private String empresa;
	private String producto;
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String toString() {
		return "Proveedor [empresa=" + empresa + ", producto=" + producto + "]";
	}
}
