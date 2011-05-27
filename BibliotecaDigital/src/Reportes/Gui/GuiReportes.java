package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	int cantCondicion=0;
	private JSpinner campoFechaNacimiento;
	private JSpinner campoFechaNacimiento2;
	private Vector<String> vectorFechas;
	
	GuiReportes()
	{
		initComponents();
	}

	private void initComponents() {
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Reportes Usuarios::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		

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
		panelRepAvanzados= new JPanel();
		tablas = new JComboBox(vectorContablas);
		atributos = new JComboBox(vectorAtributos);
		condicion= new JComboBox(vectorCondiciones);
		etiquetaTabla= new JLabel("TABLA");
		etiquetaAtributo= new JLabel("CAMPOS DE LA TABLA");
		etiquetaCondicion= new JLabel("CONDICION");
		botonGenerarReporte= new Button("Generar Reporte");
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
	  //Crear spinner para la fecha de nacimiento.
		SpinnerModel modeloFecha2 = new SpinnerDateModel();
		campoFechaNacimiento2 = new JSpinner(modeloFecha2);
	    campoFechaNacimiento2.setFont(Estilos.fontLabels);
	    campoFechaNacimiento2.setForeground(Estilos.colorLabels);
		JSpinner.DateEditor spinnerFecha2 = new JSpinner.DateEditor(campoFechaNacimiento2,"yyyy-MM-dd");
		campoFechaNacimiento2.setEditor(spinnerFecha2);
	    ((JSpinner.DateEditor) campoFechaNacimiento.getEditor()).getTextField().setEditable(true);
	    
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
		PanelreportesBasicos.add(nuevaCondicion,retricciones);
		retricciones.gridy++;
		
		//PanelreportesBasicos.add(condicion,retricciones);
		retricciones.gridwidth=1;
		retricciones.gridy=retricciones.gridy+10;
		retricciones.gridx=1;
		
		retricciones.insets= new Insets(100, 0, 0, 0);		
		PanelreportesBasicos.add(botonGenerarReporte,retricciones);
	
		
		retricciones.insets= new Insets(0, 0, 0, 0);

		scroll.setViewportView(PanelreportesBasicos);

		addTab("Reportes Basicos",scroll );
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
			nuevaCondicon();
			
		}	
	}

	public void nuevaCondicon() 
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
	}
	
}
