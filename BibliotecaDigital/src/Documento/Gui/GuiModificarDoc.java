package Documento.Gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import Principal.Gui.GuiPrincipal;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarDoc  extends GuiCatalogarModificar
{

	private String loginModificador;
	
	
	public GuiModificarDoc() 
	{
		
		
	}

	public GuiModificarDoc (String loginIngreso,Documento doc) 
	{
		
		//--------------INICIALIZAR CONTROLADORES--------------------------------
		controladorAreas = new ControladorAreaConocimiento();
	    controladorAutor= new ControladorAutor();
		controladorpalabrasClave = new ControladorPalabraClave();
		controladorTipoMaterial = new ControladorTipoMaterial() ;
		controladorDocumento= new ControladorDocumento();
		
		this.loginModificador = loginIngreso;
		this.doc=doc;
		initComponents();
		this.botonCatalogar.setText("Modificar");
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Modificar Documento");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		setBorder(borde);
		
		initDocumentInfo();
	}
	
	private void initDocumentInfo() 
	{

		campoTituloSecundario.setText(""+doc.getTitulo_secundario());    
		campoTituloPpal.setText(""+doc.getTituloppal());   
		campoEditorial.setText(""+doc.getEditorial());
		campoDescripcion.setText(""+doc.getDescripcion());
		campoResolucion.setText(""+doc.getResolucion()); 
		campoSoftware.setText(""+doc.getSoftware_recomentado());
		campoEnlaceDoc.setText(""+doc.getUrl());

		// NO he mirado que pasa con los null por esos las comillas 
		// aunque lso null se guarda como "" 
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
	
		JFrame  a = new JFrame();
		
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.add(new GuiModificarDoc(new String(),new Documento()));
		
		
		a.setSize(500,500);
		a.setVisible(true);

	}
	

}
