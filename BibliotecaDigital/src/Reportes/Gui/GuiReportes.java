package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
	
	JLabel etiquetaTabla,etiquetaAtributo,etiquetaCondicion;
	JComboBox tablas , atributos , condicion;
	Button botonGenerarReporte;
	Vector<String> vectorContablas;
	ControladorAreaConocimiento controladorArea;
	ControladorAutor controladorAutor ;
	ControladorTipoMaterial controladorTipoMaterial;
	ControladorDocumento controladorDocumento; 
	GuiReportes()
	{
		initComponents();
	}

	private void initComponents() {
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Reportes Basicos::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		

		vectorContablas= new Vector<String>();
		vectorContablas.add("Usuario");
		vectorContablas.add("Autores");
		vectorContablas.add("Areas");
		
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		PanelreportesBasicos.setBorder(borde);
		PanelreportesBasicos.setBackground(Color.WHITE);
		panelRepAvanzados= new JPanel();
		tablas = new JComboBox(vectorContablas);
		atributos = new JComboBox();
		condicion= new JComboBox();
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
		
		GridBagConstraints retricciones= new GridBagConstraints();
		retricciones.insets= new Insets(0, 0, 20, 40);
		retricciones.gridy=0;
		PanelreportesBasicos.add(etiquetaTabla,retricciones);
		PanelreportesBasicos.add(tablas,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaAtributo,retricciones);
		PanelreportesBasicos.add(atributos,retricciones);
		retricciones.gridy++;
		PanelreportesBasicos.add(etiquetaCondicion,retricciones);
		PanelreportesBasicos.add(condicion,retricciones);
		retricciones.gridy=retricciones.gridy+4;
		retricciones.gridx=1;
		retricciones.insets= new Insets(100, 0, 0, 0);		
		PanelreportesBasicos.add(botonGenerarReporte,retricciones);
		
		
		
		addTab("Reportes Basicos",PanelreportesBasicos );
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
		a.setSize(600, 500);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
