package Consultas.Gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utilidades.Button;
import Utilidades.Estilos;

public class GuiConsultaBasica extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JLabel etiquetaConsulta;
	private JTextField campoConsulta;
	private JPanel panelBotonesConsulta;
	private Button consultar;
	private Button limpiarCampoConsulta;
	private Manejador manejador;
	
	public GuiConsultaBasica()
	{
		//Estilo ---------- fuentes letras
		Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
		
		
		//Manejador de eventos
		manejador = new Manejador();
		
		this.setLayout(new FlowLayout(1,200,40));
		//panelConsultaBasica = new JPanel(new FlowLayout(1,200,40));

		etiquetaConsulta = new JLabel("Consulta",JLabel.CENTER);
		etiquetaConsulta.setFont(Estilos.fontTitulo);
		etiquetaConsulta.setForeground(Estilos.colorTitulo);
		
		
		campoConsulta = new JTextField(30);
		campoConsulta.setFont(fontLabels);
		panelBotonesConsulta = new JPanel(new GridLayout(1, 2, 5, 5));
		
		consultar = new Button("Consultar");
		consultar.setIcon(new ImageIcon("recursos/iconos/search.png"));
		consultar.setHorizontalTextPosition(SwingConstants.LEFT);

		consultar.addActionListener(manejador);
		limpiarCampoConsulta = new Button("Limpiar Campo");
		limpiarCampoConsulta.addActionListener(manejador);
		
		panelBotonesConsulta.add(consultar);
		panelBotonesConsulta.add(limpiarCampoConsulta);
		this.add(etiquetaConsulta);
		this.add(campoConsulta);
		this.add(panelBotonesConsulta);
	}
	
	private class Manejador implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
	}
}
