package ingsoft1920.cm.fna;

import java.util.HashMap;

public class BeneficiosGastosModel {

	private String nombreHotel;
	// key=tipo_habitacion, value= dinero total por tipo_habitacion
	private HashMap <String, Double> sumaReservas;
	// key=tipo_servicio, value= dinero total por tipo_servicio
	private HashMap <String, Double> sumaFacturas;
	// key=tipo_empleado (rol), value= dinero total por rol
	private HashMap <String, Double> sueldoEmpleados;
	//key=producto, value=dinero total por producto
	private HashMap <String, Double> gastoComida;
	private double total;
	private int numeroRoomNights;
	private double beneficiosPorRoomNight;
	private double gastosPorRoomNight;
	private double balancePorRoomNight;
	
	public BeneficiosGastosModel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
		this.sumaReservas = new HashMap <String, Double> ();
		this.sumaFacturas = new HashMap <String, Double> ();
		this.gastoComida = new HashMap <String, Double> ();
		this.sueldoEmpleados = new HashMap <String, Double> ();
		this.total=0;
		this.numeroRoomNights=0;
		this.beneficiosPorRoomNight=0;
		this.gastosPorRoomNight=0;
		this.balancePorRoomNight=0;
	}
	
	
	public String getNombreHotel() {
		return nombreHotel;
	}
	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public HashMap<String, Double> getGastoComida() {
		return gastoComida;
	}
	public void setGastoComida(HashMap<String, Double> gastoComida) {
		this.gastoComida = gastoComida;
	}
	public HashMap<String, Double> getSueldoEmpleados() {
		return sueldoEmpleados;
	}
	public void setSueldoEmpleados(HashMap<String, Double> sueldoEmpleados) {
		this.sueldoEmpleados = sueldoEmpleados;
	}
	public HashMap<String, Double> getSumaReservas() {
		return sumaReservas;
	}
	public void setSumaReservas(HashMap<String, Double> sumaReservas) {
		this.sumaReservas = sumaReservas;
	}
	public HashMap<String, Double> getSumaFacturas() {
		return sumaFacturas;
	}
	public void setSumaFacturas(HashMap<String, Double> sumaFacturas) {
		this.sumaFacturas = sumaFacturas;
	}
	@Override
	public String toString() {
		return "BeneficiosGastosModel [nombreHotel=" + nombreHotel + ", sumaReservas=" + sumaReservas
				+ ", sumaFacturas=" + sumaFacturas + ", sueldoEmpleados=" + sueldoEmpleados + ", gastoComida="
				+ gastoComida + "]";
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
/*	
	public static HashMap<Integer, BeneficiosGastosModel> gastosProveedores( HashMap<Integer, BeneficiosGastosModel> map) {
	String gastosProveedores =
			"SELECT P1.id AS Pedido ,PP.cantidad AS cantidad ,P2.id AS producto_id, P2.nombre AS nombre_producto, "
			+ "HPP.precio AS precio,HPP.hotel_id AS hotel_id, H.nombre AS nombre" + "FROM Pedido AS P1" + 
					"JOIN Pedido_Producto AS PP ON P1.id = PP.pedido_id" +"JOIN Producto AS P2 ON PP.producto_id=P2.id"+ 
							+ "JOIN Hotel_Proveedor_Producto AS HPP ON P2.id=HPP.producto_id AND P1.hotel_id=HPP.hotel_id" + 
					"JOIN Hotel AS H ON P1.hotel_id=H.id" + "ORDER BY P1.id";
	java.sql.Statement stmt= null;
	ResultSet rs= null;
	BeneficiosGastosModel aux;
	try {
		stmt=conector.getConn().createStatement();
		rs=stmt.executeQuery(gastosProveedores);
                    HashMap <String, Double> servicios;
		while(rs.next()) {
			map.get(rs.getInt("HPP.hotel_id"));
			if(rs!=null) {
			   servicios.put(rs.getString("P1.id") , rs.getInt(HPP.precio));
				
			}
			
		}
	}catch (SQLException ex){ 
		System.out.println("SQLException: " + ex.getMessage());
	} finally { // it is a good idea to release resources in a finally block 
		if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
		if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
	}
	return map;
}*/


	public int getNumeroRoomNights() {
		return numeroRoomNights;
	}


	public void setNumeroRoomNights(int numeroRoomNights) {
		this.numeroRoomNights = numeroRoomNights;
	}
	
	public double beneficiosPorRoomNight() {
		double resultado=0;
		if(this.numeroRoomNights==0)
			return 0;
		HashMap <String, Double> aux=this.sumaReservas;
		for (Double aux2: aux.values()) {
		    resultado+=aux2;
		}
		aux=this.sumaFacturas;
		for (Double aux2: aux.values()) {
		    resultado+=aux2;
		}
		return resultado/this.numeroRoomNights;
	}
	
	public double gastosPorRoomNight() {
		double resultado=0;
		if(this.numeroRoomNights==0)
			return 0;
		HashMap <String, Double> aux=this.sueldoEmpleados;
		for (Double aux2: aux.values()) {
		    resultado+=aux2;
		}
		aux=this.gastoComida;
		for (Double aux2: aux.values()) {
		    resultado+=aux2;
		}
		return resultado/this.numeroRoomNights;
	}
	
	public double balancePorRoomNight() {
		return this.beneficiosPorRoomNight()-this.gastosPorRoomNight();
	}


	public double getBeneficiosPorRoomNight() {
		this.beneficiosPorRoomNight=this.beneficiosPorRoomNight();
		return beneficiosPorRoomNight;
	}


	public void setBeneficiosPorRoomNight(double beneficiosPorRoomNight) {
		this.beneficiosPorRoomNight = beneficiosPorRoomNight;
	}


	public double getGastosPorRoomNight() {
		this.gastosPorRoomNight=this.gastosPorRoomNight();
		return gastosPorRoomNight;
	}


	public void setGastosPorRoomNight(double gastosPorRoomNight) {
		this.gastosPorRoomNight = gastosPorRoomNight;
	}


	public double getBalancePorRoomNight() {
		this.balancePorRoomNight=this.balancePorRoomNight();
		return balancePorRoomNight;
	}


	public void setBalancePorRoomNight(double balancePorRoomNight) {
		this.balancePorRoomNight = balancePorRoomNight;
	}



}
