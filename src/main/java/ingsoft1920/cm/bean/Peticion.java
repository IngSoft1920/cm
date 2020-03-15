package ingsoft1920.cm.bean;

import java.sql.Date;

public class Peticion {

    private int id;
    private String ciudad;
    private Boolean estado;
    private Date fecha_CI;
    private Date fecha_CO;
    private int tipo_hab_id;

    public Peticion() {
    }

    public Peticion(int id, String ciudad, Boolean estado, Date fecha_CI, Date fecha_CO, int tipo_hab_id) {
        this.id = id;
        this.ciudad = ciudad;
        this.estado = estado;
        this.fecha_CI = fecha_CI;
        this.fecha_CO = fecha_CO;
        this.tipo_hab_id = tipo_hab_id;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFecha_CI() {
        return fecha_CI;
    }

    public void setFecha_CI(Date fecha_CI) {
        this.fecha_CI = fecha_CI;
    }

    public Date getFecha_CO() {
        return fecha_CO;
    }

    public void setFecha_CO(Date fecha_CO) {
        this.fecha_CO = fecha_CO;
    }

    public int getTipo_hab_id() {
        return tipo_hab_id;
    }

    public void setTipo_hab_id(int tipo_hab_id) {
        this.tipo_hab_id = tipo_hab_id;
    }
}
