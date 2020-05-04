package ingsoft1920.cm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ingsoft1920.cm.bean.Hotel;
import ingsoft1920.cm.dao.HotelDAO;


@SpringBootApplication
public class CmApplication {	
	final static Logger logger = LogManager.getLogger(CmApplication.class.getName());

	public static void main(String[] args) {
		logger.warn("Aplicacion iniciada");
		SpringApplication.run(CmApplication.class, args);
	}
	
}
