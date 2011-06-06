 /**
 * GuiCatalogar.java
 * 
 * Clase que representa la interfaz que permite realizar la catalogación
 * de los documentos digitales de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Luis Felipe Vargas
 * Version:   4.0
 */
package Documento.Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.TitledBorder;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import GestionDocumento.Gui.GuiIngresarArea;
import GestionDocumento.Gui.GuiIngresarAutor;
import GestionDocumento.Gui.GuiIngresarPalabraClave;
import GestionDocumento.Gui.GuiIngresarTipoMaterial;
import Utilidades.Button;
import Utilidades.Estilos;



public class GuiCatalogar extends JScrollPane{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idiomasDisponibles [] = {"Ingles", "Español","Frances", "Aleman", "Portuges"};
	String derechosAutorDisponibles [] = {"Si", "No"};
	String loginCatalogador;
	protected JPanel panel,panelFecha,panel2,panel4,panel5,panelConAutores,panelConpalabrasC,panel8,panelConAreas,panelFecha2;
	JScrollPane  panelScrollAreas,panelScrollAutores,panelScrollPalabras;

	protected JLabel tipoMaterial,tituloPrincipal,idioma,autor,
	tituloSecundario,/*traducido,*/editorial,derechosAutor,descripcion,
	palabrasClave,fechaPublicacion,areas, enlaceDoc, resolucion,softwareRecomendado, formato,fechaCreacion;

	protected JTextArea campoDescripcion;

	protected JComboBox campoPalabras,campoAutor,campoTipoMaterial,
	/*campoTraducido,*/campoIdioma,campoDerechosAutor,campoAreas, campoFormato;

	protected JTextField campoEditorial,campoTituloSecundario,campoTituloPpal, campoEnlaceDoc,
	campoSoftware, campoResolucion;

	protected Button botonCatalogar,nuevaArea,nuevoTipo,nuevoAutor,nuevaPalabra, examinarDoc;
	//faltan las fechas /////////****************///
	// en caccoo falta campo editorial
	protected SpinnerModel modelCreacion,modelPublicacion;
	protected Date fechaCreacionDate,fechaPublicacionDate;
	protected JSpinner spinnerCreacion,spinnerPublicacion;
	protected JSpinner.DateEditor editorCreacion, editorPublicacion; 

	protected  Vector<String> palabrasClaveVec,areasVector,autoresVector,       
	palabActualVec,areasActualVecr,autoresActualVector,AutorIdVector,AutorIdActualVector,AreasIdVector,AreasIdActualVector,
	tipoMaterialVec,formatosVector;

	//----------------CONTROLADORES--------------------------------
	protected ControladorAreaConocimiento controladorAreas ;
	protected ControladorAutor controladorAutor;
	protected ControladorPalabraClave controladorpalabrasClave;
	protected ControladorTipoMaterial controladorTipoMaterial;
	protected ControladorDocumento controladorDocumento;
	//-------------Objetos de la base de datos
	protected Documento doc;


	public GuiCatalogar() {
	}
	public GuiCatalogar(String loginIngreso) {

		//--------------INICIALIZAR CONTROLADORES--------------------------------
		controladorAreas = new ControladorAreaConocimiento();
		controladorAutor= new ControladorAutor();
		controladorpalabrasClave = new ControladorPalabraClave();
		controladorTipoMaterial = new ControladorTipoMaterial() ;
		controladorDocumento= new ControladorDocumento();
		this.loginCatalogador = loginIngreso;
		initComponents();

	}


	public void setBotonCatalogar(Button b){ panel.remove(this.botonCatalogar);botonCatalogar = null;
	botonCatalogar = b; panel.add(botonCatalogar);}


	protected void initComponents(){

		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Catalogar Documento");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		setBorder(borde);

		//----------------------------------------
		vectoresParaComboBox();
		//----------------------------------------
		palabActualVec= new Vector<String>();
		areasActualVecr= new Vector<String>();
		autoresActualVector= new Vector<String>();
		AreasIdActualVector= new Vector<String>();
		AutorIdActualVector= new Vector<String>();

		//----------------------------------------	
		inicializarFormatos();
		//--------------------------------------
		panel= new JPanel();
		panel2= new JPanel();
		panel4= new JPanel();
		panel5= new JPanel();
		panel8= new JPanel();
		// el boxlayout es para que las organice en fila 
		
		panelConAutores= new JPanel();
		panelConAutores.setLayout(new BoxLayout(panelConAutores, BoxLayout.Y_AXIS));
		panelConpalabrasC= new JPanel();//new BoxLayout(panelConpalabrasC, BoxLayout.Y_AXIS));
		panelConpalabrasC.setLayout(new BoxLayout(panelConpalabrasC, BoxLayout.Y_AXIS));
		panelConAreas=new JPanel();//new BoxLayout(panelConAreas, BoxLayout.Y_AXIS));
		panelConAreas.setLayout(new BoxLayout(panelConAreas, BoxLayout.Y_AXIS));
		panelScrollAreas= new JScrollPane();
		panelScrollAutores= new JScrollPane();
		panelScrollPalabras= new JScrollPane();
		
		panelScrollAutores.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Autores Documentos"));

		panelScrollPalabras.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Palabras Documentos"));

		panelScrollAreas.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Areas Documentos"));


			//----------------------------------------------------
		inicializarLabels(Estilos.fontLabels);
		//--------------------------------------------------
		inicializarComboBox(Estilos.fontSubtitulos);	
		//------------------------------------------------------------
		inicializarTexfield();
		//--------------------------------------------------
		inicializarButton();

		///fecha	    
		modelCreacion = new SpinnerDateModel();
		modelPublicacion= new SpinnerDateModel();
		spinnerCreacion = new JSpinner(modelCreacion);
		spinnerPublicacion= new JSpinner(modelPublicacion);
		editorCreacion = new JSpinner.DateEditor(spinnerCreacion, "yyyy-MM-dd");
		editorPublicacion = new JSpinner.DateEditor(spinnerPublicacion, "yyyy-MM-dd");
	
		spinnerCreacion.setEditor(editorCreacion);
		spinnerPublicacion.setEditor(editorPublicacion);
		((JSpinner.DateEditor) spinnerCreacion.getEditor()).getTextField().setEditable(false);
		((JSpinner.DateEditor) spinnerPublicacion.getEditor()).getTextField().setEditable(false);

		spinnerCreacion.setFont(Estilos.fontSubtitulos);
		spinnerPublicacion.setFont (Estilos.fontSubtitulos);
		panelFecha = new JPanel(new BorderLayout());
		panelFecha.add(spinnerCreacion, BorderLayout.CENTER);

		panelFecha2 = new JPanel(new BorderLayout());
		panelFecha2.add(spinnerPublicacion, BorderLayout.CENTER);

		//-----------------------------ponerBordeaPanel----------	
		panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel4.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));

		//Organizacion de layouts y paneles
		panel2.setLayout(new GridBagLayout());

		panel4.setLayout(new BorderLayout());
		panel8.setLayout(new GridLayout(1,3));

		GridBagConstraints restriccionCampo= new GridBagConstraints()
		,restriccionEtiquetas= new GridBagConstraints(),
		restriccionBotones= new GridBagConstraints();

		restriccionCampo.ipadx = 360;      		
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(1,40,1,0);


		restriccionEtiquetas.insets= new Insets(0,14,0,0);// espacios entre componentes
		restriccionEtiquetas.anchor=GridBagConstraints.WEST;//alinear a la izquierda
		restriccionCampo.anchor=GridBagConstraints.WEST;
		restriccionEtiquetas.gridy=0;

		panel2.add(tituloPrincipal,restriccionEtiquetas);		
		panel2.add(campoTituloPpal,restriccionCampo);

		restriccionEtiquetas.gridy=1;
		restriccionCampo.gridy = 1;

		panel2.add(tituloSecundario,restriccionEtiquetas);
		panel2.add(campoTituloSecundario,restriccionCampo);

		restriccionEtiquetas.gridy=2;
		restriccionEtiquetas.weightx=0.0;
		restriccionCampo.gridy = 2;
		restriccionCampo.ipadx=50;
		restriccionCampo.weightx=0.0;
		//restriccionCampo.gridwidth=1;

		restriccionBotones.gridx=2;
		restriccionBotones.gridy=2;
		restriccionBotones.ipadx=20;
		restriccionBotones.anchor= GridBagConstraints.WEST;

		panel2.add(autor,restriccionEtiquetas);
		panel2.add(campoAutor,restriccionCampo);		
		panel2.add(nuevoAutor,restriccionBotones);


		restriccionEtiquetas.gridy=3;
		restriccionCampo.gridy = 3;
		restriccionCampo.ipadx=90;

		panel2.add(idioma,restriccionEtiquetas);
		panel2.add(campoIdioma,restriccionCampo);


		restriccionEtiquetas.gridy=4;
		restriccionCampo.gridy = 4;

		//panel2.add(traducido,restriccionEtiquetas);
		//panel2.add(campoTraducido,restriccionCampo);

		restriccionEtiquetas.gridy=5;
		restriccionCampo.gridy = 5;
		restriccionCampo.ipadx=0;
		//restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=5;
		//restriccionBotones.anchor= GridBagConstraints.EAST;
		restriccionBotones.gridx=2;
		restriccionBotones.ipadx=10;

		panel2.add(tipoMaterial,restriccionEtiquetas);
		panel2.add(campoTipoMaterial,restriccionCampo);
		panel2.add(nuevoTipo,restriccionBotones);

		restriccionEtiquetas.gridy=6;
		restriccionCampo.gridy = 6;
		restriccionCampo.ipadx=200;
		restriccionCampo.gridwidth=2;

		panel2.add(editorial,restriccionEtiquetas);
		panel2.add(campoEditorial,restriccionCampo);

		restriccionEtiquetas.gridy=7;
		restriccionCampo.gridy = 7;
		restriccionCampo.ipadx=0;
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=7;
		restriccionBotones.ipadx=7;
		restriccionBotones.gridx=2;


		panel2.add(palabrasClave,restriccionEtiquetas);
		panel2.add(campoPalabras,restriccionCampo);
		panel2.add(nuevaPalabra,restriccionBotones);

		restriccionCampo.ipadx=7;
		restriccionEtiquetas.gridy=8;
		restriccionCampo.gridy = 8;

		panel2.add(derechosAutor,restriccionEtiquetas);
		panel2.add(campoDerechosAutor,restriccionCampo);

		restriccionEtiquetas.gridy=9;
		restriccionCampo.gridy = 9;
		restriccionCampo.ipadx=0;
		restriccionBotones.gridy=9;
		restriccionBotones.ipadx=9;
		restriccionBotones.gridx=2;

		panel2.add(areas,restriccionEtiquetas);
		panel2.add(campoAreas,restriccionCampo);
		panel2.add(nuevaArea,restriccionBotones);

		restriccionEtiquetas.gridy=10;
		restriccionCampo.gridy=10;
		panel2.add(formato,restriccionEtiquetas);	
		panel2.add(campoFormato,restriccionCampo);	

		restriccionEtiquetas.gridy=11;
		restriccionCampo.gridy=11;
		restriccionCampo.ipadx=150;

		panel2.add(softwareRecomendado,restriccionEtiquetas);	
		panel2.add(campoSoftware,restriccionCampo);	

		restriccionCampo.ipadx=0;
		restriccionEtiquetas.gridy=12;
		restriccionCampo.gridy=12;

		panel2.add(fechaCreacion,restriccionEtiquetas);
		panel2.add(panelFecha,restriccionCampo);

		restriccionEtiquetas.gridy=13;
		restriccionCampo.gridy=13;

		panel2.add(fechaPublicacion,restriccionEtiquetas);
		panel2.add(panelFecha2,restriccionCampo);

		restriccionEtiquetas.gridy=14;
		restriccionCampo.gridy=14;
		restriccionCampo.ipadx=5;
		restriccionBotones.gridy=14;
		restriccionBotones.ipadx=14;
		restriccionBotones.gridx=2;

		panel2.add(enlaceDoc,restriccionEtiquetas);	
		panel2.add(campoEnlaceDoc,restriccionCampo);
		panel2.add(examinarDoc, restriccionBotones);

		restriccionEtiquetas.gridy=15;
		restriccionCampo.gridy=15;

		panel2.add(resolucion,restriccionEtiquetas);
		panel2.add(campoResolucion,restriccionCampo);

		JScrollPane scroll = new JScrollPane(campoDescripcion);

		panel5.add(scroll);
		panel.add(botonCatalogar);

		panel4.add(descripcion,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);

		panelScrollAutores.setViewportView(panelConAutores);
		panelScrollPalabras.setViewportView(panelConpalabrasC);
		panelScrollAreas.setViewportView(panelConAreas);
				
		panel8.add(panelScrollAutores);
		panel8.add(panelScrollPalabras);
		panel8.add(panelScrollAreas);

		panel8.setPreferredSize( new Dimension(100, 100));

		JPanel panel22= new JPanel();
		panel22.setLayout(new BorderLayout(10,10));
		panel22.add(panel8,BorderLayout.CENTER);
		panel22.add(panel2,BorderLayout.NORTH);		
		panel22.add(panel4,BorderLayout.SOUTH);
		this.setViewportView(panel22);

		//------------------------------------------
		setVisible(true);
		//-------------------------------------------
	}
	protected void nuevaArea() {
		GuiIngresarArea guiArea=	new GuiIngresarArea(this);
		guiArea.setVisible(true);
		//guiArea.setAlwaysOnTop(true);			
	}
	protected void nuevoTipoMaterial() {
		GuiIngresarTipoMaterial guiMaterial = new GuiIngresarTipoMaterial(this);
		guiMaterial.setVisible(true);
	}	
	protected void nuevaAutor() {
		GuiIngresarAutor guiAutor= new GuiIngresarAutor(this);
		guiAutor.setVisible(true);
	}	
	protected void nuevaPalabra() 
	{
		GuiIngresarPalabraClave guiPalabra = new GuiIngresarPalabraClave(this);
		guiPalabra.setVisible(true );
	}	
	public void vectoresParaComboBox() {
		//---------------vectores para los ComboBox------------------
		areasVector= new Vector<String>();
		autoresVector= new Vector<String>();
		palabrasClaveVec= new Vector<String>();

		Vector<Vector<String>> contenedorIdNombreArea = controladorAreas.obtenerTodasAreas();
		areasVector =contenedorIdNombreArea.get(0);
		areasVector.remove(0);
		AreasIdVector= contenedorIdNombreArea.get(1);
		AreasIdVector.remove(0);
		contenedorIdNombreArea=null;//para destruir el vector 

		palabrasClaveVec= controladorpalabrasClave.obtenerTodasPalabrasClave();
		tipoMaterialVec= controladorTipoMaterial.obtenerTodosTiposMateriales();

		Vector<Vector<String>> contenedorIdAutor = controladorAutor.obtenerTodosAutores();
		autoresVector=contenedorIdAutor.get(0);
		AutorIdVector= contenedorIdAutor.get(1);

	}

	public void actualizarAreas() 
	{
		campoAreas.addItem(areasVector.get(areasVector.size()-1));
	}
	public void actualizarAutores() 
	{
		campoAutor.addItem(autoresVector.get(autoresVector.size()-1));
	}
	public void actualizarPalabras() 
	{
		campoPalabras.addItem(palabrasClaveVec.get(palabrasClaveVec.size()-1));
	}
	public void actualizarTipoMaterial() 
	{
		campoTipoMaterial.addItem(tipoMaterialVec.get(tipoMaterialVec.size()-1));
	}

	protected void inicializarFormatos() {
		formatosVector= new Vector<String>();
		formatosVector.add("jpg");
		formatosVector.add("pdf");
		formatosVector.add("doc");
		formatosVector.add("odt");
		formatosVector.add("otro");
	}

	protected void inicializarButton() {
		botonCatalogar= new Button("   Finalizar  "); 
		nuevaArea= new Button("Crear Area");
		nuevaArea.setIcon(new ImageIcon("recursos/iconos/add.png"));
		nuevoTipo= new Button("Crear Tipo");
		nuevoTipo.setIcon(new ImageIcon("recursos/iconos/add.png"));
		nuevoAutor = new Button("Crear Autor");
		nuevoAutor.setIcon(new ImageIcon("recursos/iconos/add.png"));
		nuevaPalabra= new Button("Crear Palabra");
		nuevaPalabra.setIcon(new ImageIcon("recursos/iconos/add.png"));
		examinarDoc= new Button ("Examinar...");

		ManejadorBoton manejador = new ManejadorBoton();
		
		botonCatalogar.addActionListener(manejador);
		examinarDoc.addActionListener(manejador);
		nuevaArea.addActionListener(manejador);
		nuevoTipo.addActionListener(manejador);
		nuevoAutor.addActionListener(manejador);
		nuevaPalabra.addActionListener(manejador);
	}

	protected void inicializarTexfield() {
		campoTituloSecundario= new JTextField();    
		campoTituloPpal = new JTextField();   
		campoEditorial= new JTextField();
		campoDescripcion= new JTextArea(5,30);
		//LImite de caracteres
		campoDescripcion.setLineWrap(true);
		campoResolucion= new JTextField(10); 
		campoSoftware= new JTextField();
		campoEnlaceDoc= new JTextField(30);
		campoEnlaceDoc.setEditable(false);

		// manejadores para el tamaño de los campos 

		ManejadorJTextField manejador = new ManejadorJTextField();
		campoDescripcion.addKeyListener(manejador);
		campoSoftware.addKeyListener(manejador);
		campoEditorial.addKeyListener(manejador);
		campoTituloPpal.addKeyListener(manejador);
		campoTituloSecundario.addKeyListener(manejador);
		campoResolucion.addKeyListener(manejador);
	}

	protected void inicializarLabels(Font font1) 
	{
		tipoMaterial= new JLabel(" Tipo De Material:  ",JLabel.LEFT);
		tituloPrincipal= new JLabel(" Titulo Principal:  *",JLabel.LEFT);
		idioma= new JLabel(" Idioma:  *",JLabel.LEFT);
		tituloSecundario= new JLabel(" Titulo Secundario:",JLabel.LEFT);
		//traducido= new JLabel(" Traducido a :",JLabel.LEFT);
		editorial= new JLabel(" Editorial:  ",JLabel.LEFT);
		derechosAutor= new JLabel(" Derechos De Autor:",JLabel.LEFT);
		descripcion= new JLabel(" Descripcion:   *",JLabel.CENTER);
		autor = new JLabel(" Autor:");
		formato = new JLabel("Formato  :  *");
		palabrasClave= new JLabel(" Palabras Clave:");
		fechaPublicacion= new JLabel("Fecha De Publicacion:");//&&&&&&
		areas= new JLabel("Areas :");
		softwareRecomendado= new JLabel("Software Para Edicion");
		resolucion= new JLabel("Resolucion");
		enlaceDoc = new JLabel("Path Documento");
		fechaCreacion= new JLabel("Fecha De Creacion");

		fechaCreacion.setFont(font1);
		resolucion.setFont(font1); 
		softwareRecomendado.setFont(font1);
		palabrasClave.setFont(font1);
		autor.setFont(font1);
		descripcion.setFont(font1);
		tipoMaterial.setFont(font1);
		tituloPrincipal.setFont(font1);
		tituloSecundario.setFont(font1);
		idioma.setFont(font1);
		//traducido.setFont(font1);
		editorial.setFont(font1);
		derechosAutor.setFont(font1);
		areas.setFont(font1);
		fechaPublicacion.setFont(font1);
		formato.setFont(font1);
		enlaceDoc.setFont(font1);

		fechaCreacion.setForeground(Estilos.colorLabels);
		resolucion.setForeground(Estilos.colorLabels);
		softwareRecomendado.setForeground(Estilos.colorLabels);
		palabrasClave.setForeground(Estilos.colorLabels);
		autor.setForeground(Estilos.colorLabels);
		descripcion.setForeground(Estilos.colorLabels);
		tipoMaterial.setForeground(Estilos.colorLabels);
		tituloPrincipal.setForeground(Estilos.colorLabels);
		tituloSecundario.setForeground(Estilos.colorLabels);
		idioma.setForeground(Estilos.colorLabels);
		//traducido.setForeground(Estilos.colorLabels);
		editorial.setForeground(Estilos.colorLabels);
		derechosAutor.setForeground(Estilos.colorLabels);
		areas.setForeground(Estilos.colorLabels);
		fechaPublicacion.setForeground(Estilos.colorLabels);
		formato.setForeground(Estilos.colorLabels);
		enlaceDoc.setForeground(Estilos.colorLabels);
	}

	protected void inicializarComboBox(Font font2) 
	{
		campoAreas= new JComboBox(areasVector);
		campoDescripcion= new JTextArea();
		//campoTraducido= new JComboBox();
		campoIdioma= new JComboBox(idiomasDisponibles);
		campoDerechosAutor= new JComboBox(derechosAutorDisponibles);
		campoTipoMaterial= new JComboBox(tipoMaterialVec);
		campoAutor = new JComboBox(autoresVector);
		campoPalabras= new JComboBox(palabrasClaveVec);
		campoFormato= new JComboBox(formatosVector);
		//-------------------font combobox--------------------------
		campoFormato.setFont(font2);
		campoPalabras.setFont(font2);
		campoAreas.setFont(font2);
		campoDescripcion.setFont(font2);
		//campoTraducido.setFont(font2);
		campoIdioma.setFont(font2);
		campoDerechosAutor.setFont(font2);
		campoTipoMaterial.setFont(font2);
		campoAreas.setFont(font2);
		campoAutor.setFont(font2);
		campoPalabras.setFont(font2);
		//---------------------------------------------------------		
		ManejadorComboBox manejador = new ManejadorComboBox();
		campoAutor.addActionListener(manejador);
		campoPalabras.addActionListener(manejador);    
		campoAreas.addActionListener(manejador);
		
		campoAreas.setSelectedIndex(-1);
		campoAutor.setSelectedIndex(-1);
		campoPalabras.setSelectedIndex(-1);
	}

	protected class ManejadorBoton implements ActionListener 
	{
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource()==botonCatalogar){
				doc = new Documento();//null, campoIdioma.getSelectedItem(), campoDerechosAutor.getSelectedItem(), campoDescripcion.getText(), campoSoftware.getText(), campoResolucion.getText(), campoEditorial.getText(), campoFormato.getSelectedItem(), campoTituloPpal.getText(), campoTituloSecundario.getText(), null, , fechaPublicacion, fechaCatalogacion, loginCatalogador, campoTipoMaterial.getSelectedItem(), AutorIdActualVector,  AreasIdActualVector,palabActualVec);
				doc.setTituloppal(campoTituloPpal.getText());
				doc.setTitulo_secundario(campoTituloSecundario.getText());
				doc.setIdioma((String) campoIdioma.getSelectedItem());				
				doc.setTipoMaterial((String)campoTipoMaterial.getSelectedItem());
				doc.setEditorial(campoEditorial.getText());
				doc.setFormato((String) campoFormato.getSelectedItem());
				doc.setSoftware_recomentado(campoSoftware.getText());
				doc.setDescripcion(campoDescripcion.getText());
				doc.setResolucion(campoResolucion.getText());
				doc.setDerechosDeAutor(campoDerechosAutor.getSelectedItem().toString());
				//--------------------------------------
				//tomar fechas de splinner 
				fechaCreacionDate=  editorCreacion.getModel().getDate();
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
				String fes= sdf.format(fechaCreacionDate);			 
				fechaPublicacionDate=  editorPublicacion.getModel().getDate();
				SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");  
				String fes2= sdf2.format(fechaPublicacionDate);
				doc.setFecha_publicacion(java.sql.Date.valueOf(fes2));
				
				java.util.Date fechaactual = new Date();// fecha actual 
				SimpleDateFormat sdf3= new SimpleDateFormat("yyyy-MM-dd");  
				String fesActual= sdf3.format(fechaactual);
				
				doc.setFecha_creacion(java.sql.Date.valueOf(fes));
				doc.setFechaDeCatalogacion(java.sql.Date.valueOf(fesActual));
				doc.setCatalogadorLogin(loginCatalogador);// el login del catalogador
				doc.setUrl(campoEnlaceDoc.getText());//se envia el url de origen
				if(controladorDocumento.catalogarDocumento(doc, AreasIdActualVector, AutorIdActualVector,palabActualVec  )>=1)
				{
					JOptionPane.showMessageDialog(null, "El documento fue catalogado correctamente");
					limpiarCampos();
				}			
			}//if getsource

			if(event.getSource()==examinarDoc){
				JFileChooser manager = new JFileChooser();
				manager.setDialogTitle("Seleccionar documento a catalogar");
				manager.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = manager.showSaveDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {//si selecciona guardar
					File file = manager.getSelectedFile();
					String url = file.getAbsolutePath();
					campoEnlaceDoc.setText(url);
				}						
			}
			if(event.getSource()==nuevaArea){				
				nuevaArea();
			}
			if(event.getSource()==nuevoTipo){
				nuevoTipoMaterial();
			}
			if(event.getSource()==nuevoAutor){
				nuevaAutor();
			}
			if(event.getSource()==nuevaPalabra){
				nuevaPalabra();
			}
		}
	} 
	protected class ManejadorComboBox implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			JLabel etiqueta= new JLabel();

			if (event.getSource()==campoAutor)
			{	
				if(campoAutor.getSelectedIndex()==-1)return;
				if (autoresActualVector.indexOf(campoAutor.getSelectedItem())==-1)
				{
					autoresActualVector.add((String) campoAutor.getSelectedItem());		

					//AutorIdActualVector.add((String) campoAutor.getSelectedItem());		
					AutorIdActualVector.add((String) AutorIdVector.get( campoAutor.getSelectedIndex()));

					etiqueta.setText(""+campoAutor.getSelectedItem());			
					etiqueta.addMouseListener(new eventoMouse(1));			
					panelConAutores.add(etiqueta);			
					panelConAutores.updateUI();

				}
			};
			if (event.getSource()== campoPalabras)
			{	
				if(campoPalabras.getSelectedIndex()==-1)return;
				if (palabActualVec.indexOf(campoPalabras.getSelectedItem())==-1)
				{
					palabActualVec.add((String) campoPalabras.getSelectedItem());		
					etiqueta.setText(""+campoPalabras.getSelectedItem());			
					etiqueta.addMouseListener(new eventoMouse(2));			
					panelConpalabrasC.add(etiqueta);			
					panelConpalabrasC.updateUI();
				}
			}

			if (event.getSource()==campoAreas)
			{
				if(campoAreas.getSelectedIndex()==-1)return;
				if (areasActualVecr.indexOf(campoAreas.getSelectedItem())==-1)
				{
					areasActualVecr.add((String) campoAreas.getSelectedItem());			
					AreasIdActualVector.add((String) AreasIdVector.get( campoAreas.getSelectedIndex()));
					etiqueta.setText(""+campoAreas.getSelectedItem());				
					etiqueta.addMouseListener(new eventoMouse(3));			
					panelConAreas.add(etiqueta);			
					panelConAreas.updateUI();
				}
			}
		}
	}

	protected class eventoMouse implements MouseListener {

		int panel=0;//usado para saber de que panel es la palabra autor area o palabraclave

		eventoMouse (int i ){panel = i;};
		public void mouseClicked(MouseEvent e) {

			if (panel== 1)
			{
				JLabel refe=(JLabel) e.getSource();	

				int index = autoresActualVector.indexOf(refe.getText());
				autoresActualVector.remove(index);
				AutorIdActualVector.remove(index);
				panelConAutores.remove(refe);									
				panelConAutores.updateUI();				
			}
			if (panel == 2 )
			{
				JLabel refe=(JLabel) e.getSource();

				palabActualVec.removeElement(refe.getText());														
				panelConpalabrasC.remove(refe);					
				panelConpalabrasC.updateUI();
			}
			if (panel == 3){
				JLabel refe=(JLabel) e.getSource();
				int index = areasActualVecr.indexOf(refe.getText());
				areasActualVecr.remove(index);
				AreasIdActualVector.remove(index);
				panelConAreas.remove(refe);					
				panelConAreas.updateUI();
			}
		}

		public void mouseEntered(MouseEvent e) {

			JLabel refe=(JLabel) e.getSource();
			refe.setForeground(Color.red);
			refe.setIcon(new ImageIcon("recursos/iconos/CRUZ.gif"));
			refe.updateUI();
		}

		public void mouseExited(MouseEvent e) {

			JLabel refe=(JLabel) e.getSource();
			refe.setForeground(Color.black);
			refe.setIcon(new ImageIcon(""));
		} 

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e){}
	}
	protected class ManejadorJTextField implements KeyListener{

		public void keyPressed(KeyEvent e) {

			if(e.getSource()== campoTituloPpal || e.getSource()== campoTituloSecundario){
				if(new String(campoTituloPpal.getText()).length()>49)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoTituloPpal.setText(new String(campoTituloPpal.getText()).substring(0,49));
					}
				}
				if(new String(campoTituloSecundario.getText()).length()>49)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoTituloSecundario.setText(new String(campoTituloSecundario.getText()).substring(0,49));
					}
				}
			}

			if(e.getSource()== campoEditorial)
			{
				if(new String(campoEditorial.getText()).length()>29)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoEditorial.setText(new String(campoEditorial.getText()).substring(0,29));
					}
				}		
			}
			if(e.getSource()== campoSoftware)
			{
				if(new String(campoSoftware.getText()).length()>19)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoSoftware.setText(new String(campoSoftware.getText()).substring(0,19));
					}
				}		
			}
			if(e.getSource()== campoDescripcion)
			{
				if(new String(campoDescripcion.getText()).length()>199)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoDescripcion.setText(new String(campoDescripcion.getText()).substring(0,199));
					}
				}		
			}
			if(e.getSource()== campoResolucion)
			{
				if(new String(campoResolucion.getText()).length()>14)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoResolucion.setText(new String(campoResolucion.getText()).substring(0,14));
					}
				}		
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	public void limpiarCampos(){
		campoEditorial.setText("");
		campoTituloSecundario.setText("");
		campoTituloPpal.setText("");
		campoEnlaceDoc.setText("");
		campoSoftware.setText("");
		campoResolucion.setText("");
		campoDescripcion.setText("");
		campoPalabras.setSelectedIndex(-1);
		campoAutor.setSelectedIndex(-1);
		campoTipoMaterial.setSelectedIndex(0);
		campoIdioma.setSelectedIndex(0);
		campoDerechosAutor.setSelectedIndex(0);
		campoAreas.setSelectedIndex(-1);
		campoFormato.setSelectedIndex(0);
		panelConAutores.removeAll();									
		panelConAutores.updateUI();
		panelConpalabrasC.removeAll();
		panelConpalabrasC.updateUI();
		panelConAreas.removeAll();
		panelConAreas.updateUI();       
		palabActualVec.removeAllElements();
		areasActualVecr.removeAllElements();
		autoresActualVector.removeAllElements();
		AutorIdActualVector.removeAllElements();
		AreasIdActualVector.removeAllElements();
		
		//fechas
				
		modelCreacion=null;
		modelPublicacion=null;
		editorCreacion=null;
		editorPublicacion=null;
		
		modelCreacion = new SpinnerDateModel(new java.util.Date(),null,null,Calendar.DAY_OF_YEAR);
		modelPublicacion= new SpinnerDateModel(new java.util.Date(),null,null,Calendar.DAY_OF_YEAR);
		spinnerCreacion.setModel(modelCreacion);
		spinnerPublicacion.setModel(modelCreacion);
		editorCreacion = new JSpinner.DateEditor(spinnerCreacion, "yyyy-MM-dd");
		editorPublicacion = new JSpinner.DateEditor(spinnerPublicacion, "yyyy-MM-dd");
		
		editorCreacion.getTextField().setEditable(false);
		editorPublicacion.getTextField().setEditable(false);
		spinnerCreacion.setEditor(editorCreacion);		
		spinnerPublicacion.setEditor(editorPublicacion);
	}
}