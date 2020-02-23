package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Precio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrecioDAO {

    private static conectorBBDD conector = new conectorBBDD("localhost:3306", "root", "SUPERGUILLE", "practica1");

    //Recibe un objeto Precio (definido en ingsof1920/model)
    public void anadirPrecio(Precio precio){

        if (! conector.isConnected()){
            conector.conectar();
        }
        String anadirPrecio = "INSERT INTO precio (hotel_id, tipo, fecha, precio) VALUES (?,?,DATE(?),?)";

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(anadirPrecio);
            stmt.setInt(1, precio.getHotel_id());
            stmt.setString(2, precio.getTipo());
            stmt.setDate(3, Date.valueOf(precio.getFecha()));
            stmt.setDouble(4, precio.getPrecio());
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();

    }

}
