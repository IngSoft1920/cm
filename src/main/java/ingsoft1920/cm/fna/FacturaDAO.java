package ingsoft1920.cm.fna;


import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class FacturaDAO {	
	@Autowired
    private static ConectorBBDD conector = new ConectorBBDD();
	
	//Metodo que crear el map con los hoteles y crear todas las entradas(teoricamente, ya que un hotel deberia tener clientes)
	//y devuelve dicho map con la informacion parcialmente completa. Faltaria añadir el costeAlimentos y el sueldoEmpleados
	//Que se haria en dos llamadas posteriores.
	public static HashMap<Integer, BeneficiosGastosModel> beneficioPorHotel() {
		//key=hotel_id, value=Beneficios del hotel
		HashMap <Integer, BeneficiosGastosModel> map = new HashMap <Integer, BeneficiosGastosModel> ();
		//Consulta para obtener el beneficio de las reservas(habitaciones)
		String beneficiosReservas = "SELECT R.hotel_id,H.nombre,SUM(R.importe)\r\n" + 
				"FROM Reserva AS R\r\n" + 
				"JOIN Hotel AS H ON R.hotel_id=H.id\r\n" + 
				"GROUP BY R.hotel_id;";
		//Consulta para obteher el beneficio de los servicios
		String beneficiosServicios ="SELECT R.hotel_id,H.nombre,SUM(F.importe)\r\n" + 
				"FROM Reserva AS R\r\n" + 
				"JOIN Factura AS F ON R.id=F.reserva_id\r\n" + 
				"JOIN Hotel AS H ON R.hotel_id=H.id\r\n" + 
				"GROUP BY R.hotel_id;";
		java.sql.Statement stmt= null;
		ResultSet rs= null;
		BeneficiosGastosModel aux;
		try {
			stmt=conector.getConn().createStatement();
			rs=stmt.executeQuery(beneficiosReservas);
			while(rs.next()) {
				//aux=new BeneficiosGastosModel(rs.getString("H.nombre"),rs.getDouble("SUM(R.importe)"),0,0);
				//map.put(rs.getInt("R.hotel_id"), aux);
			}
			rs.close();
			rs=stmt.executeQuery(beneficiosServicios);
			while(rs.next()) {
				aux=map.get(rs.getInt("R.hotel_id"));
				//El hotel ya esta, añadimos beneficios de los servicios
				if(aux!=null) {
					//aux.setSumaFacturas(rs.getDouble("SUM(F.importe)"));
				}
				//Sino existe creamos un nuevo hotel, teoricamente nunca se deberia meter en este else
				//Porque no tiene sentido un hotel que no tiene clientes que alquilen habitaciones
				else {
					//aux=new BeneficiosGastosModel(rs.getString("H.nombre"),0,rs.getDouble("SUM(F.importe)"),0);
					map.put(rs.getInt("R.hotel_id"), aux);
				}
			}
		}catch (SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
		} finally { // it is a good idea to release resources in a finally block 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		return map;
	}
	
	//Metodo que te recibe un map. Cada entrada simboliza un hotel distinto. En caso de que el hotel exista previamente, se actualizara el map 
	//añadiendo el costeAlimentos. En caso de que no exista, se creara una nueva entrada y se añadira al map (en un principio no tendria sentido 
	//añadir nuevas entradas al map, ya que un hotel deberia tener beneficios y ya se deberia haber añadido la entrada previamente)
	public static HashMap<Integer, BeneficiosGastosModel> gastosAlimentosPorHotel(HashMap <Integer, BeneficiosGastosModel> map) {
		//key=hotel_id, value=Beneficios del hotel
		//Consulta para obtener el gastos de los alimentos
		String consulta="SELECT P1.id AS Pedido ,PP.cantidad AS cantidad ,P2.id AS producto_id, HPP.precio AS precio,HPP.hotel_id AS hotel_id,H.nombre AS nombre\r\n" + 
				"FROM Pedido AS P1\r\n" + 
				"JOIN Pedido_Producto AS PP ON P1.id = PP.pedido_id\r\n" + 
				"JOIN Producto AS P2 ON PP.producto_id=P2.id\r\n" + 
				"JOIN Hotel_Proveedor_Producto AS HPP ON P2.id=HPP.producto_id AND P1.hotel_id=HPP.hotel_id\r\n" + 
				"JOIN Hotel AS H ON P1.hotel_id=H.id\r\n" + 
				"ORDER BY P1.id;";
		java.sql.Statement stmt= null;
		ResultSet rs= null;
		BeneficiosGastosModel aux;
		try {
			stmt=conector.getConn().createStatement();
			rs=stmt.executeQuery(consulta);
			while(rs.next()) {
				//Aqui iria el codigo para ver si el hotel_id esta ya en el map
				aux=map.get(rs.getInt("hotel_id"));
				if(aux!=null) {
					//En caso de estarlo, se actualizaria su value (añadirias el costeAlimentos)
						aux.setGastoComida(aux.getGastoComida() + (rs.getDouble("cantidad")*rs.getDouble("precio"))); //Toma el gasto anterior y le suma el nuevo
				}
				//En caso de no estarlo, añadir nueva entrada (Nombre del hotel, y costeAlimentos, el resto de valores los pondrias a 0)
				else {
					aux=new BeneficiosGastosModel(rs.getString("nombre"),rs.getDouble("cantidad")*rs.getDouble("precio"));
					map.put(rs.getInt("hotel_id"), aux);	
				}
			}
		}catch (SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
		} finally { // it is a good idea to release resources in a finally block 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		return map;
	}

	//Metodo que te recibe un map. Cada entrada simboliza un hotel distinto. En caso de que el hotel exista previamente, se actualizara el map 
	//añadiendo el importe de los servicios. En caso de que no exista, se creara una nueva entrada y se añadira al map
	public static HashMap<Integer, BeneficiosGastosModel> beneficiosServicios( HashMap<Integer, BeneficiosGastosModel> map) {
		//key=hotel_id, value=Beneficios del hotel
		String beneficiosServicios ="SELECT R.hotel_id,H.nombre,S.id, S.nombre, SUM(F.importe)\r\n" + 
				"FROM Reserva AS R\r\n" + 
				"JOIN Factura AS F ON R.id=F.reserva_id\r\n" + 
				"JOIN Hotel AS H ON R.hotel_id=H.id\r\n" + 
				"JOIN Servicio AS S ON F.servicio_is=S.id\r\n"+
				"GROUP BY R.hotel_id, S.id;";
		java.sql.Statement stmt= null;
		ResultSet rs= null;
		BeneficiosGastosModel aux;
		try {
			stmt=conector.getConn().createStatement();
			rs=stmt.executeQuery(beneficiosServicios);
			while(rs.next()) {
				aux=map.get(rs.getInt("R.hotel_id"));
				//añadimos beneficios de los servicios
				if(aux!=null) {
					Double servicio = aux.getSumaFacturas().get(rs.getString("S.nombre"));
					if(servicio!=null) {
						//Ya hay una entrada creada para ese servicio. Actualizar el value
						aux.getSumaFacturas().replace(rs.getString("S.nombre"), rs.getDouble(" SUM(F.importe)"));
					}
					else {
						//No hay una entrada creada, la creamos y añadimos los valores.
						aux.getSumaFacturas().put(rs.getString("S.nombre"), rs.getDouble(" SUM(F.importe)"));
					}
				
				}
				else {
					aux=new BeneficiosGastosModel(rs.getString("H.nombre"),0);
					aux.getSumaFacturas().put(rs.getString("S.nombre"), rs.getDouble(" SUM(F.importe)"));
					map.put(rs.getInt("R.hotel_id"), aux);
				}
			}
		}catch (SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
		} finally { // it is a good idea to release resources in a finally block 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		return map;
	}
 
}
