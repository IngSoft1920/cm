package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.dao.ProductoDAO;

@Component
public class PedidoDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	// TODO CAMBIAR CON LO NUEVO
	// Cada Properties representa un producto en el pedido:
	// -producto_id: int
	// -cantidad: int
	public int anadir(Pedido p,List<Properties> info) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
        int cantidad;
        int importeTotal = 0;
        ProductoDAO dao = new ProductoDAO();

		String queryPedido = "INSERT INTO Pedido "
							+"(fecha,hotel_id, proveedor_id, importe) "
							+"VALUES (?,?,?,?);";
		
		String queryProductos = "INSERT INTO Pedido_Producto "
							   +"(pedido_id,producto_id,cantidad, especificaciones) "
							   +"VALUES (?,?,?,?)";

		List<Object[]> batch;
        for (Properties prod : info) {
            cantidad = (int) prod.get("cantidad");
            importeTotal += cantidad * dao.infoproducto((int) prod.get("producto_id"), p.getProveedor_id());
        }
		try (Connection conn = conector.getConn()) {
			res = runner.insert(conn, queryPedido, handler, p.getFecha(),p.getHotel_id(),p.getProveedor_id(),importeTotal);
	
			batch = new ArrayList<>();
			for (Properties prod : info) {
				batch.add(new Object[] { res.intValue(),
										 prod.get("producto_id"),
										 prod.get("cantidad"),
                                         prod.get("especificaciones")
									   });
			}
			runner.batch(conn, queryProductos, batch.toArray(new Object[info.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}
	
	public Pedido getByID(int pedidoID) {
		Pedido res=null;
		BeanHandler<Pedido> handler = new BeanHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler,pedidoID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public List<Pedido> pedidos() {
		List<Pedido> res = new ArrayList<>();
		BeanListHandler<Pedido> handler = new BeanListHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	// Cada Properties tiene:
	// id: int
	// nombre_hotel: String
	// fecha: Date
	// importe: int
	// productos: String
	public List<Properties> pedidosProveedor(int proveedorID) {
		List<Properties> res = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		BeanListHandler<Pedido> handler = new BeanListHandler<>(Pedido.class);
		String query = "SELECT * FROM Pedido WHERE proveedor_id=?";
		
		try (Connection conn = conector.getConn()) {
			pedidos = runner.query(conn, query, handler,proveedorID);

		} catch (Exception e) { e.printStackTrace(); }
		
		
		if( pedidos.size() > 0 ) {
			Properties aux;
			for( Pedido p : pedidos ) {
				aux = new Properties();
				  aux.put("id",p.getId());
				  aux.put("nombre_hotel", new HotelDAO().getByID(p.getHotel_id()).getNombre() );
				  aux.put("fecha",p.getFecha());
				  aux.put("importe",p.getImporte());
				  aux.put("productos",productosDePedido(p.getId()));
				  
				res.add(aux);
			}
		}
		
		return res;
	}


	// Devuelve una representación así:
	// tomates (10 kilos), vodka (2 litros),...,ron (1 barril)
	public String productosDePedido(int pedidoID) {
		String res = "";
		List<Map<String,Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		String query = "SELECT p.nombre,pp.cantidad,p.unidad_medida "
					  +"FROM Pedido_Producto pp "
					  +"JOIN Producto p ON pp.producto_id = p.id "
					  +"WHERE pp.pedido_id = ?";
		
		try ( Connection conn = conector.getConn() )
		{
			resConsulta = runner.query(conn,query,handler,pedidoID);
			
		} catch( Exception e ) { e.printStackTrace(); }
		
		if( resConsulta != null ) {
			for( Map<String,Object> fila : resConsulta ) {
				res += fila.get("nombre")+" ("+fila.get("cantidad")+" "+fila.get("unidad_medida")+"),";
			}
			// quitamos la coma del final
			res = res.substring(0,res.length()-1);
		}
		
		return res;
	}

}