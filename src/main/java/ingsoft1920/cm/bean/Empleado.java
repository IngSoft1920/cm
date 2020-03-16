package ingsoft1920.cm.bean;


public class Empleado {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private double sueldo;
	private int profesion_id;

	public Empleado() {}

	public Empleado(int id, String nombre, String apellidos, String email, String telefono, double sueldo,
			int profesion_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.sueldo = sueldo;
		this.profesion_id = profesion_id;
	}




	public int getId() {
		return id;
	}
	public void setId(int idGenerado) {
		this.id = idGenerado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public int getProfesion_id() {
		return profesion_id;
	}
	public void setProfesion_id(int profesion_id) {
		this.profesion_id = profesion_id;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", telefono=" + telefono + ", sueldo=" + sueldo + ", profesion_id=" + profesion_id + "]";
	}
	
	

}
