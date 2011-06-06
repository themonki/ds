/*
 * ControladorAreaConocimiento.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con las Areas de Conoci-
 * miento de la biblioteca digital involucrando algunas de las clases en el
 * paquete y subpaquetes de GestionDocumento.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     
 * Version:   4.0
 */

package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;

/**
 * Clase que permite la comunicación entre la Clase {@link GestionDocumento.Dao.DaoAreaConocimiento DaoAreaConocimiento}
 *  y las Clases
 * de aplicación: {@link GestionDocumento.Gui.GuiInsertarArea GuiInsertarArea}, 
 * y {@link GestionDocumento.Gui.GuiModificarArea GuiModificarArea} 
 * para el intercambio de datos.
 * @author 
 *
 */
public class ControladorAreaConocimiento
{
	public int insertarAreaConocimiento(String id, String nombre,
			String descripcion, String areaPadre)
	{
		nombre.toLowerCase();
		descripcion.toLowerCase();
		
		AreaConocimiento area = new AreaConocimiento(id, nombre, descripcion,areaPadre);
		DaoAreaConocimiento daoAreaConocimiento = new DaoAreaConocimiento();
		int numFilas = daoAreaConocimiento.guardarAreaConocimiento(area);
		area = null;
		daoAreaConocimiento = null;
		return numFilas;
		
	}

	public AreaConocimiento obtenerArea(String id) {
		DaoAreaConocimiento daoAreaConocimiento = new DaoAreaConocimiento();
		AreaConocimiento area = daoAreaConocimiento.consultarArea(id);
		daoAreaConocimiento = null;
		return area;
	}

	public Vector<AreaConocimiento> obtenerAreas() {
		DaoAreaConocimiento daoAreaConocimiento = new DaoAreaConocimiento();
		Vector<AreaConocimiento> areas = daoAreaConocimiento.consultarAreas();
		daoAreaConocimiento = null;
		return areas;
	}

	public int actualizarArea(String idArea, String atributo, String valor) {
		valor.toLowerCase();
		DaoAreaConocimiento daoAreaConocimiento = new DaoAreaConocimiento();
		int numFilas = daoAreaConocimiento.modificarArea(idArea, atributo,
				valor);
		daoAreaConocimiento = null;
		return numFilas;
	}

	// Corregida letra estaba aliminar . Yermi =D
	public int eliminarArea(String idArea) {
		DaoAreaConocimiento daoAreaConocimiento = new DaoAreaConocimiento();
		int numFilas = daoAreaConocimiento.eliminarArea(idArea);
		daoAreaConocimiento = null;
		return numFilas;
	}

	/*
	 */
	public Vector<Vector<String>> obtenerTodasAreas() {

		Vector<Vector<String>> vectorTodasAreas = new Vector<Vector<String>>();
		Vector<AreaConocimiento> vectorAreas;
		Vector<String> nombres = new Vector<String>();
		Vector<String> ids = new Vector<String>();
		vectorAreas = obtenerAreas();
		int cantidad = vectorAreas.size();
		for (int i = 0; i < cantidad; i++) {
			nombres.add(vectorAreas.get(i).getNombre());
			ids.add(vectorAreas.get(i).getIdArea());
		}
		vectorTodasAreas.add(nombres);
		vectorTodasAreas.add(ids);
		return vectorTodasAreas;

	}
//metodo que devuelve las areas de conocimiento de un documento dado su llave
	public Vector <AreaConocimiento> obtenerAreasDocumento(String id_documento){
		DaoAreaConocimiento daoArea = new DaoAreaConocimiento();
		return daoArea.consultarAreasDocumento(id_documento);
	}

}