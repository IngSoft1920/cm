package ingsoft1920.cm.data;

import java.util.LinkedList;

import ingsoft1920.cm.model.Peticion;

public class ExtractorDatos {

	public static void main(String[] args) {

		while (true) {

			LinkedList<Peticion> peticiones = null; // = PeticionesDAO.get() //LinkedList de objetos Peticion

			for (Peticion pet : peticiones) {
				if (pet.getEstado() == 0) {

					// UIPath procesa...

					// UIPath mete en BBDD
					// o en csv y de ahi a BBDD TODO

					// PeticionesDAO.cambiarEstado(pet.getId());
				}
			}
		}

	}
}
