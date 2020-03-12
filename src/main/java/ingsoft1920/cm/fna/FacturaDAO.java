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
    private ConectorBBDD conector = new ConectorBBDD();
	
	//Metodo que te devuelve los beneficios obtenidos de cada hotel(por alquilar habitaciones y por servicios)
	public HashMap<Integer, BeneficiosModel> beneficioPorHotel() {
		//key=hotel_id, value=Beneficios del hotel
		HashMap <Integer, BeneficiosModel> map = new HashMap <Integer, BeneficiosModel> ();
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
		BeneficiosModel aux;
		try {
			stmt=conector.getConn().createStatement();
			rs=stmt.executeQuery(beneficiosReservas);
			while(rs.next()) {
				aux=new BeneficiosModel(rs.getString("H.nombre"),rs.getInt("SUM(R.importe)"),0);
				map.put(rs.getInt("R.hotel_id"), aux);
			}
			rs.close();
			rs=stmt.executeQuery(beneficiosServicios);
			while(rs.next()) {
				aux=map.get(rs.getInt("R.hotel_id"));
				//El hotel ya esta, añadimos beneficios de los servicios
				if(aux!=null) {
					aux.setSumaFacturas(rs.getInt("SUM(F.importe)"));
				}
				//Sino existe creamos un nuevo hotel, teoricamente nunca se deberia meter en este else
				//Porque no tiene sentido un hotel que no tiene clientes que alquilen habitaciones
				else {
					aux=new BeneficiosModel(rs.getString("H.nombre"),0,rs.getInt("SUM(F.importe)"));
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
