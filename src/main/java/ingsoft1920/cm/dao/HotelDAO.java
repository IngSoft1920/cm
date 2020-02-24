package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.bean.Precio;
import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.bean.Hotel;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HotelDAO {

    private static conectorBBDD conector = new conectorBBDD();

    private int anadirHotel (String nombre, String continente, String pais, String ciudad, String direccion){

        String anadirHotel = "INSERT INTO hotel (nombre, continente, pais, ciudad, direccion) VALUES (?,?,?,?,?)";

        if (! conector.isConnected()){
            conector.conectar();
        }

        int id = 0;

        PreparedStatement stmt;
        ResultSet rs = null;
        try {

            stmt = conector.getConn().prepareStatement(anadirHotel, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nombre);
            stmt.setString(2, continente);
            stmt.setString(3, pais);
            stmt.setString(4, ciudad);
            stmt.setString(5, direccion);

            stmt.execute();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

            if (rs.next()){
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void anadirTipo (int id, Habitaciones.Tipo tipo, int num){

        String anadirHotel = "INSERT INTO habitacion (hotel_id, tipo, total_habitaciones) VALUES (?,?,?)";

        if (! conector.isConnected()){
            conector.conectar();
        }

        PreparedStatement stmt;

        try {

            stmt = conector.getConn().prepareStatement(anadirHotel);
            stmt.setInt(1, id);
            stmt.setString(2, tipo.toString());
            stmt.setInt(3, num);

            stmt.executeUpdate();

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
            stmt.setString(3, pais);
            stmt.setString(4, ciudad);
            stmt.setString(5, direccion);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editarTipo(int id, Habitaciones.Tipo tipo, int num) {

        String editarHotel = "UPDATE habitacion SET tipo = ? WHERE hotel_id  = ? AND total_habitaciones = ?";

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

    public List<Factura> facturacionHotel (int hotel_id){

        if (! conector.isConnected()) {
            conector.conectar();
        }

        String getFacturas = "SELECT factura.*\n" +
                "FROM (SELECT * \n" +
                "        FROM reserva\n" +
                "        WHERE reserva.hotel_id = ?) AS reservas_hotel\n" +
                "INNER JOIN factura \n" +
                "ON reservas_hotel.cliente_id = factura.cliente_id\n" +
                "WHERE NOT pagado";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> facturas = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getFacturas);
            stmt.setInt(1, hotel_id);
            rs = stmt.executeQuery();

            while (rs.next()){
                facturas.add(new Factura(rs.getInt("id"), rs.getInt("importe"), rs.getBoolean("pagado"), rs.getString("descripcion"), rs.getDate("fecha_factura"), rs.getInt("cliente_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facturas;
    }

    private Map<Hotel, List<Precio>> getHotelesPorUbicacion (String continente, String pais, String ciudad){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getHotelesPorUbicacion = "SELECT *  " +
                "FROM hotel " +
                "WHERE hotel.continente = ? AND hotel.pais = ? AND hotel.ciudad = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<Hotel, List<Precio>> hoteles = new HashMap<>();

        try {
            stmt = conector.getConn().prepareStatement(getHotelesPorUbicacion);
            stmt.setString(1, continente);
            stmt.setString(1, pais);
            stmt.setString(3, ciudad);

            rs = stmt.executeQuery();
            while (rs.next()){
                Hotel hotel = new Hotel(rs.getInt("id"), rs.getString("nombre"), rs.getString("continente"), rs.getString("pais"), rs.getString("ciudad"), rs.getString("direccion"));
                hoteles.put(hotel, new LinkedList<>());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hoteles;
    }

    private List<Precio> getPreciosPorFechas (int hotel_id, Date fecha_entrada, Date fecha_salida){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getPreciosPorFechas = "SELECT *\n" +
                                    "FROM precio \n" +
                                    "WHERE precio.hotel_id = ? AND ? <= precio.fecha AND ? > precio.fecha";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Precio> precios = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getPreciosPorFechas);
            stmt.setInt(1, hotel_id);
            stmt.setDate(2, fecha_entrada);
            stmt.setDate(3, fecha_salida);

            rs = stmt.executeQuery();

            while (rs.next()){
                Precio precio = new Precio(rs.getInt("hotel_id"), Habitaciones.Tipo.valueOf(rs.getString("habitaciones_tipo")), rs.getDate("fecha"), rs.getDouble("precio"));
                precios.add(precio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return precios;
    }

    public Map<Hotel, List<Precio>> hotelesPorReserva(String continente, String pais, String ciudad, Date fecha_entrada, Date fecha_salida){

        Map<Hotel, List<Precio>> hoteles = getHotelesPorUbicacion(continente, pais, ciudad);

        for (Hotel hotel: hoteles.keySet()) {
            hoteles.get(hotel).addAll(getPreciosPorFechas(hotel.getId(), fecha_entrada, fecha_salida));
        }

        return hoteles;
    }
}
