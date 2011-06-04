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
	public static int TIPO_USUARIO;
		
	public static JPanel PANEL_NOVEDADES;
	public static GuiResultadoConsulta RESULTADO_CONSULTA;
	public static GuiVistaDocumento VISTA_DOCUMENTO;
	private static TitledBorder BORDE;
	public GuiNovedades()
	{		
		PANEL_NOVEDADES = new JPanel(new BorderLayout());
		String title = "::Novedades::";

		// Linea y titulo del panel.

		BORDE = BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Estilos.colorBorder), title);
		BORDE.setTitleColor(Estilos.colorTitulo);
		BORDE.setTitleFont(Estilos.fontTitulo);
		BORDE.setTitleJustification(TitledBorder.LEFT);
		
		PANEL_NOVEDADES.setBorder(BORDE);
		
		this.setViewportView(PANEL_NOVEDADES);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	public static void ponerDescripcion()
	{
		PANEL_NOVEDADES.remove(RESULTADO_CONSULTA);
		PANEL_NOVEDADES.setBorder(null);
		PANEL_NOVEDADES.add(VISTA_DOCUMENTO, BorderLayout.CENTER);
		PANEL_NOVEDADES.updateUI();
	}
	public static void restaurar()
	{
		PANEL_NOVEDADES.remove(VISTA_DOCUMENTO);	
		PANEL_NOVEDADES.setBorder(BORDE);
		PANEL_NOVEDADES.add(RESULTADO_CONSULTA, BorderLayout.CENTER );		
		PANEL_NOVEDADES.remove(VISTA_DOCUMENTO);
		PANEL_NOVEDADES.updateUI();
	}
	
	
}
