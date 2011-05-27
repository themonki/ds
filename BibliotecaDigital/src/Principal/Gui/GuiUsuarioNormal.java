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
import javax.swing.JOptionPane;
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
	
	private static  JLabel estado;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private static  Container contenedor;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;
	private Usuario usuario;
	public static String LOGIN;

	// Otor paneles a usar
	private static GuiRegistroModificar panelModificacion;
	private static GuiConsultaBasica panelConsultaBasica;
	private static GuiConsultaAvanzada panelConsultaAvanzada;
	private static GuiNovedades panelNovedades;
	
	private Vector<Consulta> novedadesUsuario;
	
	private JLabel cuenta;
	private JLabel busqueda;

	
	
	public GuiUsuarioNormal(Usuario usuario)
	{
		super("::: Sistema de Biblioteca Digital :::");	
		setIconImage(new ImageIcon("recursos/bd.gif").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;
		LOGIN = usuario.getLogin();
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
		panelModificacion = new GuiRegistroModificar(usuario,1);
		panelConsultaBasica = new GuiConsultaBasica();
		panelConsultaAvanzada = new GuiConsultaAvanzada();
		panelNovedades = new GuiNovedades();
		GuiConsultaBasica.TIPOUSUARIO = 3;		
		GuiConsultaAvanzada.TIPOUSUARIO = 3;	
		GuiNovedades.TIPOUSUARIO = 3;
		
		
		
		contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout(20,20));
		((JComponent) contenedor).setBorder(borde);
		
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
		
		estado = new JLabel(estadoInicial);

		contenedor.add(panelconOpciones2, BorderLayout.WEST);
		contenedor.add(estado, BorderLayout.SOUTH);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
	
		//setSize(800, 500);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setSize(screenSize);
		setVisible(true);

	}
	
	public class Manejador implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent evento)
		{

			if (evento.getSource() == modificarUsuario)
			{			
				if (estado.getText().equals(estadoInicial))
				{			
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
					
				}else if(estado.getText().equals(estadoConsultaAvanzada))
				{	
					GuiConsultaAvanzada.restaurarTodo();					
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				
				}else if(estado.getText().equals(estadoNovedades))
				{
					
					contenedor.remove(panelNovedades);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}

			}else if(evento.getSource() == volver)
			{
				
				if (estado.getText().equals(estadoModificacion))
				{
					contenedor.remove(panelModificacion);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();
				}
				else if(estado.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();	
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();
					
				}
				else if(estado.getText().equals(estadoNovedades))
				{
					
					contenedor.remove(panelNovedades);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
				
			}
			else if(evento.getSource() == consultaAvanzada)
			{
				
				if (estado.getText().equals(estadoModificacion))
				{
				
					contenedor.remove(panelModificacion);
					contenedor.add(panelConsultaAvanzada, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(estado.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultaAvanzada, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(estado.getText().equals(estadoNovedades))
				{
					
					contenedor.remove(panelNovedades);
					contenedor.add(panelConsultaAvanzada, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
			}
			else if(evento.getSource() == novedades)
			{
				GuiResultadoConsulta.TIPOCONSULTA = 3;
				
				if (estado.getText().equals(estadoModificacion))
				{
				
					contenedor.remove(panelModificacion);
					contenedor.add(panelNovedades, BorderLayout.CENTER);
					estado.setText(estadoNovedades);
					repaint();
					
				}else if(estado.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelNovedades, BorderLayout.CENTER);
					estado.setText(estadoNovedades);
					repaint();					
				}else if(estado.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelNovedades, BorderLayout.CENTER);
					estado.setText(estadoNovedades);
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
				
			}
		}
	}
	
	public void cambiarPanelInicio()
	{
		contenedor.remove(panelModificacion);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText(estadoInicial);
		repaint();
		
		
	}
	public static void cambiarAvanzadaInicio()
	{
		
		
		contenedor.remove(panelConsultaAvanzada);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText("Inicio");
		contenedor.repaint();
		
	}
	public static void cambiarNovedadesInicio()
	{
		
		
		contenedor.remove(panelNovedades);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText("Inicio");
		contenedor.repaint();
		
	}
	
	public void setNovededadesUsuario(Vector<Consulta> novedades)
	{
	
		
		GuiNovedades.resultadoConsulta = new GuiResultadoConsulta(novedades, 10);
		GuiNovedades.panel.add(GuiNovedades.resultadoConsulta);
		
		GuiResultadoConsulta.TIPOCONSULTA = 3;
		this.novedades.setText("Novedades("+novedades.size()+")");
		panelNovedades.updateUI();
		novedadesUsuario = novedades;	
		
	}
	public Vector<Consulta>getNovededadesUsuario()
	{
		return novedadesUsuario;			
		
	}
}
