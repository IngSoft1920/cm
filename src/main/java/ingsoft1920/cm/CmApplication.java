package ingsoft1920.cm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Lo de scanBasePackages es necesario para indicarle a Spring
// qué paquetes tiene que explorar para encontrar los beans,
// controladores, etc

@SpringBootApplication
public class CmApplication {
	final static Logger logger = LogManager.getLogger(CmApplication.class.getName());

	public static void main(String[] args) {
		logger.warn("Aplicacion iniciada");
		SpringApplication.run(CmApplication.class, args);
	}
}
