package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.DatosPrecio;
import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.conector.ConectorBBDD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class DatosPrecioDAO {

    @Autowired
    private QueryRunner runner;
    private ConectorBBDD conector = new ConectorBBDD();

    public List<DatosPrecio> datosPrecios(){

        BeanListHandler<DatosPrecio> beanListHandler = new BeanListHandler<>(DatosPrecio.class);
        QueryRunner runner = new QueryRunner();

        String getDatos = "SELECT * FROM Datos_Precio";

        List<DatosPrecio> datosPrecio = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            datosPrecio = runner.query(conn, getDatos, beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return datosPrecio;
    }

    public DatosPrecio get(int peticion_id){

        String selectQuery = "SELECT * FROM Datos_Precio WHERE peticion_id = ?";

        BeanHandler<DatosPrecio> handler = new BeanHandler<>(DatosPrecio.class);

        DatosPrecio res = null;

        try ( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, selectQuery, handler, peticion_id);

        } catch(Exception e) { e.printStackTrace(); }

        return res;
    }

    public int add(DatosPrecio datosPrecio){

        String add = "INSERT INTO Datos_Precio (ciudad, estado, fecha, peticion_id) VALUES (?, ?, ?, ?)";
        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, add, handler, datosPrecio.getCiudad(), datosPrecio.getEstado(), datosPrecio.getFecha(), datosPrecio.getPeticion_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        datosPrecio.setId(idGenerado);

        return idGenerado;
    }

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

}
