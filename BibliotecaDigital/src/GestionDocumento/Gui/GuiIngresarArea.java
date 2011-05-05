
package GestionDocumento.Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class GuiIngresarArea extends JFrame{
	
	JLabel nombre,descripcionArea,areaPadre,indicacion;
	JTextField campoNombre;
	JComboBox campoAreaPadre;		
	JTextArea campoDescripcionArea;
	JButton botonIngresarArea;
	
	JPanel panel,panel2,panel3,panel4,panel5;
	
	
	GuiIngresarArea()
	{
		initComponents();
	}
	
	public void initComponents() 
	{
		panel= new JPanel();
		panel2 = new JPanel(new GridLayout(2,2,10,10)); 
		panel3 = new JPanel(); 
		panel5 = new JPanel(new FlowLayout());
		panel4= new JPanel(new BorderLayout());
		//--------------------------------------------------------
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel5.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel.setBorder(BorderFactory.createLineBorder(Color.yellow));

		//---------------------------------------------   
		iniciarLabels();
		//---------------------------------------------
		iniciarCampos();
		//-------------------------------------------- 

		panel2.add(nombre);
		panel2.add(campoNombre);
		panel2.add(areaPadre);
		panel2.add(campoAreaPadre);
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcionArea);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		panel5.add(scroll);
		
		panel.add(botonIngresarArea);
		
		panel4.add(descripcionArea,BorderLayout.NORTH);
		panel4.add(panel5,BorderLayout.CENTER);
		panel4.add(panel,BorderLayout.SOUTH);
		
		
		setLayout(new BorderLayout());
		add(indicacion,BorderLayout.NORTH);
		add(panel3,BorderLayout.CENTER);
		add(panel4,BorderLayout.SOUTH);
		setSize(410,320);
		setVisible(true);
	}
	

	private void iniciarCampos() {
		
		campoNombre= new JTextField(15);	
		campoDescripcionArea= new JTextArea(5,20);	
		campoAreaPadre= new JComboBox();			
		botonIngresarArea= new JButton("Registrar Area");
				
	}

	private void iniciarLabels() {
		Font font1 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
		Color colorletras= new Color(0,60,0);
		
		indicacion = new JLabel("Registrar  Area ",JLabel.CENTER);
		nombre= new JLabel("Nombre Del Area :");
		descripcionArea= new JLabel("Descripcion.",JLabel.CENTER);
		areaPadre= new JLabel("Area Padre :");

		indicacion.setFont(font1);
		areaPadre.setFont(font1);
		descripcionArea.setFont(font1);
		nombre.setFont(font1);
		
		indicacion.setFont(font3);
		indicacion.setForeground(colorletras);
		areaPadre.setForeground(colorletras);
		descripcionArea.setForeground(colorletras);
		nombre.setForeground(colorletras);		
	}

	public static void main (String args []){
		
		
		try
		{
			
			
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
			
		
		}
		catch (Exception e){e.printStackTrace();}
		
		GuiIngresarArea ventana;
		ventana = new GuiIngresarArea();
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	
	}
	
}