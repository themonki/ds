/*
 * ControladorPalabraClave.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con los Autores de la b-
 * iblioteca digital involucrando algunas de las clases en el paquete y su-
 * bpaquetes de GestionDocumento.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Cristian Leonardo Rios
 * Version:   4.0
 */
package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoPalabraClave;
import GestionDocumento.Logica.PalabraClave;

/**
 * Clase que permite la comunicación entre la Clase {@link GestionDocumento.Dao.DaoPalabraClave DaoPalabraClave}
 *  y las Clases
 * de aplicación: {@link GestionDocumento.Gui.GuiIngresarPalabraClave GuiIngresarPalabraClave}, 
 * y {@link GestionDocumento.Gui.GuiModificarPalabraClave GuiModificarPalabraClave} 
 * para el intercambio de datos.
 * @author 
 *
 */
public class ControladorPalabraClave {

	public int insertarPalabraClave(String nombre, String descripcion) {
		
		nombre.toLowerCase();
		descripcion.toLowerCase();
		
		PalabraClave palabraClave = new PalabraClave(nombre, descripcion);
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		int numFilas = daoPalabraClave.guardarPalabraClave(palabraClave);
		palabraClave = null;
		daoPalabraClave = null;
		return numFilas;
	}

	public PalabraClave obtenerPalabraClave(String nombre) {
		nombre.toLowerCase();
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		PalabraClave palabraClave = daoPalabraClave
				.consultarPalabraClave(nombre);
		daoPalabraClave = null;
		return palabraClave;
	}

	public Vector<PalabraClave> obtenerPalabrasClave() {
		DaoPalabraClave daoPalabraClave = new DaoPalabraClave();
		Vector<PalabraClave> palabrasClave = daoPalabraClave
				.consultarPalabras();
		daoPalabraClave = null;
		return palabrasClave;
	}
	/*

	 */
	public Vector<String> obtenerTodasPalabrasClave() {

		Vector<PalabraClave> vectorPalabras;
		Vector<String> nombres = new Vector<String>();
		vectorPalabras = obtenerPalabrasClave();
		int cantidad = vectorPalabras.size();
		for (int i = 0; i < cantidad; i++) {
			nombres.add(vectorPalabras.get(i).getNombre());
		}
		return nombres;

	}
//metodo que devuelve las palabras clave de un documento dado su llave
	public Vector <PalabraClave> obtenerPalabrasClaveDocumento(String id_documento){
		DaoPalabraClave daoPC = new DaoPalabraClave();
		return daoPC.consultarPalabrasClaveDocumento(id_documento);
	}
	
	public int actualizarPalabraClave(PalabraClave pc){
		DaoPalabraClave daoPalabra = new DaoPalabraClave();
		pc.setDescripcion(pc.getDescripcion().toLowerCase());
		return daoPalabra.modificarPalabraClave(pc);
	}
}