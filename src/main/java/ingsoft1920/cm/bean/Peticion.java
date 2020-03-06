package ingsoft1920.cm.bean;

import java.sql.Date;

public class Peticion {

    private int id;
    private String ciudad;
    private Date fecha;
    private int tipo_hab_id;
    private Boolean estado;

    public Peticion() {
    }

    public Peticion(int id, String ciudad, Date fecha, int tipo_hab_id, Boolean estado) {
        this.id = id;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.tipo_hab_id = tipo_hab_id;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipo_hab_id() {
        return tipo_hab_id;
    }

    public void setTipo_hab_id(int tipo_hab_id) {
        this.tipo_hab_id = tipo_hab_id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
