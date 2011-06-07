/**
 * GuiConsultarUsuario.java
 * 
 * Clase que representa una interfaz de busqueda de usuarios
 * que permite a un administrador del sistema hacer modificaciones
 * con respecto al perfil o eliminacion del usuario con el fin
 * de tener un orden y un buen manejo de los permisos de los 
 * diferentes usuarios dentro de la base de datos.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  
 * Version:   4.0
 */
package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * 
 * Clase que permite consultar usuarios para realizar tareas
 * administrativas con respecto a perfil y acceso a la aplicacion
 * pero no con respecto a datos del usuario.
 * @author 
 *
 */
public class GuiConsultarUsuarios extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JLabel login, nombre;
	private JTextField campoLogin, campoNombre;
	private JScrollPane scrolResultados, scrolUsuario;
	private Button consultar;
	private JPanel panelResultado, panelOpciones, panelPrincipal;
	private String estadoResultado = "";

	// Resultados
	JList resultadoLista;
	DefaultListModel modeloLista;
	Vector<Usuario> usuariosVector;

	/**
	 * Constructor que permite iniciar los elementos principales de la interfaz
	 */
	public GuiConsultarUsuarios() {
		iniComponents();
	}

	private void iniComponents() {

		// InicializarLabels
		login = inicializarLabel("Login: ");
		nombre = inicializarLabel("Nombre: ");

		// InicializarJTextField
		campoLogin = new JTextField(10);
		campoNombre = new JTextField(15);

		// Inicializar Boton
		consultar = new Button("Consultar");
		consultar.addActionListener(new ManejadorBoton());

		panelOpciones = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionesEtiquetas = new GridBagConstraints();
		GridBagConstraints restriccionesCampos = new GridBagConstraints();
		restriccionesEtiquetas.anchor = GridBagConstraints.WEST;
		restriccionesEtiquetas.gridx = 0;
		restriccionesEtiquetas.gridy = 0;
		restriccionesEtiquetas.insets = new Insets(4, 2, 2, 2);
		restriccionesCampos.anchor = GridBagConstraints.WEST;
		restriccionesCampos.gridx = 1;
		restriccionesCampos.gridy = 0;
		restriccionesCampos.insets = new Insets(4, 0, 2, 2);
		panelOpciones.add(login, restriccionesEtiquetas);
		panelOpciones.add(campoLogin, restriccionesCampos);
		restriccionesEtiquetas.gridy = 1;
		restriccionesCampos.gridy = 1;
		panelOpciones.add(nombre, restriccionesEtiquetas);
		panelOpciones.add(campoNombre, restriccionesCampos);
		GridBagConstraints restriccionBoton = new GridBagConstraints();
		restriccionBoton.gridy = 2;
		restriccionBoton.insets = new Insets(4, 2, 2, 2);
		restriccionBoton.anchor = GridBagConstraints.CENTER;
		restriccionBoton.gridwidth = 2;
		panelOpciones.add(consultar, restriccionBoton);

		panelResultado = new JPanel();
		TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder,
						Estilos.colorLightBorder), "::Resultados::");
		borde.setTitleColor(Estilos.colorSubtitulo);
		borde.setTitleFont(Estilos.fontSubtitulos);
		borde.setTitleJustification(TitledBorder.LEFT);
		panelResultado.setBorder(borde);

		panelPrincipal = new JPanel();
		TitledBorder bordePrincipal = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(Estilos.colorBorder,
						Estilos.colorLightBorder), "::Consultar Usuarios::");
		bordePrincipal.setTitleColor(Estilos.colorTitulo);
		bordePrincipal.setTitleFont(Estilos.fontTitulo);
		bordePrincipal.setTitleJustification(TitledBorder.LEFT);
		setBorder(bordePrincipal);
		panelPrincipal.setLayout(new BorderLayout());
		// panelPrincipal.setBorder(bordePrincipal);

		panelPrincipal.add(panelOpciones, BorderLayout.NORTH);

		this.getViewport().add(panelPrincipal);
	}

	/**
	 * Metodo que permite inicializar una etiqueta y la 
	 * retorna para que sea usada en la interfaz.
	 * 
	 * @param titulo
	 * @return JLabel
	 */
	private JLabel inicializarLabel(String titulo) {
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setFont(Estilos.fontLabels);
		label.setForeground(Estilos.colorLabels);
		return label;
	}

	/**
	 * 
	 * Clase que permite manejar los eventos generados por los botones
	 * como por ejemplo consultar o modificar
	 * @author 
	 *
	 */
	private class ManejadorBoton implements ActionListener {
		private JLabel mensajeEstado;

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == consultar) {

				ControladorUsuario controlador = new ControladorUsuario();

				String login = campoLogin.getText();
				String nombre = campoNombre.getText();
				Vector<String> atributo = new Vector<String>();
				Vector<String> valor = new Vector<String>();

				if (login.equals("") & nombre.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debe ingresar al menos un parametro de busqueda.");
					return;
				}

				if (!login.equals("")) {
					atributo.add("login");
					valor.add(login);
				}
				if (!nombre.equals("")) {
					atributo.add("nombre1");
					valor.add(nombre);
				}

				usuariosVector = controlador.consultarUsuarios(atributo, valor);

				if (usuariosVector.size() != 0) {
					modeloLista = null;

					if (resultadoLista == null) {
						resultadoLista = new JList();
					} else {
						resultadoLista.removeAll();
					}
					modeloLista = new DefaultListModel();
					resultadoLista.setModel(modeloLista);
					resultadoLista
							.addListSelectionListener(new ManejadorLista());
					resultadoLista.setPreferredSize(new Dimension(245, 400));

					for (int i = 0; i < usuariosVector.size(); i++) {

						modeloLista.addElement(usuariosVector.elementAt(i));
					}

					if (scrolResultados == null) {
						// PARA CUANDO SE CREE LA LISTA RESULTADO.
						scrolResultados = new JScrollPane(resultadoLista);
					}

					if (estadoResultado.equals("noResultado"))
						panelResultado.remove(mensajeEstado);
					if (estadoResultado.equals("modificandoUsuario")) {
						panelPrincipal.remove(scrolUsuario);
						panelPrincipal.remove(panelResultado);
					}
					estadoResultado = "resultado";

					panelResultado.add(scrolResultados);
					panelPrincipal.add(panelResultado, BorderLayout.CENTER);
					panelPrincipal.updateUI();

				} else {
					if (estadoResultado.equals("resultado"))
						panelResultado.remove(scrolResultados);
					if (estadoResultado.equals("modificandoUsuario")) {
						panelResultado.remove(scrolResultados);
						panelPrincipal.remove(scrolUsuario);
						panelPrincipal.remove(panelResultado);
					}
					estadoResultado = "noResultado";

					if (mensajeEstado == null)
						mensajeEstado = new JLabel("No hay resultados!");

					panelResultado.add(mensajeEstado);
					panelPrincipal.add(panelResultado, BorderLayout.CENTER);
					panelPrincipal.updateUI();
				}
			}
		}
	}

	/**
	 * 
	 * Clase que permite manejar los elementos que se van seleccionando de la lista 
	 * y asi mismo va mostrando el usuario a modificar en el momento
	 * @author
	 * 
	 *
	 */
	private class ManejadorLista implements ListSelectionListener {

		int flag = 0;

		public void valueChanged(ListSelectionEvent e) {
			if (flag == 0) {
				int usuarioElegido = resultadoLista.getSelectedIndex();
				if (usuarioElegido >= 0) {
					estadoResultado = "modificandoUsuario";
					if (scrolUsuario != null) {
						panelPrincipal.remove(scrolUsuario);
					}
					scrolUsuario = new GuiRegistroModificar(
							(Usuario) modeloLista.getElementAt(usuarioElegido),
							2);
					scrolUsuario.setPreferredSize(new Dimension(300, 400));
					panelPrincipal.remove(panelResultado);
					panelPrincipal.add(panelResultado, BorderLayout.WEST);
					panelPrincipal.add(scrolUsuario, BorderLayout.CENTER);
					panelPrincipal.updateUI();
				}
				flag = 1;
			} else {
				flag = 0;
			}
		}

	}
}
