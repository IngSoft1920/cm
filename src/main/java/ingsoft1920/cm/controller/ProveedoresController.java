package ingsoft1920.cm.controller;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.dao.ProductoDAO;
import ingsoft1920.cm.dao.ProveedorDAO;

@Controller
public class ProveedoresController {
	@Autowired
	ProveedorDAO proveedorDao;
	@Autowired
	ProductoDAO productoDao; 

	@GetMapping("/hotel-proveedores/{hotel_id}")
	@ResponseBody
	public String enviarProveedores(@PathVariable int hotel_id) {
		Gson jsonMaker = new Gson();
		
		List<Proveedor> proveedoresHotel = proveedorDao.proveedoresPorHotel(hotel_id);
		JsonArray res = new JsonArray();
		
		JsonObject elem;
		for( Proveedor p : proveedoresHotel ) {
			elem = new JsonObject();
			  elem.addProperty("proveedor_id",p.getId());
			  elem.addProperty("empresa",p.getEmpresa());
			  elem.add("productos",jsonMaker.toJsonTree( proveedorDao.productos(p.getId()) ));
			
			res.add(elem);
		}
		return res.toString();
	}

}
