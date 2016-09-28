package com.islasfilipinas.cj.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import com.islasfilipinas.cj.agenda.Contacto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Mostrar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Mostrar dialog = new Mostrar(new MenuPrincipal());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Mostrar(Frame propietario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mostrar.class.getResource("/com/islasfilipinas/cj/interfaz/icono_agenda.png")));
		setBounds(100, 100, 450, 300);
		BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.add(table, BorderLayout.NORTH);
			}
			
			Contacto c1 = new Contacto("Pedro", "691745679");
			DefaultListModel<Contacto> listaContactos = new DefaultListModel<Contacto>();
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);
			listaContactos.addElement(c1);

		}
					
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton volverButton = new JButton("Volver");
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					propietario.setVisible(true);
					dispose();
					}
				});
				volverButton.setActionCommand("OK");
				buttonPane.add(volverButton);
				getRootPane().setDefaultButton(volverButton);
			}
		}
	}

}
