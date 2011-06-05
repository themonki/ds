/*
 * ControladorPalabraClave.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con las Palabras Clave 
 * de la biblioteca digital involucrando algunas de las clases en el paquete
 * y subpaquetes de GestionDocumento.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Yerminson Gonzalez
 * Version:   4.0
 */
package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoTipoMaterial;
import GestionDocumento.Logica.TipoMaterial;

/*
 * Yerminson Gonzalez
 * */
public class ControladorTipoMaterial {

	public int insertarTipoMaterial(String nombre, String descripcion) {
		
		nombre.toLowerCase();
		descripcion.toLowerCase();
		
		TipoMaterial tipoMaterial = new TipoMaterial(nombre, descripcion);
		DaoTipoMaterial daoTipoMaterial = new DaoTipoMaterial();
		int numFilas = daoTipoMaterial.guardarTipoMaterial(tipoMaterial);
		tipoMaterial = null;
		daoTipoMaterial = null;
		return numFilas;
	}

	public TipoMaterial obtenerTipoMaterial(String nombre) {
		nombre.toLowerCase();
		DaoTipoMaterial daoTipoMaterial = new DaoTipoMaterial();
		TipoMaterial tipoMaterial = daoTipoMaterial.consultarTipoMaterial(nombre);
		daoTipoMaterial = null;
		return tipoMaterial;
	}

	public Vector<TipoMaterial> obtenerTiposMateriales() {
		DaoTipoMaterial daoTioMaterial = new DaoTipoMaterial();
		Vector<TipoMaterial> todosTiposMateriales = daoTioMaterial.consultarTodosTipoMaterial();
		daoTioMaterial = null;
		return todosTiposMateriales;
	}

	/*

	 */
	public Vector<String> obtenerTodosTiposMateriales() {

		Vector<TipoMaterial> vectorTipos;
		Vector<String> nombres = new Vector<String>();

		vectorTipos = obtenerTiposMateriales();
		int cantidad = vectorTipos.size();
		for (int i = 0; i < cantidad; i++) {
			nombres.add(vectorTipos.get(i).getNombre());
		}

		return nombres;

	}
	
	public int actualizarTipoMaterial(TipoMaterial tp){
		DaoTipoMaterial daoTipo = new DaoTipoMaterial();
		tp.setDescripcion(tp.getDescripcion().toLowerCase());
		return daoTipo.modificarTipoMaterial(tp);
	}
}