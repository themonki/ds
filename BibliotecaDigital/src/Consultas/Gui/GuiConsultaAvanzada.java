package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Consultas.Controlador.ControladorConsulta;
import Consultas.Logica.Consulta;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiConsultaAvanzada extends JScrollPane
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel palabraClave,area ,titulo,autor,idioma,fechaPublicacionAntes, fechaPublicacionDespues,formatoArchivo,etiquetaCantidadResultado;
	private static JTextField CAMPO_PALABRA_CLAVE;
	private static JTextField CAMPO_TITULO;
	private static JTextField CAMPO_AUTOR;
	private static JTextField CAMPO_FECHA_PUBLICACION_ANTES;
	private static JTextField CAMPO_FECHA_PUBLICACION_DESPUES;
	private JComboBox campoAreas, campoIdioma,campoFormArchivo;
	private JRadioButton cualquieraTitulo, sinTitulo, exactaTitulo, cualquieraAutor, sinAutor, exactaAutor,
	cualquieraPalabra, sinPalabra, exactaPalabra;
	private ButtonGroup grupoTitulo, grupoAutor, grupoPalabra;
	private Button botonConsultaAvanzada;
	private Manejador manejador;
	private JPanel panelCantidadResultados;
	
	public static int TIPO_USUARIO;
	
	private JComboBox campoCantidadResultados;
	private String cantidades[] = {"5","10","15","20", "25"};
	public static GuiResultadoConsulta PANEL_RESULTADO_CONSULTA;
	public static GuiVistaDocumento PANEL_VISTA_DOCUMENTO;
	
	private static JPanel PANEL_AVANZADA;
	
	private Vector<String> valoresConsulta;
	private Vector<String> atributosConsulta;
	
	//vector y arrays para inicializar los combo box
	String idiomaArray[] = {"Todos","Ingles", "Español","Frances", "Aleman", "Portuges"};
	String formatoArchivoArray[] = {"Todos", "jpg", "pdf", "doc", "odt", "otro"};
	Vector<String> areas; 
	
	public static JPanel  PANEL_PRINCIPAL;

	
	public GuiConsultaAvanzada()
	{
		manejador = new Manejador();
		initComponents();
		JPanel panelTexto = new JPanel(new GridBagLayout());
		JPanel panelCombo = new JPanel(new GridBagLayout());
		JPanel panelContenedor = new JPanel(new BorderLayout());
		PANEL_AVANZADA = new JPanel(new FlowLayout());
		GridBagConstraints restriccionesEtiqueta = configurar(0, 0, new Insets(4,10,2,2));
		GridBagConstraints restriccionesEtiqueta2 = configurar(0, 0, new Insets(4,10,2,2));
		GridBagConstraints restriccionesBoton = configurar(0, 0, new Insets(2,0,2,0));
		
		panelCantidadResultados = new JPanel(new FlowLayout());
		panelCantidadResultados.add(etiquetaCantidadResultado);
		panelCantidadResultados.add(campoCantidadResultados);

		panelTexto.add(titulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		restriccionesEtiqueta.weightx=1;
		int pax = restriccionesEtiqueta.ipadx;
		restriccionesEtiqueta.ipadx=120;
		panelTexto.add(CAMPO_TITULO, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.ipadx=pax;
		JPanel opcionTitulo = new JPanel(new GridLayout(1, 3, 5, 2));
		opcionTitulo.add(sinTitulo);
		opcionTitulo.add(cualquieraTitulo);
		opcionTitulo.add(exactaTitulo);
		panelTexto.add(opcionTitulo, restriccionesEtiqueta);

		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.weightx=0.0;
		panelTexto.add(autor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		restriccionesEtiqueta.weightx=1.0;
		restriccionesEtiqueta.ipadx=120;
		panelTexto.add(CAMPO_AUTOR, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.ipadx=pax;
		
		JPanel opcionAutor = new JPanel(new GridLayout(1,3,5,2));
		opcionAutor.add(sinAutor);
		opcionAutor.add(cualquieraAutor);
		opcionAutor.add(exactaAutor);
		panelTexto.add(opcionAutor, restriccionesEtiqueta);
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		restriccionesEtiqueta.weightx=0.0;
		panelTexto.add(palabraClave, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		restriccionesEtiqueta.weightx=1.0;
		restriccionesEtiqueta.ipadx=120;
		panelTexto.add(CAMPO_PALABRA_CLAVE, restriccionesEtiqueta);
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.ipadx=pax;
		
		JPanel opcionPalabra = new JPanel(new GridLayout(1,3,5,2));
		opcionPalabra.add(sinPalabra);
		opcionPalabra.add(cualquieraPalabra);
		opcionPalabra.add(exactaPalabra);
		panelTexto.add(opcionPalabra, restriccionesEtiqueta);
		
		
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.weightx=0.0;
		panelTexto.add(area, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		restriccionesEtiqueta.weightx=1.0;
		panelTexto.add(campoAreas, restriccionesEtiqueta);
		

		panelCombo.add(idioma, restriccionesEtiqueta2);
		restriccionesEtiqueta2.gridx=2;
		restriccionesEtiqueta2.gridwidth=3;
		restriccionesEtiqueta2.weightx=1.0;
		panelCombo.add(campoIdioma, restriccionesEtiqueta2);
		
		
		restriccionesEtiqueta2.gridx=0;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.weightx=0.0;
		panelCombo.add(formatoArchivo, restriccionesEtiqueta2);
		restriccionesEtiqueta2.gridx=2;
		restriccionesEtiqueta2.gridwidth=1;
		restriccionesEtiqueta2.weightx=1.0;
		panelCombo.add(campoFormArchivo, restriccionesEtiqueta2);	
		
		restriccionesEtiqueta2.gridx=0;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.gridwidth=2;
		restriccionesEtiqueta2.weightx=0.0;
		panelCombo.add(fechaPublicacionAntes, restriccionesEtiqueta2);
		restriccionesEtiqueta2.gridx=2;
		restriccionesEtiqueta2.gridwidth=2;
		restriccionesEtiqueta2.weightx=1.0;
		panelCombo.add(CAMPO_FECHA_PUBLICACION_ANTES, restriccionesEtiqueta2);
		
		restriccionesEtiqueta2.gridx=0;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.gridwidth=2;
		restriccionesEtiqueta2.weightx=0.0;
		panelCombo.add(fechaPublicacionDespues, restriccionesEtiqueta2);
		restriccionesEtiqueta2.gridx=2;
		restriccionesEtiqueta2.gridwidth=2;
		restriccionesEtiqueta2.weightx=1.0;
		panelCombo.add(CAMPO_FECHA_PUBLICACION_DESPUES, restriccionesEtiqueta2);
		
		
		restriccionesEtiqueta2.gridx=0;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.weightx=0.0;
		panelCombo.add(etiquetaCantidadResultado, restriccionesEtiqueta2);
		restriccionesEtiqueta2.gridx=2;
		restriccionesEtiqueta2.gridwidth=1;
		restriccionesEtiqueta2.weightx=1.0;
		panelCombo.add(campoCantidadResultados, restriccionesEtiqueta2);
		
		/*restriccionesEtiqueta2.gridx=0;
		restriccionesEtiqueta2.gridy++;
		restriccionesEtiqueta2.weightx=0.0;
		restriccionesEtiqueta2.insets= new Insets(4, 3, 2, 2);
		panelCombo.add(panelCantidadResultados, restriccionesEtiqueta2);
		*/
		restriccionesBoton.gridy=restriccionesEtiqueta.gridy+1;
		restriccionesBoton.insets= new Insets(4,2,2,2);
		restriccionesBoton.gridwidth= 3;
		restriccionesBoton.anchor = GridBagConstraints.EAST;
		
		panelContenedor.add(panelTexto, BorderLayout.WEST);
		panelContenedor.add(panelCombo, BorderLayout.EAST);
		JPanel panelboton = new JPanel();
		panelboton.add(botonConsultaAvanzada);
		panelContenedor.add(panelboton,BorderLayout.SOUTH);
		PANEL_AVANZADA.add(panelContenedor);
		//PANEL_AVANZADA.add(botonConsultaAvanzada, restriccionesBoton);
		//PANEL_AVANZADA.add();
		PANEL_PRINCIPAL = new JPanel(new BorderLayout());
		PANEL_PRINCIPAL.add(PANEL_AVANZADA, BorderLayout.NORTH);
		//setLayout(new BorderLayout());
		this.getViewport().add(PANEL_PRINCIPAL);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void initComponents()
	{
		inicializarLabels();
		inicializarTextFields();
		inicializarComboBox();
		inicializarRadioButtons();
		
		botonConsultaAvanzada = new Button("Consultar");
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
		fechaPublicacionAntes = inicializarLabel("Antes del año de publicación: ");
		fechaPublicacionDespues = inicializarLabel("Después del año de publicación: ");
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
		CAMPO_PALABRA_CLAVE = new JTextField(30); 
		CAMPO_TITULO = new JTextField(30);
		CAMPO_AUTOR = new JTextField(30);
		CAMPO_FECHA_PUBLICACION_ANTES = new JTextField(10);
		CAMPO_FECHA_PUBLICACION_ANTES.addKeyListener(manejador);
		CAMPO_FECHA_PUBLICACION_ANTES.setToolTipText("Año de cuatro digitos.");
		CAMPO_FECHA_PUBLICACION_DESPUES = new JTextField(10);
		CAMPO_FECHA_PUBLICACION_DESPUES.addKeyListener(manejador);
		CAMPO_FECHA_PUBLICACION_DESPUES.setToolTipText("Año de cuatro digitos.");
		

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
		configuracion.weightx=0.0;
		configuracion.weighty=1.0;
		configuracion.anchor= GridBagConstraints.WEST;
		return configuracion;
	}

	private class Manejador implements ActionListener, ItemListener, KeyListener
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
				
				palabraClave = CAMPO_PALABRA_CLAVE.getText();
				titulo = CAMPO_TITULO.getText();
				autor = CAMPO_AUTOR.getText();
				fechaPublicacionAntes = CAMPO_FECHA_PUBLICACION_ANTES.getText();
				fechaPublicacionDespues = CAMPO_FECHA_PUBLICACION_DESPUES.getText();
				
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
				
				atributosConsulta.add("idioma");
				valoresConsulta.add(idioma);
				atributosConsulta.add("formato");
				valoresConsulta.add(formatoArchivo);
				atributosConsulta.add("area");
				valoresConsulta.add(area);
				
				ControladorConsulta controlador = new ControladorConsulta();
				Vector<Consulta> consulta = controlador.consultaAvanzada(atributosConsulta, valoresConsulta, opcionTitulo, opcionPalabra, opcionAutor);
			
				System.out.println(consulta);
				GuiResultadoConsulta.TIPO_CONSULTA = 2;
				
				
				
				if(PANEL_RESULTADO_CONSULTA!=null){
					PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
					
					
				}else{
					PANEL_RESULTADO_CONSULTA=null;
					}
				int cantidad = Integer.parseInt((String) campoCantidadResultados.getSelectedItem());
				PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(consulta,cantidad);
				
				
				//JOptionPane.showMessageDialog(null, ""+consulta.size());
				PANEL_PRINCIPAL.add(PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER);
				PANEL_PRINCIPAL.updateUI();
				if(consulta.size() <=0){
					
					JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
					
				}	
				System.out.println(GuiConsultaBasica.TIPO_USUARIO);
				
				
				
				
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
		@Override
		public void keyPressed(KeyEvent e) {
			JTextField campoFecha = (JTextField) e.getSource();
			if(campoFecha.getText().length()>3)
			{
				if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
					getToolkit().beep();//sonido
					campoFecha.setText(campoFecha.getText().substring(0,3));
				}
			}			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			
			char caracter = e.getKeyChar();
	        if(!(Character.isDigit(caracter)) && 
	        		(caracter != KeyEvent.VK_BACK_SPACE)){
	            e.consume();
	            
	        }
			
		}
		
	}

	
	/*public static void main(String args[]) 
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
	*/
	
	
	
	public static void ponerDescripcion()
	{
		PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
		PANEL_PRINCIPAL.remove(PANEL_AVANZADA);
		PANEL_PRINCIPAL.add(PANEL_VISTA_DOCUMENTO, BorderLayout.CENTER);
		PANEL_PRINCIPAL.updateUI();
	}
	public static void restaurar()
	{
		PANEL_PRINCIPAL.remove(PANEL_VISTA_DOCUMENTO);
		//campoConsulta.setText("");
		PANEL_PRINCIPAL.add(PANEL_AVANZADA, BorderLayout.NORTH);
		PANEL_PRINCIPAL.add(PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER );
		
		//PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta();
		PANEL_PRINCIPAL.remove(PANEL_VISTA_DOCUMENTO);
		PANEL_PRINCIPAL.updateUI();
	}
	public static void restaurarTodo()
	{
		if(!(PANEL_VISTA_DOCUMENTO == null))		
			PANEL_PRINCIPAL.remove(PANEL_VISTA_DOCUMENTO);
		
		CAMPO_PALABRA_CLAVE.setText("");
		CAMPO_TITULO.setText("");
		CAMPO_AUTOR.setText("");
		CAMPO_FECHA_PUBLICACION_DESPUES.setText("");
		CAMPO_FECHA_PUBLICACION_ANTES.setText("");
		PANEL_PRINCIPAL.add(PANEL_AVANZADA, BorderLayout.NORTH);		
		
		if(!(PANEL_RESULTADO_CONSULTA == null))
					PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
		
		PANEL_PRINCIPAL.updateUI();
	}
	
}
