package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.model.Disponibles;
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

		return (res != null ? res.intValue() : -1);
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

	public List<Disponibles> disponibles(){

        BeanListHandler<Disponibles> beanListHandler = new BeanListHandler<>(Disponibles.class);
        QueryRunner runner = new QueryRunner();

	    String disponiblesQuery =
                "SELECT Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id, Tipo_Habitacion.nombre_tipo, Hotel_Tipo_Habitacion.num_disponibles, SUM(Precio_Habitacion.precio) AS precio_total\n" +
                        "FROM Hotel_Tipo_Habitacion\n" +
                        "JOIN Precio_Habitacion\n" +
                        "\tON Precio_Habitacion.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND Precio_Habitacion.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Precio_Habitacion.fecha >= @fentrada AND Precio_Habitacion.fecha < @fsalida\n" +
                        "JOIN Tipo_Habitacion\n" +
                        "\tON Hotel_Tipo_Habitacion.tipo_hab_id = Tipo_Habitacion.id\n" +
                        "LEFT JOIN (SELECT Reserva.hotel_id, Reserva.tipo_hab_id, COUNT(*) AS num_reservadas\n" +
                        "\tFROM Reserva\n" +
                        "\tWHERE Reserva.fecha_entrada <= @fsalida AND Reserva.fecha_salida >= @fentrada\n" +
                        "\tGROUP BY Reserva.hotel_id, Reserva.tipo_hab_id) AS reservadas\n" +
                        "ON reservadas.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND reservadas.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Hotel_Tipo_Habitacion.num_disponibles < reservadas.num_reservadas\n" +
                        "WHERE reservadas.hotel_id IS NULL OR reservadas.tipo_hab_id IS NULL\n" +
                        "GROUP BY Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id;";

        List<Disponibles> disponibles = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            disponibles = runner.query(conn, disponiblesQuery, beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return disponibles;

    }

}
