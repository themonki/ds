package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiConsultaBasica;
import Usuarios.Gui.GuiAutenticar;
import Usuarios.Gui.GuiRegistroModificar;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

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
		private Button volver;
		private Button crearUsuario;	
		private Button ingresarSistema;
		private Button consultaAvanzada;
		
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
			setIconImage(new ImageIcon("recursos/bd.png").getImage());
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
						
			
			
			//crearUsuario.setPreferredSize(new Dimension(10, 10));
			// Se agregan los elementos al panel de opciones del administrador.
			GridBagConstraints restricciones = new GridBagConstraints();
			restricciones.gridy=1;
			restricciones.fill = GridBagConstraints.HORIZONTAL;
			restricciones.insets= new Insets(0, 0, 20, 0);
			panelOpcionesGenerales.add(new JLabel(new ImageIcon("recursos/LOGO3D.png")),restricciones);
			restricciones.insets= new Insets(0, 0, 0, 0);
			restricciones.gridy++;
			panelOpcionesGenerales.add(volver, restricciones);
			restricciones.gridy++;
			panelOpcionesGenerales.add(crearUsuario, restricciones);
			restricciones.gridy++;
			panelOpcionesGenerales.add(consultaAvanzada, restricciones);
			restricciones.gridy++;
			panelOpcionesGenerales.add(ingresarSistema, restricciones);			
			
		
			
			
			JPanel panelconOpciones2= new JPanel();
			panelconOpciones2.add(panelOpcionesGenerales);
			
			
		

			estado = new JLabel(estadoInicial);

			contenedor.add(panelconOpciones2, BorderLayout.WEST);
			contenedor.add(estado, BorderLayout.SOUTH);
			contenedor.add(panelConsultaBasica, BorderLayout.CENTER);
			
			setSize(785, 500);
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
				NimRODTheme nt = new NimRODTheme("recursos/NimRODThemeFile2.theme");
				NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
				NimRODLookAndFeel.setCurrentTheme(nt);
				UIManager.setLookAndFeel( NimRODLF);
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
