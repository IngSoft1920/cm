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

import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ReservaDAO {

	@Autowired
    private QueryRunner runner = new QueryRunner();
	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();
	
	public int anadir(Reserva r) {		
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Reserva "
					  +"(fecha_entrada,fecha_salida,importe,"
					  +"regimen_comida,numero_acompanantes,hotel_id,"
					  +"cliente_id,tipo_hab_id) "
					  +"VALUES (?,?,?,?,?,?,?,?);";
		
		try( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn, query, handler,
								 r.getFecha_entrada(),
								 r.getFecha_salida(),
								 r.getImporte(),
								 r.getRegimen_comida().toString(),
								 r.getNumero_acompanantes(),
								 r.getHotel_id(),
								 r.getCliente_id(),
								 r.getTipo_hab_id()
								 );
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return ( res != null ? res.intValue() : -1 );
	}
	
	public List<Reserva> reservasDeUnCliente(int cliente_id){
		List<Reserva> res = new ArrayList<>();
		BeanListHandler<Reserva> handler = new BeanListHandler<>(Reserva.class);
		String query = "SELECT * FROM Reserva WHERE cliente_id=?;";
		
		try( Connection conn = conector.getConn() )
		{
			res = runner.query(conn,query,handler,cliente_id);
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
}
