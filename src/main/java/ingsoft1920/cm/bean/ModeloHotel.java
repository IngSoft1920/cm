package ingsoft1920.cm.bean;

import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.auxiliares.Hotel_Categoria;
import ingsoft1920.cm.bean.auxiliares.Hotel_Servicio;
import ingsoft1920.cm.bean.auxiliares.Hotel_Tipo_Habitacion;

/**
 * @author luism
 * Clase Bean para rellenar en BD el formulario de anadir-hotel.
 *
 */
public class ModeloHotel {

	@Autowired
	public Hotel hotel;
  	@Autowired
	public Hotel_Categoria categorias;
	@Autowired
	public Hotel_Servicio servicios;
	@Autowired
	public Hotel_Tipo_Habitacion tiposHabitacion;

}
