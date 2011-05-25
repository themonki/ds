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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private JLabel etiquetaConsulta, etiquetaCantidadResultado;
	public static  JTextField campoConsulta;
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
		etiquetaCantidadResultado= new JLabel("Resultados por pagina: ");
		
		campoConsulta = new JTextField(30);
		campoConsulta.setFont(Estilos.fontLabels);
		campoConsulta.addKeyListener(manejador);
		
		busquedaCompleta = new JCheckBox("Realizar búsqueda con coincidencia exacta");
		busquedaCompleta.addItemListener(manejador);
		
		campoCantidadResultados = new JComboBox(cantidades);
		
		
		
/////////////
		panelCampoConsulta = new JPanel(new GridBagLayout());
		GridBagConstraints restriccion = new GridBagConstraints();
		
		JPanel panelCantidadResultados = new JPanel(new FlowLayout());
		panelCantidadResultados.add(etiquetaCantidadResultado);
		panelCantidadResultados.add(campoCantidadResultados);
		
		restriccion.gridy=0;
		restriccion.anchor=GridBagConstraints.WEST;
		panelCampoConsulta.add(campoConsulta, restriccion);
		restriccion.gridy=1;
		panelCampoConsulta.add(busquedaCompleta, restriccion);
		restriccion.gridy=2;
		panelCampoConsulta.add(panelCantidadResultados, restriccion);
		
		consultar = new Button("Consultar");
		consultar.setIcon(new ImageIcon("recursos/iconos/search.png"));
		consultar.setHorizontalTextPosition(SwingConstants.LEFT);
		consultar.addActionListener(manejador);
		
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridx=1;
		restricciones.gridy=1;
		restricciones.insets= new Insets(20, 0,5, 0);
		panelConsulta.add(etiquetaConsulta, restricciones);
		restricciones.gridy=2;
		panelConsulta.add(panelCampoConsulta, restricciones);
		restricciones.gridy=3;
		restricciones.insets= new Insets(0, 0,0, 0);	
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
		panel.add(panelConsulta, BorderLayout.NORTH);
		panel.add(resultadoConsulta, BorderLayout.CENTER );		
		panel.remove(vistaDocumento);
		panel.updateUI();
	}
	public static void restaurarTodo()
	{
		if(!(vistaDocumento == null))	
			panel.remove(vistaDocumento);
		
		campoConsulta.setText("");
		panel.add(panelConsulta, BorderLayout.NORTH);		
		
		if(!(resultadoConsulta == null))
			panel.remove(resultadoConsulta);
		
		panel.updateUI();
	}
	
	private class Manejador implements ActionListener, ItemListener, KeyListener
	{
		private boolean seleccionBusquedaCompleta = false;
		@Override
		public void actionPerformed(ActionEvent evento)
		{
			if(evento.getSource() == consultar)
			{
				ControladorConsulta controlador = new ControladorConsulta();				
				Vector<Consulta> vector = new Vector<Consulta>();
				GuiResultadoConsulta.TIPOCONSULTA = 1;
				
				if(!campoConsulta.getText().equals("")){
					vector = null;
					vector = controlador.consultaGeneral(campoConsulta.getText(), seleccionBusquedaCompleta);	
				
				}else{
					JOptionPane.showMessageDialog(null, "Por favor ingrese parametros para la busqueda",
							"No hay parametros", JOptionPane.ERROR_MESSAGE);
				}
				
				if(resultadoConsulta!=null){
					panel.remove(resultadoConsulta);
				}else{
					resultadoConsulta=null;
					}
				int cantidad = Integer.parseInt((String) campoCantidadResultados.getSelectedItem());
				resultadoConsulta = new GuiResultadoConsulta(vector,cantidad);
				
				
				
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
				seleccionBusquedaCompleta = true;
			}
			else if(evento.getStateChange() == ItemEvent.DESELECTED)
			{
				seleccionBusquedaCompleta = false;
			}
		}

		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				consultar.doClick();	
		}

		public void keyReleased(KeyEvent e) {
			
		}

		
		public void keyTyped(KeyEvent e) {
			
		}
	}
}
