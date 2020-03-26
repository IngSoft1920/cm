package ingsoft1920.cm.bean;

/**
 * @author luism Clase Bean para rellenar en BD el formulario de anadir-hotel.
 *
 */
public class ModeloHotel {

	public class Hotel {
		
		private int id;
		private String nombre;
		private String continente;
		private String pais;
		private String ciudad;
		private String direccion;
		private int estrellas;
		private String descripcion;

		public Hotel() {
		}

		/**
		 * @param id
		 * @param nombre
		 * @param continente
		 * @param pais
		 * @param ciudad
		 * @param direccion
		 * @param estrellas
		 * @param descripcion
		 */
		public Hotel(int id, String nombre, String continente, String pais, String ciudad, String direccion,
				int estrellas, String descripcion) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.continente = continente;
			this.pais = pais;
			this.ciudad = ciudad;
			this.direccion = direccion;
			this.estrellas = estrellas;
			this.descripcion = descripcion;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		/**
		 * @return the continente
		 */
		public String getContinente() {
			return continente;
		}

		/**
		 * @param continente the continente to set
		 */
		public void setContinente(String continente) {
			this.continente = continente;
		}

		/**
		 * @return the pais
		 */
		public String getPais() {
			return pais;
		}

		/**
		 * @param pais the pais to set
		 */
		public void setPais(String pais) {
			this.pais = pais;
		}

		/**
		 * @return the ciudad
		 */
		public String getCiudad() {
			return ciudad;
		}

		/**
		 * @param ciudad the ciudad to set
		 */
		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		/**
		 * @return the direccion
		 */
		public String getDireccion() {
			return direccion;
		}

		/**
		 * @param direccion the direccion to set
		 */
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		/**
		 * @return the estrellas
		 */
		public int getEstrellas() {
			return estrellas;
		}

		/**
		 * @param estrellas the estrellas to set
		 */
		public void setEstrellas(int estrellas) {
			this.estrellas = estrellas;
		}

		/**
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

	}

	/**
	 * @author luism Dar el tipo de habitacion y rellenar el numero de habitaciones
	 *         para un hotel
	 *
	 */
	public class Hotel_Tipo_Habitacion {

		private int hotel_id;
		private int tipo_hab_id;
		private int num_disponibles;

		public Hotel_Tipo_Habitacion() {

		}

		/**
		 * @param hotel_id
		 * @param tipo_hab_id
		 * @param num_disponibles
		 */
		public Hotel_Tipo_Habitacion(int hotel_id, int tipo_hab_id, int num_disponibles) {
			super();
			this.hotel_id = hotel_id;
			this.tipo_hab_id = tipo_hab_id;
			this.num_disponibles = num_disponibles;
		}

		/**
		 * @return the hotel_id
		 */
		public int getHotel_id() {
			return hotel_id;
		}

		/**
		 * @param hotel_id the hotel_id to set
		 */
		public void setHotel_id(int hotel_id) {
			this.hotel_id = hotel_id;
		}

		/**
		 * @return the tipo_hab_id
		 */
		public int getTipo_hab_id() {
			return tipo_hab_id;
		}

		/**
		 * @param tipo_hab_id the tipo_hab_id to set
		 */
		public void setTipo_hab_id(int tipo_hab_id) {
			this.tipo_hab_id = tipo_hab_id;
		}

		/**
		 * @return the num_disponibles
		 */
		public int getNum_disponibles() {
			return num_disponibles;
		}

		/**
		 * @param num_disponibles the num_disponibles to set
		 */
		public void setNum_disponibles(int num_disponibles) {
			this.num_disponibles = num_disponibles;
		}

	}

	/**
	 * @author luism Rellenar los servicios que dispone un hotel
	 *
	 */
	public class Hotel_Servicio {

		private int hotel_id;
		private int servicio_id;
		private Integer precio;
		private String unidad_medida;

		public Hotel_Servicio() {
			super();

		}

		/**
		 * @param hotel_id
		 * @param servicio_id
		 * @param precio
		 * @param unidad_medida
		 */
		public Hotel_Servicio(int hotel_id, int servicio_id, Integer precio, String unidad_medida) {
			super();
			this.hotel_id = hotel_id;
			this.servicio_id = servicio_id;
			this.precio = precio;
			this.unidad_medida = unidad_medida;
		}

		/**
		 * @return the hotel_id
		 */
		public int getHotel_id() {
			return hotel_id;
		}

		/**
		 * @param hotel_id the hotel_id to set
		 */
		public void setHotel_id(int hotel_id) {
			this.hotel_id = hotel_id;
		}

		/**
		 * @return the servicio_id
		 */
		public int getServicio_id() {
			return servicio_id;
		}

		/**
		 * @param servicio_id the servicio_id to set
		 */
		public void setServicio_id(int servicio_id) {
			this.servicio_id = servicio_id;
		}

		/**
		 * @return the precio
		 */
		public Integer getPrecio() {
			return precio;
		}

		/**
		 * @param precio the precio to set
		 */
		public void setPrecio(Integer precio) {
			this.precio = precio;
		}

		/**
		 * @return the unidad_medida
		 */
		public String getUnidad_medida() {
			return unidad_medida;
		}

		/**
		 * @param unidad_medida the unidad_medida to set
		 */
		public void setUnidad_medida(String unidad_medida) {
			this.unidad_medida = unidad_medida;
		}

	}

	/**
	 * @author luism Categorias en las que se encuentra el hotel
	 *
	 */
	public class Hotel_Categoria {

		private int hotel_id;
		private int categoria_id;

		public Hotel_Categoria() {
			super();

		}

		/**
		 * @param hotel_id
		 * @param categoria_id
		 */
		public Hotel_Categoria(int hotel_id, int categoria_id) {
			super();
			this.hotel_id = hotel_id;
			this.categoria_id = categoria_id;
		}

		/**
		 * @return the hotel_id
		 */
		public int getHotel_id() {
			return hotel_id;
		}

		/**
		 * @param hotel_id the hotel_id to set
		 */
		public void setHotel_id(int hotel_id) {
			this.hotel_id = hotel_id;
		}

		/**
		 * @return the categoria_id
		 */
		public int getCategoria_id() {
			return categoria_id;
		}

		/**
		 * @param categoria_id the categoria_id to set
		 */
		public void setCategoria_id(int categoria_id) {
			this.categoria_id = categoria_id;
		}

	}

}
