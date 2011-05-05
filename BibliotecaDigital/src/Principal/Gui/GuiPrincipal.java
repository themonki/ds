package Principal.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Usuarios.Gui.GuiRegistroModificar;

public class GuiPrincipal extends JFrame {
	

		private static final long serialVersionUID = 1L;

	

		// Panel donde se colocan las opciones del administrador que siempre
		// esta
		// visible
		private JPanel panelOpcionesGenerales;

		// Botones que muestran las operaciones que puede realizar el
		// administrador
		private JButton volver;
		private JButton crearUsuario;	
		private JButton ingresarSistema;
		
	
		
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
		
		private GuiRegistroModificar panelRegistro;
		
		public GuiPrincipal(){
			
			super("::: Sistema de Biblioteca Digital :::");	

			manejador = new Manejador();		

			contenedor = getContentPane();
			contenedor.setLayout(new BorderLayout());

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
			panelOpcionesGenerales = new JPanel(new GridLayout(3, 1, 5, 5));

			volver = new JButton("Inicio");
			volver.addActionListener(manejador);
			crearUsuario = new JButton("Registrarse");
			crearUsuario.addActionListener(manejador);			
			ingresarSistema = new JButton("Ingresar");
			ingresarSistema.addActionListener(manejador);
						

			// Se agregan los elementos al panel de opciones del administrador.
			panelOpcionesGenerales.add(volver);
			panelOpcionesGenerales.add(crearUsuario);
			panelOpcionesGenerales.add(ingresarSistema);
			
		

			// Elementos del panel de inicio que se muestra en el centro apenas
			// se
			// carga el programa.
			panelTitulo = new JPanel(new BorderLayout(1, 2));
			icono = new ImageIcon("recursos/LOGO.png");
			etiquetaImagen = new JLabel(icono);
			panelTitulo.add(etiquetaImagen);
			titulo = new JLabel("Biblioteca Digital Eisc");
			

			// Elementos del panel nuevo usuario.
			panelConsultaBasica = new JPanel(new GridLayout(2, 1, 5, 5));

			campoConsulta = new JTextField(20);
			panelBotonesConsulta = new JPanel(new GridLayout(1, 2, 5, 5));
			consultar = new JButton("Consular");
			consultar.addActionListener(manejador);
			limpiarCampoConsulta = new JButton("Limpiar Campo");
			limpiarCampoConsulta.addActionListener(manejador);
			
			panelBotonesConsulta.add(consultar);
			panelBotonesConsulta.add(limpiarCampoConsulta);
			
			panelConsultaBasica.add(campoConsulta);
		
			
			
			estado = new JLabel("Iniciado");

			contenedor.add(panelOpcionesGenerales, BorderLayout.WEST);
			contenedor.add(estado, BorderLayout.SOUTH);
			contenedor.add(panelTitulo, BorderLayout.NORTH);
			contenedor.add(panelConsultaBasica, BorderLayout.CENTER);

			
			
			setSize(600, 320);
			setVisible(true);

		}

		public class Manejador implements ActionListener{		
			@Override
			public void actionPerformed(ActionEvent evento) {

				if (evento.getSource() == crearUsuario) {
					if (estado.getText().equals("Iniciado")) {
						//contenedor.remove(panelConsultaBasica);
						//contenedor.add(GuiRegistroModificar, BorderLayout.CENTER);
						estado.setText("Registrando nuevo usuario");
						repaint();
					}

					
				}

				

				

				

			}

			


		}

	

		public static void main(String args[]) {

		

		}

	

}
