package ingsoft1920.cm.fna;

import java.util.HashMap;
import java.util.Iterator;

public class PruebasFNA {
	public static void main(String[]args) {
		HashMap<Integer, BeneficiosGastosModel> prueba = new HashMap <Integer, BeneficiosGastosModel> ();
		prueba=FacturaDAO.sumaReservas();
		prueba=FacturaDAO.beneficiosServicios(prueba);
		prueba=ConexionEM.peticionSueldoEmpleados(prueba);
		prueba=FacturaDAO.gastosProveedores(prueba);
		for(BeneficiosGastosModel elem: prueba.values()) {
			System.out.println(elem.getNombreHotel()+": ");
			System.out.println("Dinero Reservas");
			for (String aux: elem.getSumaReservas().keySet()) {
			    System.out.println(aux+": "+elem.getSumaReservas().get(aux));
			}
			System.out.println("Dinero Facturas");
			for (String aux: elem.getSumaFacturas().keySet()) {
			    System.out.println(aux+": "+elem.getSumaFacturas().get(aux));
			}
			System.out.println("Dinero Empleados");
			for (String aux: elem.getSueldoEmpleados().keySet()) {
			    System.out.println(aux+": "+elem.getSueldoEmpleados().get(aux));
			}
			System.out.println("Dinero Productos");
			for (String aux: elem.getGastoComida().keySet()) {
			    System.out.println(aux+": "+elem.getGastoComida().get(aux));
			}
			System.out.println("Total");
			System.out.println(elem.getTotal());
		}
		
		HashMap<Integer, BeneficiosGastosModel> prueba2 = new HashMap <Integer, BeneficiosGastosModel> ();
		prueba2=FacturaDAO.beneficiosServicios(prueba);
		for(BeneficiosGastosModel elem: prueba2.values()) {
			for (String aux: elem.getSumaFacturas().keySet()) {
			    System.out.println(aux);
			}
		}
		Double[] resultado = FacturaDAO.beneficiosTotalesSeparados();
		for(int i =0;i<resultado.length;i++) {
			System.out.println(resultado[i]);
		}
	}

}
