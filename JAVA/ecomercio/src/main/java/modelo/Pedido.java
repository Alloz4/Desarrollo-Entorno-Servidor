package modelo;

public class Pedido {
   private int numped;
   private int cod_cliente;
   private String producto;
   private int precio;
   
public int getNumped() {
	return numped;
}
public void setNumped(int numped) {
	this.numped = numped;
}
public int getCod_cliente() {
	return cod_cliente;
}
public void setCod_cliente(int cod_cliente) {
	this.cod_cliente = cod_cliente;
}
public String getProducto() {
	return producto;
}
public void setProducto(String producto) {
	this.producto = producto;
}
public int getPrecio() {
	return precio;
}
public void setPrecio(int precio) {
	this.precio = precio;
}
   
}
