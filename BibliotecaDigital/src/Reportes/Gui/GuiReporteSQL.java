package Reportes.Gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Utilidades.Estilos;

public class GuiReporteSQL extends JPanel {
	
	JLabel etiquetaSelect;
	JTextField campoAtributos ;
	JTextArea areaConsulta;
	GuiReporteSQL(){
		initComponents();
		
	}
	private void initComponents() {
		
		etiquetaSelect= new JLabel(" SELECT  ");
		etiquetaSelect.setFont(Estilos.fontSubtitulos);
		etiquetaSelect.setForeground(Color.BLUE);
		campoAtributos= new JTextField(20);
		areaConsulta= new JTextArea(5,28);
		
		//-----------------------------------
		this.setLayout(new GridBagLayout());
		GridBagConstraints restricciones = new GridBagConstraints();
		//---------------------Agregando elementos al panel ----------
		restricciones.gridy=0;
		restricciones.insets= new Insets(10, 10, 10, 10);
		this.add(etiquetaSelect,restricciones);
		this.add(campoAtributos,restricciones);
		restricciones.gridy++;
		restricciones.gridwidth=2;
		this.add(areaConsulta,restricciones);
		
		

	

		
		
	}

}
