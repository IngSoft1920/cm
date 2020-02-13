
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RmProcesadorDatos {

	public static void main (String []args) {

		//TODO recibir qué precio queremos calcular?
		
		int precioBase = 100;
		int modCompetencia = 1;
		int modOcupacionActual = 1;
		int precioFinal = 0;
		
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
		
		for (int i = 0; i < precios.size(); i++) {
			modCompetencia = 1; //TODO hacer el calculo con precio y valoraciones
		}
		
		modOcupacionActual = 1 ; //TODO llamar a la API de CM para conseguirlo!
				
		precioFinal = precioBase * modCompetencia * modOcupacionActual; 
		//TODO enviarlo a CM
	}
}
