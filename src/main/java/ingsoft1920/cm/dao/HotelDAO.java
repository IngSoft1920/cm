package ingsoft1920.cm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		APIdho.eliminarHotel(id);
	}
	
	// Cada Properties tendrá lo siguiente (esto es así porque al pasarlo
	// a json la librería lo hace automático):
	// -hotel_id: int
	// -habs: List<Properties>, siendo cada Properties a su vez:
	// 					-tipo_hab_id: int
	//					-nombre: String
	//					-precio_total: int
	public List<Properties> disponiblesFalso(Date fecha_entrada, Date fecha_salida) {
		List<Properties> res = null;
		List<Map<String, Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		
		// Esto nos da las habitaciones disponibles entre esas dos fechas
		String query = "SELECT ocup.hotel_id,ocup.tipo_hab_id,th.nombre_tipo "
					  +"FROM"
					  	   +"("
					  	     +"SELECT hth.hotel_id,hth.tipo_hab_id,COUNT(*) AS ocupadas,hth.num_disponibles "
					  	     	+"FROM Hotel_Tipo_Habitacion hth " 
					  	     	+"JOIN Reserva r ON hth.hotel_id=r.hotel_id AND hth.tipo_hab_id=r.tipo_hab_id "
					  	     +"WHERE r.fecha_entrada <= ? AND r.fecha_salida >= ? "
					  	     +"GROUP BY hth.hotel_id,hth.tipo_hab_id"
					  	     +") AS ocup "
					  +"JOIN Tipo_Habitacion th ON ocup.tipo_hab_id=th.id "
					  +"WHERE ocup.num_disponibles - ocup.ocupadas > 0;";
		
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
	
	
	// Cada Properties tendrá lo siguiente (esto es así porque al pasarlo
	// a json la librería lo hace automático):
	// -hotel_id: int
	// -habs: List<Properties>, siendo cada Properties a su vez:
	// 					-tipo_hab_id: int
	//					-nombre: String
	//					-precio_total: int
	public List<Properties> disponibles(Date fecha_ent,Date fecha_sal) {
		List<Properties> res = new ArrayList<>();
		List<Map<String, Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		
		String query = "SELECT prec.hotel_id,"
				   			 +"GROUP_CONCAT(prec.tipo_hab_id) AS habs_disp_ids,"
				   			 +"GROUP_CONCAT(prec.nombre_tipo) AS habs_disp_nombres,"
				   			 +"GROUP_CONCAT(prec.precio_total) AS precios_totales "
				   	     +"FROM"
				   	     	 +"("
				   	     	  +"SELECT hth.hotel_id,hth.tipo_hab_id,SUM(ph.precio_por_noche) AS precio_total,th.nombre_tipo "
				   	     	  	  +"FROM Tipo_Habitacion th "
				   	     	  	  +"JOIN Hotel_Tipo_Habitacion hth ON th.id=hth.tipo_hab_id "
				   	     	  	  +"JOIN Precio_Habitacion ph ON hth.hotel_id=ph.hotel_id AND hth.tipo_hab_id=ph.tipo_hab_id "
				   	     	  +"WHERE ph.fecha BETWEEN ? AND ? "
				   	     	  +"GROUP BY hth.hotel_id,hth.tipo_hab_id"
				   	     	 +") AS prec "
				   	     +"JOIN"
				   	     	 +"("
				   	     	  +"SELECT ocup.hotel_id,ocup.tipo_hab_id " /*Hoteles con reservas en conflicto con las fechas pedidas pero con capacidad suficiente*/
				   	     	  	  +"FROM"
				   	     	  	  	  +"(" 
				   	     	  	  	   +"SELECT hth.hotel_id,hth.tipo_hab_id,COUNT(*) AS ocupadas,hth.num_disponibles "
				   	     	  	  	   	  +"FROM Hotel_Tipo_Habitacion hth "
				   	     	  	  	   	  +"JOIN Reserva r ON hth.hotel_id=r.hotel_id AND hth.tipo_hab_id=r.tipo_hab_id "
				   	     	  	  	   	+"WHERE r.fecha_entrada <= ? AND r.fecha_salida >= ? "
				   	     	  	  	   	+"GROUP BY hth.hotel_id, hth.tipo_hab_id "
				   	     	  	  	   +") AS ocup "
				   	     	  +"WHERE ocup.num_disponibles - ocup.ocupadas > 0 "
				   	     	  +"UNION "
				   	     	  +"SELECT hotel_id,tipo_hab_id " /*Hoteles sin reservas o bien hoteles con reservas pero que no tienen NINGUNA dentro de las fechas pedidas*/
				   	     	  	  +"FROM Hotel_Tipo_Habitacion "
				   	     	  +"WHERE (hotel_id,tipo_hab_id) NOT IN ("
										   			  				+"SELECT hotel_id,tipo_hab_id "
										   			  					+"FROM Reserva "
										   			  				+"WHERE fecha_entrada <= ? AND fecha_salida >= ?"
										   			  			   +")"
						     +") AS disp "
					    +"ON prec.hotel_id=disp.hotel_id AND prec.tipo_hab_id=disp.tipo_hab_id "
					    +"GROUP BY prec.hotel_id";
		
		// Esto nos da lo siguiente (ejemplo):
		// hotel_id  habs_disp_ids   habs_disp_nombres    precios_totales 
		//    1		 	 1			    normal              411
		//	  4			3,1		    presidencial,normal     552,428
		//
		// -Tal y como se aprecia, las columnas de habs_disp_ids, habs_disp_nombres
		// y precios_totales están mapeadas
		
		try( Connection conn = conector.getConn() )
		{
			resConsulta = runner.query(conn,query,handler,
										fecha_ent,
										fecha_sal,
										fecha_sal,
										fecha_ent,
										fecha_sal,
										fecha_ent);
			
		} catch( Exception e ) { e.printStackTrace(); }
		
		
		if( resConsulta != null ) {
			Properties aux;
			for( Map<String,Object> fila : resConsulta ) {
				aux = new Properties();
				  aux.put( "hotel_id" , fila.get("hotel_id") );
				  aux.put( "habs" , tratarFilaDisponibles(fila) );
				    
				res.add(aux);
			}
		}
		
		return res;
	}
	
	// Cada Properties es así:
	// -tipo_hab_id: int
	// -nombre: String
	// -precio_total: int
	private List<Properties> tratarFilaDisponibles(Map<String,Object> fila) {
		List<Properties> res = new ArrayList<>();
		
	    Integer[] habsIds = stringToArray( (String) fila.get("habs_disp_ids") );
	    String[] habsNombres = ((String) fila.get("habs_disp_nombres")).split(",");
	    Integer[] precios = stringToArray( (String) fila.get("precios_totales") );

	    Properties aux;
	    for(int i=0;i<habsIds.length;i++) {
	    	aux = new Properties();
	    	  aux.put("tipo_hab_id",habsIds[i]);
	    	  aux.put("nombre",habsNombres[i]);
	    	  aux.put("precio_total",precios[i]);
	    	  
	    	res.add(aux);
	    }
	    
		return res;
	}
	
	// Convierte una lista de números separados por comas
	// en un array: "1,2,3" -> [1,2,3]
	private Integer[] stringToArray(String s) {
		return Arrays
				 .stream( s.split(",") )
				 .map( numStr -> Integer.valueOf(numStr) )
				 .toArray(Integer[]::new);
	}
	
	public static void main(String[] args) {
		System.out.println( new HotelDAO().disponibles(Date.valueOf("2020-04-27"),
									   Date.valueOf("2020-04-30")));
		
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

}
