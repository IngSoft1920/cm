package ingsoft1920.cm.bean;

import java.sql.Date;

public class Reserva {
	
	public enum Regimen { no_aplica , media_pension , pension_completa , todo_incluido }
	public enum Metodo_Pago { efectivo , pagado }
	
	private int id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private int importe;
	private Regimen regimen_comida;
	private int numero_acompanantes;
	private int hotel_id;
	private Integer cliente_id; // Integer porque podría ser null (reserva anónima)
	private int tipo_hab_id;
	private Metodo_Pago metodo_pago;
	
	public Reserva() {}

	public Reserva(int id, Date fecha_entrada, Date fecha_salida, int importe, Regimen regimen_comida,
			int numero_acompanantes, int hotel_id, Integer cliente_id, int tipo_hab_id, Metodo_Pago metodo_pago) {
		super();
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.importe = importe;
		this.regimen_comida = regimen_comida;
		this.numero_acompanantes = numero_acompanantes;
		this.hotel_id = hotel_id;
		this.cliente_id = cliente_id;
		this.tipo_hab_id = tipo_hab_id;
		this.metodo_pago = metodo_pago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public Regimen getRegimen_comida() {
		return regimen_comida;
	}

	public void setRegimen_comida(Regimen regimen_comida) {
		this.regimen_comida = regimen_comida;
	}

	public int getNumero_acompanantes() {
		return numero_acompanantes;
	}

	public void setNumero_acompanantes(int numero_acompanantes) {
		this.numero_acompanantes = numero_acompanantes;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}

	public int getTipo_hab_id() {
		return tipo_hab_id;
	}

	public void setTipo_hab_id(int tipo_hab_id) {
		this.tipo_hab_id = tipo_hab_id;
	}

	public Metodo_Pago getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(Metodo_Pago metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida
				+ ", importe=" + importe + ", regimen_comida=" + regimen_comida + ", numero_acompanantes="
				+ numero_acompanantes + ", hotel_id=" + hotel_id + ", cliente_id=" + cliente_id + ", tipo_hab_id="
				+ tipo_hab_id + ", metodo_pago=" + metodo_pago + "]";
	}
	
	
	

}
