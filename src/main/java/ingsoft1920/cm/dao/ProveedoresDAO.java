package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.conector.conectorBBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProveedoresDAO {

    private static conectorBBDD conector = new conectorBBDD();

    private int anadirProveedor(String empresa, String producto){

        String anadirProveedor = "INSERT INTO proveedor (empresa, producto) VALUES (?,?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        int id = -1;

        try {
            stmt = conector.getConn().prepareStatement(anadirProveedor,Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, empresa);
            stmt.setString(2, producto);

            stmt.execute();
            rs = stmt.getGeneratedKeys();

            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void anadirProveedorHotel (int hotel_id, int proveedor_id){

        String anadirProveedorHotel = "INSERT INTO hotel_proveedor (hotel_id, proveedores_id) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(anadirProveedorHotel);

            stmt.setInt(1, hotel_id);
            stmt.setInt(2, proveedor_id);

            stmt.execute();

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

        String borrarProveedorHotel = "DELETE FROM hotel_proveedor WHERE proveedores_id = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(borrarProveedorHotel);
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

    	 if (! conector.isConnected()){
             conector.conectar();
         }

        String getEmpleadosDeUnHotel = "SELECT proveedor.* "+
			   	   "FROM proveedor "+
			   	   "JOIN hotel_proveedor ON proveedor.id=hotel_proveedor.proveedores_id "+
			   	   "WHERE hotel_proveedor.hotel_id=?";
        
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
