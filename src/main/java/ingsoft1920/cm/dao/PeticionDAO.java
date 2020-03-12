package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.bean.Peticion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PeticionDAO {


    @Autowired
    private QueryRunner runner;
    private ConectorBBDD conector = new ConectorBBDD();

    public List<Peticion> getPeticiones() {

        BeanListHandler<Peticion> beanListHandler = new BeanListHandler<>(Peticion.class);
        QueryRunner runner = new QueryRunner();

        String getPeticiones = "SELECT * FROM Peticion";

        List<Peticion> peticiones = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            peticiones = runner.query(conn, getPeticiones, beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return peticiones;
    }

    public void cambiaEstado(int id){

        String cambiaEstado = "UPDATE Peticion SET estado = TRUE WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEstado, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public int add(Peticion peticion){

        String add = "INSERT INTO Peticion (ciudad, estado, ano_CI, mes_CI, dia_CI, ano_CO, mes_CO, dia_CO, tipo_hab_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, add, handler, peticion.getCiudad(), peticion.getEstado(), peticion.getAnoCI(), peticion.getMesCI(), peticion.getDiaCI(), peticion.getAnoCO(), peticion.getMesCO(), peticion.getDiaCO(), peticion.getTipo_hab_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        peticion.setId(idGenerado);

        return idGenerado;
    }

}
