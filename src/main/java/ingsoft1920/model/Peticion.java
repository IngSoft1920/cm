package ingsoft1920.model;

public class Peticion {

    private int id;
    private String ciudad;
    private String fecha;//YYYY-MM-DD
    private String tipo;
    private int estado;

    public Peticion(int id, String ciudad, String fecha, String tipo, int estado) {
        this.id = id;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
