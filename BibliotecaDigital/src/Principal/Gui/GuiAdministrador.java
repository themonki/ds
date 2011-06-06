/**
 * GuiAdministrador.java
 * 
 * Clase que representa la interfaz de usuario administrador ,en la cual 
 * tiene la posibilidad de consultar basica y avanzadamente, asi mismo
 * la posibilidad de catalogar documentos,modificar sus datos,asignar perfiles
 * de usuario y eliminarlos del sistema.Y cuando consulta los documentos tiene 
 * la posibilidad tanto de descargar como de editar los documentos.
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
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Documento.Gui.GuiCatalogar;
import Documento.Gui.GuiModificarDoc;
import Reportes.Gui.GuiReportes;
import Usuarios.Gui.GuiConsultarUsuarios;
import Usuarios.Gui.GuiNovedades;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * @author 
 *
 */
public class GuiAdministrador extends JFrame
{
	
	private static final long serialVersionUID = 1L;

	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private String estadoInicial = "Inicio";
	private String estadoModificacion = "ModificandoUsuario";
	private String estadoConsultarUsuario ="ConsultarUsuario";
	private String estadoConsultaAvanzada = "ConsultaAvanzada";
	private String estadoCatalogar = "Catalogar";
	private String estadoModificandoDoc = "Modificando Documento";
	private String estadoNovedades = "Novedades Usuario";
	private String estadoReporte = "Generar Reportes";

	// Opciones basicas para un usuario
	private JPanel panelOpcionesGenerales;		
	private Button volver;
	private Button modificarUsuario;
	private Button modificarMiUsuario;
	private Button consultaAvanzada;
	private Button catalogar;
	private Button logout;
	private Button novedades;
	private Button reportes;
	
	private static JLabel ESTADO;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private static Container CONTENEDOR;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;

	// otros paneles
	private GuiRegistroModificar panelModificacion;
	private GuiConsultarUsuarios panelConsultarUsuarios;
	private static GuiConsultaBasica PANEL_CONSULTA_BASICA;
	private static GuiConsultaAvanzada PANEL_CONSULTA_AVANZADA;
	public static GuiModificarDoc PANEL_MODIFICAR_DOCUMENTO;
	private GuiCatalogar panelCatalogar;
	private static GuiNovedades PANEL_NOVEDADES;
	private static GuiReportes PANEL_REPORTES;
	
	private Usuario usuario;
	private JLabel cuenta;
	private JLabel busqueda;
	private JLabel documento;
	private JLabel usuarios;
	
	public static String LOGIN;
	
	private Vector<Consulta> novedadesUsuario;
	
	
	/**
	 * @param usuario
	 */
	public GuiAdministrador(Usuario usuario){
		
		super("::: Sistema de Biblioteca Digital :::");	
		setIconImage(new ImageIcon("recursos/bd.gif").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;
		LOGIN = usuario.getLogin();
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
		panelConsultarUsuarios = new GuiConsultarUsuarios();
		PANEL_CONSULTA_BASICA = new GuiConsultaBasica();
		PANEL_CONSULTA_AVANZADA = new GuiConsultaAvanzada();
		PANEL_NOVEDADES =  new GuiNovedades();
		PANEL_REPORTES = new GuiReportes();
		GuiConsultaBasica.TIPO_USUARIO = 1;
		GuiConsultaAvanzada.TIPO_USUARIO = 1;
		GuiNovedades.TIPO_USUARIO = 1;
		
		panelCatalogar = new GuiCatalogar(usuario.getLogin());
		panelModificacion = new GuiRegistroModificar(this.usuario,1);
	
		
		CONTENEDOR = getContentPane();
		CONTENEDOR.setLayout(new BorderLayout(20,20));
		((JComponent) CONTENEDOR).setBorder(borde);
		
		// Se instancian todos los elementos de la barra del menu.
		archivo = new JMenu("Archivo");
		archivo.setMnemonic('A');

		acercaDe = new JMenu("Acerca de ");
		acercaDe.setMnemonic('c');

		salir = new JMenuItem("Salir");
		salir.setMnemonic('S');
		salir.addActionListener(manejador);
		salir.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));

		informacion = new JMenuItem("Ayuda");
		informacion.setMnemonic('y');
		informacion.addActionListener(manejador);

		archivo.add(salir);
		acercaDe.add(informacion);

		barra = new JMenuBar();
		barra.add(archivo);
		barra.add(acercaDe);
		setJMenuBar(barra);

		// Se instancian todos los elementos que pertenecen al panel del
		// administrador
		panelOpcionesGenerales = new JPanel(new GridBagLayout());

		volver = new Button("Inicio");
		volver.setIcon(new ImageIcon("recursos/iconos/home.png"));
		volver.addActionListener(manejador);
		modificarUsuario = new Button("Modificar Usuarios");
		modificarUsuario.setIcon(new ImageIcon("recursos/iconos/edit_user.png"));
		modificarUsuario.addActionListener(manejador);	
		modificarMiUsuario = new Button("Modificar Datos");
		modificarMiUsuario.setIcon(new ImageIcon("recursos/iconos/my_account.png"));
		modificarMiUsuario.addActionListener(manejador);
		consultaAvanzada = new Button("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		catalogar = new Button("Catalogar");
		catalogar.setIcon(new ImageIcon("recursos/iconos/add_document.png"));
		catalogar.addActionListener(manejador);
		reportes = new Button("Reportes");
		reportes.addActionListener(manejador);
		novedades = new Button("Novedades");
		novedades.addActionListener(manejador);
		logout = new Button("Salir");
		logout.setIcon(new ImageIcon("recursos/iconos/logout.png"));
		logout.addActionListener(manejador);
		

		cuenta= new JLabel("Cuenta",JLabel.CENTER);
		busqueda= new JLabel("Busqueda",JLabel.CENTER);
		documento = new JLabel("Documento",JLabel.CENTER);	
		usuarios= new JLabel("Usuarios",JLabel.CENTER);
		// Se agregan los elementos al panel de opciones del administrador.
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
		panelOpcionesGenerales.add(usuarios, restricciones);
		restricciones.gridy++;
		panelOpcionesGenerales.add(modificarUsuario, restricciones);
		restricciones.gridy++;		
		panelOpcionesGenerales.add(documento, restricciones);
		restricciones.gridy++;		
		panelOpcionesGenerales.add(catalogar, restricciones);
		restricciones.gridy++;
		
		
		panelOpcionesGenerales.add(reportes, restricciones);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(cuenta, restricciones);
		restricciones.gridy++;
		
		panelOpcionesGenerales.add(modificarMiUsuario, restricciones);
		
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
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
	
		//setSize(800, 500); 
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setSize(screenSize);
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

			if (evento.getSource() == modificarUsuario)
			{
				
				
				if (ESTADO.getText().equals(estadoInicial)){
				
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
					
					
				}else if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{		
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(panelConsultarUsuarios, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultarUsuario);
					repaint();
				}

				
			}else if(evento.getSource() == volver)
			{
				
				if (ESTADO.getText().equals(estadoModificacion)) {
					CONTENEDOR.remove(panelModificacion);
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
					
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();	
				
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
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
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
					ESTADO.setText(estadoInicial);
					repaint();
				}
				
			}
			else if(evento.getSource() == consultaAvanzada)
			{
				
				if (ESTADO.getText().equals(estadoModificacion))
				{
				
					CONTENEDOR.remove(panelModificacion);
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
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(PANEL_CONSULTA_AVANZADA);
					ESTADO.setText(estadoConsultaAvanzada);
					repaint();
				}
								
			}
			else if(evento.getSource()==modificarMiUsuario)
			{
			
				if (ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(panelModificacion, BorderLayout.CENTER);
					ESTADO.setText(estadoModificacion);
					repaint();
				}
				
				
			}else if(evento.getSource() == catalogar)
			{
				if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(panelCatalogar);
					ESTADO.setText(estadoCatalogar);
					repaint();
				}
				
			}else if(evento.getSource() == novedades)
			{
				GuiResultadoConsulta.TIPO_CONSULTA = 3;
				if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();;
					
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
				}else if(ESTADO.getText().equals(estadoReporte)){
					CONTENEDOR.remove(PANEL_REPORTES);
					CONTENEDOR.add(PANEL_NOVEDADES);
					ESTADO.setText(estadoNovedades);
					repaint();
				}
				
			}else if(evento.getSource() == reportes){
				if(ESTADO.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoConsultarUsuario))
				{
					CONTENEDOR.remove(panelConsultarUsuarios);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();;
					
				}else if(ESTADO.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
					
				}else if(ESTADO.getText().equals(estadoModificacion))
				{
					CONTENEDOR.remove(panelModificacion);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
				}else if(ESTADO.getText().equals(estadoModificandoDoc))
				{
					CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
				}else if(ESTADO.getText().equals(estadoCatalogar))
				{
					CONTENEDOR.remove(panelCatalogar);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
				}else if(ESTADO.getText().equals(estadoNovedades))
				{
					CONTENEDOR.remove(PANEL_NOVEDADES);
					CONTENEDOR.add(PANEL_REPORTES);
					ESTADO.setText(estadoReporte);
					repaint();
				}
			}
			else if(evento.getSource() == logout)
			{
				GuiPrincipal gp = new GuiPrincipal();
				gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}else if(evento.getSource() == salir )
			{
				System.exit(0);
				
			}
		}
	}

	
	/**
	 * 
	 */
	public void cambiarPanelInicio()
	{
		
		CONTENEDOR.remove(panelModificacion);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText(estadoInicial);
		repaint();
		
	}
	/**
	 * 
	 */
	public static void cambiarPanelEditarDocumento()
	{
		
		CONTENEDOR.remove(PANEL_CONSULTA_BASICA);
		CONTENEDOR.add(PANEL_MODIFICAR_DOCUMENTO, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 */
	public static void cambiarPanelEditarDocumentoAvanzado()
	{
		
		CONTENEDOR.remove(PANEL_CONSULTA_AVANZADA);
		CONTENEDOR.add(PANEL_MODIFICAR_DOCUMENTO, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 */
	public static void cambiarPanelVista()
	{
		
		
		CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 */
	public static void cambiarPanelVistaAvanzado()
	{
		
		
		CONTENEDOR.remove(PANEL_MODIFICAR_DOCUMENTO);
		CONTENEDOR.add(PANEL_CONSULTA_AVANZADA, BorderLayout.CENTER);
		ESTADO.setText("ConsultaAvanzada");
		CONTENEDOR.repaint();
		
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
	
	/**
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
	 * @return
	 */
	public Vector<Consulta>getNovededadesUsuario()
	{
		return novedadesUsuario;			
		
	}
	/**
	 * 
	 */
	public static void cambiarNovedadesInicio() {
		CONTENEDOR.remove(PANEL_NOVEDADES);
		CONTENEDOR.add(PANEL_CONSULTA_BASICA, BorderLayout.CENTER);
		ESTADO.setText("Inicio");
		CONTENEDOR.repaint();
		
	}
	/**
	 * 
	 */
	public static void cambiarPanelEditarDocumentoNovedades()
	{
		
		CONTENEDOR.remove(PANEL_NOVEDADES);
		CONTENEDOR.add(PANEL_MODIFICAR_DOCUMENTO, BorderLayout.CENTER);
		ESTADO.setText("Modificando Documento");
		CONTENEDOR.repaint();
		
	}

}
