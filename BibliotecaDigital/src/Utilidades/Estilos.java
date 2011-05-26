package Utilidades;

import java.awt.Color;
import java.awt.Font;

/**
 * @author 
 *
 */
public class Estilos {
	
	/**
	 * Constructor por defecto, no inicializa nada
	 * @deprecated 
	 */
	public Estilos(){}
	
	// --------------------------Fuentes---------------------------------------------
	/**
	 * Variable que contiene las fuentes para los titulos 
	 */
	public static Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
	/**
	 * Variable que contiene las fuentes para las etiquetas
	 */
	public static Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
	/**
	 * Variable que contiene las fuentes para los subtitulos
	 */
	public static Font fontSubtitulos = new Font("Book Antiqua",Font.BOLD, 15);
	/**
	 * Variable que contiene las fuentes para los subrayados
	 */
	public static Font fontSubrayados = new Font("Book Antiqua",Font.BOLD, 15);
	
	
	//-------------------------------Color letras----------------------------
	/**
	 * Variable que contiene el color para los titulo
	 */
	public static Color colorTitulo = new Color(72,23,23);
	/**
	 * Variable que contiene el color para los subtitulos
	 */
	public static Color colorSubtitulo= new Color(160,51,51);
	/**
	 * Variable que contiene el color para las etiquetas
	 */
	public static Color colorLabels= new Color(160,51,51);
	/**
	 * Variable que contiene el color para las etiquetas2
	 */
	public static Color colorLabels2=Color.DARK_GRAY ;
	
	
	
	//------------------------------Color Border -----------------------------
	/**
	 * Variable que contiene el color para los border
	 */
	public static Color colorBorder = new Color(34,0,11);
	/**
	 * Variable que contiene el color para los lightBorder
	 */
	public static Color colorLightBorder = new Color(167,153,157);
}
