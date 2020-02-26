package ingsoft1920.cm.model;

public class Tipo {

    private String tipo;
    private double precio;
    private int disponibles;



    public Tipo(String tipo, double precio, int disponibles){
        this.tipo = tipo;
        this.precio = precio;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public void addPrecio(double precio){
        this.precio += precio;
    }
}
