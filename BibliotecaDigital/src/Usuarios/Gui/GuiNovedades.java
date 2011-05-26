package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiResultadoConsulta;
import Consultas.Gui.GuiVistaDocumento;
import Utilidades.Estilos;

public class GuiNovedades extends JScrollPane
{

	private static final long serialVersionUID = 1L;


	
	
	// Nos permite saber el tipo del usuario que realiza la consulta por defecto es cero.
	public static int TIPOUSUARIO;
	
	
	public static JPanel panel;
	public static GuiResultadoConsulta resultadoConsulta;
	public static GuiVistaDocumento vistaDocumento;
	
	public GuiNovedades()
	{		
		panel = new JPanel(new BorderLayout());
		String title = "::Novedades::";

		// Linea y titulo del panel.
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Estilos.colorBorder), title);
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
	
			
		panel.setBorder(borde);

		
		
		
		
		this.setViewportView(panel);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	public static void ponerDescripcion()
	{
		panel.remove(resultadoConsulta);
		panel.add(vistaDocumento, BorderLayout.CENTER);
		panel.updateUI();
	}
	public static void restaurar()
	{
		panel.remove(vistaDocumento);		
		panel.add(resultadoConsulta, BorderLayout.CENTER );		
		panel.remove(vistaDocumento);
		panel.updateUI();
	}
	
	
	public static void main(String[] args) {
		
	
		
	
		
		JFrame ventana = new JFrame();
		Container a = ventana.getContentPane();
		
		a.add(new GuiNovedades());
		ventana.setSize(450, 450);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		
	

	}
}
