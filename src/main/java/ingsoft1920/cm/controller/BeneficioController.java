package ingsoft1920.cm.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ingsoft1920.cm.fna.FacturaDAO;
import ingsoft1920.cm.fna.BeneficiosGastosModel;
import ingsoft1920.cm.fna.ConexionEM;
import ingsoft1920.cm.fna.BeneficioBean;

@Controller
public class BeneficioController {
	private HashMap <Integer, BeneficiosGastosModel> beneficios_gastos = new HashMap <Integer, BeneficiosGastosModel> ();
	
	
	 @GetMapping ("/beneficio0")
	   public String beneficios(Model m) {
		 
		beneficios_gastos = FacturaDAO.sumaReservas();
		beneficios_gastos = FacturaDAO.gastosProveedores(beneficios_gastos);
		beneficios_gastos = ConexionEM.peticionSueldoEmpleados(beneficios_gastos);
		beneficios_gastos = FacturaDAO.beneficiosServicios(beneficios_gastos);
		// ModelAndView modelAndView = new ModelAndView("fna/beneficio0.jsp", "command", bg);
		 
		  
		 BeneficioBean bg=new BeneficioBean();
		
		 m.addAttribute("BeneficioBean",bg);
		 m.addAttribute("treasureMap",beneficios_gastos);
		 
		  return "fna/beneficio0.jsp";
	   }

	/*
	 @GetMapping("/beneficio")
	public String beneficio(Model model) {
		beneficios_gastos = FacturaDAO.sumaReservas();
		beneficios_gastos = FacturaDAO.gastosProveedores(beneficios_gastos);
		beneficios_gastos = ConexionEM.peticionSueldoEmpleados(beneficios_gastos);
		beneficios_gastos = FacturaDAO.beneficiosServicios(beneficios_gastos);
		model.addAttribute("treasureMap", beneficios_gastos);
		return "fna/beneficio.jsp";
	}
	*/
	/*this.nombreHotel = nombreHotel;
	this.sumaReservas = new HashMap <String, Double> ();
	this.sumaFacturas = new HashMap <String, Double> ();
	this.gastoComida = gastoComida;
	this.sueldoEmpleados = new HashMap <String, Double> ();
	this.total=0;
	*/
	@PostMapping("/beneficio0")
	public String showBeneficio (@ModelAttribute("BeneficioBean") BeneficioBean bg,
			Model model) {
		
		
		model.addAttribute("treasureMap", beneficios_gastos);
		//model.addAttribute("Hoteles", bg.getNombresHotel());
		model.addAttribute("reserva", bg.getReserva());
		model.addAttribute("servicios", bg.getServicios());
		model.addAttribute("empleados", bg.getComida()); //boolean
		model.addAttribute("comida", bg.getEmpleados());
		
		return "fna/beneficios.jsp";
	}
	
	/*
	@ModelAttribute("hotelesList")
	   public List<String> getHotelesList() {
	      List<String> hotelesList = new ArrayList<String>();
	      //System.out.println("aaa");
	      for(BeneficiosGastosModel e: beneficios_gastos.values()) {
	    	  	hotelesList.add(e.getNombreHotel());
	    	  	//System.out.println("xxx");
	      }
	      //hotelesList.add("Mi puta viuda");
	      //hotelesList.add("La tuya");
	      
	      return hotelesList;
	   }
	
	@ModelAttribute("habitacionesList")
	   public List<String> getHabitacionesList() {
		List<String>habitacionesList = new ArrayList<String>();
		
		Iterator<String> it;
	      
	      for(BeneficiosGastosModel e: beneficios_gastos.values()) {
	    	  	it= e.getSumaReservas().keySet().iterator();
	    	  	String st=it.next();
	    	  	while(st!=null) {
	    	  		if(!habitacionesList.contains(st))
	    	  		habitacionesList.add(st);
	    	  		st=it.next();
	    	  	}
	      }
	      
		
	     // habitacionesList.add("premium");
	      //habitacionesList.add("caca");
	      //habitacionesList.add("genial");
	      return habitacionesList;
	   }
	
	@ModelAttribute("serviciosList")
	   public List<String> getServiciosList() {
		Iterator<String> it;
	      List<String>serviciosList = new ArrayList<String>();
	      
	      for(BeneficiosGastosModel e: beneficios_gastos.values()) {
	    	  	it= e.getSumaFacturas().keySet().iterator();
	    	  	String st=it.next();
	    	  	while(st!=null) {
	    	  		if(!serviciosList.contains(st))
	    	  		serviciosList.add(st);
	    	  		st=it.next();
	    	  	}
	      }
	      
	      //serviciosList.add("Piscina");
	      //serviciosList.add("Discoteca");
	      
	      
	      return serviciosList;
	   }
	
	@ModelAttribute("empleadosList")
	   public List<String> getEmpleadosList() {
		Iterator<String> it;
	      List<String>empleadosList = new ArrayList<String>();
	      
	      for(BeneficiosGastosModel e: beneficios_gastos.values()) {
	    	  	it= e.getSumaFacturas().keySet().iterator();
	    	  	String st=it.next();
	    	  	while(st!=null) {
	    	  		if(!empleadosList.contains(st))
	    	  		empleadosList.add(st);
	    	  		st=it.next();
	    	  	}
	      }
	      
	      //empleadosList.add("Cocinero");
	      //empleadosList.add("Disco");
	      
	      return empleadosList;
	   }


	/*
	@ModelAttribute("camposList")
	   public List<String> CamposList() {
	      List<String> camposList = new ArrayList<String>();
	      camposList.add("Tipo de Habitacion");
	      camposList.add("Servicios");
	      camposList.add("Comida");
	      camposList.add("Empleados");
	      return camposList;
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
