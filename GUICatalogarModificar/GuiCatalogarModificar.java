
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
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.sql.Date;

import  javax.swing.*;


public class GuiCatalogarModificar extends JFrame{
	
	 
	JPanel panel,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
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
	    fechaPublicacion= new JLabel("Fecha De Publicacion");
	     
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
		
		//panel2.setLayout(new GridLayout(5,2,10,10));	
		panel5.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel4.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel6.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel7.setBorder(BorderFactory.createLineBorder(Color.yellow));

		
		
		panel2.setLayout(new GridLayout(9,3,30,10));
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new BorderLayout());
		panel8.setLayout(new GridLayout(2,1,30,10));
		
		panel2.add(tituloPrincipal);
		panel2.add(campoTituloPpal);
		panel2.add(new JPanel());
		panel2.add(tituloSecundario);
		panel2.add(campoTituloSecundario);
		panel2.add(new JPanel());
		panel2.add(autor);
		panel2.add(campoAutor);
		panel2.add(nuevoAutor);
		panel2.add(idioma);
		panel2.add(campoIdioma);
		panel2.add(new JPanel());
		panel2.add(traducido);
		panel2.add(campoTraducido);
		panel2.add(new JPanel());
		panel2.add(tipoMaterial);
		panel2.add(campoTipoMaterial);
		panel2.add(nuevotipo);
		panel2.add(editorial);
		panel2.add(campoEditorial);
		panel2.add(new JPanel());
		panel2.add(palabrasClave);
		panel2.add(campoPalabras);
		panel2.add(nuevapalabra);
		panel2.add(derechosAutor);
		panel2.add(campoDerechosAutor);

		
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel5.add(scroll);
		panel.add(botonCatalogar);
		panel4.add(descripcion,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);
		
		JLabel 
		autores= new JLabel("Lista De Autores Actual.",JLabel.CENTER),
		palabrasC= new JLabel("Palabras Clave Actuales.");
		palabrasC.setFont(font2);
		autores.setFont(font2);
		//panel6.setLayout(new GridLayout(6,1,10,10));
		panel6.add(autores); 
		panel6.add(new JLabel("Yerminson Doney Gonzales ",JLabel.LEFT));
		panel6.add(new JLabel("Monki feo Moncada ",JLabel.LEFT));
		panel6.add(new JLabel("Cris leo Riete ",JLabel.LEFT));
		panel6.add(new JLabel("Maria Andrea Cruz Blandon   ",JLabel.LEFT));
		panel7.add(palabrasC);
		panel8.add(panel6);
		panel8.add(panel7);

		
		setLayout(new BorderLayout());
		add(indicacion,BorderLayout.NORTH);	
		add(panel8,BorderLayout.CENTER);
		add(panel3,BorderLayout.WEST);		
		add(panel4,BorderLayout.SOUTH);
		//------------------------------------------
		setVisible(true);
		setResizable(true);
		setSize(800,570);
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
