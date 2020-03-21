	package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.bean.Peticion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PeticionDAO {


    @Autowired
    private QueryRunner runner = new QueryRunner();
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
    	
        String add = "INSERT INTO Peticion (ciudad, estado, fecha_CI, fecha_CO, tipo_hab_id) VALUES (?, ?, ?, ?, ?)";

        ScalarHandler<BigInteger> handler = new ScalarHandler<>();

        BigInteger idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, add, handler, peticion.getCiudad(), peticion.getEstado(), peticion.getFecha_CI(), peticion.getFecha_CO(), peticion.getTipo_hab_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        peticion.setId(idGenerado.intValue());

        return idGenerado.intValue();
    }

}
