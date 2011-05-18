package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

public class GuiConsultaAvanzada extends JPanel
{
	
	JLabel palabraClave,area ,titulo,autor,idioma,fechaPublicacion,formatoArchivo;
	JTextField campoPalabraClave,campoTitulo, campoAutor;
	JComboBox campoAreas, campoParametrosTitulo, campoParametrosAutor, campoParametrosPalabraClave, 
	campoIdioma,campoFormArchivo,campoFechaPublicacion;
	
	JButton botonConsultaAvanzada;
	
	JPanel principal;
	
	Vector<String> valoresConsulta;
	Vector<String> atributosConsulta;
	
	GuiConsultaAvanzada(){
		initComponents();
		
		principal = new JPanel(new FlowLayout());
		
		principal.add(titulo);
		principal.add(campoTitulo);
		principal.add(autor);
		principal.add(campoAutor);
		principal.add(palabraClave);
		principal.add(campoPalabraClave);
		principal.add(area);
		principal.add(campoAreas);
		principal.add(idioma);
		principal.add(campoIdioma);
		principal.add(fechaPublicacion);
		principal.add(campoFechaPublicacion);
		principal.add(formatoArchivo);
		principal.add(campoFormArchivo);
		principal.add(botonConsultaAvanzada);
		//principal.add();
		
		setLayout(new BorderLayout());
		add(principal);
	}
	
	private void initComponents(){
		inicializarLabels();
		inicializarTextFields();
		inicializarComboBox();
		
		botonConsultaAvanzada = new Button("Buscar");
	    botonConsultaAvanzada.setIcon(new ImageIcon("recursos/iconos/search.png"));
		botonConsultaAvanzada.setHorizontalTextPosition(SwingConstants.LEFT);
		
	}
	
	private void inicializarLabels() {
		palabraClave = inicializarLabel("Palabra Clave: ");
		area = inicializarLabel("Área: ");
		titulo = inicializarLabel("Titulo: ");
		autor = inicializarLabel("Autor: ");
		idioma = inicializarLabel("Idioma: ");
		fechaPublicacion = inicializarLabel("Fecha de publicación: ");
		formatoArchivo = inicializarLabel("Formato de archivo: ");
		
	}
	
	private JLabel inicializarLabel(String titulo){
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setFont(Estilos.fontLabels);
		label.setForeground(Estilos.colorLabels);
		return label;
	}
	
	private void inicializarTextFields() {
		campoPalabraClave = new JTextField(30); 
		campoTitulo = new JTextField(30);
		campoAutor = new JTextField(30);
	}

	private void inicializarComboBox() {
		//vector y arrays para inicializar los combo box
		
		String idiomaArray[] = {"Todos","Ingles", "Español","Frances", "Aleman", "Portuges"};
		String formatoArchivoArray[] = {"Todos", "jpg", "pdf", "doc", "odt", "otro"};
		String fechaPublicacionArray[] = {"Cualquiera", "Último mes", "Hace 6 meses", "Hace 2 años", "Hace 5 años"};
		String parametrosArray[] = {"Con todas las palabras", "Con algunas de estas palabras", "Sin estas palabras"};
		
		Vector<String> areas; 
		
		areas = new Vector<String>();
		areas.addElement("Todas");
		
		ControladorAreaConocimiento controladorAreas = new ControladorAreaConocimiento();
		Vector<AreaConocimiento> areasConocimiento;
		areasConocimiento =  controladorAreas.obtenerAreas();
		
		for(int i=0; i< areasConocimiento.size(); i++){
			areas.addElement(areasConocimiento.elementAt(i).getNombre());
		}

		campoAreas = new JComboBox(areas);
		campoAreas.setSelectedIndex(0);
		campoAreas.setMaximumRowCount(5);
		campoIdioma = new JComboBox(idiomaArray);
		campoIdioma.setSelectedIndex(0);
		campoFormArchivo = new JComboBox(formatoArchivoArray);
		campoFormArchivo.setSelectedIndex(0);
		campoFechaPublicacion = new JComboBox(fechaPublicacionArray);
		campoFechaPublicacion.setSelectedIndex(0);
		campoParametrosTitulo = new JComboBox(parametrosArray);
		campoParametrosTitulo.setSelectedIndex(0);
		campoParametrosAutor = new JComboBox(parametrosArray);
		campoParametrosAutor.setSelectedIndex(0);
		campoParametrosPalabraClave = new JComboBox(parametrosArray);
		campoParametrosPalabraClave.setSelectedIndex(0);
		
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
	
		GuiConsultaAvanzada aD = new GuiConsultaAvanzada();
		JFrame a = new JFrame();
		a.add(aD);
		a.setVisible(true);
		a.setSize(370,400);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	

}
