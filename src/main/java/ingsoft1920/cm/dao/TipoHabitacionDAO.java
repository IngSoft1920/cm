package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Tipo_Habitacion;
import ingsoft1920.cm.conector.ConectorBBDD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();
    private ConectorBBDD conector = new ConectorBBDD();

    public int anadir(Tipo_Habitacion tipoHabitacion){
        String anadirQuery = "INSERT INTO Tipo_Habitacion (nombre) VALUES (?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, anadirQuery, handler, tipoHabitacion.getNombre());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        tipoHabitacion.setId(idGenerado);

        return idGenerado;
    }

    public Tipo_Habitacion get(int id){
        String selectQuery = "SELECT * FROM Tipo_Habitacion WHERE id = ?";

        BeanHandler<Tipo_Habitacion> handler = new BeanHandler<>(Tipo_Habitacion.class);

        Tipo_Habitacion res = null;

        try ( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, selectQuery, handler, id);

        } catch(Exception e) { e.printStackTrace(); }

        return res;
    }

    public List<Tipo_Habitacion> tipos(){
        List<Tipo_Habitacion> res = new ArrayList<>();

        BeanListHandler<Tipo_Habitacion> handler = new BeanListHandler<>(Tipo_Habitacion.class);

        String query = "SELECT * FROM Tipo_Habitacion";

        try (Connection conn = conector.getConn()) {
            res = runner.query(conn, query, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
