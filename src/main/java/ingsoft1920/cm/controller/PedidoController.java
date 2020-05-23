package ingsoft1920.cm.controller;

import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ingsoft1920.cm.bean.Pedido;
import ingsoft1920.cm.dao.PedidoDAO;
import ingsoft1920.cm.dao.ProveedorDAO;

@Controller
public class PedidoController {
	
	@Autowired
	private ProveedorDAO proveedorDao;
	
	@PostMapping("/pedido")
	@ResponseBody
	public void recibirPedido(@RequestBody String json) {
		JsonObject jsonO = JsonParser.parseString(json).getAsJsonObject();
		
		Pedido pedido = new Pedido();
		  pedido.setFecha( Date.valueOf( jsonO.get("fecha").getAsString() ) );
		  pedido.setHotel_id( jsonO.get("hotel_id").getAsInt() );
		  pedido.setProveedor_id( jsonO.get("proveedor_id").getAsInt() );
		
		List<Properties> productos = StreamSupport
										.stream(jsonO.get("productos").getAsJsonArray().spliterator(), false)
										.map( jsonE -> jsonE.getAsJsonObject() )
									    .map( producto -> { 
										  Properties aux = new Properties();
										    aux.put("producto_id",producto.get("producto_id").getAsInt());
										    aux.put("cantidad",producto.get("cantidad").getAsInt());
										    
										    if( !producto.get("especificaciones").isJsonNull() )
										    	aux.put("especificaciones",producto.get("especificaciones").getAsString());
										    
										  return aux;
									     })
									    .collect( Collectors.toList() );
		
		int importe = 0;
		int precioVenta;
		for( Properties prod : productos ) {
			if( (precioVenta=
					proveedorDao
						.getPrecioCantidad((int) prod.get("producto_id"),
										   (int) prod.get("cantidad")) ) == -1 )
				return;
			
			importe += precioVenta;
		}
		pedido.setImporte(importe);
		
		
		new PedidoDAO().anadir(pedido, productos);
	}

}
