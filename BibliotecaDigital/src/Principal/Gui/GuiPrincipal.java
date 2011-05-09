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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import Consultas.Gui.GuiConsultaBasica;
import Usuarios.Gui.GuiAutenticar;
import Usuarios.Gui.GuiRegistroModificar;

public class GuiPrincipal extends JFrame
{
		private static final long serialVersionUID = 1L;
	
		// Estados para cada una de las acciones que se puede realiza sirven de memoria a la gui.
		private String estadoInicial = "Inicio";
		private String estadoRegistro = "RegistrandoUsuario";
		private static String estadoIngrensando = "Autentificando";
		private String estadoConsultaAvanzada = "consultaAvanzada";

		// Opciones basicas para un usuario
		private JPanel panelOpcionesGenerales;		
		private JButton volver;
		private JButton crearUsuario;	
		private JButton ingresarSistema;
		private JButton consultaAvanzada;
		
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

		// Paneles a usar		
		private static GuiRegistroModificar panelRegistro;
		private static GuiAutenticar panelAutentificar;
		private GuiConsultaBasica panelConsultaBasica;
		
		public GuiPrincipal()
		{
			
			super("::: Sistema de Biblioteca Digital :::");	

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
					.createLineBorder(Color.black), tituloMuestra);
			borde.setTitleColor(colorTitulo);
			borde.setTitleFont(fontTitulo);
			borde.setTitleJustification(TitledBorder.CENTER);
			
			// se instancias paneles adicionales
			panelRegistro = new GuiRegistroModificar();
			panelConsultaBasica = new GuiConsultaBasica();
			panelAutentificar = new GuiAutenticar(this);
			
			//se obtiene el contenedor de la gui principal
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

			// Se instancian todos los elementos que pertenecen al panel de
			//opciones
			panelOpcionesGenerales = new JPanel(new GridLayout(8,1,10,20));

			volver = new JButton("Inicio");
			volver.addActionListener(manejador);
			crearUsuario = new JButton("Registrarse");
			crearUsuario.addActionListener(manejador);			
			ingresarSistema = new JButton("Ingresar");
			ingresarSistema.addActionListener(manejador);
			consultaAvanzada = new JButton("Consulta Avanzada");
			consultaAvanzada.addActionListener(manejador);			
						

			
			//crearUsuario.setPreferredSize(new Dimension(10, 10));
			// Se agregan los elementos al panel de opciones del administrador.
			panelOpcionesGenerales.add(volver);
			panelOpcionesGenerales.add(crearUsuario);
			panelOpcionesGenerales.add(consultaAvanzada);
			panelOpcionesGenerales.add(ingresarSistema);			
			
			JPanel panelconOpciones2= new JPanel();
			panelconOpciones2.add(panelOpcionesGenerales);
			panelOpcionesGenerales.setBackground(new Color(250, 230,250));
		

			estado = new JLabel(estadoInicial);

			contenedor.add(panelconOpciones2, BorderLayout.WEST);
			contenedor.add(estado, BorderLayout.SOUTH);
			contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
			
		
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

				if (evento.getSource() == crearUsuario)
				{
					if (estado.getText().equals(estadoInicial))
					{
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelRegistro, BorderLayout.CENTER);
						estado.setText(estadoRegistro);
						repaint();
					}else if(estado.getText().equals(estadoIngrensando))
					{
						
						contenedor.remove(panelAutentificar);
						contenedor.add(panelRegistro, BorderLayout.CENTER);
						estado.setText(estadoRegistro);
						repaint();
						
					}else if(estado.getText().equals(estadoConsultaAvanzada))
					{
						
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelRegistro, BorderLayout.CENTER);
						estado.setText(estadoRegistro);
						repaint();
						
					}
				}else if(evento.getSource() == volver)
				{
					
					if (estado.getText().equals(estadoRegistro)) {
						contenedor.remove(panelRegistro);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoInicial);
						repaint();
					}
					else if(estado.getText().equals(estadoIngrensando))
					{
						
						contenedor.remove(panelAutentificar);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoInicial);
						repaint();
						
					}else if(estado.getText().equals(estadoConsultaAvanzada))
					{
						
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoInicial);
						repaint();
						
					}
				}
				else if(evento.getSource() == ingresarSistema)
				{
					
					if (estado.getText().equals(estadoRegistro))
					{
						contenedor.remove(panelRegistro);
						contenedor.add(panelAutentificar, BorderLayout.CENTER);
						estado.setText(estadoIngrensando);
						repaint();
					}else if(estado.getText().equals(estadoInicial))
					{
						
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelAutentificar, BorderLayout.CENTER);
						estado.setText(estadoIngrensando);
						repaint();
						
					}else if(estado.getText().equals(estadoConsultaAvanzada))
					{
						
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelAutentificar, BorderLayout.CENTER);
						estado.setText(estadoIngrensando);
						repaint();
						
					}
				
				}else if(evento.getSource() == consultaAvanzada)
				{
					
					if (estado.getText().equals(estadoRegistro)) {
						contenedor.remove(panelRegistro);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoConsultaAvanzada);
						repaint();
						JOptionPane.showMessageDialog(null,"En construccion");
					}else if(estado.getText().equals(estadoInicial))
					{
						
						contenedor.remove(panelConsultaBasica);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoConsultaAvanzada);
						repaint();
						JOptionPane.showMessageDialog(null,"En construccion");
					}else if(estado.getText().equals(estadoIngrensando))
					{
						
						contenedor.remove(panelAutentificar);
						contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
						estado.setText(estadoConsultaAvanzada);
						repaint();
						JOptionPane.showMessageDialog(null,"En construccion");
						
					}
				}	
				if(evento.getSource() == salir )
				{
					System.exit(0);
					
				}
			}
		}

	

		public static void main(String args[]) {

			try
			{				
				NimRODTheme nt = new NimRODTheme();
				nt.setPrimary1( new Color(10,10,230));
				nt.setPrimary2( new Color(110,110,150));
				nt.setPrimary3( new Color(0,0,230));
				//nt.setPrimary(new Color(100,100,100));
				//nt.setSecondary(new Color(230, 220,250));
				nt.setSecondary1(new Color(0,0,100));
				nt.setSecondary2(new Color(0, 100,0));
				nt.setSecondary3(new Color(250,250,250));
				nt.setWhite(new Color(250, 230,250));
				
				
	
				NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
				NimRODLookAndFeel.setCurrentTheme( nt);
				UIManager.setLookAndFeel( NimRODLF);
				//UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
			}
			catch (Exception e){e.printStackTrace();}
		
			GuiPrincipal a = new GuiPrincipal();
			
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
		public static void cambiarPanelIngresar()
		{
			
			contenedor.remove(panelRegistro);
			contenedor.add(panelAutentificar, BorderLayout.CENTER);
			estado.setText(estadoIngrensando);
			
			
		}
		
		
		
	

}
