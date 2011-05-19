package Consultas.Gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Consultas.Controlador.ControladorConsulta;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiConsultaBasica extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaConsulta;
	private JTextField campoConsulta;
	private JPanel panelCampoConsulta;
	private Button consultar;
	private JCheckBox busquedaCompleta;
	private Manejador manejador;
	// Nos permite saber el tipo del usuario que realiza la consulta por defecto es cero.
	public static int TIPOUSUARIO;
	
	JPanel panel;
	
	public GuiConsultaBasica()
	{
		//Estilo ---------- fuentes letras
		Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		
		
		//Manejador de eventos
		manejador = new Manejador();
		panel = new JPanel(new FlowLayout(1,200,40));
		
		this.setLayout(new FlowLayout(1,200,40));
		//panelConsultaBasica = new JPanel(new FlowLayout(1,200,40));

		etiquetaConsulta = new JLabel("Consulta",JLabel.CENTER);
		etiquetaConsulta.setFont(Estilos.fontTitulo);
		etiquetaConsulta.setForeground(Estilos.colorTitulo);
		
		campoConsulta = new JTextField(30);
		campoConsulta.setFont(fontLabels);
		
		busquedaCompleta = new JCheckBox("Realizar b�squeda con coincidencia exacta");
		busquedaCompleta.addItemListener(manejador);
		
		panelCampoConsulta = new JPanel(new GridLayout(2,1,5,5));
		panelCampoConsulta.add(campoConsulta);
		panelCampoConsulta.add(busquedaCompleta);
		
		consultar = new Button("Consultar");
		consultar.setIcon(new ImageIcon("recursos/iconos/search.png"));
		consultar.setHorizontalTextPosition(SwingConstants.LEFT);
		consultar.addActionListener(manejador);
		
		this.add(etiquetaConsulta);
		this.add(panelCampoConsulta);
		this.add(consultar);
	}
	
	void incluirGuiResultado(GuiResultadoConsulta gr)
	{
		panel.add(gr);
		this.add(panel);
		this.updateUI();
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
				
			
				GuiResultadoConsulta resultadoConsulta = new GuiResultadoConsulta(controlador.consultaGeneral(campoConsulta.getText(), seleccionBusquedaCompeta),10);
				incluirGuiResultado(resultadoConsulta);
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
