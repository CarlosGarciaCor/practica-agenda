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

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarContacto extends JDialog{
	private JTextField textoNombre;
	private JTextField textoTlfn;
	private Agenda agenda;
	
	/**
	 * Constructor del JDialog AgregarContacto. Como su nombre indica permite agregar un contacto a la agenda previamente cargada.
	 * @param padre Este parámetro es el JFrame.
	 */
	public AgregarContacto(JFrame padre){
		super(padre);
		agenda=((MenuPrincipal) padre).getAgenda();
		/* 
		 * Cinco métodos básicos que sirven para colocar el título, el icono, 
		 * la operación de cierre, el tamaño y el layout.
		 */
		setTitle("Agregar contacto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 304, 289);
		setModal(true);
		setLayout(null);
		
		/*
		 * Etiqueta de texto. Indica donde deberá escribir el nombre el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombre.setBounds(72, 63, 55, 14);
		add(labelNombre);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(128, 61, 86, 20);
		textoNombre.setColumns(10);
		add(textoNombre);
		
		/*
		 * Etiqueta de texto. Indica donde deberá escribir el teléfono el usuario.
		 * El JTextField que le sigue es el campo de texto donde el usuario puede escribir.
		 */
		JLabel labelTelfono = new JLabel("Tel\u00E9fono:");
		labelTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTelfono.setBounds(72, 112, 68, 17);
		add(labelTelfono);
		
		textoTlfn = new JTextField();
		textoTlfn.setBounds(128, 111, 86, 20);
		textoTlfn.setColumns(10);
		add(textoTlfn);
		
		/*
		 * Estas dos etiquetas son dos imágenes que acompañan a las etiquetas de texto.
		 */
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
		label.setBounds(33, 48, 32, 41);
		add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadirtlf.png")));
		lblNewLabel.setBounds(33, 100, 32, 32);
		add(lblNewLabel);
		
		/*
		 * Los botones inferiores para bien añadir el contacto o bien volver al menú anterior.
		 * Se añaden junto a ellos los correspondientes listener y eventos.
		 */
		JButton botonAniadir = new JButton("A\u00F1adir");
		botonAniadir.setBounds(22, 203, 89, 23);
		botonAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contacto nuevo = new Contacto (textoNombre.getText(), textoTlfn.getText());
				try {
					agenda.annadir(nuevo);
					mostrarPopupAniadido();
				} catch (ContactoRepetidoException e1) {
					mostrarPopupYaExiste();
				}
			}

		});
		add(botonAniadir);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(168, 203, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		add(botonVolver);
		
		setVisible(true);
		
		
	}
	
	private void mostrarPopupYaExiste() {
		JOptionPane.showMessageDialog(this,
			    "El contacto que intenta añadir ya existe.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	private void mostrarPopupAniadido() {
		JOptionPane.showMessageDialog(this,
			    "El contacto " + textoNombre.getText() + " ha sido añadido.",
			    "Completado",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
	}
}
