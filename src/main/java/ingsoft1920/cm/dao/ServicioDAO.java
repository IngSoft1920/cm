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

import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.Servicio_Profesion;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ServicioDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Servicio s, List<Servicio_Profesion> profesiones) {
		// Primero añadimos el hotel mismamente
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryS = "INSERT INTO Servicio " + "(nombre) " + "VALUES (?);";

		String queryPro = "INSERT INTO Servicio_Profesion " + "(profesion_id,servicio_id) " + "VALUES (?,?)";

		List<Object[]> batch;
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryS, handler, s.getNombre());
	
			batch = new ArrayList<>();
			for (Servicio_Profesion pro : profesiones) {
				batch.add(new Object[] { res.intValue(), pro.getProfesion_id() });
			}
			runner.batch(conn, queryPro, batch.toArray(new Object[profesiones.size()][]));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (res != null ? res.intValue() : -1);
	}

	public List<Servicio> servicios() {
		List<Servicio> res = new ArrayList<>();
		BeanListHandler<Servicio> handler = new BeanListHandler<>(Servicio.class);
		String query = "SELECT * FROM Servcio";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

}
