package ingsoft1920.cm.bean;

import java.sql.Date;

public class DatosPrecio {

    private int id;
    private String ciudad;
    private int estado;
    private Date fecha;
    private int peticion_id;

    public DatosPrecio() {
    }

    public DatosPrecio(int id, String ciudad, int estado, Date fecha, int peticion_id) {
        this.id = id;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fecha = fecha;
        this.peticion_id = peticion_id;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPeticion_id() {
        return peticion_id;
    }

    public void setPeticion_id(int peticion_id) {
        this.peticion_id = peticion_id;
    }
}
