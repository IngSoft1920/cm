package ingsoft1920.cm.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ingsoft1920.cm.bean.Factura;
import ingsoft1920.cm.dao.FacturaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.LinkedList;

public class FacturaController {

    @Autowired
    FacturaDAO dao;


    /*Recibe ..
    [
        {
            "importe" : 10,
            "fecha" : "2020-04-15",
            "pagado" : true,
            "cantidad_consumidad" : 12,
            "servicio_id" : 1,
            "reserva_id" : 1,
        },
        {
            "importe" : 10,
            "fecha" : "2020-04-15",
            "pagado" : false,
            "cantidad_consumidad" : 3,
            "servicio_id" : 2,
            "reserva_id" : 83,
        },
    ]
    */

    @GetMapping("/facturas")
    @ResponseBody
    public void volcarFacturas(@RequestBody String json) {

        JsonArray jsonA = JsonParser.parseString(json).getAsJsonArray();

        Factura factura = new Factura();
        JsonObject jsonO;

        for ( JsonElement jsonE : jsonA){
            if (jsonE.isJsonObject()){

                jsonO = jsonE.getAsJsonObject();

                factura.setImporte(jsonO.get("importe").getAsInt());
                factura.setFecha(Date.valueOf(jsonO.get("fecha").getAsString()));
                factura.setPagado(jsonO.get("pagado").getAsBoolean());
                factura.setCantidad_consumida(jsonO.get("cantidad_consumida").getAsInt());
                factura.setServicio_id(jsonO.get("servicio_id").getAsInt());
                factura.setReserva_id(jsonO.get("reserva_id").getAsInt());

               dao.anadir(factura);

            }
        }
    }


    /*
     [
         {
            "reserva_id" : 12
         },
         {
            "reserva_id" : 16
         }
     ]
    */
    @GetMapping("/pagar")
    @ResponseBody
    public void pagarFacturas(@RequestBody String json) {

        JsonArray jsonA = JsonParser.parseString(json).getAsJsonArray();

        JsonObject jsonO;

        for ( JsonElement jsonE : jsonA){
            if (jsonE.isJsonObject()){

                jsonO = jsonE.getAsJsonObject();

                dao.pagar(jsonO.get("reserva_id").getAsInt());

            }
        }

    }
}
