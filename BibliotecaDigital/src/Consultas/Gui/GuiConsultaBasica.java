 /**
 * GuiConsultaBasica.java
 * 
 * Clase que representa la interfaz que permite realizar la consulta
 * general de los documentos digitales de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  
 * Version:   4.0
 */
package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Consultas.Controlador.ControladorConsulta;
import Consultas.Logica.Consulta;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiConsultaBasica extends JScrollPane
{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaConsulta, etiquetaCantidadResultado;
	public static  JTextField CAMPO_CONSULTA;
	private JPanel panelCampoConsulta;
	private Button consultar;
	private JCheckBox busquedaCompleta;
	private JComboBox campoCantidadResultados;
	private String cantidades[] = {"5","10","15","20", "25"};
	private Manejador manejador;
	// Nos permite saber el tipo del usuario que realiza la consulta por defecto es cero.
	public static int TIPO_USUARIO;
	
	private static JPanel PANEL_CONSULTA;
	static JPanel PANEL_PRINCIPAL;
	public static GuiResultadoConsulta PANEL_RESULTADO_CONSULTA;
	public static GuiVistaDetalladaConsulta PANEL_VISTA_DETALLADA_CONSULTA;
	
	public GuiConsultaBasica()
	{		
		PANEL_PRINCIPAL = new JPanel(new BorderLayout());
		//Manejador de eventos
		manejador = new Manejador();
		PANEL_CONSULTA = new JPanel(new GridBagLayout());
		
		//this.setLayout(new FlowLayout(1,150,40));
		//PANEL_CONSULTABasica = new JPanel(new FlowLayout(1,200,40));

		etiquetaConsulta = new JLabel("Consulta",JLabel.CENTER);
		etiquetaConsulta.setFont(Estilos.fontTitulo);
		etiquetaConsulta.setForeground(Estilos.colorTitulo);
		etiquetaCantidadResultado= new JLabel("Resultados por pagina: ");
		
		CAMPO_CONSULTA = new JTextField(30);
		CAMPO_CONSULTA.setFont(Estilos.fontLabels);
		CAMPO_CONSULTA.addKeyListener(manejador);
		
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
		panelCampoConsulta.add(CAMPO_CONSULTA, restriccion);
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
		PANEL_CONSULTA.add(etiquetaConsulta, restricciones);
		restricciones.gridy=2;
		PANEL_CONSULTA.add(panelCampoConsulta, restricciones);
		restricciones.gridy=3;
		restricciones.insets= new Insets(0, 0,0, 0);	
		PANEL_CONSULTA.add(consultar, restricciones);
		PANEL_PRINCIPAL.add(PANEL_CONSULTA, BorderLayout.NORTH);
		//add(PANEL_PRINCIPAL);
		
		this.setViewportView(PANEL_PRINCIPAL);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	public static void ponerDescripcion()
	{
		PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
		PANEL_PRINCIPAL.remove(PANEL_CONSULTA);
		PANEL_PRINCIPAL.add(PANEL_VISTA_DETALLADA_CONSULTA, BorderLayout.CENTER);
		PANEL_PRINCIPAL.updateUI();
	}
	public static void restaurar()
	{
		PANEL_PRINCIPAL.remove(PANEL_VISTA_DETALLADA_CONSULTA);		
		PANEL_PRINCIPAL.add(PANEL_CONSULTA, BorderLayout.NORTH);
		PANEL_PRINCIPAL.add(PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER );		
		PANEL_PRINCIPAL.remove(PANEL_VISTA_DETALLADA_CONSULTA);
		PANEL_PRINCIPAL.updateUI();
	}
	public static void restaurarTodo()
	{
		if(!(PANEL_VISTA_DETALLADA_CONSULTA == null))	
			PANEL_PRINCIPAL.remove(PANEL_VISTA_DETALLADA_CONSULTA);
		
		CAMPO_CONSULTA.setText("");
		PANEL_PRINCIPAL.add(PANEL_CONSULTA, BorderLayout.NORTH);		
		
		if(!(PANEL_RESULTADO_CONSULTA == null))
			PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
		
		PANEL_PRINCIPAL.updateUI();
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
				GuiResultadoConsulta.TIPO_CONSULTA = 1;
				
				if(!CAMPO_CONSULTA.getText().equals("")){
					vector = null;
					vector = controlador.consultaGeneral(CAMPO_CONSULTA.getText(), seleccionBusquedaCompleta);	
				
				}else{
					JOptionPane.showMessageDialog(null, "Por favor ingrese parametros para la busqueda",
							"No hay parametros", JOptionPane.ERROR_MESSAGE);
				}
				
				if(PANEL_RESULTADO_CONSULTA!=null){
					PANEL_PRINCIPAL.remove(PANEL_RESULTADO_CONSULTA);
				}else{
					PANEL_RESULTADO_CONSULTA=null;
					}
				int cantidad = Integer.parseInt((String) campoCantidadResultados.getSelectedItem());
				PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(vector,cantidad);
				
				
				
				PANEL_PRINCIPAL.add(PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER);
				PANEL_PRINCIPAL.updateUI();
				if(vector.size() <=0 && !CAMPO_CONSULTA.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
					
				}	
				System.out.println(GuiConsultaBasica.TIPO_USUARIO);
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
