package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Documento.Gui.GuiCatalogarModificar;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Logica.Usuario;


public class GuiRegistroModificar extends JScrollPane{

	//ATRIBUTOS GUI
	JTextField campoLoginTF, campoRespuestaSecreta, campoNombre1, campoNombre2, campoApellido1, campoApellido2, campoEmail, campoNivelEscolaridad, campoFechaNacimientoAdmin;
	JLabel login, password, verificacionPassword, preguntaSecreta, respuestaSecreta, nombre1, nombre2, apellido1, apellido2, genero, fechaNacimiento, email, nivelEscolaridad, vinculoUnivalle, perfilLabel, estadoLabel, areasInteres;
	JPasswordField campoPassword,campoVerificacionPassword;
	JComboBox campoPreguntaSecreta, campoGenero, campoPerfil, campoEstado, campoAreasInteres, campoVinculoUnivalle;
	JSpinner campoFechaNacimiento;
	JPanel panelAreasInteres, panelPrincipal, panelAdministrador, panelDatos, panelBotones; 
	JButton registrar, modificar, cancelar;
	
	Usuario usuarioModificar;

	int modo; // Indica si es modo registrar 0, modo modificar por usuario
				// normal 1 o modo modificar por usuario administrador 2.

	// Arrays para JComboBox
	String preguntaSecretaArray[] = { "Mejor amigo de la infancia",
			"Ciudad natal de la abuela", "Superheroe preferido",
			"Ciudad donde pasa las vacaciones", "Nombre de la primer mascota",
			"Cuento que mas veces ha leido" };
	String vinculoUnivalleArray[] = { "Estudiante de pregrado",
			"Estudiante de postgrado", "Egresado", "Profesor activo",
			"Jubilado", "Ninguno" };
	String generoArray[] = { "M", "F" };
	String perfilArray[] = { "Administrador", "Catalogador", "Usuario Normal" };
	String estadoArray[] = { "Activo", "Desactivo" };
	String areasInteresArray[];

	
	
	//Estilos.
	//-------------------------------fuentes letras-------------------------
	Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
	Font fontSubtitulos = new Font("Book Antiqua",Font.BOLD, 15);
	Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
	
	//-------------------------------Color letras----------------------------
	Color colorTitulo = new Color(0,50,0);
	Color colorSubtitulo= new Color(0,50,10);
	Color colorLabels= new Color(0,60,0);	
	
	
	
	//Constructor para modificar usuario.
	GuiRegistroModificar(Usuario usuarioModificar, int modo){ 
		this.modo=modo;
		this.usuarioModificar= usuarioModificar;
		initComponents();		
	}
	
	//Constructor para registrar usuario.
	GuiRegistroModificar(){
		this.modo=0;
		this.usuarioModificar= null;
		initComponents();		
	}



	public void initComponents() {

		// titulo del panel, segun el modo.
		String title = "";
		if (modo == 0)
			title = "::Registro::";
		if (modo == 1 || modo == 2)
			title = "::Modificar Usuario::";

		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.yellow), title);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);

		// super.setIconImage(new ImageIcon("/recursos/LOGO.png").getImage());

		// Inicializar Labels que apareceran en cualquier modo
		login = inicializarLabel("Login: ");
		nombre1 = inicializarLabel("Primer nombre: ");
		nombre2 = inicializarLabel("Segundo nombre: ");
		apellido1 = inicializarLabel("Primer apellido: ");
		apellido2 = inicializarLabel("Segundo apellido: ");
		genero = inicializarLabel("Género: ");
		fechaNacimiento = inicializarLabel("Fecha de nacimiento: ");
		email = inicializarLabel("email: ");
		nivelEscolaridad = inicializarLabel("Nivel Escolaridad: ");
		vinculoUnivalle = inicializarLabel("Vinculo con Univalle: ");
		areasInteres = inicializarLabel("Areas de Interés: ");

		if (modo == 0 || modo == 1) {

			// Inicializar labels solo para los modos registro y modificar
			// usuario normal.
			password = inicializarLabel("Password: ");
			verificacionPassword = inicializarLabel("Verificar password: ");
			preguntaSecreta = inicializarLabel("Pregunta secreta: ");
			respuestaSecreta = inicializarLabel("Respuesta secreta: ");
		}

		if (modo == 2) {

			// Inicializar labels solo para modo modificar usuario admin.
			perfilLabel = inicializarLabel("Perfil: ");
			estadoLabel = inicializarLabel("Estado: ");
		}

		// Controlador para hacer consulta de areas de interes.
		ControladorAreaConocimiento controladorAreasInteres = new ControladorAreaConocimiento();

		/*Vector<AreaConocimiento> areasInteresVector = controladorAreasInteres.obtenerAreas();
		
		//Construir areasInteresArray, con el vector areasInteresVector.
		
		for(int i=0;i<areasInteresVector.size();i++){
			areasInteresArray[i]= areasInteresVector.elementAt(i).getNombre();
		}*/
		areasInteresArray = new String[1];
		areasInteresArray[0]="areas Uno";
		
		
		
		//Ininializar JTextField, JPasswordField, JComboBox, JSpinner.DateEditor segun modos.
		if(modo==0){//modo registro
		
			campoLoginTF = new JTextField(10);
			campoRespuestaSecreta = new JTextField(30);
			campoNombre1 = new JTextField(30);
			campoNombre2 = new JTextField(30);
			campoApellido1 = new JTextField(30);
			campoApellido2 = new JTextField(30);
			campoEmail = new JTextField(30);
			campoNivelEscolaridad = new JTextField(30);

			campoPassword = new JPasswordField(20);

			campoVerificacionPassword = new JPasswordField(20);	
			
			campoPreguntaSecreta= new JComboBox(preguntaSecretaArray);
			campoGenero= new JComboBox(generoArray);
			campoPerfil= new JComboBox(perfilArray);
			campoEstado= new JComboBox(estadoArray);
			campoAreasInteres= new JComboBox(areasInteresArray);
			campoVinculoUnivalle = new JComboBox(vinculoUnivalleArray);
			
			//Crear spinner para la fecha de nacimiento.
			
			SpinnerModel modeloFecha = new SpinnerDateModel();
			campoFechaNacimiento = new JSpinner(modeloFecha);
		    campoFechaNacimiento.setFont(fontLabels);
		    campoFechaNacimiento.setForeground(colorLabels);
			JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFechaNacimiento,"yyyy-mm-dd");
			campoFechaNacimiento.setEditor(spinnerFecha);
		    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(false);

			campoVerificacionPassword = new JPasswordField(20);

			campoPreguntaSecreta = new JComboBox(preguntaSecretaArray);
			campoGenero = new JComboBox(generoArray);
			campoPerfil = new JComboBox(perfilArray);
			campoEstado = new JComboBox(estadoArray);
			campoAreasInteres = new JComboBox(areasInteresArray);

		}
		if (modo == 1) {// modo modificar usuario normal
			campoLoginTF = new JTextField(usuarioModificar.getLogin());
			campoLoginTF.setEditable(false);
			campoRespuestaSecreta = new JTextField(usuarioModificar
					.getRespuestaSecreta());
			campoNombre1 = new JTextField(usuarioModificar.getNombre1());
			campoNombre2 = new JTextField(usuarioModificar.getNombre2());
			campoApellido1 = new JTextField(usuarioModificar.getApellido1());
			campoApellido2 = new JTextField(usuarioModificar.getApellido2());
			campoEmail = new JTextField(usuarioModificar.getEmail());
			campoNivelEscolaridad = new JTextField(usuarioModificar
					.getNivelEscolaridad());

			campoPassword = new JPasswordField(usuarioModificar.getContrasena());
			campoVerificacionPassword = new JPasswordField(usuarioModificar
					.getContrasena());

			campoPreguntaSecreta = new JComboBox(preguntaSecretaArray);
			campoPreguntaSecreta.setSelectedItem(usuarioModificar
					.getPreguntaSecreta());
			campoGenero = new JComboBox(generoArray);
			campoGenero.setSelectedItem(usuarioModificar.getGenero());

			campoAreasInteres= new JComboBox(areasInteresArray);
			campoVinculoUnivalle= new JComboBox(vinculoUnivalleArray);
			campoVinculoUnivalle.setSelectedItem(usuarioModificar.getVinculoUnivalle());
			
			//Crear spinner para la fecha de nacimiento.
			
			SpinnerModel modeloFecha = new SpinnerDateModel();
			campoFechaNacimiento = new JSpinner(modeloFecha);
		    campoFechaNacimiento.setFont(fontLabels);
		    campoFechaNacimiento.setForeground(colorLabels);
			JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFechaNacimiento,"yyyy-mm-dd");
			campoFechaNacimiento.setEditor(spinnerFecha);
		    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(false);

			campoAreasInteres = new JComboBox(areasInteresArray);


		}
		if (modo == 2) {// modo modificar usuario admin
			campoLoginTF = new JTextField(usuarioModificar.getLogin());
			campoLoginTF.setEditable(false);
			campoNombre1 = new JTextField(usuarioModificar.getNombre1());
			campoNombre1.setEditable(false);
			campoNombre2 = new JTextField(usuarioModificar.getNombre2());
			campoNombre2.setEditable(false);
			campoApellido1 = new JTextField(usuarioModificar.getApellido1());
			campoApellido1.setEditable(false);
			campoApellido2 = new JTextField(usuarioModificar.getApellido2());
			campoApellido2.setEditable(false);
			campoEmail = new JTextField(usuarioModificar.getEmail());
			campoEmail.setEditable(false);
			campoNivelEscolaridad = new JTextField(usuarioModificar
					.getNivelEscolaridad());
			campoNivelEscolaridad.setEditable(false);
			
			//Obtener fecha para organizar el formato para mostrar
			Date fechaNacimientoUsuarioModificar = usuarioModificar.getFechaNacimiento();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		    String fechaNacimientoUsuarioModificarString= formatoFecha.format(fechaNacimientoUsuarioModificar);
			campoFechaNacimientoAdmin = new JTextField(fechaNacimientoUsuarioModificarString);
			campoFechaNacimientoAdmin.setEditable(false);
			
			campoGenero= new JComboBox(generoArray);
			campoGenero.setSelectedItem(usuarioModificar.getGenero());
			campoGenero.setEnabled(false);
			campoVinculoUnivalle=  new JComboBox(vinculoUnivalleArray);
			campoVinculoUnivalle.setSelectedItem(usuarioModificar.getVinculoUnivalle());
			campoVinculoUnivalle.setEnabled(false);
			campoPerfil= new JComboBox(perfilArray);
			campoPerfil.setSelectedIndex(usuarioModificar.getTipo());
			campoEstado= new JComboBox(estadoArray);
			campoEstado.setSelectedIndex(usuarioModificar.getEstado()? 0: 1);
			



		}
		
		//Paneles para organizar la vista
		
		//Vista Panel Areas de Interes
		panelAreasInteres = new JPanel();
		
		TitledBorder bordeAreaInteres;
		bordeAreaInteres = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow),"Áreas de interés");
		bordeAreaInteres.setTitleColor(colorSubtitulo);
		bordeAreaInteres.setTitleFont(fontSubtitulos);
		bordeAreaInteres.setTitleJustification(TitledBorder.CENTER);
		panelAreasInteres.setBorder(bordeAreaInteres);
		panelAreasInteres.setSize(450, 50);
		
		panelPrincipal = new JPanel();
		
		panelAdministrador = new JPanel(new GridBagLayout());
				
		//ingresar elementos al panel de datos
		int filaPanelDatos=0;
		panelDatos = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionEtiqueta = configurar(0,0,new Insets(2,14,2,2));
		GridBagConstraints restriccionCampo = configurar(1,0,new Insets(2,40,2,2));
		panelDatos.add(login, restriccionEtiqueta);
		panelDatos.add(campoLoginTF,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(nombre1,restriccionEtiqueta);
		panelDatos.add(campoNombre1,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(nombre2,restriccionEtiqueta);
		panelDatos.add(campoNombre2,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(apellido1,restriccionEtiqueta);
		panelDatos.add(campoApellido1,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(apellido2,restriccionEtiqueta);
		panelDatos.add(campoApellido2,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(email,restriccionEtiqueta);
		panelDatos.add(campoEmail,restriccionCampo);
		
		if(modo==0 || modo==1){
			filaPanelDatos++;
			restriccionEtiqueta.gridy =filaPanelDatos;
			restriccionCampo.gridy=filaPanelDatos;
			panelDatos.add(password,restriccionEtiqueta);
			panelDatos.add(campoPassword,restriccionCampo);
			filaPanelDatos++;
			restriccionEtiqueta.gridy =filaPanelDatos;
			restriccionCampo.gridy=filaPanelDatos;
			panelDatos.add(verificacionPassword,restriccionEtiqueta);
			panelDatos.add(campoVerificacionPassword,restriccionCampo);
			filaPanelDatos++;
			restriccionEtiqueta.gridy =filaPanelDatos;
			restriccionCampo.gridy=filaPanelDatos;
			panelDatos.add(preguntaSecreta,restriccionEtiqueta);
			panelDatos.add(campoPreguntaSecreta,restriccionCampo);
			filaPanelDatos++;
			restriccionEtiqueta.gridy =filaPanelDatos;
			restriccionCampo.gridy=filaPanelDatos;
			panelDatos.add(respuestaSecreta,restriccionEtiqueta);
			panelDatos.add(campoRespuestaSecreta,restriccionCampo);
		}

		
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(nivelEscolaridad,restriccionEtiqueta);
		panelDatos.add(campoNivelEscolaridad,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(vinculoUnivalle,restriccionEtiqueta);
		panelDatos.add(campoVinculoUnivalle,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(genero,restriccionEtiqueta);
		panelDatos.add(campoGenero,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy=filaPanelDatos;
		panelDatos.add(nivelEscolaridad,restriccionEtiqueta);
		panelDatos.add(campoNivelEscolaridad,restriccionCampo);
		filaPanelDatos++;
		restriccionEtiqueta.gridy =filaPanelDatos;
		restriccionCampo.gridy= filaPanelDatos;
		panelDatos.add(areasInteres,restriccionEtiqueta);
		panelDatos.add(campoAreasInteres, restriccionCampo);
		filaPanelDatos++;
		GridBagConstraints restriccionPanelArea = new GridBagConstraints();
		restriccionPanelArea.gridy= filaPanelDatos;
		restriccionPanelArea.gridwidth= 2;
		restriccionPanelArea.ipadx= 450;
		restriccionPanelArea.ipady= 50;
		restriccionPanelArea.anchor= GridBagConstraints.CENTER;
		panelDatos.add(panelAreasInteres,restriccionPanelArea);
		
		//Ingresar JComponents al panel administrador
		if(modo==2){
			panelAdministrador = new JPanel(new GridBagLayout());
			GridBagConstraints restriccionEtiquetaAdmin = configurar(0,0,new Insets(2,14,2,2));
			GridBagConstraints restriccionCampoAdmin = configurar(1,0,new Insets(2,40,2,2));
			panelAdministrador.add(perfilLabel, restriccionEtiquetaAdmin);
			panelAdministrador.add(campoPerfil, restriccionCampoAdmin);
			restriccionCampoAdmin.gridy=1;
			restriccionEtiquetaAdmin.gridy=1;
			panelAdministrador.add(estadoLabel, restriccionEtiquetaAdmin);
			panelAdministrador.add(campoEstado, restriccionCampoAdmin);
			
			filaPanelDatos++;
			restriccionEtiqueta.gridy =filaPanelDatos;
			panelDatos.add(panelAdministrador,restriccionEtiqueta);
		}
		
		panelDatos.setBorder(borde);
						
		//Inicializar Botones segun modo.
		registrar = new JButton("REGISTRAR");
		modificar = new JButton("MODIFICAR");
		cancelar = new JButton("CANCELAR");
		 
		filaPanelDatos++;
		GridBagConstraints restriccionBotones = new GridBagConstraints();
		restriccionBotones.gridy=filaPanelDatos;
		restriccionBotones.insets= new Insets(4,2,2,2);
		restriccionBotones.anchor = GridBagConstraints.CENTER;
		
		if(modo == 0){
			restriccionBotones.gridx=0;
			panelDatos.add(registrar, restriccionBotones);
			restriccionBotones.gridx=1;
			panelDatos.add(cancelar,restriccionBotones);
		}
		if(modo==1 || modo==2){
			restriccionBotones.gridx=0;
			panelDatos.add(modificar,restriccionBotones);
			restriccionBotones.gridx=1;
			panelDatos.add(cancelar,restriccionBotones);
		}
		
		this.getViewport().add(panelDatos);

	}

	// Inicializa labels, creando una nueva con el nombre indicado(titulo) y
	// estilo
	// de letra y color predifinidos.
	public JLabel inicializarLabel(String titulo) {
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setForeground(colorLabels);
		label.setFont(fontLabels);
		return label;
	}

	//Permite configurar opciones de inseccion, y estilo de los JComponents en los paneles.
	public GridBagConstraints configurar(int x, int y, Insets insets){
		
		GridBagConstraints configuracion = new GridBagConstraints();
		configuracion.gridx=x;
		configuracion.gridy=y;
		configuracion.ipady=10;
		configuracion.ipadx=10;
		configuracion.weighty=10;
		configuracion.weightx=15;
		configuracion.insets= insets;
		configuracion.anchor= GridBagConstraints.WEST;
		return configuracion;
	}
	
	public static void main (String args []){
		
		try
		{
			
			
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
			
		
		}
		catch (Exception e){e.printStackTrace();}
		
		JFrame ventana;
		ventana = new JFrame();
		GuiRegistroModificar miPanel = new GuiRegistroModificar();
		ventana.getContentPane().add(miPanel);
		ventana.setVisible(true);
		ventana.setSize(650,500);		
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		

	}
	
	
	



}
