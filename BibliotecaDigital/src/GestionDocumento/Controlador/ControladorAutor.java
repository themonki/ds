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
//metodo que devuelve los autores de un documento dado su llave
	public Vector <Autor> obtenerAutoresDocumento(String id_documento){
		DaoAutor daoAutor = new DaoAutor();
		return daoAutor.consultarAutoresDocumento(id_documento);
	}
	
	public int actualizarAutor(Autor a){
		DaoAutor daoAutor = new DaoAutor();
		String nombre= a.getNombre(), apellido = a.getApellido() ,acronimo=a.getAcronimo(),
		correoElectronico= a.getCorreo();
		
		nombre.toLowerCase();
		apellido.toLowerCase();
		acronimo.toLowerCase();
		correoElectronico.toLowerCase();
		
		a.setNombre(nombre);
		a.setAcronimo(acronimo);
		a.setApellido(apellido);
		
		return daoAutor.modificarAutor(a);
	}
}