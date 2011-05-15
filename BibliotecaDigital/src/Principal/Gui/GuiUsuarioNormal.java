package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiConsultaBasica;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;
import Utilidades.Estilos;

public class GuiUsuarioNormal extends JFrame
{
	private static final long serialVersionUID = 1L;


	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private  String estadoInicial = "Inicio";
	private String estadoModificacion = "ModificandoUsuario";
	private String estadoConsultaAvanzada = "ConsultaAvanzada";		

	// Opciones basicas para un usuario normal
	private JPanel panelOpcionesGenerales;		
	private JButton volver; //incio
	private JButton modificarUsuario;	
	private JButton consultaAvanzada;
	private JButton logout;
	
	private  JLabel estado;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private  Container contenedor;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;
	private Usuario usuario;

	// Otor paneles a usar
	private static GuiRegistroModificar panelModificacion;
	private static GuiConsultaBasica panelConsultaBasica;
	
	
	public GuiUsuarioNormal(Usuario usuario)
	{
		super("::: Sistema de Biblioteca Digital :::");	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.usuario = usuario;

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
		panelOpcionesGenerales = new JPanel(new GridLayout(8,1,10,20));

		volver = new JButton("Inicio");
		volver.addActionListener(manejador);
		modificarUsuario = new JButton("Modificar Datos");
		modificarUsuario.addActionListener(manejador);			
		consultaAvanzada = new JButton("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		logout = new JButton("Salir");
		logout.addActionListener(manejador);
					

		// Se agregan los elementos al panel de opciones del usuario normal.
		panelOpcionesGenerales.add(volver);
		panelOpcionesGenerales.add(modificarUsuario);
		panelOpcionesGenerales.add(consultaAvanzada);
		panelOpcionesGenerales.add(logout);
		
		panelOpcionesGenerales.setBackground(new Color(250, 230 , 250));
		JPanel panelconOpciones2= new JPanel(); //evita que los botones crescan si la ventana es redimensionada
		panelconOpciones2.add(panelOpcionesGenerales);
		
		estado = new JLabel(estadoInicial);

		contenedor.add(panelconOpciones2, BorderLayout.WEST);
		contenedor.add(estado, BorderLayout.SOUTH);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		//contenedor.add(new JPanel(), BorderLayout.EAST);
		//contenedor.add(new JPanel(), BorderLayout.WEST);
	
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-700/2,(screenSize.height)/2-500/2);
		
		setSize(700, 500);
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
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
					
				}else if(estado.getText().equals(estadoConsultaAvanzada))
				{		
					
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				
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
					contenedor.remove(panelConsultaBasica);
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
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}else if(estado.getText().equals(estadoInicial))
				{
					
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
			}
			else if(evento.getSource() == logout)
			{

				new GuiPrincipal();
				dispose();
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
	
	/*public static void cambiarPanelInicial()
	{
		contenedor.remove(panelModificacion);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		estado.setText(estadoInicial);
	
		
	}*/






}
