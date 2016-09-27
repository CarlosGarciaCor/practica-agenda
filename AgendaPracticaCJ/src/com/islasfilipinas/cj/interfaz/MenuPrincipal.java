package com.islasfilipinas.cj.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("Agenda de contactos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/com/islasfilipinas/cj/interfaz/icono_agenda.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAgenda = new JMenu("Agenda");
		menuBar.add(mnAgenda);
		
		JMenuItem mntmCargarAgenda = new JMenuItem("Cargar agenda");
		mnAgenda.add(mntmCargarAgenda);
		
		JMenuItem mntmGuardarAgenda = new JMenuItem("Guardar agenda");
		mnAgenda.add(mntmGuardarAgenda);
		
		JMenuItem mntmMostrarAgenda = new JMenuItem("Mostrar agenda");
		
		mntmMostrarAgenda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mostrarAgenda();
			}
		});
		mnAgenda.add(mntmMostrarAgenda);
		
		JMenu mnContactos = new JMenu("Contactos");
		menuBar.add(mnContactos);
		
		JMenuItem mntmAgregarContacto = new JMenuItem("Agregar contacto");
		mnContactos.add(mntmAgregarContacto);
		
		JMenuItem mntmModificarContacto = new JMenuItem("Modificar contacto");
		mnContactos.add(mntmModificarContacto);
		
		JMenuItem mntmEliminarContacto = new JMenuItem("Eliminar contacto");
		mnContactos.add(mntmEliminarContacto);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	private void mostrarAgenda(){
		Mostrar agenda = new Mostrar(this);
	}

}
