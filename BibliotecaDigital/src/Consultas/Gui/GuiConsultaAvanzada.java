package Consultas.Gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.peer.PanelPeer;

import javax.swing.*;

import Principal.Gui.GuiPrincipal;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

public class GuiConsultaAvanzada extends JFrame
{
	
	JLabel palabraClave,area ,titulo,autor,idioma,fechaPublicacion,formArchivo;
	JTextField campoPal,campoTitu;
	JComboBox palabrasContenidasClave , palabrasContenidasAutor,campoAutor,
	campoArea,campoIdioma,campoFormArchivo,fechaPublicCampo;
	
	JPanel panel1; 
	JButton botonConsultaAvanzada;
	
	GuiConsultaAvanzada(){
		
		//this.setLayout(new FlowLayout());
	
		panel1= new JPanel(true);
		
		this.add(new JButton("añañy"));
		this.setSize(500, 500);
		this.setVisible(true);
		
		
		
	}
	public static void main(String args[]) {

		try
		{				
			NimRODTheme nt = new NimRODTheme();
			nt.setPrimary1( new Color(10,10,230));
			nt.setPrimary2( new Color(110,110,150));
			nt.setPrimary3( new Color(0,0,230));
			//nt.setPrimary(new Color(100,100,100));
			//nt.setSecondary(new Color(230, 220,250));
			nt.setSecondary1(new Color(0,0,100));
			nt.setSecondary2(new Color(0, 100,0));
			nt.setSecondary3(new Color(250,250,250));
			nt.setWhite(new Color(250, 230,250));
			
			

			NimRODLookAndFeel NimRODLF = new NimRODLookAndFeel();
			NimRODLookAndFeel.setCurrentTheme( nt);
			UIManager.setLookAndFeel( NimRODLF);
			//UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
		}
		catch (Exception e){e.printStackTrace();}
	
		GuiConsultaAvanzada a = new GuiConsultaAvanzada();
		
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	

}
