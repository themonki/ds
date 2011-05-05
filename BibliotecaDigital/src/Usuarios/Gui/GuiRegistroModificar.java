package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Logica.Usuario;


@SuppressWarnings({ "serial" })
public class GuiRegistroModificar extends JPanel{

	//ATRIBUTOS GUI
	JTextField campoLoginTF, campoRespuestaSecreta, campoNombre1, campoNombre2, campoApellido1, campoApellido2, campoEmail, campoNivelEscolaridad;
	JLabel login, password, verificacionPassword, preguntaSecreta, respuestaSecreta, nombre1, nombre2, apellido1, apellido2, genero, fechaNacimiento, email, nivelEscolaridad, vinculoUnivalle, perfilLabel, estadoLabel, areasInteres;
	JPasswordField campoPassword,campoVerificacionPassword;
	JComboBox campoPreguntaSecreta, campoGenero, campoPerfil, campoEstado, campoAreasInteres, campoVinculoUnivalle;
	JSpinner campoFechaNacimiento;
	JPanel panelAreasInteres, panelPrincipal, panelAdministrador; 
	JButton registrar, modificar;
	JScrollPane scrollPanelPrincipal;
	
	Usuario usuarioModificar;
	
	int modo; // Indica si es modo registrar 0, modo modificar por usuario normal 1  o modo modificar por usuario administrador 2.
	
	//Arrays para JComboBox
	String preguntaSecretaArray[] = {"Mejor amigo de la infancia", "Ciudad natal de la abuela", "Superheroe preferido", "Ciudad donde pasa las vacaciones", "Nombre de la primer mascota", "Cuento que mas veces ha leido"};
	String vinculoUnivalleArray[] = {"Estudiante de pregrado","Estudiante de postgrado","Egresado","Profesor activo","Jubilado","Ninguno"};
	String generoArray[] = {"M","F"};
	String perfilArray[] = {"Administrador","Catalogador", "Usuario Normal"};
	String estadoArray[] = {"Activo","Desactivo"};
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
	}
	
		
	public void initComponents(){
		
		//titulo del panel, segun el modo.
		String title="";
		if(modo==0)
			title = "::Registro::";
		if(modo==1 || modo==2)
			title= "::Modificar Usuario::";
		
		//Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow),title);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		
		//super.setIconImage(new ImageIcon("/recursos/LOGO.png").getImage());

		//Inicializar Labels que apareceran en cualquier modo
		login = inicializarLabel("Login: ");
		nombre1=inicializarLabel("Primer nombre: ");
		nombre2= inicializarLabel("Segundo nombre: ");
		apellido1= inicializarLabel("Primer apellido: ");
		apellido2= inicializarLabel("Segundo apellido: ");
		genero= inicializarLabel("Género: ");
		fechaNacimiento= inicializarLabel("Fecha de nacimiento: ");
		email= inicializarLabel("email: ");
		nivelEscolaridad= inicializarLabel("Nivel Escolaridad: ");
		vinculoUnivalle= inicializarLabel("Vinculo con Univalle: ");
		areasInteres= inicializarLabel("Areas de Interés: ");
			
	
		if (modo==0 || modo==1){
			
			//Inicializar labels solo para los modos registro y modificar usuario normal.
			password= inicializarLabel("Password: ");
			verificacionPassword= inicializarLabel("Verificar password: ");
			preguntaSecreta= inicializarLabel("Pregunta secreta: ");
			respuestaSecreta= inicializarLabel("Respuesta secreta: ");
		}
				
		if(modo==2){
			
			//Inicializar labels solo para modo modificar usuario admin.
			perfilLabel= inicializarLabel("Perfil: ");
			estadoLabel= inicializarLabel("Estado: ");
		}
		
		//Controlador para hacer consulta de areas de interes.
		ControladorAreaConocimiento controladorAreasInteres = new ControladorAreaConocimiento();
		Vector<AreaConocimiento> areasInteresVector = controladorAreasInteres.obtenerAreas();
		
		//Construir areasInteresArray, con el vector areasInteresVector.
		
		for(int i=0;i<areasInteresVector.size();i++){
			areasInteresArray[i]= areasInteresVector.elementAt(i).getNombre();
		}
		
		
		//Ininializar JTextField, JPasswordField, JComboBox segun modos.
		if(modo==0){//modo registro
			
			campoLoginTF = new JTextField(10);
			campoRespuestaSecreta = new JTextField(30);
			campoNombre1 = new JTextField(30);
			campoNombre2 = new JTextField(30);
			campoApellido1 = new JTextField(30);
			campoApellido2 = new JTextField(30);
			campoEmail = new JTextField(50);
			campoNivelEscolaridad = new JTextField(30);
			
			campoPassword = new JPasswordField(20);
			campoVerificacionPassword = new JPasswordField(20);	
			
			campoPreguntaSecreta= new JComboBox(preguntaSecretaArray);
			campoGenero= new JComboBox(generoArray);
			campoPerfil= new JComboBox(perfilArray);
			campoEstado= new JComboBox(estadoArray);
			campoAreasInteres= new JComboBox(areasInteresArray);
		}
		if(modo==1){//modo modificar usuario normal
			campoLoginTF = new JTextField(usuarioModificar.getLogin());
			campoLoginTF.setEditable(false);
			campoRespuestaSecreta = new JTextField(usuarioModificar.getRespuestaSecreta());
			campoNombre1 = new JTextField(usuarioModificar.getNombre1());
			campoNombre2 = new JTextField(usuarioModificar.getNombre2());
			campoApellido1 = new JTextField(usuarioModificar.getApellido1());
			campoApellido2 = new JTextField(usuarioModificar.getApellido2());
			campoEmail = new JTextField(usuarioModificar.getEmail());
			campoNivelEscolaridad = new JTextField(usuarioModificar.getNivelEscolaridad());
			
			campoPassword = new JPasswordField(usuarioModificar.getContrasena());
			campoVerificacionPassword = new JPasswordField(usuarioModificar.getContrasena());
			
			campoPreguntaSecreta= new JComboBox(preguntaSecretaArray);
			campoPreguntaSecreta.setSelectedItem(usuarioModificar.getPreguntaSecreta());
			campoGenero= new JComboBox(generoArray);
			campoGenero.setSelectedItem(usuarioModificar.getGenero());
			campoAreasInteres= new JComboBox(areasInteresArray);
			
			
		}
		if(modo==2){//modo modificar usuario admin
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
			campoNivelEscolaridad = new JTextField(usuarioModificar.getNivelEscolaridad());
			campoNivelEscolaridad.setEditable(false);
			campoVinculoUnivalle.setEditable(false);
		}
		
		
		 
		this.setLayout(new BorderLayout());
	}
	
	//Inicializa labels, creando una nueva con el nombre indicado(titulo) y estilo 
	//de letra y color predifinidos.
	public JLabel inicializarLabel(String titulo){
		JLabel label= new JLabel(titulo, JLabel.LEFT);
		label.setForeground(colorLabels);
		label.setFont(fontLabels);
		return label;
	}
	
	
	


}
