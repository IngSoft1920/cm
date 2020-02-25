package ingsoft1920.cm.apiout;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ingsoft1920.cm.bean.Empleado;
import ingsoft1920.cm.bean.Reserva;

public class APIout {

	private static final String SERVIDOR = "http://piedrafita.ls.fi.upm.es";
	private static final Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

	public static void enviar(String json, int puerto, String endpoint) {

		String destino = SERVIDOR + ":" + puerto + endpoint;
		HttpPost post = new HttpPost(destino);
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(json, "UTF-8"));

		HttpClient client = HttpClientBuilder.create().build();

		HttpResponse respuesta = null;
		try { respuesta = client.execute(post); }
		catch (IOException e) { e.printStackTrace(); return; }

		int codigoRespuesta = respuesta.getStatusLine().getStatusCode();

		if (codigoRespuesta != 200) {
			System.out.println("Ha habido un error, con código " + codigoRespuesta);
			return;
		}

		// Leemos la respuesta
		String mensRespuesta = "";
		try {
			mensRespuesta = EntityUtils.toString(respuesta.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(mensRespuesta);
	}

	public static void enviarReserva(Reserva r) {
		String reservaJson = gson.toJson(r, Reserva.class);

		// Cambiar el endpoint al que diga dho
		enviar(reservaJson, 7002, "/recibirReserva");
	}

	public static void enviarEmpleado(Empleado e) {
		String empleadoJson = gson.toJson(e, Empleado.class);
		enviar(empleadoJson, 7002, "/creaEmpleado");
	}

	public static void eliminarEmpleado(String email) {
		JsonObject jsonO = new JsonObject();
		jsonO.addProperty("email", email);

		enviar(jsonO.toString(), 7002, "/eliminarEmpleado");
	}

	public static void asignarNomina(int id_empleado, double valor) {
		JsonObject jsonO = new JsonObject();
		jsonO.addProperty("id_empleado", id_empleado);
		jsonO.addProperty("valor", valor);

		enviar(jsonO.toString(), 7002, "/asignaNomina");
	}

	public static void main(String[] args) {

	}

}
