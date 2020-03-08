package ingsoft1920.cm.bean;

public class Valoracion {
	
	private int id;
	private String cabecera;
	private String cuerpo;
	private int nota;
	private int cliente_id;
	private int hotel_id;
	
	public Valoracion() {}
	public Valoracion(int id, String cabecera, String cuerpo, int nota, int cliente_id, int hotel_id) {
		this.id = id;
		this.cabecera = cabecera;
		this.cuerpo = cuerpo;
		this.nota = nota;
		this.cliente_id = cliente_id;
		this.hotel_id = hotel_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", cabecera=" + cabecera + ", cuerpo=" + cuerpo + ", nota=" + nota
				+ ", cliente_id=" + cliente_id + ", hotel_id=" + hotel_id + "]";
	}

}
