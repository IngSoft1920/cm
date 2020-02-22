package ingsoft1920.dao;

import ingsoft1920.conector.conectorBBDD;
import ingsoft1920.model.Precio;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrecioDAO {

    private static conectorBBDD conector = new conectorBBDD("localhost:3306", "root", "SUPERGUILLE", "practica1");

    //Recibe un objeto Precio (definido en ingsof1920/model)
    public void anadirPrecio(Precio precio){

        if (! conector.isConnected()){
            conector.conectar();
        }
        String anadirPrecio = "INSERT INTO precio (hotel_id, tipo, fecha, precio) VALUES (?,?,?,?)";

        PreparedStatement stmt;

        try {
            stmt = conector.getConn().prepareStatement(anadirPrecio);
            stmt.setString(1, String.valueOf(precio.getHotel_id()));
            stmt.setString(2, String.valueOf(precio.getTipo()));
            stmt.setString(3, String.valueOf(precio.getFecha()));
            stmt.setString(4, String.valueOf(precio.getPrecio()));
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conector.isConnected()){
            conector.closeConn();
        }
    }
}
