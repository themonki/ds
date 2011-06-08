/**
 * GuiReportes.java
 * 
 * Clase que representa la interfaz de reportes,en la cual un usuario 
 * administrador tiene la posibilidad de generar distintos tipos de reportes
 * con los cuales se puede hacer un seguimiento de lo que sucede dia a dia 
 * en la biblioteca digital de una forma organiza y bonita como lo es un 
 * reporte el cual puede mostrar tambien graficos.
 * 
 * 
 * JAVA version "1.6.0"
 *  
 * Autor(es):  Luis Felipe Vargas
 * 			   Maria Andrea Cruz Blandon
 * 			  
 * Version:   4.0
 */

package Reportes.Gui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

/**
 * 
 * Clase que permite a un usuario administrador tener una interfaz detallada 
 * con los diferentes tipos de reportes que el puede realizar, ya sean 
 * basicos, avazanzados o reportes generados por el mismo mediante la ejecuccion 
 * de una consulta sql.
 * 
 * @author 
 *
 */
public class GuiReportes extends JTabbedPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel PanelreportesBasicos; 
	private GuiReportesAvanzados panelReportesAvanzados;
	
	private GridBagConstraints retricciones;
	
	private JCheckBox habilitar, restringirAnio, restringirMes;
	
	private JScrollPane scroll;
	
	private JLabel etiquetaIcono,etiquetaTabla, etiquetaAtributo, etiquetaTitulo, etiquetaDesde, etiquetaHasta,
				   etiquetaDesdeAnio, etiquetaDesdeMes, etiquetaHastaAnio, etiquetaHastaMes;
	
	private JTextField campoTitulo;
	
	private JComboBox tablas , atributos ,fechas;
	
	private Button botonGenerarReporte;
	
	private JRadioButton detallado, totales;
	
	private ButtonGroup opcionReporte;
	
	private JSpinner campoFecha, campoFecha2, campoFechaDesdeAnio, campoFechaDesdeMes,
					 campoFechaHastaAnio, campoFechaHastaMes; //estos dos son para el intervalo de reportes basicos
 
	private ControladorReportes controladorReporte;

	/**
	 *
	 *	Construcor que permite inicializar los componentes principales de la gui
	 *	esta interfaz se encuentra dividad en pestanas para hacer mas facil su
	 *	uso y la elaboracion de cada uno de los reportes requeridos.
	 * 
	 */
	public GuiReportes()
	{
		controladorReporte= new ControladorReportes();
		initComponents();
	}

	/**
	 * 
	 * Metodo que inicializa cada uno de los componentes de la interfaz
	 * dandole un determinado estilo e instanciandolos adecuandamente, es 
	 * decir asignandoles espacio en memoria.
	 * 
	 * 
	 */
	private void initComponents() {
		
		scroll= new JScrollPane();
		
		inicializarLabels();
		inicializarComboBOx();
		
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		//PanelreportesBasicos.setBackground(Color.WHITE);
						
		habilitar= new JCheckBox("Habilitar Periodo Con : ");
		habilitar.setFont(Estilos.fontSubrayados);
		habilitar.addActionListener(new Manejador());
		
		restringirAnio = new JCheckBox("Restringir por año");
		restringirAnio.setFont(Estilos.fontSubrayados);
		restringirAnio.setVisible(false);
		restringirAnio.addActionListener(new Manejador());
		
		restringirMes = new JCheckBox("Restringir por mes");
		restringirMes.setFont(Estilos.fontSubrayados);
		restringirMes.setVisible(false);
		restringirMes.addActionListener(new Manejador());

		detallado = new JRadioButton("Informe Detallado", true);
		detallado.addActionListener(new Manejador());
		totales = new JRadioButton("Informe Solo Con Totales", false);
		totales.addActionListener(new Manejador());
		
		opcionReporte = new ButtonGroup();
		opcionReporte.add(detallado);
		opcionReporte.add(totales);
		
		campoTitulo = new JTextField(20);
		
		//Crear spinner para la fecha1.
		SpinnerModel modeloFecha = new SpinnerDateModel();
		campoFecha = new JSpinner(modeloFecha);
	    campoFecha.setFont(Estilos.fontLabels);
	    campoFecha.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFecha,"yyyy-MM-dd");
		campoFecha.setEditor(spinnerFecha);
	    ((JSpinner.DateEditor) campoFecha.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFecha.setVisible(false);
	    //-------------------------------------
	    //Crear spinner para la fecha2.
		SpinnerModel modeloFecha2 = new SpinnerDateModel();
		campoFecha2 = new JSpinner(modeloFecha2);
	    campoFecha2.setFont(Estilos.fontLabels);
	    campoFecha2.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha2 = new JSpinner.DateEditor(campoFecha2,"yyyy-MM-dd");
		campoFecha2.setEditor(spinnerFecha2);
	    ((JSpinner.DateEditor) campoFecha2.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFecha2.setVisible(false);
	    //-------------------------------------
	    
	  //Crear spinner para la fecha desde del anio.
		SpinnerModel modeloFechaDesdeAnio = new SpinnerDateModel();
		campoFechaDesdeAnio = new JSpinner(modeloFechaDesdeAnio);
	    campoFechaDesdeAnio.setFont(Estilos.fontLabels);
	    campoFechaDesdeAnio.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaDA = new JSpinner.DateEditor(campoFechaDesdeAnio,"yyyy");
		campoFechaDesdeAnio.setEditor(spinnerFechaDA);
	    ((JSpinner.DateEditor) campoFechaDesdeAnio.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFechaDesdeAnio.setVisible(false);
	    //-------------------------------------
	    //Crear spinner para la fecha desde del mes.
	    SpinnerModel modeloFechaDesdeMes = new SpinnerDateModel();
		campoFechaDesdeMes = new JSpinner(modeloFechaDesdeMes);
	    campoFechaDesdeMes.setFont(Estilos.fontLabels);
	    campoFechaDesdeMes.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaDM = new JSpinner.DateEditor(campoFechaDesdeMes,"MM");
		campoFechaDesdeMes.setEditor(spinnerFechaDM);
	    ((JSpinner.DateEditor) campoFechaDesdeMes.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFechaDesdeMes.setVisible(false);
	    //-------------------------------------
	    
	  //Crear spinner para la fecha hasta del anio.
		SpinnerModel modeloFechaHastaAnio = new SpinnerDateModel();
		campoFechaHastaAnio = new JSpinner(modeloFechaHastaAnio);
	    campoFechaHastaAnio.setFont(Estilos.fontLabels);
	    campoFechaHastaAnio.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHA = new JSpinner.DateEditor(campoFechaHastaAnio,"yyyy");
		campoFechaHastaAnio.setEditor(spinnerFechaHA);
	    ((JSpinner.DateEditor) campoFechaHastaAnio.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFechaHastaAnio.setVisible(false);
	    //-------------------------------------
	    //Crear spinner para la fecha hasta del mes.
	    SpinnerModel modeloFechaHastaMes = new SpinnerDateModel();
		campoFechaHastaMes = new JSpinner(modeloFechaHastaMes);
	    campoFechaHastaMes.setFont(Estilos.fontLabels);
	    campoFechaHastaMes.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHM = new JSpinner.DateEditor(campoFechaHastaMes,"MM");
		campoFechaHastaMes.setEditor(spinnerFechaHM);
	    ((JSpinner.DateEditor) campoFechaHastaMes.getEditor()).getTextField().setEditable(false);
	    //-------------------------------------
	    campoFechaHastaMes.setVisible(false);
	    //-------------------------------------
	    

		botonGenerarReporte= new Button("Generar Reporte");
		botonGenerarReporte.setIcon(new ImageIcon("recursos/iconos/Report2.png"));
		botonGenerarReporte.addActionListener(new Manejador());
		
		//--- INGRESANDO ATRIBUTOS EN EL PANEL ---
		
		retricciones= new GridBagConstraints();
		retricciones.insets= new Insets(6,2,60,2);
		retricciones.gridy=0;
		retricciones.gridwidth=4;
		
		retricciones.anchor= GridBagConstraints.CENTER;
		//retricciones.weightx=0.0;
		PanelreportesBasicos.add(etiquetaIcono,retricciones);
		retricciones.insets= new Insets(6,10,6,2);
		retricciones.anchor= GridBagConstraints.WEST;
		retricciones.gridwidth=1;
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaTabla,retricciones);
		//retricciones.weightx=1.0;
		PanelreportesBasicos.add(tablas,retricciones);
		//retricciones.gridy++;
		//retricciones.weightx=0.0;
		retricciones.insets= new Insets(6, 20, 6, 50);		
		PanelreportesBasicos.add(etiquetaAtributo,retricciones);
		//retricciones.weightx=1.0;
		retricciones.insets= new Insets(6,10,6,0);
		PanelreportesBasicos.add(atributos,retricciones);
		retricciones.gridy++;
		//retricciones.weightx=0.0;
		retricciones.insets= new Insets(35, 10, 2, 6);		

		PanelreportesBasicos.add(etiquetaTitulo, retricciones);
		retricciones.gridwidth=2;
		PanelreportesBasicos.add(campoTitulo, retricciones);
		retricciones.gridy++;
		retricciones.insets= new Insets(5,10,16,2);
		
		//retricciones.weightx=0.0;
		PanelreportesBasicos.add(detallado, retricciones);
		PanelreportesBasicos.add(totales, retricciones);
		retricciones.gridy++;
		retricciones.insets= new Insets(10,10,16,2);
		
		
		PanelreportesBasicos.add(restringirAnio, retricciones);
		retricciones.insets= new Insets(6,10,6,2);
	    retricciones.gridy++;
	    retricciones.gridx=0;
	    retricciones.gridwidth=1;
	    PanelreportesBasicos.add(etiquetaDesdeAnio, retricciones);
	    retricciones.gridx = 1;
	    PanelreportesBasicos.add(campoFechaDesdeAnio, retricciones);
	    retricciones.gridx = 2;
	    PanelreportesBasicos.add(etiquetaHastaAnio, retricciones);
	    retricciones.gridx = 3;
	    PanelreportesBasicos.add(campoFechaHastaAnio, retricciones);
	    retricciones.gridy++;
	    retricciones.insets= new Insets(10,10,16,2);
	    retricciones.gridx = 0;
	    retricciones.gridwidth=2;
	    PanelreportesBasicos.add(restringirMes, retricciones);
	    retricciones.insets= new Insets(6,10,6,2);
	    retricciones.gridy++;
	    retricciones.gridwidth=1;
	    PanelreportesBasicos.add(etiquetaDesdeMes, retricciones);
	    retricciones.gridx = 1;	    
	    PanelreportesBasicos.add(campoFechaDesdeMes, retricciones);
	    retricciones.gridx = 2;
	    PanelreportesBasicos.add(etiquetaHastaMes, retricciones);
	    retricciones.gridx = 3;
	    PanelreportesBasicos.add(campoFechaHastaMes, retricciones);
	    retricciones.gridy++;
	    retricciones.insets= new Insets(30,10,16,2);
	    retricciones.gridx = 0;
	   
		PanelreportesBasicos.add(habilitar,retricciones);
		//retricciones.weightx=1.0;
		retricciones.gridx=2;
		PanelreportesBasicos.add(fechas,retricciones);
		retricciones.insets= new Insets(6,10,6,2);
	    retricciones.gridy++;
	    retricciones.gridx=0;
	    //retricciones.weightx=0.0;
	    PanelreportesBasicos.add(etiquetaDesde,retricciones);
	    retricciones.gridx=1;
	    //retricciones.weightx=1.0;
		PanelreportesBasicos.add(campoFecha,retricciones);
		//retricciones.gridy++;
		retricciones.gridx=2;
		//retricciones.weightx=0.0;	    
		PanelreportesBasicos.add(etiquetaHasta,retricciones);
		retricciones.gridx=3;
		//retricciones.weightx=1.0;
		PanelreportesBasicos.add(campoFecha2,retricciones);
		
		
		retricciones.gridy++;
		retricciones.gridwidth=2;
		retricciones.gridy++;
		retricciones.gridy=retricciones.gridy+10;
		retricciones.gridx=1;
		retricciones.anchor= GridBagConstraints.CENTER;
		//retricciones.weightx=0.0;
		retricciones.insets= new Insets(100, 0, 0, 0);		
		PanelreportesBasicos.add(botonGenerarReporte,retricciones);
	
		retricciones.insets= new Insets(0, 0, 0, 0);

		scroll.setViewportView(PanelreportesBasicos);
		panelReportesAvanzados = new GuiReportesAvanzados();
		//scroll2.setViewportView(panelReportesAvanzados);
		
		//-------------------------------------
		GuiReporteSQL sqlpanel= new GuiReporteSQL();
		
		addTab("Reportes Basicos",scroll);
		addTab("Reportes Avanzados",panelReportesAvanzados);
		addTab("Consultas SQL",sqlpanel );
	}


	private void inicializarComboBOx() {
		
		tablas = new JComboBox();
		tablas.addItem("Usuarios");
		tablas.addItem("Areas");
		tablas.addItem("Documentos");
		tablas.addActionListener(new Manejador());
		
		atributos = new JComboBox();
		atributos.removeAllItems();
		atributos.addItem("genero");
		atributos.addItem("nivel_escolaridad");
		atributos.addItem("vinculo_univalle");
		atributos.addItem("tipo");
		atributos.addItem("fecha_nacimiento");
		atributos.addItem("fecha_registro");
		atributos.addActionListener(new Manejador());
		
		fechas= new JComboBox();
		fechas.addItem("fecha_nacimiento");
		fechas.addItem("fecha_registro");
		fechas.addItem("fecha_ultimo_acceso");
		
	}

	/**
	 * 
	 * Metodo que permite inicializar las etiquetas
	 * dandoles su respectivo nombre y algunas cambiandoles
	 * la imagen con la que van a ser visualizadas
	 * 
	 * 
	 * 
	 */
	private void inicializarLabels() {
		
		etiquetaTabla= new JLabel("Reporte De: ");
		etiquetaAtributo= new JLabel("Agrupados Por : ");
		etiquetaTitulo = new JLabel("Titulo Del Reporte : ");
		etiquetaDesde= new JLabel("Desde: ");
		etiquetaHasta= new JLabel("Hasta: ");
		etiquetaDesdeAnio = new JLabel("Desde: ");
		etiquetaDesdeMes = new JLabel("Desde: ");
		etiquetaHastaAnio = new JLabel("Hasta: ");
		etiquetaHastaMes = new JLabel("Hasta: ");
		etiquetaIcono= new JLabel(new ImageIcon("recursos/iconos/custom-reports.png"));
		
		etiquetaHasta.setVisible(false);
		etiquetaDesde.setVisible(false);
		etiquetaDesdeAnio.setVisible(false);
		etiquetaDesdeMes.setVisible(false);
		etiquetaHastaAnio.setVisible(false);
		etiquetaHastaMes.setVisible(false);
		
		etiquetaTabla.setForeground(Estilos.colorLabels);
		etiquetaAtributo.setForeground(Estilos.colorLabels);
		//etiquetaTitulo.setForeground(Estilos.colorLabels);
		
		etiquetaTabla.setFont(Estilos.fontLabels);
		etiquetaAtributo.setFont(Estilos.fontLabels);
		etiquetaDesde.setFont(Estilos.fontSubtitulos);
		etiquetaHasta.setFont(Estilos.fontSubtitulos);
		etiquetaTitulo.setFont(Estilos.fontSubtitulos);
		etiquetaDesdeAnio.setFont(Estilos.fontSubtitulos);
		etiquetaDesdeMes.setFont(Estilos.fontSubtitulos);
		etiquetaHastaAnio.setFont(Estilos.fontSubtitulos);
		etiquetaHastaMes.setFont(Estilos.fontSubtitulos);
		
	}

	/*public static void main(String args[]) {

		try
		{				
			NimRODTheme nt = new NimRODTheme("recursos/NimRODThemeFile2.theme");
			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			NimRODLookAndFeel.setCurrentTheme(nt);
			UIManager.setLookAndFeel( NimRODLF);
		}
		catch (Exception e){e.printStackTrace();}

	
		GuiReportes al = new GuiReportes();
		JFrame a= new JFrame();
		a.add(al);
		a.setVisible(true);
		a.setSize(500,500);
		//a.setExtendedState(a.MAXIMIZED_BOTH);

		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}*/
	
	/**
	 * Clase que permite manejar todos los eventos relacionados con 
	 * los botones que pertenecen a la interfaz asi mismo como 
	 * realizar una determinada accion correspondiente al evento generado
	 * 
	 * @author 
	 *
	 */
	public class Manejador implements ActionListener
	{		
		
		public void actionPerformed(ActionEvent evento)
		{
			if (evento.getSource()==habilitar)
			{
				if(habilitar.isSelected())
				{
					campoFecha.setVisible(true);
					campoFecha2.setVisible(true);
					etiquetaHasta.setVisible(true);
					etiquetaDesde.setVisible(true);
				}
				else
				{
					campoFecha.setVisible(false);
					campoFecha2.setVisible(false);
					etiquetaHasta.setVisible(false);
					etiquetaDesde.setVisible(false);
				}
				
			}else if (evento.getSource() == restringirAnio)
			{
				if(restringirAnio.isSelected())
				{
					etiquetaDesdeAnio.setVisible(true);
					etiquetaHastaAnio.setVisible(true);
					campoFechaDesdeAnio.setVisible(true);
					campoFechaHastaAnio.setVisible(true);
					
				}else
				{
					
					etiquetaDesdeAnio.setVisible(false);
					etiquetaHastaAnio.setVisible(false);
					campoFechaDesdeAnio.setVisible(false);
					campoFechaHastaAnio.setVisible(false);
				}
				
				
				
			}else if (evento.getSource() == restringirMes)
			{
				if(restringirMes.isSelected())
				{
					
					etiquetaDesdeMes.setVisible(true);
					etiquetaHastaMes.setVisible(true);
					campoFechaDesdeMes.setVisible(true);
					campoFechaHastaMes.setVisible(true);
				}else
				{
					etiquetaDesdeMes.setVisible(false);
					etiquetaHastaMes.setVisible(false);
					campoFechaDesdeMes.setVisible(false);
					campoFechaHastaMes.setVisible(false);
				}
				
			}else if(evento.getSource() == atributos)
			{
				if(atributos.getSelectedIndex() != -1)
				{
					if(((String) atributos.getSelectedItem()).contains("fecha_nacimiento") ||
							   ((String) atributos.getSelectedItem()).contains("fecha_registro"))
							{
								restringirAnio.setVisible(true);
								restringirMes.setVisible(true);
								habilitar.setVisible(false);
								habilitar.setSelected(false);
								fechas.setVisible(false);
								etiquetaDesde.setVisible(false);
								etiquetaHasta.setVisible(false);
								campoFecha.setVisible(false);
								campoFecha2.setVisible(false);
								
								if(restringirAnio.isSelected())
								{
									etiquetaDesdeAnio.setVisible(true);
									etiquetaHastaAnio.setVisible(true);
									campoFechaDesdeAnio.setVisible(true);
									campoFechaHastaAnio.setVisible(true);
								}
								
								if(restringirMes.isSelected())
								{
									etiquetaDesdeMes.setVisible(true);
									etiquetaHastaMes.setVisible(true);
									campoFechaDesdeMes.setVisible(true);
									campoFechaHastaMes.setVisible(true);
								}
							}
					else
					{
						restringirAnio.setVisible(false);
						restringirMes.setVisible(false);
						etiquetaDesdeMes.setVisible(false);
						etiquetaHastaMes.setVisible(false);
						campoFechaDesdeMes.setVisible(false);
						campoFechaHastaMes.setVisible(false);
						etiquetaDesdeAnio.setVisible(false);
						etiquetaHastaAnio.setVisible(false);
						campoFechaDesdeAnio.setVisible(false);
						campoFechaHastaAnio.setVisible(false);
						campoFecha.setVisible(false);
						campoFecha2.setVisible(false);
						if(tablas.getSelectedItem().equals("Usuarios"))
						{
							habilitar.setVisible(true);
							fechas.setVisible(true);
						}
						
					}
				}
				
				
			}else if (evento.getSource()== tablas)
			{
				String item =(String) tablas.getSelectedItem(); 
				//System.out.println(item);
				if ( item.contains("Areas"))
				{
					atributos.removeAllItems();
					atributos.addItem("area_padre");
					campoFecha.setVisible(false);
					campoFecha2.setVisible(false);
					habilitar.setVisible(false);
					fechas.setVisible(false);
					etiquetaDesde.setVisible(false);
					etiquetaHasta.setVisible(false);
					restringirAnio.setVisible(false);
					restringirMes.setVisible(false);
					etiquetaDesdeMes.setVisible(false);
					etiquetaHastaMes.setVisible(false);
					campoFechaDesdeMes.setVisible(false);
					campoFechaHastaMes.setVisible(false);
					etiquetaDesdeAnio.setVisible(false);
					etiquetaHastaAnio.setVisible(false);
					campoFechaDesdeAnio.setVisible(false);
					campoFechaHastaAnio.setVisible(false);
				}
				if ( item.contains("Usuario"))
				{
					atributos.removeAllItems();
					atributos.addItem("genero");
					atributos.addItem("nivel_escolaridad");
					atributos.addItem("vinculo_univalle");
					atributos.addItem("tipo");
					atributos.addItem("fecha_nacimiento");
					atributos.addItem("fecha_registro");
					habilitar.setVisible(true);
					fechas.setVisible(true);
					restringirAnio.setVisible(false);
					restringirMes.setVisible(false);
					etiquetaDesdeMes.setVisible(false);
					etiquetaHastaMes.setVisible(false);
					campoFechaDesdeMes.setVisible(false);
					campoFechaHastaMes.setVisible(false);
					etiquetaDesdeAnio.setVisible(false);
					etiquetaHastaAnio.setVisible(false);
					campoFechaDesdeAnio.setVisible(false);
					campoFechaHastaAnio.setVisible(false);
					
					if(habilitar.isSelected())
					{
						etiquetaDesde.setVisible(true);
						etiquetaHasta.setVisible(true);
						campoFecha.setVisible(true);
						campoFecha2.setVisible(true);
					}
					
				}
				if ( item.contains("Documentos"))
				{
					atributos.removeAllItems();
					atributos.addItem("formato");
					atributos.addItem("area");
					atributos.addItem("autor");
					atributos.addItem("tipo");
					campoFecha.setVisible(false);
					campoFecha2.setVisible(false);
					habilitar.setVisible(false);
					fechas.setVisible(false);
					etiquetaDesde.setVisible(false);
					etiquetaHasta.setVisible(false);
					restringirAnio.setVisible(false);
					restringirMes.setVisible(false);
					etiquetaDesdeMes.setVisible(false);
					etiquetaHastaMes.setVisible(false);
					campoFechaDesdeMes.setVisible(false);
					campoFechaHastaMes.setVisible(false);
					etiquetaDesdeAnio.setVisible(false);
					etiquetaHastaAnio.setVisible(false);
					campoFechaDesdeAnio.setVisible(false);
					campoFechaHastaAnio.setVisible(false);
				}
			
			}else if (evento.getSource()== botonGenerarReporte)
			{
				//tablas.getSelectedItem();
					scroll.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					
					String rutaFinal = "";
					String encabezado = campoTitulo.getText();
					boolean detalladoR = opcionReporte.isSelected(detallado.getModel());
					boolean totalesR = opcionReporte.isSelected(totales.getModel());
					boolean habilitarFechas = habilitar.isSelected();
					boolean restringirAnioBool = restringirAnio.isSelected();
					boolean restringirMesBool = restringirMes.isSelected();
					boolean usuario = tablas.getSelectedItem().equals("Usuarios");
					boolean areas = tablas.getSelectedItem().equals("Areas");
					boolean documento = tablas.getSelectedItem().equals("Documentos");
					
					String atributoSeleccionado = (String) atributos.getSelectedItem();
					String fechaBusqueda = (String)fechas.getSelectedItem();
					Date fecha= ((JSpinner.DateEditor) campoFecha.getEditor()).getModel().getDate();
				    SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
					String fechaInicioString = formatoFecha.format(fecha);
					//System.out.println("Fecha inio " + fechaInicioString);
					fecha= ((JSpinner.DateEditor) campoFecha2.getEditor()).getModel().getDate();
					String fechaFinString = formatoFecha.format(fecha);
					//System.out.println("Fecha fin " +fechaFinString);
					
					SimpleDateFormat formatoAnio= new SimpleDateFormat("yyyy");
					String fechaDesdeAnio =  formatoAnio.format(((JSpinner.DateEditor) campoFechaDesdeAnio.getEditor()).getModel().getDate());
					String fechaHastaAnio =  formatoAnio.format(((JSpinner.DateEditor) campoFechaHastaAnio.getEditor()).getModel().getDate());
					SimpleDateFormat formatoMes= new SimpleDateFormat("MM");
					String fechaDesdeMes =  formatoMes.format(((JSpinner.DateEditor) campoFechaDesdeMes.getEditor()).getModel().getDate());
					String fechaHastaMes =  formatoMes.format(((JSpinner.DateEditor) campoFechaHastaMes.getEditor()).getModel().getDate());
					
					
					
					if(detalladoR)
					{
						if(usuario)
						{
							try
							{
								JasperPrint reporte;
								if(habilitarFechas)
								{
									
										reporte = controladorReporte.reporteUsuariosAgrupados(atributoSeleccionado, fechaBusqueda, fechaInicioString, fechaFinString, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									
								}else
								{
									if(restringirMesBool && restringirAnioBool)
									{
										reporte = controladorReporte.reporteUsuariosAnioMes(atributoSeleccionado, fechaDesdeAnio, fechaHastaAnio, fechaDesdeMes, fechaHastaMes, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else if(restringirAnioBool)
									{
										reporte = controladorReporte.reporteUsuariosAnio(atributoSeleccionado, fechaDesdeAnio, fechaHastaAnio, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else if (restringirMesBool)
									{
										reporte = controladorReporte.reporteUsuariosAnioMes(atributoSeleccionado, fechaDesdeMes, fechaHastaMes, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										/*if(atributoSeleccionado.contains("fecha_"))
										{
											reporte = controladorReporte.reporteUsuariosAnio(atributoSeleccionado, encabezado);
											controladorReporte.generarReporte(rutaFinal, reporte);
											
										}else{*/
											reporte = controladorReporte.reporteUsuariosAgrupados(atributoSeleccionado, encabezado);
											controladorReporte.generarReporte(rutaFinal, reporte);
											
										//}
										
										
									}
									
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								scroll.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
								e.printStackTrace();
							}
						}
						
						if(areas)
						{
							try
							{
								JasperPrint reporte = controladorReporte.reporteAreasAgrupadas(encabezado);
								controladorReporte.generarReporte(rutaFinal, reporte);
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed" +
										"tratando de llamarse el generar reporte de areas detalladas");
								e.printStackTrace();
							}
						}
						
						if(documento)
						{
							try
							{
								JasperPrint reporte;
								
								if(atributoSeleccionado.contains("formato"))
								{
									reporte= controladorReporte.reporteDocumentosAgrupadosFormato(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("area"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosArea(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("autor"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosAutor(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("tipo"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosTipo(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
								e.printStackTrace();
							}
							
						}
					}
					if(totalesR)
					{
						if(usuario)
						{
							try
							{
								JasperPrint reporte;
								if(habilitarFechas)
								{
									reporte = controladorReporte.reporteUsuariosAgrupadosTotales(atributoSeleccionado, fechaBusqueda, fechaInicioString, fechaFinString, encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else
								{
									if(restringirMesBool && restringirAnioBool)
									{
										reporte = controladorReporte.reporteUsuariosAnioMesTotales(atributoSeleccionado, fechaDesdeAnio, fechaHastaAnio, fechaDesdeMes, fechaHastaMes, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else if(restringirAnioBool)
									{
										reporte = controladorReporte.reporteUsuariosAnioTotales(atributoSeleccionado, fechaDesdeAnio, fechaHastaAnio, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									} else if(restringirMesBool)
									{
										reporte = controladorReporte.reporteUsuariosAnioMesTotales(atributoSeleccionado, fechaDesdeMes, fechaHastaMes, encabezado);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{		
										/*if(atributoSeleccionado.contains("fecha_"))
										{
											reporte = controladorReporte.reporteUsuariosAnioTotales(atributoSeleccionado, encabezado);
											controladorReporte.generarReporte(rutaFinal, reporte);
											
										}else{*/
											reporte = controladorReporte.reporteUsuariosAgrupadosTotales(atributoSeleccionado, encabezado);
											controladorReporte.generarReporte(rutaFinal, reporte);
										//}
										
									}
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
								e.printStackTrace();
							}
						}
						
						if(areas)
						{
							try
							{
								JasperPrint reporte = controladorReporte.reporteAreasAgrupadasTotales(encabezado);
								controladorReporte.generarReporte(rutaFinal, reporte);
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed" +
										"tratando de llamarse el generar reporte de areas totales");
								e.printStackTrace();
							}
						}
						
						if(documento)
						{
							try
							{
								JasperPrint reporte;
								
								if(atributoSeleccionado.contains("formato"))
								{
									reporte= controladorReporte.reporteDocumentosAgrupadosFormatoTotales(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("area"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosAreaTotales(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("autor"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosAutorTotales(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else if(atributoSeleccionado.contains("tipo"))
								{
									reporte = controladorReporte.reporteDocumentosAgrupadosTipoTotales(encabezado);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
								e.printStackTrace();
							}
							
						}
					}
					
					//reporte.setEncabezado(encabezado);
				
				
				//System.out.println(controladorReporte.consultarUsuariosAgrupados((String) atributos.getSelectedItem()));
				
				//System.out.println("reporte generado");
					scroll.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

				}
			//nuevaCondicon();
		}	
	}

	/*public void nuevaCondicon() 
	{
		if(cantCondicion==3)return;
		JComboBox combo= new JComboBox(vectorAtributos);
		//combo.setName("atributo "+cantCondicion);
		JComboBox combocondicion= new JComboBox(vectorCondiciones);
		//combocondicion.setName("condicion "+cantCondicion);
		JTextField campo= new JTextField();
		//campo.setName("campo "+cantCondicion);
		
		retricciones.gridy=6+cantCondicion;
		retricciones.gridx=0;
		retricciones.anchor= GridBagConstraints.EAST;
		PanelreportesBasicos.add(combo,retricciones);
		retricciones.gridx=1;
		retricciones.anchor= GridBagConstraints.CENTER;
		PanelreportesBasicos.add(combocondicion,retricciones);
		retricciones.gridx=2;
		retricciones.gridx=2;
		retricciones.ipadx=100;
		PanelreportesBasicos.add(campo,retricciones);
		retricciones.ipadx=0;
		
		cantCondicion++;
		
		
		PanelreportesBasicos.updateUI();
	}*/
	
}
