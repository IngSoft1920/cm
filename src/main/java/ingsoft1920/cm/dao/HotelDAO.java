package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;

import ingsoft1920.cm.model.Disponibles;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.aux.Hotel_Categoria;
import ingsoft1920.cm.bean.aux.Hotel_Servicio;
import ingsoft1920.cm.bean.aux.Hotel_Tipo_Habitacion;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class HotelDAO {
	
	@Autowired
    private QueryRunner runner = new QueryRunner();
	
	@Autowired
    private ConectorBBDD conector = new ConectorBBDD();

	
	// Todas las categorias, servicios y tipo_habitaciones
	// han de estar añadidas en la base de datos antes. Además,
	// las listas no tendrán el campo hotel_id puesto (lógicamente pues
	// estamos añadiendo el hotel correspondiente ahora. Este método
	// devuelve el id generado para el hotel
	public int anadir(Hotel h,
					  List<Hotel_Tipo_Habitacion> habs,
					  List<Hotel_Servicio> servicios,
					  List<Hotel_Categoria> categorias)
	{
		// Primero añadimos el hotel mismamente
		BigInteger res = null;
		ScalarHandler<BigInteger> handlerH = new ScalarHandler<>();
		String queryH = "INSERT INTO Hotel "
					   +"(nombre,continente,pais,ciudad,direccion,estrellas,descripcion) "
					   +"VALUES (?,?,?,?,?,?,?);";
		
		String queryHabs = "INSERT INTO Hotel_Tipo_Habitacion "
						  +"(hotel_id,tipo_hab_id,num_disponibles) "
						  +"VALUES (?,?,?)";
		
		String queryServ = "INSERT INTO Hotel_Servicio "
				  		  +"(hotel_id,servicio_id,precio,unidad_medida) "
				  		  +"VALUES (?,?,?,?)";
		
		String queryCat = "INSERT INTO Hotel_Categoria "
				  		 +"(hotel_id,categoria_id) "
				  		 +"VALUES (?,?)";
		
		List<Object[]> batch;
		try( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn,queryH,handlerH,
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
			for( Hotel_Tipo_Habitacion hab : habs ) {
				batch.add( new Object[] {
					res.intValue(),
					hab.getTipo_hab_id(),
					hab.getNum_disponibles()
				});
			}
			runner.batch(conn,queryHabs, batch.toArray(new Object[habs.size()][]));
			
			// Enlazamos con los servicios
			batch = new ArrayList<>();
			for( Hotel_Servicio srv : servicios ) {
				batch.add( new Object[] {
					res.intValue(),
					srv.getServicio_id(),
					srv.getPrecio(),
					srv.getUnidad_medida()
				});
			}
			runner.batch(conn,queryServ, batch.toArray(new Object[servicios.size()][]));
			
			// Enlazamos con las categorias
			batch = new ArrayList<>();
			for( Hotel_Categoria cat : categorias ) {
				batch.add( new Object[] {
					res.intValue(),
					cat.getCategoria_id()
				});
			}
			runner.batch(conn,queryCat, batch.toArray(new Object[categorias.size()][]));
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return ( res != null ? res.intValue() : -1 );
	}
	
	public List<Hotel> hoteles(){
		List<Hotel> res = new ArrayList<>();
		BeanListHandler<Hotel> handler = new BeanListHandler<>(Hotel.class);
		String query = "SELECT * FROM Hotel";
		
		try ( Connection conn = conector.getConn() )
		{	
			res = runner.query(conn,query,handler);
			
		} catch( Exception e ) { e.printStackTrace(); }
		return res;
	}
	
	public List<Categoria> categoriasHotel(int hotelID){
		List<Categoria> res = new ArrayList<>();
		BeanListHandler<Categoria> handler = new BeanListHandler<>(Categoria.class);
		String query = "SELECT c.* "
					  +"FROM Categoria AS c "
					  +"JOIN Hotel_Categoria AS hc ON c.id=hc.categoria_id "
					  +"JOIN Hotel AS h ON hc.hotel_id=h.id "
					  +"WHERE h.id=?;";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.query(conn,query,handler,hotelID);
			
		} catch ( Exception e ) { e.printStackTrace(); }
				
		return res;
	}
	
	public Map<Servicio,Hotel_Servicio> serviciosHotel(int hotelID) {
		Map<Servicio,Hotel_Servicio> res = null;
		
		// Tendremos un map por cada FILA de la consulta.
		// La 'key' es justamente el nombre de la columna
		List<Map<String,Object>> resConsulta = null;
		MapListHandler handler = new MapListHandler();
		String query = "SELECT s.id,s.nombre,hs.precio,hs.unidad_medida "
					  +"FROM Servicio AS s "
					  +"JOIN Hotel_Servicio AS hs ON s.id=hs.servicio_id "
					  +"JOIN Hotel AS h ON hs.hotel_id=h.id "
					  +"WHERE h.id=?;";
		
		try ( Connection conn = conector.getConn() )
		{
			resConsulta = runner.query(conn,query,handler,hotelID);
			
		} catch ( Exception e ) { e.printStackTrace(); }
			
		if( resConsulta != null ) {
			res = new HashMap<>();
			
			int servicioID;
			String nombreServicio;
			Integer precioServicio;
			String unidadMedida;
			
			Object aux;
			for( Map<String,Object> fila : resConsulta ) {
				
				servicioID = (Integer) fila.get("id");
				nombreServicio = (String) fila.get("nombre");
				
				// Este campo podría ser null y petar al hacer el cast a Integer
				aux = fila.get("precio");
				precioServicio = aux != null ? (Integer) fila.get("precio") : null;
				
				unidadMedida = (String) fila.get("unidad_medida");
				
				res.put(  
						new Servicio(servicioID,nombreServicio),
						new Hotel_Servicio(hotelID,servicioID,precioServicio,unidadMedida)
						);
			}
		}
		
		return res;
	}

    public List<Disponibles> disponibles(Date fecha_entrada, Date fecha_salida){

        BeanListHandler<Disponibles> beanListHandler = new BeanListHandler<>(Disponibles.class);
        QueryRunner runner = new QueryRunner();

        String disponiblesQuery =
                "SELECT Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id, Tipo_Habitacion.nombre_tipo, Hotel_Tipo_Habitacion.num_disponibles, SUM(Precio_Habitacion.precio) AS precio_total " +
                        "FROM Hotel_Tipo_Habitacion " +
                        "JOIN Precio_Habitacion " +
                        "ON Precio_Habitacion.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND Precio_Habitacion.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Precio_Habitacion.fecha >= ? AND Precio_Habitacion.fecha < ? " +
                        "JOIN Tipo_Habitacion " +
                        "ON Hotel_Tipo_Habitacion.tipo_hab_id = Tipo_Habitacion.id " +
                        "LEFT JOIN (SELECT Reserva.hotel_id, Reserva.tipo_hab_id, COUNT(*) AS num_reservadas " +
                        "FROM Reserva " +
                        "WHERE Reserva.fecha_entrada <= ? AND Reserva.fecha_salida >= ? " +
                        "GROUP BY Reserva.hotel_id, Reserva.tipo_hab_id) AS reservadas " +
                        "ON reservadas.hotel_id = Hotel_Tipo_Habitacion.hotel_id AND reservadas.tipo_hab_id = Hotel_Tipo_Habitacion.tipo_hab_id AND Hotel_Tipo_Habitacion.num_disponibles < reservadas.num_reservadas " +
                        "WHERE reservadas.hotel_id IS NULL OR reservadas.tipo_hab_id IS NULL " +
                        "GROUP BY Hotel_Tipo_Habitacion.hotel_id, Hotel_Tipo_Habitacion.tipo_hab_id;";

        List<Disponibles> disponibles = new LinkedList<>();

        try( Connection conn = conector.getConn() )
        {
            disponibles = runner.query(conn, disponiblesQuery, beanListHandler, fecha_entrada, fecha_salida, fecha_salida, fecha_entrada);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return disponibles;

    }
	
//	public static void main(String[] args) {
//		HotelDAO dao = new HotelDAO();		
//		
//		Hotel h = new Hotel(-1, "Super Exp", "America", "Argentina", "Buenos Aires", "Calle Jose,21", 4, "Una experiencia auténticamente argentina");
//		List<Hotel_Tipo_Habitacion> habs = List.of(new Hotel_Tipo_Habitacion(-1,1,100),new Hotel_Tipo_Habitacion(-1,2,20));
//		List<Hotel_Servicio> servs = List.of(new Hotel_Servicio(-1, 2, 20, "por_dia"));
//		List<Hotel_Categoria> cats = List.of();
//		
//		dao.anadir(h, habs, servs, cats);
//	}

}
