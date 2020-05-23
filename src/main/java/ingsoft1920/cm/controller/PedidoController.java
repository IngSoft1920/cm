package ingsoft1920.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PedidoController {

	@PostMapping("/pedido")
	@ResponseBody
	public String recibirPedido() {
		
		return "";
	}
	
}
