package ingsoft1920.cm.bean;


public class DatosPrecio {

    private int id;
    private int estado;
    private int peticion_id;
    private double precio;
    private double puntuacion;

    public DatosPrecio() {
    }

    public DatosPrecio(int id, int estado, int peticion_id, double precio, double puntuacion) {
        this.id = id;
        this.estado = estado;
        this.peticion_id = peticion_id;
        this.precio = precio;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setFecha(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPeticion_id() {
        return peticion_id;
    }

    public void setPeticion_id(int peticion_id) {
        this.peticion_id = peticion_id;
    }
}
