package ingsoft1920.cm.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Precio_Habitacion;
import ingsoft1920.cm.conector.ConectorBBDD;

public class Precio_HabitacionDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();

    @Autowired
    private ConectorBBDD conector = new ConectorBBDD();
    
    // Si no está añade y si está edita
    public void setPrecio(Precio_Habitacion ph) {
    	
    	String query;
    	
    	if( existe(ph) ) {
    		query = "UPDATE Precio_Habitacion "
    			   +"SET precio_por_noche=? "
    			   +"WHERE hotel_id=? AND tipo_hab_id=? AND fecha=?";
    	}
    	else {
    		query = "INSERT INTO Precio_Habitacion "
    				+"(precio_por_noche,hotel_id,tipo_hab_id,fecha) "
    				+"VALUES (?,?,?,?)";
    	}
    	
    	try( Connection conn = conector.getConn() )
    	{
    		runner.update(conn, query,
    					   ph.getPrecio_por_noche(),
    					   ph.getHotel_id(),
    					   ph.getTipo_hab_id(),
    					   ph.getFecha()
    					  );
    		    		
    	} catch(Exception e) { e.printStackTrace(); }
    	
    }
    
    private boolean existe(Precio_Habitacion ph) {
    	Long num = null;
    	ScalarHandler<Long> handler = new ScalarHandler<>();
    	String query = "SELECT COUNT(*) FROM Precio_Habitacion "
    				  +"WHERE hotel_id=? AND tipo_hab_id=? AND fecha=?";
    	
    	try( Connection conn = conector.getConn() )
    	{
    		num = runner.query(conn,query,handler,
    							ph.getHotel_id(),
    							ph.getTipo_hab_id(),
    							ph.getFecha()
    						   );
    		    		
    	} catch(Exception e) { e.printStackTrace(); }
    	
    	return num != null ? num > 0 : false;
    }
    

	
}
