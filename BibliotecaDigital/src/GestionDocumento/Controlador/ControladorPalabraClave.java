package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoPalabraClave;
import GestionDocumento.Logica.PalabraClave;



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
	 * METODO QUE NECESITA EL MANKO DE FELIPE
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
	/*
	 * public static void main(String args[]) { ControladorPalabraClave
	 * controladorPalabraClave = new ControladorPalabraClave();
	 * 
	 * 
	 * System.out.println(controladorPalabraClave.insertarPalabraClave("bases datos"
	 * , "mineria de datos"));
	 * 
	 * 
	 * PalabraClave p1 =
	 * controladorPalabraClave.obtenerPalabraClave("bases datos");
	 * 
	 * 
	 * System.out.println(p1.getNombre());
	 * 
	 * 
	 * System.out.println(controladorPalabraClave.obtenerPalabrasClave()); }
	 */

}