package Usuarios.Gui;

/**
 * GuiRecuperarPassword.java
 * 
 * Clase que representa una interfaz de usuario que permite cambiar 
 * el password en caso de haberlo olvidado, para esto hace uso de la 
 * pregunta secreta que el usuario proporciono cuando se registro al
 * sistema.
 *  
 * JAVA version "1.6.0"
 *  
 * Autor:  Yerminson Gonzalez Munoz
 * Version:   4.0
 */
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Principal.Gui.GuiPrincipal;
import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * 
 * @author Yerminson Gonzalez Munoz
 *
 */
public class GuiRecuperarPassword extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuario;
	private JTextField campoNombre;

	private JLabel preguntaSecreta;
	private JTextField campoDescripcionPregunta;
	private JLabel respuestaPregunta;
	private JTextField campoRespuestaPregunta;

	private JLabel password;
	private JPasswordField campoPassword;
	private JLabel verificarPassword;
	private JPasswordField campoVerificarPassword;

	JPanel panelBotonSolicitud;
	JPanel panelBotonCambiar;

	JPanel panelBotonGuardar;

	JPanel panelConsultarLogin;
	JPanel panelDatosInterno;

	JPanel panelPreguntaSecreta;
	JPanel panelDatosInterno2;

	JPanel panelPassword;
	JPanel panelDatosInterno3;

	JPanel panelGeneral;

	private Button botonIngresar;
	private Button botonRegresar;
	private Button botonCambiarPassword;
	private Button botonGuardar;
	private GuiPrincipal gp;

	/**
	 * Constructor que recibe como parametro la gui principal a la que pertenece
	 * @param gp
	 */
	public GuiRecuperarPassword(GuiPrincipal gp) {
		this.gp = gp;
		this.initComponents();
	}

	/**
	 * Metodo que inicializa los componentes de la gui basicos
	 * 
	 */
	private void initComponents() {
		panelBotonSolicitud = new JPanel();
		panelBotonCambiar = new JPanel();
		panelBotonGuardar = new JPanel();
		panelDatosInterno = new JPanel(new GridBagLayout());
		panelDatosInterno2 = new JPanel(new GridBagLayout());
		panelDatosInterno3 = new JPanel(new GridBagLayout());

		// --------------------------------------------------------
		// panelDatosInterno.setBorder(BorderFactory.createLineBorder(Color.black));
		// panelDatosInterno2.setBorder(BorderFactory.createLineBorder(Color.black));
		// panelBoton.setBorder(BorderFactory.createLineBorder(Color.black));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------

		String title = "::Recuperar Password::";

		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Estilos.colorBorder), title);
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);

		GridBagConstraints restriccionCampo = new GridBagConstraints(), restriccionEtiquetas = new GridBagConstraints();

		restriccionCampo.ipadx = 10;
		restriccionCampo.weightx = 10;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(5, 40, 5, 0);

		restriccionEtiquetas.gridy = 0;
		restriccionEtiquetas.gridx = 0;

		restriccionEtiquetas.anchor = GridBagConstraints.CENTER;
		restriccionCampo.anchor = GridBagConstraints.EAST;

		panelDatosInterno.add(nombreUsuario, restriccionEtiquetas);
		panelDatosInterno.add(campoNombre, restriccionCampo);
		restriccionEtiquetas.gridy = 1;
		restriccionCampo.gridy = 1;

		panelDatosInterno2.add(preguntaSecreta, restriccionEtiquetas);
		panelDatosInterno2.add(campoDescripcionPregunta, restriccionCampo);
		restriccionEtiquetas.gridy = 2;
		restriccionCampo.gridy = 2;
		panelDatosInterno2.add(respuestaPregunta, restriccionEtiquetas);
		panelDatosInterno2.add(campoRespuestaPregunta, restriccionCampo);
		restriccionEtiquetas.gridy = 3;
		restriccionCampo.gridy = 3;

		panelDatosInterno3.add(password, restriccionEtiquetas);
		panelDatosInterno3.add(campoPassword, restriccionCampo);
		restriccionEtiquetas.gridy = 4;
		restriccionCampo.gridy = 4;
		panelDatosInterno3.add(verificarPassword, restriccionEtiquetas);
		panelDatosInterno3.add(campoVerificarPassword, restriccionCampo);

		panelBotonSolicitud.add(botonIngresar);
		panelBotonSolicitud.add(botonRegresar);
		panelBotonCambiar.add(botonCambiarPassword);
		panelBotonGuardar.add(botonGuardar);
		panelConsultarLogin = new JPanel();
		panelConsultarLogin.setLayout(new BorderLayout());
		panelConsultarLogin.add(panelDatosInterno, BorderLayout.CENTER);
		panelConsultarLogin.add(panelBotonSolicitud, BorderLayout.SOUTH);

		panelPreguntaSecreta = new JPanel();

		panelPreguntaSecreta.setLayout(new BorderLayout());
		panelPreguntaSecreta.add(panelDatosInterno2, BorderLayout.CENTER);
		panelPreguntaSecreta.add(panelBotonCambiar, BorderLayout.SOUTH);

		panelPassword = new JPanel();
		panelPassword.setLayout(new BorderLayout());
		panelPassword.add(panelDatosInterno3, BorderLayout.CENTER);
		panelPassword.add(panelBotonGuardar, BorderLayout.SOUTH);

		panelGeneral = new JPanel(new BorderLayout());
		panelGeneral.setBorder(borde);
		panelGeneral.add(panelConsultarLogin, BorderLayout.NORTH);
		panelGeneral.add(panelPreguntaSecreta, BorderLayout.CENTER);
		panelGeneral.add(panelPassword, BorderLayout.SOUTH);

		panelPreguntaSecreta.setVisible(false);
		panelBotonCambiar.setVisible(false);
		panelPassword.setVisible(false);
		panelBotonGuardar.setVisible(false);

		this.add(panelGeneral);

		this.setSize(430, 220);
		this.setVisible(true);
	}

	/**
	 * Metodo que inicializa todas las etiquetas de la interfaz
	 * 
	 */
	private void iniciarCampos() {
		campoNombre = new JTextField(20);
		campoNombre.addKeyListener(new ManejadorJTexField());

		campoRespuestaPregunta = new JTextField(20);

		campoDescripcionPregunta = new JTextField(20);
		campoDescripcionPregunta.setEditable(false);

		campoPassword = new JPasswordField(20);
		campoVerificarPassword = new JPasswordField(20);

		ManejadorBoton manejador = new ManejadorBoton();

		botonIngresar = new Button("Ingresar al Sistema");
		botonIngresar.addActionListener(manejador);
		botonCambiarPassword = new Button("Cambiar Contraseña");

		botonCambiarPassword.addActionListener(manejador);
		botonGuardar = new Button("Guardar");
		botonGuardar.addActionListener(manejador);
		botonRegresar = new Button("Regresar");
		botonRegresar.addActionListener(manejador);

	}

	/**
	 * 
	 * Metodo que permite inicializar las etiquetas de la interfaz
	 * 
	 * 
	 */
	private void iniciarLabels() {

		nombreUsuario = new JLabel("Login  :");
		nombreUsuario.setFont(Estilos.fontLabels);
		nombreUsuario.setForeground(Estilos.colorLabels);

		preguntaSecreta = new JLabel("Pregunta Secreta: ");
		preguntaSecreta.setFont(Estilos.fontLabels);
		preguntaSecreta.setForeground(Estilos.colorLabels);
		respuestaPregunta = new JLabel("Respuesta: ");
		respuestaPregunta.setFont(Estilos.fontLabels);
		respuestaPregunta.setForeground(Estilos.colorLabels);

		password = new JLabel("Password: ");
		password.setFont(Estilos.fontLabels);
		password.setForeground(Estilos.colorLabels);
		verificarPassword = new JLabel("Verifique el Password: ");
		verificarPassword.setFont(Estilos.fontLabels);
		verificarPassword.setForeground(Estilos.colorLabels);

	}

	/**
	 * 
	 * Clase que permite manejar los eventos generados por los botones
	 * @author Yemrinson Gonzalez Munoz
	 *
	 */
	private class ManejadorBoton implements ActionListener {

		Usuario usuario = null;
		ControladorUsuario controladorUsuario;

		@Override
		public void actionPerformed(ActionEvent evento) {
			if (evento.getSource() == botonIngresar) {
				controladorUsuario = new ControladorUsuario();
				String login = campoNombre.getText();
				usuario = controladorUsuario.consultarUsuario(login);
				String nombre = usuario.getNombre1();
				if (nombre == null) {

					JOptionPane.showMessageDialog(null,
							"Login incorrecto por favor intente nuevamente");

				} else {
					panelConsultarLogin.setVisible(false);
					panelBotonSolicitud.setVisible(false);
					panelBotonSolicitud.remove(botonRegresar);
					panelPreguntaSecreta.setVisible(true);
					panelBotonCambiar.add(botonRegresar);
					panelBotonCambiar.setVisible(true);

					campoDescripcionPregunta.setText(usuario
							.getPreguntaSecreta());

				}

			} else if (evento.getSource() == botonCambiarPassword) {
				String respuestaDada = campoRespuestaPregunta.getText();
				String respuestaCorrecta = usuario.getRespuestaSecreta();

				if (respuestaDada.equals(respuestaCorrecta)) {
					panelPreguntaSecreta.setVisible(false);
					panelBotonCambiar.setVisible(false);
					panelBotonCambiar.remove(botonRegresar);
					panelPassword.setVisible(true);
					panelBotonGuardar.add(botonRegresar);
					panelBotonGuardar.setVisible(true);

				} else {
					JOptionPane
							.showMessageDialog(null,
									"No es la respuesta adecuada por favor intente nuevamente");

				}

			} else if (evento.getSource() == botonGuardar) {
				String password = new String(campoPassword.getPassword());
				String verificacionPassword = new String(campoVerificarPassword
						.getPassword());
				if (password.equals(verificacionPassword)) {

					if (!password.equals("")) {

						usuario.setContrasena(password);
						controladorUsuario.modificarUsuario(usuario);
						JOptionPane
								.showMessageDialog(null,
										"Se cambio correctamente la contraseña ahora puede ingresar");
						GuiPrincipal.cambiarPanelIngresarRemover();
						gp.repaint();
						restaurar();
					} else {
						JOptionPane
								.showMessageDialog(null,
										"Los campos no pueden estar vacios por favor intente nuevamente");

					}

				} else {
					JOptionPane
							.showMessageDialog(null,
									"Las contraseñas no coinciden por favor intente nuevamente");
				}

			} else if (evento.getSource() == botonRegresar) {

				GuiPrincipal.cambiarPanelIngresarRemover();
				restaurar();
				gp.repaint();

			}
		}
	}

	/**
	 * Clase que permite manejar todos los eventos generados cuando 
	 * se presiona una tecla especialmente enter despues de llenar algunoas campos.
	 * @author Yerminson Gonzalez Munoz
	 *
	 */
	private class ManejadorJTexField implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				botonIngresar.doClick();
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

	/**
	 * 
	 * Metodo que permite recuperar la gui inicial de autentificar es 
	 * decir volver en caso de que la accion realizada no sea la que
	 * se queria y poder elegir nuevamente la opcion correcta en la 
	 * interfaz.
	 * 
	 */
	private void restaurar() {
		panelConsultarLogin.setVisible(true);
		panelBotonSolicitud.add(botonRegresar);
		panelBotonSolicitud.setVisible(true);

		campoNombre.setText("");
		campoDescripcionPregunta.setText("");
		campoRespuestaPregunta.setText("");

		campoPassword.setText("");
		campoVerificarPassword.setText("");

		panelPassword.setVisible(false);
		panelBotonGuardar.setVisible(false);
		panelPreguntaSecreta.setVisible(false);
		panelBotonCambiar.setVisible(false);

	}

}
