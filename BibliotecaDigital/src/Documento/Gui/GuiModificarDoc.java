 /**
 * GuiModificarDoc.java
 * 
 * Clase que representa la interfaz que permite realizar la modificación
 * de los documentos digitales de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Luis Felipe Vargas
 * Autor:     Edgar Andres Moncada
 * Version:   4.0
 */
package Documento.Gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

import Consultas.Controlador.ControladorConsulta;
import Consultas.Gui.GuiConsultaAvanzada;
import Consultas.Gui.GuiConsultaBasica;
import Consultas.Gui.GuiResultadoConsulta;
import Consultas.Gui.GuiVistaDetalladaConsulta;
import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;
import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * Clase que permite manejar los datos del los documentos que son modificados
 * por los usuarios con perfil de catalogador y con perfil de administrador
 * de la Biblioteca Digital.
 * 
 * @author Luis Felipe Vargas
 * @author Edgar Andres Moncada
 * 
 */
public class GuiModificarDoc  extends GuiCatalogar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Button botonModificar;
	private JLabel campoFechaCatalogacion, fechaCatalogacion;

	public GuiModificarDoc() {}

	public GuiModificarDoc (Documento doc) 
	{
		
		//--------------INICIALIZAR CONTROLADORES--------------------------------
		controladorAreas = new ControladorAreaConocimiento();
		controladorAutor= new ControladorAutor();
		controladorpalabrasClave = new ControladorPalabraClave();
		controladorTipoMaterial = new ControladorTipoMaterial() ;
		controladorDocumento= new ControladorDocumento();
		initComponents();
		this.doc=doc;
	
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
		this.panelConDatos.remove(this.examinarDoc);
		this.panelConDatos.remove(this.formato);
		this.panelConDatos.remove(this.campoFormato);
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
		this.campoDerechosAutor.setSelectedItem(doc.getDerechosDeAutor());
		this.campoFormato.setSelectedItem(doc.getFormato());
		this.campoIdioma.setSelectedItem(doc.getIdioma());
		initLabels(Estilos.fontLabels);
		initFechas();
		initAreasActuales();
		initAutoresActuales();
		initPalabrasActuales();
	}
	
	private void initLabels(Font font1){
		fechaCatalogacion = new JLabel("Fecha de Catalogacion: ");
		campoFechaCatalogacion = new JLabel(doc.getFechaDeCatalogacion().toString());
		fechaCatalogacion.setFont(font1);
		fechaCatalogacion.setForeground(Estilos.colorLabels);
		campoFechaCatalogacion.setFont(font1);
		campoFechaCatalogacion.setForeground(Estilos.colorLabels);
		GridBagConstraints restriccionCampo= new GridBagConstraints()
		,restriccionEtiquetas= new GridBagConstraints();     		
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.insets = new Insets(1,40,1,0);
		restriccionCampo.anchor=GridBagConstraints.WEST;
		restriccionCampo.gridy=16;
		restriccionCampo.ipadx=5;
		
		restriccionEtiquetas.insets= new Insets(0,14,0,0);// espacios entre componentes
		restriccionEtiquetas.anchor=GridBagConstraints.WEST;//alinear a la izquierda		
		restriccionEtiquetas.gridy=16;		
		panelConDatos.add(fechaCatalogacion, restriccionEtiquetas);
		panelConDatos.add(campoFechaCatalogacion, restriccionCampo);
		
	}
	
	private void initFechas(){
		this.panelFecha.remove(this.spinnerCreacion);
		this.panelFecha2.remove(this.spinnerPublicacion);
		
		//spiner que obtiene la fecha de creacion
		Date fechaCreacion = doc.getFecha_creacion();
		this.modelCreacion= new SpinnerDateModel(fechaCreacion,null,null,Calendar.DAY_OF_YEAR);
		this.spinnerCreacion = new JSpinner(modelCreacion);
		this.spinnerCreacion.setFont(Estilos.fontLabels);
		this.spinnerCreacion.setForeground(Estilos.colorLabels);
		this.editorCreacion= new JSpinner.DateEditor(this.spinnerCreacion,"yyyy-MM-dd");
		this.spinnerCreacion.setEditor(editorCreacion);
	    ((JSpinner.DateEditor) this.spinnerCreacion.getEditor()).getTextField().setEditable(false);
	    
	    Date fechaPublicacion = doc.getFecha_publicacion();
	    this.modelPublicacion = new SpinnerDateModel(fechaPublicacion,null,null,Calendar.DAY_OF_YEAR);
		this.spinnerPublicacion = new JSpinner(modelPublicacion);
		this.spinnerPublicacion.setFont(Estilos.fontLabels);
		this.spinnerPublicacion.setForeground(Estilos.colorLabels);
		this.editorPublicacion = new JSpinner.DateEditor(this.spinnerPublicacion,"yyyy-MM-dd");
		this.spinnerPublicacion.setEditor(editorPublicacion);
	    ((JSpinner.DateEditor) this.spinnerPublicacion.getEditor()).getTextField().setEditable(false);
	    
	    this.panelFecha.add(this.spinnerCreacion, BorderLayout.CENTER);
	    this.panelFecha2.add(this.spinnerPublicacion, BorderLayout.CENTER);
	}

	private void initPalabrasActuales() {		
		Vector<PalabraClave> vpc = doc.getPalabrasClave();		
		for(int i =0; i < vpc.size();i++){
			PalabraClave palabra = vpc.get(i);
			palabActualVec.add(palabra.getNombre());
			JLabel etiqueta = new JLabel(vpc.get(i).getNombre());
			etiqueta.addMouseListener(new eventoMouse(2));
			panelConpalabrasC.add(etiqueta);
		}
		panelConpalabrasC.updateUI();
		
	}

	private void initAutoresActuales() {
		Vector<Autor> va = doc.getAutores();
		for (int i=0;i< va.size();i++)
		{
			Autor autor =va.get(i);
			autoresActualVector.add(autor.getNombre());
			AutorIdActualVector.add(autor.getId());
			JLabel etiqueta = new JLabel(autor.getNombre());
			etiqueta.addMouseListener(new eventoMouse(1));
			panelConAutores.add(etiqueta);
		}		
		panelConAreas.updateUI();
	}

	private void initAreasActuales() {
		Vector<AreaConocimiento> vac = doc.getAreas();
		for (int i=0;i< vac.size();i++)
		{
			AreaConocimiento area =vac.get(i);
			areasActualVecr.add(area.getNombre());	
			AreasIdActualVector.add(area.getIdArea());			
			JLabel etiqueta = new JLabel(area.getNombre());
			etiqueta.addMouseListener(new eventoMouse(3));		
			panelConAreas.add(etiqueta);
			}		
		panelConAreas.updateUI();
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
			fechaCreacionDate = editorCreacion.getModel().getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fes = sdf.format(fechaCreacionDate);
			fechaPublicacionDate = editorPublicacion.getModel().getDate();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String fes2 = sdf2.format(fechaPublicacionDate);
			doc.setFecha_creacion(java.sql.Date.valueOf(fes));
			doc.setFecha_publicacion(java.sql.Date.valueOf(fes2));
			if (controladorDocumento.modificarDatosDocumento(doc,
					AreasIdActualVector, AutorIdActualVector, palabActualVec) >= 1) {
				JOptionPane.showMessageDialog(null,
						"El documento fue modificado correctamente");
				ControladorConsulta conConsulta = new ControladorConsulta();
				
				Documento d  = conConsulta.obtenerDatosDocumento(doc.getId_doc());
				
				
				if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
				{
					GuiConsultaBasica.PANEL_VISTA_DETALLADA_CONSULTA = new GuiVistaDetalladaConsulta(d);
					GuiConsultaBasica.ponerDescripcion();
					
					if(GuiConsultaBasica.TIPO_USUARIO == 2)
					{
						GuiCatalogador.cambiarPanelVista();
					}
					if(GuiConsultaBasica.TIPO_USUARIO == 3)
					{				
						GuiAdministrador.cambiarPanelVista();
					}	
				}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
				{
					GuiConsultaAvanzada.PANEL_VISTA_DETALLADA_CONSULTA = new GuiVistaDetalladaConsulta(d);
					GuiConsultaAvanzada.ponerDescripcion();
					if(GuiConsultaBasica.TIPO_USUARIO == 2)
					{
						GuiCatalogador.cambiarPanelVistaAvanzado();
					}
					if(GuiConsultaBasica.TIPO_USUARIO == 1)
					{				
						GuiAdministrador.cambiarPanelVistaAvanzado();
					}
				}
				
			}
		}
	}
}
