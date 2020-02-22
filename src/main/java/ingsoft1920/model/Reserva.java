package ingsoft1920.model;

import ingsoft1920.model.Hotel;

public class Reserva {

    private Hotel hotel;
    private Tipo tipo;



    private String fecha_ent;
    private String fecha_sal;

    public Reserva(Hotel hotel, Tipo tipo) {
        this.hotel = hotel;
        this.tipo = tipo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFecha_ent() {
        return fecha_ent;
    }

    public void setFecha_ent(String fecha_ent) {
        this.fecha_ent = fecha_ent;
    }

    public String getFecha_sal() {
        return fecha_sal;
    }

    public void setFecha_sal(String fecha_sal) {
        this.fecha_sal = fecha_sal;
    }
}
