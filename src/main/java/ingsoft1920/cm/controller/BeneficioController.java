package ingsoft1920.cm.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ingsoft1920.cm.fna.FacturaDAO;
import ingsoft1920.cm.fna.BeneficiosGastosModel;
import ingsoft1920.cm.fna.ConexionEM;

@Controller
public class BeneficioController {
	private HashMap <Integer, BeneficiosGastosModel> beneficios_gastos = new HashMap <Integer, BeneficiosGastosModel> ();
	
	@GetMapping("/beneficio")
	public String beneficio(Model model) {
		beneficios_gastos = FacturaDAO.sumaReservas();
		beneficios_gastos = FacturaDAO.gastosAlimentosPorHotel(beneficios_gastos);
		beneficios_gastos = ConexionEM.peticionSueldoEmpleados(beneficios_gastos);
		beneficios_gastos = FacturaDAO.beneficiosServicios(beneficios_gastos);
		model.addAttribute("treasureMap", beneficios_gastos);
		return "fna/beneficio.jsp";
	}

	/*
	 * @PostMapping("/beneficio") public String
	 * beneficioHotel(@RequestParam("hotel_id") int hotel_id) {
	 * 
	 * //ganancias=beneficio.beneficioPorHotel(hotel_id);
	 * 
	 * 
	 * return "redirect:beneficio-result"; }
	 * 
	 * @GetMapping("/beneficio-result") public String mapBeneficio(Model model) {
	 * model.addAttribute("ganancias", ganancias); return
	 * "fna/beneficio-result.jsp"; }
	 * 
	 */

}
