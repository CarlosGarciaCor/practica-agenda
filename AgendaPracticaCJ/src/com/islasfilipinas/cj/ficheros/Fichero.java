package com.islasfilipinas.cj.ficheros;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.islasfilipinas.cj.agenda.*;

public class Fichero {

	public static void guardar(Agenda agenda, File file) throws IOException{
		
		ObjectOutputStream oOutputStream=null;
		
		try {
			oOutputStream=new ObjectOutputStream(new FileOutputStream(file));
			oOutputStream.writeObject(agenda.getContactos());
		} catch (IOException e){
			throw e;
		} finally {
			oOutputStream.close();
		}
		
	}
	
	public static Agenda sacarContactos(File file) throws IOException{
		
		ObjectInputStream oInputStream=null;
		
		oInputStream=new ObjectInputStream(new FileInputStream(file));
		
		
		
		
		
		
		
		
		return null;
	}
	
}
