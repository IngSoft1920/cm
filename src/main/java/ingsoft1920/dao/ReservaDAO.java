package ingsoft1920.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import ingsoft1920.conector.conectorBBDD;

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

    public HashMap<String, int[]> getNumeroHabitacionesReservadas(String hotel, String fecha_inicio, String fecha_fin) {

        //hay que encontrar el id del hodel
        String getHotelId = "SELECT id FROM hotel WHERE nombre = ?";


        //Por cada dia hay que ver cuantas habitaciones hay reservadas (por cada tipo)
        String getReservasPorDias =
                "SELECT reserva.tipo, count(reserva.tipo) as 'num_reservas' " +
                        "FROM reserva " +
                        "WHERE reserva.id_hotel = ? " +
                        "AND reserva.fecha_inicio <= DATE('?') AND reserva.fecha_fin >= DATE('?') " +
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

        if (conector.getConn() == null)
            conector.conectar();

        PreparedStatement stmt = null;
        PreparedStatement stmtGetNumHabitaciones = null;
        PreparedStatement stmtGetPrecio = null;

        ResultSet rs = null;
        ResultSet rsGetNumHabitaciones = null;
        ResultSet rsGetPrecio = null;

        int hotel_id = 0;
        HashMap<String, int[]> disponibles = new HashMap<String, int[]>();

        //Obtenemos el id del hotel que buscamos..
        try {
            stmt = conector.getConn().prepareStatement(getHotelId);
            stmt.setString(1, hotel);
            rs = stmt.executeQuery();
            rs.next();
            hotel_id = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Vemos cuantas habitaciones hay por cada tipo y las almacenamos en disponibles
        try {
            stmtGetNumHabitaciones = conector.getConn().prepareStatement(getNumeroDeHabitaciones);
            stmtGetNumHabitaciones.setString(1,  String.valueOf(hotel_id));

            while(rsGetNumHabitaciones.next()){
                disponibles.put(rsGetNumHabitaciones.getString("tipo"), new int[2]);
                disponibles.get(rsGetNumHabitaciones.getString("tipo"))[0] = Integer.parseInt(rsGetNumHabitaciones.getString("n_disponibles"));
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
                    if (!disponibles.containsKey(rs.getString("tipo"))) {

                    }
                    //Si has mas habitaciones en el hotel de un tipo que de reservas...
                    else if (disponibles.get(rs.getString("tipo"))[0] > Integer.parseInt(rs.getString("num_reservas"))) {
                        stmtGetPrecio.setString(3, rs.getString("tipo"));
                        rsGetPrecio = stmtGetPrecio.executeQuery();
                        disponibles.get(rs.getString("tipo"))[1] += Integer.parseInt(rsGetPrecio.getString("precio"));
                    }
                    //Si no hay habitaciones disponibles se borra la entrada de disponibles
                    else {
                        disponibles.remove(rs.getString("tipo"));
                    }
                }

                day = nextDay;
                nextDay = getNextDay(day);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponibles;
    }

}
