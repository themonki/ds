package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import Reportes.Controlador.ControladorReportes;
import Reportes.Gui.GuiReportes.Manejador;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiReportesAvanzados extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	
	private Vector<String> vectorContablasAvanzado;
	private Vector<String> vectorAtributosAvanzado;

	
	private JCheckBox habilitarAvanzado;
	
	private JComboBox tablasAvanzado;
	private JComboBox atributosAvanzado;
	
	private JLabel etiquetaTablaAvanzado;
	private JLabel etiquetaAtributoAvanzado;
	
	private GridBagConstraints retriccionesAvanzado;
	
	private JCheckBox habilitarPorDia;
	private JCheckBox habilitarPorMes;
	private JCheckBox habilitarPorAño;
	private JCheckBox habilitarPorHora;
	
	private JSpinner campoFecha3; //estos dos son para el intervaloe de reportes avanzados
	private JSpinner campoFecha4;
	
	private Button botonAvanzado;
	
	ControladorReportes controladorReporte;
	
	public GuiReportesAvanzados() {
		controladorReporte= new ControladorReportes();
		initComponents();
	}
	
	private void initComponents()
	{

		
		botonAvanzado= new Button("Generar Reporte");
		//botonAvanzado.addActionListener(new Manejador());
		
		
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
		
		
		panelPrincipal= new JPanel(new GridBagLayout());
		//panelPrincipal.setBorder(borde2);
		panelPrincipal.setBackground(Color.WHITE);
		
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
		//panelPrincipal.add(new JComboBox(vectorFechasAvanzado),retriccionesAvanzado);
		
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
		
				
		panelPrincipal.add(botonAvanzado,retriccionesAvanzado);
	
		
		retriccionesAvanzado.insets= new Insets(0, 0, 0, 0);
		
		this.setLayout(new BorderLayout());
		this.add(panelPrincipal, BorderLayout.CENTER);
		
	}

}
