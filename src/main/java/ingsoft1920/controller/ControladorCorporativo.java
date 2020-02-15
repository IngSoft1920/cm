package ingsoft1920.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControladorCorporativo {

	@RequestMapping("/home-corp")
	public String homeCorporativo() {
		return "home-corp.jsp";
	}

}
