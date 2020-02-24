package ingsoft1920.cm.dao;

import ingsoft1920.cm.conector.conectorBBDD;
import ingsoft1920.cm.model.Peticion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PeticionesDAO {

    private static conectorBBDD conector = new conectorBBDD("8000", "cm1", "ingSoft20ge1.711", "piedrafita.ls.fi.upm.es");

    public LinkedList<Peticion> getPeticiones(int id) {

        if (!conector.isConnected()){
            conector.conectar();
        }

        LinkedList<Peticion> peticiones = new LinkedList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        String getPeticiones = "SELECT * FROM peticiones";

        try {
            stmt = conector.getConn().prepareStatement(getPeticiones);
            rs = stmt.executeQuery();

            while (rs.next()){
                peticiones.add(new Peticion(rs.getInt("id"), rs.getString("ciudad"), rs.getString("fecha"), rs.getDate("tipo").toString(), rs.getInt("estado")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return peticiones;
    }

    public void cambiaEstado(int id){

        if (!conector.isConnected()){
            conector.conectar();
        }

        String cambiaEstado = "UPDATE peticion SET estado = 1 WHERE id = ?";

        PreparedStatement stmt = null;

        try {
            stmt = conector.getConn().prepareStatement(cambiaEstado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conector.closeConn();
    }


}
