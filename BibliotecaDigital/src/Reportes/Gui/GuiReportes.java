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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	private JCheckBox habilitar;
	
	private JScrollPane scroll, scroll2;
	
	private JLabel etiquetaIcono,etiquetaTabla, etiquetaAtributo, etiquetaTitulo, etiquetaDesde, etiquetaHasta; ;
	
	private JTextField campoTitulo;
	
	private JComboBox tablas , atributos ,fechas;
	
	private Button botonGenerarReporte;
	
	private JRadioButton detallado, totales;
	
	private ButtonGroup opcionReporte;
	
	private JSpinner campoFecha, campoFecha2; //estos dos son para el intervalo de reportes basicos
 
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
		scroll2= new JScrollPane();
		
		inicializarLabels();
		inicializarComboBOx();
		
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		//PanelreportesBasicos.setBackground(Color.WHITE);
						
		habilitar= new JCheckBox("Habilitar Periodo Con : ");
		habilitar.setFont(Estilos.fontSubrayados);
		habilitar.addActionListener(new Manejador());

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
		retricciones.insets= new Insets(30,10,16,2);
		
		PanelreportesBasicos.add(habilitar,retricciones);
		//retricciones.weightx=1.0;
		PanelreportesBasicos.add(fechas,retricciones);
		retricciones.insets= new Insets(6,10,6,2);
	    retricciones.gridy++;
	    retricciones.gridx=0;
	    //retricciones.weightx=0.0;
	    PanelreportesBasicos.add(etiquetaDesde,retricciones);
	    retricciones.anchor=GridBagConstraints.WEST;
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
		tablas.addActionListener(new Manejador());
		
		atributos = new JComboBox();
		atributos.removeAllItems();
		atributos.addItem("genero");
		atributos.addItem("nivel_escolaridad");
		atributos.addItem("vinculo_univalle");
		atributos.addItem("tipo");
		
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
		etiquetaDesde= new JLabel("Desde :");
		etiquetaHasta= new JLabel("Hasta :");
		etiquetaIcono= new JLabel(new ImageIcon("recursos/iconos/custom-reports.png"));
		
		etiquetaHasta.setVisible(false);
		etiquetaDesde.setVisible(false);
		
		etiquetaTabla.setForeground(Estilos.colorLabels);
		etiquetaAtributo.setForeground(Estilos.colorLabels);
		//etiquetaTitulo.setForeground(Estilos.colorLabels);
		
		etiquetaTabla.setFont(Estilos.fontLabels);
		etiquetaAtributo.setFont(Estilos.fontLabels);
		etiquetaDesde.setFont(Estilos.fontSubtitulos);
		etiquetaHasta.setFont(Estilos.fontSubtitulos);
		etiquetaTitulo.setFont(Estilos.fontSubtitulos);
		
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

	
		GuiReportes al = new GuiReportes();
		JFrame a= new JFrame();
		a.add(al);
		a.setVisible(true);
		a.setSize(500,500);
		//a.setExtendedState(a.MAXIMIZED_BOTH);

		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
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
				
			}else if (evento.getSource()== tablas)
			{
				String item =(String) tablas.getSelectedItem(); 
				System.out.println(item);
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
				}
				if ( item.contains("Usuario"))
				{
					atributos.removeAllItems();
					atributos.addItem("genero");
					atributos.addItem("nivel_escolaridad");
					atributos.addItem("vinculo_univalle");
					atributos.addItem("tipo");
					habilitar.setVisible(true);
					fechas.setVisible(true);
					if(habilitar.isSelected())
					{
						etiquetaDesde.setVisible(true);
						etiquetaHasta.setVisible(true);
						campoFecha.setVisible(true);
						campoFecha2.setVisible(true);
					}
				}
			
			}else if (evento.getSource()== botonGenerarReporte)
			{
				//tablas.getSelectedItem();
				String ruta;
				JFileChooser archivos = new JFileChooser();
				archivos.setDialogType(JFileChooser.SAVE_DIALOG);
				archivos.setDragEnabled(true);
				archivos.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filtroPdf = new FileNameExtensionFilter("*.pdf", "pdf");
				archivos.setFileFilter(filtroPdf);
				int opcion = archivos.showSaveDialog(null);
					
				if(opcion == JFileChooser.APPROVE_OPTION)
				{
					File archivo = archivos.getSelectedFile();
					ruta = archivo.getPath();
					String rutaFinal = ruta+".pdf";
					
					String encabezado = campoTitulo.getText();
					boolean detalladoR = opcionReporte.isSelected(detallado.getModel());
					boolean totalesR = opcionReporte.isSelected(totales.getModel());
					boolean habilitarFechas = habilitar.isSelected();
					boolean usuario = tablas.getSelectedItem().equals("Usuarios");
					boolean areas = tablas.getSelectedItem().equals("Areas");
					
					String atributoSeleccionado = (String) atributos.getSelectedItem();
					String fechaBusqueda = (String)fechas.getSelectedItem();
					Date fecha= ((JSpinner.DateEditor) campoFecha.getEditor()).getModel().getDate();
				    SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
					String fechaInicioString = formatoFecha.format(fecha);
					System.out.println("Fecha inio " + fechaInicioString);
					fecha= ((JSpinner.DateEditor) campoFecha2.getEditor()).getModel().getDate();
					String fechaFinString = formatoFecha.format(fecha);
					System.out.println("Fecha fin " +fechaFinString);
					
					ControladorReportes controlador = new ControladorReportes();
					
					if(detalladoR)
					{
						if(usuario)
						{
							try
							{
								JasperPrint reporte;
								if(habilitarFechas)
								{
									reporte = controlador.reporteUsuariosAgrupados(atributoSeleccionado, fechaBusqueda, fechaInicioString, fechaFinString, encabezado);
									controlador.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controlador.reporteUsuariosAgrupados(atributoSeleccionado, encabezado);
									controlador.generarReporte(rutaFinal, reporte);
								}
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
								JasperPrint reporte = controlador.reporteAreasAgrupadas(encabezado);
								controlador.generarReporte(rutaFinal, reporte);
								
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed" +
										"tratando de llamarse el generar reporte de areas detalladas");
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
									reporte = controlador.reporteUsuariosAgrupadosTotales(atributoSeleccionado, fechaBusqueda, fechaInicioString, fechaFinString, encabezado);
									controlador.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controlador.reporteUsuariosAgrupadosTotales(atributoSeleccionado, encabezado);
									controlador.generarReporte(rutaFinal, reporte);
								}
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
								JasperPrint reporte = controlador.reporteAreasAgrupadasTotales(encabezado);
								controlador.generarReporte(rutaFinal, reporte);
								
							}catch(JRException e)
							{
								System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed" +
										"tratando de llamarse el generar reporte de areas totales");
								e.printStackTrace();
							}
						}
					}
					
					//reporte.setEncabezado(encabezado);
				}
				
				//System.out.println(controladorReporte.consultarUsuariosAgrupados((String) atributos.getSelectedItem()));
				JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
				//System.out.println("reporte generado");
				
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
