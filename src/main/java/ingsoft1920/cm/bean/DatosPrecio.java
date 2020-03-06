package ingsoft1920.cm.bean;

public class DatosPrecio {

    private int id;
    private double precio;
    private double puntuacion;
    private boolean estado;
    private int peticion_id;

    public DatosPrecio() {
    }

    public DatosPrecio(int id, double precio, double puntuacion, boolean estado, int peticion_id) {
        this.id = id;
        this.precio = precio;
        this.puntuacion = puntuacion;
        this.estado = estado;
        this.peticion_id = peticion_id;
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

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getPeticion_id() {
        return peticion_id;
    }

    public void setPeticion_id(int peticion_id) {
        this.peticion_id = peticion_id;
    }
}
