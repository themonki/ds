package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


import Documento.Controlador.ControladorDocumento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import Principal.Gui.GuiPrincipal;
import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

public class GuiReportes extends JTabbedPane{
	
	JPanel PanelreportesBasicos,panelRepAvanzados; 
	GridBagConstraints retricciones;
	JCheckBox habilitar;
	
	JScrollPane scroll;
	JLabel etiquetaTabla,etiquetaAtributo,etiquetaCondicion;
	JComboBox tablas , atributos , condicion;
	Button botonGenerarReporte,nuevaCondicion;
	Vector<String> vectorContablas,vectorCondiciones,vectorAtributos;
	ControladorAreaConocimiento controladorArea;
	ControladorAutor controladorAutor ;
	ControladorTipoMaterial controladorTipoMaterial;
	ControladorDocumento controladorDocumento; 
	ControladorReportes controladorReporte;
	
	int cantCondicion=0;
	private JSpinner campoFechaNacimiento;
	private JSpinner campoFechaNacimiento2;
	private Vector<String> vectorFechas;
	private JScrollPane scroll2;
	private Vector<String> vectorCondicionesAvanzado;
	private Vector<String> vectorContablasAvanzado;
	private Vector<String> vectorAtributosAvanzado;
	private Vector<String> vectorFechasAvanzado;
	private JSpinner campoFechaNacimiento3;
	private JSpinner campoFechaNacimiento4;
	private JCheckBox habilitarAvanzado;
	private Button nuevaCondicionAvanzado;
	private JComboBox tablasAvanzado;
	private JComboBox atributosAvanzado;
	private JComboBox condicionAvanzado;
	private JLabel etiquetaTablaAvanzado;
	private JLabel etiquetaAtributoAvanzado;
	private JLabel etiquetaCondicionAvanzado;
	private Button botonGenerarReporteAvanzado;
	private GridBagConstraints retriccionesAvanzado;
	private JCheckBox habilitarPorDia;
	private JCheckBox habilitarPorMes;
	private JCheckBox habilitarPorAño;
	private JCheckBox habilitarPorHora;
	private Button botonAvanzado;
	
	GuiReportes()
	{
		controladorReporte= new ControladorReportes();
		initComponents();
	}

	private void initComponents() {
	
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

		scroll= new JScrollPane();
		vectorCondiciones= new Vector<String>();
		vectorCondiciones.add("Igual a");
		vectorCondiciones.add("Diferente a");
	
		vectorContablas= new Vector<String>();
		vectorContablas.add("Usuario");
		vectorContablas.add("Autores");
		vectorContablas.add("Areas");
		
		vectorAtributos= new Vector<String>();
		vectorAtributos.add("genero ");
		vectorAtributos.add("nivel_escolaridad");
		vectorAtributos.add("vinculo_univalle");
		vectorAtributos.add("perfil");
		
		vectorFechas= new Vector<String>();
		vectorFechas.add("fecha_nacimiento");
		vectorFechas.add("fecha_registro");
		vectorFechas.add("fecha_ultimo_acceso");

		habilitar= new JCheckBox("habilitar periodo");
		habilitar.setFont(Estilos.fontSubrayados);
		
		nuevaCondicion= new Button("Añadir condicion");
		nuevaCondicion.addActionListener(new Manejador());
		
		
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		PanelreportesBasicos.setBorder(borde);
		PanelreportesBasicos.setBackground(Color.WHITE);
		
		tablas = new JComboBox(vectorContablas);
		atributos = new JComboBox(vectorAtributos);
		condicion= new JComboBox(vectorCondiciones);
		etiquetaTabla= new JLabel("TABLA");
		etiquetaAtributo= new JLabel("AGRUPADOS POR  :");
		etiquetaCondicion= new JLabel("CONDICION");
		botonGenerarReporte= new Button("Generar Reporte");
		botonAvanzado= new Button("Generar Reporte");
		
		etiquetaTabla.setForeground(Estilos.colorLabels);
		etiquetaAtributo.setForeground(Estilos.colorLabels);
		etiquetaCondicion.setForeground(Estilos.colorLabels);
		etiquetaTabla.setFont(Estilos.fontLabels);
		etiquetaAtributo.setFont(Estilos.fontLabels);
		etiquetaCondicion.setFont(Estilos.fontLabels);
		
		retricciones= new GridBagConstraints();
		retricciones.insets= new Insets(0, 0, 20, 40);
		retricciones.gridy=0;
		retricciones.anchor= GridBagConstraints.WEST;
		PanelreportesBasicos.add(etiquetaTabla,retricciones);
		PanelreportesBasicos.add(tablas,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaAtributo,retricciones);
		PanelreportesBasicos.add(atributos,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(habilitar,retricciones);
		PanelreportesBasicos.add(new JComboBox(vectorFechas),retricciones);
		
		//PanelreportesBasicos.add(atributos,retricciones);
		
		//Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha = new SpinnerDateModel();
		campoFechaNacimiento = new JSpinner(modeloFecha);
	    campoFechaNacimiento.setFont(Estilos.fontLabels);
	    campoFechaNacimiento.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha = new JSpinner.DateEditor(campoFechaNacimiento,"yyyy-MM-dd");
		campoFechaNacimiento.setEditor(spinnerFecha);
	    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(false);
	  
	    //-------------------------------------
	    campoFechaNacimiento.setEnabled(false);
	    //-------------------------------------
	    //Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha2 = new SpinnerDateModel();
		campoFechaNacimiento2 = new JSpinner(modeloFecha2);
	    campoFechaNacimiento2.setFont(Estilos.fontLabels);
	    campoFechaNacimiento2.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha2 = new JSpinner.DateEditor(campoFechaNacimiento2,"yyyy-MM-dd");
		campoFechaNacimiento2.setEditor(spinnerFecha2);
	    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(true);
	    //-------------------------------------
	    campoFechaNacimiento2.setEnabled(false);
	    //-------------------------------------
	    retricciones.gridy++;
	    retricciones.gridx=0;
	    PanelreportesBasicos.add(new JLabel("Desde :"),retricciones);
	    retricciones.anchor=GridBagConstraints.EAST;
	    retricciones.gridx=0;
		PanelreportesBasicos.add(campoFechaNacimiento,retricciones);
		retricciones.gridy++;
		retricciones.gridx=0;
		retricciones.anchor=GridBagConstraints.WEST;
	    
		PanelreportesBasicos.add(new JLabel("Hasta :"),retricciones);
		retricciones.gridx=0;
		retricciones.anchor=GridBagConstraints.EAST;
		PanelreportesBasicos.add(campoFechaNacimiento2,retricciones);
		
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
		botonGenerarReporteAvanzado= new Button("Generar Reporte");
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
		
		//Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha3 = new SpinnerDateModel();
		campoFechaNacimiento3 = new JSpinner(modeloFecha3);
	    campoFechaNacimiento3.setFont(Estilos.fontLabels);
	    campoFechaNacimiento3.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha3 = new JSpinner.DateEditor(campoFechaNacimiento3,"yyyy-MM-dd");
		campoFechaNacimiento3.setEditor(spinnerFecha3);
	    ((JSpinner.DateEditor) campoFechaNacimiento3.getEditor()).getTextField().setEditable(false);
	  //Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha4 = new SpinnerDateModel();
		campoFechaNacimiento4 = new JSpinner(modeloFecha4);
	    campoFechaNacimiento4.setFont(Estilos.fontLabels);
	    campoFechaNacimiento4.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha4 = new JSpinner.DateEditor(campoFechaNacimiento4,"yyyy-MM-dd");
		campoFechaNacimiento4.setEditor(spinnerFecha4);
	    ((JSpinner.DateEditor) campoFechaNacimiento4.getEditor()).getTextField().setEditable(true);
	    
	    retriccionesAvanzado.gridy++;
	    retriccionesAvanzado.gridx=0;
	    panelRepAvanzados.add(new JLabel("Desde :"),retriccionesAvanzado);
	    retriccionesAvanzado.anchor=GridBagConstraints.EAST;
	    retriccionesAvanzado.gridx=0;
	    panelRepAvanzados.add(campoFechaNacimiento3,retriccionesAvanzado);
		retriccionesAvanzado.gridy++;
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.WEST;
	    
		panelRepAvanzados.add(new JLabel("Hasta :"),retriccionesAvanzado);
		retriccionesAvanzado.gridx=0;
		retriccionesAvanzado.anchor=GridBagConstraints.EAST;
		panelRepAvanzados.add(campoFechaNacimiento4,retriccionesAvanzado);
		
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
		a.setExtendedState(a.MAXIMIZED_BOTH);

		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public class Manejador implements ActionListener
	{		
		
		public void actionPerformed(ActionEvent evento)
		{
			if (evento.getSource()==habilitar);
			if (evento.getSource()== botonGenerarReporte)
				{
				//tablas.getSelectedItem();
				
				controladorReporte.equals(atributos.getSelectedItem());
				
				
				
				
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
