package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.DatosPrecio;
import ingsoft1920.cm.conector.ConectorBBDD;

public class DatosPrecioDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();
	
	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public List<DatosPrecio> datosPrecios() {
		List<DatosPrecio> res = new LinkedList<>();
		BeanListHandler<DatosPrecio> handler = new BeanListHandler<>(DatosPrecio.class);
		String query = "SELECT * FROM Datos_Precio";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler);
			
		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}

	public List<DatosPrecio> getByPeticionID(int peticion_id) {
		List<DatosPrecio> res = new ArrayList<>();
		BeanListHandler<DatosPrecio> handler = new BeanListHandler<>(DatosPrecio.class);
		String query = "SELECT * FROM Datos_Precio WHERE peticion_id = ?";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler, peticion_id);

		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}

	public int anadir(DatosPrecio datosPrecio) {
		BigInteger idGenerado = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Datos_Precio (estado, peticion_id, precio, puntuacion) VALUES (?, ?, ?, ?)";


		try (Connection conn = conector.getConn()) 
		{
			idGenerado = runner.insert(conn, query, handler, 
									   datosPrecio.getEstado(),
									   datosPrecio.getPeticion_id(),
									   datosPrecio.getPrecio(),
									   datosPrecio.getPuntuacion()
									  );
			
		} catch (Exception e) { e.printStackTrace(); }

		return idGenerado != null ? idGenerado.intValue() : -1;
	}

	public void cambiaEstado(int datosPrecioID) {
		String query = "UPDATE Datos_Precio SET estado = TRUE WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			runner.update(conn, query, datosPrecioID);
			
		} catch (Exception e) { e.printStackTrace(); }
	}

}
