package ingsoft1920.dbhulio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PruebaDB {
	
	public static void main(String[] args) {
		//SpringApplication.run(PruebaDB.class, args);
		
		new Conexion().conectar();;
	}

}
