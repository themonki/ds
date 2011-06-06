package Reportes.Gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Utilidades.Button;
import Utilidades.Estilos;

public class GuiReporteSQL extends JTabbedPane {
	JPanel panelppal,panelConEsquemas;
	JLabel etiquetaSelect,icon;
	JTextField campoAtributos ;
	JTextArea areaConsulta;
	JTable tabla;
	Button botonConsulta;
	GuiReporteSQL(){
		initComponents();
		
	}
	private void initComponents() {
		icon= new JLabel(new ImageIcon("recursos/iconos/tabla.png"));
		icon.setText("Esquema BD");
		etiquetaSelect= new JLabel(" SELECT  ");
		etiquetaSelect.setFont(Estilos.fontSubtitulos);
		etiquetaSelect.setForeground(Color.BLUE);
		campoAtributos= new JTextField(20);
		areaConsulta= new JTextArea(5,28);
		botonConsulta= new Button(" Consultar ");
		botonConsulta.addActionListener(new Manejador());
		
		panelppal= new JPanel();
		panelConEsquemas= new JPanel();
		
		
		//-----------------------------------
		panelppal.setLayout(new GridBagLayout());
		GridBagConstraints restricciones = new GridBagConstraints();
		//---------------------Agregando elementos al panel ----------
		restricciones.gridy=0;
		restricciones.insets= new Insets(10, 10, 10, 10);
		//panelppal.add(icon,restricciones);
		//restricciones.gridy++;
		panelppal.add(etiquetaSelect,restricciones);
		panelppal.add(campoAtributos,restricciones);
		restricciones.gridy++;
		restricciones.gridwidth=2;
		panelppal.add(areaConsulta,restricciones);
		restricciones.gridy++;
		restricciones.gridwidth=1;
		restricciones.gridx=1;
		panelppal.add(botonConsulta,restricciones);
		
		
		this.addTab("",new ImageIcon("recursos/iconos/SQL.png") , panelppal);
		this.addTab("",icon.getIcon(), panelConEsquemas);
		
	
	}
	private class Manejador implements ActionListener
	{		
		
		public void actionPerformed(ActionEvent evento)
		{
			String sql =" SELECT " + campoAtributos.getText()+" "+areaConsulta.getText();		
		
		}
		
	}

}
