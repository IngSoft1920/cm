package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.Profesion;
import ingsoft1920.cm.conector.ConectorBBDD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    @Autowired
    private QueryRunner runner = new QueryRunner();
    private ConectorBBDD conector = new ConectorBBDD();

    public int anadir(Categoria categoria){

        String anadirQuery = "INSERT INTO Categoria (nombre) VALUES (?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, anadirQuery, handler, categoria.getNombre());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        categoria.setId(idGenerado);

        return idGenerado;
    }

    public Categoria get(int id){

        String selectQuery = "SELECT * FROM Categoria WHERE id = ?";

        BeanHandler<Categoria> handler = new BeanHandler<>(Categoria.class);

        Categoria res = null;

        try ( Connection conn = conector.getConn() )
        {
            res = runner.query(conn, selectQuery, handler, id);

        } catch(Exception e) { e.printStackTrace(); }

        return res;
    }

    public List<Categoria> categorias(){
        List<Categoria> res = new ArrayList<>();

        BeanListHandler<Categoria> handler = new BeanListHandler<>(Categoria.class);

        String query = "SELECT * FROM Categoria";

        try (Connection conn = conector.getConn()) {
            res = runner.query(conn, query, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
