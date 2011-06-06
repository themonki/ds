package Reportes.Gui;


import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
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
	
	private JCheckBox habilitarAvanzado, habilitarPorDia, habilitarPorMes, habilitarPorAño, habilitarPorHora;
	
	private JComboBox tablasAvanzado, atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado, etiquetaAtributoAvanzado, etiquetaDescargasConsultas, etiquetaDocumento;
	
	private GridBagConstraints retriccionesAvanzado;
	
	private JSpinner campoFecha3, campoFecha4; //estos dos son para el intervaloe de reportes avanzados
	
	private Button botonDescargas, botonConsultas, botonDocumento;
	
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

	private void inicializarPanels() {
		
		panelPrincipal= new JPanel(new BorderLayout());
		panelPrincipal.setBackground(Color.WHITE);
		
		panelOpcionesReportes = new JPanel (new GridBagLayout());
		
		//panelConsultas = new JPanel(new GridBagLayout());
		inicializarPanelConsultas();
		//panelDescargas = new JPanel(new GridBagLayout());
		inicializarPanelDescargas();
		panelDocumento = new JPanel(new GridBagLayout());
		inicializarPanelDocumento();
		
	}

	private void inicializarPanelDocumento() {
		
		
	}

	private void inicializarPanelDescargas() {
		
		
	}

	private void inicializarPanelConsultas() {
		
		
	}

	private void inicializarButtons() {

		botonDescargas = new Button("Generar Reporte");
		botonConsultas = new Button("Generar Reporte");
		botonDocumento = new Button("Generar Reporte");
		
		//botonDescargas.addActionListener(new Manejador());
		//botonConsultas.addActionListener(new Manejador());
		//botonDocumento.addActionListener(new Manejador());
		
		
	}

	private void inicializarCheckBox() {
		
		habilitarAvanzado= new JCheckBox("habilitar periodo");
		habilitarPorDia= new JCheckBox("Generar Por Dia");
		habilitarPorMes= new JCheckBox("Generar Por Mes");
		habilitarPorAño= new JCheckBox("Generar Por Año");
		habilitarPorHora= new JCheckBox("Generar Por Hora");
		habilitarAvanzado.setFont(Estilos.fontSubrayados);
		
	}

	private void inicializarComboBox() {
		
		tablasAvanzado = new JComboBox(vectorContablasAvanzado);
		atributosAvanzado = new JComboBox(vectorAtributosAvanzado);
		
	}

	private void inicializarLabels() {
		
		//Etiquetas del menu.
		etiquetaDescargasConsultas = new JLabel("Descargas-Consultas");
		etiquetaDescargasConsultas.setIcon(new ImageIcon("recursos/iconos/big/download.png"));
		etiquetaDescargasConsultas.setVerticalTextPosition(JLabel.BOTTOM);
		etiquetaDescargasConsultas.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		etiquetaDocumento = new JLabel("Consultas");
		etiquetaDocumento.setIcon(new ImageIcon("recursos/iconos/big/edit_document.png"));
		etiquetaDocumento.setVerticalTextPosition(JLabel.BOTTOM);
		etiquetaDocumento.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		//Fin etiquetas del menu
		
		etiquetaTablaAvanzado= new JLabel("TABLA");
		etiquetaAtributoAvanzado= new JLabel("CONSULTAR POR   :");

		
		etiquetaTablaAvanzado.setForeground(Estilos.colorLabels);
		etiquetaAtributoAvanzado.setForeground(Estilos.colorLabels);

		
		etiquetaTablaAvanzado.setFont(Estilos.fontLabels);
		etiquetaAtributoAvanzado.setFont(Estilos.fontLabels);
		
	}


}
