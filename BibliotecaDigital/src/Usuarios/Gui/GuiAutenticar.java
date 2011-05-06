/*
 * Nombre autor: Cristian Ríos
 * Responsabilidad: crear la interfaz que permite a un usuario ingresar al sistema.
 * Nombre Archivo: GuiAutenticar.java
 * Fecha creación: Mayo 05 2011
 * Fecha Ultima modificación: Mayo 05 2011 
 * */

package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GuiAutenticar extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuario, contrasena, indicacion;
	private JTextField campoNombre;
	private JPasswordField campoContrasena;
	private JButton botonAutenticar;
	
	public GuiAutenticar()
	{
		this.initComponents();
	}
	
	private void initComponents()
	{
		JPanel panelBoton = new JPanel();
		JPanel panelDatosInterno = new JPanel(new GridBagLayout());
		JPanel panelDatos = new JPanel();
		// --------------------------------------------------------
		panelDatosInterno.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panelBoton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------

		GridBagConstraints restriccionCampo = new GridBagConstraints(), restriccionEtiquetas = new GridBagConstraints();

		restriccionCampo.ipadx = 10;
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(5, 40, 5, 0);

		restriccionEtiquetas.gridy = 0;
		restriccionEtiquetas.gridx = 0;

		restriccionEtiquetas.anchor = GridBagConstraints.WEST;
		restriccionCampo.anchor = GridBagConstraints.WEST;

		panelDatosInterno.add(nombreUsuario, restriccionEtiquetas);
		panelDatosInterno.add(campoNombre, restriccionCampo);

		restriccionEtiquetas.gridy = 1;
		restriccionCampo.gridy = 1;

		panelDatosInterno.add(contrasena, restriccionEtiquetas);
		panelDatosInterno.add(campoContrasena, restriccionCampo);

		panelDatos.add(panelDatosInterno);

		panelBoton.add(botonAutenticar);

		this.setLayout(new BorderLayout());
		this.add(panelDatos, BorderLayout.CENTER);
		this.add(panelDatos, BorderLayout.CENTER);
		this.add(indicacion, BorderLayout.NORTH);
		this.add(panelBoton, BorderLayout.SOUTH);

		this.setSize(430, 220);
		this.setVisible(true);
	}
	
	private void iniciarCampos()
	{
		campoNombre = new JTextField(20);
		campoContrasena = new JPasswordField(20);
		
		botonAutenticar = new JButton("Ingresar al Sistema");
		botonAutenticar.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels()
	{
		Font font1 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 25);

		Color colorletras = new Color(0, 60, 0);

		indicacion = new JLabel("Autenticarse", JLabel.CENTER);
		nombreUsuario = new JLabel("Login  :");
		contrasena = new JLabel("Contraseña  :");
		
		indicacion.setFont(font3);
		contrasena.setFont(font1);
		nombreUsuario.setFont(font1);
		
		indicacion.setForeground(colorletras);
		contrasena.setForeground(colorletras);
		nombreUsuario.setForeground(colorletras);
		
	}
	
	private class ManejadorBoton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String args[])
	{

		try
		{
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		JFrame ventana;
		ventana = new JFrame();
		GuiAutenticar miPanel = new GuiAutenticar();
		ventana.getContentPane().add(miPanel);
		ventana.setVisible(true);
		ventana.setSize(650,500);		
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
