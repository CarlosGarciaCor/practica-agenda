package com.islasfilipinas.cj.interfaz;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.exceptions.FicheroNoValidoException;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class MenuPrincipal extends JFrame {
	
	private Agenda agenda;
	private File file=null;
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
		setBounds(400, 250, 600, 500);
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
		
		JMenuItem opcionNuevaAgenda = new JMenuItem("Nueva agenda...");
		opcionNuevaAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearAgenda();
			}
			
		});
		menuAgenda.add(opcionNuevaAgenda);
	
		JMenuItem opcionCargarAgenda = new JMenuItem("Cargar agenda");
		opcionCargarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarAgenda();
			}
		});
		menuAgenda.add(opcionCargarAgenda);
		
		JMenuItem opcionGuardarAgenda = new JMenuItem("Guardar agenda");
		opcionGuardarAgenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarAgenda();
			}
			
		});
		menuAgenda.add(opcionGuardarAgenda);
		
		JMenuItem opcionMostrarAgenda = new JMenuItem("Mostrar agenda");
		opcionMostrarAgenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
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
				if (file==null)
					mostrarPopupAgendaNoCargada();
				else
					agregarContacto();
			}
		});
		menuContactos.add(opcionAgregarContacto);
		
		JMenuItem opcionModificarBorrarContacto = new JMenuItem("Modificar/Eliminar contacto");
		opcionModificarBorrarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
					modificarBorrarContacto();
			}
		});
		menuContactos.add(opcionModificarBorrarContacto);
		
		JMenuItem opcionBuscarContacto = new JMenuItem("Buscar");
		opcionBuscarContacto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null)
					mostrarPopupAgendaNoCargada();
				else if (agenda.getContactos().isEmpty())
					mostrarPopupAgendaVacia();
				else
					buscar();
			}
			
		});
		menuContactos.add(opcionBuscarContacto);
		
		
		// --------------------------------------- SISTEMA ----------------------------------------
		
		/*
		 * Constructor de la opci�n de men� para ver la ayuda referente al programa.
		 * Tambi�n est� el m�todo para capturar el evento mediante un listener y el
		 * m�todo para a�adir la opci�n a la barra de men�.
		 */
		JMenu menuSistema=new JMenu("Sistema");
		menuBar.add(menuSistema);
		
		JMenuItem opcionAyuda = new JMenuItem("Ayuda");
		opcionAyuda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()){
					try {
						Desktop.getDesktop().browse(new URI("http://en.lmgtfy.com/"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		menuSistema.add(opcionAyuda);
		
		JMenuItem opcionSalir = new JMenuItem("Salir");
		opcionSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		menuSistema.add(opcionSalir);
		

	}
	
	private void salir() {
		try {
			int valorRetorno=mostrarPopupSalir();
			if (valorRetorno==0){
				System.exit(0);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int mostrarPopupSalir() {
		return JOptionPane.showConfirmDialog(this, 
				"�Est� seguro de que desea salir?"
				+ "\nLos cambios no guardados se perder�n.", "Salir", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/*
	 * A partir de aqu� est�n los m�todos privados que se utilizan para realizar las diferentes acciones
	 * de los eventos.
	 */
	
	private void crearAgenda(){
		if (!agenda.getContactos().isEmpty()){
			int valorRetorno=mostrarPopupPreguntaCrearAgenda();
			if (valorRetorno==0){
				crearArchivo();
			}
		}
		else
			crearArchivo();
	}
	
	private void crearArchivo(){
		JFileChooser jfc=new JFileChooser();
		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setCurrentDirectory(new File("C:/Users/"+System.getProperty("user.name")+"/Desktop"));
		
		if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			this.file=jfc.getSelectedFile();
			
			if (this.file.exists()){
				mostrarPopupSobreescribir();
			}
			else {
				try {
					String nombreVie = file.getAbsolutePath();
					if (!this.file.getName().endsWith(".ag"))
						file = new File(nombreVie+".ag");
						this.file.createNewFile();
						agenda.getContactos().clear();
						agenda.guardar(file);
				} catch (IOException e) {
					mostrarPopupIOException();
				}
			}
		}
		
	}
	
	private void mostrarPopupSobreescribir() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"Ese archivo ya existe", "Archivo existente", 
				JOptionPane.INFORMATION_MESSAGE);
	}

	private int mostrarPopupPreguntaCrearAgenda() {
		// TODO Auto-generated method stub
		return JOptionPane.showConfirmDialog(this, 
				"Si crea una nueva agenda los cambios"
				+ "\nque no haya guardado se perder�n,"
				+ "\n�est� seguro de que quiere crearla? ", "Crear agenda", 
				JOptionPane.YES_NO_OPTION);
	}
	
	private void mostrarAgenda(){
		Mostrar mostrarAgenda = new Mostrar(this);
	}
	
	private void mostrarPopupAgendaVacia() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"La agenda est� vac�a", "Informaci�n",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void mostrarPopupAgendaNoCargada() {
		JOptionPane.showMessageDialog(this, 
				"No existe ninguna agenda cargada.", "Informaci�n",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/*
	 * 
	 */
	private void guardarAgenda() {
		if (agenda.getContactos().isEmpty()){
			mostrarPopupAgendaVacia();
		}
		else if (this.file!=null){
			try {
				agenda.guardar(this.file);
				mostrarPopupAgendaGuardadaExito();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mostrarPopupIOException();
			}
		}
		else {
			mostrarPopupAgendaVacia();
		}
	}
	
	private void mostrarPopupAgendaGuardadaExito() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, 
				"Agenda guardada con �xito", "Guardar",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// CARGAR AGENDA DESDE EL FICHERO - FILECHOOSER
	private void cargarAgenda() {
		
		JFileChooser jfc=new JFileChooser();
		FileNameExtensionFilter agFiltro = new FileNameExtensionFilter("Ficheros agenda (*.ag)", "ag");
		jfc.addChoosableFileFilter(agFiltro);
        jfc.setFileFilter(agFiltro);
		
		jfc.setDialogType(JFileChooser.APPROVE_OPTION);
		
		//Si esta ruta absoluta no existe, se coloca en el directorio del proyecto
		//de forma autom�tica
		File currentFile=new File("C:/Users/"+System.getProperty("user.name")+"/Desktop");
		
		if (currentFile.exists())
			jfc.setCurrentDirectory(currentFile);
		
		if (jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
			try {
				this.file=jfc.getSelectedFile();
				agenda.cargar(jfc.getSelectedFile());
				mostrarPopupAgendaCargada();
			} catch (ClassNotFoundException | FicheroNoValidoException e1) {
				mostrarPopupFicheroInvalido();
			} catch (IOException e1) {
				mostrarPopupIOException();
			}
		}
	}

	private void mostrarPopupAgendaCargada() {
		JOptionPane.showMessageDialog(this, 
				"Agenda cargada con �xito.", "Cargar agenda",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void mostrarPopupFicheroInvalido() {
		JOptionPane.showMessageDialog(this, 
				"�El fichero que est� intentando cargar no es una agenda!", "Fichero inv�lido",
				JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarPopupIOException() {
		JOptionPane.showMessageDialog(this, 
				"Ha ocurrido un error inesperado, "
				+ "\npor favor vuelva a intentarlo.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private void agregarContacto() {
			AgregarContacto agregar = new AgregarContacto(this);
	}
	
	private void modificarBorrarContacto() {
		if (file != null){
			BorrarModificar borrarmodificar = new BorrarModificar(this);
		}else{
			mostrarPopupAgendaNoCargada();
		}
	}
	
	private void mostrarAyuda(){
		
	}
	
	private void buscar(){
		BuscarContacto buscar = new BuscarContacto(this);
	}
}
