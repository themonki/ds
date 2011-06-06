/**
 * GuiPrincipal.java
 * 
 * Clase que representa la interfaz de usuario no registrado en la cual 
 * tiene la posibilidad de consultar basica y avanzadaamente, asi mismo
 * la posibilidad de registrarse al sistema para despues ingresar y 
 * tener los privilegios de un usuario normal.
 * 
 * 
 * JAVA version "1.6.0"
 *  
 * Autor(es):  Yerminson Doney Gonzalez 	
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiConsultaAvanzada;
import Consultas.Gui.GuiConsultaBasica;
import Usuarios.Gui.GuiAutenticar;
import Usuarios.Gui.GuiRecuperarPassword;
import Usuarios.Gui.GuiRegistroModificar;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

/**
 * @author 
 *
 */
public class GuiPrincipal extends JFrame
{
		private static final long serialVersionUID = 1L;
	
		// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
		private String estadoInicial = "Inicio";
		private static String ESTADO_REGISTRO = "RegistrandoUsuario";
		private static String ESTADO_INGRESANDO = "Autentificando";
		private String estadoConsultaAvanzada = "consultaAvanzada";

		// Opciones basicas para un usuario
		private JPanel panelOpcionesGenerales;		
		private Button volver;
		private Button crearUsuario;	
		private Button ingresarSistema;
		private Button consultaAvanzada;
		
		private static JLabel ESTADO;
		private JLabel cuenta, busqueda;
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

		// Paneles a usar		
		private static GuiRegistroModificar PANEL_REGISTRO;
		private static GuiAutenticar PANEL_AUTENTICAR;
		private static GuiConsultaBasica PANEL_CONSULTA_BASICA;
		private static GuiConsultaAvanzada PANEL_CONSULTA_AVANZADA;
		private static GuiRecuperarPassword PANEL_RECUPERAR_PASSWORD;
		
		/**
		 * 
		 */
		public GuiPrincipal()
		{
			
			super("::: Sistema de Biblioteca Digital :::");	
			setIconImage(new ImageIcon("recursos/iconos/bd.gif").getImage());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			manejador = new Manejador();	
			
			String tituloMuestra = "::Sistema Biblioteca Digital::";
			
			TitledBorder borde;
			borde = BorderFactory.createTitledBorder(BorderFactory
					.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), tituloMuestra);
			borde.setTitleColor(Estilos.colorTitulo);
			borde.setTitleFont(Estilos.fontTitulo);
			borde.setTitleJustification(TitledBorder.CENTER);
			
			// se instancias paneles adicionales
			PANEL_REGISTRO = new GuiRegistroModificar();
			PANEL_CONSULTA_BASICA = new GuiConsultaBasica();
			PANEL_CONSULTA_AVANZADA = new GuiConsultaAvanzada();
			GuiConsultaBasica.TIPO_USUARIO = 0;
			GuiConsultaAvanzada.TIPO_USUARIO = 0;
			PANEL_AUTENTICAR = new GuiAutenticar(this);
			PANEL_RECUPERAR_PASSWORD = new GuiRecuperarPassword(this);
			
			//se obtiene el contenedor de la gui principal
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
			
			contenidoAyuda = new JMenuItem("Contenido de Ayuda");
			contenidoAyuda.setMnemonic('Y');
			contenidoAyuda.addActionListener(manejador);
			contenidoAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
			
			informacion = new JMenuItem("Acerca de");
			informacion.setMnemonic('c');
			informacion.addActionListener(manejador);

			archivo.add(salir);
			ayuda.add(contenidoAyuda);
			ayuda.add(informacion);

			barra = new JMenuBar();
			barra.add(archivo);
			barra.add(ayuda);
			setJMenuBar(barra);
			

			// Se instancian todos los elementos que pertenecen al panel de
			//opciones
			panelOpcionesGenerales = new JPanel(new GridBagLayout());
			//GridLayout(8,1,2,5)
			volver = new Button("Inicio");
			volver.setIcon(new ImageIcon("recursos/iconos/home.png"));
			volver.addActionListener(manejador);
			crearUsuario = new Button("Registrarse");
			crearUsuario.setIcon(new ImageIcon("recursos/iconos/add_user.png"));
			crearUsuario.addActionListener(manejador);			
			ingresarSistema = new Button("Ingresar");
			ingresarSistema.setIcon(new ImageIcon("recursos/iconos/user.png"));
			ingresarSistema.addActionListener(manejador);
			consultaAvanzada = new Button("Consulta Avanzada");
			consultaAvanzada.addActionListener(manejador);			
			cuenta= new JLabel("Cuenta",JLabel.CENTER);
			busqueda= new JLabel("Busqueda",JLabel.CENTER);
			
			
			
			//crearUsuario.setPreferredSize(new Dimension(10, 10));
			// Se agregan los elementos al panel de opciones del administrador.
			GridBagConstraints restricciones = new GridBagConstraints();
			restricciones.gridy=1;
			restricciones.fill = GridBagConstraints.HORIZONTAL;
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
			
			panelOpcionesGenerales.add(cuenta, restricciones);
			restricciones.gridy++;
			panelOpcionesGenerales.add(crearUsuario, restricciones);
			restricciones.gridy++;			
			panelOpcionesGenerales.add(ingresarSistema, restricciones);			
		
		
			
			
			JPanel panelconOpciones2= new JPanel();
			panelconOpciones2.add(panelOpcionesGenerales);
			
			
		

			ESTADO = new JLabel(estadoInicial);

			CONTENEDOR.add(panelconOpciones2, BorderLayout.WEST);
			CONTENEDOR.add(ESTADO, BorderLayout.SOUTH);
			CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
			
			//setSize(800, 500);
			//centrar en la pantalla
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			setSize(screenSize);
			//this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
						
			//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			//this.setSize(screenSize);
			//System.out.println(this.getWidth());
			//setSize(this.getWidth(),this.getHeight());
			//this.setMinimumSize(new Dimension(this.getWidth(),this.getHeight()));
			//this.setResizable(false);
			setVisible(true);

		}

		/**
		 * @author yerminson
		 *
		 */
		public class Manejador implements ActionListener
		{		
			@Override
			public void actionPerformed(ActionEvent evento)
			{

				if (evento.getSource() == crearUsuario)
				{
					if (ESTADO.getText().equals(estadoInicial))
					{
						GuiConsultaBasica.restaurarTodo();
					
						CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
						CONTENEDOR.add(PANEL_REGISTRO, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_REGISTRO);
						repaint();
					}else if(ESTADO.getText().equals(ESTADO_INGRESANDO))
					{
						
						CONTENEDOR.remove(PANEL_AUTENTICAR);
						CONTENEDOR.add(PANEL_REGISTRO, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_REGISTRO);
						repaint();
						
					}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
					{
						GuiConsultaAvanzada.restaurarTodo();
						CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
						CONTENEDOR.add(PANEL_REGISTRO, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_REGISTRO);
						repaint();
						
					}
				}else if(evento.getSource() == volver)
				{
					
					if (ESTADO.getText().equals(ESTADO_REGISTRO)) {
						CONTENEDOR.remove(PANEL_REGISTRO);
						CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
						ESTADO.setText(estadoInicial);
						repaint();
					}
					else if(ESTADO.getText().equals(ESTADO_INGRESANDO))
					{
						
						CONTENEDOR.remove(PANEL_AUTENTICAR);
						CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
						ESTADO.setText(estadoInicial);
						repaint();
						
					}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
					{
						GuiConsultaAvanzada.restaurarTodo();
						CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
						CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
						ESTADO.setText(estadoInicial);
						repaint();
						
					}
				}
				else if(evento.getSource() == ingresarSistema)
				{
					
					if (ESTADO.getText().equals(ESTADO_REGISTRO))
					{
						CONTENEDOR.remove(PANEL_REGISTRO);
						CONTENEDOR.add(PANEL_AUTENTICAR, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_INGRESANDO);
						repaint();
					}else if(ESTADO.getText().equals(estadoInicial))
					{
						GuiConsultaBasica.restaurarTodo();
						
						CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
						CONTENEDOR.add(PANEL_AUTENTICAR, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_INGRESANDO);
						repaint();
						
					}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
					{
						GuiConsultaAvanzada.restaurarTodo();
						CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
						CONTENEDOR.add(PANEL_AUTENTICAR, BorderLayout.CENTER);
						ESTADO.setText(ESTADO_INGRESANDO);
						repaint();
						
					}
				
				}else if(evento.getSource() == consultaAvanzada)
				{
					
					if (ESTADO.getText().equals(ESTADO_REGISTRO)) {
						CONTENEDOR.remove(PANEL_REGISTRO);
						CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
						ESTADO.setText(estadoConsultaAvanzada);
						repaint();
						//JOptionPane.showMessageDialog(null,"En construccion");
					}else if(ESTADO.getText().equals(estadoInicial))
					{
						GuiConsultaBasica.restaurarTodo();					
						CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
						CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
						ESTADO.setText(estadoConsultaAvanzada);
						repaint();
						//JOptionPane.showMessageDialog(null,"En construccion");
					}else if(ESTADO.getText().equals(ESTADO_INGRESANDO))
					{
						
						CONTENEDOR.remove(PANEL_AUTENTICAR);
						CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
						ESTADO.setText(estadoConsultaAvanzada);
						repaint();
						//JOptionPane.showMessageDialog(null,"En construccion");
						
					}
				}	
				if(evento.getSource() == salir )
				{
					System.exit(0);
					
				}
				if(evento.getSource()==contenidoAyuda){
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
		 * @param args
		 */
		public static void main(String args[]) {

			try
			{				
				NimRODTheme nt = new NimRODTheme("recursos/NimRODThemeFile2.theme");
				NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
				NimRODLookAndFeel.setCurrentTheme(nt);
				UIManager.setLookAndFeel( NimRODLF);
			}
			catch (Exception e){e.printStackTrace();}

		
			GuiPrincipal a = new GuiPrincipal();
			
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
		/**
		 * 
		 */
		public static void cambiarPanelIngresar()
		{
			
			CONTENEDOR.remove(PANEL_REGISTRO);
			CONTENEDOR.add(PANEL_AUTENTICAR, BorderLayout.CENTER);
			ESTADO.setText(ESTADO_INGRESANDO);
			
			
			
		}
		/**
		 * 
		 */
		public static void cambiarPanelRegistro()
		{
			
			CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
			CONTENEDOR.add(PANEL_REGISTRO, BorderLayout.CENTER);
			ESTADO.setText(ESTADO_REGISTRO);
			CONTENEDOR.repaint();
			
			
			
		}
		/**
		 * 
		 */
		public static void cambiarPanelRegistroAvanzado()
		{
			
			CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
			CONTENEDOR.add(PANEL_REGISTRO, BorderLayout.CENTER);
			ESTADO.setText(ESTADO_REGISTRO);
			CONTENEDOR.repaint();
			
			
			
		}
		/**
		 * 
		 */
		public static void insertarPanelCambiar()
		{
			
			CONTENEDOR.remove(PANEL_AUTENTICAR);
			CONTENEDOR.add(PANEL_RECUPERAR_PASSWORD, BorderLayout.CENTER);
			ESTADO.setText("Cambiando Password");
			
			
			
		}

		/**
		 * 
		 */
		public static void cambiarPanelIngresarRemover() {
			CONTENEDOR.remove(PANEL_RECUPERAR_PASSWORD);
			
			CONTENEDOR.add(PANEL_AUTENTICAR, BorderLayout.CENTER);
			ESTADO.setText(ESTADO_INGRESANDO);
		}	
		/**
		 * 
		 */
		public static void cambiarAvanzadaInicio()
		{
			
			
			CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
			CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
			ESTADO.setText("Inicio");
			CONTENEDOR.repaint();
			
		}
		
		
		
		
	

}
