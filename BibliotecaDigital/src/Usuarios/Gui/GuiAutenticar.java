/**
 * GuiAutentificar.java
 * 
 * Clase que representa la interfaz de usuario que permite ingresar
 * al sistema , proporcionando un login y un password lo que permitira
 * acceder a las funcioanalidades del sistema de acuerdo a al perfil
 * de usaurio.
 * 
 * JAVA version "1.6.0"
 *  
 * Autor:  Cristian Leornando Rios 
 * Version:   4.0
 */

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

/**
 * Clase que permite manejar el ingreso de usuarios a la
 * bibliteca mediante la solicitud de un login y password
 * de esta manera se logra ingresar al sistema y de acuerdo
 * al perfil realizar diferentes actividades.
 * 
 * @author Cristian Leonardo Rios
 * 
 */

public class GuiAutenticar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuario, contrasena;
	private JTextField campoNombre;
	private JPasswordField campoContrasena;
	private Button botonAutenticar;
	private Button botonCambiarPassword;
	private GuiPrincipal guiPrincipal;

	/**
	 * 
	 * Constructor que permite inicializar los componentes de la interfaz
	 * y recibe la gui principal donde se encuentra ubicada para ser
	 * despues agregada o qutada dinamicamente
	 * 
	 * @param gp
	 */
	public GuiAutenticar(GuiPrincipal gp) {
		this.guiPrincipal = gp;
		this.initComponents();
	}

	/**
	 * Metodo que permite iniciar los componentes mas importantes de la interfaz
	 * asignadoles estilos  y propiedades iniciales
	 */
	private void initComponents() {
		JPanel panelBoton = new JPanel();
		JPanel panelDatosInterno = new JPanel(new GridBagLayout());
		JPanel panelDatos = new JPanel();
		// --------------------------------------------------------
		panelDatosInterno.setBorder(BorderFactory.createEtchedBorder(
				Estilos.colorBorder, Estilos.colorLightBorder));
		// panelBoton.setBorder(BorderFactory.createLineBorder(Color.black));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------

		String title = "::Autentificar::";

		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder,
						Estilos.colorLightBorder), title);
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

	/**
	 * Metodo  que permite inicializar los botones y los campos dandoles
	 * un nombre y una capacidad inicial respectivamente.
	 */
	private void iniciarCampos() {
		campoNombre = new JTextField(20);
		campoContrasena = new JPasswordField(20);
		campoContrasena.addKeyListener(new ManejadorJPasswordField());

		botonAutenticar = new Button("Ingresar al Sistema");
		botonAutenticar.addActionListener(new ManejadorBoton());
		botonCambiarPassword = new Button("Olvide Contraseña");
		botonCambiarPassword.addActionListener(new ManejadorBoton());

	}

	/**
	 * Metodo que permite inicializar las etiquetas
	 * dandole un nombre adecuado un color y un tipo de
	 * letra
	 * 
	 * 
	 */
	private void iniciarLabels() {

		nombreUsuario = new JLabel("  Login  :");
		contrasena = new JLabel("  Contraseña  :");

		contrasena.setFont(Estilos.fontLabels);
		nombreUsuario.setFont(Estilos.fontLabels);

		contrasena.setForeground(Estilos.colorLabels);
		nombreUsuario.setForeground(Estilos.colorLabels);

	}

	/**
	 * Clase que permite manejar los eventos generados por un boton
	 * @author Cristian Leonardo Rios
	 * 
	 */
	private class ManejadorBoton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == botonAutenticar) {
				ControladorVentanaPrincipal controladorVentanaPrincipal = new ControladorVentanaPrincipal();
				if (controladorVentanaPrincipal.verificarUsuario(campoNombre
						.getText(), new String(campoContrasena.getPassword()))) {

					guiPrincipal.setVisible(false);
					guiPrincipal.dispose();

				} else {
					if (controladorVentanaPrincipal.getError() == 1) {
						campoNombre.selectAll();
						campoNombre.requestFocus(true);
					} else if (controladorVentanaPrincipal.getError() == 2) {
						campoContrasena.selectAll();
						campoContrasena.requestFocus(true);
					}
				}

			} else if (evento.getSource() == botonCambiarPassword) {
				GuiPrincipal.insertarPanelCambiar();
				guiPrincipal.repaint();

			}
		}
	}

	/**
	 * 
	 * Clase que permite manejar los eventos generados al presionar las teclas
	 * simulando que se hubiera hecho un clic con el mouse.
	 * @author Cristian Leonardo Rios
	 * 
	 */
	private class ManejadorJPasswordField implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
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
