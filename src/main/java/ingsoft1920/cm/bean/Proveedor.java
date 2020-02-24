package ingsoft1920.cm.bean;

public class Proveedor {
	private int id;
	private String empresa;
	private String producto;

    public Proveedor() {
    }

    public Proveedor(int id, String empresa, String producto) {
        this.id = id;
        this.empresa = empresa;
        this.producto = producto;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", empresa=" + empresa + ", producto=" + producto + "]";
	}
}
