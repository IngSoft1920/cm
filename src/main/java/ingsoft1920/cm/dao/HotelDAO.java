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

import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.Hotel;
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
	
	public static void main(String[] args) {
		HotelDAO dao = new HotelDAO();		
		
		Hotel h = new Hotel(-1, "Super Exp", "America", "Argentina", "Buenos Aires", "Calle Jose,21", 4, "Una experiencia auténticamente argentina");
		List<Hotel_Tipo_Habitacion> habs = List.of(new Hotel_Tipo_Habitacion(-1,1,100),new Hotel_Tipo_Habitacion(-1,2,20));
		List<Hotel_Servicio> servs = List.of(new Hotel_Servicio(-1, 2, 20, "por_dia"));
		List<Hotel_Categoria> cats = List.of();
		
		dao.anadir(h, habs, servs, cats);
	}

}
