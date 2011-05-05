package Documento.Gui;


import java.awt.BorderLayout;
import java.awt.Color;
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
import java.sql.Date;
import java.util.Vector;

import  javax.swing.*;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Controlador.ControladorTipoMaterial;



public class GuiCatalogarModificar extends JFrame{
	

	private JPanel panel,panel2,panel3,panel4,panel5,panelConAutores,panelConpalabrasC,panel8,panelConAreas;
	private JLabel tipoMaterial,tituloPrincipal,idioma,autor,
	tituloSecundario,traducido,editorial,derechosAutor,descripcion,indicacion,
	palabrasClave,fechaPublicacion,areas;
	
	private JTextArea campoDescripcion;

	private JComboBox campoPalabras,campoAutor,campoTipoMaterial,
	campoTraducido,campoIdioma,campoDerechosAutor,campoAreas;
	  
	private JTextField campoEditorial,campoNumeroIdentificacion,campoTituloSecundario,campoTituloPpal;
	
	private JButton botonCatalogar,nuevaArea,nuevotipo,nuevoAutor,nuevoidioma,nuevapalabra;
	//faltan las fechas /////////****************///
  // en caccoo falta campo editorial
	
	private Vector<String> palabrasClaveVec,areasVector,autoresVector,       
	palabActualVec,areasActualVecr,autoresActualVector,AutorIdVector,AutorIdActualVector,AreasIdVector,AreasIdActualVector;
	
	//----------------CONTROLADORES--------------------------------
	ControladorAreaConocimiento controladorAreas ;
	ControladorAutor controladorAutor;
	ControladorPalabraClave controladorpalabrasClave;
	ControladorTipoMaterial controladorTipoMaterial;
	//-------------Objetos de la base de datos
	Documento doc;
	private JLabel formato;
	private JTextField campoFormato;
	private JLabel softwareRecomendado;
	private JLabel resolucion;
	private JTextField campoResolucion;
	private JTextField campoSofware;
	
	public GuiCatalogarModificar() {
		
		//--------------INICIALIZAR CONTROLADORES--------------------------------
		controladorAreas = new ControladorAreaConocimiento();
	    controladorAutor= new ControladorAutor();
		controladorpalabrasClave = new ControladorPalabraClave();
		controladorTipoMaterial = new ControladorTipoMaterial() ;
		
		initComponents();
	}

	public void initComponents(){
		
		//super.setTitle("Catalogar Documento");
		//super.setIconImage(new ImageIcon("LOGO1.png").getImage() );
	    indicacion = new JLabel("   Catalogar Documento");
	    controladorAreas= new ControladorAreaConocimiento();
	
	   
		//---------------vectores para los ComboBox------------------
		areasVector= new Vector<String>();
		autoresVector= new Vector<String>();
		palabrasClaveVec= new Vector<String>();
		//AreasIdVector = new Vector<String>();	
		Vector<Vector<String>> contenedorIdNombreArea = controladorAreas.obtenerTodasAreas();
		areasVector =contenedorIdNombreArea.get(0);
		AreasIdVector= contenedorIdNombreArea.get(1); 
		contenedorIdNombreArea=null;//para destruir el vector 
		
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
		
		panel= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel4= new JPanel();
		panel5= new JPanel();
		panelConAutores= new JPanel();
		panelConpalabrasC= new JPanel();
		panel8= new JPanel();
		panelConAreas= new JPanel();
		//-------------------------------fuentes letras-------------------------
		Font font1 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		Font font2 = new Font("Book Antiqua",Font.BOLD, 15);
		Font font3 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
		
		//----------------------------------------------------
		inicializarLabels(font1);
		//--------------------------------------------------
		inicializarComboBox(font2);	
		//------------------------------------------------------------
		inicializarTexfield();
		///--------------------------------------------------
		inicializarButton();
		
	   
	    //-----------labels locales-----------------------
	    JLabel 
		autores= new JLabel("           Lista De Autores Actual.           ",JLabel.CENTER),
		palabrasC= new JLabel("         Palabras Clave Actuales.          "),
		areaPertenece= new JLabel("          Areas Actuales.              ");
	    indicacion = new JLabel("   Catalogar Documento",JLabel.CENTER);
		Color colorletras= new Color(0,50,10);
		areaPertenece.setFont(font2);
		palabrasC.setFont(font2);
		autores.setFont(font2);
		indicacion.setFont(font3);
		areaPertenece.setForeground(colorletras);
		palabrasC.setForeground(colorletras);
		autores.setForeground(colorletras);
		indicacion.setForeground(new Color(0,50,0));
		indicacion.setBackground(Color.red);
		
		
	    //-----------------------------ponerBordeaPanel----------	
		
		panel5.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel4.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panelConAutores.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panelConpalabrasC.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panelConAreas.setBorder(BorderFactory.createLineBorder(Color.yellow));

		
		//Organizacion de layouts y paneles
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new BorderLayout());
		panel8.setLayout(new GridLayout(3,1,30,10));
		
		GridBagConstraints restriccionCampo= new GridBagConstraints()
		,restriccionEtiquetas= new GridBagConstraints(),
		restriccionBotones= new GridBagConstraints();
		
		restriccionCampo.ipadx = 360;      		
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(2,40,2,0);
		
		
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
		restriccionCampo.ipadx=140;
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
		
		panel2.add(traducido,restriccionEtiquetas);
		panel2.add(campoTraducido,restriccionCampo);
		
		restriccionEtiquetas.gridy=5;
		restriccionCampo.gridy = 5;
		restriccionCampo.ipadx=100;
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=5;
		restriccionBotones.anchor= GridBagConstraints.EAST;
		restriccionBotones.gridx=1;
		restriccionBotones.ipadx=10;
		
		panel2.add(tipoMaterial,restriccionEtiquetas);
		panel2.add(campoTipoMaterial,restriccionCampo);
		panel2.add(nuevotipo,restriccionBotones);
		
		restriccionEtiquetas.gridy=6;
		restriccionCampo.gridy = 6;
		restriccionCampo.ipadx=200;
		restriccionCampo.gridwidth=2;
		
		panel2.add(editorial,restriccionEtiquetas);
		panel2.add(campoEditorial,restriccionCampo);
		
		restriccionEtiquetas.gridy=7;
		restriccionCampo.gridy = 7;
		restriccionCampo.ipadx=10;
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=7;
		restriccionBotones.ipadx=7;
		restriccionBotones.gridx=1;
		
		
		panel2.add(palabrasClave,restriccionEtiquetas);
		panel2.add(campoPalabras,restriccionCampo);
		panel2.add(nuevapalabra,restriccionBotones);
		
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
	
		JPanel pa= new JPanel();
		pa.add(campoAreas);
		panel2.add(areas,restriccionEtiquetas);
		panel2.add(campoAreas,restriccionCampo);
		panel2.add(nuevaArea,restriccionBotones);
		
		restriccionCampo.ipadx=150;
		restriccionEtiquetas.gridy=10;
		restriccionCampo.gridy=10;

		panel2.add(formato,restriccionEtiquetas);	
		panel2.add(campoFormato,restriccionCampo);	
		
		
		restriccionEtiquetas.gridy=11;
		restriccionCampo.gridy=11;

		panel2.add(softwareRecomendado,restriccionEtiquetas);	
		panel2.add(campoSofware,restriccionCampo);	
		
		
		restriccionEtiquetas.gridy=12;
		restriccionCampo.gridy=12;

		panel2.add(fechaPublicacion,restriccionEtiquetas);	
		
		panel3.add(panel2);
		
		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel5.add(scroll);
		panel.add(botonCatalogar);
		
		panel4.add(descripcion,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);
		
		panelConAutores.add(autores); 
		panelConpalabrasC.add(palabrasC);
		panelConAreas.add(areaPertenece);
		panel8.add(panelConAutores);
		panel8.add(panelConpalabrasC);
		panel8.add(panelConAreas);

		
		setLayout(new BorderLayout());
		add(indicacion,BorderLayout.NORTH);	
		add(panel8,BorderLayout.CENTER);
		add(panel3,BorderLayout.WEST);		
		add(panel4,BorderLayout.SOUTH);
		//------------------------------------------
		setVisible(true);
		setResizable(true);
		setSize(900,670);
		//-------------------------------------------
	
		
		
	}
	
	private void inicializarButton() {
		botonCatalogar= new JButton("   FINALIZAR   "); 
	    nuevaArea= new JButton("Crear Area");
	    nuevotipo= new JButton("Crear Tipo");
	    nuevoAutor = new JButton("Crear Autor");
	    nuevapalabra= new JButton("Crear Palabra");
	    
	    botonCatalogar.addActionListener(new ManejadorBoton());
	}

	private void inicializarTexfield() { 
		campoNumeroIdentificacion = new JTextField();
		campoTituloSecundario= new JTextField();    
		campoTituloPpal = new JTextField();   
		campoEditorial= new JTextField();
		campoDescripcion= new JTextArea(5,30);
		campoFormato= new JTextField();
		campoResolucion= new JTextField(); 
		campoSofware= new JTextField();
		
	}

	private void inicializarLabels(Font font1) 
	{
		
		tipoMaterial= new JLabel(" Tipo De Material:  ",JLabel.LEFT);
		tituloPrincipal= new JLabel(" Titulo Principal:  *",JLabel.LEFT);
		idioma= new JLabel(" Idioma:  *",JLabel.LEFT);
	    tituloSecundario= new JLabel(" Titulo Secundario:",JLabel.LEFT);
	    traducido= new JLabel(" Traducido a :",JLabel.LEFT);
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
 
	    Color colorletras= new Color(0,60,0);
	   
	    resolucion.setFont(font1); 
	    softwareRecomendado.setFont(font1);
	    palabrasClave.setFont(font1);
	    autor.setFont(font1);
	    descripcion.setFont(font1);
		tipoMaterial.setFont(font1);
		tituloPrincipal.setFont(font1);
		tituloSecundario.setFont(font1);
		idioma.setFont(font1);
		traducido.setFont(font1);
		editorial.setFont(font1);
		derechosAutor.setFont(font1);
		areas.setFont(font1);
		fechaPublicacion.setFont(font1);
		formato.setFont(font1);
		
		indicacion.setFont(font1);
		resolucion.setForeground(colorletras);
		softwareRecomendado.setForeground(colorletras);
		palabrasClave.setForeground(colorletras);
	    autor.setForeground(colorletras);
	    descripcion.setForeground(colorletras);
		tipoMaterial.setForeground(colorletras);
		tituloPrincipal.setForeground(colorletras);
		tituloSecundario.setForeground(colorletras);
		idioma.setForeground(colorletras);
		traducido.setForeground(colorletras);
		editorial.setForeground(colorletras);
		derechosAutor.setForeground(colorletras);
		areas.setForeground(colorletras);
		fechaPublicacion.setForeground(colorletras);
		formato.setForeground(colorletras);
	}

	private void inicializarComboBox(Font font2) 
	{
		campoAreas= new JComboBox(areasVector);
	    campoDescripcion= new JTextArea();
	    campoTraducido= new JComboBox();
	    campoIdioma= new JComboBox();
	    campoDerechosAutor= new JComboBox();
	    campoTipoMaterial= new JComboBox();
	    campoAutor = new JComboBox(autoresVector);
	    campoPalabras= new JComboBox(palabrasClaveVec);
	    //-------------------font combobox--------------------------
	    campoPalabras.setFont(font2);
	    campoAreas.setFont(font2);
	    campoDescripcion.setFont(font2);
	    campoTraducido.setFont(font2);
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
			
			
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
			
		
		}
		catch (Exception e){e.printStackTrace();}
		
		GuiCatalogarModificar ventana;
		ventana = new GuiCatalogarModificar();
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		

	}
	
	private boolean validacionDeDatos() {
		if (campoNumeroIdentificacion.getText().length()<10  &&
		campoTituloSecundario.getText().length()   <50  &&
		campoTituloPpal.getText().length() <50   &&
		campoEditorial.getText().length() <30   &&
		campoDescripcion.getText().length() < 200  &&
		campoFormato.getText().length() <5   &&
		campoResolucion.getText().length()<15 &&
		campoSofware.getText().length()<10  &&
		!campoTituloPpal.getText().isEmpty() &&
		!campoFormato.getText().isEmpty()   &&
		!campoDescripcion.getText().isEmpty()   
	//	campoIdioma.getSelectedIndex()!= -1
		)
			
			return true;
		
		return false;
		
	}
	private class ManejadorBoton implements ActionListener 
	{

		
		public void actionPerformed(ActionEvent event) 
		{
			
			if(validacionDeDatos())
				System.out.println("yujuuuuuuu");
			//doc = new Documento(null, campoIdioma.getSelectedItem(), campoDerechosAutor.getSelectedItem(), campoDescripcion.getText(), softwareRecomendado, resolucion, editorial, formato, tituloPrincipal, tituloSecundario, null, , fechaPublicacion, fechaCatalogacion, loginCatalogador, campoTipoMaterial.getSelectedItem(), autores, areas, palabrasClave)
			
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
					
					AutorIdActualVector.add((String) campoAutor.getSelectedItem());		
					
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
					palabActualVec.add((String) AutorIdVector.get(campoPalabras.getSelectedIndex()));		
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
