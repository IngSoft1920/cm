package ingsoft1920.cm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Producto;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.dao.ProveedorDAO;
import ingsoft1920.cm.dao.ProductoDAO;

@Controller
public class ProveedoresController {
	@Autowired
	ProveedorDAO ProveedorDao;
	@Autowired
	ProductoDAO ProductoDao; 

	
	@GetMapping("/hotel-proveedores/{hotel_id}")
	@ResponseBody
	public String listarProveedores(@PathVariable int hotel_id) {
		List<Proveedor> listaProveedores = ProveedorDao.proveedoresPorHotel(hotel_id);
		
		
		JsonArray res = new JsonArray();
		JsonObject elem;
		for(Proveedor p : listaProveedores) {
			elem = new JsonObject();
			
			elem.addProperty("proveedor_id",p.getId());
			elem.addProperty("empresa", p.getEmpresa());

			List<Properties>listaProductos = ProveedorDao.productos(p.getId());

			JsonArray productos = new JsonArray();
			JsonObject unproducto;
			for(Properties producto : listaProductos) {
				unproducto = new JsonObject();
				unproducto.addProperty("id", (Integer) producto.get("id")); 
				unproducto.addProperty("nombre",producto.getProperty("nombre"));
				unproducto.addProperty("precio_venta",(Integer)producto.get("precio_venta"));
				unproducto.addProperty("unidad_medida",producto.getProperty("unidad_medida"));
				productos.add(unproducto);
			}
			
			
			elem.add("productos",productos);
			res.add(elem);
			
		}
		
		
		return res.toString();
		
	}

}
