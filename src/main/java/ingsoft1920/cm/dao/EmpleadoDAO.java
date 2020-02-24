package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.conector.conectorBBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmpleadoDAO {

    private static conectorBBDD conector = new conectorBBDD("8000", "cm1", "ingSoft20ge1.711", "piedrafita.ls.fi.upm.es");

    private int anadirEmpleadoEmpleado(String nombre, String apellidos, String email, String telefono, String ocupacion){

        String anadirEmpleado = "INSERT INTO empleado (nombre, apellidos, email, telefono, ocupacion) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        int id = -1;

        try {
            stmt = conector.getConn().prepareStatement(anadirEmpleado);

            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            stmt.setString(5, ocupacion);

            rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void anadirEmpleadoHotel (int hotel_id, int empleado_id){

        String anadirEmpleadoHotel = "INSERT INTO hotel_empleado (hotel_id, empleado_id) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(anadirEmpleadoHotel);

            stmt.setInt(1, hotel_id);
            stmt.setInt(2, empleado_id);

            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void anadirEmpleado(String nombre, String apellidos, String email, String telefono, String ocupacion, int hotel_id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        anadirEmpleadoHotel(hotel_id, anadirEmpleadoEmpleado(nombre, apellidos, email, telefono, ocupacion));

        conector.closeConn();
    }

    private void borrarEmpleadoEmpleado(int id){
        String borrarEmpleadoEmpleado = "DELETE FROM empleado WHERE id = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(borrarEmpleadoEmpleado);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void borrarEmpleadoHotel (int id){
        String borrarEmpleadoEmpleado = "DELETE FROM hotel_empleado WHERE id = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(borrarEmpleadoEmpleado);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarEmpleado(int id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        borrarEmpleadoEmpleado(id);
        borrarEmpleadoHotel(id);

        conector.closeConn();
    }

    public List<Empleado> empleados() {

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getEmpleados = "SELECT * FROM empleado";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Empleado> empleados = new LinkedList<>();
        Empleado empleado;

        try {
            stmt = conector.getConn().prepareStatement(getEmpleados);
            rs = stmt.executeQuery();

            while (rs.next()){
                empleado = new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("telefono"), rs.getString("ocupacion"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public List<Empleado> empleadosDeUnHotel(int hotel_id){
        String getEmpleadosDeUnHotel = "SELECT empleado.* " +
                                        "FROM (SELECT * " +
                                                "FROM hotel_empleado" +
                                                "WHERE hotel_id = ?) as ids_empleados " +
                                        "JOIN empleado " +
                                        "ON ids_empleados.empleado_id = empleado.id";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Empleado> empleados = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getEmpleadosDeUnHotel);
            stmt.setInt(1, hotel_id);

            rs = stmt.executeQuery();

            while (rs.next()){
                Empleado empleado = new Empleado();

                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidos(rs.getString("apellidos"));
                empleado.setEmail(rs.getString("email"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setOcupacion(rs.getString("ocupacion"));

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

}