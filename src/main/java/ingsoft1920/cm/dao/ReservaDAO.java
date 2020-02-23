package ingsoft1920.cm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Hotel;
import ingsoft1920.cm.model.Reserva;
import ingsoft1920.cm.model.Tipo;

public class ReservaDAO {

    private static conectorBBDD conector = new conectorBBDD("localhost:3306", "root", "SUPERGUILLE", "practica1");

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

    public HashSet<String> getCiudades(){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getCiudades = "SELECT hotel.ciudad FROM hotel GROUP BY hotel.ciudad";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        HashSet<String> ciudades = new HashSet<String>();

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

        String getHotel = "SELECT * FROM hotel WHERE hotel.id = " + id;
        Hotel hotel = new Hotel();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conector.getConn().prepareStatement(getHotel);
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

    //TODO: PREPARED STATEMENT
    public HashSet<Hotel> getHotelesPorUbicacion(String ciudad){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String getHoteles = (ciudad.compareTo("") == 0) ? "SELECT * FROM hotel WHERE hotel.ciudad = " + ciudad : "SELECT * FROM hotel";

        if (conector.getConn() == null)
            conector.conectar();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        HashSet<Hotel> res = new HashSet<Hotel>();

        try {
            stmt = conector.getConn().prepareStatement(getHoteles);

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

    private HashSet<Tipo> getNumeroHabitacionesDisponibles(int hotel_id, String fecha_inicio, String fecha_fin) {

        if (! conector.isConnected()){
            conector.conectar();
        }

        //Por cada dia hay que ver cuantas habitaciones hay reservadas (por cada tipo)
        String getReservasPorDias =
                "SELECT reserva.tipo, count(reserva.tipo) as 'num_reservas' " +
                        "FROM reserva " +
                        "WHERE reserva.id_hotel = ? " +
                        "AND reserva.fecha_ent <= DATE('?') AND reserva.fecha_sal >= DATE('?') " +
                        "GROUP BY reserva.tipo;";

        //Hay que saber cuantas habitaciones hay en un hotel de un cierto tipo
        String getNumeroDeHabitaciones =
                "SELECT habitaciones.tipo, habitaciones.n_disponibles " +
                        "FROM habitaciones " +
                        "WHERE habitaciones.hotel_id = ?";

        //Por cada tipo de reserva hay que mirar el precio
        String getPrecio =
                "SELECT precio.precio " +
                        "FROM precio" +
                        "WHERE precio.hotel_id = ? AND precio.fecha = DATE(?) AND precio.tipo = ?";


        String day = fecha_inicio;
        String nextDay = getNextDay(day);


        PreparedStatement stmt = null;
        PreparedStatement stmtGetNumHabitaciones = null;
        PreparedStatement stmtGetPrecio = null;

        ResultSet rs = null;
        ResultSet rsGetNumHabitaciones = null;
        ResultSet rsGetPrecio = null;

        HashMap<String, Tipo> disponibles = new HashMap<String, Tipo>();

        //Vemos cuantas habitaciones hay por cada tipo y las almacenamos en disponibles
        try {
            stmtGetNumHabitaciones = conector.getConn().prepareStatement(getNumeroDeHabitaciones);
            stmtGetNumHabitaciones.setString(1,  String.valueOf(hotel_id));
            rsGetNumHabitaciones = stmtGetNumHabitaciones.executeQuery();

            while(rsGetNumHabitaciones.next()){
                disponibles.put(rsGetNumHabitaciones.getString("tipo"), new Tipo(rsGetNumHabitaciones.getString("tipo"), 0, Integer.parseInt(rsGetNumHabitaciones.getString("n_disponibles"))));
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            stmt = conector.getConn().prepareStatement(getReservasPorDias);
            stmtGetPrecio = conector.getConn().prepareStatement(getPrecio);

            //Ponemos en los preparedStatements el hotel_id
            stmt.setString(1, String.valueOf(hotel_id));
            stmtGetPrecio.setString(1, String.valueOf(hotel_id));

            while (nextDay != fecha_fin) {

                stmt.setString(2, day);
                stmtGetPrecio.setString(2, day);

                stmt.setString(3, nextDay);

                rsGetNumHabitaciones = stmtGetNumHabitaciones.executeQuery();

                stmtGetPrecio.setString(2, rs.getString(day));

                //Vemos que tipos de habitaciones estan reservadas y cuantas hay
                rs = stmt.executeQuery();
                while (rs.next()) {

                    //No esta el tipo en disponibles...
                    if (disponibles.containsKey(rs.getString("tipo"))) {

                        if (disponibles.get(rs.getString("tipo")).getDisponibles() > Integer.parseInt(rs.getString("num_reservas"))) {

                            stmtGetPrecio.setString(3, rs.getString("tipo"));
                            rsGetPrecio = stmtGetPrecio.executeQuery();

                            if (rsGetPrecio.next()){
                                disponibles.get(rs.getString("tipo")).addPrecio(rsGetPrecio.getDouble("precio"));
                            }
                        }
                        //Si no hay habitaciones disponibles se borra la entrada de disponibles
                        else {
                            disponibles.remove(rs.getString("tipo"));
                        }
                    }
                    //Si hay mas habitaciones en el hotel de un tipo que de reservas...
                    //No se hace nada
                }

                day = nextDay;
                nextDay = getNextDay(day);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashSet<Tipo> res = new HashSet<Tipo>();

        for (String tipoStr: disponibles.keySet()) {
            res.add(disponibles.get(tipoStr));
        }

        return res;
    }


    public HashSet<Reserva> getPrecios(int hotel_id, String ciudad, String fechaInicio, String fechaFin){

        HashSet<Reserva> res = new HashSet<Reserva>();

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


    public void crearReserva(Reserva reserva, int cliente_id){

        if (! conector.isConnected()){
            conector.conectar();
        }

        String crearReserva = "INSERT INTO reserva (fecha_ent, fecha_sal, importe, hotel_id, tipo, cliente_id) VALUES (?,?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(crearReserva);
            stmt.setString(1, reserva.getFecha_ent());
            stmt.setString(2, reserva.getFecha_sal());
            stmt.setString(3, String.valueOf(reserva.getTipo().getPrecio()));
            stmt.setString(4, String.valueOf(reserva.getHotel().getId()));
            stmt.setString(5, String.valueOf(reserva.getTipo().getTipo()));
            stmt.setString(6, String.valueOf(cliente_id));
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();
    }


    public void cancelarReserva(int id){
        String borrarReserva = "DELETE FROM reserva WHERE id = ?";

        if (!conector.isConnected()){
            conector.conectar();
        }
        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(borrarReserva);
            stmt.setString(1, String.valueOf(id));
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

    }
}
