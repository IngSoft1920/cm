package ingsoft1920.cm.dao;

import java.math.BigInteger;	
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
	
	public int anadir(Profesion p) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Profesion "
					  +"(nombre) "
					  +"VALUES (?);";
		
		try (Connection conn = conector.getConn()) 
		{
			res = runner.insert(conn, query, handler, p.getNombre());
			
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
}