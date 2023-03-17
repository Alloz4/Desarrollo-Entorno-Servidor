package com.openwebinars.spring.model;

public class Empleado {

	private long id;
	private String nombre;
	private String email;
	private String telefono;

	public Empleado(int id, String nombre, String email, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public Empleado() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + "]";
	}

}