package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.DatosPrecio;
import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.conector.ConectorBBDD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class DatosPrecioDAO {

    @Autowired
    private QueryRunner runner;

    private static ConectorBBDD conector = new ConectorBBDD();

    public void cambiaEstado(int id){

        String cambiaEstado = "UPDATE Datos_Precio SET estado = TRUE WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEstado, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public List<DatosPrecio> get(int idPeticion){

        BeanListHandler<DatosPrecio> beanListHandler = new BeanListHandler<>(DatosPrecio.class);
        QueryRunner runner = new QueryRunner();

        String getDatosPrecio = "SELECT * FROM Datos_Precio WHERE peticion_id = ?";

        List<DatosPrecio> datosPrecios = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            datosPrecios = runner.query(conn, getDatosPrecio, beanListHandler, idPeticion);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return datosPrecios;
    }

    public void add(DatosPrecio datosPrecio){

        String add = "INSERT INTO Datos_Precio (precio, puntuacion, estado, peticion_id) VALUES (?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, add, handler, datosPrecio.getPrecio(), datosPrecio.getPuntuacion(), datosPrecio.getEstado(), datosPrecio.getPeticion_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        datosPrecio.setId(idGenerado);
    }

}
