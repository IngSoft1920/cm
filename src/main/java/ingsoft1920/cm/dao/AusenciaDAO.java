package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.apiout.APIem;
import ingsoft1920.cm.bean.Ausencia;
import ingsoft1920.cm.bean.Producto;
import ingsoft1920.cm.bean.Ausencia.Estado;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class AusenciaDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public List<Ausencia> ausencias() {
		BeanListHandler<Ausencia> beanListHandler = new BeanListHandler<>(Ausencia.class);
		String query = "SELECT * FROM Ausencia";
		List<Ausencia> ausencias = new LinkedList<>();

		try (Connection conn = conector.getConn()) 
		{
			ausencias = runner.query(conn, query, beanListHandler);
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return ausencias;
	}
	
	public List<Ausencia> ausenciasPendientes() {
		BeanListHandler<Ausencia> beanListHandler = new BeanListHandler<>(Ausencia.class);
		String query = "SELECT * FROM Ausencia WHERE Estado = 'pendiente'";
		List<Ausencia> ausencias = new LinkedList<>();

		try (Connection conn = conector.getConn()) 
		{
			ausencias = runner.query(conn, query, beanListHandler);
			
		} catch (Exception e) { e.printStackTrace(); }
		
		return ausencias;
	}

	public void resultadoAusencia(Ausencia a, Ausencia.Estado resolucion) {

		String cambiaEstado = "UPDATE Ausencia SET estado = ? WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			runner.update(conn, cambiaEstado, resolucion.name(), a.getId());
			
		} catch (Exception e) { e.printStackTrace(); }

		// Notificamos a em del resultado
		
		APIem.resultadoAusencia(a.getId(),
								resolucion.name(),
								a.getMotivo());
	}

	public int anadir(Ausencia a) {
		BigInteger idGenerado = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Ausencia " 
					  +"(id,motivo,fecha_inicio,fecha_fin,estado,empleado_id) "
					  +"VALUES (?,?,?,?,?,?)";

		try (Connection conn = conector.getConn()) 
		{
			idGenerado = runner.insert(conn, query, handler,
									   a.getId(),
									   a.getMotivo(),
									   a.getFecha_inicio(),
									   a.getFecha_fin(),
									   a.getEstado().name(),
									   a.getEmpleado_id()
									  );
			
		} catch (Exception e) { e.printStackTrace(); }

		return (idGenerado != null ? idGenerado.intValue() : -1);
	}

	// Not used yet
	public void eliminarAusencia(Ausencia ausencia) {
		String eliminarAusencia = "DELETE FROM Ausencia WHERE id = ?";
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, eliminarAusencia, handler, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Not used yet
	public void cambiarMotivo(Ausencia ausencia, String nuevoMotivo) {

		String cambiarMotivo = "UPDATE Ausencia SET motivo = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiarMotivo, handler, nuevoMotivo, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Not used yet
	public void cambiarFechaInicio(Ausencia ausencia, String nuevaFechaInicio) {

		String cambiaFechaInicio = "UPDATE Ausencia SET fecha_inicio = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiaFechaInicio, handler, nuevaFechaInicio, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Not used yet
	public void cambiarFechaFin(Ausencia ausencia, String nuevaFechaFin) {

		String cambiaFechaFin = "UPDATE Ausencia SET fecha_fin = ? WHERE id = ?";

		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		try (Connection conn = conector.getConn()) {
			runner.update(conn, cambiaFechaFin, handler, nuevaFechaFin, ausencia.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Ausencia getById(int id) {
		Ausencia res=null;
		BeanHandler<Ausencia> handler = new BeanHandler<>(Ausencia.class);
		String query = "SELECT * FROM Ausencia WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler,id);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	

	public static void main(String[] args) {
		
		AusenciaDAO dao = new AusenciaDAO();
		Ausencia primera = new Ausencia(4,"enfermedad",Date.valueOf("2020-02-03"),Date.valueOf("2020-04-03"),Estado.pendiente,1);
		dao.anadir(primera);
	}
	
}
