package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.conector.conectorBBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProveedoresDAO {

    private static conectorBBDD conector = new conectorBBDD("8000", "cm1", "ingSoft20cm1.711", "piedrafita.ls.fi.upm.es");

    private int anadirProveedor(String empresa, String producto){

        String anadirEmpleado = "INSERT INTO empleado (empresa, producto) VALUES (?,?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        int id = -1;

        try {
            stmt = conector.getConn().prepareStatement(anadirEmpleado);

            stmt.setString(1, empresa);
            stmt.setString(2, producto);

            rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void anadirProveedorHotel (int hotel_id, int proveedor_id){

        String anadirEmpleadoHotel = "INSERT INTO proveedor_hotel (hotel_id, proveedor_id) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(anadirEmpleadoHotel);

            stmt.setInt(1, hotel_id);
            stmt.setInt(2, proveedor_id);

            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void anadirProveedor(String empresa, String producto, int hotel_id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        anadirProveedorHotel(hotel_id, anadirProveedor(empresa, producto));

        conector.closeConn();
    }

    private void borrarProveedorProveedor(int id){
        String borrarEmpleadoEmpleado = "DELETE FROM proveedor WHERE id = ?";

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

    private void borrarProveedorHotel (int id){
        String borrarEmpleadoEmpleado = "DELETE FROM hotel_proveedor WHERE id = ?";

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

    public void borrarProveedor(int id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        borrarProveedorProveedor(id);
        borrarProveedorHotel(id);

        conector.closeConn();
    }

    public List<Proveedor> proveedores() {

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getEmpleados = "SELECT * FROM proveedor";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Proveedor> proveedores = new LinkedList<>();
        Proveedor proveedor;

        try {
            stmt = conector.getConn().prepareStatement(getEmpleados);
            rs = stmt.executeQuery();

            while (rs.next()){
                proveedor = new Proveedor(rs.getInt("id"), rs.getString("empresa"), rs.getString("producto"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    public List<Proveedor> proveedoresDeUnHotel(int hotel_id){

        String getEmpleadosDeUnHotel = "SELECT proveedor.* " +
                "FROM (SELECT * " +
                "FROM hotel_proveedor " +
                "WHERE hotel_id = ?) as ids_proveedores " +
                "JOIN proveedor " +
                "ON ids_proveedores.proveedor_id = proveedor.id";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Proveedor> proveedores = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getEmpleadosDeUnHotel);
            stmt.setInt(1, hotel_id);

            rs = stmt.executeQuery();

            while (rs.next()){

                proveedores.add(new Proveedor(rs.getInt("id"), rs.getString("empresa"), rs.getString("producto")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

}
