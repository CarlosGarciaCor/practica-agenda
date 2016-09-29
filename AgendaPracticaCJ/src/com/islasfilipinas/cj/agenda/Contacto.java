package com.islasfilipinas.cj.agenda;

import java.io.Serializable;

public class Contacto implements Serializable, Comparable<Contacto> {

	private static final long serialVersionUID = 3575857020519478314L;
	private String nombre;
	private String telefono;
	
	//El constructor de Contactos hace una validación no muy restrictiva, solo del número de
	//caracteres de los campos, para poder dar luego más flexibilidad a la aplicación.
	public Contacto (String _nombre, String _telefono){
		if (_nombre.length()<=20 && _nombre.length()>=2
				&& _telefono.length()>=3 && _telefono.length()<=13)
		this.nombre=_nombre;
		this.telefono=_telefono;
	}
	
	public int compareTo(Contacto contacto) {
		// TODO Auto-generated method stub
		if (this.nombre.charAt(0)<contacto.getNombre().charAt(0))
			return -1;
		if (this.nombre.charAt(0)>contacto.getNombre().charAt(0))
			return 1;
		else 
			return 0;
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
