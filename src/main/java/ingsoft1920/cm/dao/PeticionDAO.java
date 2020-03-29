	package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Peticion;
import ingsoft1920.cm.conector.ConectorBBDD;

public class PeticionDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();
    
    @Autowired
    private ConectorBBDD conector = new ConectorBBDD();

    public List<Peticion> peticiones() {
        List<Peticion> res = new LinkedList<>();
        BeanListHandler<Peticion> beanListHandler = new BeanListHandler<>(Peticion.class);
        String query = "SELECT * FROM Peticion";

        try( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, query, beanListHandler);
            
        }
        catch(Exception e) { e.printStackTrace(); }

        return res;
    }

    public void cambiaEstado(int id){
        String query = "UPDATE Peticion SET estado = TRUE WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, query, id);
            
        }
        catch(Exception e) { e.printStackTrace(); }
    }


    public int anadir(Peticion peticion){
        BigInteger idGenerado = null;
        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        String query = "INSERT INTO Peticion "
        			  +"(ciudad, estado, fecha_CI, fecha_CO, tipo_hab_id) "
        			  +"VALUES (?, ?, ?, ?, ?)";

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, query, handler,
            							peticion.getCiudad(),
            							peticion.getEstado(),
            							peticion.getFecha_CI(),
            							peticion.getFecha_CO(),
            							peticion.getTipo_hab_id()
            						   );
        }
        catch(Exception e) { e.printStackTrace(); }

        return idGenerado.intValue();
    }

}
