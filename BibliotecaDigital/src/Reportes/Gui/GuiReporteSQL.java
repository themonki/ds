/**
 * GuiReporteSQL.java
 * 
 * Clase que permite a un usuario administrador
 * realizar consultas sql mostrando el resultado ordenadamente 
 * en una tabla las consultas no tienen restriccion , siempre y 
 * cuando se realicen siguiendo el esquema dado para la base de
 * datos de la biblioteca digital 
 * 
 * JAVA version "1.6.0"
 *  
 * Autor(es):  Cristian Leonardo Rios	
 * 			   Luis Felipe Vargas	
 * 			  
 * Version:   4.0
 */


package Reportes.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Reportes.Controlador.ControladorReportes;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * 
 * Clase que permite modelar una interfaz desde la cual se pueden realizar consultas SQL
 * si se tiene conocimiento en esta area. 
 * Desde aca se puede visualizar el esquema de la base de datos para tener un conocimiento sobre 
 * cuales son los atributos de una relacion determinada.
 * 
 * @author Luis Felipe Vargas
 *
 */
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
	
	
	/**
	 * Constructor que inicializa el controlador de reportes y llama
	 * al metodo encargado de inicializar todos los componentes de la interfaz
	 * 
	 * 
	 */
	GuiReporteSQL(){
		conReport= new ControladorReportes();
		initComponents();

	}
	
	/**
	 * 
	 * Metodo que permite configurar todos los paneles de la interfaz
	 * a si mismo como su distribuccion  y el estilo que se manejara 
	 * cada uno de estos elementos se une para formar el editor 
	 * sql que permitira realizar consultas que tendran como unico
	 * limite la imaginacion y el conocimiento sql del administrador
	 * 
	 */
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
	/**
	 * Metodo que permite inicializar el esquema de la base de datos es decir el nombre de las tablas
	 */
	private void initEsquemas() {
		Vector <String> nada= conReport.obtenerNombreTablas();
		esquemas.setText(""+nada);
		panelConEsquemas.add(esquemas);

	}
	/**
	 * Metodo que permite administrar todos los eventos generados 
	 * por los botones y los cuales hacen referencia a mostrar el esquema de 
	 * la base de datos y a realizar la consulta propuesta en los campos
	 * en caso de que se presente un error se avisara de ello por lo que 
	 * no se vera ningun resultado en la consulta.
	 * @author Luis Felipe Vargas
	 *
	 */
	private class Manejador implements ActionListener
	{		

		public void actionPerformed(ActionEvent evento)
		{
			
			/*
			 * Si se solicita a la interfaz que se realice una consulta lo que se busca es
			 * tomar los valores de los campos y de esta manera crear la consulta SQL que 
			 * sera enviada al controlador y asu vez esta sera enviadad al dao quien sera
			 * el encargado de ejecutarla
			 * 
			 * 
			 * */
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
				JTextArea etiquetaEsquema = new JTextArea();
				etiquetaEsquema.setEditable(false);
				etiquetaEsquema.setLineWrap(true);
				etiquetaEsquema.setFont(Estilos.fontSubtitulos);
				etiquetaEsquema.setForeground(Estilos.colorFondoPanel);
				JFrame vistaEsquemas= new JFrame();
				String item= (String) Esquematablas.getSelectedItem();
				String atributos= item+":\n"+conReport.obtenerNombreTablas(item);


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
