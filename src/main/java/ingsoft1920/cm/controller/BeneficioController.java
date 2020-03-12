package ingsoft1920.cm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ingsoft1920.cm.fna.FacturaDAO;
@Controller
public class BeneficioController {
	
	private FacturaDAO beneficio=new FacturaDAO();
	private double ganancias=0;
	@GetMapping("/beneficio")
	public String beneficio() {
		return "fna/beneficio.jsp";
	}
	
	@PostMapping("/beneficio")
	public String beneficioHotel(@RequestParam("hotel_id") int hotel_id) {
		
		ganancias=beneficio.beneficioPorHotel(hotel_id);
		
		
		return "redirect:beneficio-result";
	}
	
	@GetMapping("/beneficio-result")
	public String mapBeneficio(Model model) {
		model.addAttribute("ganancias", ganancias);
		return "fna/beneficio-result.jsp";
	}
	
	

}
