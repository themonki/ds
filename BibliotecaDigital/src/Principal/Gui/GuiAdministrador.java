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
import Documento.Gui.GuiCatalogar;
import Documento.Gui.GuiModificarDoc;
import Usuarios.Gui.GuiConsultarUsuarios;
import Usuarios.Gui.GuiNovedades;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;
import Utilidades.Button;
import Utilidades.Estilos;

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
	private String estadoNovedades = "NovedadesUsuario";

	// Opciones basicas para un usuario
	private JPanel panelOpcionesGenerales;		
	private Button volver;
	private Button modificarUsuario;
	private Button modificarMiUsuario;
	private Button consultaAvanzada;
	private Button catalogar;
	private Button logout;
	private Button novedades;

	
	private static JLabel estado;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private static Container contenedor;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;

	// otros paneles
	private GuiRegistroModificar panelModificacion;
	private GuiConsultarUsuarios panelConsultarUsuarios;
	private static GuiConsultaBasica panelConsultaBasica;
	private static GuiConsultaAvanzada panelConsultaAvanzada;
	public static GuiModificarDoc panelModificarDoc;
	private GuiCatalogar panelCatalogar;
	private static GuiNovedades panelNovedades;
	
	private Usuario usuario;
	private JLabel cuenta;
	private JLabel busqueda;
	private JLabel documento;
	private JLabel usuarios;
	
	public static String LOGIN;
	
	private Vector<Consulta> novedadesUsuario;
	
	
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
		panelConsultaBasica = new GuiConsultaBasica();
		panelConsultaAvanzada = new GuiConsultaAvanzada();
		panelNovedades =  new GuiNovedades();
		GuiConsultaBasica.TIPOUSUARIO = 1;
		GuiConsultaAvanzada.TIPOUSUARIO = 1;
		GuiNovedades.TIPOUSUARIO = 1;
		
		panelCatalogar = new GuiCatalogar(usuario.getLogin());
		panelModificacion = new GuiRegistroModificar(this.usuario,1);
	
		
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

		estado = new JLabel(estadoInicial);

		contenedor.add(panelconOpciones2, BorderLayout.WEST);
		contenedor.add(estado, BorderLayout.SOUTH);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
	
		setSize(800, 500); 
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		
		setVisible(true);

	}

	public class Manejador implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent evento)
		{

			if (evento.getSource() == modificarUsuario)
			{
				
				
				if (estado.getText().equals(estadoInicial)){
				
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
					
					
				}else if(estado.getText().equals(estadoConsultaAvanzada))
				{		
					GuiConsultaAvanzada.restaurarTodo();
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
					
				}else if(estado.getText().equals(estadoModificacion))
				{
					contenedor.remove(panelModificacion);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
					
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
				}else if(estado.getText().equals(estadoNovedades))
				{
					contenedor.remove(panelNovedades);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
				}

				
			}else if(evento.getSource() == volver)
			{
				
				if (estado.getText().equals(estadoModificacion)) {
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
					
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();	
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
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
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelConsultaAvanzada, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelConsultaAvanzada);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
					contenedor.add(panelConsultaAvanzada);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(estado.getText().equals(estadoNovedades))
				{
					contenedor.remove(panelNovedades);
					contenedor.add(panelConsultaAvanzada);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					//JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
								
			}
			else if(evento.getSource()==modificarMiUsuario)
			{
			
				if (estado.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
					
				}else if(estado.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}else if(estado.getText().equals(estadoNovedades))
				{
					contenedor.remove(panelNovedades);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}
				
				
			}else if(evento.getSource() == catalogar)
			{
				if(estado.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
					
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
					
				}else if(estado.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
					
				}else if(estado.getText().equals(estadoModificacion))
				{
					contenedor.remove(panelModificacion);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
				}else if(estado.getText().equals(estadoNovedades))
				{
					contenedor.remove(panelNovedades);
					contenedor.add(panelCatalogar);
					estado.setText(estadoCatalogar);
					repaint();
				}
				
			}else if(evento.getSource() == novedades)
			{
				GuiResultadoConsulta.TIPOCONSULTA = 3;
				if(estado.getText().equals(estadoConsultaAvanzada))
				{
					GuiConsultaAvanzada.restaurarTodo();
					contenedor.remove(panelConsultaAvanzada);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
					repaint();
					
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
					repaint();;
					
				}else if(estado.getText().equals(estadoInicial))
				{
					GuiConsultaBasica.restaurarTodo();
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
					repaint();
					
				}else if(estado.getText().equals(estadoModificacion))
				{
					contenedor.remove(panelModificacion);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
					repaint();
				}else if(estado.getText().equals(estadoModificandoDoc))
				{
					contenedor.remove(panelModificarDoc);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
					repaint();
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelNovedades);
					estado.setText(estadoNovedades);
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

	
	public void cambiarPanelInicio()
	{
		
		contenedor.remove(panelModificacion);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText(estadoInicial);
		repaint();
		
	}public static void cambiarPanelEditarDocumento()
	{
		
		contenedor.remove(panelConsultaBasica);
		contenedor.add(panelModificarDoc, BorderLayout.CENTER);
		estado.setText("Modificando Documento");
		contenedor.repaint();
		
	}
	public static void cambiarPanelEditarDocumentoAvanzado()
	{
		
		contenedor.remove(panelConsultaAvanzada);
		contenedor.add(panelModificarDoc, BorderLayout.CENTER);
		estado.setText("Modificando Documento");
		contenedor.repaint();
		
	}
	public static void cambiarPanelVista()
	{
		
		
		contenedor.remove(panelModificarDoc);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText("Inicio");
		contenedor.repaint();
		
	}
	public static void cambiarPanelVistaAvanzado()
	{
		
		
		contenedor.remove(panelModificarDoc);
		contenedor.add(panelConsultaAvanzada, BorderLayout.CENTER);
		estado.setText("ConsultaAvanzada");
		contenedor.repaint();
		
	}
	public static void cambiarAvanzadaInicio()
	{
		
		
		contenedor.remove(panelConsultaAvanzada);
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
	public static void cambiarNovedadesInicio() {
		contenedor.remove(panelNovedades);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText("Inicio");
		contenedor.repaint();
		
	}
	public static void cambiarPanelEditarDocumentoNovedades()
	{
		
		contenedor.remove(panelNovedades);
		contenedor.add(panelModificarDoc, BorderLayout.CENTER);
		estado.setText("Modificando Documento");
		contenedor.repaint();
		
	}

}
