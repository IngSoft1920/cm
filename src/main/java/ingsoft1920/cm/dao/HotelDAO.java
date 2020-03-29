package ingsoft1920.cm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.apiout.APIdho;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.conector.ConectorBBDD;
import ingsoft1920.cm.model.Disponibles;

@Component
public class HotelDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	// *En habs cada Properties tendrá:
	// -tipo_hab_id: int
	// -num_disponibles: int
	
	// *En servs cada Properties tendrá:
	// -servicio_id: int
	// -precio: Integer
	// -unidad_medida: String
	
	// *En cats cada Properties tendrá:
	// -categoria_id: int
	public int anadir(Hotel h,
					  List<Properties> habs, 
					  List<Properties> servs,
					  List<Properties> cats)
	{
		BigInteger idGenerado = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		
		String queryHotel = "INSERT INTO Hotel "
						   +"(nombre,continente,pais,ciudad,"
						   +"direccion,estrellas,descripcion) "
						   +"VALUES (?,?,?,?,?,?,?);";

		String queryHabs = "INSERT INTO Hotel_Tipo_Habitacion "
						  +"(hotel_id,tipo_hab_id,num_disponibles) "
						  +"VALUES (?,?,?)";

		String queryServs = "INSERT INTO Hotel_Servicio "
						   +"(hotel_id,servicio_id,precio,unidad_medida) "
						   +"VALUES (?,?,?,?)";

		String queryCats = "INSERT INTO Hotel_Categoria "
						  +"(hotel_id,categoria_id) "
						  +"VALUES (?,?)";

		List<Object[]> batch;
		try ( Connection conn = conector.getConn() )
		{
			// Insertamos el hotel
			idGenerado = runner.insert(conn, queryHotel, handler,
									   h.getNombre(),
									   h.getContinente(),
									   h.getPais(),
									   h.getCiudad(),
									   h.getDireccion(),
									   h.getEstrellas(),
									   h.getDescripcion()
							   		  );

			// Enlazamos con los tipo de habitaciones:
			batch = new ArrayList<>();
			for (Properties hab : habs) {
				batch.add(new Object[] { idGenerado.intValue(),
										 hab.get("tipo_hab_id"),
										 hab.get("num_disponibles") 
									   });
			}
			runner.batch(conn, queryHabs, batch.toArray(new Object[habs.size()][]));

			
			// Enlazamos con los servicios
			batch = new ArrayList<>();
			for (Properties serv : servs) {
				batch.add(new Object[] { idGenerado.intValue(),
										 serv.get("servicio_id"),
										 serv.get("precio"),
										 serv.get("unidad_medida")
									   });
			}
			runner.batch(conn, queryServs, batch.toArray(new Object[servs.size()][]));

			
			// Enlazamos con las categorias
			batch = new ArrayList<>();
			for (Properties cat : cats) {
				batch.add(new Object[] { idGenerado.intValue(),
										 cat.get("categoria_id")
									   });
			}
			runner.batch(conn, queryCats, batch.toArray(new Object[cats.size()][]));

		} catch (Exception e) { e.printStackTrace(); }

		// Mandamos el hotel a dho:
		if (idGenerado != null) {
			h.setId( idGenerado.intValue() );
			APIdho.enviarHotel(h);
		}

		return (idGenerado != null ? idGenerado.intValue() : -1);
	}



	public List<Hotel> hoteles() {
		List<Hotel> res = new ArrayList<>();
		BeanListHandler<Hotel> handler = new BeanListHandler<>(Hotel.class);
		String query = "SELECT * FROM Hotel";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public Hotel getByID(int hotelID) {
		Hotel res = null;
		BeanHandler<Hotel> handler = new BeanHandler<>(Hotel.class);
		String query = "SELECT * FROM Hotel WHERE id=?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, hotelID);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}

	public void eliminar(int id) {
		String query = "DELETE FROM Hotel WHERE id = ?";

		try (Connection conn = conector.getConn()) {
			runner.update(conn, query, id);

		} catch (Exception e) { e.printStackTrace(); }

	}

	// TODO cambiar esto
	public List<Disponibles> disponibles(Date fecha_entrada, Date fecha_salida) {

		BeanListHandler<Disponibles> beanListHandler = new BeanListHandler<>(Disponibles.class);
		QueryRunner runner = new QueryRunner();

		String disponiblesQuery = "SELECT Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id, Tipo_Habitacion.nombre_tipo, Hotel_Tipo_Habitacion.num_disponibles, SUM(Precio_Habitacion.precio) AS precio_total "
				+ "FROM Hotel_Tipo_Habitacion " + "JOIN Precio_Habitacion "
				+ "ON Precio_Habitacion.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND Precio_Habitacion.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Precio_Habitacion.fecha >= ? AND Precio_Habitacion.fecha < ? "
				+ "JOIN Tipo_Habitacion " + "ON Hotel_Tipo_Habitacion.tipo_hab_id = Tipo_Habitacion.id "
				+ "LEFT JOIN (SELECT Reserva.hotel_id, Reserva.tipo_hab_id, COUNT(*) AS num_reservadas "
				+ "FROM Reserva " + "WHERE Reserva.fecha_entrada <= ? AND Reserva.fecha_salida >= ? "
				+ "GROUP BY Reserva.hotel_id, Reserva.tipo_hab_id) AS reservadas "
				+ "ON reservadas.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND reservadas.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Hotel_Tipo_Habitacion.num_disponibles < reservadas.num_reservadas "
				+ "WHERE reservadas.hotel_id IS NULL OR reservadas.tipo_hab_id IS NULL "
				+ "GROUP BY Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id;";

		List<Disponibles> disponibles = new LinkedList<>();

		try (Connection conn = conector.getConn()) {
			disponibles = runner.query(conn, disponiblesQuery, beanListHandler, fecha_entrada, fecha_salida,
					fecha_salida, fecha_entrada);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return disponibles;

	}

	/*
	 * Cada Properties de la lista tiene los siguientes atributos:
	 * 
	 * id : int,
	 * ciudad : String,
	 * nota : double
	 * ocupacion : Map<Date,Double> -> (ocupación en porcentaje, ej: 0.25 -> tiene 
	 *                                  ocupado un cuarto de su capacidad)
	 * 
	 * habitaciones : List<Properties> (cada una a su vez tendrá: 
	 *    id: int
	 *    nombre_tipo: String
	 *    num_disponibles: int (esto sería la capacidad)
	 */
	public List<Properties> getDataRM() {
		List<Properties> res = new ArrayList<>();

		List<Hotel> hoteles = hoteles();
		Properties aux;
		for (Hotel h : hoteles) {
			aux = new Properties();

			aux.put("id", h.getId());
			aux.put("ciudad", h.getCiudad());
			aux.put("nota", new ValoracionDAO().getNotaHotel(h.getId()));
			aux.put("ocupacion", getOcupacionesHotel(h.getId()));
			aux.put("habitaciones", new TipoHabitacionDAO().habsHotel(h.getId()));

			res.add(aux);
		}
		return res;
	}


	// La ocupación se devuelve en tanto porciento. Se devuelve
	// todos los datos desde hoy hasta dentro de x dias
	private static final int INVERVALO_DIAS = 5;

	public Map<Date, Double> getOcupacionesHotel(int hotel_id) {
		Map<Date, Double> res = new HashMap<>();

		LocalDate actual = LocalDate.now();
		LocalDate fechaTope = actual.plusDays(INVERVALO_DIAS);

		while (actual.isBefore(fechaTope)) {

			res.put(Date.valueOf(actual), getOcupacionHotel(hotel_id, Date.valueOf(actual)));

			actual = actual.plusDays(1);
		}

		return res;
	}

	// Devuelve la ocupacion de un hotel en una fecha concreta
	public double getOcupacionHotel(int hotel_id, Date fecha) {
		BigDecimal res = null;
		ScalarHandler<BigDecimal> handler = new ScalarHandler<>();
		String query = "SELECT COUNT(*)/capacidad AS ocupacion "
					  +"FROM ("
					  +"      SELECT hotel_id,SUM(num_disponibles) AS capacidad "
					  +"      FROM Hotel_Tipo_Habitacion "
					  +"      WHERE hotel_id = ? "
					  +"     ) AS hcap "
					  +"JOIN Reserva r ON hcap.hotel_id = r.hotel_id "
					  +"WHERE ? BETWEEN r.fecha_entrada AND r.fecha_salida "
					  +"GROUP BY r.hotel_id ";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, hotel_id, fecha);

		} catch (Exception e) { e.printStackTrace(); }
		return res != null ? res.doubleValue() : 0;
	}

	
//	/**
//	 * Metodo para anadir un hotel a BD
//	 * 
//	 * @param h
//	 * @param habs
//	 * @param servicios
//	 * @param categorias Creado por Luis(Front)
//	 */
//	public void anadirHotel(Hotel h, List<Hotel_Tipo_Habitacion> habs, List<Hotel_Servicio> servicios,
//			List<Hotel_Categoria> categorias) {
//		// Primero añadimos el hotel mismamente
//		BigInteger res = null;
//		ScalarHandler<BigInteger> handlerH = new ScalarHandler<>();
//		String queryH = "INSERT INTO Hotel " + "(nombre,continente,pais,ciudad,direccion,estrellas,descripcion) "
//				+ "VALUES (?,?,?,?,?,?,?);";
//
//		String queryHabs = "INSERT INTO Hotel_Tipo_Habitacion " + "(hotel_id,tipo_hab_id,num_disponibles) "
//				+ "VALUES (?,?,?)";
//
//		String queryServ = "INSERT INTO Hotel_Servicio " + "(hotel_id,servicio_id,precio,unidad_medida) "
//				+ "VALUES (?,?,?,?)";
//
//		String queryCat = "INSERT INTO Hotel_Categoria " + "(hotel_id,categoria_id) " + "VALUES (?,?)";
//
//		List<Object[]> batch;
//		try (Connection conn = conector.getConn()) {
//			res = runner.insert(conn, queryH, handlerH, h.getNombre(), h.getContinente(), h.getPais(), h.getCiudad(),
//					h.getDireccion(), h.getEstrellas(), h.getDescripcion());
//
//			// Enlazamos con los tipo de habitaciones:
//			batch = new ArrayList<>();
//			for (Hotel_Tipo_Habitacion hab : habs) {
//				batch.add(new Object[] { res.intValue(), hab.getTipo_hab_id(), hab.getNum_disponibles() });
//			}
//			runner.batch(conn, queryHabs, batch.toArray(new Object[habs.size()][]));
//
//			// Enlazamos con los servicios
//			batch = new ArrayList<>();
//			for (Hotel_Servicio srv : servicios) {
//				batch.add(
//						new Object[] { res.intValue(), srv.getServicio_id(), srv.getPrecio(), srv.getUnidad_medida() });
//			}
//			runner.batch(conn, queryServ, batch.toArray(new Object[servicios.size()][]));
//
//			// Enlazamos con las categorias
//			batch = new ArrayList<>();
//			for (Hotel_Categoria cat : categorias) {
//				batch.add(new Object[] { res.intValue(), cat.getCategoria_id() });
//			}
//			runner.batch(conn, queryCat, batch.toArray(new Object[categorias.size()][]));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		//return ( res != null ? res.intValue() : -1 );
//	}
	
	
	public static void main(String[] args) {
		/*
		HotelDAO dao = new HotelDAO();
		Hotel h = new Hotel(-1, "Hotel New Japón","Asia", "Japón", "Tokyo", "Calle Luna,12", 5, "Oriental");
		
		Properties hab1 = new Properties();
		  hab1.put("tipo_hab_id", 1);
		  hab1.put("num_disponibles", 30);
		Properties hab2 = new Properties();
		  hab2.put("tipo_hab_id", 2);
		  hab2.put("num_disponibles", 15);
		List<Properties> habs = List.of(hab1,hab2);
		
		
		Properties serv1 = new Properties();
		  serv1.put("servicio_id",1);
		  serv1.put("precio",100);
		  serv1.put("unidad_medida",2);
		List<Properties> servs = List.of(serv1);
		
		
		Properties cat1 = new Properties();
		  cat1.put("categoria_id",1);
		List<Properties> cats = List.of(cat1);

 		
		dao.anadir(h, habs, servs, cats);*/
		
		new HotelDAO().getDataRM().forEach( p -> System.out.println(p + "\n") );
	}	

}
