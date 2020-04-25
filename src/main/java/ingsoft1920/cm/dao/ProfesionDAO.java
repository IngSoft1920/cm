package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ProfesionDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();
	
	// Cada Properties nos da info de las profesiones que atienden
	// a dicho servicio:
	// -servicio_id: int
	public int anadir(Profesion p, List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String queryProf = "INSERT INTO Profesion "
					  	  +"(nombre) "
					  	  +"VALUES (?);";
		
		String queryServs = "INSERT INTO Servicio_Profesion "
				   		   +"(profesion_id,servicio_id) "
				   		   +"VALUES (?,?)";
		
		List<Object[]> batch;
		try (Connection conn = conector.getConn()) 
		{
			res = runner.insert(conn, queryProf, handler, p.getNombre());
			
			batch = new ArrayList<>();
			for (Properties serv : info) {
				batch.add(new Object[] { res.intValue(),
										 serv.get("servicio_id") 
									   });
			}
			runner.batch(conn,queryServs,batch.toArray(new Object[info.size()][]));
			
		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}

	public List<Profesion> profesiones() {
		List<Profesion> res = new ArrayList<>();
		BeanListHandler<Profesion> handler = new BeanListHandler<>(Profesion.class);
		String query = "SELECT * FROM Profesion";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		return res;
	}
	
	public Profesion getByID(int profesion_id) {
		Profesion res = null;
		BeanHandler<Profesion> handler = new BeanHandler<>(Profesion.class);
		String query = "SELECT * FROM Profesion WHERE id = ?";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.query(conn, query, handler, profesion_id);
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	// Son profesiones que no están enlazadas con servicios, estilo
	// guardia de seguridad, recepcionista, limpieza, etc
	public List<Profesion> profesionesNoDependientesDeServicio() {
		List<Profesion> res = new ArrayList<Profesion>();
		BeanListHandler<Profesion> handler = new BeanListHandler<>(Profesion.class);
		String query = "SELECT * "
					  +"FROM Profesion "
					  +"WHERE id NOT IN "
					  +"(SELECT p.id "
					  +"FROM Profesion p "
					  +"JOIN Servicio_Profesion sp ON p.id = sp.profesion_id );";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.query(conn, query, handler);
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	// Es la combinación de las profesiones no dependientes de servicios más
	// las dependientes de los servicios del hotel en cuestión
	public List<Profesion> profesionesHotel(int hotelId) {
		List<Profesion> res = new ArrayList<>();
		BeanListHandler<Profesion> handler = new BeanListHandler<>(Profesion.class);
		String queryProfsDependientes = 
					 "SELECT DISTINCT p.* "
					+"FROM Profesion p "
				    +"JOIN Servicio_Profesion sp ON p.id = sp.profesion_id "
				    +"JOIN Servicio s ON sp.servicio_id=s.id "
				    +"JOIN Hotel_Servicio hs ON s.id=hs.servicio_id ";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.query(conn, queryProfsDependientes, handler);
			
		} catch(Exception e) { e.printStackTrace(); }
		
		// Concatenamos con las profesiones no dependientes:
		res.addAll( profesionesNoDependientesDeServicio() );
		
		return res;
	}
	
	
	
	
	
	
	
	
}