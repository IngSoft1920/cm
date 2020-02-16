package ingsoft1920.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class RmProcesadorDatos {
	
	public static double procesar(/*parametros*/) {
		//TODO recibir qué precio queremos calcular?
		
		String[] docs = {"EUNVerano1", "EUNVerano2" /*.....*/};

		ArrayList<Integer> precios = new ArrayList<Integer>();
		ArrayList<Double> ratings = new ArrayList<Double>();

		for (String doc : docs) {

			String path = "C:\\Users\\User\\Documents\\UiPath\\Prueba1\\" + doc + ".csv"; //Cambiar por ruta del repositorio?
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
					//System.out.println(linea);

					String data[] = linea.split(",");
					if (data.length <= 3) continue; // si faltan datos, ignoro la linea

					ratings.add(Integer.parseInt(data[1]) + (double) Integer.parseInt(data[2])/10);
					precios.add(Integer.parseInt((data[3])));

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
