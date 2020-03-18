package ingsoft1920.cm.controller;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Tipo_Habitacion;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.HotelDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;
import ingsoft1920.cm.model.Disponibles;


@Controller
public class HotelController {
	
	@Autowired
	HotelDAO dao = new HotelDAO();
	
	@GetMapping("/hotel/ge")
	@ResponseBody
	public String hotelesGE() {
		Gson jsonMaker = new Gson();
		JsonArray res = new JsonArray();
		List<Hotel> hoteles = dao.hoteles();
		
		JsonObject hotelJson;
		for( Hotel h : hoteles ) {
			
			hotelJson = jsonMaker.toJsonTree(h, Hotel.class).getAsJsonObject();
			hotelJson.add("categorias",
						  jsonMaker.toJsonTree(dao.categoriasHotel( h.getId() ))
						 );
			
			res.add(hotelJson);
		}
		
		return res.toString();
	}
	
	@GetMapping("/hotel/servicios/{hotel_id}")
	@ResponseBody
	public String serviciosHotel(@PathVariable int hotel_id) {
		
		/* Cada Properties es así (todo se refiere al servicio):
		 id : int /
	 	 nombre : String
	 	 precio : int
	 	 unidad : String
		 */
		List<Properties> servicios = dao.serviciosHotelGE(hotel_id);
		
		if(servicios==null) {
			JsonObject error = new JsonObject();
			error.addProperty("error","Ha habido un problema buscando los servicios");
			return error.toString();
		}
		
		JsonArray res = new JsonArray();
		JsonObject elem;
		for( Properties serv : servicios ) {
			elem = new JsonObject();
			
			elem.addProperty("id",(int) serv.get("id"));
			elem.addProperty("nombre",(String) serv.get("nombre"));
			
			// Precio y unidad podrían ser campos a null
			
			elem.addProperty("precio", serv.get("precio") != null ?
								(int) serv.get("precio") : null);
			
			elem.addProperty("unidad", serv.get("unidad") != null ? 
							 (String) serv.get("unidad") : null);
						
			res.add(elem);
		}
		return res.toString();
	}

    @GetMapping("/hotel/disponibles")
    @ResponseBody
    public String disponibles(@RequestParam Date fecha_inicio, @RequestParam Date fecha_fin){

        List<Disponibles> disponibles = dao.disponibles(fecha_inicio, fecha_fin);

        JsonArray res = new JsonArray();
        JsonArray habitaciones;

        JsonObject elem;
        JsonObject habitacion;

        int id;

        Disponibles disponible;

        int i = 0;

        while( i < disponibles.size()){

            disponible = disponibles.get(i);

            elem = new JsonObject();
            habitaciones = new JsonArray();

            id = disponible.getHotel_id();
            elem.addProperty("hotel_id", id);

            while (disponible.getHotel_id() == id){

                habitacion = new JsonObject();

                habitacion.addProperty("tipo_hab_id", disponible.getTipo_ha_id());
                habitacion.addProperty("nombre", disponible.getNombre_tipo());
                habitacion.addProperty("precio_total", disponible.getPrecio_total());

                habitaciones.add(habitacion);

                i++;
                if (i < (disponibles.size())){
                    disponible = disponibles.get(i);
                }
                else{
                    break;
                }
            }

            elem.add("habitaciones", habitaciones);

            res.add(elem);

        }

        return res.toString();
    }

    
}
