package Documento.Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import  javax.swing.*;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

import java.io.File;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;
import GestionDocumento.Gui.*;



public class GuiCatalogarModificar extends JScrollPane{
	
	private GuiIngresarArea ingresarAreaNueva;
	private GuiIngresarPalabraClave ingresarPalabraNueva;
	private GuiIngresarAutor ingresarAutorNuevo;
	private GuiIngresarTipoMaterial ingresarTipoNuevo;
	
	String idiomasDisponibles [] = {"Ingles", "Español","Frances", "Aleman", "Portuges"};
	String derechosAutorDisponibles [] = {"Si", "No"};
	String loginCatalogador;
	private JPanel panel,panelFecha,panel2,panel3,panel4,panel5,panelConAutores,panelConpalabrasC,panel8,panelConAreas;
	JScrollPane  panelScrollAreas,panelScrollAutores,panelScrollPalabras;
	
	private JLabel tipoMaterial,tituloPrincipal,idioma,autor,
	tituloSecundario,/*traducido,*/editorial,derechosAutor,descripcion,indicacion,
	palabrasClave,fechaPublicacion,areas, enlaceDoc;
	
	private JTextArea campoDescripcion;

	private JComboBox campoPalabras,campoAutor,campoTipoMaterial,
	/*campoTraducido,*/campoIdioma,campoDerechosAutor,campoAreas;
	  
	private JTextField campoEditorial,campoNumeroIdentificacion,campoTituloSecundario,campoTituloPpal, campoEnlaceDoc;
	
	private JButton botonCatalogar,nuevaArea,nuevoTipo,nuevoAutor,nuevaPalabra, examinarDoc;
	//faltan las fechas /////////****************///
  // en caccoo falta campo editorial
	SpinnerModel model;
	Date fecha;
	JSpinner spinner;
	JSpinner.DateEditor editor; 
	
	private Vector<String> palabrasClaveVec,areasVector,autoresVector,       
	palabActualVec,areasActualVecr,autoresActualVector,AutorIdVector,AutorIdActualVector,AreasIdVector,AreasIdActualVector,
	tipoMaterialVec,formatosVector;
	
	//----------------CONTROLADORES--------------------------------
	ControladorAreaConocimiento controladorAreas ;
	ControladorAutor controladorAutor;
	ControladorPalabraClave controladorpalabrasClave;
	ControladorTipoMaterial controladorTipoMaterial;
	ControladorDocumento controladorDocumento;
	//-------------Objetos de la base de datos
	Documento doc;
	private JLabel formato;
	private JComboBox campoFormato;
	private JLabel softwareRecomendado;
	private JLabel resolucion;
	private JTextField campoResolucion;
	private JTextField campoSofware;
	
	public GuiCatalogarModificar(String loginIngreso) {
		
		//--------------INICIALIZAR CONTROLADORES--------------------------------
		controladorAreas = new ControladorAreaConocimiento();
	    controladorAutor= new ControladorAutor();
		controladorpalabrasClave = new ControladorPalabraClave();
		controladorTipoMaterial = new ControladorTipoMaterial() ;
		controladorDocumento= new ControladorDocumento();
		loginCatalogador = loginIngreso;
		initComponents();
	}

	public void initComponents(){
		
		//super.setTitle("Catalogar Documento");
		//super.setIconImage(new ImageIcon("LOGO1.png").getImage() );
		setBorder(BorderFactory.createTitledBorder(BorderFactory
			    .createLineBorder(Color.black), "Catalogar Documento"));
		
		
		//indicacion = new JLabel("   Catalogar Documento");
	
	   
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
		panel3= new JPanel();
		panel4= new JPanel();
		panel5= new JPanel();
		panelConAutores= new JPanel();
		panelConpalabrasC= new JPanel();
		panel8= new JPanel();
		panelConAreas= new JPanel();
		
		panelScrollAreas= new JScrollPane();
		panelScrollAutores= new JScrollPane();
		panelScrollPalabras= new JScrollPane();
		
		
		panelScrollAreas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelScrollAutores.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelScrollPalabras.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		//-------------------------------fuentes letras-------------------------
		Font font1 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		Font font2 = new Font("Book Antiqua",Font.BOLD, 15);
		
		//----------------------------------------------------
		inicializarLabels(font1);
		//--------------------------------------------------
		inicializarComboBox(font2);	
		//------------------------------------------------------------
		inicializarTexfield();
		//--------------------------------------------------
		inicializarButton();
		
		///fechaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa	    
		        model = new SpinnerDateModel();
			    spinner = new JSpinner(model);
			    editor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
			   
			    spinner.setEditor(editor);
			    ((JSpinner.DateEditor) spinner.getEditor()).getTextField().setEditable(false);

			    spinner.setFont(font2);
			    
			    panelFecha = new JPanel(new BorderLayout());
			    panelFecha.add(spinner, BorderLayout.CENTER);
			    		   
			   
			    

	    
		
		
	    //-----------------------------ponerBordeaPanel----------	
		Color coloborde = Color.black;
		panel5.setBorder(BorderFactory.createLineBorder(coloborde));
		panel4.setBorder(BorderFactory.createLineBorder(coloborde));
		panel2.setBorder(BorderFactory.createLineBorder(coloborde));
		//panelConAutores.setBorder(BorderFactory.createLineBorder(Color.yellow));
		//panelConpalabrasC.setBorder(BorderFactory.createLineBorder(Color.yellow));	
		//panelConAreas.setBorder(BorderFactory.createLineBorder(Color.yellow));

		panelConAutores.setBorder(BorderFactory.createTitledBorder(BorderFactory
			    .createLineBorder(coloborde), "Autores Actuales"));
		
		panelConpalabrasC.setBorder(BorderFactory.createTitledBorder(BorderFactory
			    .createLineBorder(coloborde), "Palabras Actuales"));
		
		panelConAreas.setBorder(BorderFactory.createTitledBorder(BorderFactory
			    .createLineBorder(coloborde), "Areas Actuales"));
		
		//Organizacion de layouts y paneles
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new FlowLayout());
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
		restriccionCampo.gridy = 2;
		restriccionCampo.ipadx=50;
		restriccionCampo.gridwidth=1;

		restriccionBotones.gridx=2;
		restriccionBotones.gridy=2;
		restriccionBotones.ipadx=20;
		
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
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=5;
		restriccionBotones.anchor= GridBagConstraints.EAST;
		restriccionBotones.gridx=1;
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
		restriccionBotones.gridx=1;
		
		
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
		panel2.add(campoSofware,restriccionCampo);	
		
		restriccionCampo.ipadx=0;
		restriccionEtiquetas.gridy=12;
		restriccionCampo.gridy=12;

		panel2.add(fechaPublicacion,restriccionEtiquetas);
		panel2.add(panelFecha,restriccionCampo);
		
		restriccionEtiquetas.gridy=13;
		restriccionCampo.gridy=13;
		restriccionCampo.ipadx=5;
		restriccionBotones.gridy=13;
		restriccionBotones.ipadx=13;
		restriccionBotones.gridx=2;

		
		panel2.add(enlaceDoc,restriccionEtiquetas);	
		panel2.add(campoEnlaceDoc,restriccionCampo);
		panel2.add(examinarDoc, restriccionBotones);
		
		
	
		//panel2.add(campoEnlaceDoc,restriccionCampo);
		//panel2.add(examinarDoc, restriccionBotones);
		
		
		panel3.add(panel2);
		
		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel5.add(scroll);
		panel.add(botonCatalogar);
		
		panel4.add(descripcion,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);
		
	
		
		panelScrollAutores.setViewportView(panelConAutores);
		panelScrollPalabras.setViewportView(panelConAreas);
		panelScrollAreas.setViewportView(panelConpalabrasC);
		
	
		panel8.add(panelScrollAutores);
		panel8.add(panelScrollPalabras);
		panel8.add(panelScrollAreas);

		panel8.setPreferredSize( new Dimension(100, 100));
		
		JPanel panel22= new JPanel();
		panel22.setLayout(new BorderLayout());
		panel22.add(panel8,BorderLayout.CENTER);
		panel22.add(panel3,BorderLayout.NORTH);		
		panel22.add(panel4,BorderLayout.SOUTH);
		this.setViewportView(panel22);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//------------------------------------------
		setSize(900,900);
		setVisible(true);
		setSize(600,800 );
		//-------------------------------------------
	
		
	}

	
	
	private void inicializarFormatos() {
		formatosVector= new Vector<String>();
		formatosVector.add("jpg");
		formatosVector.add("pdf");
		formatosVector.add("doc");
		formatosVector.add("odt");
		formatosVector.add("otro");
		
	}

	private void inicializarButton() {
		botonCatalogar= new JButton("   FINALIZAR   "); 
	    nuevaArea= new JButton("Crear Area");
	    nuevoTipo= new JButton("Crear Tipo");
	    nuevoAutor = new JButton("Crear Autor");
	    nuevaPalabra= new JButton("Crear Palabra");
	    examinarDoc= new JButton ("Examinar...");
	    
	    botonCatalogar.addActionListener(new ManejadorBoton());
	    examinarDoc.addActionListener(new ManejadorBoton(this));
	    nuevaArea.addActionListener(new ManejadorBoton());
	    nuevoTipo.addActionListener(new ManejadorBoton());
	    nuevoAutor.addActionListener(new ManejadorBoton());
	    nuevaPalabra.addActionListener(new ManejadorBoton());
	}

	private void inicializarTexfield() { 
		campoNumeroIdentificacion = new JTextField();
		campoTituloSecundario= new JTextField();    
		campoTituloPpal = new JTextField();   
		campoEditorial= new JTextField();
		campoDescripcion= new JTextArea(5,30);
		campoResolucion= new JTextField(); 
		campoSofware= new JTextField();
		campoEnlaceDoc= new JTextField(30);
		campoEnlaceDoc.setEditable(false);
		
	}

	private void inicializarLabels(Font font1) 
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
 
	    Color colorletras= new Color(10,10,10);
	   
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
		

		resolucion.setForeground(colorletras);
		softwareRecomendado.setForeground(colorletras);
		palabrasClave.setForeground(colorletras);
	    autor.setForeground(colorletras);
	    descripcion.setForeground(colorletras);
		tipoMaterial.setForeground(colorletras);
		tituloPrincipal.setForeground(colorletras);
		tituloSecundario.setForeground(colorletras);
		idioma.setForeground(colorletras);
		//traducido.setForeground(colorletras);
		editorial.setForeground(colorletras);
		derechosAutor.setForeground(colorletras);
		areas.setForeground(colorletras);
		fechaPublicacion.setForeground(colorletras);
		formato.setForeground(colorletras);
		enlaceDoc.setForeground(colorletras);
	}

	private void inicializarComboBox(Font font2) 
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
	    campoAutor.addActionListener(new ManejadorComboBox());
	    campoPalabras.addActionListener(new ManejadorComboBox());    
	    campoAreas.addActionListener(new ManejadorComboBox());
	    	    
	}

	public static void main (String args []){
		
		try
		{			
			/*NimRODTheme nt = new NimRODTheme();
			nt.setPrimary1( new Color(10,10,230));
			nt.setPrimary2( new Color(0,110,150));
			nt.setPrimary3( new Color(0,0,230));
			//nt.setPrimary(new Color(100,100,100));
			//nt.setSecondary(new Color(230, 220,250));
			nt.setSecondary1(new Color(1,0,100));
			nt.setSecondary2(new Color(0, 100,0));
			nt.setSecondary3(new Color(150,20,10));
			nt.setWhite(new Color(20, 230,250));
			nt.setBlack(new Color(250,230,250));
			
			
			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			NimRODLF.setCurrentTheme( nt);
			UIManager.setLookAndFeel( NimRODLF);*/
			//LookAndFeel n = new NimRODLookAndFeel( );
			
			//UIManager.setLookAndFeel(); 

			
			
		}
		catch (Exception e){e.printStackTrace();}
		JFrame ventana= new JFrame() ;

	
		//JScrollPane scroll= new JScrollPane();

		int ancho = 1000, alto = 600;
		
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setLocation((screenSize.width)/2-ancho/2,(screenSize.height)/2-alto/2);
		//
        //JScrollPane scroll= new JScrollPane();

        GuiCatalogarModificar ventana2;
		ventana2 = new GuiCatalogarModificar("444");
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//scroll.setViewportView(ventana2);
		//scroll.setSize(100,100);
		//scroll.setVisible(true);
		ventana.add(ventana2);
		ventana.setSize(400,400);
		ventana.setVisible(true);

	}

	private boolean validacionDeDatos() {
		if (campoNumeroIdentificacion.getText().length()<10  &&
		campoTituloSecundario.getText().length()   <50  &&
		campoTituloPpal.getText().length() <50   &&
		campoEditorial.getText().length() <30   &&
		campoDescripcion.getText().length() < 200  &&
		campoResolucion.getText().length()<15 &&
		campoSofware.getText().length()<10  &&
		!campoTituloPpal.getText().isEmpty() &&
		//!campoFormato.getText()..getSelectedIndex()   &&
		!campoDescripcion.getText().isEmpty()   
	//	campoIdioma.getSelectedIndex()!= -1
		&& !campoEnlaceDoc.getText().isEmpty()
		&& campoEnlaceDoc.getText().length()<200
		)
			
			return true;
		
		return false;
		
	}
	private class ManejadorBoton implements ActionListener 
	{
		private GuiCatalogarModificar p;
		public ManejadorBoton(GuiCatalogarModificar p){
			this.p = p;			
		}
		public ManejadorBoton(){}
		
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource()==botonCatalogar){
			if(validacionDeDatos())
			{		
			
			doc = new Documento();//null, campoIdioma.getSelectedItem(), campoDerechosAutor.getSelectedItem(), campoDescripcion.getText(), campoSofware.getText(), campoResolucion.getText(), campoEditorial.getText(), campoFormato.getSelectedItem(), campoTituloPpal.getText(), campoTituloSecundario.getText(), null, , fechaPublicacion, fechaCatalogacion, loginCatalogador, campoTipoMaterial.getSelectedItem(), AutorIdActualVector,  AreasIdActualVector,palabActualVec);
			doc.setTituloppal(campoTituloPpal.getText());
			doc.setTitulo_secundario(campoTituloSecundario.getText());
			doc.setIdioma((String) campoIdioma.getSelectedItem());
			//doc.set
			doc.setTipoMaterial((String)campoTipoMaterial.getSelectedItem());
			doc.setEditorial(campoEditorial.getText());
			doc.setFormato((String) campoFormato.getSelectedItem());
			doc.setSoftware_recomentado(campoSofware.getText());
			//---------------------------------------
			 fecha=  editor.getModel().getDate();
			 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
			 String fes= sdf.format(fecha);
			 System.out.println(java.sql.Date.valueOf(fes));
			 doc.setFecha_publicacion(java.sql.Date.valueOf(fes));
			 doc.setFecha_creacion(java.sql.Date.valueOf(fes));
			 doc.setFechaDeCatalogacion(java.sql.Date.valueOf(fes));
			 doc.setCatalogadorLogin(loginCatalogador);// el login del catalogador
			 doc.setUrl(controladorDocumento.copiarDocumento(campoEnlaceDoc.getText()));//metodo de controlador que obtenga un enlace
			 
			 controladorDocumento.catalogarDocumento(doc, AreasIdActualVector, AutorIdActualVector,palabActualVec  );
			}
			else System.out.println("no valido ");			
			
			}//if getsource
			
			if(event.getSource()==examinarDoc){
				JFileChooser manager = new JFileChooser();
				 int returnVal = manager.showSaveDialog(new JFrame());
				 if (returnVal == JFileChooser.APPROVE_OPTION) {//si selecciona guardar
						File file = manager.getSelectedFile();
						String url = file.getAbsolutePath();						
						campoEnlaceDoc.setText(url);
				 }						
			}
			if(event.getSource()==nuevaArea){				
				new GuiIngresarArea().setVisible(true);				
			}
			if(event.getSource()==nuevoTipo){
				new GuiIngresarTipoMaterial().setVisible(true);
			}
			if(event.getSource()==nuevoAutor){
				new GuiIngresarAutor().setVisible(true);
			}
			if(event.getSource()==nuevaPalabra){
				new GuiIngresarPalabraClave().setVisible(true);
			}
	}		

	}
	
	 
	private class ManejadorComboBox implements ActionListener {

		//int panel;
		//ManejadorComboBox(int i){panel = i;};
		
		public void actionPerformed(ActionEvent event) {
			

			JLabel etiqueta= new JLabel();
			
			if (event.getSource()==campoAutor)
			{
				
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
				if (areasActualVecr.indexOf(campoAreas.getSelectedItem())==-1)
				{
					
					areasActualVecr.add((String) campoAreas.getSelectedItem());			
					AreasIdActualVector.add((String) AreasIdVector.get( campoAreas.getSelectedIndex()));
					etiqueta.setText(""+campoAreas.getSelectedItem());				
					etiqueta.addMouseListener(new eventoMouse(3));			
					panelConAreas.add(etiqueta);			
					panelConAreas.updateUI();
				}
				
			
			};
			
		
		}
		
		private class eventoMouse implements MouseListener {

			int panel=0;//usado para saber de que panel es la palabra autor area o palabraclave
			
			eventoMouse (int i ){panel = i;};
			public void mouseClicked(MouseEvent arg0) {

				
				if (panel== 1)
				{
				
					JLabel refe=(JLabel) arg0.getSource();	
					

					int index = autoresActualVector.indexOf(refe.getText());
					
					autoresActualVector.remove(index);
					AutorIdActualVector.remove(index);
					
																				
					panelConAutores.remove(refe);									
					panelConAutores.updateUI();				
				}
				
				if (panel == 2 )
				{
					
					JLabel refe=(JLabel) arg0.getSource();
											
					palabActualVec.removeElement(refe.getText());														
					panelConpalabrasC.remove(refe);					
					panelConpalabrasC.updateUI();
					
					}
				
				if (panel == 3){
					JLabel refe=(JLabel) arg0.getSource();
					
					int index = areasActualVecr.indexOf(refe.getText());
					
					areasActualVecr.remove(index);
					AreasIdActualVector.remove(index);
					
					panelConAreas.remove(refe);					
					panelConAreas.updateUI();
					}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			
				JLabel refe=(JLabel) arg0.getSource();
				refe.setForeground(Color.red);
				refe.setIcon(new ImageIcon("recursos/CRUZ.gif"));
				refe.updateUI();
				
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				JLabel refe=(JLabel) arg0.getSource();
				refe.setForeground(Color.black);
				refe.setIcon(new ImageIcon(""));				
								
			} 
			
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0){}

		
		}
	
	}
}
