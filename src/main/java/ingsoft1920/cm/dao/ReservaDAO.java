package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.bean.Reserva.Regimen;
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
		String query = "INSERT INTO Reserva " + "(fecha_entrada,fecha_salida,importe,"
				+ "regimen_comida,numero_acompanantes,hotel_id," + "cliente_id,tipo_hab_id) "
				+ "VALUES (?,?,?,?,?,?,?,?);";

		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, query, handler, r.getFecha_entrada(), r.getFecha_salida(), r.getImporte(),
					r.getRegimen_comida().toString(), r.getNumero_acompanantes(), r.getHotel_id(), r.getCliente_id(),
					r.getTipo_hab_id());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Mandamos la reserva a dho
		if( res != null ) {
			
			JsonObject json = new JsonObject();
			  json.addProperty("reserva_id",res.intValue());
			  json.addProperty("fecha_entrada",r.getFecha_entrada().toString());
			  json.addProperty("fecha_salida",r.getFecha_salida().toString());
			  json.addProperty("importe",r.getImporte());
			  json.addProperty("cliente_id",r.getCliente_id());
			  json.addProperty("numero_acompanantes",r.getNumero_acompanantes());
			  json.addProperty("hotel_id", r.getHotel_id());
			  json.addProperty("tipo_hab_id", r.getTipo_hab_id());
			  
			APIout.enviar(json.toString(),7001, "/recibirReserva");
		}
		

		return (res != null ? res.intValue() : -1);
	}
	
	public static void main(String[] args) {
		ReservaDAO dao = new ReservaDAO();
		Reserva r = new Reserva(-1,Date.valueOf("2020-10-01"),Date.valueOf("2020-10-10"), 1000, Regimen.media_pension, 3, 1, 1, 1);
	
		dao.anadir(r);
	}

	public List<Reserva> reservasDeUnCliente(int cliente_id) {
		List<Reserva> res = new ArrayList<>();
		BeanListHandler<Reserva> handler = new BeanListHandler<>(Reserva.class);
		String query = "SELECT * FROM Reserva WHERE cliente_id=?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, cliente_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public void eliminarReserva(int reserva_id) {
		String query = "DELETE FROM Reserva WHERE id = ?;";
		
		try ( Connection conn = conector.getConn() )
		{
			runner.update(conn, query, reserva_id);
			
		} catch(Exception e) { e.printStackTrace(); }
	}

	public Cliente getCliente(int reserva_id){

	    Cliente res = null;

        BeanHandler<Cliente> handler = new BeanHandler<>(Cliente.class);

        String query = "SELECT Cliente.* " +
                       "FROM (SELECT Reserva.cliente_id " +
                       "      FROM Reserva " +
                       "      WHERE Reserva.id = ?) AS La_Reserva " +
                       "JOIN Cliente " +
                       "ON Cliente.id = La_Reserva.cliente_id ";

        try ( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, query, handler, reserva_id);

        } catch(Exception e) { e.printStackTrace(); }

        return res;
    }
}
