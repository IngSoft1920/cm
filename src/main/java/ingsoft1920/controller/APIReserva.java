package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ingsoft1920.dao.ReservaDAO;

import java.util.HashMap;

//Mas info sobre JSON y Spring aqui:
//https://www.baeldung.com/spring-request-response-body
@Controller
public class APIReserva {
    //OPCION MAS SIMPLE: TENEMOS QUE HACER MAS TRABAJO PERO TENEMOS MAS CONTROL SOBRE
    //LO QUE OCURRE

    //Con la etiqueta responseBody estamos especificando que el string que devolvamos
    //sera el cuerpo de la respuesta HTTP que daremos a la peticion
    @ResponseBody
    @GetMapping("/datoUsuario1")
    public String getgetNumeroHabitacionesReservadas(@RequestBody String req) {
        //Suponiendo que me llega algo como
        /*
         * {
         * 	"hotel_id":"12",
         *  "fecha_inicio":"yyyy-MM-dd"
         *  "fecha_fin":"yyyy-MM-dd"
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        //Vamos accediendo a sus propiedades, y las guardamos
        int hotel_id = obj.get("hotel_id").getAsInt();
        String fechaInicio = obj.get("fecha_inicio").getAsString();
        String fechaFin = obj.get("fecha_fin").getAsString();

        ReservaDAO reserva = new ReservaDAO();

        HashMap<String, int[]> disponibles = reserva.getNumeroHabitacionesDisponibles(hotel_id, fechaInicio, fechaFin);

        //Instanciamos un nuevo objeto Json
        JsonObject res = new JsonObject();

        //Declaramos una nueva lista (array) en Json y añadimos elementos
        JsonArray tipos = new JsonArray();
        JsonArray precios = new JsonArray();
        for (String tipo: disponibles.keySet()) {
            tipos.add(tipo);
            precios.add(disponibles.get(tipo)[1]);
        }

        //Si estamos añadiendo un objeto Json (como es un array) a otro, tenemos que utilizar
        //el metodo add("nombrePropiedad",valor)
        res.add("tipos", tipos);
        res.add("precios", tipos);

        //Obtenemos la representacion en String del objeto JSON y la enviamos como respuesta
        return res.getAsString();

        //Devolvera lo siguiente en el cuerpo de la respuesta
        /*
         * {
         * 	"tipos":[normal, premium, ...]
         *  "precios":[282, 739,...]
         * }
         */
    }

}