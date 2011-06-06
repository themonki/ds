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
	
	private JCheckBox habilitarPeriodoDescargasConsultas, habilitarPorMesDescargasConsultas, habilitarPorAñoDescargasConsultas, habilitarPorHoraDescargasConsultas;
	
	private JComboBox tablasAvanzado, atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado, etiquetaAtributoAvanzado, etiquetaTituloDescargasConsultas,
				   etiquetaTituloDocumento, etiquetaIntroDocumento, etiquetaIntroDescargasConsultas;
	
	private JTextField campoTituloDescargasConsultas, campoTituloDocumento;
	
	private JTextArea campoIntroDescargasConsultas, campoIntroDocumento;
	
	private GridBagConstraints retriccionesAvanzado;
	
	private JSpinner campoFecha3, campoFecha4; //estos dos son para el intervaloe de reportes avanzados
	
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
		inicializarPanels();
		
		
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

	private void inicializarTexts() {
		
		campoTituloDescargasConsultas = new JTextField("Reporte", 20);
		campoTituloDocumento = new JTextField("Reporte", 20);
		
		campoIntroDescargasConsultas = new JTextArea(8,50);
		campoIntroDescargasConsultas.setLineWrap(true);
		campoIntroDocumento = new JTextArea(8,50);
		campoIntroDocumento.setLineWrap(true);
		
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
		habilitarPorAñoDescargasConsultas= new JCheckBox("Por Año");
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
