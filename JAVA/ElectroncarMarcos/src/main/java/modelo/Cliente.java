package modelo;

public class Cliente {

    private String cod_cli;
    private String nombre;
    private String clave;
    private int cod_car;

    public Cliente(String cod_cli, String nombre, String clave, int cod_car) {
        this.cod_cli = cod_cli;
        this.nombre = nombre;
        this.clave = clave;
        this.cod_car = cod_car;
    }

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getCod_car() {
        return cod_car;
    }

    public void setCod_car(int cod_car) {
        this.cod_car = cod_car;
    }
}

