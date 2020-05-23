package ingsoft1920.cm.apiout;

import java.io.IOException;	

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

// Esta clase, con su método enviar(), nos permite mandar json's a otros equipos
// con peticiones HTTP POST
public class APIout {

	private static final String SERVIDOR = "http://piedrafita.ls.fi.upm.es";
	
	public static void enviar(String json, int puerto, String endpoint) {
		String destino = SERVIDOR + ":" + puerto + endpoint;
		
		HttpPost post = new HttpPost(destino);
		  post.addHeader("Content-Type", "application/json");
		  post.setEntity(new StringEntity(json, "UTF-8"));

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse respuesta = null;
		try { respuesta = client.execute(post); }
		catch (IOException e) { e.printStackTrace(); return; }
		
		handleResponse(respuesta);
	}
	
	public static void post(int puerto,String endpoint) {
		HttpResponse respuesta = null;
		try {
			respuesta = HttpClients.createDefault().execute( new HttpPost(SERVIDOR+":"+puerto+endpoint) );
			
		} catch (Exception e) { e.printStackTrace(); }
		
		handleResponse(respuesta);
	}
	
	
	private static void handleResponse(HttpResponse res) {
		int codigoRespuesta = res.getStatusLine().getStatusCode();
		if (codigoRespuesta != 200) {
			System.out.println("Ha habido un error, con código " + codigoRespuesta);
			return;
		}

		// Leemos la respuesta
		String mensRespuesta = "";
		try { mensRespuesta = EntityUtils.toString(res.getEntity()); }
		catch (IOException e) { e.printStackTrace(); }

		System.out.println(mensRespuesta);
	}
}
