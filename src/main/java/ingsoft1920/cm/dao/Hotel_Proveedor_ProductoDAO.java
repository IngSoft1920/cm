package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.auxiliares.Hotel_Proveedor_Producto;

import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class Hotel_Proveedor_ProductoDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Hotel_Proveedor_Producto d) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();

		String queryS = "INSERT INTO Hotel_Proveedor_Producto " + "(hotel_id, producto_id, proveedor_id, precio, unidad_medida) " + "VALUES (?,?,?,?,?);";

		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryS, handler, d.getHotel_id(), d.getProducto_id(), d.getProveedor_id(), d.getPrecio(), d.getUnidad_medida());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (res != null ? res.intValue() : -1);
	}
	
	public List<Hotel_Proveedor_Producto> proveedoresHotel(int hotel_id ) {
		List<Hotel_Proveedor_Producto> res = new ArrayList<>();
		BeanListHandler<Hotel_Proveedor_Producto> handler = new BeanListHandler<>(Hotel_Proveedor_Producto.class);
		String query = "SELECT * FROM Hotel_Proveedor_Producto WHERE hotel_id = ?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, hotel_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
