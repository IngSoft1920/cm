package ingsoft1920.cm.fna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConexionEM {
	public static HashMap<Integer, BeneficiosGastosModel> peticionSueldoEmpleados(HashMap <Integer, BeneficiosGastosModel> map) {
		try {
			HttpClient client= new HttpClient("http://piedrafita.ls.fi.upm.es:7002/sueldoHotel","GET");
			int respCode = client.getResponseCode();
			System.out.println(respCode);
			if(respCode==200){
				System.out.println("conexion establecida");
				//parseamos respuesta
				String resp=client.getResponseBody();
				JsonObject obj = (JsonObject) JsonParser.parseString(resp);
				//obtenemos los campos
				JsonArray id_HotelLista=obj.get("id_hotel").getAsJsonArray();
				int[] id_hotel= new int[id_HotelLista.size()];
				JsonArray sueldoLista=obj.get("valor").getAsJsonArray();
				double[] sueldo= new double[sueldoLista.size()];
				//Creamos un objeto auxiliar
				BeneficiosGastosModel aux;
				//Añadimos la info
				for(int i=0;i<id_HotelLista.size();i++) {
					id_hotel[i]=id_HotelLista.get(i).getAsInt();
					sueldo[i]=sueldoLista.get(i).getAsDouble();
					aux=map.get(id_hotel[i]);
					System.out.println("Hotel_id: "+id_hotel[i]+" , sueldo: "+sueldo[i]);
					if(aux!=null) {
						//Añadimos el gasto en pagar empleados
						aux.setSueldoEmpleados(sueldo[i]);
					}
					else {
						//Si se mete aqui, deberia haber algun error de sincronizacion entre las distintas aplicaciones. No veo 
						//factible que haya un hotel cuyo unico movimiento monetario sea pagar a los empleados
					}
				}
			}
			else
				System.out.println("conexion fallida");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}