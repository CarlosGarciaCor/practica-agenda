package com.islasfilipinas.cj.agenda;

import java.io.Serializable;

public class Contacto implements Serializable {

	private static final long serialVersionUID = 3575857020519478314L;
	private String nombre;
	private String telefono;
	
	public Contacto (String _nombre, String _telefono){
		this.nombre=_nombre;
		this.telefono=_telefono;
	}
	
	public Contacto leer(){
		return this;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
