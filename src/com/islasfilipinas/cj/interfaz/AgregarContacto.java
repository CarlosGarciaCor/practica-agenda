package com.islasfilipinas.cj.interfaz;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;

/**
 * Esta clase es la encargada de generar la ventana que permita al usuario añadir un contacto
 * a una agenda.
 * Para poder generar la ventana esta clase extiende JDialog. Esta clase es llamada desde el método
 * {@link MenuPrincipal.mostrarAgenda()} del frame principal. La ventana generada es modal, por lo cual
 * se situará sobre el JFrame sin que este deje de ser visualizable. Sólo se podrá volver a la ventana
 * anterior mediante un botón de volver.
 * <br>
 * El formato de la ventana es el siguiente:
 * <br><br>
 * <img src="http://i.imgur.com/YQbFuwn.jpg">
 * @author Carlos Garcia Corpas & Javier Sánchez Gómez
 * @version v1.3.1
 */
public class AgregarContacto extends JDialog{
	/**
	 * Identificador único de la clase AgregarContacto.
	 */
	private static final long serialVersionUID = -8350866977294136115L;
	/**
	 * Componente gráfico correspondiente con el campo de texto donde se va a introducir el nombre.
	 */
	private JTextField textoNombre;
	/**
	 * Componente gráfico correspondiente con el campo de texto donde se va a introducir el número de teléfono.
	 */
	private JTextField textoTlfn;
	/**
	 * Objeto Agenda obtenido del frame padre.
	 */
	private Agenda agenda;
	
	/**
	 * Permite agregar un contacto a la agenda previamente cargada.
	 * Este constructor establece el formato que va a tener la ventana, inicia los componentes
	 * que contiene con sus debidos Listeners ({@link initComponents()}) y permite al usuario
	 * añadir un contacto a la agenda cargada ({@link annadirContacto()}).
	 * @param padre Este parámetro es el JFrame principal.
	 */
	public AgregarContacto(JFrame padre){
		super(padre);
		agenda=((MenuPrincipal) padre).getAgenda();
		/* 
		 * Cinco métodos básicos que sirven para colocar el título, el icono, 
		 * la operación de cierre, el tamaño y el layout.
		 */
		setTitle("Añadir contacto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 320, 289);
		setModal(true);
		getContentPane().setLayout(null);
		
		initComponents();
	}
	
	/**
	 * Inicia los componentes de este JDialog.
	 * Los componentes son los siguientes:
	 * <ul>
	 * 	<li>Caja de texto para el nombre - JLabel</li>
	 *  <li>Caja de texto para el teléfono - JLabel</li>
	 *  <li>Botón de añadir - JButton</li>
	 *  <li>Botón de volver - JButton</li>
	 * </ul>
	 * <br>
	 * Asignará a ambos campos de texto un KeyListener que permitirá al usuario 
	 * escribir los datos.
	 */
	private void initComponents() {
		/*
		 * Etiqueta de texto. Indica donde deberá escribir el nombre el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombre.setBounds(72, 63, 55, 14);
		getContentPane().add(labelNombre);
		
		textoNombre = new JTextField();
		int limiteNombre = 20;
		textoNombre.setBounds(128, 61, 143, 20);
		textoNombre.setColumns(10);
		textoNombre.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (textoNombre.getText().length() >= limiteNombre){
					e.consume();
					Toolkit.getDefaultToolkit().beep();}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		getContentPane().add(textoNombre);
		
		/*
		 * Etiqueta de texto. Indica donde deberá escribir el teléfono el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelTelfono = new JLabel("Tel\u00E9fono:");
		labelTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTelfono.setBounds(72, 112, 68, 17);
		getContentPane().add(labelTelfono);
		
		textoTlfn = new JTextField();
		int limiteTelefono = 13;
		textoTlfn.setBounds(128, 111, 143, 20);
		textoTlfn.setColumns(10);
		textoTlfn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (textoTlfn.getText().length() >= limiteTelefono){
					e.consume();
					Toolkit.getDefaultToolkit().beep();}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		getContentPane().add(textoTlfn);
		
		/*
		 * Estas dos etiquetas son dos imágenes que acompañan a las etiquetas de texto.
		 */
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
		label.setBounds(33, 48, 32, 41);
		getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadirtlf.png")));
		lblNewLabel.setBounds(33, 100, 32, 32);
		getContentPane().add(lblNewLabel);
		
		/*
		 * Los botones inferiores para bien añadir el contacto o bien volver al menú anterior.
		 * Se añaden junto a ellos los correspondientes listener y eventos.
		 */
		JButton botonAniadir = new JButton("A\u00F1adir");
		botonAniadir.setBounds(47, 203, 89, 23);
		botonAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annadirContacto();
			}

			
		});
		getContentPane().add(botonAniadir);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(169, 203, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		getContentPane().add(botonVolver);
		
		setVisible(true);
	}
	
	/**
	 * Permite añadir un contacto a la agenda en memoria.
	 * Este método llama a la funcionalidad añadir contacto teniendo en cuenta una serie
	 * de validaciones. 
	 * <br>
	 * Es peculiar de este método que juzga si un nombre de contacto es normal o no, pudiendo 
	 * resultar esto de que el usuario se haya equivocado al escribir.
	 * Considera nombres normales aquellos que sólo están formados por letras y el carácter espacio.
	 * Si considera que el nombre no es normal, le preguntará al usuario si la información que 
	 * quiere guardar es correcta.
	 */
	private void annadirContacto() {
		try {
			Contacto nuevo = new Contacto (textoNombre.getText(), textoTlfn.getText());
			if (textoNombre.getText().matches("[a-zA-Z ]+")
					&& textoTlfn.getText().matches("[0-9]+")){
				agenda.annadir(nuevo);
				mostrarPopupAniadido();
				textoNombre.setText("");
				textoTlfn.setText("");
			}
			else {
				if (mostrarPopupContactoRaro()==0){
					agenda.annadir(nuevo);
					mostrarPopupAniadido();
					textoNombre.setText("");
					textoTlfn.setText("");
				}
			}
				
		} catch (ContactoRepetidoException e1) {
			mostrarPopupYaExiste();
		} catch (InputMismatchException e2) {
			mostrarPopupCampoSinRellenar();
		}
	}
	
	/**
	 * "Ya existe un contacto con este nombre o número de teléfono.
	 * Inténtelo de nuevo por favor."
	 */
	private void mostrarPopupYaExiste() {
		JOptionPane.showMessageDialog(this,
			    "Ya existe un contacto con este nombre o número de teléfono.\n"
			    + "Inténtelo de nuevo por favor.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * "El contacto "ejemplo" ha sido añadido."
	 */
	private void mostrarPopupAniadido() {
		JOptionPane.showMessageDialog(this,
			    "El contacto " + textoNombre.getText() + " ha sido añadido.",
			    "Completado",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
	}
	
	/**
	 * "El contacto que intenta añadir es algo inusual ¿está seguro de que quiere añadirlo?"
	 * @return 0 - Sí, 1 - No
	 */
	private int mostrarPopupContactoRaro() {
		return JOptionPane.showConfirmDialog(this, 
				"El contacto que intenta añadir es algo inusual, "
				+ "\n¿está seguro de que quiere añadirlo?", "Contacto inusual", 
				JOptionPane.YES_NO_OPTION);
	}
	
	/**
	 * "Es necesario que rellene ambos campos para continuar."
	 */
	private void mostrarPopupCampoSinRellenar() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this,
			    "Es necesario que rellene ambos campos para continuar.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
}
