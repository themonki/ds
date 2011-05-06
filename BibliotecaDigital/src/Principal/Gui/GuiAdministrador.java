package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


import Usuarios.Gui.GuiConsultarUsuarios;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;

public class GuiAdministrador extends JFrame {
	

	private static final long serialVersionUID = 1L;


	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private String estadoInicial = "Inicio";
	private String estadoModificacion = "ModificandoUsuario";
	private String estadoConsultarUsuario ="ConsultarUsuario";
	private String estadoConsultaAvanzada = "ConsultaAvanzada";		

	// Opciones basicas para un usuario
	private JPanel panelOpcionesGenerales;		
	private JButton volver;
	private JButton modificarUsuario;	
	private JButton consultaAvanzada;
	private JButton modificarMiUsuario;
	private JButton logout;

	
	private JLabel estado;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;


	// Panel donde se pone la imagen inicial
	private JPanel panelTitulo;

	// Imagen que se muestra en la pantalla inicial
	private JLabel etiquetaImagen;
	private ImageIcon icono;
	private JLabel titulo;
	
	
	
	private JPanel panelConsultaBasica;
	private JLabel etiquetaConsulta;
	private JTextField campoConsulta;
	private JPanel panelBotonesConsulta;
	private JButton consultar;
	private JButton limpiarCampoConsulta;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private Container contenedor;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;

	// Elementos del panel consulta basica
	
	private GuiRegistroModificar panelModificacion;
	private GuiConsultarUsuarios panelConsultarUsuarios;
	
	private Usuario usuario;
	
	
	public GuiAdministrador(Usuario usuario){
		
		super("::: Sistema de Biblioteca Digital :::");	
		this.usuario = usuario;
		manejador = new Manejador();	
		
		//Estilos.
		//-------------------------------fuentes letras-------------------------

		Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
		Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		
		//-------------------------------Color letras----------------------------
		
		String tituloMuestra = "::Sistema Biblioteca Digital::";
		Color colorTitulo = new Color(0,50,0);
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.yellow), tituloMuestra);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		
		
					
		
		
		
		panelConsultarUsuarios = new GuiConsultarUsuarios(); 
		//panelModificacion = new GuiRegistroModificar(this.usuario,2);
	
		
		
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
		// administrador
		panelOpcionesGenerales = new JPanel(new FlowLayout());

		volver = new JButton("Inicio");
		volver.addActionListener(manejador);
		modificarUsuario = new JButton("Modificar Usuarios");
		modificarUsuario.addActionListener(manejador);	
		modificarMiUsuario = new JButton("Modificar Datos");
		modificarMiUsuario.addActionListener(manejador);
		consultaAvanzada = new JButton("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		logout = new JButton("Salir");
		logout.addActionListener(manejador);
		// Se agregan los elementos al panel de opciones del administrador.
		panelOpcionesGenerales.add(volver);
		panelOpcionesGenerales.add(modificarUsuario);
		panelOpcionesGenerales.add(modificarMiUsuario);
		panelOpcionesGenerales.add(consultaAvanzada);
		panelOpcionesGenerales.add(logout);
	

		// Elementos del panel de inicio que se muestra en el centro apenas
		// se
		// carga el programa.
		
	
		
		panelTitulo = new JPanel(new GridLayout(1, 1,5,5));
		//icono = new ImageIcon("recursos/LogoPequeno.png");
		
		//etiquetaImagen = new JLabel(icono,JLabel.CENTER);
		//panelTitulo.add(etiquetaImagen);
		titulo = new JLabel("Biblioteca Digital Eisc",JLabel.CENTER);
		//panelTitulo.add(titulo);
		

		//contenedorConsultaBasica = new JPanel(new FlowLayout());
		// Elementos del panel nuevo usuario.
		panelConsultaBasica = new JPanel(new FlowLayout(1,60,40));

		etiquetaConsulta = new JLabel("Consulta",JLabel.CENTER);
		etiquetaConsulta.setFont(fontLabels);
		campoConsulta = new JTextField(60);
		campoConsulta.setFont(fontLabels);
		panelBotonesConsulta = new JPanel(new GridLayout(1, 2, 5, 5));
		
		consultar = new JButton("Consular");
		consultar.addActionListener(manejador);
		limpiarCampoConsulta = new JButton("Limpiar Campo");
		limpiarCampoConsulta.addActionListener(manejador);
		
		panelBotonesConsulta.add(consultar);
		panelBotonesConsulta.add(limpiarCampoConsulta);
		panelConsultaBasica.add(etiquetaConsulta);
		panelConsultaBasica.add(campoConsulta);
		panelConsultaBasica.add(panelBotonesConsulta);
		
		
		
		estado = new JLabel(estadoInicial);

		contenedor.add(panelOpcionesGenerales, BorderLayout.NORTH);
		contenedor.add(estado, BorderLayout.SOUTH);
		contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
		contenedor.add(new JPanel(), BorderLayout.EAST);
		contenedor.add(new JPanel(), BorderLayout.WEST);
	

		
		
		setSize(600, 320);
		setVisible(true);

	}

	public class Manejador implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent evento) {

			if (evento.getSource() == modificarUsuario) {
				
			
				
				if (estado.getText().equals(estadoInicial)){
				
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
					
					
				}else if(estado.getText().equals(estadoConsultaAvanzada))
				{		
					
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultarUsuarios, BorderLayout.CENTER);
					estado.setText(estadoConsultarUsuario);
					repaint();
					
				}else if(estado.getText().equals(estadoModificacion))
				{
					contenedor.remove(panelModificacion);
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
					
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();
					
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoInicial);
					repaint();	
				}
				
			}
			else if(evento.getSource() == consultaAvanzada)
			{
				
				if (estado.getText().equals(estadoModificacion)) {
				
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
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
				
			}
			else if(evento.getSource()==modificarMiUsuario){
				panelModificacion = new GuiRegistroModificar(usuario,1);
				if (estado.getText().equals(estadoConsultaAvanzada)) {
					contenedor.remove(panelConsultaBasica);
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
					contenedor.remove(panelConsultaBasica);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}
				
				
			}else if(evento.getSource() == logout)
			{
				new GuiPrincipal();
				dispose();
			}	

			

		}

		


	}



	public static void main(String args[]) {

		try
		{	
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
		}
		catch (Exception e){e.printStackTrace();}
		Usuario usuario = new Usuario();
	
		GuiAdministrador a = new GuiAdministrador(usuario);
	
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}



}
