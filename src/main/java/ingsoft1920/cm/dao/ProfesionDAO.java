package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.bean.Servicio_Profesion;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ProfesionDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();


	
	public int anadir(Profesion s, List<Servicio_Profesion> servicios) {
		// Primero añadimos el hotel mismamente
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String queryH = "INSERT INTO Profesion " + "(nombre) "
				+ "VALUES (?);";
		
		String querySer = "INSERT INTO Servicio_Profesion " + "(profesion_id,servicio_id) " + "VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryH, handler, s.getNombre());
			
			batch = new ArrayList<>();
			for (Servicio_Profesion ser : servicios) {
				batch.add(new Object[] { res.intValue(), ser.getServicio_id() });
			}
			runner.batch(conn, querySer, batch.toArray(new Object[servicios.size()][]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (res != null ? res.intValue() : -1);
	}

	public List<Profesion> Profesions() {
		List<Profesion> res = new ArrayList<>();
		BeanListHandler<Profesion> handler = new BeanListHandler<>(Profesion.class);
		String query = "SELECT * FROM Servcio";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}