package Usuarios.Gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
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
	
	
	public static JPanel PANELNOVEDADES;
	public static GuiResultadoConsulta RESULTADOCONSULTA;
	public static GuiVistaDocumento VISTADOCUMENTO;
	private static TitledBorder BORDE;
	public GuiNovedades()
	{		
		PANELNOVEDADES = new JPanel(new BorderLayout());
		String title = "::Novedades::";

		// Linea y titulo del panel.

		BORDE = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Estilos.colorBorder), title);
		BORDE.setTitleColor(Estilos.colorTitulo);
		BORDE.setTitleFont(Estilos.fontTitulo);
		BORDE.setTitleJustification(TitledBorder.LEFT);
	
			
		PANELNOVEDADES.setBorder(BORDE);
		
		

		
		
		
		
		this.setViewportView(PANELNOVEDADES);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	public static void ponerDescripcion()
	{
		PANELNOVEDADES.remove(RESULTADOCONSULTA);
		PANELNOVEDADES.setBorder(null);
		PANELNOVEDADES.add(VISTADOCUMENTO, BorderLayout.CENTER);
		PANELNOVEDADES.updateUI();
	}
	public static void restaurar()
	{
		PANELNOVEDADES.remove(VISTADOCUMENTO);	
		PANELNOVEDADES.setBorder(BORDE);
		PANELNOVEDADES.add(RESULTADOCONSULTA, BorderLayout.CENTER );		
		PANELNOVEDADES.remove(VISTADOCUMENTO);
		PANELNOVEDADES.updateUI();
	}
	
	
}
