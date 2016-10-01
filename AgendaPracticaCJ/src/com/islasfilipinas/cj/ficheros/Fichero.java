package com.islasfilipinas.cj.ficheros;


import java.io.*;
import java.util.ArrayList;

import com.islasfilipinas.cj.agenda.*;

//En principio esta clase ya est�. La agenda interactua con el fichero solo cuando se carga
//y cuando se guarda, no cada vez que se hace una operaci�n sobre ella.

//Lo que si puede faltar son validaciones de formato, pero al ser directamente streams de objetos
//si no puede formar un objeto el m�todo guardar es que han toqueteado el fichero
//Excepcion- ClassNotFound
public class Fichero {

	public static void guardar(Agenda agenda, File file) throws IOException, FileNotFoundException{
		
		if (file.exists()){
			ObjectOutputStream oOutputStream=null;
			
			try {
				oOutputStream=new ObjectOutputStream(new FileOutputStream(file));
				oOutputStream.writeObject(agenda);
			} catch (IOException e){
				throw e;
			} finally {
				oOutputStream.close();
			}
		}
		else
			//Si el fichero no existe se comprueba tambi�n en esta clase
			throw new FileNotFoundException();
	}
	
	public static ArrayList<Contacto> leerAgenda(File file) throws IOException, FileNotFoundException, ClassNotFoundException{
		
		if (file.exists()){
			
			ObjectInputStream oInputStream=null;
		
			try {
				oInputStream=new ObjectInputStream(new FileInputStream(file));
				Agenda agenda=(Agenda)oInputStream.readObject();
				return agenda.getContactos();
			} catch (IOException e){
				throw e;
			} finally {
				if (oInputStream!=null)
					oInputStream.close();
			}
		}
		else
			throw new FileNotFoundException();
	}
}
