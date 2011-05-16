package GestionDocumento.Controlador;

import java.util.Vector;

import GestionDocumento.Dao.DaoAutor;
import GestionDocumento.Logica.Autor;

/*
 * Implementado por Yerminson Gonzalez
 * Controlador Autor
 * 
 * */

public class ControladorAutor {

	public int insertarAutor(String nombre, String apellido, String acronimo,
			String correoElectronico) {
		
		nombre.toLowerCase();
		apellido.toLowerCase();
		acronimo.toLowerCase();
		correoElectronico.toLowerCase();
		
		Autor autor = new Autor(nombre, apellido, acronimo, correoElectronico,
				"");
		DaoAutor daoAutor = new DaoAutor();
		int numFilas = daoAutor.guardarAutor(autor);
		autor = null;
		daoAutor = null;
		return numFilas;
	}

	public Autor obtenerAutor(String acronimo) {
		acronimo.toLowerCase();
		DaoAutor daoAutor = new DaoAutor();
		Autor autor = daoAutor.consultarAutor(acronimo);
		daoAutor = null;
		return autor;
	}

	public Vector<Autor> obtenerAutores() {
		DaoAutor daoAutor = new DaoAutor();
		Vector<Autor> autores = daoAutor.consultarAutores();
		daoAutor = null;
		return autores;
	}

	/*
	 * METODO QUE NECESITA EL MANKO DE FELIPE
	 */
	public Vector<Vector<String>> obtenerTodosAutores() {

		Vector<Vector<String>> vectorTodosAutores = new Vector<Vector<String>>();
		Vector<Autor> vectorAutores;
		Vector<String> nombres = new Vector<String>();
		Vector<String> ids = new Vector<String>();

		vectorAutores = obtenerAutores();
		int cantidad = vectorAutores.size();
		for (int i = 0; i < cantidad; i++) {
			nombres.add(vectorAutores.get(i).getNombre());
			ids.add(vectorAutores.get(i).getId());
		}
		vectorTodosAutores.add(nombres);
		vectorTodosAutores.add(ids);

		return vectorTodosAutores;

	}

	/* main para prueba OK */
	/*
	 * public static void main(String args[]) { ControladorAutor
	 * controladorAutor = new ControladorAutor();
	 * 
	 * System.out.println(controladorAutor.insertarAutor("Edgar", "Moncada",
	 * "monki", "monkiloro@gmail.com"));
	 * System.out.println(controladorAutor.insertarAutor("Yerminson",
	 * "Gonzalez", "yermi", "yermiloro@gmail.com"));
	 * 
	 * Autor a1 = controladorAutor.obtenerAutor("monki"); Autor a2 =
	 * controladorAutor.obtenerAutor("yermi");
	 * 
	 * System.out.println(a1.getNombre()); System.out.println(a2.getNombre());
	 * 
	 * System.out.println(controladorAutor.obtenerAutores().get(0).getNombre());
	 * }
	 */

}