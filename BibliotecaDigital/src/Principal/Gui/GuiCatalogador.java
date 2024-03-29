/**
 * GuiCatalogador.java
 * 
 * Clase que representa la interfaz de usuario catalogador en la cual 
 * tiene la posibilidad de consultar basica y avanzadaamente, asi mismo
 * la posibilidad de catalogar documentos, ,modificar sus datos de usuario
 * y cuando consulta los documentos tienela posibilidad tanto de descargar 
 * como de editar los documentos.
 * 
 * 
 * JAVA version "1.6.0"
 *  
 * Autor(es):  Cristian Leonardo Rios	
 * 			   Luis Felipe Vargas	
 * 			  
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiConsultaAvanzada;
import Consultas.Gui.GuiConsultaBasica;
import Consultas.Gui.GuiResultadoConsulta;
import Consultas.Logica.Consulta;
import Documento.Gui.GuiCatalogar;
import Documento.Gui.GuiModificarDoc;
import GestionDocumento.Gui.GuiGestionDocumento;
import Usuarios.Gui.GuiNovedades;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;


/**
 * Clase que representa la interfaz de un usuario 
 * catalogador, lo que permite catalogar nuevos documentos
 * y tambien la opcion de modificar los documentos
 * despues de que han sido consultados, tambien
 * tiene las opciones de un usuario normal para modificar
 * informacion y descargar documentos.
 * 
 * 
 * @author 
 *
 */
public class GuiCatalogador extends JFrame
{
	

	private static final long serialVersionUID = 1L;


	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private String estadoInicial = "Inicio";
	private String estadoModificacion = "Modificando Usuario";
	private String estadoConsultaAvanzada = "Consulta Avanzada";	
	private String estadoCatalogando = "Catalogando Documento";
	private String estadoGestionDocumento = "Gestion de Documento";
	private String estadoModificandoDoc = "Modificando Documento";
	private String estadoNovedades = "Novedades Usuario";

	// Opciones basicas para un usuario
	private JPanel panelOpcionesGenerales;		
	private Button volver; //incio
	private Button modificarUsuario;	
	private Button consultaAvanzada;
	private Button logout;
	private Button catalogar;
	private Button novedades;
	private Button gestionDocumento;

	private static JLabel ESTADO;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu ayuda;
	private static Container CONTENEDOR;
	private JMenuItem salir;
	private JMenuItem informacion, contenidoAyuda;
	private JMenuBar barra;

	// otros paneles
	private GuiRegistroModificar panelModificacion;
	private GuiCatalogar panelCatalogar;
	private GuiGestionDocumento panelGestionDocumento;
	private static GuiConsultaBasica panelConsultaBasica;
	private static GuiConsultaAvanzada panelConsultaAvanzada;
	public static GuiModificarDoc panelModificarDoc;
	private static GuiNovedades panelNovedades;
	
	private Usuario usuario;
	private JLabel cuenta;
	private JLabel busqueda;
	private JLabel documento;
	
	public static String LOGIN;
	
	private Vector<Consulta> novedadesUsuario;
	
	
	/**
	 * Constructor que permite inicializar los elementos principales de la interfaz
	 * lo que permite organizar de manera adecuada variables y elementos
	 * de estilo y forma en la interfaz
	 * @param usuario
	 */
	public GuiCatalogador(Usuario usuario)
	{
		
		super("::: Sistema de Biblioteca Digital :::");	
		setIconImage(new ImageIcon("recursos/iconos/bd.gif").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;
		LOGIN = this.usuario.getLogin();
		manejador = new Manejador();	

		novedadesUsuario = new Vector<Consulta>();
		String tituloMuestra = "::Sistema Biblioteca Digital::";

		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), tituloMuestra);
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		
		// se instancias paneles adicionales
		panelModificacion = new GuiRegistroModificar(usuario,1);
		panelCatalogar = new GuiCatalogar(usuario.getLogin());
		panelCatalogar.setGuiRegistroModi(panelModificacion);
		panelGestionDocumento = new GuiGestionDocumento(panelCatalogar);
		panelGestionDocumento.setGuiRegistroModi(panelModificacion);
		panelConsultaBasica = new GuiConsultaBasica();
		panelConsultaAvanzada = new GuiConsultaAvanzada();
		panelNovedades = new GuiNovedades();
	
		GuiConsultaBasica.TIPO_USUARIO = 2;
		GuiConsultaAvanzada.TIPO_USUARIO = 2;
		GuiNovedades.TIPO_USUARIO = 2;
		
		
		
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

		// Se instancian todos los elementos que pertenecen al panel de
		// opciones de catalogador
		panelOpcionesGenerales = new JPanel(new GridBagLayout());

		volver = new Button("Inicio");
		volver.setIcon(new ImageIcon("recursos/iconos/home.png"));
		volver.addActionListener(manejador);
		modificarUsuario = new Button("Modificar Datos");
		modificarUsuario.setIcon(new ImageIcon("recursos/iconos/my_account.png"));
		modificarUsuario.addActionListener(manejador);			
		consultaAvanzada = new Button("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		catalogar = new Button("Catalogar Documento");
		catalogar.setIcon(new ImageIcon("recursos/iconos/add_document.png"));		
		catalogar.addActionListener(manejador);
		
		novedades = new Button("Novedades");
		novedades.addActionListener(manejador);
		
		gestionDocumento = new Button("Gestion de Documento");
		gestionDocumento.setIcon(new ImageIcon("recursos/iconos/add_document.png"));		
		gestionDocumento.addActionListener(manejador);
		
		logout = new Button("Salir");
		logout.setIcon(new ImageIcon("recursos/iconos/logout.png"));
		logout.addActionListener(manejador);
		
		cuenta= new JLabel("Cuenta",JLabel.CENTER);
		busqueda= new JLabel("Busqueda",JLabel.CENTER);
		documento = new JLabel("Documento",JLabel.CENTER);			

		// Se agregan los elementos al panel de opciones del catalogador.
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		restricciones.gridx=1;
		restricciones.gridy=1;
		restricciones.insets= new Insets(0, 0, 20, 0);
		
		panelOpcionesGenerales.add(new JLabel(new ImageIcon("recursos/logo3dpajaro.png")),restricciones);
		restricciones.insets= new Insets(0, 0, 0, 0);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(busqueda, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(volver, restricciones);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(consultaAvanzada, restricciones);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(documento, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(catalogar, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(gestionDocumento, restricciones);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(cuenta, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(modificarUsuario, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(novedades, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(logout, restricciones);
		
		//panelOpcionesGenerales.setBackground(new Color(250, 230,250));
		JPanel panelconOpciones2= new JPanel(); //evita que los botones crescan si la ventana es redimensionada
		panelconOpciones2.add(panelOpcionesGenerales);
		
		ESTADO = new JLabel(estadoInicial);

		CONTENEDOR.add(panelconOpciones2, BorderLayout.WEST);
		CONTENEDOR.add(ESTADO, BorderLayout.SOUTH);
		CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
		//setSize(800, 500);
		//centrar en la pantalla 
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		//this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setResizable(false);
		setVisible(true);

	}

	/**
	 * Clase que permite manejar todos los eventos
	 * generados por botones y se basan mas que todo
	 * en el manejo dinamico de los diferentes paneles dela interfaz
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
					CONTENEDOR.remove(panelConsultaBasica);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{		
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(panelConsultaAvanzada);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				
				}else if(ESTADO.getText().equals(estadoCatalogando))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(panelNovedades);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if (ESTADO.getText().equals(estadoGestionDocumento)){
					CONTENEDOR.remove(panelGestionDocumento);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
				}

			}else if(evento.getSource() == volver)
			{
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(panelConsultaAvanzada);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				
				}else if(ESTADO.getText().equals(estadoCatalogando))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(panelNovedades);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if (ESTADO.getText().equals(estadoGestionDocumento)){
					CONTENEDOR.remove(panelGestionDocumento);
					CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
					
				}
				
			}
			else if(evento.getSource() == consultaAvanzada)
			{
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
					
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(panelConsultaBasica);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				
				}else if(ESTADO.getText().equals(estadoCatalogando))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(panelNovedades);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if (ESTADO.getText().equals(estadoGestionDocumento)){
					CONTENEDOR.remove(panelGestionDocumento);
					CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
				}
			}
			else if(evento.getSource() == catalogar)
			{
				if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(panelConsultaBasica);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogando);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(panelConsultaAvanzada);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogando);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogando);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogando);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(panelNovedades);
					CONTENEDOR.add(panelCatalogar, BorderLayout.CENTER);
					ESTADO.setText(estadoCatalogando);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if (ESTADO.getText().equals(estadoGestionDocumento)){
					CONTENEDOR.remove(panelGestionDocumento);
					CONTENEDOR.add(panelCatalogar, BorderLayout.CENTER);
					ESTADO.setText(estadoCatalogando);
					repaint();
					
				}
				
			}else if (evento.getSource() ==gestionDocumento)
			{
				if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(panelConsultaBasica);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(panelConsultaAvanzada);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();
				}else if(ESTADO.getText().equals(estadoCatalogando))
				{
					
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					
					CONTENEDOR.remove(panelNovedades);
					CONTENEDOR.add(panelGestionDocumento);
					ESTADO.setText(estadoGestionDocumento);
					repaint();				
					
				}
			}
			else if(evento.getSource() == novedades)
			{
				GuiResultadoConsulta.TIPO_CONSULTA = 3;
				if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(panelConsultaBasica);
					CONTENEDOR.add(panelNovedades);
					ESTADO.setText(estadoNovedades);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(panelConsultaAvanzada);
					CONTENEDOR.add(panelNovedades);
					ESTADO.setText(estadoNovedades);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelNovedades);
					ESTADO.setText(estadoNovedades);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(panelModificarDoc);
					CONTENEDOR.add(panelNovedades);
					ESTADO.setText(estadoNovedades);
					repaint();
				}else if(ESTADO.getText().equals(estadoCatalogando))
				{
					
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelNovedades);
					ESTADO.setText(estadoNovedades);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if (ESTADO.getText().equals(estadoGestionDocumento)){
					CONTENEDOR.remove(panelGestionDocumento);
					CONTENEDOR.add(panelNovedades);
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
			if(evento.getSource() == informacion)
			{
				JOptionPane.showMessageDialog(null,
						"Sistema Biblioteca Digital" +
						"Desarrollado por:\n" +
						"		 Maria Andrea Cruz  \n" +
						"		 Cristian Leonardo Rios  \n" +
						"		 Edgar Andres Moncada \n" +
						"		 Luis Felipe Vargas \n" +
						"		 Yerminson Gonzalez Munoz");
			}
		}
	}

	/**
	 * 
	 * Permite regresar desde el panel de moficacion al panel inicio
	 * que es donde se encuentra consulta basica
	 */
	public void cambiarPanelInicio()
	{
		
		CONTENEDOR.remove(panelModificacion);
		CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
		ESTADO.setText(estadoInicial);
		repaint();
		
	}
	/**
	 * 
	 * Permite cambiar desde al panel de consulta basica al 
	 * panel de edicion de documento donde se pueden hacer
	 * modifaciones sobre el documento seleccionado
	 * 
	 * 
	 */
	public static void cambiarPanelEditarDocumento()
	{
		
		CONTENEDOR.remove(panelConsultaBasica);
		CONTENEDOR.add(panelModificarDoc, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}
	/**
	 * Metodo que permite cambiar de la interfaz consulta 
	 * avanzada a la interfaz de edicion de documento
	 * Cuando realizamos una consulta y la vista detallada del
	 * documento deseamos editarlo 
	 * 
	 */
	public static void cambiarPanelEditarDocumentoAvanzado()
	{
		
		CONTENEDOR.remove(panelConsultaAvanzada);
		CONTENEDOR.add(panelModificarDoc, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 * Metodo que permite cambiar desde la vista detallada
	 * de la interfaz novedades a la interfaz editar documento
	 * en caso de que un catalogador vea que se ha catalogado
	 * nuevos documentos tiene la posibilidad de modificarlos
	 * desde la seleccion de uno en la interfaz de novedades
	 * 
	 * 
	 */
	public static void cambiarPanelEditarDocumentoNovedades()
	{
		
		CONTENEDOR.remove(panelNovedades);
		CONTENEDOR.add(panelModificarDoc, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}
	/**
	 * Metodo que permite cambiar desde la opcion de edicion documento
	 * hasta la interfaz devista detallada documento
	 * 
	 */
	public static void cambiarPanelVista()
	{
		
		
		CONTENEDOR.remove(panelModificarDoc);
		CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	/**
	 * Metodo que permite cambiar desde la interfaz de editar
	 * documento al panel de vista avanzada de documento donde
	 * se pueden ver los efectos del cambio
	 */
	public static void cambiarPanelVistaAvanzado()
	{
		
		
		CONTENEDOR.remove(panelModificarDoc);
		CONTENEDOR.add(panelConsultaAvanzada, BorderLayout.CENTER);
		ESTADO.setText("ConsultaAvanzada");
		CONTENEDOR.repaint();
		
	}
	/**
	 * Metodo que permite cambiar desde el panel de consulta avanazda al
	 * de consulta basica 
	 * 
	 */
	public static void cambiarAvanzadaInicio()
	{
		
		
		CONTENEDOR.remove(panelConsultaAvanzada);
		CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 * Metodo que permite cambiar desde el panel novedades
	 * a la consulta basica es decir al panel de inicio
	 */
	public static void cambiarNovedadesInicio()
	{
		
		
		CONTENEDOR.remove(panelNovedades);
		CONTENEDOR.add(panelConsultaBasica, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	
	/**
	 * 
	 * Metodo que orgaiza las novedades cuando se inicializa la gui
	 * mostrando el total de documentos catalogados en las areas de
	 * interes de un usuario.
	 * 
	 * @param novedades
	 */
	public void setNovededadesUsuario(Vector<Consulta> novedades)
	{
		
		
		GuiNovedades.PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(novedades, 10);
		GuiNovedades.PANEL_NOVEDADES.add(GuiNovedades.PANEL_RESULTADO_CONSULTA);
		
		GuiResultadoConsulta.TIPO_CONSULTA = 3;
		this.novedades.setText("Novedades("+novedades.size()+")");
		panelNovedades.updateUI();
		novedadesUsuario = novedades;	
	}
	/**
	 * Metodo que retorna las consultas de documentos relacionadas con las areas de interes de
	 * un usuario catalogador
	 * @return Vector<Consulta> con los documentos de las areas de interes
	 */
	public Vector<Consulta> getNovededadesUsuario()
	{
		return novedadesUsuario;			
		
	}

}
