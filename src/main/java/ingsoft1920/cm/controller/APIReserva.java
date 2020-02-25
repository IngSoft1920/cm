package ingsoft1920.cm.controller;

import ingsoft1920.cm.dao.ReservaDAO;
import ingsoft1920.cm.model.Hotel;
import ingsoft1920.cm.model.Reserva;
import ingsoft1920.cm.model.Tipo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashSet;
import java.util.LinkedList;

@Controller
public class APIReserva {

    @ResponseBody
    @GetMapping("/precioDisponible")
    public LinkedList<String> getPrecioDisponibles(@RequestBody String req) {
        //Suponiendo que me llega algo como
        /*
         * {
         * 	"hotel_id":"12"
         *  "ubicacion":"Ayelo"
         *  "fecha_inicio":"yyyy-MM-dd"
         *  "fecha_fin":"yyyy-MM-dd"
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        //Vamos accediendo a sus propiedades, y las guardamos
        int hotel_id = obj.get("hotel_id").getAsInt();
        String ubicacion = obj.get("ubicacion").getAsString();
        String fechaInicio = obj.get("fecha_inicio").getAsString();
        String fechaFin = obj.get("fecha_fin").getAsString();

        ReservaDAO reserva = new ReservaDAO();

        LinkedList<String> res = new LinkedList<String>();

        HashSet<Reserva> reservas = reserva.getPrecios(hotel_id, ubicacion, fechaInicio, fechaFin);

        for (Reserva reserva1: reservas) {
            //Instanciamos un nuevo objeto Json
            obj = new JsonObject();

            obj.addProperty("nombre", reserva1.getHotel().getNombre());
            obj.addProperty("ubicacion", reserva1.getHotel().getUbicacion());
            obj.addProperty("hotel_id", reserva1.getHotel().getId());
            obj.addProperty("tipo", reserva1.getTipo().getTipo());
            obj.addProperty("precio", reserva1.getTipo().getPrecio());


            res.add(obj.getAsString());

        }


        return res;

        //Devolvera lo siguiente en el cuerpo de la respuesta
        /*
         * [
         * {
         *  "nombre":"Uno caro"
         * 	"ubicacion":"Ayelo"
         *  "hotel_id":"1"
         *  "tipo":"lujo"
         *  "precio":"1000000"
         * },
         * {
         *  "nombre":"Otro caro"
         * 	"ubicacion":"Ayelo"
         *  "hotel_id":"2020"
         *  "tipo":"lujo"
         *  "precio":"1000000"
         * }
         * ]
         */
    }

    @ResponseBody
    @GetMapping("/ciudades")
    public String getCiudades() {

        ReservaDAO reserva = new ReservaDAO();

        HashSet<String> ciudades = reserva.getCiudades();

        //Instanciamos un nuevo objeto Json
        JsonObject res = new JsonObject();

        //Declaramos una nueva lista (array) en Json y añadimos elementos
        JsonArray ciudades_arr = new JsonArray();
        JsonArray precios = new JsonArray();
        for (String ciudad: ciudades) {
            ciudades_arr.add(ciudad);
        }

        //Si estamos añadiendo un objeto Json (como es un array) a otro, tenemos que utilizar
        //el metodo add("nombrePropiedad",valor)
        res.add("ciudades", ciudades_arr);

        //Obtenemos la representacion en String del objeto JSON y la enviamos como respuesta
        return res.getAsString();

        //Devolvera lo siguiente en el cuerpo de la respuesta
        /*
         * {
         * 	"ciudades":[Valencia, Tavernes, ...]
         * }
         */
    }

    @ResponseBody
    @GetMapping("/hoteles")
    public String getHoteles(@RequestBody String req) {
        /*
         * {
         * 	"ubicacion":"Benitatxell",
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        //LinkedList<String> res = new LinkedList<String>();

        //Vamos accediendo a sus propiedades, y las guardamos
        String ubicacion = obj.get("ubicacion").getAsString();

        ReservaDAO reserva = new ReservaDAO();

        HashSet<Hotel> hoteles = reserva.getHotelesPorUbicacion(ubicacion);
        JsonArray res = new JsonArray();

        for (Hotel hotel: hoteles) {
            //Instanciamos un nuevo objeto Json
            obj = new JsonObject();

            obj.addProperty("nombre", hotel.getNombre());
            obj.addProperty("ubicacion", hotel.getUbicacion());
            obj.addProperty("id", hotel.getId());


            res.add(obj);

        }


        return res.getAsString();

        //Devolvera lo siguiente en el cuerpo de la respuesta
        /*
         * [
         * {
         *  "nombre":"Uno caro"
         * 	"ubicacion":"Benitaxell"
         *  "id":"1"
         * },
         * {
         *  "nombre":"Otro caro"
         * 	"ubicacion":"Benitaxell"
         *  "id":"2020"
         * }
         * ]
         */
    }

    @ResponseBody
    @GetMapping("/crearReserva")
    public void crearReserva(@RequestBody String req) {
        /*
         * {
         *
         *  "fecha_inicio":"yyyy-MM-dd"
         *  "fecha_fin":"yyyy-MM-dd"
         *  "precio":"1023124"
         *  "hotel_id":"12"
         *  "tipo":"lujo"
         *  "cliente_id":"123456789"
         *
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        //Vamos accediendo a sus propiedades, y las guardamos
        String fecha_ent = obj.get("fecha_inicio").getAsString();
        String fecha_sal = obj.get("fecha_fin").getAsString();
        double precio = obj.get("precio").getAsDouble();
        int hotel_id = obj.get("hotel_id").getAsInt();
        String tipo = obj.get("tipo").getAsString();
        int cliente_id = obj.get("cliente_id").getAsInt();

        ReservaDAO reservaDAO = new ReservaDAO();

        Reserva reserva = new Reserva(new Hotel(hotel_id, "", ""), new Tipo(tipo, precio, 0));

        reservaDAO.crearReserva(reserva, cliente_id);

    }

    @ResponseBody
    @GetMapping("/cancelarReserva")
    public void cancelarReserva(@RequestBody String req) {
        /*
         * {
         *  "id"="1234567890"
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        //Vamos accediendo a sus propiedades, y las guardamos
        int id = obj.get("id").getAsInt();

        ReservaDAO reservaDAO = new ReservaDAO();

        reservaDAO.cancelarReserva(id);

    }
}