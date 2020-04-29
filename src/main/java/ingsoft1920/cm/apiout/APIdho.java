package ingsoft1920.cm.apiout;


import com.google.gson.Gson;
import com.google.gson.JsonObject;


import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Reserva;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.ServicioDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;

public class APIdho {
	
	private static final int PUERTO = 7001;
	private static JsonObject json;
	
	public static void enviarReserva(Reserva r) {
		json = new JsonObject();
		  json.addProperty("reserva_id",r.getId());
		  json.addProperty("fecha_entrada",r.getFecha_entrada().toString());
		  json.addProperty("fecha_salida",r.getFecha_salida().toString());
		  json.addProperty("importe",r.getImporte());
		  json.addProperty("cliente_id",r.getCliente_id());
		  json.addProperty("numero_acompanantes",r.getNumero_acompanantes());
		  json.addProperty("hotel_id", r.getHotel_id());
		  json.addProperty("tipo_hab_id", r.getTipo_hab_id());
		  json.addProperty("metodo_pago",r.getMetodo_pago().toString());
		  
		System.out.println( json.toString() );
		APIout.enviar(json.toString(),PUERTO, "/recibirReserva");
	}
	
	public static void enviarHotel(Hotel h) {
		Gson jsonMaker = new Gson();
		
		json = new JsonObject();
		  json.addProperty("id", h.getId());
		  json.addProperty("nombre", h.getNombre());
		  json.addProperty("descripcion", h.getDescripcion());
		  json.addProperty("estrellas", h.getEstrellas());
		  json.addProperty("continente", h.getContinente());
		  json.addProperty("pais", h.getPais());
		  json.addProperty("ciudad", h.getCiudad());
		  json.add("habitaciones",jsonMaker.toJsonTree(new TipoHabitacionDAO().habsHotel( h.getId() )));
		  json.add("servicios",jsonMaker.toJsonTree(new ServicioDAO().serviciosHotel( h.getId() )));
		  json.add("categorias",jsonMaker.toJsonTree(new CategoriaDAO().categoriasHotel( h.getId() )));
		
		System.out.println( json.toString() );
		APIout.enviar(json.toString(), PUERTO, "/recibirHotel");

	}
	
	public static void eliminarHotel(int hotel_id) {
		APIout.post(PUERTO, "/eliminarHotel/"+hotel_id);
	}

}
