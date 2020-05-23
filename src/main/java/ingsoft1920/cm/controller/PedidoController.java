package ingsoft1920.cm.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.dao.PedidoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Controller
public class PedidoController {

	@PostMapping("/pedido")
	@ResponseBody
	public String recibirPedido(@RequestBody String json) {

        PedidoDAO ped = new PedidoDAO();
        Pedido pedido = new Pedido();
        JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();

        String fecha = jsonO.get("fecha").getAsString();
        int hotelId = jsonO.get("hotel_id").getAsInt();
        int proveedor = jsonO.get("proveedor_id").getAsInt();

        pedido.setFecha(Date.valueOf(fecha));
        pedido.setProveedor_id(proveedor);
        pedido.setHotel_id(hotelId);

        JsonArray arr =  jsonO.getAsJsonArray("productos");

        List<Properties> props = new LinkedList<>();
        Properties prop;

        for (JsonElement element: arr) {
            prop = new Properties();
            prop.put("producto_id", element.getAsJsonObject().get("producto_id").getAsInt());
            prop.put("cantidad", element.getAsJsonObject().get("cantidad").getAsInt());
            prop.put("especificaciones", element.getAsJsonObject().get("especificaciones").getAsString());
            props.add(prop);
        }

        int ret = ped.anadir(pedido, props);
        JsonObject res = new JsonObject();
        res.addProperty("res", res.toString());

        return res.toString();
	}
	
}
