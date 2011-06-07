/**
 * GuiNovedades.java
 * 
 * Clase que representa un resultado de una consulta de un usuario
 * donde se mantiene informando al usuario sobre los nuevos documentos
 * catalogados despues de su ultimo ingreso teniendolo asi actualizado 
 * en cuanto a documentos de  
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Yerminson Gonzalez Munoz
 * Version:   4.0
 */

package Usuarios.Gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Consultas.Gui.GuiResultadoConsulta;
import Consultas.Gui.GuiVistaDetalladaConsulta;
import Utilidades.Estilos;

/**
 * Clase que permite informar al usuario sobre nuevos documentos
 * que han sido catalogados depues de la fecha de ultimo acceso.
 * @author Yerminson Gonzalez Munoz
 *
 */
public class GuiNovedades extends JScrollPane
{

	private static final long serialVersionUID = 1L;
	
	// Nos permite saber el tipo del usuario que realiza la consulta por defecto es cero.
	public static int TIPO_USUARIO;
		
	public static JPanel PANEL_NOVEDADES;
	public static GuiResultadoConsulta PANEL_RESULTADO_CONSULTA;
	public static GuiVistaDetalladaConsulta PANEL_VISTA_DETALLADA_CONSULTA;
	private static TitledBorder BORDE;
	
	
	
	/**
	 * 
	 * Constructor que inicializa todos los elementos basicos de la interfaz.
	 * 
	 */
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
	
	/**
	 * Metodo que permite poner la vista detallada de un documento que 
	 * se ha consultado y se ha seleccionado de los documentos que 
	 * hacen parte del resultado.
	 */
	public static void ponerDescripcion()
	{
		PANEL_NOVEDADES.remove(PANEL_RESULTADO_CONSULTA);
		PANEL_NOVEDADES.setBorder(null);
		PANEL_NOVEDADES.add(PANEL_VISTA_DETALLADA_CONSULTA, BorderLayout.CENTER);
		PANEL_NOVEDADES.updateUI();
	}
	
	/**
	 *Metodo que permite volver al estado inicial es decir mostrar
	 *unicamente la lista de resultados de los documentos que hacen 
	 *parte de novedades 
	 */
	public static void restaurar()
	{
		PANEL_NOVEDADES.remove(PANEL_VISTA_DETALLADA_CONSULTA);	
		PANEL_NOVEDADES.setBorder(BORDE);
		PANEL_NOVEDADES.add(PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER );		
		PANEL_NOVEDADES.remove(PANEL_VISTA_DETALLADA_CONSULTA);
		PANEL_NOVEDADES.updateUI();
	}
	
	
}
