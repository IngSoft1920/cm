package ingsoft1920.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ingsoft1920.bean.Hotel;
import ingsoft1920.dao.ReservaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.LinkedList;

public class APIRRHH {


    @ResponseBody
    @GetMapping("/cojoBaja")
    public String puedeCogerBaja(@RequestBody String req) {
        /*
         * {
         * 	"id_empleado":7867
         *  "id_baja": 4
         *  "duracion": 8657
         * }
         */

        //Parseamos el texto a un JsonObject
        JsonObject obj = (JsonObject) JsonParser.parseString(req);

        LinkedList<String> res = new LinkedList<String>();

        //Vamos accediendo a sus propiedades, y las guardamos
        int idEmpleado = obj.get("id_empleado").getAsInt();
        int idBaja = obj.get("id_baja").getAsInt();
        int duracion = obj.get("duracion").getAsInt();

        ReservaDAO reserva = new ReservaDAO();

        String resultado = "denegada";//Por ahora denegamos todas las bajas

        obj = new JsonObject();
        obj.addProperty("id_empleado", idEmpleado);
        obj.addProperty("id_bajo", idBaja);
        obj.addProperty("resultado", resultado);


        return obj.getAsString();

        //Devolvera lo siguiente en el cuerpo de la respuesta
        /*
         * {
         * 	"id_empleado":7867
         *  "id_baja": 4
         *  "resultado": "denegado"
         * }
         */
    }
}
