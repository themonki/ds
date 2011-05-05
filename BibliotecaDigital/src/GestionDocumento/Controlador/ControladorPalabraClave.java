package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoPalabraClave;
import GestionDocumento.Logica.PalabraClave;;

public class ControladorPalabraClave {

	

	public int insertarPalabraClave(String nombre,String descripcion)
	{
		PalabraClave palabraClave = new PalabraClave(nombre,descripcion);
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		int numFilas = daoPalabraClave.guardarPalabraClave(palabraClave);
		palabraClave = null;
		daoPalabraClave = null;
		return numFilas;
	}
	
	public PalabraClave obtenerPalabraClave(String nombre)
	{
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		PalabraClave palabraClave = daoPalabraClave.consultarPalabraClave(nombre);
		daoPalabraClave = null;
		return palabraClave;
	}
	
	public Vector<PalabraClave> obtenerPalabrasClave()
	{
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		Vector<PalabraClave> palabrasClave = daoPalabraClave.consultarPalabras();
		daoPalabraClave = null;
		return palabrasClave;
	}
}