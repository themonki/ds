package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Consultas.Controlador.ControladorConsulta;
import Consultas.Logica.Consulta;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import com.sun.org.apache.regexp.internal.RESyntaxException;

public class GuiConsultaAvanzada extends JScrollPane
{
	
	private JLabel palabraClave,area ,titulo,autor,idioma,fechaPublicacionAntes, fechaPublicacionDespues,formatoArchivo,etiquetaCantidadResultado;
	private static JTextField campoPalabraClave;
	private static JTextField campoTitulo;
	private static JTextField campoAutor;
	private static JTextField campoFechaPublicacionAntes;
	private static JTextField campoFechaPublicacionDespues;
	private JComboBox campoAreas, campoIdioma,campoFormArchivo;
	private JRadioButton cualquieraTitulo, sinTitulo, exactaTitulo, cualquieraAutor, sinAutor, exactaAutor,
	cualquieraPalabra, sinPalabra, exactaPalabra;
	private ButtonGroup grupoTitulo, grupoAutor, grupoPalabra;
	private JButton botonConsultaAvanzada;
	private Manejador manejador;
	
	public static int TIPOUSUARIO;
	
	private JComboBox campoCantidadResultados;
	private String cantidades[] = {"5","10","15","20", "25"};
	public static GuiResultadoConsulta resultadoConsulta;
	public static GuiVistaDocumento vistaDocumento;
	
	private static JPanel principal;
	
	private Vector<String> valoresConsulta;
	private Vector<String> atributosConsulta;
	
	//vector y arrays para inicializar los combo box
	String idiomaArray[] = {"Todos","Ingles", "Español","Francés", "Aleman", "Portuges"};
	String formatoArchivoArray[] = {"Todos", "jpg", "pdf", "doc", "odt", "otro"};
	Vector<String> areas; 
	
	public static JPanel  panel;

	
	public GuiConsultaAvanzada()
	{
		manejador = new Manejador();
		initComponents();
		
		principal = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionesEtiqueta = configurar(0, 0, new Insets(2,2,2,0));
		GridBagConstraints restriccionesBoton = configurar(0, 0, new Insets(2,0,2,0));
		
		JPanel panelCantidadResultados = new JPanel(new FlowLayout());
		panelCantidadResultados.add(etiquetaCantidadResultado);
		panelCantidadResultados.add(campoCantidadResultados);

		
		principal.add(titulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoTitulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		principal.add(sinTitulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		principal.add(cualquieraTitulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=3;
		principal.add(exactaTitulo, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.gridwidth=1;
		principal.add(autor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoAutor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		principal.add(sinAutor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		principal.add(cualquieraAutor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=3;
		principal.add(exactaAutor, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		principal.add(palabraClave, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoPalabraClave, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		principal.add(sinPalabra, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		principal.add(cualquieraPalabra, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=3;
		principal.add(exactaPalabra, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.gridwidth=1;
		principal.add(area, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoAreas, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.weightx=1.0;
		principal.add(idioma, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoIdioma, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		principal.add(formatoArchivo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoFormArchivo, restriccionesEtiqueta);	
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.gridwidth=1;
		principal.add(fechaPublicacionAntes, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		restriccionesEtiqueta.gridwidth=2;
		principal.add(campoFechaPublicacionAntes, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.gridwidth=1;
		principal.add(fechaPublicacionDespues, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=2;
		restriccionesEtiqueta.gridwidth=2;
		principal.add(campoFechaPublicacionDespues, restriccionesEtiqueta);
		
		
		restriccionesBoton.gridy=restriccionesEtiqueta.gridy+1;
		restriccionesBoton.insets= new Insets(4,2,2,2);
		restriccionesBoton.gridwidth= 4;
		restriccionesBoton.anchor = GridBagConstraints.CENTER;
		principal.add(botonConsultaAvanzada, restriccionesBoton);
		//principal.add();
		panel = new JPanel(new BorderLayout());
		panel.add(principal, BorderLayout.NORTH);
		//setLayout(new BorderLayout());
		this.setViewportView(panel);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void initComponents()
	{
		inicializarLabels();
		inicializarTextFields();
		inicializarComboBox();
		inicializarRadioButtons();
		
		botonConsultaAvanzada = new Button("Buscar");
	    botonConsultaAvanzada.setIcon(new ImageIcon("recursos/iconos/search.png"));
		botonConsultaAvanzada.setHorizontalTextPosition(SwingConstants.LEFT);
		botonConsultaAvanzada.addActionListener(manejador);
		
	}
	
	private void inicializarLabels() 
	{
		palabraClave = inicializarLabel("Palabra Clave: ");
		area = inicializarLabel("Área: ");
		titulo = inicializarLabel("Titulo: ");
		autor = inicializarLabel("Autor: ");
		idioma = inicializarLabel("Idioma: ");
		fechaPublicacionAntes = inicializarLabel("Fecha de publicación antes del año: ");
		fechaPublicacionDespues = inicializarLabel("Fecha de publicación después del año: ");
		formatoArchivo = inicializarLabel("Formato de archivo: ");
		
		etiquetaCantidadResultado= inicializarLabel("Resultados por pagina: ");
		
	}
	
	private JLabel inicializarLabel(String titulo)
	{
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setFont(Estilos.fontLabels);
		label.setForeground(Estilos.colorLabels);
		return label;
	}
	
	private void inicializarTextFields() 
	{
		campoPalabraClave = new JTextField(30); 
		campoTitulo = new JTextField(30);
		campoAutor = new JTextField(30);
		campoFechaPublicacionAntes = new JTextField(10);
		campoFechaPublicacionDespues = new JTextField(10);

	}

	private void inicializarComboBox() 
	{
		areas = new Vector<String>();
		areas.addElement("Todas");
		
		ControladorAreaConocimiento controladorAreas = new ControladorAreaConocimiento();
		Vector<AreaConocimiento> areasConocimiento;
		areasConocimiento =  controladorAreas.obtenerAreas();
		
		for(int i=1; i< areasConocimiento.size(); i++)
		{
			areas.addElement(areasConocimiento.elementAt(i).getNombre());
		}

		campoAreas = new JComboBox(areas);
		campoAreas.setSelectedIndex(0);
		campoAreas.setMaximumRowCount(5);
		campoAreas.addActionListener(manejador);
		campoIdioma = new JComboBox(idiomaArray);
		campoIdioma.setSelectedIndex(0);
		campoIdioma.addActionListener(manejador);
		campoFormArchivo = new JComboBox(formatoArchivoArray);
		campoFormArchivo.setSelectedIndex(0);
		campoFormArchivo.addActionListener(manejador);
		//campoParametrosTitulo = new JComboBox(parametrosArray);
		//campoParametrosTitulo.setSelectedIndex(0);
		//campoParametrosAutor = new JComboBox(parametrosArray);
		//campoParametrosAutor.setSelectedIndex(0);
		//campoParametrosPalabraClave = new JComboBox(parametrosArray);
		//campoParametrosPalabraClave.setSelectedIndex(0);
		
		campoCantidadResultados = new JComboBox(cantidades);
		
	}
	
	private void inicializarRadioButtons()
	{
		cualquieraTitulo = new JRadioButton("Con estas palabras", true);
		cualquieraTitulo.addItemListener(manejador);
		sinTitulo = new JRadioButton("Sin estas palabras");
		sinTitulo.addItemListener(manejador);
		exactaTitulo = new JRadioButton("Concidencia exacta");
		exactaTitulo.addItemListener(manejador);
		
		cualquieraAutor = new JRadioButton("Con estas palabras", true);
		cualquieraAutor.addItemListener(manejador);
		sinAutor = new JRadioButton("Sin estas palabras");
		sinAutor.addItemListener(manejador);
		exactaAutor = new JRadioButton("Concidencia exacta");
		exactaAutor.addItemListener(manejador);
		
		cualquieraPalabra = new JRadioButton("Con estas palabras", true);
		cualquieraPalabra.addItemListener(manejador);
		sinPalabra = new JRadioButton("Sin estas palabras");
		sinPalabra.addItemListener(manejador);
		exactaPalabra = new JRadioButton("Concidencia exacta");
		exactaPalabra.addItemListener(manejador);
		
		grupoTitulo = new ButtonGroup();
		grupoTitulo.add(cualquieraTitulo);
		grupoTitulo.add(sinTitulo);
		grupoTitulo.add(exactaTitulo);
		
		grupoAutor = new ButtonGroup();
		grupoAutor.add(cualquieraAutor);
		grupoAutor.add(sinAutor);
		grupoAutor.add(exactaAutor);
		
		grupoPalabra = new ButtonGroup();
		grupoPalabra.add(cualquieraPalabra);
		grupoPalabra.add(sinPalabra);
		grupoPalabra.add(exactaPalabra);
	}
	
	private GridBagConstraints configurar(int x, int y, Insets insets)
	{
		
		GridBagConstraints configuracion = new GridBagConstraints();
		configuracion.gridx=x;
		configuracion.gridy=y;
		configuracion.insets= insets;
		configuracion.anchor= GridBagConstraints.WEST;
		return configuracion;
	}

	private class Manejador implements ActionListener, ItemListener
	{
		String formatoArchivo = "Todos", area = "Todas", idioma = "Todos", titulo, autor, palabraClave, fechaPublicacionAntes, fechaPublicacionDespues;
		int opcionTitulo = 2, opcionPalabra = 2, opcionAutor = 2;
				
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Object fuente = e.getSource();
			
			if(fuente == botonConsultaAvanzada)
			{
				atributosConsulta = new Vector<String>();
				valoresConsulta = new Vector<String>();
				
				palabraClave = campoPalabraClave.getText();
				titulo = campoTitulo.getText();
				autor = campoAutor.getText();
				fechaPublicacionAntes = campoFechaPublicacionAntes.getText();
				fechaPublicacionDespues = campoFechaPublicacionDespues.getText();
				
				if(!palabraClave.equals(""))
				{
					atributosConsulta.add("palabra");
					valoresConsulta.add(palabraClave);
				}
				if(!autor.equals(""))
				{
					atributosConsulta.add("autor");
					valoresConsulta.add(autor);
				}
				if(!titulo.equals(""))
				{
					atributosConsulta.add("titulo");
					valoresConsulta.add(titulo);
				}
				if(!fechaPublicacionAntes.equals(""))
				{
					atributosConsulta.add("fecha_antes");
					valoresConsulta.add(fechaPublicacionAntes);
				}
				if(!fechaPublicacionDespues.equals(""))
				{
					atributosConsulta.add("fecha_despues");
					valoresConsulta.add(fechaPublicacionDespues);
				}
				
				atributosConsulta.add("formato");
				valoresConsulta.add(formatoArchivo);
				atributosConsulta.add("idioma");
				valoresConsulta.add(idioma);
				atributosConsulta.add("area");
				valoresConsulta.add(area);
				
				ControladorConsulta controlador = new ControladorConsulta();
				Vector<Consulta> consulta = controlador.consultaAvanzada(atributosConsulta, valoresConsulta, opcionTitulo, opcionPalabra, opcionAutor);
			
				System.out.println(consulta);
				GuiResultadoConsulta.TIPOCONSULTA = 2;
				
				
				
				if(resultadoConsulta!=null){
					panel.remove(resultadoConsulta);
					
					
				}else{
					resultadoConsulta=null;
					}
				int cantidad = Integer.parseInt((String) campoCantidadResultados.getSelectedItem());
				resultadoConsulta = new GuiResultadoConsulta(consulta,cantidad);
				
				
				JOptionPane.showMessageDialog(null, ""+consulta.size());
				panel.add(resultadoConsulta, BorderLayout.SOUTH);
				panel.updateUI();
				if(consulta.size() <=0){
					
					JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
					
				}	
				System.out.println(GuiConsultaBasica.TIPOUSUARIO);
				
				
				
				
			}else if(fuente == campoAreas)
			{
				area = (String)campoAreas.getSelectedItem();
				System.out.println("seleccionado area " + area);
				
			}else if(fuente == campoIdioma)
			{
				idioma = (String)campoIdioma.getSelectedItem();
				
			}else if(fuente == campoFormArchivo)
			{
				formatoArchivo = (String)campoFormArchivo.getSelectedItem();
			}
			
		}
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Object fuente = e.getSource();
			
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				if(fuente == cualquieraTitulo)
				{
					opcionTitulo = 2;
					
				}else if(fuente == exactaTitulo)
				{
					opcionTitulo = 3;
				}else if(fuente == sinTitulo)
				{
					opcionTitulo = 1;
				}else if(fuente == cualquieraAutor)
				{
					opcionAutor = 2;
				}else if(fuente == exactaAutor)
				{
					opcionAutor = 3;
				}else if(fuente == sinAutor)
				{
					opcionAutor = 1;
				}else if(fuente == cualquieraPalabra)
				{
					opcionPalabra = 2;
				}else if(fuente == exactaPalabra)
				{
					opcionPalabra = 3;
				}else if(fuente == sinPalabra)
				{
					opcionPalabra = 1;
				}
			}
		}
		
	}
	
	public static void main(String args[]) 
	{

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
	
		GuiConsultaAvanzada aD = new GuiConsultaAvanzada();
		JFrame a = new JFrame();
		a.add(aD);
		a.setVisible(true);
		a.setSize(550,360);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	
	
	public static void ponerDescripcion()
	{
		panel.remove(resultadoConsulta);
		panel.remove(principal);
		panel.add(vistaDocumento, BorderLayout.CENTER);
		panel.updateUI();
	}
	public static void restaurar()
	{
		panel.remove(vistaDocumento);
		//campoConsulta.setText("");
		panel.add(principal, BorderLayout.NORTH);
		panel.add(resultadoConsulta, BorderLayout.SOUTH );
		
		//resultadoConsulta = new GuiResultadoConsulta();
		panel.remove(vistaDocumento);
		panel.updateUI();
	}
	public static void restaurarTodo()
	{
		
		panel.remove(vistaDocumento);
		campoPalabraClave.setText("");
		campoTitulo.setText("");
		campoAutor.setText("");
		campoFechaPublicacionDespues.setText("");
		campoFechaPublicacionAntes.setText("");
		panel.add(principal, BorderLayout.NORTH);		
		resultadoConsulta = new GuiResultadoConsulta();
		panel.add(resultadoConsulta, BorderLayout.SOUTH );
		panel.remove(vistaDocumento);
		panel.updateUI();
	}
	
	
	
	
	
	
	
	
}
