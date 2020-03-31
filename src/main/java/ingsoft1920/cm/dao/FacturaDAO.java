package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.conector.ConectorBBDD;

public class FacturaDAO {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConectorBBDD conector = new ConectorBBDD();

    public int anadir(Factura factura) {
        BigInteger idGenerado = null;
        ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        String query = "INSERT INTO Factura (importe, fecha, pagado, cantidad_consumida, servicio_id, reserva_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conector.getConn()) 
        {
            idGenerado = runner.insert(conn, query, handler,
            						   factura.getImporte(),
            						   factura.getFecha(),
            						   factura.isPagado(),
            						   factura.getCantidad_consumida(),
            						   factura.getServicio_id(),
            						   factura.getReserva_id()
            						  );
        } catch (Exception e) { e.printStackTrace(); }

        return idGenerado != null ? idGenerado.intValue() : -1;
    }


    public void pagar(int reservaID){
        String query = "UPDATE Factura SET pagado = TRUE WHERE reserva_id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, query, reservaID);
            
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
