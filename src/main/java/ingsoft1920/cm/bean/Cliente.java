package ingsoft1920.cm.bean;

public class Cliente {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String DNI;
	private String nacionalidad;
	private String telefono;
	private String email;
	private String password;
	private String preferencias;
	
	public Cliente() {}

	public Cliente(int id, String nombre, String apellidos, String dNI, String nacionalidad, String telefono,
			String email, String password, String preferencias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		DNI = dNI;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.preferencias = preferencias;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI
				+ ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", email=" + email + ", password="
				+ password + ", preferencias=" + preferencias + "]";
	}

}
