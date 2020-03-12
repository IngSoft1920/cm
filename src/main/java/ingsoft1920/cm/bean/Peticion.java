package ingsoft1920.cm.bean;

import java.sql.Date;

public class Peticion {

    private int id;
    private String ciudad;
    private Boolean estado;
    private int anoCI;
    private int mesCI;
    private int diaCI;
    private int anoCO;
    private int mesCO;
    private int diaCO;
    private int tipo_hab_id;

    public Peticion() {
    }

    public Peticion(int id, String ciudad, Boolean estado, int anoCI, int mesCI, int diaCI, int anoCO, int mesCO, int diaCO, int tipo_hab_id) {
        this.id = id;
        this.ciudad = ciudad;
        this.estado = estado;
        this.anoCI = anoCI;
        this.mesCI = mesCI;
        this.diaCI = diaCI;
        this.anoCO = anoCO;
        this.mesCO = mesCO;
        this.diaCO = diaCO;
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

    public int getAnoCI() {
        return anoCI;
    }

    public void setAnoCI(int anoCI) {
        this.anoCI = anoCI;
    }

    public int getMesCI() {
        return mesCI;
    }

    public void setMesCI(int mesCI) {
        this.mesCI = mesCI;
    }

    public int getDiaCI() {
        return diaCI;
    }

    public void setDiaCI(int diaCI) {
        this.diaCI = diaCI;
    }

    public int getAnoCO() {
        return anoCO;
    }

    public void setAnoCO(int anoCO) {
        this.anoCO = anoCO;
    }

    public int getMesCO() {
        return mesCO;
    }

    public void setMesCO(int mesCO) {
        this.mesCO = mesCO;
    }

    public int getDiaCO() {
        return diaCO;
    }

    public void setDiaCO(int diaCO) {
        this.diaCO = diaCO;
    }

    public int getTipo_hab_id() {
        return tipo_hab_id;
    }

    public void setTipo_hab_id(int tipo_hab_id) {
        this.tipo_hab_id = tipo_hab_id;
    }
}
