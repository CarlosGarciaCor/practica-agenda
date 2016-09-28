package com.islasfilipinas.cj.agenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.islasfilipinas.cj.ficheros.Fichero;

public class Agenda {
	
	private HashMap<Integer, Contacto> contactos;
	
	public Agenda(File file) throws IOException{
		
	}
	
	public void guardar(File file) throws IOException{
		Fichero.guardar(this, file);
	}
	
	public HashMap<Integer, Contacto> getContactos(){
		return null;
	}
	
	public void annadir(Contacto contacto){
		
	}
	public void borrar(Contacto contacto){
		 
	}
	public void modificar(Contacto contacto, Contacto contactoMod){
		
	}
	public void buscar(Contacto contacto){
		
	}
}
