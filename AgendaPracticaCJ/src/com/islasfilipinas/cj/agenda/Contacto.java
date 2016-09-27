package com.islasfilipinas.cj.agenda;

public class Contacto {

	private String nombre;
	private String telefono;
	
	public Contacto (String _nombre, String _telefono){
		this.nombre=_nombre;
		this.telefono=_telefono;
	}
	
	public Contacto leer(){
		return this;
	}
}
