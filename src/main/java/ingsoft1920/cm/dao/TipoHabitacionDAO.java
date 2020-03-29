package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Tipo_Habitacion;
import ingsoft1920.cm.conector.ConectorBBDD;

public class TipoHabitacionDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();
    
    @Autowired
    private ConectorBBDD conector = new ConectorBBDD();

    public int anadir(Tipo_Habitacion tipoHabitacion){
        BigInteger idGenerado = null;
        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        String query = "INSERT INTO Tipo_Habitacion (nombre) VALUES (?)";

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, query, handler, tipoHabitacion.getNombre());
            
        }
        catch(Exception e) { e.printStackTrace(); }

        return idGenerado != null ? idGenerado.intValue() : -1;
    }

    public Tipo_Habitacion getByID(int id){
        Tipo_Habitacion res = null;
        BeanHandler<Tipo_Habitacion> handler = new BeanHandler<>(Tipo_Habitacion.class);
        String query = "SELECT * FROM Tipo_Habitacion WHERE id = ?";
        
        try ( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, query, handler, id);

        } catch(Exception e) { e.printStackTrace(); }

        return res;
    }

    public List<Tipo_Habitacion> tipos(){
        List<Tipo_Habitacion> res = new ArrayList<>();
        BeanListHandler<Tipo_Habitacion> handler = new BeanListHandler<>(Tipo_Habitacion.class);
        String query = "SELECT * FROM Tipo_Habitacion";

        try (Connection conn = conector.getConn()) 
        {
            res = runner.query(conn, query, handler);

        } catch (Exception e) { e.printStackTrace(); }

        return res;
    }
    
    // Cada Properties será así:
 	// -id: int
 	// -nombre_tipo: String
 	// -num_disponibles: int
    public List<Properties> habsHotel(int hotelID) {
    	List<Map<String, Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		String query = "SELECT th.*,hth.num_disponibles "
					  +"FROM Tipo_Habitacion th "
					  +"JOIN Hotel_Tipo_Habitacion hth ON th.id = hth.tipo_hab_id "
					  +"WHERE hth.hotel_id = ?";

		try (Connection conn = conector.getConn()) {
			resConsulta = runner.query(conn, query, handler,hotelID);

		} catch (Exception e) { e.printStackTrace(); }
		
		List<Properties> res = new ArrayList<>();
		Properties aux;
		for( Map<String,Object> fila : resConsulta ) {
			aux = new Properties();
			aux.put("id", fila.get("id") );
			aux.put("nombre_tipo", fila.get("nombre_tipo") );
			aux.put("num_disponibles", fila.get("num_disponibles"));
			
			res.add(aux);
		}
		return res;
    }
    
    public static void main(String[] args) {
		new TipoHabitacionDAO().habsHotel(1).forEach( p -> System.out.println(p + "\n") );; 
	}
}
