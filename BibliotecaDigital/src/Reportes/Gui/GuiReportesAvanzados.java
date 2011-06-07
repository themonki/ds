package Reportes.Gui;


import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

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
	private Vector<String> vectorAgrupadoAtributos;
	

	private JPanel panelPrincipal, panelOpcionesReportes, panelDescargasConsultas, panelDocumento, panelOpcionesReporteDescargas,
				   panelOpcionesReportesDocumento;
	
	private JCheckBox habilitarPeriodoDescargasConsultas, habilitarPeriodoDocumento;
	
	private JComboBox tablasAvanzado, atributosAvanzado, campoAtributo;
	
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
						 detallado, totales, ascendente, descendente, documentosExistentes, documentosCatalogados,
						 detalladoDocumento, totalesDocumento, ascendenteDocumento, descendenteDocumento,
						 habilitarPorMesDescargasConsultas, habilitarPorAnioDescargasConsultas,
						 habilitarPorDiaDescargasConsultas, habilitarPorMesDocumento, habilitarPorAnioDocumento,
						 habilitarPorDiaDocumento;
	
	private ButtonGroup opcionTipo, opcionAgrupado, opcionReporte, opcionOrden, menu,
						opcionTipoDocuemento, opcionReporteDocumento, opcionOrdenCatalogadores,
						opcionMostrarDescargas, opcionMostrarDocumento;
	
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
		
		vectorAgrupadoAtributos =  new Vector<String>();
		vectorAgrupadoAtributos.add("area");
		vectorAgrupadoAtributos.add("autor");
		vectorAgrupadoAtributos.add("formato");
		vectorAgrupadoAtributos.add("tipo");
		
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
		
		opcionTipoDocuemento = new ButtonGroup();
		opcionTipoDocuemento.add(detalladoDocumento);
		opcionTipoDocuemento.add(totalesDocumento);
		
		opcionReporteDocumento = new ButtonGroup();
		opcionReporteDocumento.add(documentosExistentes);
		opcionReporteDocumento.add(documentosCatalogados);
		
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
		totales.addActionListener(manejador);
		
		ascendente = new JRadioButton("Ascendente", true);	
		ascendente.setVisible(false);
		descendente = new JRadioButton("Descendente", false);
		descendente.setVisible(false);
		
		documentosExistentes = new JRadioButton("Documentos existentes", true);
		documentosExistentes.addActionListener(manejador);
		documentosCatalogados = new JRadioButton("Documentos catalogados", false);
		documentosCatalogados.addActionListener(manejador);
		
		detalladoDocumento = new JRadioButton("Detallado", true);		
		totalesDocumento = new JRadioButton("Solo totales", false);
		
		ascendenteDocumento = new JRadioButton("Ascendente", false);
		ascendenteDocumento.setVisible(false);
		descendenteDocumento = new JRadioButton("Descendente", false);
		descendenteDocumento.setVisible(false);
		
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
		JLabel tipoReporte, orden, agrupadosAtributo, agrupadoPeriodo, formato, desde, hasta;
		
		tipoReporte = new JLabel("Reporte de: ");
		tipoReporte.setFont(Estilos.fontLabels);
		tipoReporte.setForeground(Estilos.colorSubtitulo);
		
		orden = new JLabel("Orden de catalogadores");
		orden.setFont(Estilos.fontSubrayados);
		
		agrupadosAtributo = new JLabel("Agrupado por: ");
		agrupadosAtributo.setFont(Estilos.fontSubrayados);
		
		agrupadoPeriodo = new JLabel("Agrupado por periodo: ");
		agrupadoPeriodo.setFont(Estilos.fontSubrayados);
		
		formato = new JLabel("Formato ");
		formato.setFont(Estilos.fontSubrayados);
		
		desde = new JLabel("Desde: ");
		hasta = new JLabel("Hasta: ");
		
		//-----Fin labels indicatorias
		
		//---panel para opciones reportes
		JPanel opcionesReporte = new JPanel(new GridBagLayout());
		
		TitledBorder bordeOpciones = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Opciones reporte");
		bordeOpciones.setTitleJustification(TitledBorder.LEFT);
		bordeOpciones.setTitleFont(Estilos.fontSubtitulos);
		bordeOpciones.setTitleColor(Estilos.colorSubtitulo);
		opcionesReporte.setBorder(bordeOpciones);
		
		
		GridBagConstraints restriccionesOpciones = new GridBagConstraints();
		restriccionesOpciones.anchor = GridBagConstraints.WEST;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.insets = new Insets(4, 20, 4, 20);
		
		restriccionesOpciones.gridwidth = 1;
		opcionesReporte.add(orden, restriccionesOpciones);
		restriccionesOpciones.insets.left = 40;
		restriccionesOpciones.gridy++;
		opcionesReporte.add(ascendenteDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(descendenteDocumento, restriccionesOpciones);
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 1;
		opcionesReporte.add(formato, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		opcionesReporte.add(detalladoDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(totalesDocumento, restriccionesOpciones);
		
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridy = 0;
		restriccionesOpciones.gridx = 2;
		opcionesReporte.add(agrupadoPeriodo, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 40;
		opcionesReporte.add(habilitarPorMesDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		opcionesReporte.add(habilitarPorAnioDocumento, restriccionesOpciones);
				
		restriccionesOpciones.gridwidth = 1;
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy++;
		restriccionesOpciones.insets.left = 20;
		opcionesReporte.add(agrupadosAtributo, restriccionesOpciones);
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.gridwidth = 2;
		opcionesReporte.add(campoAtributo, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth = 3;
		opcionesReporte.add(habilitarPeriodoDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridwidth =2;
		restriccionesOpciones.insets.left= 40;
		opcionesReporte.add(desde, restriccionesOpciones);
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 1;
		opcionesReporte.add(campoFechaDesdeDocumento, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		restriccionesOpciones.gridx = 0;
		restriccionesOpciones.insets.left = 40;
		opcionesReporte.add(hasta, restriccionesOpciones);
		restriccionesOpciones.insets.left = 20;
		restriccionesOpciones.gridx = 1;
		opcionesReporte.add(campoFechaHastaDocumento, restriccionesOpciones);
		
		//---fin panel de opciones reprotes
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.anchor = GridBagConstraints.WEST;
		restricciones.gridx = 0;
		restricciones.gridy = 0;
		restricciones.gridwidth = 4;
		restricciones.insets = new Insets(4, 20, 4, 20);
		
		panelDocumento.add(tipoReporte, restricciones);
		restricciones.gridy++;
		panelDocumento.add(documentosExistentes, restricciones);
		restricciones.gridy++;
		panelDocumento.add(documentosCatalogados, restricciones);
		
		restricciones.gridy++;
		panelDocumento.add(opcionesReporte, restricciones);
		restricciones.gridwidth = 2;
		restricciones.gridy++; 
		panelDocumento.add(etiquetaTituloDocumento, restricciones);
		restricciones.gridx = 2;
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
		
		panelOpcionesReporteDescargas.add(formato, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(detallado, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(totales, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 1;
		restriccionesOpciones.gridy = 0;
		
		panelOpcionesReporteDescargas.add(ordenDescargas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(ascendente, restriccionesOpciones);
		restriccionesOpciones.gridy++;
		panelOpcionesReporteDescargas.add(descendente, restriccionesOpciones);
		
		restriccionesOpciones.gridx = 2;
		restriccionesOpciones.gridy = 0;
		
		panelOpcionesReporteDescargas.add(mostrarDescargas, restriccionesOpciones);
		restriccionesOpciones.gridy++;
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
		
		panelDescargasConsultas.add(tipoReporte, restricciones);
		restricciones.gridy++;
		panelDescargasConsultas.add(descargasRadio, restricciones);
		restricciones.gridx = 1;
		panelDescargasConsultas.add(consultasRadio, restricciones);
		
		restricciones.gridx = 0;
		restricciones.gridy++;
		restricciones.gridwidth = 3;
		
		panelDescargasConsultas.add(contenido, restricciones);
		restricciones.gridwidth = 1;
		restricciones.gridy++;
		panelDescargasConsultas.add(usuariosRadio, restricciones);
		restricciones.gridx = 1;
		panelDescargasConsultas.add(areasRadio, restricciones);
		restricciones.gridx = 2;
		panelDescargasConsultas.add(fechaRadio, restricciones);
		
		restricciones.gridy++;
		restricciones.gridwidth=3;
		restricciones.gridx = 0;
		panelDescargasConsultas.add(panelOpcionesReporteDescargas, restricciones);
		
		restricciones.gridwidth =1;
		restricciones.gridy++;
		panelDescargasConsultas.add(etiquetaTituloDescargasConsultas, restricciones);
		restricciones.gridwidth = 2;
		restricciones.gridx = 1;
		panelDescargasConsultas.add(campoTituloDescargasConsultas, restricciones);
		
		restricciones.gridy++;
		restricciones.gridwidth=3;
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
		
	
		
		botonDocumento = new JButton("    Documentos    ");
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
		campoAtributo = new JComboBox(vectorAgrupadoAtributos);
		
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
		
		ordenDocumento = new JLabel("Orden usuarios");
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
					if(totales.isSelected())
					{
						ordenDescargas.setVisible(true);
						ascendente.setVisible(true);
						descendente.setVisible(true);
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
					
						mostrarDescargas.setVisible(true);
						habilitarPorAnioDescargasConsultas.setVisible(true);
						habilitarPorDiaDescargasConsultas.setVisible(true);
						habilitarPorMesDescargasConsultas.setVisible(true);
						ordenDescargas.setVisible(false);
						ascendente.setVisible(false);
						descendente.setVisible(false);
					
					
				}else
				{
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
			if (e.getSource() == documentosCatalogados)
			{
				ascendenteDocumento.setSelected(true);
				ascendenteDocumento.setEnabled(true);
				descendenteDocumento.setEnabled(true);
			}
			if (e.getSource() == documentosExistentes)
			{
				opcionOrdenCatalogadores.clearSelection();
				ascendenteDocumento.setEnabled(false);
				descendenteDocumento.setEnabled(false);
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
				boolean descargas = descargasRadio.isSelected();
				if(descargas)
				{
					
					
				}else //consultas
				{
					
				}
			}
			if(e.getSource() == botonDocumentoGenerar)
			{
				
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
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
}
