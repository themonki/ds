/*
 * Nombre autor: Cristian Ríos
 * Responsabilidad: crear la interfaz que permite a un usuario ingresar al sistema.
 * Nombre Archivo: GuiAutenticar.java
 * Fecha creación: Mayo 05 2011
 * Fecha Ultima modificación: Mayo 05 2011 
 * */

package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Principal.Controlador.ControladorVentanaPrincipal;
import Principal.Gui.GuiPrincipal;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiAutenticar extends JPanel
{
	private JPanel panel = this;

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuario, contrasena;
	private JTextField campoNombre;
	private JPasswordField campoContrasena;
	private Button botonAutenticar;
	private Button botonCambiarPassword;
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
		panelDatosInterno.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		//panelBoton.setBorder(BorderFactory.createLineBorder(Color.black));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------
		
		String title = "::Autentificar::";

		
		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), title);
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
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
		panelBoton.add(botonCambiarPassword);

		JPanel borde22 = new JPanel();
		borde22.setLayout(new BorderLayout());
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
		
		botonAutenticar = new Button("Ingresar al Sistema");
		botonAutenticar.addActionListener(new ManejadorBoton());
		botonCambiarPassword = new Button("Olvide Contraseña");
		botonCambiarPassword.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels()
	{
	
		nombreUsuario = new JLabel("  Login  :");
		contrasena = new JLabel("  Contraseña  :");
		
		
		contrasena.setFont(Estilos.fontLabels);
		nombreUsuario.setFont(Estilos.fontLabels);
		
		
		contrasena.setForeground(Estilos.colorLabels);
		nombreUsuario.setForeground(Estilos.colorLabels);
		
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
				
				
			}else if(evento.getSource() == botonCambiarPassword)
			{
				GuiPrincipal.insertarPanelCambiar();
				gp.repaint();
				
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
}
