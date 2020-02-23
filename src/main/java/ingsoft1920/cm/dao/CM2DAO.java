package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.bean.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CM2DAO {

    private static conectorBBDD conector = new conectorBBDD("localhost:3306", "root", "SUPERGUILLE", "practica1");

    private int anadirHotel (String nombre, String continente, String pais, String ciudad, String direccion){

        String anadirHotel = "INSERT INTO hotel (nombre, continente, pais, ciudad, direccion) VALUES (?,?,?,?,?)";

        if (! conector.isConnected()){
            conector.conectar();
        }

        int id = 0;

        PreparedStatement stmt;
        ResultSet rs = null;
        try {

            stmt = conector.getConn().prepareStatement(anadirHotel);
            stmt.setString(1, nombre);
            stmt.setString(2, continente);
            stmt.setString(3, pais);
            stmt.setString(4, ciudad);
            stmt.setString(5, direccion);

            rs = stmt.executeQuery();

            if (rs.next()){
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void anadirTipo (int id, Habitaciones.Tipo tipo, int num){

        String anadirHotel = "INSERT INTO habitacion (hotel_id, tipo, n_disponibles) VALUES (?,?,?)";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {

            stmt = conector.getConn().prepareStatement(anadirHotel);
            stmt.setInt(1, id);
            stmt.setString(2, tipo.toString());
            stmt.setInt(3, num);

            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void anadirHotel (String nombre, String continente, String pais, String ciudad, String direccion, Map<Habitaciones.Tipo, Integer> habitaciones){

        int id = anadirHotel(nombre, continente, pais, ciudad, direccion);

        for (Habitaciones.Tipo tipo : habitaciones.keySet()) {
            anadirTipo(id, tipo, habitaciones.get(tipo));
        }

        conector.closeConn();
    }

    public void eliminarHotel (int id){

        String eliminarHotel = "DELETE FROM hotel WHERE id = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(eliminarHotel);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editarHotel (int id, String nombre, String continente, String pais, String ciudad, String direccion){

        String editarHotel = "UPDATE hotel SET nombre = ?, continente = ?, pais = ?, ciudad = ?, direccion = ? WHERE id  = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {

            stmt = conector.getConn().prepareStatement(editarHotel);
            stmt.setInt(6, id);
            stmt.setString(1, nombre);
            stmt.setString(2, continente);
            stmt.setString(2, pais);
            stmt.setString(2, ciudad);
            stmt.setString(3, direccion);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editarTipo(int id, Habitaciones.Tipo tipo, int num) {

        String editarHotel = "UPDATE habitacion SET n_disponible = ? WHERE hotel_id  = ? AND = ?";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {

            stmt = conector.getConn().prepareStatement(editarHotel);
            stmt.setInt(2, id);
            stmt.setString(3, tipo.toString());
            stmt.setInt(1, num);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarHotel (int id, String nombre, String continente, String pais, String ciudad, String direccion, Map<Habitaciones.Tipo, Integer> habitaciones){

        editarHotel(id, nombre, continente, pais, ciudad, direccion);

        for (Habitaciones.Tipo tipo : habitaciones.keySet()) {
            editarTipo(id, tipo, habitaciones.get(tipo));
        }

        conector.closeConn();
    }

    public List<Hotel> hoteles(){

        if (! conector.isConnected()) {
            conector.conectar();
        }

        String getHoteles = "SELECT * FROM hotel";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Hotel> hoteles = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getHoteles);
            rs = stmt.executeQuery();

            while (rs.next()){
                hoteles.add(new Hotel(rs.getInt("id"), rs.getString("nombre"), rs.getString("continente"), rs.getString("pais"), rs.getString("ciudad"), rs.getString("direccion")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoteles;

    }
}
