package Documento.Gui;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import Documento.Controlador.ControladorDocumento;
import Documento.Gui.GuiCatalogar.eventoMouse;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;
import Principal.Gui.GuiPrincipal;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarDoc  extends GuiCatalogar
{

	private String loginModificador;
	private Button botonModificar;

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
		
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Modificar Documento");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		setBorder(borde);
		
		botonModificar= new Button("Modificar");
		setBotonCatalogar(botonModificar);
		this.botonModificar.addActionListener(new ManejadorBotonModificar());
		
		this.examinarDoc.setEnabled(false);
		this.panel2.remove(this.examinarDoc);
		
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
		
		

		initAreasActuales();
		initAutoresActuales();
		initPalabrasActuales();
		
		
		// NO he mirado que pasa con los null por esos las comillas 
		// aunque lso null se guarda como "" 
	}

	private void initPalabrasActuales() {
		
		Vector<PalabraClave> vectorResult = this.controladorpalabrasClave.obtenerPalabrasClaveDocumento(doc.getId_doc());

		for (int i=0;i< vectorResult.size();i++)
		{
			PalabraClave palabraClave =vectorResult.get(i);
			palabActualVec.add(palabraClave.getNombre());			
			JLabel etiqueta = new JLabel(palabraClave.getNombre());
			etiqueta.addMouseListener(new eventoMouse(1));
			panelConpalabrasC.add(etiqueta);
		}
		
		panelConpalabrasC.updateUI();
		
	}

	private void initAutoresActuales() {
		

		Vector<Autor> vectorResult = this.controladorAutor.obtenerAutoresDocumento(doc.getId_doc());

		for (int i=0;i< vectorResult.size();i++)
		{
			Autor autor =vectorResult.get(i);
			autoresActualVector.add(autor.getNombre());	
			AutorIdActualVector.add(autor.getId());
			JLabel etiqueta = new JLabel(autor.getNombre());
			etiqueta.addMouseListener(new eventoMouse(1));		
			
			panelConAutores.add(etiqueta);
		}
		
		panelConAreas.updateUI();
	}

	private void initAreasActuales() {
		Vector<AreaConocimiento> vectorResult = this.controladorAreas.obtenerAreasDocumento(doc.getId_doc());

		for (int i=0;i< vectorResult.size();i++)
		{
			AreaConocimiento area =vectorResult.get(i);
			areasActualVecr.add(area.getNombre());	
			AreasIdActualVector.add(area.getIdArea());
			
			JLabel etiqueta = new JLabel(area.getNombre());
			etiqueta.addMouseListener(new eventoMouse(1));		
			
			panelConAreas.add(etiqueta);
		}
		
		panelConAreas.updateUI();
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
		ControladorDocumento da = new ControladorDocumento();
		
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.add(new GuiModificarDoc("admin",da.obtenerDocumento("10000")));


		a.setSize(500,500);
		a.setVisible(true);

	}
	

	protected class ManejadorBotonModificar implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			doc.setTituloppal(campoTituloPpal.getText());
			doc.setTitulo_secundario(campoTituloSecundario.getText());
			doc.setIdioma((String) campoIdioma.getSelectedItem());
			doc.setTipoMaterial((String) campoTipoMaterial.getSelectedItem());
			doc.setEditorial(campoEditorial.getText());
			doc.setFormato((String) campoFormato.getSelectedItem());
			doc.setSoftware_recomentado(campoSoftware.getText());
			doc.setDescripcion(campoDescripcion.getText());
			doc.setResolucion(campoResolucion.getText());
			doc.setDerechosDeAutor(campoDerechosAutor.getSelectedItem()
					.toString());
			// --------------------------------------
			// tomar fechas de splinner
			fecha = editor.getModel().getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fes = sdf.format(fecha);
			fecha2 = editor.getModel().getDate();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String fes2 = sdf2.format(fecha2);
			doc.setFecha_publicacion(java.sql.Date.valueOf(fes));
			///java.util.Date fechaactual = new Date();// fecha actual
			//SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			//String fes3 = sdf3.format(fechaactual);
			doc.setFecha_creacion(java.sql.Date.valueOf(fes2));
			if (controladorDocumento.modificarDatosDocumento(doc,
					AreasIdActualVector, AutorIdActualVector, palabActualVec) >= 1) {
				JOptionPane.showMessageDialog(null,
						"El documento fue modificado correctamente");
			}

		}
	}
	
	


}
