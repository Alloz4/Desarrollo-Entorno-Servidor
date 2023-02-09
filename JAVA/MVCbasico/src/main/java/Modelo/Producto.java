package Modelo;
/**
 *  Clase Libro tipo java Bean (POJO)
 */



public class Producto {
	private int id;
	private String nombre;
	private int stock;
	private int precio;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String producto) {
		this.nombre = producto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public Producto (int id, String nombre, int stock, int precio){
		this.id = id;
		this.nombre = nombre;
		this.stock = stock;
		this.precio = precio;	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
