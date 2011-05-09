/*
 * Nombre autor: Cristian R�os
 * Responsabilidad: crear la interfaz que permite a un usuario ingresar al sistema.
 * Nombre Archivo: GuiAutenticar.java
 * Fecha creaci�n: Mayo 05 2011
 * Fecha Ultima modificaci�n: Mayo 05 2011 
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Principal.Controlador.ControladorVentanaPrincipal;
import Principal.Gui.GuiPrincipal;
import Usuarios.Controlador.ControladorUsuario;

public class GuiAutenticar extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuario, contrasena;
	private JTextField campoNombre;
	private JPasswordField campoContrasena;
	private JButton botonAutenticar;
	private GuiPrincipal gp;
	
	public GuiAutenticar(GuiPrincipal gp)
	{
		this.gp = gp;
		this.initComponents();
	}
	
	private void initComponents()
	{
		JPanel panelBoton = new JPanel();
		JPanel panelDatosInterno = new JPanel(new GridBagLayout());
		JPanel panelDatos = new JPanel();
		// --------------------------------------------------------
		panelDatosInterno.setBorder(BorderFactory.createLineBorder(Color.black));
		//panelBoton.setBorder(BorderFactory.createLineBorder(Color.black));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------
		
		String title = "::Autentificar::";

		Color colorTitulo = new Color(0,50,0);
		Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.black), title);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		
		

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

		JPanel borde22 = new JPanel();
		borde22.setLayout(new BorderLayout());
		borde22.add(panelDatos, BorderLayout.CENTER);
		borde22.add(panelDatos, BorderLayout.CENTER);
		borde22.add(panelBoton, BorderLayout.SOUTH);
		borde22.setBorder(borde);

		this.add(borde22);
		this.setSize(430, 220);
		this.setVisible(true);
	}
	
	private void iniciarCampos()
	{
		campoNombre = new JTextField(20);
		campoContrasena = new JPasswordField(20);
		campoContrasena.addKeyListener(new ManejadorJPasswordField());
		
		botonAutenticar = new JButton("Ingresar al Sistema");
		botonAutenticar.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels()
	{
		Font font1 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 25);

		Color colorletras = new Color(0, 60, 0);

		
		nombreUsuario = new JLabel("Login  :");
		contrasena = new JLabel("Contraseña  :");
		
		
		contrasena.setFont(font1);
		nombreUsuario.setFont(font1);
		
		
		contrasena.setForeground(colorletras);
		nombreUsuario.setForeground(colorletras);
		
	}
	
	private class ManejadorBoton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent evento)
		{

			if(evento.getSource() == botonAutenticar)
			{
				ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal();
				if(controladorVentanaPrincipal.verificarUsuario(campoNombre.getText(),new String(campoContrasena.getPassword())))
				{
					
				
					gp.setVisible(false);
					gp.dispose();
					
					
				}else{
					if(controladorVentanaPrincipal.getError()==1)
					{
						campoNombre.selectAll();
						campoNombre.requestFocus(true);
					}else if(controladorVentanaPrincipal.getError() == 2){
						campoContrasena.selectAll();
						campoContrasena.requestFocus(true);
					}
				}
				
				
			}			
		}	
	}
	
	private class ManejadorJPasswordField implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				botonAutenticar.doClick();			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
/*	public static void main(String args[])
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
	}*/
}
