package com.islasfilipinas.cj.agenda;

import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.ficheros.Fichero;

//Faltan métodos modificar y buscar, y hacer validaciones de contacto
public class Agenda implements Serializable{
	
	private static final long serialVersionUID = 6832112334979262272L;
	private ArrayList<Contacto> contactos;
	
	
	//El constructor de Agenda la carga directamente, nos ahorramos un método
	//Además siempre que esté instanciado un objeto Agenda estarán cargados los datos,
	//lo cual es un requisito
	public Agenda(){
		this.contactos=new ArrayList<Contacto>();
	}
	public Agenda(File file) throws IOException, ClassNotFoundException{
		contactos=Fichero.leerAgenda(file);
		
	}

	public void guardar(File file) throws IOException{
		Fichero.guardar(this, file);
	}
	
	//Método para comprobar si un contacto existe en la agenda:
	//Es necesario porque el contains de Object es puta mierda
	public boolean containsContacto(Contacto contacto){
		for (Contacto item: this.contactos){
			if (contacto.getNombre().equals(item.getNombre())
					&& contacto.getTelefono().equals(item.getNombre()))
					return true;
		}
		return false;
	}
	
	//Si existe el contacto lanza una excepción que hay que programar (no miro a nadie)
	public void annadir(Contacto contacto){
		if (!this.containsContacto(contacto)){
			this.contactos.add(contacto);
		}
		//else
			//throw new ContactoRepetidoException();
	}
	
	//Más de lo mismo
	public void borrar(Contacto contacto){
		if (this.containsContacto(contacto))
			this.contactos.remove(contacto);
		//else
			//throw new ContactoRepetidoException();
	}
	
	public void modificar(Contacto contacto, Contacto contactoMod){
		
	}
	
	public void buscar(Contacto contacto){
		
	}
	public ArrayList<Contacto> getContactos() {
		return contactos;
	}

}
