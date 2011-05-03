package Documento.Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.sql.Date;
import  javax.swing.*;

@SuppressWarnings({ "serial", "unused" })
public class GuiCatalogarModificar extends JFrame{
	
	 
	JPanel panel,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9;
	JLabel tipoMaterial,numeroIndentificacion,tituloPrincipal,idioma,autor,
	tituloSecundario,traducido,editorial,derechosAutor,descripcion,indicacion,
	palabrasClave,fechaPublicacion;
	
	JTextArea campoDescripcion;

	JComboBox campoPalabras,campoAutor,campoTipoMaterial,
	campoTraducido,campoIdioma,campoDerechosAutor;
	  
	JTextField campoEditorial,campoNumeroIdentificacion,campoTituloSecundario,campoTituloPpal;
	
	JButton botonCatalogar,nuevaArea,nuevotipo,nuevoAutor,nuevoidioma,nuevapalabra;
	//faltan las fechas /////////****************///
    // en caccoo falta campo editorial
	

	public GuiCatalogarModificar() {
		
		initComponents();
	}

	public void initComponents(){
		super.setTitle("Catalogar Documento");
		super.setIconImage(new ImageIcon("LOGO.png").getImage() );
		
	
		panel= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		panel4= new JPanel();
		panel5= new JPanel();
		panel6= new JPanel();
		panel7= new JPanel();
		panel8= new JPanel();
		panel9= new JPanel();
		
		Font font1 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		Font font2 = new Font("Book Antiqua",Font.BOLD, 15);
		
		//----------------------------------------------------
		tipoMaterial= new JLabel(" Tipo De Material:",JLabel.LEFT);
		tituloPrincipal= new JLabel(" Titulo Principal:",JLabel.LEFT);
		idioma= new JLabel(" Idioma:",JLabel.LEFT);
	    tituloSecundario= new JLabel(" Titulo Secundario:",JLabel.LEFT);
	    traducido= new JLabel(" Traducido a :",JLabel.LEFT);
	    editorial= new JLabel(" Editorial:",JLabel.LEFT);
	    derechosAutor= new JLabel(" Derechos De Autor:",JLabel.LEFT);
	    descripcion= new JLabel(" Descripcion:",JLabel.CENTER);
	    autor = new JLabel(" Autor:");
	    indicacion = new JLabel(" ");
	    palabrasClave= new JLabel(" Palabras Clave:");
	    fechaPublicacion= new JLabel("Fecha De Publicacion");//&&&&&&
		JLabel 
		autores= new JLabel("Lista De Autores Actual.",JLabel.CENTER),
		palabrasC= new JLabel("Palabras Clave Actuales."),
		areaPertenece= new JLabel("Areas Actuales.");
		
		
		
	    campoDescripcion= new JTextArea();
	    campoTraducido= new JComboBox();
	    campoIdioma= new JComboBox();
	    campoDerechosAutor= new JComboBox();
	    campoTipoMaterial= new JComboBox();
	    campoAutor = new JComboBox();
	    campoPalabras= new JComboBox();
	    
	    campoNumeroIdentificacion = new JTextField();
	    campoTituloSecundario= new JTextField();
	    campoTituloPpal = new JTextField();
	    campoEditorial= new JTextField();
	    
	    campoDescripcion= new JTextArea(5,30);
	    
	    botonCatalogar= new JButton("FINALIZAR"); 
	    nuevaArea= new JButton("Crear Area");
	    nuevotipo= new JButton("Crear Tipo");
	    nuevoAutor = new JButton("Crear Autor");
	    nuevapalabra= new JButton("Crear Palabra");
	    
	    Color colorletras= new Color(0,70,0);
	    
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
		//-------------------------------
		areaPertenece.setFont(font2);
		palabrasC.setFont(font2);
		autores.setFont(font2);
		areaPertenece.setForeground(colorletras);
		palabrasC.setForeground(colorletras);
		autores.setForeground(colorletras);
		//panel2.setLayout(new GridLayout(5,2,10,10));	
		panel5.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel4.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel6.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel7.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel9.setBorder(BorderFactory.createLineBorder(Color.yellow));

		
		
		panel2.setLayout(new GridBagLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new BorderLayout());
		panel8.setLayout(new GridLayout(3,1,30,10));
		
		GridBagConstraints restriccionCampo= new GridBagConstraints()
		,restriccionEtiquetas= new GridBagConstraints(),
		restriccionBotones= new GridBagConstraints();
		
		restriccionCampo.ipadx = 340;      		
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(10,40,10,10);
		
		
		restriccionEtiquetas.insets= new Insets(0,40,10,10);// espacios entre componentes
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
		restriccionCampo.gridwidth=2;
		
		panel2.add(idioma,restriccionEtiquetas);
		panel2.add(campoIdioma,restriccionCampo);
		
		
		restriccionEtiquetas.gridy=4;
		restriccionCampo.gridy = 4;
		
		panel2.add(traducido,restriccionEtiquetas);
		panel2.add(campoTraducido,restriccionCampo);
		
		restriccionEtiquetas.gridy=5;
		restriccionCampo.gridy = 5;
		restriccionCampo.ipadx=110;
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridx=2;
		restriccionBotones.gridy=5;
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
		restriccionCampo.ipadx=120;
		restriccionCampo.gridwidth=1;
		restriccionBotones.gridy=7;
		panel2.add(palabrasClave,restriccionEtiquetas);
		panel2.add(campoPalabras,restriccionCampo);
		panel2.add(nuevapalabra,restriccionBotones);
		
		restriccionEtiquetas.gridy=8;
		restriccionCampo.gridy = 8;
		panel2.add(derechosAutor,restriccionEtiquetas);
		panel2.add(campoDerechosAutor,restriccionCampo);

		
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel5.add(scroll);
		panel.add(botonCatalogar);
		panel4.add(descripcion,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);
		
	
		//panel6.setLayout(new GridLayout(6,1,10,10));
		
		panel6.add(autores); 
		panel7.add(palabrasC);
		panel9.add(areaPertenece);
		panel8.add(panel6);
		panel8.add(panel7);
		panel8.add(panel9);

		
		setLayout(new BorderLayout());
		add(indicacion,BorderLayout.NORTH);	
		add(panel8,BorderLayout.CENTER);
		add(panel3,BorderLayout.WEST);		
		add(panel4,BorderLayout.SOUTH);
		//------------------------------------------
		setVisible(true);
		setResizable(true);
		setSize(900,620);
		//-------------------------------------------
	
		
		
	}
	
	public static void main (String args []){
		try{
			
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
			
		}catch (Exception e){e.printStackTrace();}
		GuiCatalogarModificar ventana;
		ventana = new GuiCatalogarModificar();
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	
	

}
