package ingsoft1920.cm.fna;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.dho.controller.Conexion;
import ingsoft1920.dho.fna.FacturaBean;

@Component
public class FacturaDAO {
	
	@Autowired
    private QueryRunner runner = new QueryRunner();
	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();
	
	public double beneficio(int hotel_id) {
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
	/*
	// Devuelve null si no se ha podido hacer login
	public Cliente login(String email,String password) {
		Cliente res = null;
		BeanHandler<Cliente> handler = new BeanHandler<>(Cliente.class);
		String query = "SELECT * FROM Cliente WHERE email=? and password=?";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.query(conn,query, handler,email,password);
			
		} catch( Exception e ) { e.printStackTrace(); }
		
		return res;
	}

  try{
		    stmt = conexion.getConexion().createStatement();
		    rs = stmt.executeQuery("SELECT sum(importe) AS sumaReservas, sum(precio) AS sumaFacturas\r\n" + "FROM Reserva, Factura\r\n" + 
		    "WHERE hotel_id=" +hotel_id + "\r\n", "SET total="sumaReservas+sumaFacturas +"\r\n");
		}catch(SQLException ex){
		    System.out.println("SQLException: "+ ex.getMessage());
		}finally{
		    if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
			if (stmt != null) { try {  stmt.close(); } catch (SQLException sqlEx) { }  stmt = null; } 
		}
		return total;
	} 
	*/
 
}
