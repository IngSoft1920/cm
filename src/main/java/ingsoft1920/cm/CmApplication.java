package ingsoft1920.cm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ingsoft1920.cm.dao.EmpleadoDAO;

@SpringBootApplication
@Controller
public class CmApplication implements ErrorController {
	final static Logger logger = LogManager.getLogger(CmApplication.class.getName());

	public static void main(String[] args) {
		logger.warn("Aplicacion iniciada");
		SpringApplication.run(CmApplication.class, args);
	}



	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public String error() {
		return "/error/pagina-error.jsp";
	}


}
