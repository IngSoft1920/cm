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

import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ServicioDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	// Cada Properties nos da info de las profesiones que atienden
	// a dicho servicio:
	// -profesion_id: int
	public int anadir(Servicio s, List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryServ = "INSERT INTO Servicio "
						  +"(nombre) "
						  +"VALUES (?);";

		String queryProfs = "INSERT INTO Servicio_Profesion "
						   +"(servicio_id,profesion_id) "
						   +"VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryServ, handler, s.getNombre());
	
			batch = new ArrayList<>();
			for (Properties prof : info) {
				batch.add(new Object[] { res.intValue(),
										 prof.get("profesion_id") 
									   });
			}
			runner.batch(conn, queryProfs, batch.toArray(new Object[info.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	public Servicio getByID(int servicioID) {
		Servicio res=null;
		BeanHandler<Servicio> handler = new BeanHandler<>(Servicio.class);
		String query = "SELECT * FROM Servicio WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,servicioID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Servicio> servicios() {
		List<Servicio> res = new ArrayList<>();
		BeanListHandler<Servicio> handler = new BeanListHandler<>(Servicio.class);
		String query = "SELECT * FROM Servicio";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}	

}
