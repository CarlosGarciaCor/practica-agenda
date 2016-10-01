package com.islasfilipinas.cj.interfaz;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;
import com.islasfilipinas.cj.exceptions.ContactoRepetidoException;

public class ModificarContacto extends JDialog{
	
	private Agenda agenda;
	private JTextField textoNombre;
	private JTextField textoTlfn;
	
	public ModificarContacto(JFrame padre, String nombreVie, String tlfnVie) {
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Modificar contacto: " + nombreVie);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 320, 268);
		setModal(true);
		getContentPane().setLayout(null);
		
		
		JLabel labelNombre = new JLabel("Nuevo nombre:");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNombre.setBounds(31, 74, 93, 14);
		getContentPane().add(labelNombre);
		
		
		textoNombre = new JTextField();
		int limiteNombre = 20;
		textoNombre.setBounds(128, 72, 143, 20);
		textoNombre.setColumns(10);
		textoNombre.setText(nombreVie);
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
		
		
		JLabel labelTelfono = new JLabel("Nuevo tel\u00E9fono:");
		labelTelfono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelTelfono.setBounds(31, 121, 93, 17);
		getContentPane().add(labelTelfono);
		
		textoTlfn = new JTextField();
		int limiteTelefono = 13;
		textoTlfn.setBounds(128, 120, 143, 20);
		textoTlfn.setColumns(10);
		textoTlfn.setText(tlfnVie);
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
		
		
		JButton botonAniadir = new JButton("Modificar");
		botonAniadir.setBounds(41, 175, 89, 23);
		botonAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contacto viejo = new Contacto (nombreVie, tlfnVie);
				Contacto nuevo = new Contacto (textoNombre.getText(), textoTlfn.getText());
				try {
					if (textoNombre.getText().matches("[a-zA-Z]+")
							&& textoTlfn.getText().matches("[0-9]+")){
						agenda.modificar(viejo, nuevo);;
						mostrarPopupModificado();
						dispose();
					}
					else {
						if (mostrarPopupContactoRaro()==0){
							agenda.modificar(viejo, nuevo);
							mostrarPopupModificado();
							dispose();
						}
					}
						
				} catch (ContactoRepetidoException e1) {
					mostrarPopupYaExiste();
				}
			}
		});
		getContentPane().add(botonAniadir);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(164, 175, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		getContentPane().add(botonVolver);
		
		JLabel lblModiqueElCampo = new JLabel("Modique el campo o campos deseados.");
		lblModiqueElCampo.setBounds(61, 11, 186, 14);
		getContentPane().add(lblModiqueElCampo);
		
		JLabel lbllosCamposPor = new JLabel("(Los campos por defecto son los propios del contacto)");
		lbllosCamposPor.setBounds(21, 30, 261, 20);
		getContentPane().add(lbllosCamposPor);
		
		setVisible(true);
	}
	
	private void mostrarPopupYaExiste() {
		JOptionPane.showMessageDialog(this,
			    "Está escribiendo los datos de un contacto ya existente.\n Inténtelo de nuevo por favor.",
			    "Advertencia",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	private void mostrarPopupModificado() {
		JOptionPane.showMessageDialog(this,
			    "El nuevo nombre de su contacto es " + textoNombre.getText() + ".\nLa modificación ha sido guardada.",
			    "Completado",
			    JOptionPane.INFORMATION_MESSAGE,
			    new ImageIcon(AgregarContacto.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/aniadircont.png")));
	}
	
	private int mostrarPopupContactoRaro() {
		return JOptionPane.showConfirmDialog(this, 
				"El contacto que intenta añadir es algo inusual, "
				+ "\n¿está seguro de que quiere añadirlo?", "Contacto inusual", 
				JOptionPane.YES_NO_OPTION);
	}
}
