package ingsoft1920.cm.fna;

import java.util.HashMap;
import java.util.Iterator;

public class PruebasFNA {
	public static void main(String[]args) {
		HashMap<Integer, BeneficiosGastosModel> prueba = new HashMap <Integer, BeneficiosGastosModel> ();
		prueba=ConexionEM.peticionSueldoEmpleados(prueba);
		for(BeneficiosGastosModel elem: prueba.values()) {
			for (String aux: elem.getSueldoEmpleados().keySet()) {
			    System.out.println(aux);
			}
		}
	}

}
