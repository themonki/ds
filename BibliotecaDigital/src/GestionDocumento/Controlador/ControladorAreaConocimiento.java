/*
 * Nombre: Cristian R�os.
 * Responsabilidad: Realizar la debida manipulacion de datos referentes a el area de conocimiento
 * entre la GUI y el controlado.
 * Nombre archivo: ControladorAreaConocimiento.java
 * Fecha Creacion: Mayo 03 2011
 * Fecha ultima modificaci�n: Mayo 04 2011
 * */

package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;

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
	 * METODO QUE NECESITA EL MANKO DE FELIPE
	 */
	public Vector<Vector<String>> obtenerTodasAreas() {

		Vector<Vector<String>> vectorTodasAreas = new Vector<Vector<String>>();
		Vector<AreaConocimiento> vectorAreas;
		Vector<String> nombres = new Vector<String>();
		Vector<String> ids = new Vector<String>();

		vectorAreas = obtenerAreas();
		int cantidad = vectorAreas.size();
		for (int i = 0; i < cantidad; i++) {// empieza en uno porque no se
											// necesita super
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

	/* main para prueba OK */
	/*
	 * public static void main(String args[]) { ControladorAreaConocimiento
	 * controladorAreaConocimiento = new ControladorAreaConocimiento();
	 * 
	 * 
	 * System.out.println(controladorAreaConocimiento.insertarAreaConocimiento("1"
	 * , "Bases Datos",
	 * "Mineria de datos descubrimiento de conocimiento a partir de bases de datos"
	 * , "0"));
	 * 
	 * AreaConocimiento a1 = controladorAreaConocimiento.obtenerArea("1");
	 * 
	 * 
	 * System.out.println(a1.getNombre());
	 * 
	 * 
	 * System.out.println(controladorAreaConocimiento.obtenerAreas().get(0).
	 * getDescripcion()); }
	 */

}