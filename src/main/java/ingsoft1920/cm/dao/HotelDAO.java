package ingsoft1920.cm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.apiout.APIdho;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.conector.ConectorBBDD;

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
	// -num_instalaciones: int
	
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
						   +"(hotel_id,servicio_id,precio,unidad_medida,num_instalaciones) "
						   +"VALUES (?,?,?,?,?)";

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
										 serv.get("unidad_medida"),
										 serv.get("num_instalaciones")
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
	
	// TODO: Re-hacer la query criminal
	// Cada Properties tendrá lo siguiente:
	// -hotel_id: int
	// -habs: List<Properties>, siendo cada Properties a su vez:
	// 					-tipo_hab_id: int
	//					-nombre: String
	//					-precio_total: int
	public List<Properties> disponibles(Date fecha_entrada, Date fecha_salida) {
		List<Properties> res = null;
		List<Map<String, Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		String query = "SELECT h.id, GROUP_CONCAT(hth.tipo_hab_id) AS habIDs, GROUP_CONCAT(th.nombre_tipo) AS habNombres "
					  +" FROM Hotel h "
					  +" JOIN Hotel_Tipo_Habitacion hth ON h.id = hth.hotel_id "
					  +" JOIN Tipo_Habitacion th ON hth.tipo_hab_id = th.id "
					  +"GROUP BY h.id";
		
		try( Connection conn = conector.getConn() )
		{
			resConsulta = runner.query(conn,query,handler);
			
		} catch( Exception e ) { e.printStackTrace(); }
		
		if( resConsulta != null ) {
			res = new ArrayList<>();
			
			List<Properties> habs;
			Properties hotel, aux;
			String[] habIDs, habNombres;
			for( Map<String,Object> fila : resConsulta ) {
				hotel = new Properties();
				hotel.put("hotel_id", fila.get("id") );
				
				habs = new ArrayList<>();
				
				// Tienen el mismo num de elementos pues matchean
				habIDs = ((String)fila.get("habIDs")).split(",");
				habNombres = ((String)fila.get("habNombres")).split(",");
				
				for(int i=0;i<habIDs.length;i++) {
					aux = new Properties();
					aux.put("tipo_hab_id",habIDs[i]);
					aux.put("nombre",habNombres[i]);
					aux.put("precio_total",new Random().nextInt(100)+50);	
					
					habs.add(aux);
				}
				hotel.put("habitaciones",habs);
				
				res.add(hotel);
			}
		}
		return res;
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
	
	
	public static void main(String[] args) {
		
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
		  serv1.put("num_instalaciones",2);
		List<Properties> servs = List.of(serv1);
		
		
		Properties cat1 = new Properties();
		  cat1.put("categoria_id",1);
		List<Properties> cats = List.of(cat1);

 		
		dao.anadir(h, habs, servs, cats);
		
	}	

}
