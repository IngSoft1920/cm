package ingsoft1920.cm.bean;


public class DatosPrecio {

    private int id;
    private int estado;
    private int peticion_id;
    private String precio;
    private String puntuacion;

    public DatosPrecio() {
    }

    public DatosPrecio(int id, int estado, int peticion_id, String precio, String puntuacion) {
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setFecha(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPeticion_id() {
        return peticion_id;
    }

    public void setPeticion_id(int peticion_id) {
        this.peticion_id = peticion_id;
    }
}
