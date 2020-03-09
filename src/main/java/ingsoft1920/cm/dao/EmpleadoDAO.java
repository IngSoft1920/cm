package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Profesion;
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

    public void anadirEmpleado(Empleado empleado){

        String anadeEmpleado = "INSERT INTO Empleado (nombre, apellidos, email,"
        		+ " telefono, sueldo, profesion) VALUES (?, ?, ?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try( Connection conn = conector.getConn() )
        {
            idGenerado = runner.insert(conn, anadeEmpleado, handler,
            		empleado.getNombre(), empleado.getApellidos(), empleado.getEmail(),
            		empleado.getTelefono(), empleado.getSueldo(), empleado.getProfesion_id());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        empleado.setId(idGenerado);
    }
    
    public void eliminarEmpleado(Empleado empleado){

        String eliminaEmpleado = "DELETE FROM Empleado WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, eliminaEmpleado, handler, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarEmail(Empleado empleado, String nuevoEmail){

        String cambiaEmail = "UPDATE Empleado SET email = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaEmail, handler, nuevoEmail, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarNombre(Empleado empleado, String nuevoNombre){

        String cambiaNombre = "UPDATE Empleado SET nombre = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaNombre, handler, nuevoNombre, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarApellidos(Empleado empleado, String nuevosApellidos){

        String cambiaApellidos = "UPDATE Empleado SET apellidos = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, cambiaApellidos, handler, nuevosApellidos,
            		empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambiarProfesion(Empleado empleado, Profesion nuevaProfesion){

        String add = "UPDATE Empleado SET profesion_id = ? WHERE email = ?";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, add, handler, nuevaProfesion, empleado.getEmail());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    

}
