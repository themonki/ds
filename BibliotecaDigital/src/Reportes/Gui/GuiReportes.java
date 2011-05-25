package Reportes.Gui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
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
		PanelreportesBasicos= new JPanel();
		panelRepAvanzados= new JPanel();
		tablas = new JComboBox();
		
		
		
		
		
		
		
		
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
