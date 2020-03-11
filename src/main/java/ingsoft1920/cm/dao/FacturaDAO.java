package ingsoft1920.cm.dao;

import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.conector.ConectorBBDD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;

public class FacturaDAO {

    @Autowired
    private QueryRunner runner;

    private ConectorBBDD conector = new ConectorBBDD();


    /**
     * Añade una a factura devolviendo el id generado
     * @param factura
     * @return id de la factura generada
     */
    public int anadir(Factura factura) {

        String anadirFactura = "INSERT INTO Factura (importe, fecha, pagado, cantidad_consumida, servicio_id, reserva_id) VALUES (?, ?, ?, ?, ?, ?)";

        ScalarHandler<Integer> handler = new ScalarHandler<>();

        Integer idGenerado = null;

        try (Connection conn = conector.getConn()) {
            idGenerado = runner.insert(conn, anadirFactura, handler, factura.getImporte(), factura.getFecha(), factura.isPagado(),
                    factura.getCantidad_consumida(), factura.getServicio_id(), factura.getReserva_id());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idGenerado;

    }

    /**
     * Pone pagado a true
     * @param id
     */
    public void pagar(int id){

        String paga = "UPDATE Peticiones SET pagado = TRUE WHERE id = ?";

        try( Connection conn = conector.getConn() )
        {
            runner.update(conn, paga, id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
