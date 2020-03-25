package ingsoft1920.cm.model;

public class Disponibles {

    private int hotel_id;
    private int tipo_ha_id;
    private String nombre_tipo;
    private int precio_total;

    public Disponibles() {
    }

    public Disponibles(int hotel_id, int tipo_ha_id, String nombre_tipo, int precio_total) {
        this.hotel_id = hotel_id;
        this.tipo_ha_id = tipo_ha_id;
        this.nombre_tipo = nombre_tipo;
        this.precio_total = precio_total;
    }


    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getTipo_ha_id() {
        return tipo_ha_id;
    }

    public void setTipo_ha_id(int tipo_ha_id) {
        this.tipo_ha_id = tipo_ha_id;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public int getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(int precio_total) {
        this.precio_total = precio_total;
    }
}
