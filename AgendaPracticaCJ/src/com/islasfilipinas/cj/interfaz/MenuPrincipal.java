package com.islasfilipinas.cj.interfaz;


import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.islasfilipinas.cj.agenda.Agenda;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
	
	private Agenda agenda;
	/**
	 * Main de la aplicaci�n. Lanza el men� principal desde el cual se pueden realizar todas las acciones deseadas.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Instancio el men� principal para que aparezca a la ejecuci�n del programa.
					MenuPrincipal menuPrincipal = new MenuPrincipal();
					menuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor del men� principal. Contiene todos los m�todos para darle forma y estilo. Tambi�n contiene los listeners y los eventos.
	 */
	public MenuPrincipal() {
		// Estos cinco m�todos colocan el t�tulo, el icono, la operaci�n de cierre, el tama�o y el layout respectivamente.
		setTitle("Agenda de contactos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 456, 270);
		setLayout(new FlowLayout());
		iniciarComponentes();
		agenda = new Agenda();
		
	}

	public Agenda getAgenda() {
		return agenda;
	}

	private void iniciarComponentes() {
		// Constructor de la barra de men�, dentro tendremos todas las acciones que el programa puede llevar a cabo. 
		// Tambi�n est� el m�todo que coloca la barra dentro del frame.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		// ------------------------------------- AGENDA -------------------------------------------------
		
		
		
		// Constructor de una de las opciones del men�, la de la agenda.
		// Tambi�n est� el m�todo que coloca la opci�n en la barra de men�.
		JMenu menuAgenda = new JMenu("Agenda");
		menuBar.add(menuAgenda);
		
		/* 
		 * Los tres constructores a continuaci�n a�aden opciones al men� de agenda, adem�s se programan 
		 * sus listeners y actionEvents para hacer diferentes acciones al hacer click en las acciones.
		 */
		JMenuItem opcionCargarAgenda = new JMenuItem("Cargar agenda");
		opcionCargarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cargarAgenda();
				
			}
		});
		menuAgenda.add(opcionCargarAgenda);
		
		JMenuItem opcionGuardarAgenda = new JMenuItem("Guardar agenda");
		opcionGuardarAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				guardarAgenda();
				
			}
			
		});
		menuAgenda.add(opcionGuardarAgenda);
		
		JMenuItem opcionMostrarAgenda = new JMenuItem("Mostrar agenda");
		opcionMostrarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mostrarAgenda();
			}
		});
		menuAgenda.add(opcionMostrarAgenda);
		
		
		// ------------------------------- CONTACTOS -----------------------------------
		
		
		// Constructor de otra opci�n de la barra de men�, los contactos.
		// Tambi�n est� el m�todo que coloca la opci�n en la barra de men�.
		JMenu menuContactos = new JMenu("Contactos");
		menuBar.add(menuContactos);
		
		// Constructores, listeners y actionEvents para las dos opciones del men� de contactos.
		JMenuItem opcionAgregarContacto = new JMenuItem("Agregar contacto");
		opcionAgregarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarContacto();
			}
		});
		menuContactos.add(opcionAgregarContacto);
		
		JMenuItem opcionModificarBorrarContacto = new JMenuItem("Modificar/Eliminar contacto");
		opcionModificarBorrarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				modificarBorrarContacto();
			}
		});
		menuContactos.add(opcionModificarBorrarContacto);
		
		
		// --------------------------------------- AYUDA ----------------------------------------
		
		/*
		 * Constructor de la opci�n de men� para ver la ayuda referente al programa.
		 * Tambi�n est� el m�todo para capturar el evento mediante un listener y el
		 * m�todo para a�adir la opci�n a la barra de men�.
		 */
		JMenuItem opcionAyuda = new JMenu("Ayuda");
		opcionAyuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarAyuda();
				
			}
		});
		menuBar.add(opcionAyuda);
	}
	
	
	/*
	 * A partir de aqu� est�n los m�todos privados que se utilizan para realizar las diferentes acciones
	 * de los eventos.
	 */
	private void mostrarAgenda(){
		Mostrar mostrarAgenda = new Mostrar(this);
	}
	
	private void guardarAgenda() {
		
	}
	
	private void cargarAgenda() {
		
	}
	
	private void agregarContacto() {
		AgregarContacto agregar = new AgregarContacto(this);
	}
	
	private void modificarBorrarContacto() {
	
	}
	
	private void mostrarAyuda(){
		
	}

}
