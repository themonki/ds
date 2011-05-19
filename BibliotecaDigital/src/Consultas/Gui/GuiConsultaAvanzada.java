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
import com.sun.org.apache.regexp.internal.RESyntaxException;

public class GuiConsultaAvanzada extends JPanel
{
	
	JLabel palabraClave,area ,titulo,autor,idioma,fechaPublicacionAntes, fechaPublicacionDespues,formatoArchivo;
	JTextField campoPalabraClave,campoTitulo, campoAutor,campoFechaPublicacionAntes, campoFechaPublicacionDespues;
	JComboBox campoAreas, campoParametrosTitulo, campoParametrosAutor, campoParametrosPalabraClave, 
	campoIdioma,campoFormArchivo;
	
	JButton botonConsultaAvanzada;
	
	JPanel principal;
	
	Vector<String> valoresConsulta;
	Vector<String> atributosConsulta;
	
	GuiConsultaAvanzada(){
		initComponents();
		
		principal = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionesEtiqueta = configurar(0, 0, new Insets(2,2,2,0));
		GridBagConstraints restriccionesBoton = configurar(0, 0, new Insets(2,0,2,0));

		
		principal.add(titulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoTitulo, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		//restriccionesEtiqueta.gridwidth=1;
		principal.add(autor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoAutor, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=0;
		restriccionesEtiqueta.gridy++;
		restriccionesEtiqueta.gridwidth=1;
		principal.add(palabraClave, restriccionesEtiqueta);
		restriccionesEtiqueta.gridx=1;
		restriccionesEtiqueta.gridwidth=3;
		principal.add(campoPalabraClave, restriccionesEtiqueta);
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
		restriccionesBoton.anchor = GridBagConstraints.WEST;
		principal.add(botonConsultaAvanzada, restriccionesBoton);
		//principal.add();
		
		//setLayout(new BorderLayout());
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
		fechaPublicacionAntes = inicializarLabel("Fecha de publicación antes de: ");
		fechaPublicacionDespues = inicializarLabel("Fecha de publicación después de: ");
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
		campoFechaPublicacionAntes = new JTextField(10);
		campoFechaPublicacionDespues = new JTextField(10);

	}

	private void inicializarComboBox() {
		//vector y arrays para inicializar los combo box
		
		String idiomaArray[] = {"Todos","Ingles", "Español","Frances", "Aleman", "Portuges"};
		String formatoArchivoArray[] = {"Todos", "jpg", "pdf", "doc", "odt", "otro"};
		String parametrosArray[] = {"Con todas las palabras", "Con algunas de estas palabras", "Sin estas palabras"};
		
		Vector<String> areas; 
		
		areas = new Vector<String>();
		areas.addElement("Todas");
		
		ControladorAreaConocimiento controladorAreas = new ControladorAreaConocimiento();
		Vector<AreaConocimiento> areasConocimiento;
		areasConocimiento =  controladorAreas.obtenerAreas();
		
		for(int i=1; i< areasConocimiento.size(); i++){
			areas.addElement(areasConocimiento.elementAt(i).getNombre());
		}

		campoAreas = new JComboBox(areas);
		campoAreas.setSelectedIndex(0);
		campoAreas.setMaximumRowCount(5);
		campoIdioma = new JComboBox(idiomaArray);
		campoIdioma.setSelectedIndex(0);
		campoFormArchivo = new JComboBox(formatoArchivoArray);
		campoFormArchivo.setSelectedIndex(0);
		campoParametrosTitulo = new JComboBox(parametrosArray);
		campoParametrosTitulo.setSelectedIndex(0);
		campoParametrosAutor = new JComboBox(parametrosArray);
		campoParametrosAutor.setSelectedIndex(0);
		campoParametrosPalabraClave = new JComboBox(parametrosArray);
		campoParametrosPalabraClave.setSelectedIndex(0);
		
	}
	
	private GridBagConstraints configurar(int x, int y, Insets insets){
		
		GridBagConstraints configuracion = new GridBagConstraints();
		configuracion.gridx=x;
		configuracion.gridy=y;
		configuracion.insets= insets;
		configuracion.anchor= GridBagConstraints.WEST;
		return configuracion;
	}

	private class ManejadorBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == botonConsultaAvanzada){
				
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
	
		GuiConsultaAvanzada aD = new GuiConsultaAvanzada();
		JFrame a = new JFrame();
		a.add(aD);
		a.setVisible(true);
		a.setSize(550,360);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	

}
