package ingsoft1920.cm.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Empleado {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private double sueldo;
	private int profesion_id;
	private String dias_libres; //->0-Lunes,1-Martes,...,6-Domingo . Es un json array: '[1,3,4]'

	public Empleado() {}

	public Empleado(int id, String nombre, String apellidos, String email, String telefono, double sueldo,
			int profesion_id, String dias_libres) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.sueldo = sueldo;
		this.profesion_id = profesion_id;
		this.dias_libres = dias_libres;
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

	public String getDias_libres() {
		return dias_libres;
	}

	public void setDias_libres(String dias_libres) {
		this.dias_libres = dias_libres;
	}
	
	public List<String> getDiasLibresString() {
		List<String> res = new ArrayList<>();
		
		JsonParser
		  .parseString(dias_libres)
		  .getAsJsonArray()
		  .forEach(dia -> {
			  String diaStr="";
			  switch(dia.getAsInt()) {
			  
			  case 0:
				  diaStr="Lunes";
				  break;
			  case 1:
				  diaStr="Martes";
				  break;
			  case 2:
				  diaStr="Miércoles";
				  break;
			  case 3:
				  diaStr="Jueves";
				  break;
			  case 4:
				  diaStr="Viernes";
				  break;
			  case 5:
				  diaStr="Sábado";
				  break;
			  case 6:
				  diaStr="Domingo";
				  break;
			  }
			  res.add( diaStr );
		  });
		
		return res;
	}
	

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", telefono=" + telefono + ", sueldo=" + sueldo + ", profesion_id=" + profesion_id + ", dias_libres="
				+ dias_libres + "]";
	}
	

}
