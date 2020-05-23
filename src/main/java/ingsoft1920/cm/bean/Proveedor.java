package ingsoft1920.cm.bean;

public class Proveedor {
	
	private int id;
	private String empresa;
	private String CIF;
	private String nombre;
	private String password;
	
	public Proveedor() {}
	
	public Proveedor(int id, String empresa, String cIF, String nombre, String password) {
		super();
		this.id = id;
		this.empresa = empresa;
		CIF = cIF;
		this.nombre = nombre;
		this.password = password;
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
	public String getCIF() {
		return CIF;
	}
	public void setCIF(String cIF) {
		CIF = cIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", empresa=" + empresa + ", CIF=" + CIF + ", nombre=" + nombre + ", password="
				+ password + "]";
	}
}
