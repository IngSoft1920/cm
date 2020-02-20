package ingsoft1920.cm.bean;

public class Feedback {

	private int id;
	private String cabecera;
	private String cuerpo;
	private double nota;	// En principio de 0.0 a 5.0
	private int cliente_id;
	private int hotel_id;
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
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
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
		return "Feedback [id=" + id + ", cabecera=" + cabecera + ", cuerpo=" + cuerpo + ", nota=" + nota
				+ ", cliente_id=" + cliente_id + ", hotel_id=" + hotel_id + "]";
	}

}
