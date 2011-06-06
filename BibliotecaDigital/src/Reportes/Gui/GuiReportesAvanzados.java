package Reportes.Gui;


import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

import Reportes.Controlador.ControladorReportes;
import Reportes.Gui.GuiReportes.Manejador;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiReportesAvanzados extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> vectorContablasAvanzado;
	private Vector<String> vectorAtributosAvanzado;
	

	private JPanel panelPrincipal, panelOpcionesReportes, panelDescargasConsultas, panelDocumento;
	
	private JCheckBox habilitarPeriodoDescargasConsultas, habilitarPorMesDescargasConsultas, habilitarPorAnioDescargasConsultas, habilitarPorHoraDescargasConsultas;
	
	private JComboBox tablasAvanzado, atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado, etiquetaAtributoAvanzado, etiquetaTituloDescargasConsultas,
				   etiquetaTituloDocumento, etiquetaIntroDocumento, etiquetaIntroDescargasConsultas;
	
	private JTextField campoTituloDescargasConsultas, campoTituloDocumento;
	
	private JTextArea campoIntroDescargasConsultas, campoIntroDocumento;
	
	private JScrollPane scrollIntroDescargasConsultas, scrollIntroDocumento;
	
	private GridBagConstraints retriccionesAvanzado;
	
	private JSpinner campoFechaDesdeDescargas, campoFechaDesdeDocumento, campoFechaHastaDescargas, campoFechaHastaDocumento; //estos dos son para el intervaloe de reportes avanzados
	
	private Button botonDescargasConsultasGenerar, botonDocumentoGenerar;
	
	private JButton botonDescargasConsultas, botonDocumento;
	
	private JRadioButton descargasRadio, consultasRadio, documentosRadio, usuariosRadio, detallado, totales, ascendente, descendente;
	
	private ButtonGroup opcionTipo, opcionInformacion, opcionReporte, opcionOrden;
	
	ControladorReportes controladorReporte;
	
	public GuiReportesAvanzados() {
		controladorReporte= new ControladorReportes();
		initComponents();
	}
	
	private void initComponents()
	{

		vectorContablasAvanzado= new Vector<String>();
		vectorContablasAvanzado.add("Descargas");
		vectorContablasAvanzado.add("Consultas");
		vectorContablasAvanzado.add("Catalogar");
		vectorContablasAvanzado.add("Modificar");
		
		vectorAtributosAvanzado= new Vector<String>();
		vectorAtributosAvanzado.add("Usuario que mas ...");
		vectorAtributosAvanzado.add("Documento que mas..");
		vectorAtributosAvanzado.add("Cantidad De ..");
		
		inicializarLabels();
		inicializarComboBox();
		inicializarCheckBox();
		inicializarButtons();
		inicializarRadioButtons();
		inicializarButtonsGroup();
		inicializarTexts();
		inicializarSpinners();
		inicializarPanels();
		
		
	    panelPrincipal.add(panelOpcionesReportes, BorderLayout.NORTH);
	    //---- INSERTAR COMPONENTES EN PANEL ---
/*
		retriccionesAvanzado= new GridBagConstraints();
		retriccionesAvanzado.insets= new Insets(0, 0, 20, 40);
		retriccionesAvanzado.gridy=0;
		retriccionesAvanzado.anchor= GridBagConstraints.WEST;
		panelPrincipal.add(etiquetaTablaAvanzado,retriccionesAvanzado);
		panelPrincipal.add(tablasAvanzado,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		panelPrincipal.add(etiquetaAtributoAvanzado,retriccionesAvanzado);
		panelPrincipal.add(atributosAvanzado,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		panelPrincipal.add(habilitarPorAño,retriccionesAvanzado);
		panelPrincipal.add(habilitarPorMes,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		panelPrincipal.add(habilitarPorDia,retriccionesAvanzado);
		panelPrincipal.add(habilitarPorHora,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		panelPrincipal.add(habilitarAvanzado,retriccionesAvanzado);
	    
	    retriccionesAvanzado.gridy++;
	    retriccionesAvanzado.gridx=0;
	    panelPrincipal.add(new JLabel("Desde :"),retriccionesAvanzado);
	    retriccionesAvanzado.anchor=GridBagConstraints.EAST;
	    retriccionesAvanzado.gridx=0;
	    panelPrincipal.add(campoFecha3,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.WEST;
	    
		panelPrincipal.add(new JLabel("Hasta :"),retriccionesAvanzado);
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.EAST;
		panelPrincipal.add(campoFecha4,retriccionesAvanzado);
		
		retriccionesAvanzado.gridx=1;
		retriccionesAvanzado.gridy++;
		retriccionesAvanzado.anchor=GridBagConstraints.WEST;
		retriccionesAvanzado.gridwidth=2;
		//panelPrincipal.add(nuevaCondicionAvanzado,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		
		//PanelreportesBasicos.add(condicion,retricciones);
		retriccionesAvanzado.gridwidth=1;
		retriccionesAvanzado.gridy=retriccionesAvanzado.gridy+10;
		retriccionesAvanzado.gridx=1;
		
				
		//panelPrincipal.add(botonAvanzado,retriccionesAvanzado);
	
		
		retriccionesAvanzado.insets= new Insets(0, 0, 0, 0);
		*/
		this.setLayout(new BorderLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
		
	}

	private void inicializarSpinners() {
		
		//Crear spinner para la fecha desde de descargas
		SpinnerModel modeloFechaDesdeDescargas = new SpinnerDateModel();
		campoFechaDesdeDescargas = new JSpinner(modeloFechaDesdeDescargas);
	    campoFechaDesdeDescargas.setFont(Estilos.fontLabels);
	    campoFechaDesdeDescargas.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaDD = new JSpinner.DateEditor(campoFechaDesdeDescargas,"yyyy-MM-dd");
		campoFechaDesdeDescargas.setEditor(spinnerFechaDD);
	    ((JSpinner.DateEditor) campoFechaDesdeDescargas.getEditor()).getTextField().setEditable(false);
	    
	    //Crear spinner para la fecha hasta de descargas
		SpinnerModel modeloFechaHastaDescargas = new SpinnerDateModel();
		campoFechaHastaDescargas = new JSpinner(modeloFechaHastaDescargas);
	    campoFechaHastaDescargas.setFont(Estilos.fontLabels);
	    campoFechaHastaDescargas.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHD = new JSpinner.DateEditor(campoFechaHastaDescargas,"yyyy-MM-dd");
		campoFechaHastaDescargas.setEditor(spinnerFechaHD);
	    ((JSpinner.DateEditor) campoFechaHastaDescargas.getEditor()).getTextField().setEditable(false);
	    
	  //Crear spinner para la fecha desde de descargas
		SpinnerModel modeloFechaDesdeDocumento = new SpinnerDateModel();
		campoFechaDesdeDocumento = new JSpinner(modeloFechaDesdeDocumento);
	    campoFechaDesdeDocumento.setFont(Estilos.fontLabels);
	    campoFechaDesdeDocumento.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaDDo = new JSpinner.DateEditor(campoFechaDesdeDocumento,"yyyy-MM-dd");
		campoFechaDesdeDocumento.setEditor(spinnerFechaDDo);
	    ((JSpinner.DateEditor) campoFechaDesdeDocumento.getEditor()).getTextField().setEditable(false);
	    
	    //Crear spinner para la fecha hasta de descargas
		SpinnerModel modeloFechaHastaDocumento = new SpinnerDateModel();
		campoFechaHastaDocumento = new JSpinner(modeloFechaHastaDocumento);
	    campoFechaHastaDocumento.setFont(Estilos.fontLabels);
	    campoFechaHastaDocumento.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHDo = new JSpinner.DateEditor(campoFechaHastaDocumento,"yyyy-MM-dd");
		campoFechaHastaDocumento.setEditor(spinnerFechaHDo);
	    ((JSpinner.DateEditor) campoFechaHastaDocumento.getEditor()).getTextField().setEditable(false);
		
	}

	private void inicializarTexts() {
		
		campoTituloDescargasConsultas = new JTextField("Reporte", 20);
		campoTituloDocumento = new JTextField("Reporte", 20);
		
		campoIntroDescargasConsultas = new JTextArea(8,50);
		campoIntroDescargasConsultas.setLineWrap(true);
		scrollIntroDescargasConsultas = new JScrollPane(campoIntroDescargasConsultas);
		campoIntroDocumento = new JTextArea(8,50);
		campoIntroDocumento.setLineWrap(true);
		scrollIntroDocumento = new JScrollPane(campoIntroDocumento);
		
	}

	private void inicializarButtonsGroup() {
		
		opcionTipo = new ButtonGroup();
		opcionTipo.add(detallado);
		opcionTipo.add(totales);
		
		opcionInformacion = new ButtonGroup();
		opcionInformacion.add(documentosRadio);
		opcionInformacion.add(usuariosRadio);
		
		opcionReporte = new ButtonGroup();
		opcionReporte.add(descargasRadio);
		opcionReporte.add(consultasRadio);
		
		opcionOrden = new ButtonGroup();
		opcionOrden.add(ascendente);
		opcionOrden.add(descendente);
		
	}

	private void inicializarRadioButtons() {
		
		descargasRadio = new JRadioButton("Descargas", true);
		consultasRadio = new JRadioButton("Consultas", false);
		
		documentosRadio = new JRadioButton("Documentos", true);		
		usuariosRadio = new JRadioButton("Usuarios", false);
		
		detallado = new JRadioButton("Detallado", true);		
		totales = new JRadioButton("Solo totales", false);
		
		ascendente = new JRadioButton("Ascendente", true);		
		descendente = new JRadioButton("Descendente", false);

	}

	private void inicializarPanels() {
		
		panelPrincipal= new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.WHITE);
		
		panelOpcionesReportes = new JPanel (new GridBagLayout());
		inicializarPanelOpcionesReportes();
		
		panelDescargasConsultas = new JPanel(new GridBagLayout());
		inicializarPanelDescargasConsultas();
		
		panelDocumento = new JPanel(new GridBagLayout());
		inicializarPanelDocumento();
		
	}

	private void inicializarPanelOpcionesReportes() {
		
		panelOpcionesReportes.setBackground(Estilos.colorFondoPanel);
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.anchor = GridBagConstraints.NORTHWEST;
		restricciones.insets = new Insets(4, 5, 4, 5);
		restricciones.gridx=0;
		restricciones.weightx=0.0;
		
		panelOpcionesReportes.add(botonDescargasConsultas, restricciones);
		restricciones.gridx=1;
		restricciones.weightx=0.3;
		panelOpcionesReportes.add(botonDocumento, restricciones);
		
	}

	private void inicializarPanelDocumento() {
		
		
	}

	private void inicializarPanelDescargasConsultas() {
		
		//--INGRESAR COMPONENTES AL PANEL--
		
		panelDescargasConsultas.setBackground(Color.WHITE);
		
		//Labels indicativas
		
		JLabel tipoReporte, contenido, orden, formato, agrupado, desde, hasta;
		
		tipoReporte = new JLabel("Reporte de: ");
		tipoReporte.setFont(Estilos.fontSubrayados);
		contenido = new JLabel("Que contenga sólo información de: ");
		orden = new JLabel("Orden");
		formato = new JLabel("Formato");
		agrupado = new JLabel("Agrupado");
		desde = new JLabel("Desde: ");
		hasta = new JLabel("Hasta: ");
		
		//Panel para opciones
		
		JPanel opcionesReporte = new JPanel(new GridBagLayout());
		
		TitledBorder bordeOpciones = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Opciones reporte");
		bordeOpciones.setTitleJustification(TitledBorder.LEFT);
		opcionesReporte.setBorder(bordeOpciones);
		
		GridBagConstraints restriccionesOpciones = new GridBagConstraints();
		restriccionesOpciones.anchor = GridBagConstraints.WEST;
		restriccionesOpciones.insets = new Insets(4, 4, 4, 4);
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy = 0;
		
		opcionesReporte.add(orden, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(ascendente, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(descendente, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.gridy = 0;
		
		opcionesReporte.add(formato, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(detallado, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(totales, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 2;
		restriccionesOpciones.gridy = 0;
		
		opcionesReporte.add(agrupado, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(habilitarPorHoraDescargasConsultas);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(habilitarPorMesDescargasConsultas);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(habilitarPorAnioDescargasConsultas);
		
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth=2;
		
		opcionesReporte.add(habilitarPeriodoDescargasConsultas, restriccionesOpciones);
		restriccionesOpciones.gridwidth=1;
		restriccionesOpciones.insets = new Insets(4, 20, 4, 4);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(desde, restriccionesOpciones);
		restriccionesOpciones.gridx = 1;
		opcionesReporte.add(campoFechaDesdeDescargas, restriccionesOpciones);
		
		
		//Fin panel para opciones
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.anchor= GridBagConstraints.WEST;
		
		
		
		
		
		
	}

	private void inicializarButtons() {

		botonDescargasConsultasGenerar = new Button("Generar Reporte");
		botonDocumentoGenerar = new Button("Generar Reporte");
		
		//botonDescargas.addActionListener(new Manejador());
		//botonConsultas.addActionListener(new Manejador());
		//botonDocumento.addActionListener(new Manejador());
		
		//Etiquetas del menu.
		botonDescargasConsultas = new JButton("    Descargas-Consultas    ");
		botonDescargasConsultas.setIcon(new ImageIcon("recursos/iconos/big/reports.png"));
		botonDescargasConsultas.setVerticalTextPosition(JLabel.BOTTOM);
		botonDescargasConsultas.setHorizontalTextPosition(JLabel.CENTER);
		
		
		
		botonDocumento = new JButton("    Documentos    ");
		botonDocumento.setIcon(new ImageIcon("recursos/iconos/big/edit_document.png"));
		botonDocumento.setVerticalTextPosition(JLabel.BOTTOM);
		botonDocumento.setHorizontalTextPosition(JLabel.CENTER);
		//Fin etiquetas del menu
		
		
	}

	private void inicializarCheckBox() {
		
		habilitarPeriodoDescargasConsultas = new JCheckBox("habilitar periodo");
		habilitarPeriodoDescargasConsultas.setFont(Estilos.fontSubrayados);
		
		habilitarPorMesDescargasConsultas= new JCheckBox("Por Mes");
		habilitarPorAnioDescargasConsultas= new JCheckBox("Por Año");
		habilitarPorHoraDescargasConsultas= new JCheckBox("Por Hora");
		
		
	}

	private void inicializarComboBox() {
		
		tablasAvanzado = new JComboBox(vectorContablasAvanzado);
		atributosAvanzado = new JComboBox(vectorAtributosAvanzado);
		
	}

	private void inicializarLabels() {
		

		
		etiquetaTituloDescargasConsultas = new JLabel("Título: ");
		etiquetaTituloDescargasConsultas.setForeground(Estilos.colorLabels);
		etiquetaTituloDescargasConsultas.setFont(Estilos.fontLabels);
		etiquetaIntroDescargasConsultas = new JLabel("Introducción reporte: ");
		etiquetaIntroDescargasConsultas.setForeground(Estilos.colorLabels);
		etiquetaIntroDescargasConsultas.setFont(Estilos.fontLabels);
		
		etiquetaTituloDocumento = new JLabel("Título: ");
		etiquetaTituloDocumento.setForeground(Estilos.colorLabels);
		etiquetaTituloDocumento.setFont(Estilos.fontLabels);
		etiquetaIntroDocumento = new JLabel("Introducción reporte: ");
		etiquetaIntroDocumento.setForeground(Estilos.colorLabels);
		etiquetaIntroDocumento.setFont(Estilos.fontLabels);		
		
		etiquetaTablaAvanzado= new JLabel("TABLA");
		etiquetaTablaAvanzado.setForeground(Estilos.colorLabels);
		etiquetaTablaAvanzado.setFont(Estilos.fontLabels);
		
		etiquetaAtributoAvanzado= new JLabel("CONSULTAR POR   :");
		etiquetaAtributoAvanzado.setForeground(Estilos.colorLabels);
		etiquetaAtributoAvanzado.setFont(Estilos.fontLabels);
		
	}


}
