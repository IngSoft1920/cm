package ingsoft1920.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test {
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hola test";
	}

	@RequestMapping("/hola")
	@ResponseBody
	public String hola() {
		return "Hola mundo";
	}
	
}
