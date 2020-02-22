package ingsoft1920.model;

public class Precio {

    int hotel_id;
    String tipo;
    String fecha;//formato = YYYY-MM-DD
    double precio;

    public Precio(int hotel_id, String tipo, String fecha, double precio) {
        this.hotel_id = hotel_id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
