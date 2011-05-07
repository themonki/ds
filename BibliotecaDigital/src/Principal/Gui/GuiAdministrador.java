package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import Documento.Gui.GuiCatalogarModificar;
import Usuarios.Gui.GuiConsultarUsuarios;
import Usuarios.Gui.GuiRegistroModificar;
import Usuarios.Logica.Usuario;

public class GuiAdministrador extends JFrame
{
	
	private static final long serialVersionUID = 1L;

	// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
	private String estadoInicial = "Inicio";
	private String estadoModificacion = "ModificandoUsuario";
	private String estadoConsultarUsuario ="ConsultarUsuario";
	private String estadoConsultaAvanzada = "ConsultaAvanzada";
	private String estadoCatalogar = "Catalogar";

	// Opciones basicas para un usuario
	private JPanel panelOpcionesGenerales;		
	private JButton volver;
	private JButton modificarUsuario;
	private JButton modificarMiUsuario;
	private JButton consultaAvanzada;
	private JButton catalogar;
	private JButton logout;

	
	private JLabel estado;

	// Clase interna que permite administrar todos los eventos que genera la
	// ventana y son escuchados.
	private Manejador manejador;

	// Elementos de la barra de menu
	private JMenu archivo;
	private JMenu acercaDe;
	private Container contenedor;
	private JMenuItem salir;
	private JMenuItem informacion;
	private JMenuBar barra;

	// otros paneles
	private GuiRegistroModificar panelModificacion;
	private GuiConsultarUsuarios panelConsultarUsuarios;
	private GuiConsultaBasica panelConsultaBasica;
	private GuiCatalogarModificar panelCatalogar;
	
	private Usuario usuario;
	
	
	public GuiAdministrador(Usuario usuario){
		
		super("::: Sistema de Biblioteca Digital :::");	
		this.usuario = usuario;
		manejador = new Manejador();	
		
		//Estilos.
		//-------------------------------fuentes letras-------------------------

		Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
		//Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		
		//-------------------------------Color letras----------------------------
		
		String tituloMuestra = "::Sistema Biblioteca Digital::";
		Color colorTitulo = new Color(0,50,0);
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.yellow), tituloMuestra);
		borde.setTitleColor(colorTitulo);
		borde.setTitleFont(fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		
		// se instancias paneles adicionales		
		panelConsultarUsuarios = new GuiConsultarUsuarios();
		panelConsultaBasica = new GuiConsultaBasica();
		panelCatalogar = new GuiCatalogarModificar(usuario.getLogin());
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
		panelOpcionesGenerales = new JPanel(new GridLayout(8,1,10,20));

		volver = new JButton("Inicio");
		volver.addActionListener(manejador);
		modificarUsuario = new JButton("Modificar Usuarios");
		modificarUsuario.addActionListener(manejador);	
		modificarMiUsuario = new JButton("Modificar Datos");
		modificarMiUsuario.addActionListener(manejador);
		consultaAvanzada = new JButton("Consulta Avanzada");
		consultaAvanzada.addActionListener(manejador);
		catalogar = new JButton("Catalogar");
		catalogar.addActionListener(manejador);
		logout = new JButton("Salir");
		logout.addActionListener(manejador);
		
		// Se agregan los elementos al panel de opciones del administrador.
		panelOpcionesGenerales.add(volver);
		panelOpcionesGenerales.add(modificarUsuario);
		panelOpcionesGenerales.add(modificarMiUsuario);
		panelOpcionesGenerales.add(consultaAvanzada);
		panelOpcionesGenerales.add(catalogar);
		panelOpcionesGenerales.add(logout);
		
		panelOpcionesGenerales.setBackground(new Color(250, 230,250));
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
				
				//panelConsultarUsuarios = new GuiConsultarUsuarios();
				
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
					
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
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
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
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
				}else if(estado.getText().equals(estadoConsultarUsuario))
				{
					contenedor.remove(panelConsultarUsuarios);
					contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelConsultaBasica);
					estado.setText(estadoConsultaAvanzada);
					repaint();
					
					JOptionPane.showMessageDialog(null,"Consulta Avanzada en Construccion");
				}
								
			}
			else if(evento.getSource()==modificarMiUsuario)
			{
				//panelModificacion = new GuiRegistroModificar(usuario,1);
			
				if (estado.getText().equals(estadoConsultaAvanzada))
				{
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
				
				}else if(estado.getText().equals(estadoCatalogar))
				{
					contenedor.remove(panelCatalogar);
					contenedor.add(panelModificacion, BorderLayout.CENTER);
					estado.setText(estadoModificacion);
					repaint();
				}
				
				
			}else if(evento.getSource() == catalogar)
			{
				if(estado.getText().equals(estadoConsultaAvanzada))
				{
					contenedor.remove(panelConsultaBasica);
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
				}
				
			}else if(evento.getSource() == logout)
			{
				new GuiPrincipal();
				dispose();
			}	
		}
	}



	/*public static void main(String args[])
	{

		try
		{	
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
		}
		catch (Exception e){e.printStackTrace();}
		Usuario usuario = new Usuario();
	
		GuiAdministrador a = new GuiAdministrador(usuario);
	
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/



}
