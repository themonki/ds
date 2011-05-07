package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.TitledBorder;

import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;


public class GuiRegistroModificar extends JScrollPane{

	//ATRIBUTOS GUI
	private static final long serialVersionUID = 1L;

	private JTextField campoLoginTF, campoRespuestaSecreta, campoNombre1, campoNombre2, campoApellido1, campoApellido2, campoEmail, campoNivelEscolaridad, campoFechaNacimientoAdmin;
	
	private JLabel login, password, verificacionPassword, preguntaSecreta, respuestaSecreta, nombre1, nombre2, apellido1, apellido2, genero, fechaNacimiento, email, nivelEscolaridad, vinculoUnivalle, perfilLabel, estadoLabel, areasInteres;
	private JPasswordField campoPassword,campoVerificacionPassword;
	private JComboBox campoPreguntaSecreta, campoGenero, campoPerfil, campoEstado, campoAreasInteres, campoVinculoUnivalle;
	private JSpinner campoFechaNacimiento;
	private JPanel panelAreasInteres, panelPrincipal, panelAdministrador, panelDatos, panelBotones; 
	private JButton registrar, modificar;
	private JScrollPane scrollAreaInteres;
	
	private Usuario usuarioModificar; //usuario para construir o modificar, y enviarlo a la base de datos.
	
	private Vector<String> areaConocimientoVector; //contiene las areas de interes que el usuario ha elegido.
	private Vector<AreaConocimiento> areasInteresVector; //vector que contiene todas las areas de interes del sistema.
	private Vector<AreaConocimiento> areasInteresUsuarioViejas; // vector que contendra las areas viejas anadidas las nuevas, y eliminadas la que usuario decide eliminar.

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
	Vector<String> areasInteresArray;
	
	//Estilos.
	//-------------------------------fuentes letras-------------------------
	Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
	Font fontSubtitulos = new Font("Book Antiqua",Font.BOLD, 15);
	Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
	
	//-------------------------------Color letras----------------------------
	Color colorTitulo = new Color(0,50,0);
	Color colorSubtitulo= new Color(0,50,10);
	Color colorLabels= new Color(0,60,0);	
	
	
	//Constructor para registrar usuario.
	public GuiRegistroModificar(){
		this.modo=0;
		this.usuarioModificar= null;
		initComponents();		
	}
	
	//Constructor para modificar usuario.
	public GuiRegistroModificar(Usuario usuarioModificar, int modo){ 
		this.modo=modo; //1 normal 2 
		this.usuarioModificar= usuarioModificar;
		this.areasInteresUsuarioViejas = this.usuarioModificar.getAreas();
		initComponents();		
	}

	//Inicializar todos los JComponents segun modos, y organizar vistas.
	private void initComponents() {

		// titulo del JPanel, segun el modo.
		String title = "";
		if (modo == 0)
			title = "::Registro::";
		if (modo == 1 || modo == 2)
			title = "::Modificar Usuario::";

		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), title);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);

		// Inicializar Labels que apareceran en cualquier modo
		
		login = inicializarLabel("Login:* ");
		nombre1 = inicializarLabel("Primer nombre:* ");
		nombre2 = inicializarLabel("Segundo nombre: ");
		apellido1 = inicializarLabel("Primer apellido:* ");
		apellido2 = inicializarLabel("Segundo apellido: ");
		genero = inicializarLabel("Género: ");
		fechaNacimiento = inicializarLabel("Fecha de nacimiento: ");
		email = inicializarLabel("email:* ");
		nivelEscolaridad = inicializarLabel("Nivel Escolaridad: ");
		vinculoUnivalle = inicializarLabel("Vinculo con Univalle: ");
		areasInteres = inicializarLabel("Áreas de Interés: ");

		if (modo == 0 || modo == 1) {

			// Inicializar labels solo para los modos registro y modificar
			// usuario normal.
			password = inicializarLabel("Password:* ");
			verificacionPassword = inicializarLabel("Verificar password:* ");
			preguntaSecreta = inicializarLabel("Pregunta secreta: ");
			respuestaSecreta = inicializarLabel("Respuesta secreta:* ");
		}

		if (modo == 2) {

			// Inicializar labels solo para modo modificar usuario admin.
			perfilLabel = inicializarLabel("Perfil: ");
			estadoLabel = inicializarLabel("Estado: ");
		}

		//Solo lo necesito si es en modo registro o modificar en modo usuario normal
		if(modo==0 || modo==1){
			// Controlador para hacer consulta de areas de interes.
			ControladorAreaConocimiento controladorAreasInteres = new ControladorAreaConocimiento();

			areasInteresVector = controladorAreasInteres.obtenerAreas();
			
			// Construir areasInteresArray (el que se usar para inicializar el jcombobox), 
			// con el vector areasInteresVector.
			
			areasInteresArray = new Vector<String> (areasInteresVector.size());
			for(int i=0;i<areasInteresVector.size()-1;i++){
				areasInteresArray.add(i, areasInteresVector.elementAt(i).getNombre()); //llenamos el vector con el nombre de las areas que hay en el sistema.
			}
		}

		
		
		//Ininializar JTextField, JPasswordField, JComboBox, JSpinner.DateEditor segun modos.
		
		if(modo==0)//modo registro
			inicializarJComponentsRegistro();
		
		if (modo == 1) // modo modificar usuario normal
			inicializarJComponentsModificarUsuarioNormal();
		
		if (modo == 2) // modo modificar usuario admin
			inicializarJComponentsModificarAdministrador();

		
		
		//Paneles para organizar la vista
		
		
		//Vista Panel Areas de Interes
		panelAreasInteres = new JPanel();
		TitledBorder bordeAreaInteres;
		bordeAreaInteres = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Áreas de interés");
		bordeAreaInteres.setTitleColor(colorSubtitulo);
		bordeAreaInteres.setTitleFont(fontSubtitulos);
		bordeAreaInteres.setTitleJustification(TitledBorder.CENTER);
		panelAreasInteres.setBorder(bordeAreaInteres);
		panelAreasInteres.setSize(450, 100);
		
		//scroll para areas de interes, por si crece demasiado.
		scrollAreaInteres = new JScrollPane(panelAreasInteres);
		scrollAreaInteres.getViewport().add(panelAreasInteres);
		scrollAreaInteres.setPreferredSize(new Dimension(450,100));
		
		//Panel donde se incluira todo.
		panelPrincipal = new JPanel();
		
		//Panel que solo contiene los campos que puede modificarle un administrador
		//a un usuario cualquiera del sistema.
		panelAdministrador = new JPanel(new GridBagLayout());
		
		//Panel botones 
		panelBotones = new JPanel(new GridBagLayout());
				
		//Inicializar e ingresar elementos al panel de datos.
		
		int filaPanelDatos=0; //Variable que indica en que fila se debe incluir un componente.
		panelDatos = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionEtiqueta = configurar(0,0,new Insets(2,14,2,2));
		GridBagConstraints restriccionCampo = configurar(1,0,new Insets(2,40,2,2));
		
		restriccionCampo.ipady=0;
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
		restriccionEtiqueta.gridy = filaPanelDatos;
		restriccionCampo.gridy = filaPanelDatos;
		panelDatos.add(fechaNacimiento,restriccionEtiqueta);
		
		if(modo==1 | modo==0)
			panelDatos.add(campoFechaNacimiento, restriccionCampo);
		if(modo==2)
			panelDatos.add(campoFechaNacimientoAdmin,restriccionCampo);
		
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
		
		if(modo==0 || modo==1){ //para usuarios a registrar, o usuarios normales que van a modificar.
			panelDatos.add(areasInteres,restriccionEtiqueta);
			panelDatos.add(campoAreasInteres, restriccionCampo);
		}
		if(modo==2){
			panelDatos.add(areasInteres,restriccionEtiqueta);
		}
		
		filaPanelDatos++;
		
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
		
		//panelDatos.setBorder(borde);
						
		//Inicializar Botones segun modo.
		registrar = new JButton("REGISTRAR");
		registrar.addActionListener(new ManejadorBoton());
		modificar = new JButton("MODIFICAR");
		modificar.addActionListener(new ManejadorBoton());
		 
		
		GridBagConstraints restriccionBotones = new GridBagConstraints();
		restriccionBotones.gridy=0;
		restriccionBotones.insets= new Insets(4,2,2,2);
		restriccionBotones.anchor = GridBagConstraints.CENTER;
		
		//Elegir boton a introducir segun el modo.
		if(modo == 0){
			restriccionBotones.gridx=0;
			panelBotones.add(registrar, restriccionBotones);
		}
		if(modo==1 || modo==2){
			restriccionBotones.gridx=0;
			panelBotones.add(modificar,restriccionBotones);
		}
		
		//Organizar vista para el panel principal.
		panelPrincipal.setLayout(new BorderLayout(0,10)); 
		panelPrincipal.add(panelDatos,BorderLayout.NORTH);
		panelPrincipal.add(scrollAreaInteres,BorderLayout.CENTER);
		panelPrincipal.add(panelBotones,BorderLayout.SOUTH);
		panelPrincipal.setBorder(borde);
		
		this.getViewport().add(panelPrincipal);
		
		//Inicializar vector de areas de interes usuario.
		 areaConocimientoVector = new Vector<String>();
		
		//Para el modo modificar por parte de un usuario normal
		//se debe tener, ya visualizado en el panel de areas las areas que tiene
		//desde su ultima modificacion o registro.
		if(modo==1){	
			
			for(int i=0; i<areasInteresUsuarioViejas.size();i++){
				String nombreArea = areasInteresUsuarioViejas.elementAt(i).getNombre();
				//System.out.println("Nombre Area: " + nombreArea);
				areaConocimientoVector.add(nombreArea);
				JLabel nuevaArea= new JLabel();
				nuevaArea.setText(nombreArea);				
				nuevaArea.addMouseListener(new ManejadorMouse());			
				panelAreasInteres.add(nuevaArea);
				panelAreasInteres.updateUI();
				
			}
		}
		//Para el modo modificar por parte de un usuario administrador
		//se debe tener, ya visualizado en el panel de areas las areas que tiene
		//desde su ultima modificacion o registro pero no se podran modificar.
		if(modo==2){	
			
			for(int i=0; i<areasInteresUsuarioViejas.size();i++){
				String nombreArea = areasInteresUsuarioViejas.elementAt(i).getNombre();
				//System.out.println("Nombre Area: " + nombreArea);
				areaConocimientoVector.add(nombreArea);
				JLabel nuevaArea= new JLabel();
				nuevaArea.setText(nombreArea);						
				panelAreasInteres.add(nuevaArea);
				panelAreasInteres.updateUI();
				
			}
		}
		
		
		

	}

	//Incializar jcomponents para registro
	
	//Inicializar jcomponents para registrar usuario.
	private void inicializarJComponentsRegistro(){
		
		//JTextField 
		campoLoginTF = new JTextField(10);
		campoLoginTF.addKeyListener(new ManejadorJTextField());
		campoRespuestaSecreta = new JTextField(30);
		campoRespuestaSecreta.addKeyListener(new ManejadorJTextField());
		campoNombre1 = new JTextField(30);
		campoNombre1.addKeyListener(new ManejadorJTextField());
		campoNombre2 = new JTextField(30);
		campoNombre2.addKeyListener(new ManejadorJTextField());
		campoApellido1 = new JTextField(30);
		campoApellido1.addKeyListener(new ManejadorJTextField());
		campoApellido2 = new JTextField(30);
		campoApellido2.addKeyListener(new ManejadorJTextField());
		campoEmail = new JTextField(30);
		campoEmail.addKeyListener(new ManejadorJTextField());
		campoNivelEscolaridad = new JTextField(30);
		campoNivelEscolaridad.addKeyListener(new ManejadorJTextField());

		//JPasswordField
		campoPassword = new JPasswordField(25);
		campoPassword.addKeyListener(new ManejadorJTextField());
		campoVerificacionPassword = new JPasswordField(25);	
		campoVerificacionPassword.addKeyListener(new ManejadorJTextField());
		
		//JComboBox
		campoPreguntaSecreta= new JComboBox(preguntaSecretaArray);
		campoPreguntaSecreta.setSelectedIndex(0); //seleccionar el primero como default.
		campoGenero= new JComboBox(generoArray);
		campoGenero.setSelectedIndex(0);
		campoAreasInteres= new JComboBox(areasInteresArray);
		campoAreasInteres.addActionListener(new ManejadorComboBox());
		campoVinculoUnivalle = new JComboBox(vinculoUnivalleArray);
		campoVinculoUnivalle.setSelectedIndex(0);
		
		
		//Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha = new SpinnerDateModel();
		campoFechaNacimiento = new JSpinner(modeloFecha);
	    campoFechaNacimiento.setFont(fontLabels);
	    campoFechaNacimiento.setForeground(colorLabels);
		JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFechaNacimiento,"yyyy-MM-dd");
		campoFechaNacimiento.setEditor(spinnerFecha);
	    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(false);
	}
	
	//Inicializar jcomponents para modificar en perfil administrador.
	private void inicializarJComponentsModificarAdministrador(){
		
		//Obtener datos del usuario e ingresarlos a los correspondientes JTextField
		campoLoginTF = new JTextField(10);
		campoLoginTF.setText(usuarioModificar.getLogin());
		campoLoginTF.setEditable(false);
		campoNombre1 = new JTextField(30);
		campoNombre1.setText(usuarioModificar.getNombre1());
		campoNombre1.setEditable(false);
		campoNombre2 = new JTextField(30);
		campoNombre2.setText(usuarioModificar.getNombre2());
		campoNombre2.setEditable(false);
		campoApellido1 = new JTextField(30);
		campoApellido1.setText(usuarioModificar.getApellido1());
		campoApellido1.setEditable(false);
		campoApellido2 = new JTextField(30);
		campoApellido2.setText(usuarioModificar.getApellido2());
		campoApellido2.setEditable(false);
		campoEmail = new JTextField(30);
		campoEmail.setText(usuarioModificar.getEmail());
		campoEmail.setEditable(false);
		campoNivelEscolaridad = new JTextField(30);
		campoNivelEscolaridad.setText(usuarioModificar.getNivelEscolaridad());
		campoNivelEscolaridad.setEditable(false);
		
		//Obtener fecha para organizar el formato para mostrar
		Date fechaNacimientoUsuarioModificar = usuarioModificar.getFechaNacimiento();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	    String fechaNacimientoUsuarioModificarString= formatoFecha.format(fechaNacimientoUsuarioModificar);
		campoFechaNacimientoAdmin = new JTextField(fechaNacimientoUsuarioModificarString);
		campoFechaNacimientoAdmin.setEditable(false);
		
		//Obtner datos para seleccionarlos en los jcombobox.
		campoGenero= new JComboBox(generoArray);
		campoGenero.setSelectedItem(usuarioModificar.getGenero());
		campoGenero.setEnabled(false);
		campoVinculoUnivalle=  new JComboBox(vinculoUnivalleArray);
		campoVinculoUnivalle.setSelectedItem(usuarioModificar.getVinculoUnivalle());
		campoVinculoUnivalle.setEnabled(false);
		campoPerfil= new JComboBox(perfilArray);
		campoPerfil.setSelectedIndex(Integer.parseInt(usuarioModificar.getTipo())-1);
		campoEstado= new JComboBox(estadoArray);
		campoEstado.setSelectedIndex(usuarioModificar.getEstado()? 0: 1);
	}
	
	//Inicializar jcomponents para modificar en perfil usuario normal.
	private void inicializarJComponentsModificarUsuarioNormal(){
		
		//Obtener datos de usuario e introducirlos en los JTextField.
		campoLoginTF = new JTextField(10);
		campoLoginTF.setText(usuarioModificar.getLogin());
		campoLoginTF.setEditable(false);
		campoRespuestaSecreta = new JTextField(30);
		campoRespuestaSecreta.setText(usuarioModificar.getRespuestaSecreta());
		campoRespuestaSecreta.addKeyListener(new ManejadorJTextField());
		campoNombre1 = new JTextField(30);
		campoNombre1.setText(usuarioModificar.getNombre1());
		campoNombre1.addKeyListener(new ManejadorJTextField());
		campoNombre2 = new JTextField(30);
		campoNombre2.setText(usuarioModificar.getNombre2());
		campoNombre2.addKeyListener(new ManejadorJTextField());
		campoApellido1 = new JTextField(30);
		campoApellido1.setText(usuarioModificar.getApellido1());
		campoApellido1.addKeyListener(new ManejadorJTextField());
		campoApellido2 = new JTextField(30);
		campoApellido2.setText(usuarioModificar.getApellido2());
		campoApellido2.addKeyListener(new ManejadorJTextField());
		campoEmail = new JTextField(30);
		campoEmail.setText(usuarioModificar.getEmail());
		campoEmail.addKeyListener(new ManejadorJTextField());
		campoNivelEscolaridad = new JTextField(30);
		campoNivelEscolaridad.setText(usuarioModificar.getNivelEscolaridad());
		campoNivelEscolaridad.addKeyListener(new ManejadorJTextField());

		//Obtener password para introducirla en los campo password y verificarPassword.
		campoPassword = new JPasswordField(20);
		campoPassword.setText(usuarioModificar.getContrasena());
		campoPassword.addKeyListener(new ManejadorJTextField());
		campoVerificacionPassword = new JPasswordField(20);
		campoVerificacionPassword.setText(usuarioModificar.getContrasena());
		campoVerificacionPassword.addKeyListener(new ManejadorJTextField());

		//Obtener los datos que deben de aparecer seleccionados en los JComboBox.
		campoPreguntaSecreta = new JComboBox(preguntaSecretaArray);
		campoPreguntaSecreta.setSelectedItem(usuarioModificar.getPreguntaSecreta());		
		campoGenero = new JComboBox(generoArray);
		campoGenero.setSelectedItem(usuarioModificar.getGenero());
		campoAreasInteres= new JComboBox(areasInteresArray);
		campoAreasInteres.addActionListener(new ManejadorComboBox());
		campoVinculoUnivalle= new JComboBox(vinculoUnivalleArray);
		campoVinculoUnivalle.setSelectedItem(usuarioModificar.getVinculoUnivalle());
				
		//Crear spinner para la fecha de nacimiento. y obtenerla del usuario
		Date fechaNacimientoUsuario = usuarioModificar.getFechaNacimiento();
		SpinnerModel modeloFecha = new SpinnerDateModel(fechaNacimientoUsuario,null,null,Calendar.DAY_OF_YEAR);
		campoFechaNacimiento = new JSpinner(modeloFecha);
	    campoFechaNacimiento.setFont(fontLabels);
	    campoFechaNacimiento.setForeground(colorLabels);
		JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFechaNacimiento,"yyyy-MM-dd");
		campoFechaNacimiento.setEditor(spinnerFecha);
	    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(false);
	    
	}
	
	// Inicializa labels, creando una nueva con el nombre indicado(titulo) y
	// estilo de letra y color predifinidos.
	private JLabel inicializarLabel(String titulo) {
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setForeground(colorLabels);
		label.setFont(fontLabels);
		return label;
	}

	//Permite configurar opciones de inseccion, y estilo de los JComponents en los paneles.
	
	//Configura las restricciones para ingresar en el panel de datos.
	private GridBagConstraints configurar(int x, int y, Insets insets){
		
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
	
	//Funcion que limpia los campos despues de registrar.
	private void LimpiarCampos(){
		campoLoginTF.setText("");
		campoNombre1.setText("");
		campoNombre2.setText("");
		campoApellido1.setText("");
		campoApellido2.setText("");
		campoPassword.setText("");
		campoVerificacionPassword.setText("");
		campoEmail.setText("");
		campoPreguntaSecreta.setSelectedIndex(0);
		campoRespuestaSecreta.setText("");
		campoNivelEscolaridad.setText("");
		campoVinculoUnivalle.setSelectedIndex(0);
		campoGenero.setSelectedIndex(0);
		campoAreasInteres.setSelectedIndex(0);
		panelAreasInteres.removeAll();
	}
	
	
	//ActionListeners de diferntes JComponents
	
	//ActionListener de los botones dependiendo del modo.
	private class ManejadorBoton implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== registrar){
				
				System.out.println("Entra y va a ingresar usuario");
				
				String loginString, nombre1String, nombre2String, apellido1String, apellido2String,
				       emailString, passwordString, verPasswordString, preguntaSecretaString, 
				       respuestaSecretaString, nivelEscolaridadString, vinculoUnivalleString,
				       generoString, fechaNacimientoString, fechaRegistroString, perfilString;
				
				Vector<AreaConocimiento> areasInteresUsuario;
				
				perfilString = "3"; //Por default usuario normal, identificado con 3.
				boolean estado= true; //Por default usuario activo.
				
				//Obtener datos
				loginString = campoLoginTF.getText();
				nombre1String = campoNombre1.getText();
				nombre2String = campoNombre2.getText();
				apellido1String = campoApellido1.getText();
				apellido2String = campoApellido2.getText();
				emailString = campoEmail.getText();
				passwordString = new String(campoPassword.getPassword());
				verPasswordString = new String(campoVerificacionPassword.getPassword());
				preguntaSecretaString = (String) campoPreguntaSecreta.getSelectedItem();
				respuestaSecretaString = campoRespuestaSecreta.getText();
				nivelEscolaridadString = campoNivelEscolaridad.getText();
				vinculoUnivalleString = (String) campoVinculoUnivalle.getSelectedItem();
				generoString = (String) campoGenero.getSelectedItem();
				
				Date fecha= ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getModel().getDate();
			    SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
				fechaNacimientoString = formatoFecha.format(fecha);
				java.sql.Date fechaNacimientoDate = java.sql.Date.valueOf(fechaNacimientoString);
				
				Date fechaRegistro = new Date();
				SimpleDateFormat formatoFechaRegistro= new SimpleDateFormat("yyyy-MM-dd");
				fechaRegistroString = formatoFechaRegistro.format(fechaRegistro);
				java.sql.Date fechaRegistroDate = java.sql.Date.valueOf(fechaRegistroString);
				
				areasInteresUsuario = new Vector<AreaConocimiento>();
				
				for(int i=0; i<areaConocimientoVector.size();i++){
					areasInteresUsuario.addElement(
							areasInteresVector.elementAt(
									areasInteresArray.indexOf(
											areaConocimientoVector.elementAt(i))));
				}
				
				if(!(passwordString.equals(verPasswordString))){
					JOptionPane.showMessageDialog(null, "Verifique el password.", "password diferentes", JOptionPane.WARNING_MESSAGE);
					campoPassword.selectAll();
					campoPassword.requestFocus(true);
					campoVerificacionPassword.selectAll();
					campoVerificacionPassword.requestFocus(true);
					return;
				}
				
				//Crear usuario para introducirlo en la base de la biblioteca digital.
				usuarioModificar = new Usuario(loginString,passwordString,nombre1String,
						nombre2String, apellido1String, apellido2String, emailString,
						nivelEscolaridadString, vinculoUnivalleString, preguntaSecretaString,
						respuestaSecretaString, generoString, fechaRegistroDate,
						fechaNacimientoDate, perfilString, estado, areasInteresUsuario);
				
				ControladorUsuario controlador = new ControladorUsuario();
				controlador.insertarUsuario(usuarioModificar);
				controlador.insertarUsuarioAreas(areasInteresUsuario, usuarioModificar);
				System.out.println("Ingresa usuario");
				
				//Se indica que ya se puede loguear.
				JOptionPane.showMessageDialog(null, "Ya se encuentra registrado.\n Puede ingresar al sistema.");
				
				LimpiarCampos();
			}
			if(e.getSource() == modificar){
				if(modo==1){
					String nombre1String, nombre2String, apellido1String, apellido2String,
					emailString, passwordString, verPasswordString, preguntaSecretaString, 
					respuestaSecretaString, nivelEscolaridadString, vinculoUnivalleString,
					generoString, fechaNacimientoString;
					Vector<AreaConocimiento> areasInteresUsuario;

					//Obtener datos
					
					nombre1String = campoNombre1.getText();
					nombre2String = campoNombre2.getText();
					apellido1String = campoApellido1.getText();
					apellido2String = campoApellido2.getText();
					emailString = campoEmail.getText();
					passwordString = new String(campoPassword.getPassword());
					verPasswordString = new String(campoVerificacionPassword.getPassword());
					preguntaSecretaString = (String) campoPreguntaSecreta.getSelectedItem();
					respuestaSecretaString = campoRespuestaSecreta.getText();
					nivelEscolaridadString = campoNivelEscolaridad.getText();
					vinculoUnivalleString = (String) campoVinculoUnivalle.getSelectedItem();
					generoString = (String) campoGenero.getSelectedItem();

					Date fecha= ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getModel().getDate();
					SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
					fechaNacimientoString = formatoFecha.format(fecha);
					java.sql.Date fechaNacimientoDate = java.sql.Date.valueOf(fechaNacimientoString);

					
					//Organizar vector de areasInteresUsuario, esto es
					//mirar que areas se deben eliminar de las antiguas.
					//anadir las nuevas al resultado anterios.
					
					areasInteresUsuario = areasInteresUsuarioViejas;
					
					//Se eliminan areas que el usuario ya no le interesan.
					for(int i=0; i<areasInteresUsuario.size();i++){
						if(areaConocimientoVector.indexOf(
								areasInteresUsuario.elementAt(i).getNombre()) == -1)
						{
							areasInteresUsuario.remove(i);
						}
					}

					//Ingresar las nuevas areas de interes del usuario.
					for(int i=0; i<areaConocimientoVector.size();i++){
						if(areasInteresUsuario.indexOf(
								areasInteresVector.elementAt(
											areasInteresArray.indexOf(
													areaConocimientoVector.elementAt(i)))) == -1 ){
							areasInteresUsuario.addElement(areasInteresVector.elementAt(i));
						}

					} 
					
					if(!(passwordString.equals(verPasswordString))){
						JOptionPane.showMessageDialog(null, "Verifique el password.", "Passwords Diferentes", JOptionPane.WARNING_MESSAGE);
						campoPassword.selectAll();
						campoPassword.requestFocus(true);
						campoVerificacionPassword.selectAll();
						campoVerificacionPassword.requestFocus(true);
						return;
					}

					//se modifica usuario.
					usuarioModificar.setNombre1(nombre1String);
					usuarioModificar.setNombre2(nombre2String);
					usuarioModificar.setApellido1(apellido1String);
					usuarioModificar.setApellido2(apellido2String);
					usuarioModificar.setEmail(emailString);
					usuarioModificar.setContrasena(passwordString);
					usuarioModificar.setPreguntaSecreta(preguntaSecretaString);
					usuarioModificar.setRespuestaSecreta(respuestaSecretaString);
					usuarioModificar.setVinculoUnivalle(vinculoUnivalleString);
					usuarioModificar.setNivelEscolaridad(nivelEscolaridadString);
					usuarioModificar.setGenero(generoString);
					usuarioModificar.setAreas(areasInteresUsuario);
					usuarioModificar.setFechaNacimiento(fechaNacimientoDate);
					

					ControladorUsuario controlador = new ControladorUsuario();
					controlador.modificarUsuario(usuarioModificar);
					
					JOptionPane.showMessageDialog(null, "Se modifico satisfactoriamente sus datos");
					
				}
				if(modo==2){
					
					boolean estadoBool = campoEstado.getSelectedItem().equals("Activo")? true : false;
					int perfilInt = campoPerfil.getSelectedIndex()+1;
					String perfilString;
					perfilString = Integer.toString(perfilInt);
					
					//modificar peril y estado al usuario
					usuarioModificar.setTipo(perfilString);
					usuarioModificar.setEstado(estadoBool);
					ControladorUsuario controlador = new ControladorUsuario();
					controlador.modificarUsuario(usuarioModificar);
					
					JOptionPane.showMessageDialog(null, "Se modifico satisfactoriamente los datos del usuario" + usuarioModificar.getLogin());
				}
			}
			
		}
		
	}

	//ActionListener para manejar areas de interes que seleccione o deseleccione el usuario.
	
	//ActionListener del JComboBox de areas de interes.
	private class ManejadorComboBox implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(e.getSource()== campoAreasInteres){

				if (areaConocimientoVector.indexOf(campoAreasInteres.getSelectedItem())==-1)
				{
					JLabel nuevaArea= new JLabel();
					areaConocimientoVector.add((String) campoAreasInteres.getSelectedItem());
					nuevaArea.setText(""+campoAreasInteres.getSelectedItem());				
					nuevaArea.addMouseListener(new ManejadorMouse());			
					panelAreasInteres.add(nuevaArea);

					panelAreasInteres.updateUI();
				}
			}			
		}

	}
	
	//Manejador para las etiqutas (areas de interes) que agrega el usuario, y
	//se visualizan en el panelAreasInteres.
	private class ManejadorMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel areaSeleccionada =(JLabel) e.getSource();

			int index = areaConocimientoVector.indexOf(areaSeleccionada.getText());
			areaConocimientoVector.remove(index);				
			panelAreasInteres.remove(areaSeleccionada);					
			panelAreasInteres.updateUI();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel areaAEliminar=(JLabel) e.getSource();
			areaAEliminar.setForeground(Color.red);
			areaAEliminar.setIcon(new ImageIcon("recursos/CRUZ.gif"));
			areaAEliminar.updateUI();				
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel area=(JLabel) e.getSource();
			area.setForeground(Color.black);
			area.setIcon(new ImageIcon(""));				
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	
	//KeyListener para no permitir mas de determinados tamanos de cadenas.
	private class ManejadorJTextField implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if( campoEmail == e.getSource()){
				
				if(campoEmail.getText().length()>49)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoEmail.setText(campoEmail.getText().substring(0,48));
					}
				}
			}
			
			if(e.getSource()== campoPassword || e.getSource()== campoVerificacionPassword){
				if(new String(campoPassword.getPassword()).length()>19)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoPassword.setText(new String(campoPassword.getPassword()).substring(0,19));
					}
				}
				if(new String(campoVerificacionPassword.getPassword()).length()>19)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoVerificacionPassword.setText(new String(campoVerificacionPassword.getPassword()).substring(0,19));
					}
				}
				
			}
			if(e.getSource()!= campoEmail & e.getSource()!= campoPassword & e.getSource()!= campoVerificacionPassword)
			{
				JTextField campo = (JTextField)e.getSource();
				if(campo.getText().length()>campo.getColumns()-1)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campo.setText(campo.getText().substring(0,campo.getColumns()-1));
					}

				} 
			}	
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	//Main, frame para ver scrollpane.
	/*public static void main (String args []){

		try
		{	
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
		}
		catch (Exception e){e.printStackTrace();}

		//Ventana para visualizar el jscrollpane
		JFrame ventana;
		ventana = new JFrame();
		GuiRegistroModificar miPanel = new GuiRegistroModificar();
		ventana.getContentPane().add(miPanel);
		ventana.setVisible(true);
		ventana.setSize(650,500);		
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}*/
}