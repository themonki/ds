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
	
	
	public int insertarAutor(String nombre,String apellido, String acronimo, String correoElectronico)
	{
		Autor autor = new Autor(nombre,apellido,acronimo,correoElectronico,"");
		DaoAutor daoAutor = new DaoAutor();
		int numFilas = daoAutor.guardarAutor(autor);
		autor = null;
		daoAutor = null;
		return numFilas;
	}
	
	public Autor obtenerAutor(String acronimo)
	{
		DaoAutor daoAutor = new DaoAutor();
		Autor autor = daoAutor.consultarAutor(acronimo);
		daoAutor = null;
		return autor;
	}
	
	public Vector<Autor> obtenerAutores()
	{
		DaoAutor daoAutor = new DaoAutor();
		Vector<Autor> autores = daoAutor.consultarAutores();
		daoAutor = null;
		return autores;
	}
	

}