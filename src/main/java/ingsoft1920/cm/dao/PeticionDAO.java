package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.bean.Peticion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PeticionDAO {


    @Autowired
    private QueryRunner runner;

    private static conectorBBDD conector = new conectorBBDD();

    public List<Peticion> getPeticiones() {

        BeanListHandler<Peticion> beanListHandler = new BeanListHandler<>(Peticion.class);
        QueryRunner runner = new QueryRunner();

        String getPeticiones = "SELECT * FROM peticiones";

        List<Peticion> peticiones = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            peticiones = runner.query(conn, "SELECT * FROM peticion", beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        conector.closeConn();

        return peticiones;
    }

    public void cambiaEstado(int id){

        String cambiaEstado = "UPDATE Peticiones SET estado = TRUE WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEstado, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        conector.closeConn();
    }
}
