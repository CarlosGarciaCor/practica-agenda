package com.islasfilipinas.cj.agenda;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("agenda.ag");
		
		/*try {
			Agenda agenda=new Agenda();
			agenda.cargar(file);
			for (Contacto item: agenda.getContactos()){
				System.out.println(item.getNombre()+" - "+item.getTelefono());
			}
			System.out.println("----------");
			Collections.sort(agenda.getContactos());
			for (Contacto item: agenda.getContactos()){
				System.out.println(item.getNombre()+" - "+item.getTelefono());
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FicheroNoValidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Contacto c1=new Contacto("asdfsafdas", "123412341");
		Contacto c2=new Contacto("asfsafdas", "22314123231");
		Contacto c3=new Contacto("asqwe32as", "32312341");
		Contacto c4=new Contacto("asdfsafaaas", "4234512341");
		Contacto c5=new Contacto("asdfsssfdas", "524417841");
		
		try {
			Agenda agenda=new Agenda();
			
				agenda.annadir(c1);
				agenda.annadir(c2);
				agenda.annadir(c3);
				agenda.annadir(c4);
				agenda.annadir(c5);
				
			agenda.guardar(file);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

}
