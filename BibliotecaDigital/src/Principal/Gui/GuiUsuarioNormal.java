/**
 * GuiUsuarioNormal.java
 * 
 * Clase que representa la interfaz de usuario normal en la cual 
 * tiene la posibilidad de consultar basica y avanzadaamente, asi mismo
 * como un reporte de los documentos que se han catalogado despues de 
 * su ultimo acceso y la posibilidad de cambiar datos sobre su 
 * informacion. Este perfil tiene la posibilidad de descargar los documentos 
 * consultados.
 * 
 * JAVA version "1.6.0"
 *  
 * Autor(es):  Cristian Leornardo Rios
 * 			   Luis Felipe Vargas	
 * 			   Yerminson Doney Gonzalez 	
 * Version:   4.0
 */



package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiConsultaAvanzada;
import Consultas.Gui.GuiConsultaBasica;
import Consultas.Gui.GuiResultadoConsulta;
import Consultas.Logica.Consulta;
import Usuarios.Gui.GuiNovedades;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * 
 * Clase que permite mostrar las diferentes funcionalidades que 
 * tiene un usuario normal
 * @author 
 *
 */
public class GuiUsuarioNormal extends JFrame
{
	private static final long serialVersionUID = 1L;


	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private String estadoInicial = "Inicio";
	private String estadoModificacion = "ModificandoUsuario";
	private String estadoConsultaAvanzada = "ConsultaAvanzada";		
	private String estadoNovedades = "NovedadesUsuario";

	// Opciones basicas para un usuario normal
	private JPanel panelOpcionesGenerales;		
	private Button volver; //incio
	private Button modificarUsuario;	
	private Button consultaAvanzada;
	private Button novedades;
	private Button logout;
	
	private static  JLabel ESTADO;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu ayuda;
	private static  Container CONTENEDOR;
	private JMenuItem salir;
	private JMenuItem informacion, contenidoAyuda;
	private JMenuBar barra;
	private Usuario usuario;
	public static String LOGIN;

	// Otor paneles a usar
	private static GuiRegistroModificar PANEL_REGISTRO_MODIFICAR;
	private static GuiConsultaBasica PANEL_CONSULTA_BASICA;
	private static GuiConsultaAvanzada PANEL_CONSULTA_AVANZADA;
	private static GuiNovedades PANEL_NOVEDADES;
	
	private Vector<Consulta> novedadesUsuario;
	
	private JLabel cuenta;
	private JLabel busqueda;

	
	
	/**
	 * Constructor que permite inicializar los principales campos de
	 * la interfaz y realizar arreglos relacionados con el estilo de la aplicacion
	 * @param usuario
	 */
	public GuiUsuarioNormal(Usuario usuario)
	{
		super("::: Sistema de Biblioteca Digital :::");	
		setIconImage(new ImageIcon("recursos/iconos/bd.gif").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;
		LOGIN = this.usuario.getLogin();
		novedadesUsuario = new Vector<Consulta>();

		manejador = new Manejador();	
	
		String tituloMuestra = "::Sistema Biblioteca Digital::";

		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), tituloMuestra);
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		
		// se instancias paneles adicionales
		PANEL_REGISTRO_MODIFICAR = new GuiRegistroModificar(usuario,1);
		PANEL_CONSULTA_BASICA = new GuiConsultaBasica();
		PANEL_CONSULTA_AVANZADA = new GuiConsultaAvanzada();
		PANEL_NOVEDADES = new GuiNovedades();
		GuiConsultaBasica.TIPO_USUARIO = 3;		
		GuiConsultaAvanzada.TIPO_USUARIO = 3;	
		GuiNovedades.TIPO_USUARIO = 3;
		
		
		
		CONTENEDOR = getContentPane();
		CONTENEDOR.setLayout(new BorderLayout(20,20));
		((JComponent) CONTENEDOR).setBorder(borde);
		
		// Se instancian todos los elementos de la barra del menu.
		archivo = new JMenu("Archivo");
		archivo.setMnemonic('A');

		ayuda = new JMenu("Ayuda ");
		ayuda.setMnemonic('y');

		salir = new JMenuItem("Salir");
		salir.setMnemonic('S');
		salir.addActionListener(manejador);
		salir.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));

		informacion = new JMenuItem("Acerca de ");
		informacion.setMnemonic('c');
		informacion.addActionListener(manejador);
		
		contenidoAyuda = new JMenuItem("Contenido de Ayuda");
		contenidoAyuda.setMnemonic('y');
		contenidoAyuda.addActionListener(manejador);
		contenidoAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
		
		archivo.add(salir);
		ayuda.add(contenidoAyuda);
		ayuda.add(informacion);

		barra = new JMenuBar();
		barra.add(archivo);
		barra.add(ayuda);
		setJMenuBar(barra);

		// Se instancian todos los elementos que pertenecen al panel del
		// usuario normal
		panelOpcionesGenerales = new JPanel(new GridBagLayout());
		
		
		volver = new Button("Inicio");
		volver.setIcon(new ImageIcon("recursos/iconos/home.png"));
		volver.addActionListener(manejador);
		modificarUsuario = new Button("Modificar Datos");
		modificarUsuario.setIcon(new ImageIcon("recursos/iconos/my_account.png"));
		modificarUsuario.addActionListener(manejador);			
		consultaAvanzada = new Button("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		
		novedades = new Button("Novedades");
		novedades.addActionListener(manejador);
		
		logout = new Button("Salir");
		logout.setIcon(new ImageIcon("recursos/iconos/logout.png"));
		logout.addActionListener(manejador);
		
					

		// Se agregan los elementos al panel de opciones del usuario normal.
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx=1;
		restricciones.gridy=1;
		restricciones.insets= new Insets(0, 0, 20, 0);
		
		cuenta= new JLabel("Cuenta",JLabel.CENTER);
		busqueda= new JLabel("Busqueda",JLabel.CENTER);
		
		
		panelOpcionesGenerales.add(new JLabel(new ImageIcon("recursos/logo3dpajaro.png")),restricciones);
		restricciones.insets= new Insets(0, 0, 0, 0);
		restricciones.gridy++;
		panelOpcionesGenerales.add(busqueda, restricciones);
		restricciones.gridy=3;
		panelOpcionesGenerales.add(volver, restricciones);
		restricciones.gridy=4;
		panelOpcionesGenerales.add(consultaAvanzada, restricciones);
		restricciones.gridy=5;
		panelOpcionesGenerales.add(cuenta, restricciones);
		restricciones.gridy=6;
		panelOpcionesGenerales.add(modificarUsuario, restricciones);
		restricciones.gridy=7;
		panelOpcionesGenerales.add(novedades,restricciones);	
		restricciones.gridy=8;
		panelOpcionesGenerales.add(logout, restricciones);
		
		//panelOpcionesGenerales.setBackground(new Color(250, 230 , 250));
		JPanel panelconOpciones2= new JPanel(); //evita que los botones crescan si la ventana es redimensionada
		panelconOpciones2.add(panelOpcionesGenerales);
		
		ESTADO = new JLabel(estadoInicial);

		CONTENEDOR.add(panelconOpciones2, BorderLayout.WEST);
		CONTENEDOR.add(ESTADO, BorderLayout.SOUTH);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
	
		//setSize(800, 500);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setSize(screenSize);
		setVisible(true);

	}
	
	/**
	 * Metodo que permite realizar le manejo de los eventos 
	 * generados por un boton
	 * 
	 * @author 
	 *
	 */
	public class Manejador implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent evento)
		{

			if (evento.getSource() == modificarUsuario)
			{			
				if (ESTADO.getText().equals(estadoInicial))
				{			
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(PANEL_REGISTRO_MODIFICAR, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{	
					GuiConsultaAvanzada.restaurarTodo();					
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(PANEL_REGISTRO_MODIFICAR, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(PANEL_REGISTRO_MODIFICAR, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}

			}else if(evento.getSource() == volver)
			{
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(PANEL_REGISTRO_MODIFICAR);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				}
				else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();	
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
					
				}
				else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
				
			}
			else if(evento.getSource() == consultaAvanzada)
			{
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
				
					CONTENEDOR.remove(PANEL_REGISTRO_MODIFICAR);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
			}
			else if(evento.getSource() == novedades)
			{
				GuiResultadoConsulta.TIPO_CONSULTA = 3;
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
				
					CONTENEDOR.remove(PANEL_REGISTRO_MODIFICAR);
					CONTENEDOR.add(PANEL_NOVEDADES, BorderLayout.CENTER);
					ESTADO.setText(estadoNovedades);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(PANEL_NOVEDADES, BorderLayout.CENTER);
					ESTADO.setText(estadoNovedades);
					repaint();					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(PANEL_NOVEDADES, BorderLayout.CENTER);
					ESTADO.setText(estadoNovedades);
					repaint();					
				}
			}
			else if(evento.getSource() == logout)
			{

				new GuiPrincipal();
				dispose();
			}else if(evento.getSource() == salir )
			{
				System.exit(0);
				
			}else if(evento.getSource()==contenidoAyuda){
				try {
					Desktop.getDesktop().browse(new URI("http://bibliotecadigitaleisc.wikispaces.com/"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo que permite cambiar desde el panel de modificacion hasta 
	 * el panel de inicio que es donde se encuentra consulta avanzada.
	 */
	public void cambiarPanelInicio()
	{
		CONTENEDOR.remove(PANEL_REGISTRO_MODIFICAR);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText(estadoInicial);
		repaint();
		
		
	}
	/**
	 * 
	 * Metodo que permite manejar los paneles dinamicamente 
	 * es decir cambiar desde una consulta avanzada hasta la
	 * consulta basica
	 * 
	 */
	public static void cambiarAvanzadaInicio()
	{
		
		
		CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 * Metodo que permite cambiar desde de novedades hasta el
	 * lugar de la consulta basica
	 * 
	 */
	public static void cambiarNovedadesInicio()
	{
		
		
		CONTENEDOR.remove(PANEL_NOVEDADES);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	
	/**
	 * 
	 * Metodo que permite actualizar la interfaz para presentar las
	 * novedades al usuario, de la misma manera que se haria en una
	 * consulta basica o avanzada.
	 * @param novedades
	 */
	public void setNovededadesUsuario(Vector<Consulta> novedades)
	{
	
		
		GuiNovedades.PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(novedades, 10);
		GuiNovedades.PANEL_NOVEDADES.add(GuiNovedades.PANEL_RESULTADO_CONSULTA);
		
		GuiResultadoConsulta.TIPO_CONSULTA = 3;
		this.novedades.setText("Novedades("+novedades.size()+")");
		PANEL_NOVEDADES.updateUI();
		novedadesUsuario = novedades;	
		
	}
	/**
	 * 
	 * Metodo que retorna las novedades del usuario es decir
	 * el vector de consultas. En caso de ser vacio el
	 * usuario no tiene novedades es decir documentos que se 
	 * le puedan mostrar como nuevos.
	 * 
	 * @return
	 */
	public Vector<Consulta>getNovededadesUsuario()
	{
		return novedadesUsuario;			
		
	}
}
