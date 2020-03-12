package ingsoft1920.cm.controller;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonParser;
import ingsoft1920.cm.apiout.APIout;
import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Tipo_Habitacion;
import ingsoft1920.cm.dao.CategoriaDAO;
import ingsoft1920.cm.dao.TipoHabitacionDAO;
import ingsoft1920.cm.model.Disponibles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.bean.Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.dao.HotelDAO;


@Controller
public class HotelController {
	
	@Autowired
	HotelDAO dao;
	
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
		
		Map<Servicio,Hotel_Servicio> servicios = dao.serviciosHotel(hotel_id);
		
		if(servicios==null) {
			JsonObject error = new JsonObject();
			error.addProperty("error","Ha habido un problema buscando los servicios");
			return error.toString();
		}
		
		JsonArray res = new JsonArray();
		JsonObject elem;
		for(Entry<Servicio,Hotel_Servicio> entrada : servicios.entrySet()) {
			elem = new JsonObject();
			
			elem.addProperty("id",entrada.getKey().getId());
			elem.addProperty("nombre",entrada.getKey().getNombre());
			elem.addProperty("precio",entrada.getValue().getPrecio());
			elem.addProperty("unidad",entrada.getValue().getUnidad_medida());
			
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

    /**
     * TODO consultas para obtener el nombre Servicio y puerto
     * @param h
     * @param habs
     * @param servs
     * @param cats
     */
    public void enviarHorel(Hotel h, List<Hotel_Tipo_Habitacion> habs, List<Hotel_Servicio> servs, List<Hotel_Categoria> cats){

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

	    JsonObject jsonO = new JsonObject();

        jsonO.addProperty("id", h.getId());
        jsonO.addProperty("nombre", h.getNombre());
        jsonO.addProperty("descripcion", h.getDescripcion());
        jsonO.addProperty("estrellas", h.getEstrellas());
        jsonO.addProperty("continente", h.getContinente());
        jsonO.addProperty("pais", h.getPais());
        jsonO.addProperty("ciudad", h.getCiudad());

        JsonArray habitaciones = new JsonArray();
        JsonObject habitacion;

        for (Hotel_Tipo_Habitacion hab : habs) {
            habitacion = new JsonObject();

            habitacion.addProperty("id", hab.getTipo_hab_id());
            habitacion.addProperty("nombre", tipoHabitacionDAO.get(hab.getTipo_hab_id()).getId());
            habitacion.addProperty("num_disponibles", hab.getNum_disponibles());

            habitaciones.add(habitacion);
        }

        jsonO.add("habitaciones", habitaciones);

        JsonArray categorias = new JsonArray();
        JsonObject categoria;

        for (Hotel_Categoria cat: cats){
            categoria = new JsonObject();

            categoria.addProperty("id", cat.getCategoria_id());
            categoria.addProperty("nombre", categoriaDAO.get(cat.getCategoria_id()).getId());

            categorias.add(categoria);
        }

        jsonO.add("categorias", categorias);

        JsonArray servicios = new JsonArray();
        JsonObject servicio;

        for (Hotel_Servicio serv : servs) {
            servicio = new JsonObject();

            servicio.addProperty("id", serv.getServicio_id());
            servicio.addProperty("nombre", "nombre");
            servicio.addProperty("precio", serv.getPrecio());
            servicio.addProperty("unidad", serv.getUnidad_medida());

            servicios.add(servicio);
        }

        jsonO.add("servicios", servicios);

        APIout.enviar(jsonO.toString(), 7001, "/recibirHotel");
    }
}
