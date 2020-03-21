package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Ausencia;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class AusenciaDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	private ConectorBBDD conector = new ConectorBBDD();

	public List<Ausencia> ausencias() {

		BeanListHandler<Ausencia> beanListHandler = new BeanListHandler<>(Ausencia.class);
		QueryRunner runner = new QueryRunner();

		String getAusencias = "SELECT * FROM Ausencia";

		List<Ausencia> ausencias = new LinkedList<>();

		try (Connection conn = conector.getConn()) {
			ausencias = runner.query(conn, getAusencias, beanListHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ausencias;
	}

	public void resultadoAusencia(Ausencia a, Ausencia.Estado resolucion) {

		String cambiaEstado = "UPDATE Ausencia SET estado = ? WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiaEstado, resolucion.name(), a.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Notificamos a em del resultado
		JsonObject json = new JsonObject();
		json.addProperty("id_ausencia", a.getId());
		json.addProperty("resultado", resolucion.name());
		json.addProperty("motivo", a.getMotivo());
		APIout.enviar(json.toString(), 7002, "/resultadoAusencia");

	}

	public static void main(String[] args) {

		AusenciaDAO dao = new AusenciaDAO();
		Ausencia primera = dao.ausencias().get(0);
		dao.resultadoAusencia(primera, Ausencia.Estado.aprobada);

	}

	public int anadir(Ausencia a) {
		BigInteger idGenerado = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Ausencia " + "(id,motivo,fecha_inicio,fecha_fin,estado,empleado_id) "
				+ "VALUES (?,?,?,?,?,?)";

		try (Connection conn = conector.getConn()) {
			idGenerado = runner.insert(conn, query, handler, a.getId(), a.getMotivo(), a.getFecha_inicio(),
					a.getFecha_fin(), a.getEstado().name(), a.getEmpleado_id());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (idGenerado != null ? idGenerado.intValue() : -1);
	}

	public void eliminarAusencia(Ausencia ausencia) {

		String eliminarAusencia = "DELETE FROM Ausencia WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, eliminarAusencia, handler, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cambiarMotivo(Ausencia ausencia, String nuevoMotivo) {

		String cambiarMotivo = "UPDATE Ausencia SET motivo = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiarMotivo, handler, nuevoMotivo, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cambiarFechaInicio(Ausencia ausencia, String nuevaFechaInicio) {

		String cambiaFechaInicio = "UPDATE Ausencia SET fecha_inicio = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiaFechaInicio, handler, nuevaFechaInicio, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cambiarFechaFin(Ausencia ausencia, String nuevaFechaFin) {

		String cambiaFechaFin = "UPDATE Ausencia SET fecha_fin = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiaFechaFin, handler, nuevaFechaFin, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Se entiende que no interesa cambiar el empleado_id de una ausencia.

}
