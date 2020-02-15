package ingsoft1920.demo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Lo de scanBasePackages es necesario para indicarle a Spring 
// que busque los controladores en nuestro paquete controller
@SpringBootApplication(scanBasePackages = {"ingsoft1920.controller"})
public class CmApplication {
	final static Logger logger = LogManager.getLogger(CmApplication.class.getName());

	public static void main(String[] args) {
		//logger.warn("Aplicacion iniciada");
		SpringApplication.run(CmApplication.class, args);
	}
}
