package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import Documento.Controlador.ControladorDocumento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import Principal.Gui.GuiPrincipal;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

public class GuiReportes extends JTabbedPane{
	
	JPanel PanelreportesBasicos,panelRepAvanzados; 
	
	JLabel etiquetaTabla,etiquetaAtributo,etiquetaCondicion;
	JComboBox tablas , atributos , condicion;
	ControladorAreaConocimiento controladorArea;
	ControladorAutor controladorAutor ;
	ControladorTipoMaterial controladorTipoMaterial;
	ControladorDocumento controladorDocumento; 
	GuiReportes()
	{
		initComponents();
	}

	private void initComponents() {
		PanelreportesBasicos= new JPanel(new GridBagLayout());
		panelRepAvanzados= new JPanel();
		tablas = new JComboBox();
		atributos = new JComboBox();
		condicion= new JComboBox();
		etiquetaTabla= new JLabel("TABLA");
		etiquetaAtributo= new JLabel("CAMPOS DE LA TABLA");
		etiquetaCondicion= new JLabel("CONDICION");

	
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
		a.setSize(200, 200);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
