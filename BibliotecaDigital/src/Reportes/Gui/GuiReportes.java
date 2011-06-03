package Reportes.Gui;

import java.io.File;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import com.sun.org.apache.xml.internal.security.utils.resolver.ResourceResolverException;

public class GuiReportes extends JTabbedPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel PanelreportesBasicos,panelRepAvanzados; 
	
	private GridBagConstraints retricciones;
	
	private JCheckBox habilitar;
	
	private JScrollPane scroll;
	
	private JLabel etiquetaTabla, etiquetaAtributo, etiquetaTitulo;
	
	private JTextField campoTitulo;
	
	private JComboBox tablas , atributos ,fechas;
	
	private Button botonGenerarReporte;
	
	private JRadioButton detallado, totales;
	
	private ButtonGroup opcionReporte;
	
 
	ControladorReportes controladorReporte;
	
	//int cantCondicion=0;
	private JSpinner campoFecha; //estos dos son para el intervalo de reportes basicos
	private JSpinner campoFecha2;
	private JSpinner campoFecha3; //estos dos son para el intervaloe de reportes avanzados
	private JSpinner campoFecha4;
	
	private Vector<String> vectorContablasAvanzado;
	private Vector<String> vectorAtributosAvanzado;
	
	private JScrollPane scroll2;
	
	private JCheckBox habilitarAvanzado;
	
	private JComboBox tablasAvanzado;
	private JComboBox atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado;
	private JLabel etiquetaAtributoAvanzado;
	private JLabel etiquetaDesde;
	private JLabel etiquetaHasta;
	
	private GridBagConstraints retriccionesAvanzado;
	
	private JCheckBox habilitarPorDia;
	private JCheckBox habilitarPorMes;
	private JCheckBox habilitarPorAño;
	private JCheckBox habilitarPorHora;
	
	private Button botonAvanzado;

	public GuiReportes()
	{
		controladorReporte= new ControladorReportes();
		initComponents();
	}

	private void initComponents() {
	
		//-----------------------------BORDES----------
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Reportes Usuarios::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		TitledBorder borde2;
		
		borde2 = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Reportes Avanzados::");
		borde2.setTitleColor(Estilos.colorTitulo);
		borde2.setTitleFont(Estilos.fontTitulo);
		borde2.setTitleJustification(TitledBorder.LEFT);

		//--------------------------------------------------
		scroll= new JScrollPane();
						
		habilitar= new JCheckBox("Habilitar Periodo Con:");
		habilitar.setFont(Estilos.fontSubrayados);
		habilitar.addActionListener(new Manejador());
		
				
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		PanelreportesBasicos.setBorder(borde);
		PanelreportesBasicos.setBackground(Color.WHITE);
		
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
		//condicion= new JComboBox(vectorCondiciones);
		
		detallado = new JRadioButton("Informe detallado", true);
		detallado.addActionListener(new Manejador());
		totales = new JRadioButton("Informe solo con totales", false);
		totales.addActionListener(new Manejador());
		
		opcionReporte = new ButtonGroup();
		opcionReporte.add(detallado);
		opcionReporte.add(totales);
		
		campoTitulo = new JTextField(20);
		
		etiquetaTabla= new JLabel("TABLA: ");
		etiquetaAtributo= new JLabel("AGRUPADOS POR: ");
		etiquetaTitulo = new JLabel("TITULO DEL REPORTE: ");
		etiquetaDesde= new JLabel("Desde :");
		etiquetaHasta= new JLabel("Hasta :");
		etiquetaHasta.setVisible(false);
		etiquetaDesde.setVisible(false);
		
		//etiquetaCondicion= new JLabel("CONDICION");
		botonGenerarReporte= new Button("Generar Reporte");
		botonAvanzado= new Button("Generar Reporte");
		botonGenerarReporte.addActionListener(new Manejador());
		botonAvanzado.addActionListener(new Manejador());
		
		etiquetaTabla.setForeground(Estilos.colorLabels);
		etiquetaAtributo.setForeground(Estilos.colorLabels);
		etiquetaTitulo.setForeground(Estilos.colorLabels);
		//etiquetaCondicion.setForeground(Estilos.colorLabels);
		etiquetaTabla.setFont(Estilos.fontLabels);
		etiquetaAtributo.setFont(Estilos.fontLabels);
		etiquetaTitulo.setFont(Estilos.fontLabels);
		//etiquetaCondicion.setFont(Estilos.fontLabels);
		
		retricciones= new GridBagConstraints();
		retricciones.insets= new Insets(0, 0, 20, 40);
		retricciones.gridy=0;
		retricciones.anchor= GridBagConstraints.WEST;
		//retricciones.weightx=1.0;
		//retricciones.weighty=1.0;
		PanelreportesBasicos.add(etiquetaTabla,retricciones);
		PanelreportesBasicos.add(tablas,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaAtributo,retricciones);
		PanelreportesBasicos.add(atributos,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaTitulo, retricciones);
		PanelreportesBasicos.add(campoTitulo, retricciones);
		retricciones.gridy++;	
		PanelreportesBasicos.add(detallado, retricciones);
		PanelreportesBasicos.add(totales, retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(habilitar,retricciones);
		PanelreportesBasicos.add(fechas,retricciones);
		
		//PanelreportesBasicos.add(atributos,retricciones);
		
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
	    retricciones.gridy++;
	    retricciones.gridx=0;
	    PanelreportesBasicos.add(etiquetaDesde,retricciones);
	    retricciones.anchor=GridBagConstraints.EAST;
	    retricciones.gridx=0;
		PanelreportesBasicos.add(campoFecha,retricciones);
		retricciones.gridy++;
		retricciones.gridx=0;
		retricciones.anchor=GridBagConstraints.WEST;
	    
		PanelreportesBasicos.add(etiquetaHasta,retricciones);
		retricciones.gridx=0;
		retricciones.anchor=GridBagConstraints.EAST;
		PanelreportesBasicos.add(campoFecha2,retricciones);
		
		retricciones.gridx=1;
		retricciones.gridy++;
		retricciones.anchor=GridBagConstraints.WEST;
		retricciones.gridwidth=2;
		//PanelreportesBasicos.add(nuevaCondicion,retricciones);
		retricciones.gridy++;
		
		//PanelreportesBasicos.add(condicion,retricciones);
		retricciones.gridwidth=1;
		retricciones.gridy=retricciones.gridy+10;
		retricciones.gridx=1;
		
		retricciones.insets= new Insets(100, 0, 0, 0);		
		PanelreportesBasicos.add(botonGenerarReporte,retricciones);
	
		
		retricciones.insets= new Insets(0, 0, 0, 0);

		
		//******************************PANEL AVANZADO**********************************
		//--------------------------------------------------------
		panelRepAvanzados= new JPanel();
		scroll2= new JScrollPane();
		
		
		vectorContablasAvanzado= new Vector<String>();
		vectorContablasAvanzado.add("Descargas");
		vectorContablasAvanzado.add("Consultas");
		vectorContablasAvanzado.add("Catalogar");
		vectorContablasAvanzado.add("Modificar");
		
		vectorAtributosAvanzado= new Vector<String>();
		vectorAtributosAvanzado.add("Usuario que mas ...");
		vectorAtributosAvanzado.add("Documento que mas..");
		vectorAtributosAvanzado.add("Cantidad De ..");
		
		//vectorAtributosAvanzado.add("");
	
	
		habilitarAvanzado= new JCheckBox("habilitar periodo");
		habilitarPorDia= new JCheckBox("Generar Por Dia");
		habilitarPorMes= new JCheckBox("Generar Por Mes");
		habilitarPorAño= new JCheckBox("Generar Por Año");
		habilitarPorHora= new JCheckBox("Generar Por Hora");
		habilitarAvanzado.setFont(Estilos.fontSubrayados);
		
		
		panelRepAvanzados= new JPanel(new GridBagLayout());
		panelRepAvanzados.setBorder(borde2);
		panelRepAvanzados.setBackground(Color.WHITE);
		
		tablasAvanzado = new JComboBox(vectorContablasAvanzado);
		atributosAvanzado = new JComboBox(vectorAtributosAvanzado);
		//condicionAvanzado= new JComboBox(vectorCondicionesAvanzado);
		etiquetaTablaAvanzado= new JLabel("TABLA");
		etiquetaAtributoAvanzado= new JLabel("CONSULTAR POR   :");
		//etiquetaCondicionAvanzado= new JLabel("CONDICION");
		//botonGenerarReporteAvanzado= new Button("Generar Reporte");
		etiquetaTablaAvanzado.setForeground(Estilos.colorLabels);
		etiquetaAtributoAvanzado.setForeground(Estilos.colorLabels);
		//etiquetaCondicionAvanzado.setForeground(Estilos.colorLabels);
		etiquetaTablaAvanzado.setFont(Estilos.fontLabels);
		etiquetaAtributoAvanzado.setFont(Estilos.fontLabels);
		//etiquetaCondicionAvanzado.setFont(Estilos.fontLabels);
		//nuevaCondicionAvanzado= new Button("Añadir condicion");
		//nuevaCondicionAvanzado.addActionListener(new Manejador());
		
		retriccionesAvanzado= new GridBagConstraints();
		retriccionesAvanzado.insets= new Insets(0, 0, 20, 40);
		retriccionesAvanzado.gridy=0;
		retriccionesAvanzado.anchor= GridBagConstraints.WEST;
		//retriccionesAvanzado.weightx=1.0;
		//retriccionesAvanzado.weighty=1.0;
		panelRepAvanzados.add(etiquetaTablaAvanzado,retriccionesAvanzado);
		panelRepAvanzados.add(tablasAvanzado,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		panelRepAvanzados.add(etiquetaAtributoAvanzado,retriccionesAvanzado);
		panelRepAvanzados.add(atributosAvanzado,retriccionesAvanzado);

		retriccionesAvanzado.gridy++;
		
		panelRepAvanzados.add(habilitarPorAño,retriccionesAvanzado);
		panelRepAvanzados.add(habilitarPorMes,retriccionesAvanzado);
		
		retriccionesAvanzado.gridy++;
		
		panelRepAvanzados.add(habilitarPorDia,retriccionesAvanzado);
		panelRepAvanzados.add(habilitarPorHora,retriccionesAvanzado);
		
		
		retriccionesAvanzado.gridy++;
		
		panelRepAvanzados.add(habilitarAvanzado,retriccionesAvanzado);
		//panelRepAvanzados.add(new JComboBox(vectorFechasAvanzado),retriccionesAvanzado);
		
		//PanelreportesBasicos.add(atributos,retricciones);
		
		//Crear spinner para la fecha3.
		SpinnerModel modeloFecha3 = new SpinnerDateModel();
		campoFecha3 = new JSpinner(modeloFecha3);
	    campoFecha3.setFont(Estilos.fontLabels);
	    campoFecha3.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha3 = new JSpinner.DateEditor(campoFecha3,"yyyy-MM-dd");
		campoFecha3.setEditor(spinnerFecha3);
	    ((JSpinner.DateEditor) campoFecha3.getEditor()).getTextField().setEditable(false);
	  //Crear spinner para la fecha4.
		SpinnerModel modeloFecha4 = new SpinnerDateModel();
		campoFecha4 = new JSpinner(modeloFecha4);
	    campoFecha4.setFont(Estilos.fontLabels);
	    campoFecha4.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha4 = new JSpinner.DateEditor(campoFecha4,"yyyy-MM-dd");
		campoFecha4.setEditor(spinnerFecha4);
	    ((JSpinner.DateEditor) campoFecha4.getEditor()).getTextField().setEditable(false);
	    
	    retriccionesAvanzado.gridy++;
	    retriccionesAvanzado.gridx=0;
	    panelRepAvanzados.add(new JLabel("Desde :"),retriccionesAvanzado);
	    retriccionesAvanzado.anchor=GridBagConstraints.EAST;
	    retriccionesAvanzado.gridx=0;
	    panelRepAvanzados.add(campoFecha3,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.WEST;
	    
		panelRepAvanzados.add(new JLabel("Hasta :"),retriccionesAvanzado);
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.EAST;
		panelRepAvanzados.add(campoFecha4,retriccionesAvanzado);
		
		retriccionesAvanzado.gridx=1;
		retriccionesAvanzado.gridy++;
		retriccionesAvanzado.anchor=GridBagConstraints.WEST;
		retriccionesAvanzado.gridwidth=2;
		//panelRepAvanzados.add(nuevaCondicionAvanzado,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		
		//PanelreportesBasicos.add(condicion,retricciones);
		retriccionesAvanzado.gridwidth=1;
		retriccionesAvanzado.gridy=retriccionesAvanzado.gridy+10;
		retriccionesAvanzado.gridx=1;
		
		retricciones.insets= new Insets(100, 0, 0, 0);		
		panelRepAvanzados.add(botonAvanzado,retriccionesAvanzado);
	
		
		retriccionesAvanzado.insets= new Insets(0, 0, 0, 0);
		retricciones.insets= new Insets(0, 0, 0, 0);
		

		
		scroll.setViewportView(PanelreportesBasicos);
		scroll2.setViewportView(panelRepAvanzados);
		
		addTab("Reportes Basicos",scroll );
		addTab("Reportes Avanzados",scroll2 );
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
									reporte = controlador.reporteUsuariosAgrupados(atributoSeleccionado, encabezado, fechaBusqueda, fechaInicioString, fechaFinString);
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
									reporte = controlador.reporteUsuariosAgrupadosTotales(atributoSeleccionado, encabezado, fechaBusqueda, fechaInicioString, fechaFinString);
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
			if(evento.getSource()==botonAvanzado){
				JOptionPane.showMessageDialog(null, "En construccion","Lo sentimos", JOptionPane.INFORMATION_MESSAGE);
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
