package ingsoft1920.cm.bean;

public class Proveedor {
	
	private int id;
	private String empresa;
	private String CIF;
	
	public Proveedor() {}
	
	public Proveedor(int id, String empresa, String cIF) {
		this.id = id;
		this.empresa = empresa;
		CIF = cIF;
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
	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", empresa=" + empresa + ", CIF=" + CIF + "]";
	}
}
