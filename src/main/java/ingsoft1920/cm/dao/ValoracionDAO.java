package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
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
					  +"(cabecera,cuerpo,nota,cliente_id,hotel_id) "
					  +"VALUES (?,?,?,?,?);";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn,query,handler,
							    v.getCabecera(),
							    v.getCuerpo(),
							    v.getNota(),
							    v.getCliente_id(),
							    v.getHotel_id()
							   );
			
		} catch( Exception e ) { e.printStackTrace(); }
		
		return res != null ? res.intValue() : -1;
	}
	
}