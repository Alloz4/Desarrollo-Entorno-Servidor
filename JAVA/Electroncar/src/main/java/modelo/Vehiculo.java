package modelo;

public class Vehiculo {

    private int cod_car;
    private String localidad;
    private int estado;
    private int bateria;

    public int getCod_car() {
        return cod_car;
    }

    public void setCod_car(int cod_car) {
        this.cod_car = cod_car;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}
