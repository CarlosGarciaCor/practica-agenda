package com.islasfilipinas.cj.interfaz;

import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;

import java.awt.Color;

public class BuscarContacto extends JDialog{
	private JTextField busqueda;
	private JTextField resulNombre;
	private JTextField resulTlfn;
	private Agenda agenda;
	private Contacto resultado;
	
	public BuscarContacto(JFrame padre){
		super(padre);
		this.agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Buscar contacto");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 427, 300);
		getContentPane().setLayout(null);
		
		
		ButtonGroup grupoBotones = new ButtonGroup();
		
		JRadioButton radioNombre = new JRadioButton("Nombre", true);
		radioNombre.setBounds(17, 32, 67, 23);
		getContentPane().add(radioNombre);
		
		JRadioButton radioTel = new JRadioButton("Tel\u00E9fono");
		radioTel.setBounds(17, 58, 67, 23);
		getContentPane().add(radioTel);
		
		grupoBotones.add(radioNombre);
		grupoBotones.add(radioTel);
		
		busqueda = new JTextField();
		busqueda.setBounds(103, 46, 173, 20);
		busqueda.setColumns(10);
		busqueda.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (busqueda.getText().length() >= 20){
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
		getContentPane().add(busqueda);
		
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioNombre.isSelected()){
					resultado = agenda.buscarPorNombre(busqueda.getText().toString());
					resulNombre.setText(resultado.getNombre());
					resulTlfn.setText(resultado.getTelefono());
				}else{
					resultado = agenda.buscarPorTelefono(busqueda.getText().toString());
					resulNombre.setText(resultado.getNombre());
					resulTlfn.setText(resultado.getTelefono());}
			}
		});
		botonBuscar.setBounds(298, 45, 89, 23);
		getContentPane().add(botonBuscar);
		
		JLabel labelBusqueda = new JLabel("Buscar por:");
		labelBusqueda.setBounds(10, 11, 67, 14);
		getContentPane().add(labelBusqueda);
		
		JPanel panelResul = new JPanel();
		panelResul.setBorder(new TitledBorder(new LineBorder(new Color(185, 209, 234), 2), "Resultado de b\u00FAsqueda: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelResul.setBounds(37, 88, 340, 130);
		getContentPane().add(panelResul);
		panelResul.setLayout(null);
		
		JLabel labelTlfn = new JLabel("Tel\u00E9fono:");
		labelTlfn.setBounds(41, 83, 46, 14);
		panelResul.add(labelTlfn);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(46, 42, 41, 14);
		panelResul.add(labelNombre);
		
		resulNombre = new JTextField();
		resulNombre.setColumns(10);
		resulNombre.setBounds(106, 40, 173, 20);
		resulNombre.setEditable(false);
		panelResul.add(resulNombre);
		
		resulTlfn = new JTextField();
		resulTlfn.setColumns(10);
		resulTlfn.setBounds(106, 80, 173, 20);
		resulTlfn.setEditable(false);
		panelResul.add(resulTlfn);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBounds(298, 227, 89, 23);
		botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(botonVolver);
		
		setVisible(true);
	}
}
