package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.bean.Empleado;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmpleadoDAO {


    @Autowired
    private QueryRunner runner;

    private ConectorBBDD conector = new ConectorBBDD();

    public List<Empleado> getEmpleados() {

        BeanListHandler<Empleado> beanListHandler = new BeanListHandler<>(Empleado.class);
        QueryRunner runner = new QueryRunner();

        String getEmpleados = "SELECT * FROM empleado";

        List<Empleado> empleados = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            empleados = runner.query(conn, getEmpleados, beanListHandler);
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return empleados;
    }

    public void asignarSueldo(int id, int sueldo){

        String asignaSueldo = "UPDATE Empleado SET sueldo = ? WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, asignaSueldo, sueldo, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void add(Empleado empleado){

        String add = "INSERT INTO Empleado (id, nombre, apellidos, email, telefono, sueldo) VALUES (?, ?, ?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, add, handler, empleado.getId(), empleado.getNombre(), empleado.getApellidos(), empleado.getEmail(), empleado.getTelefono(), empleado.getSueldo());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    
    public void delete(Empleado empleado){

        String add = "DELETE FROM Empleado WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.update(conn, add, handler, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    
    public void cambiarEmail(Empleado empleado, String nuevoEmail){

        String add = "UPDATE Empleado SET email = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.update(conn, add, handler, nuevoEmail, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    
    public void cambiarNombre(Empleado empleado, String nuevoNombre){

        String add = "UPDATE Empleado SET nombre = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.update(conn, add, handler, nuevoNombre, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    
    public void cambiarApellidos(Empleado empleado, String nuevosApellidos){

        String add = "UPDATE Empleado SET apellidos = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.update(conn, add, handler, nuevosApellidos, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    

}
