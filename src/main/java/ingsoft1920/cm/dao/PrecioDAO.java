package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Habitaciones.Tipo;
import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Precio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrecioDAO {

    private static conectorBBDD conector = new conectorBBDD();

    //Recibe un objeto Precio (definido en ingsof1920/model)
    public void anadirPrecio(Precio precio){

        if (! conector.isConnected()){
            conector.conectar();
        }
        String anadirPrecio = "INSERT INTO precio (hotel_id, tipo, fecha, valor) VALUES (?,?,?,?)";

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(anadirPrecio);

            stmt.setInt(1, precio.getHotel_id());
            stmt.setString(2, precio.getTipo());
            stmt.setDate(3, Date.valueOf(precio.getFecha()));
            stmt.setDouble(4, precio.getPrecio());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

    }
    
    public void setPrecio(int id_hotel, Tipo tipoHabitacion,Date fecha, float precio) {
		  PreparedStatement st=null;
		  
		  if (! conector.isConnected())
	          conector.conectar();
	      
		  try {
			  st=conector.getConn().prepareStatement
					  ("Add into precio(id_hotel, tipo_habitacion, fecha, valor) values(?, ?, ?, ?);");
			  
			  st.setInt(1, id_hotel);	
			  st.setString(2,tipoHabitacion.toString());
			  st.setDate(3, fecha);
			  st.setFloat(4,precio);
			  st.executeQuery();
			  
		  } catch (SQLException e) {
				e.printStackTrace();
			}finally { 

				if (st != null) { try { st.close(); } catch (SQLException sqlEx) { }  st = null; }
				conector.closeConn();
			}
	  }
    
    public void addDatosPrecio(int idPeticion, float precio, int puntuacion) {
		  PreparedStatement st=null;
		  
		  if (! conector.isConnected())
	          conector.conectar();
	      
		  try {
			  st=conector.getConn().prepareStatement("Add into datos_precios(id_peticion, precio,puntuacion) "
			  		+ "values(?,?,?);");
			  st.setInt(1, idPeticion);	
			  st.setFloat(2,precio);
			  st.setInt(3, puntuacion);
			  st.executeQuery();
			  
		  } catch (SQLException e) {
				e.printStackTrace();
			}finally { 

				if (st != null) { try { st.close(); } catch (SQLException sqlEx) { }  st = null; }
				conector.closeConn();
			}

    }
    
}