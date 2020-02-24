package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Peticion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PeticionesDAO {

    private static conectorBBDD conector = new conectorBBDD("8000", "cm1", "ingSoft20cm1.711", "piedrafita.ls.fi.upm.es");

    public LinkedList<Peticion> getPeticiones(int id) {

        if (!conector.isConnected()){
            conector.conectar();
        }

        LinkedList<Peticion> peticiones = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        String getPeticiones = "SELECT * FROM peticiones";

        try {
            stmt = conector.getConn().prepareStatement(getPeticiones);
            rs = stmt.executeQuery();

            while (rs.next()){
                peticiones.add(new Peticion(rs.getInt("id"), rs.getString("ciudad"), rs.getString("fecha_peticiones"), rs.getDate("tipo_habitacion").toString(), rs.getInt("estado")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peticiones;
    }

    public void cambiaEstado(int id){

        if (!conector.isConnected()){
            conector.conectar();
        }

        String cambiaEstado = "UPDATE peticiones SET estado = 1 WHERE id = ?;";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(cambiaEstado);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();
    }
    
    
    public int addPeticion(String ciudad, Date fecha, Tipo tipo_habitacion,int estado) {
    		PreparedStatement st=null;
    		PreparedStatement st0=null;
		ResultSet rs=null;  
		int id=-1;
		  if (! conector.isConnected())
	          conector.conectar();
	      
		  try {
			  st=conector.getConn().prepareStatement("Add into peticiones(ciudad, fecha_peticiones, tipo_habitacion, estado) "
			  		+ "values(?,?,?,?);");
			  st.setString(1, ciudad);	
			  st.setDate(2,fecha);
			  st.setString(3, tipo_habitacion.toString());
			  st.setInt(4, estado);
			  st.executeQuery();
			  
			  st0=conector.getConn().prepareStatement("Select max(id) from peticiones");
			  rs=st.executeQuery();
			  id=rs.getInt("id");
			  
		  } catch (SQLException e) {
				e.printStackTrace();
			}finally { 
				if (rs != null) { try { rs.close(); } catch (SQLException sqlEx) { } rs = null; } 
				if (st != null) { try { st.close(); } catch (SQLException sqlEx) { }  st = null; } 
				if (st0 != null) { try { st0.close(); } catch (SQLException sqlEx) { }  st0 = null; } 
				conector.closeConn();
			}
    	
    		return id;
    }
    
    
		  
}
    
    


