package ingsoft1920.cm.bean.auxiliares;

public class Servicio_Profesion {
	private int servicio_id;
	private int profesion_id;
	public int getServicio_id() {
		return servicio_id;
	}
	public void setServicio_id(int servicio_id) {
		this.servicio_id = servicio_id;
	}
	public int getProfesion_id() {
		return profesion_id;
	}
	public void setProfesion_id(int profesion_id) {
		this.profesion_id = profesion_id;
	}
	@Override
	public String toString() {
		return "Servicio_Profesion [servicio_id=" + servicio_id + ", profesion_id=" + profesion_id + "]";
	}
	
}
