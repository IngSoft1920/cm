package ingsoft1920.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ingsoft1920.cm.bean.Habitaciones;
import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Hotel;
import ingsoft1920.cm.model.Reserva;
import ingsoft1920.cm.model.Tipo;

public class ReservaDAO {

    private static conectorBBDD conector = new conectorBBDD();

    /*
    @param day con el formato yyyy-MM-dd
    @return dia siguiente
     */
    private String getNextDay(String day) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dayDate = null;

        try {
            dayDate = dateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dayDate);
        c.add(Calendar.DATE, 1);

        Date nextDay = c.getTime();

        return dateFormat.format(nextDay);
    }

    /**
     * @return ciudades
     */
    public HashSet<String> getCiudades(){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getCiudades = "SELECT hotel.ciudad FROM hotel GROUP BY hotel.ciudad";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        HashSet<String> ciudades = new HashSet<>();

        try {
            stmt = conector.getConn().prepareStatement(getCiudades);
            rs = stmt.executeQuery();

            while (rs.next()){
                ciudades.add(rs.getString("ciudad"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

        return ciudades;
    }

    private Hotel getHotel(int id) {

        String getHotel = "SELECT * FROM hotel WHERE hotel.id = ?";
        Hotel hotel = new Hotel();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conector.getConn().prepareStatement(getHotel);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                hotel.setNombre(rs.getString("nombre")); 
                hotel.setUbicacion(rs.getString("ciudad")); 
                hotel.setId(Integer.parseInt(rs.getString("id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotel;

    }

    public HashSet<Hotel> getHotelesPorUbicacion(String ciudad){

        String getHoteles = (ciudad.compareTo("") != 0) ? "SELECT * FROM hotel WHERE hotel.ciudad = ?" : "SELECT * FROM hotel";

        if (conector.getConn() == null)
            conector.conectar();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        HashSet<Hotel> res = new HashSet<>();

        try {
            stmt = conector.getConn().prepareStatement(getHoteles);

            if (ciudad.compareTo("") != 0){
                stmt.setString(1, ciudad);
            }

            rs = stmt.executeQuery();

            while(rs.next()){
                Hotel hotel = new Hotel();

                hotel.setId(Integer.parseInt(rs.getString("id")));
                hotel.setNombre(rs.getString("nombre"));
                hotel.setUbicacion(rs.getString("ciudad"));
                res.add(hotel);
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

        return res;
    }

    private Map<String, Tipo> getNumHabitaciones(int hotel_id){

        //Hay que saber cuantas habitaciones hay en un hotel de un cierto tipo
        String getNumeroDeHabitaciones =
                "SELECT habitaciones.tipo, habitaciones.total_habitaciones " +
                        "FROM habitaciones " +
                        "WHERE habitaciones.hotel_id = ?";


        Map<String, Tipo> disponibles = new HashMap<>();

        PreparedStatement stmtGetNumHabitaciones = null;
        ResultSet rsGetNumHabitaciones = null;

        //Vemos cuantas habitaciones hay por cada tipo y las almacenamos en disponibles
        try {
            stmtGetNumHabitaciones = conector.getConn().prepareStatement(getNumeroDeHabitaciones);
            stmtGetNumHabitaciones.setInt(1,  hotel_id);
            rsGetNumHabitaciones = stmtGetNumHabitaciones.executeQuery();

            while(rsGetNumHabitaciones.next()){
                disponibles.put(rsGetNumHabitaciones.getString("tipo"), new Tipo(rsGetNumHabitaciones.getString("tipo"), 0, Integer.parseInt(rsGetNumHabitaciones.getString("total_habitaciones"))));
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponibles;
    }

    private double getPrecio(int hotel_id, String fecha, String tipo){
        //Por cada tipo de reserva hay que mirar el precio
        String getPrecio =
                "SELECT precio.valor " +
                        "FROM precio" +
                        "WHERE precio.hotel_id = ? AND precio.fecha = ? AND precio.habitacion_tipo = ?";

        PreparedStatement stmtGetPrecio = null;
        ResultSet rsGetPrecio = null;

        double precio = 0;

        try {
            stmtGetPrecio = conector.getConn().prepareStatement(getPrecio);

            stmtGetPrecio.setInt(1,  hotel_id);
            stmtGetPrecio.setDate(2, java.sql.Date.valueOf(fecha));
            stmtGetPrecio.setString(3, tipo);

            rsGetPrecio = stmtGetPrecio.executeQuery();

            if (rsGetPrecio.next()){
                precio = rsGetPrecio.getDouble("valor");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }

    private HashSet<Tipo> getNumeroHabitacionesDisponibles(int hotel_id, String fecha_inicio, String fecha_fin) {

        if (! conector.isConnected()){
            conector.conectar();
        }

        //Por cada dia hay que ver cuantas habitaciones hay reservadas (por cada tipo)
        String getReservasPorDias =
                "SELECT reserva.habitacion_tipo, count(reserva.tipo) as 'num_reservas' " +
                        "FROM reserva " +
                        "WHERE reserva.hotel_id = ? " +
                        "AND reserva.fecha_inicio <= ? AND reserva.fecha_fin >= ? " +
                        "GROUP BY reserva.habitacion_tipo;";


        String day = fecha_inicio;
        String nextDay = getNextDay(day);


        PreparedStatement stmt = null;

        ResultSet rs = null;

        HashMap<String, Tipo> disponibles = (HashMap<String, Tipo>) getNumHabitaciones(hotel_id);

        try {

            while (nextDay != fecha_fin) {

                stmt = conector.getConn().prepareStatement(getReservasPorDias);

                //Ponemos en los preparedStatements el hotel_id
                stmt.setInt(1, hotel_id);

                stmt.setString(2, day);

                stmt.setString(3, nextDay);

                //Vemos que tipos de habitaciones estan reservadas y cuantas hay
                rs = stmt.executeQuery();
                while (rs.next()) {

                    //Esta el tipo en disponibles...
                    if (disponibles.containsKey(rs.getString("habitacion_tipo"))) {

                        //Si hay mas habitaciones en el hotel de un tipo que de reservas...
                        if (disponibles.get(rs.getString("habitacion_tipo")).getDisponibles() > Integer.parseInt(rs.getString("num_reservas"))) {

                            disponibles.get(rs.getString("habitacion_tipo")).addPrecio(getPrecio(hotel_id, day, rs.getString("habitacion_tipo")));

                        }
                        //Si no hay habitaciones disponibles se borra la entrada de disponibles
                        else {
                            disponibles.remove(rs.getString("habitacion_tipo"));
                        }
                    }
                }

                day = nextDay;
                nextDay = getNextDay(day);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashSet<Tipo> res = new HashSet<>();

        for (String tipoStr: disponibles.keySet()) {
            res.add(disponibles.get(tipoStr));
        }

        return res;
    }

    public HashSet<Reserva> getPrecios(int hotel_id, String ciudad, String fechaInicio, String fechaFin){

        HashSet<Reserva> res = new HashSet<>();

        if (! conector.isConnected()){
            conector.conectar();
        }

        if (hotel_id == -1){
            for (Hotel hotel: getHotelesPorUbicacion(ciudad)) {
                for (Tipo tipo: getNumeroHabitacionesDisponibles(hotel.getId(), fechaInicio, fechaFin)) {
                    Reserva reserva = new Reserva(hotel, tipo);
                    reserva.setFecha_ent(fechaInicio);
                    reserva.setFecha_sal(fechaFin);
                    res.add(reserva);
                }
            }
        }
        else {
            for (Tipo tipo: getNumeroHabitacionesDisponibles(hotel_id, fechaInicio, fechaFin)) {
                res.add(new Reserva(getHotel(hotel_id), tipo));
            }
        }

        conector.closeConn();
        return res;
    }

    public int crearReserva(Reserva reserva, int cliente_id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String crearReserva = "INSERT INTO reserva (fecha_inicio, fecha_fin, importe, hotel_id, habitacion_tipo, cliente_id) VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;

        try {
            stmt = conector.getConn().prepareStatement(crearReserva, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setDate(1, java.sql.Date.valueOf(reserva.getFecha_ent()));
            stmt.setDate(2, java.sql.Date.valueOf(reserva.getFecha_sal()));
            stmt.setDouble(3, reserva.getTipo().getPrecio());
            stmt.setInt(4, reserva.getHotel().getId());
            stmt.setString(5, reserva.getTipo().getTipo());
            stmt.setInt(6, cliente_id);

            stmt.execute();
            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

        return id;
    }

    public int anadirReserva(String fecha_entrada, String fecha_salida, double importe, int hotel_id, Habitaciones.Tipo tipo, int cliente_id){

        Reserva reserva = new Reserva(new Hotel(hotel_id, "", ""), new Tipo(tipo.toString(), importe, 0));

        reserva.setFecha_ent(fecha_entrada);
        reserva.setFecha_sal(fecha_salida);

        return crearReserva(reserva, cliente_id);
    }

    public void cancelarReserva(int id){
        String borrarReserva = "DELETE FROM reserva WHERE id = ?";

        if (!conector.isConnected()){
            conector.conectar();
        }
        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(borrarReserva);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

    }

    public List<ingsoft1920.cm.bean.Reserva> reservasCliente (int cliente_id){

        if (! conector.isConnected()){
            conector.conectar();
        }
        String getReservas = "SELECT * FROM reserva WHERE cliente_id = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        ingsoft1920.cm.bean.Reserva reserva = null;
        List<ingsoft1920.cm.bean.Reserva> reservas = new LinkedList<>();

        try {
            stmt = conector.getConn().prepareStatement(getReservas);
            stmt.setInt(1, cliente_id);

            rs = stmt.executeQuery();

            while (rs.next()){

                reserva = new ingsoft1920.cm.bean.Reserva();

                reserva.setId(rs.getInt("id"));
                reserva.setFecha_entrada(rs.getDate("fecha_inicio"));
                reserva.setFecha_salida(rs.getDate("fecha_fin"));
                reserva.setImporte(rs.getDouble("importe"));
                reserva.setHotel_id(rs.getInt("hotel_id"));
                reserva.setTipo(Habitaciones.Tipo.valueOf(rs.getString("tipo")));
                reserva.setCliente_id(rs.getInt("cliente_id"));

                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

        return reservas;
    }

}