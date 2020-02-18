package ingsoft1920.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class RmProcesadorDatos {
	
	public static double procesar(Calendar calendar, String localizacion) { //EUN
		int month = calendar.get(Calendar.MONTH);
		
		int estacion=0;//P=0 V=1 O=2 I=3
		
		switch(localizacion) {
			case "EUN":
			case "EUS":
			case "ASN":
			case "ASS":
			case "AMNN":
			case "AMNS":
			case "AFN":
				if (month > 5 && month <= 8)
					estacion=1;
				else if (month > 2 && month <= 5)
					estacion=0;
				else if (month > 8)
					estacion=2;
				else if (month <= 2)
					estacion=3;
				break;
			case "AMSN":
			case "AMSS":
			case "AFS":
			case "OC":
				if (month > 5 && month <= 8)
					estacion=3;
				else if (month > 2 && month <= 5)
					estacion=2;
				else if (month > 8)
					estacion=0;
				else if (month <= 2)
					estacion=1;
				break;
		}
		
		//String[] docs = {"EUNVerano1", "EUNVerano2" /*.....*/};

		ArrayList<Integer> precios = new ArrayList<Integer>();
		ArrayList<Double> ratings = new ArrayList<Double>();

			String path = "C:\\Users\\User\\Documents\\UiPath\\" + localizacion + estacion + ".csv"; //Cambiar por ruta del repositorio?
			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String linea = "";

			try {
				linea = br.readLine(); //saltar la primera linea
				while ( (linea = br.readLine()) != null) {
					linea = linea.replace("\"", "");
					String data[] = linea.split(",");
					
					// si faltan datos, ignoro la linea
					if (data.length <= 3 || data[0].equals("") || data[1].equals("") || data[2].equals("")) continue; 

					ratings.add(Integer.parseInt(data[1]) + (double) Integer.parseInt(data[2])/10);
					precios.add(Integer.parseInt((data[0].replaceAll("[^\\d.]", "")))); //se salta el simbolo de euros
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//Media ponderada segun ratings
		int media = 0;
		int suma = 0;
		for (int i = 0; i < precios.size(); i++) {
			media += precios.get(i) * ratings.get(i);
			suma += ratings.get(i);
		}
		media = media / suma;
		
		return media;
	}

}
