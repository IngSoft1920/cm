package ingsoft1920.cm.fna;

import java.util.HashMap;
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
				JsonArray id_HotelLista=obj.get("hotel_id").getAsJsonArray();
				int[] id_hotel= new int[id_HotelLista.size()];
				JsonArray sueldoLista=obj.get("sueldo").getAsJsonArray();
				double[] sueldo= new double[sueldoLista.size()];
				JsonArray id_empleadoLista=obj.get("empleado_id").getAsJsonArray();
				int[] id_empleado= new int[id_empleadoLista.size()];
				JsonArray rolLista=obj.get("rol").getAsJsonArray();
				String[] rol = new String[rolLista.size()];
				JsonArray incentivoLista=obj.get("incentivo").getAsJsonArray();
				double[] incentivo= new double[incentivoLista.size()];
				//Creamos un objeto auxiliar
				BeneficiosGastosModel aux;
				Double sueldoEmpleados;
				//Añadimos la info
				for(int i=0;i<id_HotelLista.size();i++) {
					id_hotel[i]=id_HotelLista.get(i).getAsInt();
					sueldo[i]=sueldoLista.get(i).getAsDouble();
					id_empleado[i]=id_HotelLista.get(i).getAsInt();
					rol[i]=sueldoLista.get(i).getAsString();
					incentivo[i]=id_HotelLista.get(i).getAsDouble();
					aux=map.get(id_hotel[i]);
					System.out.println("Hotel_id: "+id_hotel[i]+" , sueldo: "+sueldo[i]+", id_empleado: "+id_empleado[i]+", rol: "+rol[i]+", incentivo: "+incentivo[i]);
					if(aux!=null) {
						sueldoEmpleados = aux.getSueldoEmpleados().get(rol[i]);
						if(sueldoEmpleados!=null) {
							//Ya hay una entrada creada para ese rol. Actualizar el value
							aux.getSueldoEmpleados().replace(rol[i], sueldoEmpleados+sueldo[i]+incentivo[i]);
						}
						else {
							//No hay una entrada creada, la creamos y añadimos los valores.
							aux.getSueldoEmpleados().put(rol[i], sueldo[i]+incentivo[i]);
						}
					
					}
					else {
						//Si se mete aqui, deberia haber algun error de sincronizacion entre las distintas aplicaciones. No veo 
						//factible que haya un hotel cuyo unico movimiento monetario sea pagar a los empleados.
						//Aun asi, por si acaso, creamos una nueva entrada en el map
						aux=new BeneficiosGastosModel("",0,0,0);
						aux.getSueldoEmpleados().put(rol[i], sueldo[i]+incentivo[i]);
						map.put(id_hotel[i], aux);
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