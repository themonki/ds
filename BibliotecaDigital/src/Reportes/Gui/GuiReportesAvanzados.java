package Reportes.Gui;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiReportesAvanzados extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> vectorContablasAvanzado;
	private Vector<String> vectorAtributosAvanzado;
	

	private JPanel panelPrincipal, panelOpcionesReportes, panelDescargasConsultas, panelDocumento, panelOpcionesReporteDescargas,
				   panelOpcionesReportesDocumento;
	
	private JCheckBox habilitarPeriodoDescargasConsultas, habilitarPeriodoDocumento;
	
	private JComboBox tablasAvanzado, atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado, etiquetaAtributoAvanzado, etiquetaTituloDescargasConsultas,
				   etiquetaTituloDocumento, etiquetaIntroDocumento, etiquetaIntroDescargasConsultas,
				   ordenDescargas, ordenDocumento, mostrarDescargas, mostrarDocumento;
	
	private JTextField campoTituloDescargasConsultas, campoTituloDocumento;
	
	private JTextArea campoIntroDescargasConsultas, campoIntroDocumento;
	
	private JScrollPane scrollIntroDescargasConsultas, scrollIntroDocumento, scrollPanel;
	
	private JSpinner campoFechaDesdeDescargas, campoFechaDesdeDocumento, campoFechaHastaDescargas, campoFechaHastaDocumento; //estos dos son para el intervaloe de reportes avanzados
	
	private Button botonDescargasConsultasGenerar, botonDocumentoGenerar;
	
	private JButton botonDescargasConsultas, botonDocumento;
	
	private JRadioButton descargasRadio, consultasRadio, areasRadio, usuariosRadio, fechaRadio, 
						 detallado, totales, ascendente, descendente,
						 detalladoDocumento, totalesDocumento, ascendenteDocumento, descendenteDocumento,
						 habilitarPorMesDescargasConsultas, habilitarPorAnioDescargasConsultas,
						 habilitarPorDiaDescargasConsultas, habilitarPorMesDocumento, habilitarPorAnioDocumento,
						 habilitarPorDiaDocumento, areasRadioDocumento, catalogadorRadio, fechaRadioDocumento;
	
	private ButtonGroup opcionTipo, opcionAgrupado, opcionReporte, opcionOrden, menu,
						opcionTipoDocumento, opcionOrdenCatalogadores,
						opcionMostrarDescargas, opcionMostrarDocumento, opcionAgrupadoDocumento;
	
	private ControladorReportes controladorReporte;
	
	private Manejador manejador;
	
	
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
		
		manejador = new Manejador();
		
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
	    scrollPanel = new JScrollPane();
	    scrollPanel.setViewportView(panelDescargasConsultas);
	    panelPrincipal.add(scrollPanel, BorderLayout.CENTER);
	    
	    
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
	    campoFechaDesdeDescargas.setEnabled(false);
	    
	    //Crear spinner para la fecha hasta de descargas
		SpinnerModel modeloFechaHastaDescargas = new SpinnerDateModel();
		campoFechaHastaDescargas = new JSpinner(modeloFechaHastaDescargas);
	    campoFechaHastaDescargas.setFont(Estilos.fontLabels);
	    campoFechaHastaDescargas.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHD = new JSpinner.DateEditor(campoFechaHastaDescargas,"yyyy-MM-dd");
		campoFechaHastaDescargas.setEditor(spinnerFechaHD);
	    ((JSpinner.DateEditor) campoFechaHastaDescargas.getEditor()).getTextField().setEditable(false);
	    campoFechaHastaDescargas.setEnabled(false);
	    
	  //Crear spinner para la fecha desde de descargas
		SpinnerModel modeloFechaDesdeDocumento = new SpinnerDateModel();
		campoFechaDesdeDocumento = new JSpinner(modeloFechaDesdeDocumento);
	    campoFechaDesdeDocumento.setFont(Estilos.fontLabels);
	    campoFechaDesdeDocumento.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaDDo = new JSpinner.DateEditor(campoFechaDesdeDocumento,"yyyy-MM-dd");
		campoFechaDesdeDocumento.setEditor(spinnerFechaDDo);
	    ((JSpinner.DateEditor) campoFechaDesdeDocumento.getEditor()).getTextField().setEditable(false);
	    campoFechaDesdeDocumento.setEnabled(false);
	    
	    //Crear spinner para la fecha hasta de descargas
		SpinnerModel modeloFechaHastaDocumento = new SpinnerDateModel();
		campoFechaHastaDocumento = new JSpinner(modeloFechaHastaDocumento);
	    campoFechaHastaDocumento.setFont(Estilos.fontLabels);
	    campoFechaHastaDocumento.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFechaHDo = new JSpinner.DateEditor(campoFechaHastaDocumento,"yyyy-MM-dd");
		campoFechaHastaDocumento.setEditor(spinnerFechaHDo);
	    ((JSpinner.DateEditor) campoFechaHastaDocumento.getEditor()).getTextField().setEditable(false);
	    campoFechaHastaDocumento.setEnabled(false);
		
	}

	private void inicializarTexts() {
		
		campoTituloDescargasConsultas = new JTextField("Título del reporte", 20);
		campoTituloDocumento = new JTextField("Título del reporte", 20);
		
		campoIntroDescargasConsultas = new JTextArea(5,50);
		campoIntroDescargasConsultas.setLineWrap(true);
		campoIntroDescargasConsultas.addKeyListener(manejador);
		scrollIntroDescargasConsultas = new JScrollPane(campoIntroDescargasConsultas);
		campoIntroDocumento = new JTextArea(5,50);
		campoIntroDocumento.setLineWrap(true);
		campoIntroDocumento.addKeyListener(manejador);
		scrollIntroDocumento = new JScrollPane(campoIntroDocumento);
		
	}

	private void inicializarButtonsGroup() {
		
		opcionTipo = new ButtonGroup();
		opcionTipo.add(detallado);
		opcionTipo.add(totales);
		
		opcionAgrupado = new ButtonGroup();
		opcionAgrupado.add(usuariosRadio);
		opcionAgrupado.add(areasRadio);
		opcionAgrupado.add(fechaRadio);
		
		opcionReporte = new ButtonGroup();
		opcionReporte.add(descargasRadio);
		opcionReporte.add(consultasRadio);
		
		opcionOrden = new ButtonGroup();
		opcionOrden.add(ascendente);
		opcionOrden.add(descendente);
		
		opcionTipoDocumento = new ButtonGroup();
		opcionTipoDocumento.add(detalladoDocumento);
		opcionTipoDocumento.add(totalesDocumento);
		
		opcionOrdenCatalogadores = new ButtonGroup();
		opcionOrdenCatalogadores.add(ascendenteDocumento);
		opcionOrdenCatalogadores.add(descendenteDocumento);
		
		opcionMostrarDescargas = new ButtonGroup();
		opcionMostrarDescargas.add(habilitarPorDiaDescargasConsultas);
		opcionMostrarDescargas.add(habilitarPorMesDescargasConsultas);
		opcionMostrarDescargas.add(habilitarPorAnioDescargasConsultas);
		
		opcionMostrarDocumento = new ButtonGroup();
		opcionMostrarDocumento.add(habilitarPorDiaDocumento);
		opcionMostrarDocumento.add(habilitarPorMesDocumento);
		opcionMostrarDocumento.add(habilitarPorAnioDocumento);
		
		opcionAgrupadoDocumento = new ButtonGroup();
		opcionAgrupadoDocumento.add(catalogadorRadio);
		opcionAgrupadoDocumento.add(areasRadioDocumento);
		opcionAgrupadoDocumento.add(fechaRadioDocumento);
		
		menu = new ButtonGroup();
		menu.add(botonDescargasConsultas);
		menu.add(botonDocumento);
		
	}

	private void inicializarRadioButtons() {
		
		descargasRadio = new JRadioButton("Descargas", true);
		consultasRadio = new JRadioButton("Consultas", false);
		
		areasRadio = new JRadioButton("Áreas", false);
		areasRadio.addActionListener(manejador);
		fechaRadio = new JRadioButton("Fecha", false);
		fechaRadio.addActionListener(manejador);
		usuariosRadio = new JRadioButton("Usuarios", true);
		usuariosRadio.addActionListener(manejador);
		
		detallado = new JRadioButton("Detallado", true);
		detallado.addActionListener(manejador);
		totales = new JRadioButton("Solo totales", false);
		//totales.setEnabled(false);
		//totales.addActionListener(manejador);
		
		ascendente = new JRadioButton("Ascendente", true);	
		ascendente.setVisible(false);
		descendente = new JRadioButton("Descendente", false);
		descendente.setVisible(false);
		
		detalladoDocumento = new JRadioButton("Detallado", true);		
		detalladoDocumento.addActionListener(manejador);
		totalesDocumento = new JRadioButton("Solo totales", false);
		//totalesDocumento.setEnabled(false);
		//totalesDocumento.addActionListener(manejador);
		
		ascendenteDocumento = new JRadioButton("Ascendente", false);
		ascendenteDocumento.setVisible(false);
		descendenteDocumento = new JRadioButton("Descendente", false);
		descendenteDocumento.setVisible(false);
		descendenteDocumento.setSelected(true);
		
		habilitarPorAnioDescargasConsultas = new JRadioButton("Por año", false);
		habilitarPorAnioDescargasConsultas.setVisible(false);
		habilitarPorMesDescargasConsultas = new JRadioButton("Por mes", false);
		habilitarPorMesDescargasConsultas.setVisible(false);
		habilitarPorDiaDescargasConsultas = new JRadioButton("Por día", true);
		habilitarPorDiaDescargasConsultas.setVisible(false);
		
		habilitarPorAnioDocumento = new JRadioButton("Por año", false);
		habilitarPorAnioDocumento.setVisible(false);
		habilitarPorMesDocumento = new JRadioButton("Por mes", false);
		habilitarPorMesDocumento.setVisible(false);
		habilitarPorDiaDocumento = new JRadioButton("Por día", true);
		habilitarPorDiaDocumento.setVisible(false);

		areasRadioDocumento = new JRadioButton("Áreas", false);
		areasRadioDocumento.addActionListener(manejador);
		fechaRadioDocumento = new JRadioButton("Fecha", false);
		fechaRadioDocumento.addActionListener(manejador);
		catalogadorRadio = new JRadioButton("Catalogadores", true);
		catalogadorRadio.addActionListener(manejador);
	}

	private void inicializarPanels() {
		
		panelPrincipal= new JPanel(new BorderLayout());
		
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
		
		//--Labels indicatorias
		JLabel tipoReporte,agrupado, formato, desde, hasta;
		
		tipoReporte = new JLabel("Reporte de documentos catalogados");
		tipoReporte.setFont(Estilos.fontLabels);
		tipoReporte.setForeground(Estilos.colorSubtitulo);
		
		agrupado = new JLabel("Agrupado por: ");
		agrupado.setFont(Estilos.fontSubrayados);
		
		formato = new JLabel("Formato ");
		formato.setFont(Estilos.fontSubrayados);
		
		desde = new JLabel("Desde: ");
		hasta = new JLabel("Hasta: ");
		
		//-----Fin labels indicatorias
		
		//---panel para opciones reportes
		panelOpcionesReportesDocumento = new JPanel(new GridBagLayout());
		
		TitledBorder bordeOpciones = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Opciones reporte");
		bordeOpciones.setTitleJustification(TitledBorder.LEFT);
		bordeOpciones.setTitleFont(Estilos.fontSubtitulos);
		bordeOpciones.setTitleColor(Estilos.colorSubtitulo);
		panelOpcionesReportesDocumento.setBorder(bordeOpciones);
		
		
		GridBagConstraints restriccionesOpciones = new GridBagConstraints();
		restriccionesOpciones.anchor = GridBagConstraints.WEST;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.insets = new Insets(4, 20, 4, 20);		
		restriccionesOpciones.gridwidth = 1;
		
		panelOpcionesReportesDocumento.add(formato, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReportesDocumento.add(detalladoDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReportesDocumento.add(totalesDocumento, restriccionesOpciones);
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 1;
		
		panelOpcionesReportesDocumento.add(ordenDocumento, restriccionesOpciones);
		restriccionesOpciones.insets.left = 40;
		restriccionesOpciones.gridy++;
		panelOpcionesReportesDocumento.add(ascendenteDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReportesDocumento.add(descendenteDocumento, restriccionesOpciones);
		
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 2;
		panelOpcionesReportesDocumento.add(mostrarDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReportesDocumento.add(habilitarPorDiaDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReportesDocumento.add(habilitarPorMesDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReportesDocumento.add(habilitarPorAnioDocumento, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth = 3;
		panelOpcionesReportesDocumento.add(habilitarPeriodoDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth =2;
		restriccionesOpciones.insets.left= 40;
		panelOpcionesReportesDocumento.add(desde, restriccionesOpciones);
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 1;
		panelOpcionesReportesDocumento.add(campoFechaDesdeDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReportesDocumento.add(hasta, restriccionesOpciones);
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 1;
		panelOpcionesReportesDocumento.add(campoFechaHastaDocumento, restriccionesOpciones);
		
		//---fin panel de opciones reprotes
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.anchor = GridBagConstraints.WEST;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.gridwidth = 4;
		restricciones.insets = new Insets(4, 20, 4, 20);
		
		panelDocumento.add(tipoReporte, restricciones);
		
		restricciones.gridy++;
		panelDocumento.add(agrupado, restricciones);
		restricciones.gridwidth = 1;
		restricciones.gridy++;
		panelDocumento.add(catalogadorRadio, restricciones);
		restricciones.gridx = 1;
		panelDocumento.add(areasRadioDocumento, restricciones);
		restricciones.gridx = 2;
		panelDocumento.add(fechaRadioDocumento, restricciones);
		
		restricciones.gridx = 0;
		restricciones.gridy++;
		restricciones.gridwidth = 4;
		
		panelDocumento.add(panelOpcionesReportesDocumento, restricciones);
		restricciones.gridwidth = 2;
		restricciones.gridy++; 
		panelDocumento.add(etiquetaTituloDocumento, restricciones);
		restricciones.gridx = 1;
		panelDocumento.add(campoTituloDocumento, restricciones);
		
		restricciones.gridx = 0;
		restricciones.gridwidth = 4;
		restricciones.gridy++;
		panelDocumento.add(etiquetaIntroDocumento, restricciones);
		restricciones.gridy++;
		panelDocumento.add(scrollIntroDocumento, restricciones);
		
		restricciones.gridy++;
		restricciones.anchor = GridBagConstraints.CENTER;
		panelDocumento.add(botonDocumentoGenerar, restricciones);
		
		
		
		
	}

	private void inicializarPanelDescargasConsultas() {
		
		//--INGRESAR COMPONENTES AL PANEL--
		
		//Labels indicativas
		
		JLabel tipoReporte, contenido, formato, desde, hasta;
		
		tipoReporte = new JLabel("Reporte de: ");
		tipoReporte.setFont(Estilos.fontLabels);
		tipoReporte.setForeground(Estilos.colorSubtitulo);
		contenido = new JLabel("Agrupado por: ");
		contenido.setFont(Estilos.fontSubrayados);
		formato = new JLabel("Formato");
		formato.setFont(Estilos.fontSubrayados);		
		desde = new JLabel("Desde: ");
		hasta = new JLabel("Hasta: ");
		
		//
		
		//-------------Panel para opciones
		
		panelOpcionesReporteDescargas = new JPanel(new GridBagLayout());
		
		TitledBorder bordeOpciones = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Opciones reporte");
		bordeOpciones.setTitleJustification(TitledBorder.LEFT);
		bordeOpciones.setTitleFont(Estilos.fontSubtitulos);
		bordeOpciones.setTitleColor(Estilos.colorSubtitulo);
		panelOpcionesReporteDescargas.setBorder(bordeOpciones);
		
		GridBagConstraints restriccionesOpciones = new GridBagConstraints();
		restriccionesOpciones.anchor = GridBagConstraints.WEST;
		restriccionesOpciones.insets = new Insets(4, 40, 4, 40);
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridwidth = 1;
		
		panelOpcionesReporteDescargas.add(formato, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReporteDescargas.add(detallado, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(totales, restriccionesOpciones);
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.gridy = 0;
		
		panelOpcionesReporteDescargas.add(ordenDescargas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReporteDescargas.add(ascendente, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(descendente, restriccionesOpciones);
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 2;
		restriccionesOpciones.gridy = 0;
		
		panelOpcionesReporteDescargas.add(mostrarDescargas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReporteDescargas.add(habilitarPorDiaDescargasConsultas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(habilitarPorMesDescargasConsultas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(habilitarPorAnioDescargasConsultas, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth=2;
		
		panelOpcionesReporteDescargas.add(habilitarPeriodoDescargasConsultas, restriccionesOpciones);
		restriccionesOpciones.gridwidth=1;
		restriccionesOpciones.insets = new Insets(4, 80, 4, 4);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(desde, restriccionesOpciones);
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReporteDescargas.add(campoFechaDesdeDescargas, restriccionesOpciones);
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.insets.left = 80;
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(hasta, restriccionesOpciones);
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.insets.left = 40;
		panelOpcionesReporteDescargas.add(campoFechaHastaDescargas, restriccionesOpciones);		
		
		//---------Fin panel para opciones
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.anchor= GridBagConstraints.WEST;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.insets = new Insets(4, 20, 8, 10);
		restricciones.gridwidth = 4;
		
		panelDescargasConsultas.add(tipoReporte, restricciones);
		restricciones.gridy++;
		panelDescargasConsultas.add(descargasRadio, restricciones);
		restricciones.gridx = 1;
		panelDescargasConsultas.add(consultasRadio, restricciones);
		
		restricciones.gridx = 0;
		restricciones.gridy++;
		restricciones.gridwidth = 4;
		
		panelDescargasConsultas.add(contenido, restricciones);
		restricciones.gridwidth = 1;
		restricciones.gridy++;
		panelDescargasConsultas.add(usuariosRadio, restricciones);
		restricciones.gridx = 1;
		panelDescargasConsultas.add(areasRadio, restricciones);
		restricciones.gridx = 2;
		panelDescargasConsultas.add(fechaRadio, restricciones);
		
		restricciones.gridy++;
		restricciones.gridwidth=4;
		restricciones.gridx = 0;
		panelDescargasConsultas.add(panelOpcionesReporteDescargas, restricciones);
		
		restricciones.gridwidth =1;
		restricciones.gridy++;
		panelDescargasConsultas.add(etiquetaTituloDescargasConsultas, restricciones);
		restricciones.gridwidth = 2;
		restricciones.gridx = 1;
		panelDescargasConsultas.add(campoTituloDescargasConsultas, restricciones);
		
		restricciones.gridy++;
		restricciones.gridwidth=4;
		restricciones.gridx = 0;
		panelDescargasConsultas.add(etiquetaIntroDescargasConsultas, restricciones);
		restricciones.gridy++;
		panelDescargasConsultas.add(scrollIntroDescargasConsultas, restricciones);
		
		restricciones.gridy++;
		restricciones.anchor = GridBagConstraints.CENTER;
		panelDescargasConsultas.add(botonDescargasConsultasGenerar, restricciones);	
		
	}

	private void inicializarButtons() {

		botonDescargasConsultasGenerar = new Button("Generar Reporte");
		botonDescargasConsultasGenerar.setIcon(new ImageIcon("recursos/iconos/Report2.png"));
		botonDescargasConsultasGenerar.addActionListener(manejador);
		botonDocumentoGenerar = new Button("Generar Reporte");
		botonDocumentoGenerar.setIcon(new ImageIcon("recursos/iconos/Report2.png"));
		botonDocumentoGenerar.addActionListener(manejador);
		
		
		
		//Botones del menu.
		botonDescargasConsultas = new JButton("    Descargas-Consultas    ");
		botonDescargasConsultas.setIcon(new ImageIcon("recursos/iconos/big/reports.png"));
		botonDescargasConsultas.setVerticalTextPosition(JLabel.BOTTOM);
		botonDescargasConsultas.setHorizontalTextPosition(JLabel.CENTER);
		botonDescargasConsultas.addActionListener(manejador);
		
	
		
		botonDocumento = new JButton("    Documentos catalogados   ");
		botonDocumento.setIcon(new ImageIcon("recursos/iconos/big/edit_document.png"));
		botonDocumento.setVerticalTextPosition(JLabel.BOTTOM);
		botonDocumento.setHorizontalTextPosition(JLabel.CENTER);
		botonDocumento.addActionListener(manejador);
		
		//Fin botones del menu
		
		
	}

	private void inicializarCheckBox() {
		
		habilitarPeriodoDescargasConsultas = new JCheckBox("Restringir periodo");
		habilitarPeriodoDescargasConsultas.setFont(Estilos.fontSubrayados);
		habilitarPeriodoDescargasConsultas.addActionListener(manejador);
		
		
		habilitarPeriodoDocumento = new JCheckBox("Restringir periodo");
		habilitarPeriodoDocumento.setFont(Estilos.fontSubrayados);
		habilitarPeriodoDocumento.addActionListener(manejador);
		
	
		
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
		
		ordenDescargas = new JLabel("Orden usuarios");
		ordenDescargas.setFont(Estilos.fontSubrayados);
		ordenDescargas.setVisible(false);
		
		mostrarDescargas = new JLabel("Mostrar");
		mostrarDescargas.setFont(Estilos.fontSubrayados);
		mostrarDescargas.setVisible(false);
		
		ordenDocumento = new JLabel("Orden catalogadores");
		ordenDocumento.setFont(Estilos.fontSubrayados);
		ordenDocumento.setVisible(false);
		
		mostrarDocumento = new JLabel("Mostrar");
		mostrarDocumento.setFont(Estilos.fontSubrayados);
		mostrarDocumento.setVisible(false);
		
	}

	private class Manejador implements ActionListener, KeyListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == usuariosRadio)
			{
				if(usuariosRadio.isSelected())
				{
					totales.setEnabled(true);
					if(totales.isSelected())
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
						/*ordenDescargas.setVisible(true);
						ascendente.setVisible(true);
						descendente.setVisible(true);*/
						mostrarDescargas.setVisible(false);
						habilitarPorAnioDescargasConsultas.setVisible(false);
						habilitarPorDiaDescargasConsultas.setVisible(false);
						habilitarPorMesDescargasConsultas.setVisible(false);
					}
					else
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
						mostrarDescargas.setVisible(false);
						habilitarPorAnioDescargasConsultas.setVisible(false);
						habilitarPorDiaDescargasConsultas.setVisible(false);
						habilitarPorMesDescargasConsultas.setVisible(false);
					}
					
				}else
				{
					ordenDescargas.setVisible(false);
					ascendente.setVisible(false);
					descendente.setVisible(false);
				}
			}
			
			if(e.getSource() == fechaRadio)
			{

				if(fechaRadio.isSelected())
				{
						totales.setEnabled(true);
						totales.setSelected(false);
						detallado.setSelected(true);
						mostrarDescargas.setVisible(true);
						habilitarPorAnioDescargasConsultas.setVisible(true);
						habilitarPorDiaDescargasConsultas.setVisible(true);
						habilitarPorMesDescargasConsultas.setVisible(true);
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
					
					
				}else
				{
					totales.setEnabled(true);
					mostrarDescargas.setVisible(false);
					habilitarPorAnioDescargasConsultas.setVisible(false);
					habilitarPorDiaDescargasConsultas.setVisible(false);
					habilitarPorMesDescargasConsultas.setVisible(false);
				}
			}
			
			if(e.getSource() == areasRadio)
			{

				if(areasRadio.isSelected())
				{
					
					totales.setEnabled(true);
					mostrarDescargas.setVisible(false);
					habilitarPorAnioDescargasConsultas.setVisible(false);
					habilitarPorDiaDescargasConsultas.setVisible(false);
					habilitarPorMesDescargasConsultas.setVisible(false);
					ordenDescargas.setVisible(false);
					ascendente.setVisible(false);
					descendente.setVisible(false);
				}
			}
			
			if(e.getSource() == totales)
			{
				if(totales.isSelected())
				{
					if(usuariosRadio.isSelected())
					{
						ordenDescargas.setVisible(true);
						ascendente.setVisible(true);
						descendente.setVisible(true);
						mostrarDescargas.setVisible(false);
						habilitarPorAnioDescargasConsultas.setVisible(false);
						habilitarPorDiaDescargasConsultas.setVisible(false);
						habilitarPorMesDescargasConsultas.setVisible(false);
					}else
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
					}
					
				}
			}
			
			if(e.getSource() == detallado)
			{
				if(detallado.isSelected())
				{
					if(usuariosRadio.isSelected())
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
						mostrarDescargas.setVisible(false);
						habilitarPorAnioDescargasConsultas.setVisible(false);
						habilitarPorDiaDescargasConsultas.setVisible(false);
						habilitarPorMesDescargasConsultas.setVisible(false);
					}else if(areasRadio.isSelected())
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
						mostrarDescargas.setVisible(false);
						habilitarPorAnioDescargasConsultas.setVisible(false);
						habilitarPorDiaDescargasConsultas.setVisible(false);
						habilitarPorMesDescargasConsultas.setVisible(false);
					}else
					{
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
					}
				}
			}
			if(e.getSource() == catalogadorRadio)
			{
				if(catalogadorRadio.isSelected())
				{
					totalesDocumento.setEnabled(true);
					if(totalesDocumento.isSelected())
					{
						ordenDocumento.setVisible(false);
						ascendenteDocumento.setVisible(false);
						descendenteDocumento.setVisible(false);
						/*ordenDocumento.setVisible(true);
						ascendenteDocumento.setVisible(true);
						descendenteDocumento.setVisible(true);*/
						mostrarDocumento.setVisible(false);
						habilitarPorAnioDocumento.setVisible(false);
						habilitarPorDiaDocumento.setVisible(false);
						habilitarPorMesDocumento.setVisible(false);
					}else
					{
						ordenDocumento.setVisible(false);
						ascendenteDocumento.setVisible(false);
						descendenteDocumento.setVisible(false);
						mostrarDocumento.setVisible(false);
						habilitarPorAnioDocumento.setVisible(false);
						habilitarPorDiaDocumento.setVisible(false);
						habilitarPorMesDocumento.setVisible(false);
					}
				}else
				{
					ordenDocumento.setVisible(false);
					ascendenteDocumento.setVisible(false);
					descendenteDocumento.setVisible(false);
				}
			}
			
			if(e.getSource() == fechaRadioDocumento)
			{

				if(fechaRadioDocumento.isSelected())
				{
					totalesDocumento.setEnabled(true);
					totalesDocumento.setSelected(false);
					detalladoDocumento.setSelected(true);
					ordenDocumento.setVisible(false);
					ascendenteDocumento.setVisible(false);
					descendenteDocumento.setVisible(false);
					mostrarDocumento.setVisible(true);
					habilitarPorAnioDocumento.setVisible(true);
					habilitarPorDiaDocumento.setVisible(true);
					habilitarPorMesDocumento.setVisible(true);
				}else
				{
					totalesDocumento.setEnabled(true);
					mostrarDocumento.setVisible(false);
					habilitarPorAnioDocumento.setVisible(false);
					habilitarPorDiaDocumento.setVisible(false);
					habilitarPorMesDocumento.setVisible(false);
				}
			}
			
			if(e.getSource() == areasRadioDocumento)
			{
				if(areasRadioDocumento.isSelected())
				{
					totalesDocumento.setEnabled(true);
					ordenDocumento.setVisible(false);
					ascendenteDocumento.setVisible(false);
					descendenteDocumento.setVisible(false);
					mostrarDocumento.setVisible(false);
					habilitarPorAnioDocumento.setVisible(false);
					habilitarPorDiaDocumento.setVisible(false);
					habilitarPorMesDocumento.setVisible(false);
				}
			}
			
			if(e.getSource() == totalesDocumento)
			{
				if(totalesDocumento.isSelected())
				{
					if(catalogadorRadio.isSelected())
					{
						/*ordenDocumento.setVisible(true);
						ascendenteDocumento.setVisible(true);
						descendenteDocumento.setVisible(true);*/
						mostrarDocumento.setVisible(false);
						habilitarPorAnioDocumento.setVisible(false);
						habilitarPorDiaDocumento.setVisible(false);
						habilitarPorMesDocumento.setVisible(false);
						
					}else 
					{
						ordenDocumento.setVisible(false);
						ascendenteDocumento.setVisible(false);
						descendenteDocumento.setVisible(false);
						//mostrarDocumento.setVisible(false);
						//habilitarPorAnioDocumento.setVisible(false);
						//habilitarPorDiaDocumento.setVisible(false);
						//habilitarPorMesDocumento.setVisible(false);
						
					}
				}
				
			}
			
			if(e.getSource()==detalladoDocumento)
			{
				if(detalladoDocumento.isSelected())
				{
					if(fechaRadioDocumento.isSelected())
					{
						ordenDocumento.setVisible(false);
						ascendenteDocumento.setVisible(false);
						descendenteDocumento.setVisible(false);
						mostrarDocumento.setVisible(true);
						habilitarPorAnioDocumento.setVisible(true);
						habilitarPorDiaDocumento.setVisible(true);
						habilitarPorMesDocumento.setVisible(true);
					}else
					{
						ordenDocumento.setVisible(false);
						ascendenteDocumento.setVisible(false);
						descendenteDocumento.setVisible(false);
						mostrarDocumento.setVisible(false);
						habilitarPorAnioDocumento.setVisible(false);
						habilitarPorDiaDocumento.setVisible(false);
						habilitarPorMesDocumento.setVisible(false);
					}
				}
			}
			
			if(e.getSource() == botonDescargasConsultas)
			{
				botonDescargasConsultas.getModel().setArmed(true);
				botonDescargasConsultas.getModel().setPressed(true);
				scrollPanel.setViewportView(panelDescargasConsultas);
				panelPrincipal.updateUI();	
			}
			if (e.getSource() == botonDocumento)
			{
				botonDocumento.getModel().setArmed(true);
				botonDocumento.getModel().setPressed(true);
				scrollPanel.setViewportView(panelDocumento);
				panelPrincipal.updateUI();
			}
			if (e.getSource() == habilitarPeriodoDescargasConsultas)
			{
				if(habilitarPeriodoDescargasConsultas.isSelected())
				{
					campoFechaDesdeDescargas.setEnabled(true);
					campoFechaHastaDescargas.setEnabled(true);
				}
				else
				{
					campoFechaDesdeDescargas.setEnabled(false);
					campoFechaHastaDescargas.setEnabled(false);
				}
			}
			if (e.getSource() == habilitarPeriodoDocumento)
			{
				if(habilitarPeriodoDocumento.isSelected())
				{
					campoFechaDesdeDocumento.setEnabled(true);
					campoFechaHastaDocumento.setEnabled(true);
				}
				else
				{
					campoFechaDesdeDocumento.setEnabled(false);
					campoFechaHastaDocumento.setEnabled(false);
				}
			}
			if(e.getSource() == botonDescargasConsultasGenerar)
			{
				
					
					String rutaFinal = "";

					boolean descargas = descargasRadio.isSelected();
					boolean detalladoBool = detallado.isSelected();
					boolean totalesBool = totales.isSelected();
					boolean areas = areasRadio.isSelected();
					boolean usuarios = usuariosRadio.isSelected();
					boolean fecha = fechaRadio.isSelected();
					boolean habilitarFecha = habilitarPeriodoDescargasConsultas.isSelected();
					boolean porDia = habilitarPorDiaDescargasConsultas.isSelected();
					boolean porMes = habilitarPorMesDescargasConsultas.isSelected();
					boolean porAnio = habilitarPorAnioDescargasConsultas.isSelected();
					boolean asc = ascendente.isSelected();
					boolean desc = descendente.isSelected();

					SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
					Date fechaIni= ((JSpinner.DateEditor) campoFechaDesdeDescargas.getEditor()).getModel().getDate();			    
					String fechaInicial = formatoFecha.format(fechaIni);
					Date fechaFin = ((JSpinner.DateEditor) campoFechaHastaDescargas.getEditor()).getModel().getDate();
					String fechaFinal = formatoFecha.format(fechaFin);

					String tituloReporte = campoTituloDescargasConsultas.getText();
					String introduccion = campoIntroDescargasConsultas.getText();


					if(descargas)
					{
						try
						{
							JasperPrint reporte;

							if(detalladoBool)
							{
								if(areas)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosDescargadosArea(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosDescargadosArea(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								} else if(usuarios)
								{
									if(habilitarFecha)
									{
										reporte =  controladorReporte.reporteDocumentosDescargadosUsuario(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosDescargadosUsuario(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								}else if(fecha)
								{
									if(porDia)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFecha(fechaInicial, fechaFinal, introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFecha(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
										//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
									} else if(porMes)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaMes(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaMes(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									} else if(porAnio)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaAnio(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaAnio(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}
									
								}
							} else if(totalesBool)
							{
								if(areas)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosDescargadosAreaTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosDescargadosAreaTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
								} else if(usuarios)
								{
									if(habilitarFecha)
									{
										reporte =  controladorReporte.reporteDocumentosDescargadosUsuarioTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosDescargadosUsuarioTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
																	
								}else if(fecha)
								{
									if(porDia)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}else if(porMes)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaMesTotales(fechaInicial, fechaFinal,introduccion,  tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaMesTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									} else if(porAnio)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaAnioTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosDescargadosFechaAnioTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}									
								}
							}

							
						}catch(JRException exc)
						{
							System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
							exc.printStackTrace();
						}
					}else //consultas
					{
						try
						{
							JasperPrint reporte;

							if(detalladoBool)
							{
								if(areas)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosConsultadosArea(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosConsultadosArea(introduccion,tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								} else if(usuarios)
								{
									if(habilitarFecha)
									{
										reporte =  controladorReporte.reporteDocumentosConsultadosUsuario(fechaInicial, fechaFinal, introduccion,tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosConsultadosUsuario(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								} else if(fecha)
								{
									if(porDia)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFecha(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFecha(introduccion,tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
										//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
									} else if(porMes)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaMes(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaMes(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									} else if(porAnio)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaAnio(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaAnio(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}
									
								}
							} else if(totalesBool)
							{
								if(areas)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosConsultadosAreaTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosConsultadosAreaTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
								}else if(usuarios)
								{
									if(habilitarFecha)
									{
										reporte =  controladorReporte.reporteDocumentosConsultadosUsuarioTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosConsultadosUsuarioTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
								}else if(fecha)
								{
									if(porDia)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}else if(porMes)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaMesTotales(fechaInicial, fechaFinal,introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaMesTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}else if(porAnio)
									{
										if(habilitarFecha)
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaAnioTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}else
										{
											reporte = controladorReporte.reporteDocumentosConsultadosFechaAnioTotales(introduccion, tituloReporte);
											controladorReporte.generarReporte(rutaFinal, reporte);
										}
									}									
								}
							}

							
						}catch(JRException exc)
						{
							System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
							exc.printStackTrace();
						}
					}
				
			}
			if(e.getSource() == botonDocumentoGenerar)
			{
				
					String rutaFinal = "";
					
					
					boolean detalladoBool = detalladoDocumento.isSelected();
					boolean totalesBool = totalesDocumento.isSelected();
					
					boolean areas = areasRadioDocumento.isSelected();
					boolean catalogadores = catalogadorRadio.isSelected();
					boolean fecha = fechaRadioDocumento.isSelected();
					
					boolean habilitarFecha = habilitarPeriodoDocumento.isSelected();
					
					boolean porDia = habilitarPorDiaDocumento.isSelected();
					boolean porMes = habilitarPorMesDocumento.isSelected();
					boolean porAnio = habilitarPorAnioDocumento.isSelected();
					boolean asc = ascendenteDocumento.isSelected();
					boolean desc = descendenteDocumento.isSelected();

					SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
					Date fechaIni= ((JSpinner.DateEditor) campoFechaDesdeDocumento.getEditor()).getModel().getDate();			    
					String fechaInicial = formatoFecha.format(fechaIni);
					Date fechaFin = ((JSpinner.DateEditor) campoFechaHastaDocumento.getEditor()).getModel().getDate();
					String fechaFinal = formatoFecha.format(fechaFin);

					String tituloReporte = campoTituloDocumento.getText();
					String introduccion = campoIntroDocumento.getText();
					
					try
					{
						JasperPrint reporte;
						
						if(detalladoBool)
						{
							if(catalogadores)
							{
								if(habilitarFecha)
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosUsuario(fechaInicial, fechaFinal, introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosUsuario(introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								
							}else if(areas)
							{
								if(habilitarFecha)
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosArea(fechaInicial, fechaFinal, introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosArea(introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
								//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
							}else if (fecha)
							{
								if(porDia)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFecha(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFecha(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
									
								}else if(porMes)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaMes(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaMes(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								}else if(porAnio)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaAnio(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaAnio(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									//JOptionPane.showMessageDialog(null, "Informe Generado correctamente");
								}
							}
								
						}else if(totalesBool)
						{
							if(catalogadores)
							{
								if(habilitarFecha)
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosUsuarioTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosUsuarioTotales(introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
							}else if(areas)
							{
								if(habilitarFecha)
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosAreaTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}else
								{
									reporte = controladorReporte.reporteDocumentosCatalogadosAreaTotales(introduccion, tituloReporte);
									controladorReporte.generarReporte(rutaFinal, reporte);
								}
							}else if (fecha)
							{
								if(porDia)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
										
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									
								}else if(porMes)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaMesTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaMesTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
									
									
								}else if(porAnio)
								{
									if(habilitarFecha)
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaAnioTotales(fechaInicial, fechaFinal, introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}else
									{
										reporte = controladorReporte.reporteDocumentosCatalogadosFechaAnioTotales(introduccion, tituloReporte);
										controladorReporte.generarReporte(rutaFinal, reporte);
									}
								}
							}
						}
						
					}catch(JRException exc)
					{
						System.out.println("Exception generada en GuiReportes.Manejador,actionPreformed");
						exc.printStackTrace();
					}
					
					
				
				
			}
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getSource() == campoIntroDescargasConsultas || e.getSource() == campoIntroDocumento)
			{
				if(new String(campoIntroDescargasConsultas.getText()).length()>299)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoIntroDescargasConsultas.setText(new String(campoIntroDescargasConsultas.getText()).substring(0,299));
					}
				}
				if(new String(campoIntroDocumento.getText()).length()>299)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoIntroDocumento.setText(new String(campoIntroDocumento.getText()).substring(0,299));
					}
				}
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}

		
	}
}
