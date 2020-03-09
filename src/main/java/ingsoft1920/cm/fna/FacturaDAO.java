package ingsoft1920.cm.fna;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class FacturaDAO {	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();
	
	public double beneficioPorHotel(int hotel_id) {
		double beneficio = 0;		
		String query = "SELECT sum(f.importe), sum(r.importe), h.id " + "FROM Factura AS f"  
					  +"JOIN Reserva AS r on f.reserva_id = r.id " + "JOIN Hotel AS h on r.hotel_id=h.id"
				      + "WHERE h.id="+hotel_id;
		java.sql.Statement stmt= null;
		ResultSet rs= null;
		try {
			stmt=conector.getConn().createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				beneficio+=rs.getDouble("sum(f.importe)")+rs.getDouble("sum(r.importe)");
			}
		}catch (SQLException ex){ 
			System.out.println("SQLException: " + ex.getMessage());
		} finally { // it is a good idea to release resources in a finally block 
			if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		return beneficio;
	}

 
}
