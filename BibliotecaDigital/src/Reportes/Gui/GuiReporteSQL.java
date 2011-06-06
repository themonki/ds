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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

public class GuiReporteSQL extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelppal,panelConEsquemas;
	JLabel etiquetaSelect,icon,esquemas;
	JComboBox Esquematablas;
	JTextField campoAtributos ;
	JTextArea areaConsulta;
	JTable tabla;
	Button botonConsulta,botonEsquemas;
	JPanel resultadoPanel;
	JScrollPane resultadoScroll,scrollArea;
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
		campoAtributos= new JTextField(30);
		areaConsulta= new JTextArea(5,40);
		areaConsulta.setLineWrap(true);
		botonConsulta= new Button(" Consultar ");
		botonEsquemas= new Button("Ver Esquema");
		Manejador manejador=new Manejador();
		botonEsquemas.addActionListener(manejador);
		botonConsulta.addActionListener(manejador);
		Esquematablas= new JComboBox(conReport.obtenerNombreTablas());
		Esquematablas.addActionListener(manejador);
		Esquematablas.setVisible(false);

		panelppal= new JPanel();
		//panelGlobal= new JPanel(new BorderLayout());
		this.setLayout(new BorderLayout());
		panelConEsquemas= new JPanel();
		resultadoPanel= new JPanel();
		resultadoScroll= new JScrollPane();
		scrollArea= new JScrollPane(areaConsulta);
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

		panelppal.add(scrollArea,restricciones);
		restricciones.gridy++;
		restricciones.gridwidth=1;
		restricciones.gridx=1;
		restricciones.anchor=GridBagConstraints.WEST;
		
		panelppal.add(botonEsquemas,restricciones);
		restricciones.anchor= GridBagConstraints.EAST;
		restricciones.gridx=1;
		panelppal.add(botonConsulta,restricciones);
		restricciones.gridy++;
		restricciones.gridwidth=3;
		restricciones.gridx=0;
		panelppal.add(Esquematablas,restricciones);
		//restricciones.gridy++;
		//restricciones.gridwidth=4;
		//restricciones.gridx=0;
		//panelppal.add(resultadoScroll,restricciones);

		this.add(panelppal,BorderLayout.NORTH);
		this.add(resultadoScroll,BorderLayout.CENTER);
		//---------------------------------------------
		esquemas= new JLabel();
		initEsquemas();




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
			if(evento.getSource()==botonConsulta){
				tabla=null;
				resultadoPanel.removeAll();
				String consultaSql =" SELECT " + campoAtributos.getText()+" "+areaConsulta.getText();
				tabla = conReport.consultaGenerica(consultaSql);

				if(tabla==null){return;}
				tabla.setEnabled(false);
				tabla.setBorder(BorderFactory.createTitledBorder(BorderFactory
						.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), ""));


				resultadoPanel.add(tabla);
				resultadoPanel.repaint();
				resultadoPanel.updateUI();
				resultadoScroll.setViewportView(resultadoPanel);

				System.out.println(tabla.getValueAt(0, 0));
			}

			if(evento.getSource()==botonEsquemas){
				if (Esquematablas.isVisible())
				{
					Esquematablas.setVisible(false);
					botonEsquemas.setText("Ver Esquema");

				}
				else {
					Esquematablas.setVisible(true);
					botonEsquemas.setText("Quitar Esquema");
				}


			}
			if(evento.getSource()==Esquematablas){
				JScrollPane scroll= new JScrollPane();
				JLabel etiquetaEsquema = new JLabel("",JLabel.CENTER);
				etiquetaEsquema.setFont(Estilos.fontSubtitulos);
				JFrame vistaEsquemas= new JFrame();
				String item= (String) Esquematablas.getSelectedItem();
				String atributos= ""+conReport.obtenerNombreTablas(item);


				etiquetaEsquema.setText(atributos);
				scroll.setViewportView(etiquetaEsquema);
				vistaEsquemas.setTitle(item);
				vistaEsquemas.add(scroll);
				vistaEsquemas.setVisible(true);
				System.out.println(atributos.length());
				vistaEsquemas.setSize(400,100);
				vistaEsquemas.setAlwaysOnTop(true);
				vistaEsquemas.setLocation(300,300);
				


			}

		}
	}

}
