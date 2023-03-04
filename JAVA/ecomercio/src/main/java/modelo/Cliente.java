package modelo;

public class Cliente {
  private int cod_cliente;
  private String nombre;
  private String clave;
  private int veces;
  
public int getCod_cliente() {
	return cod_cliente;
}
public void setCod_cliente(int cod_cliente) {
	this.cod_cliente = cod_cliente;
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
public int getVeces() {
	return veces;
}
public void setVeces(int veces) {
	this.veces = veces;
}
}
