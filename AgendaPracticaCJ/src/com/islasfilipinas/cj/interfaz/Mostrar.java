package com.islasfilipinas.cj.interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import com.islasfilipinas.cj.agenda.Agenda;
import com.islasfilipinas.cj.agenda.Contacto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Comparator;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.UIManager;

public class Mostrar extends JDialog {
	private Agenda agenda;
	private JTable table;

	/**
	 * Constructor del JDialog encargado de mostrar el contenido de la agenda.
	 */
	public Mostrar(JFrame padre) {
		super(padre);
		agenda = ((MenuPrincipal) padre).getAgenda();
		setTitle("Mostrar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/iconos/icono_agenda.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(padre.getX()+50, padre.getY()+50, 450, 300);
		getContentPane().setLayout(null);
		
		
		iniciarComponentes(padre);
		setVisible(true);
		
	}

	private void iniciarComponentes(JFrame padre) {	
		String[] nombrecolumnas = {"Nombre", "Teléfono"};
		DefaultTableModel modeloTabla = new DefaultTableModel(nombrecolumnas,0);
		agenda.getContactos().sort(new Comparator<Contacto>() {

			@Override
			public int compare(Contacto o1, Contacto o2) {
				 return (o1.getNombre().compareToIgnoreCase(o2.getNombre()));
			}
		});
		for(Contacto item : agenda.getContactos()){
			String nombre = item.getNombre();
			String telefono = item.getTelefono();
			Object[] contacto = {nombre, telefono};
			modeloTabla.addRow(contacto);
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(351, 227, 73, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				padre.setVisible(true);
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 35, 364, 170);
		getContentPane().add(scrollPane);
		
		table = new JTable(modeloTabla){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		getContentPane().add(btnVolver);
		
	}
}
