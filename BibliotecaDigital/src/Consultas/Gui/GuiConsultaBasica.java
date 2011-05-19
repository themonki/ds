package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Consultas.Controlador.ControladorConsulta;
import Consultas.Logica.Consulta;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiConsultaBasica extends JScrollPane
{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaConsulta;
	private static  JTextField campoConsulta;
	private JPanel panelCampoConsulta;
	private Button consultar;
	private JCheckBox busquedaCompleta;
	private JComboBox campoCantidadResultados;
	private String cantidades[] = {"5","10","15","20", "25"};
	private Manejador manejador;
	// Nos permite saber el tipo del usuario que realiza la consulta por defecto es cero.
	public static int TIPOUSUARIO;
	
	private static JPanel panelConsulta;
	private JPanel panelResultado;
	static JPanel panel;
	public static GuiResultadoConsulta resultadoConsulta;
	public static GuiVistaDocumento vistaDocumento;
	
	public GuiConsultaBasica()
	{		
		panel = new JPanel(new BorderLayout());
		//Manejador de eventos
		manejador = new Manejador();
		panelConsulta = new JPanel(new GridBagLayout());
		panelResultado = new JPanel(new FlowLayout());
		
		//this.setLayout(new FlowLayout(1,150,40));
		//panelConsultaBasica = new JPanel(new FlowLayout(1,200,40));

		etiquetaConsulta = new JLabel("Consulta",JLabel.CENTER);
		etiquetaConsulta.setFont(Estilos.fontTitulo);
		etiquetaConsulta.setForeground(Estilos.colorTitulo);
		
		campoConsulta = new JTextField(30);
		campoConsulta.setFont(Estilos.fontLabels);
		
		busquedaCompleta = new JCheckBox("Realizar b�squeda con coincidencia exacta");
		busquedaCompleta.addItemListener(manejador);
		
		campoCantidadResultados = new JComboBox(cantidades);
		
		panelCampoConsulta = new JPanel(new GridLayout(2,1,5,5));
		panelCampoConsulta.add(campoConsulta);
		panelCampoConsulta.add(busquedaCompleta);
		panelCampoConsulta.add(campoCantidadResultados);
		
		consultar = new Button("Consultar");
		consultar.setIcon(new ImageIcon("recursos/iconos/search.png"));
		consultar.setHorizontalTextPosition(SwingConstants.LEFT);
		consultar.addActionListener(manejador);
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridx=1;
		restricciones.gridy=1;
		panelConsulta.add(etiquetaConsulta, restricciones);
		restricciones.gridy=2;
		panelConsulta.add(panelCampoConsulta, restricciones);
		restricciones.gridy=3;
		panelConsulta.add(consultar, restricciones);
		panel.add(panelConsulta, BorderLayout.NORTH);
		
		//add(panel);
		
		this.setViewportView(panel);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	public static void ponerDescripcion()
	{
	
		
		panel.remove(resultadoConsulta);
		panel.remove(panelConsulta);
		panel.add(vistaDocumento, BorderLayout.CENTER);
		panel.updateUI();
	}
	public static void restaurar()
	{
	
		
		panel.remove(vistaDocumento);
		//campoConsulta.setText("");
		panel.add(panelConsulta, BorderLayout.NORTH);
		panel.add(resultadoConsulta, BorderLayout.SOUTH );
		
		//resultadoConsulta = new GuiResultadoConsulta();
		panel.remove(vistaDocumento);
		panel.updateUI();
	}
	
	private class Manejador implements ActionListener, ItemListener
	{
		private boolean seleccionBusquedaCompeta = false;
		@Override
		public void actionPerformed(ActionEvent evento)
		{
			if(evento.getSource() == consultar)
			{
				//no estoy seguro de instanciar el controlador aqui
				ControladorConsulta controlador = new ControladorConsulta();
				//mira que se hace con loque retorna
				
				Vector<Consulta> vector = new Vector<Consulta>();
				
				if(!campoConsulta.getText().equals("")){
					vector = null;
					vector = controlador.consultaGeneral(campoConsulta.getText(), seleccionBusquedaCompeta);					
				}else{
					JOptionPane.showMessageDialog(null, "Por favor ingrese parametros para la busqueda",
							"No ahi parametros", JOptionPane.ERROR_MESSAGE);
				}
				
				if(resultadoConsulta!=null){
					panel.remove(resultadoConsulta);
				}else{
					resultadoConsulta=null;
					}
				resultadoConsulta = new GuiResultadoConsulta(vector,10);
				panel.add(resultadoConsulta, BorderLayout.CENTER);
				panel.updateUI();
				if(vector.size() <=0 && !campoConsulta.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
					
				}	
					
					
				
				
				System.out.println(GuiConsultaBasica.TIPOUSUARIO);
			}
			
		}
		
		public void itemStateChanged(ItemEvent evento)
		{
			
			if(evento.getStateChange() == ItemEvent.SELECTED)
			{
				seleccionBusquedaCompeta = true;
			}
			else if(evento.getStateChange() == ItemEvent.DESELECTED)
			{
				seleccionBusquedaCompeta = false;
			}
		}
	}
}
