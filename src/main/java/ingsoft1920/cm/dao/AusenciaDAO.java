package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.bean.Ausencia;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AusenciaDAO {


    @Autowired
    private QueryRunner runner;

    private ConectorBBDD conector = new ConectorBBDD();

    public List<Ausencia> getAusencias() {

        BeanListHandler<Ausencia> beanListHandler = new BeanListHandler<>(Ausencia.class);
        QueryRunner runner = new QueryRunner();

        String getAusencias = "SELECT * FROM ausencia";

        List<Ausencia> ausencias = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            ausencias = runner.query(conn, getAusencias, beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return ausencias;
    }

    public void cambiarEstadoAusencia(int id, int estado){

        String cambiaEstado = "UPDATE Ausencia SET estado = ? WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEstado, estado, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int anadirAusencia( Ausencia ausencia){

        String anadirAusencia = "INSERT INTO Ausencia (motivo, fecha_inicio, fecha_fin, estado,"
        		+ " empleado_id) VALUES ( ?, ?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, anadirAusencia, handler, ausencia.getMotivo(),
            		ausencia.getFecha_inicio(), ausencia.getFecha_fin(),ausencia.getEstado(),ausencia.getEmpleado_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return idGenerado;
    }
    
    public void eliminarAusencia(Ausencia ausencia){

        String eliminarAusencia = "DELETE FROM Ausencia WHERE id = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, eliminarAusencia, handler, ausencia.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public void cambiarMotivo(Ausencia ausencia, String nuevoMotivo){

        String cambiarMotivo = "UPDATE Ausencia SET motivo = ? WHERE id = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiarMotivo, handler, nuevoMotivo, ausencia.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarFechaInicio(Ausencia ausencia, String nuevaFechaInicio){

        String cambiaFechaInicio = "UPDATE Ausencia SET fecha_inicio = ? WHERE id = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaFechaInicio, handler, nuevaFechaInicio, ausencia.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    public void cambiarFechaFin(Ausencia ausencia, String nuevaFechaFin){

        String cambiaFechaFin = "UPDATE Ausencia SET fecha_fin = ? WHERE id = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaFechaFin, handler, nuevaFechaFin, ausencia.getId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    
    // Se entiende que no interesa cambiar el empleado_id de una ausencia.

}
