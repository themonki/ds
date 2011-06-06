package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiReporteSQL extends JTabbedPane {
	JPanel panelGlobal,panelppal,panelConEsquemas;
	JLabel etiquetaSelect,icon,esquemas;
	JTextField campoAtributos ;
	JTextArea areaConsulta;
	JTable tabla;
	Button botonConsulta;
	JPanel resultadoPanel;
	JScrollPane resultadoScroll;
	ControladorReportes conReport;
	GuiReporteSQL(){
		conReport= new ControladorReportes();
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
		panelGlobal= new JPanel(new BorderLayout());
		panelConEsquemas= new JPanel();
		resultadoPanel= new JPanel();
		resultadoScroll= new JScrollPane();
		//resultadoScroll.setPreferredSize(new Dimension(400, 100));
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
		//restricciones.gridy++;
		//restricciones.gridwidth=4;
		//restricciones.gridx=0;
		//panelppal.add(resultadoScroll,restricciones);
		
		panelGlobal.add(panelppal,BorderLayout.NORTH);
		panelGlobal.add(resultadoScroll,BorderLayout.CENTER);
		//---------------------------------------------
		esquemas= new JLabel();
		initEsquemas();
		
		
		this.addTab("",new ImageIcon("recursos/iconos/SQL.png") , panelGlobal);
		this.addTab("",icon.getIcon(), panelConEsquemas);
		
	
	}
	private void initEsquemas() {
		Vector <String> nada= conReport.obtenerNombreTablas();
		esquemas.setText(""+nada);
		panelConEsquemas.add(esquemas);
	
	}
	private class Manejador implements ActionListener
	{		
		
		public void actionPerformed(ActionEvent evento)
		{
			tabla=null;
			resultadoPanel.removeAll();
			String consultaSql =" SELECT " + campoAtributos.getText()+" "+areaConsulta.getText();
			tabla = conReport.consultaGenerica(consultaSql);
			if(tabla==null){return;}
			resultadoPanel.add(tabla);
			resultadoPanel.repaint();
			resultadoPanel.updateUI();
			resultadoScroll.setViewportView(resultadoPanel);
		
			System.out.println(tabla.getValueAt(0, 0));
			
			
		}
		
	}

}
