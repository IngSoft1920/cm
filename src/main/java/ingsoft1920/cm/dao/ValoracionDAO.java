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


import ingsoft1920.cm.bean.Valoracion;
import ingsoft1920.cm.conector.ConectorBBDD;


@Component
public class ValoracionDAO {
	@Autowired
    private QueryRunner runner = new QueryRunner();
	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();
	
	public int anadir(Valoracion v) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Valoracion "
					  +"(cabecera,cuerpo,nota,"
					  +"hotel_id,cliente_id) "
					  +"VALUES (?,?,?,?,?);";
		
		try( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn, query, handler,
								 v.getCabecera(),
								 v.getCuerpo(),
								 v.getNota(),
								 v.getHotel_id(),
								 v.getCliente_id()
								 );
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return ( res != null ? res.intValue() : -1 );
	}
	
	public Valoracion getByID(int id) {
		Valoracion res=null;
		BeanHandler<Valoracion> handler = new BeanHandler<>(Valoracion.class);
		String query = "SELECT * FROM Valoracion WHERE id = ?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<Valoracion> valoraciones() {
		List<Valoracion> res = new ArrayList<>();
		BeanListHandler<Valoracion> handler = new BeanListHandler<>(Valoracion.class);
		String query = "SELECT * FROM Valoracion";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
